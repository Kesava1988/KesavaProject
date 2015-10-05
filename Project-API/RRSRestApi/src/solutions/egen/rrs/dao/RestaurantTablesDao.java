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

import solutions.egen.rrs.model.Reservation;
import solutions.egen.rrs.model.TableDetails;
import solutions.egen.rrs.utils.DBUtil;

/**
 * @author Kesava
 *
 */
public class RestaurantTablesDao
{

	/**
	 * Checks if there are any tables available for the party size 
	 * at the time and day of reservation
	 * @param con
	 * @param date
	 * @param time
	 * @param partySize
	 * @return Table details
	 */
	public static TableDetails getAvailableTable(Connection con, Date date, Time time, int partySize)
	{
		TableDetails result = null;
		
		String customerEmail = reservation.getCustomerEmail();
		int confNo = reservation.getConfNo();
		Date date = reservation.getDate();
		Time time = reservation.getTime();
		int partySize = reservation.getPartySize();
		int status = reservation.getStatus();
		int tableID = reservation.getTableID();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			TableDetails td = RestaurantTablesDao.getAvailableTable(con,date,time,partySize);
			if(td.isTableAvailable())
			{
				status = RESERVATION_SUCCESS;
			}
			else
			{
				status = RESERVATION_WAITING;
			}
			ps = con.prepareStatement("INSERT INTO reservations "
					+ "(cust_email, date, time, party_size, status, table_no, conf_no) "
					+ "VALUES (?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.executeQuery();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next())
			{
				customerEmail = rs.getString("cust_email");
				confNo = rs.getInt("conf_no");
				date = rs.getDate("date");
				time = rs.getTime("time");
				partySize = rs.getInt("party_size");
				status = rs.getInt("status");
				tableID = rs.getInt("table_no");
				result = new Reservation(
						customerEmail,confNo,date,time,partySize,status,tableID);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBUtil.releaseResources(con,ps,rs);
		}
		
		return result;
		
		return null;
	}

}
