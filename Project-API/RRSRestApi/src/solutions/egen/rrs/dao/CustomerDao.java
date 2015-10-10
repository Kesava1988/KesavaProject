/**
 * 
 */
package solutions.egen.rrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import solutions.egen.rrs.model.Customer;
import solutions.egen.rrs.model.Reservation;
import solutions.egen.rrs.utils.DBUtil;

/**
 * @author Kesava
 *
 */
public class CustomerDao
{
	/**
	 * Check if customer is already present in the database
	 * if he is not present in the database, add him to database
	 * @param reservation
	 */
	public void addCustomer(Reservation reservation)
	{
		Connection con = DBUtil.getConnection();
		String customerEmail = reservation.getCustomerEmail();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean customerFound = false;
		try
		{
			//First check if this customer is already present in database
			ps = con.prepareStatement("SELECT * FROM customer_details WHERE email = ?");
			ps.setString(1, customerEmail);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				// Customer is already present so we dont have to do anything
				customerFound = true;
			}
		}
		catch (SQLException e)
		{
			//TODO need custom exception
			e.printStackTrace();
		}
		finally
		{
			DBUtil.releaseResources(con,ps,rs);
		}
		
		if(!customerFound)
		{
			//Create new customer and add to database
			createNewCustomer(reservation);
		}
	}

	private void createNewCustomer(Reservation reservation)
	{
		String customerEmail = reservation.getCustomerEmail();
		String first_name = reservation.getFirst_name();
		String last_name = reservation.getLast_name();
		String phone = reservation.getPhone();
		Customer customer =  new Customer(customerEmail, 
				first_name, last_name, phone);
		
		//create customer in database
		createCustomer(customer);
	}

	/**
	 * Get all customer from database
	 * @return
	 */
	public List<Customer> getAllCustomers()
	{
		List<Customer> result = null;
		Connection con = DBUtil.getConnection();
		Customer customer = null;
		String customerEmail = "";
		String first_name = "";
		String last_name = "";
		String phone = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			result = new ArrayList<Customer>();
			ps = con.prepareStatement("Select * from customer_details");
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				first_name = rs.getString("first_name");
				last_name = rs.getString("last_name");
				phone = rs.getString("phone");
				customerEmail = rs.getString("email");
				
				customer = new Customer(customerEmail, first_name,
						last_name, phone);
				result.add(customer);
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
	 * Find customer from database based on email
	 * @param email
	 * @return
	 */
	public Customer getCustomer(String email)
	{
		Customer result = null;
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String first_name = "";
		String last_name = "";
		String phone = "";
		try
		{
			
			ps = con.prepareStatement("Select * from customer_details where email = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				first_name = rs.getString("first_name");
				last_name = rs.getString("last_name");
				phone = rs.getString("phone");
				result = new Customer(email, first_name, last_name, phone);
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
	 * Create a new customer
	 * @param customer
	 */
	public Customer createCustomer(Customer customer)
	{		
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//First check if this customer is already present in database
			ps = con.prepareStatement("INSERT INTO customer_details "
					+ "(first_name, last_name, email, phone) "
					+ "VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getEmail());
			ps.setString(4, customer.getPhone());
			ps.execute();
		}
		catch (SQLException e)
		{
			//TODO need custom exception
			e.printStackTrace();
		}
		finally
		{
			DBUtil.releaseResources(con,ps,rs);
		}
		
		return customer;
	}

	/**
	 * Edit the customer in database
	 * @param customer
	 * @return
	 */
	public Customer editCustomer(Customer customer)
	{
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//First check if this customer is already present in database
			ps = con.prepareStatement("UPDATE customer_details SET first_name = ?,"
					+ "last_name = ?, phone = ? WHERE email = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getPhone());
			ps.setString(4, customer.getEmail());
			ps.execute();
		}
		catch (SQLException e)
		{
			//TODO need custom exception
			e.printStackTrace();
		}
		finally
		{
			DBUtil.releaseResources(con,ps,rs);
		}
		
		return customer;
	}

	/**
	 * Delete a customer from database
	 * @param customer
	 */
	public void deleteCustomer(String email)
	{
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//First check if this customer is already present in database
			ps = con.prepareStatement("DELETE FROM customer_details WHERE "
					+ "email = ?");
			ps.setString(1, email);
			ps.execute();
		}
		catch (SQLException e)
		{
			//TODO need custom exception
			e.printStackTrace();
		}
		finally
		{
			DBUtil.releaseResources(con,ps,rs);
		}
	}

}
