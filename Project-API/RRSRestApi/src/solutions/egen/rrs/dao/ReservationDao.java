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
import java.util.ArrayList;
import java.util.List;

import solutions.egen.rrs.model.Reservation;
import solutions.egen.rrs.model.TableDetails;
import solutions.egen.rrs.utils.DBUtil;

/**
 * @author Kesava
 *
 */
public class ReservationDao
{
	public static final int RESERVATION_SUCCESS = 1;
	public static final int RESERVATION_WAITING = 0;
	/**
	 * Get all reservations in the database
	 * @return List of reservations
	 */
	public List<Reservation> getAllReservations()
	{
		List<Reservation> result = null;
		Connection con = DBUtil.getConnection();
		Reservation reservation = null;
		String customerEmail = "";
		String first_name = "";
		String last_name = "";
		String phone = "";
		int confNo = 0;
		Timestamp datetime = null;
		int partySize = 0;
		int status = 0;
		int tableID = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			result = new ArrayList<Reservation>();
			ps = con.prepareStatement("Select * from reservations");
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				customerEmail = rs.getString("cust_email");
				confNo = rs.getInt("conf_no");
				datetime = rs.getTimestamp("datetime");
				partySize = rs.getInt("party_size");
				status = rs.getInt("status");
				tableID = rs.getInt("table_id");
				reservation = new Reservation(
						customerEmail, first_name, last_name, phone, confNo,
						datetime, partySize, status, tableID);
				result.add(reservation);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DBUtil.releaseResources(con,ps,rs);
		}
		return result;
	}
	
	/**
	 * Get all reservations in the database on particular date
	 * @return List of reservations
	 */

	public List<Reservation> getAllReservations(Timestamp datetime)
	{
		List<Reservation> result = null;
		Connection con = DBUtil.getConnection();
		Reservation reservation = null;
		String customerEmail = "";
		String first_name = "";
		String last_name = "";
		String phone = "";
		int confNo = 0;
		Timestamp time = null;
		int partySize = 0;
		int status = 0;
		int tableID = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			result = new ArrayList<Reservation>();
			ps = con.prepareStatement("Select * from reservations where datetime = ?");
			ps.setTimestamp(1, datetime);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				customerEmail = rs.getString("cust_email");
				confNo = rs.getInt("conf_no");
				time = rs.getTimestamp("datetime");
				partySize = rs.getInt("party_size");
				status = rs.getInt("status");
				tableID = rs.getInt("table_id");
				reservation = new Reservation(
						customerEmail, first_name, last_name, phone, confNo,
						time, partySize, status, tableID);
				result.add(reservation);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DBUtil.releaseResources(con,ps,rs);
		}
		
		
		return result;
	}

	/**
	 * Get reservation from dataase based on unique confirmation number
	 * @param conf_no
	 * @return Reservation
	 */
	public Reservation getReservation(int conf_no)
	{
		Reservation result = null;
		Connection con = DBUtil.getConnection();
		String customerEmail = "";
		String first_name = "";
		String last_name = "";
		String phone = "";
		Timestamp time = null;
		int partySize = 0;
		int status = 0;
		int tableID = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			
			ps = con.prepareStatement("Select * from reservations where conf_no = ?");
			ps.setInt(1, conf_no);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				customerEmail = rs.getString("cust_email");
				time = rs.getTimestamp("datetime");
				partySize = rs.getInt("party_size");
				status = rs.getInt("status");
				tableID = rs.getInt("table_id");
				result = new Reservation(
						customerEmail, first_name, last_name, phone, conf_no,
						time, partySize, status, tableID);
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
	}

	/**
	 * Create a new reservation with the details provided and store in database
	 * @param reservation
	 * @return reservation with status, confirmation number and table number(If assigned)
	 */
	public Reservation createReservation(Reservation reservation)
	{
		//TODO : need to check the table status, available tables etc
		//TODO : if status is success, put the confirmation to 1 else 0
		createNewReservation(reservation);
		
		//Now check for table and assign it
		RestaurantTablesDao rtDao = new RestaurantTablesDao();
		TableDetails td = rtDao.getAvailableTable(reservation);
		
		//Update the reservation model object
		boolean tableAvailable = td.isTableAvailable();
		int status = tableAvailable ?  RESERVATION_SUCCESS : RESERVATION_SUCCESS;
		int tableID = td.getTableId();
		reservation.setStatus(status);
		reservation.setTableID(tableID);
		
		//Update reservation in database with these details
		updateReservation(reservation);

		//DO we really need to create a new reservation object? or just return
		//the same reservation object
		return reservation;
	}


	/**
	 * Create a new reservation
	 * @param reservation
	 */
	private void createNewReservation(Reservation reservation)
	{
		Connection con = DBUtil.getConnection();
		String customerEmail = reservation.getCustomerEmail();
		int confNo;
		Timestamp time = reservation.getTime();
		int partySize = reservation.getPartySize();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//First create a reservation with default status and table ids
			//Then update based on the table availability.
			ps = con.prepareStatement("INSERT INTO reservations "
					+ "(cust_email, datetime, party_size) "
					+ "VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, customerEmail);
			ps.setTimestamp(2, time);
			ps.setInt(3, partySize);
			ps.execute();
			rs = ps.getGeneratedKeys();
			
			//Reservation is created and a confirmation number is created
			if(rs.next())
			{
				confNo = rs.getInt("conf_no");
				reservation.setConfNo(confNo);
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
	}

	/**
	 * If the reservation status is waiting, ask customer if he wants to confirm or cancel
	 * If he confirms the reservation, update it
	 * @param reservation
	 * @return reservation
	 */
	public Reservation confirmReservation(Reservation reservation)
	{
		//If the customer confirms wait status, since we already created a 
		//reservation in databse what is the need to update it again?
		
//		Connection con = DBUtil.getConnection();
//		int confNo = reservation.getConfNo();
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try
//		{
//			
//			ps = con.prepareStatement("UPDATE reservations SET confirmed = ? WHERE conf_no = ?", PreparedStatement.RETURN_GENERATED_KEYS);
//			ps.setInt(1, 1);
//			ps.setInt(2, confNo);
//			ps.execute();
//			
//			rs = ps.getGeneratedKeys();
//			
//			if(rs.next())
//			{
//				//Do nothing, we just have to update the reservation with confirmation status
//			}
//		}
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			DBUtil.releaseResources(con,ps,rs);
//		}
//		
		return reservation;
	}

	/**
	 * Edit exiting reservation with a combination of new date, new time,
	 * new party size
	 * @param reservation
	 * @return
	 */
	public Reservation editReservation(Reservation reservation)
	{
		int status = reservation.getStatus();
		
		//Since we are changing an existing reservation,
		//Delete the assigned table (if assigned)
		//Then assign a new table for updated reservation (if available)
		if(status == 1) //Table is assigned so delete it
		{
			RestaurantTablesDao rTablesDao = new RestaurantTablesDao();
			rTablesDao.deleteAssignedTable(reservation);
		}
		
		//Now assign a new table for the updated reservation
		//Now check for table and assign it
		RestaurantTablesDao rtDao = new RestaurantTablesDao();
		TableDetails td = rtDao.getAvailableTable(reservation);
		
		//Update the reservation model object
		boolean tableAvailable = td.isTableAvailable();
		status = tableAvailable ?  RESERVATION_SUCCESS : RESERVATION_SUCCESS;
		int tableID = td.getTableId();
		reservation.setStatus(status);
		reservation.setTableID(tableID);
		
		//Update reservation in database with these details
		updateReservation(reservation);

		//DO we really need to create a new reservation object? just return
		//the same reservation object
		return reservation;
	}
	
	/**
	 * Update existing reservation in database
	 * @param reservation
	 */
	private void updateReservation(Reservation reservation)
	{
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String customerEmail = reservation.getCustomerEmail();
		int confNo = reservation.getConfNo();
		Timestamp time = reservation.getTime();
		int partySize = reservation.getPartySize();
		int status = reservation.getStatus();
		int tableID = reservation.getTableID();
		try
		{
			//First create a reservation with default status and table ids
			//Then update based on the table availability.
			ps = con.prepareStatement("UPDATE reservations SET cust_email = ?,"
					+ "datetime = ?, party_size = ?, status = ?,"
					+ " table_id = ? WHERE conf_no = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, customerEmail);
			ps.setTimestamp(2, time);
			ps.setInt(3, partySize);
			ps.setInt(4, status);
			ps.setInt(5, tableID);
			ps.setInt(6, confNo);
			ps.execute();
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
	
	/**
	 * Delete a reservation in the database
	 * @param reservation
	 */
	public void deleteReservation(int conf_no)
	{
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//First create a reservation with default status and table ids
			//Then update based on the table availability.
			ps = con.prepareStatement("DELETE from reservations WHERE conf_no = ?");
			ps.setInt(1, conf_no);
			ps.execute();
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
