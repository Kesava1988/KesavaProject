/**
 * 
 */
package solutions.egen.rrs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;

import solutions.egen.rrs.model.Reservation;
import solutions.egen.rrs.model.Restaurant;
import solutions.egen.rrs.model.TableDetails;
import solutions.egen.rrs.utils.DBUtil;
import solutions.egen.rrs.utils.TableHelper;

/**
 * @author Kesava
 *
 */
public class RestaurantTablesDao
{

	/**
	 * Checks if there are any tables available for the customer 
	 * at the time and day of reservation
	 * @param reservation
	 * @param connection
	 * @param preparedStatement
	 * @param resultset
	 * @return Table details
	 */
	public TableDetails getAvailableTable(Reservation reservation)
	{
		TableDetails result = null;
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Timestamp time = reservation.getTime();
		int partySize = reservation.getPartySize();
		HashMap<Integer, Integer> tablesAssigned = new HashMap<>();
		TableHelper tHelper = new TableHelper();
		try
		{
			//Assign table and reservation are kind of dependent on each other.
			//So first create a reservation with given data, get a confirmation number
			//Use it to assign a table (if available and tentatively if auto assign is off)
			//Then update with the reservation with id of assigned table.
			
			//Get all assigned tables with size >= party size
			ps = con.prepareStatement("SELECT table_size, COUNT(table_size) from tables_assigned "
					+ "where (datetime = ? and table_size >= ?) "
					+ "group by table_size order by table_size");
			ps.setTimestamp(1, time);
			ps.setInt(2, partySize);
			rs = ps.executeQuery();			
			
			while(rs.next())
			{
				tablesAssigned.put(rs.getInt(1), rs.getInt(2));
			}
			
			//Now assign a table if available for the reservation
			result = tHelper.getAvailableTable(reservation,tablesAssigned,con,ps,rs );
			
		}
		catch (SQLException e)
		{
			//TODO add custom exception
			e.printStackTrace();
		}
		finally
		{
			DBUtil.releaseResources(con,ps,rs);
		}
		
		return result;
	}
	
	/**
	 * Assign a table for the reservation
	 * @param reservation
	 * @param tableSize 
	 * @param con
	 * @param ps
	 * @param rs
	 */
	public TableDetails assignTable(Reservation reservation, 
			int tableSize, Connection con, PreparedStatement ps, ResultSet rs)
	{
		ps = null;
		rs = null;
		TableDetails result = null;
		int partySize = reservation.getPartySize();
		//Over assigned if party size is less than table size
		int isOverAssigned = (partySize == tableSize) ? 0 : 1;
		// if auto assign is set to 0, then we assign a tentative table
		int isTentative = 1 - Restaurant.getAUTO_ASSIGN();
		
		
		try
		{
			if(isTentative == 1) //If tentative we dont need table id
			{
				ps = con.prepareStatement("INSERT INTO tables_assigned"
						+ " (table_size, conf_no, is_tentative, datetime, "
						+ "over_assigned) VALUES (?,?,?,?,?,?)");
				ps.setInt(1, tableSize);
				ps.setInt(2, reservation.getConfNo());
				ps.setInt(3, isTentative);
				ps.setTimestamp(4, reservation.getTime());
				ps.setInt(5, isOverAssigned);
				rs = ps.executeQuery();	
				result = new TableDetails();
				result.setTableAvailable(true);
				result.setTableSize(tableSize);
			}
			else
			{
				//First we need a table id
				int tableId = 0;
				ps = con.prepareStatement("SELECT restaurant_tables.table_id"
						+ "FROM restaurant_tables WHERE ( "
						+ "(restaurant_tables.table_size = ?) AND"
						+ "restaurant_tables.table_id  NOT IN "
						+ "(SELECT tables_assigned.table_id from tables_assigned"
						+ "where (tables_assigned.datetime = ?"
						+ "and tables_assigned.table_size = ? )  ) ) LIMIT 1");
				ps.setInt(1, tableSize);
				ps.setTimestamp(2, reservation.getTime());
				ps.setInt(3, tableSize);
				rs = ps.executeQuery();	
				
				tableId = rs.getInt("table_id");
				
				//Now assign table
				ps = con.prepareStatement("INSERT INTO tables_assigned"
						+ " (table_size, conf_no, is_tentative, date, time, "
						+ "table_id, over_assigned) VALUES (?,?,?,?,?,?,?)");
				ps.setInt(1, tableSize);
				ps.setInt(2, reservation.getConfNo());
				ps.setInt(3, isTentative);
				ps.setTimestamp(4, reservation.getTime());
				ps.setInt(5, tableId);
				ps.setInt(6, isOverAssigned);
				rs = ps.executeQuery();
				
				result = new TableDetails();
				result.setTableId(tableId);
				result.setTableAvailable(true);
				result.setTableSize(tableSize);
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * Delete a table which is assigned for this reservation
	 * @param reservation
	 */
	public void deleteAssignedTable(Reservation reservation)
	{
		Connection con = DBUtil.getConnection();
		int confNo = reservation.getConfNo();
		Date date = reservation.getDate();
		Timestamp time = reservation.getTime();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = con.prepareStatement("DELETE FROM tables_assigned WHERE"
					+ "(conf_no = ? AND date = ? AND time = ?)");
			ps.setInt(1, confNo);
			ps.setDate(2, date);
			ps.setTimestamp(3, time);
			rs = ps.executeQuery();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBUtil.releaseResources(con,ps,rs);
		}
	}

}
