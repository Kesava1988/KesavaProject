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
		int confNo = 0;
		Date date = null;
		Time time = null;
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
				date = rs.getDate("date");
				time = rs.getTime("time");
				partySize = rs.getInt("party_size");
				status = rs.getInt("status");
				tableID = rs.getInt("table_no");
				reservation = new Reservation(
						customerEmail,confNo,date,time,partySize,status,tableID);
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

	public List<Reservation> getAllReservations(Date onDate)
	{
		List<Reservation> result = null;
		Connection con = DBUtil.getConnection();
		Reservation reservation = null;
		String customerEmail = "";
		int confNo = 0;
		Date date = null;
		Time time = null;
		int partySize = 0;
		int status = 0;
		int tableID = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			result = new ArrayList<Reservation>();
			ps = con.prepareStatement("Select * from reservations where date = ?");
			ps.setDate(1, onDate);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				customerEmail = rs.getString("cust_email");
				confNo = rs.getInt("conf_no");
				date = rs.getDate("date");
				time = rs.getTime("time");
				partySize = rs.getInt("party_size");
				status = rs.getInt("status");
				tableID = rs.getInt("table_no");
				reservation = new Reservation(
						customerEmail,confNo,date,time,partySize,status,tableID);
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
		int confNo = 0;
		Date date = null;
		Time time = null;
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
		Reservation result = null;
		Connection con = DBUtil.getConnection();
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
	}

	/**
	 * If the reservation status is waiting, ask customer if he wants to confirm or cancel
	 * If he confirms the reservation, update it
	 * @param reservation
	 * @return reservation
	 */
	public Reservation confirmReservation(Reservation reservation)
	{
		Connection con = DBUtil.getConnection();
		int confNo = reservation.getConfNo();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			
			ps = con.prepareStatement("UPDATE reservations SET confirmed = ? WHERE conf_no = ?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 1);
			ps.setInt(2, confNo);
			ps.executeQuery();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next())
			{
				//Do nothing, we just have to update the reservation with confirmation status
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
		
		return null;
	}
}
