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
import solutions.egen.rrs.utils.DBUtil;

/**
 * @author Kesava
 *
 */
public class ReservationDao
{
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
	 * @return
	 */
	public Reservation createReservation(Reservation reservation)
	{
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
			
			ps = con.prepareStatement("INSERT INTO reservations "
					+ "(cust_email, date, time, party_size, status, table_no, conf_no) "
					+ "VALUES (?,?,?,?,?,?,?)");
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
}
