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

import solutions.egen.rrs.model.Owner;
import solutions.egen.rrs.utils.DBUtil;

/**
 * @author Kesava
 *
 */
public class OwnerDao
{

	/**
	 * Add owner into database
	 * @param owner
	 */
	public void addOwner(Owner owner)
	{		
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//First check if this owner is already present in database
			ps = con.prepareStatement("INSERT INTO owner "
					+ "(email, password) "
					+ "VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, owner.getEmail());
			ps.setString(2, owner.getPassword());
			ps.executeQuery();
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

	/**
	 * Get all owner from database
	 * @return
	 */
	public List<Owner> getAllOwners()
	{
		List<Owner> result = null;
		Connection con = DBUtil.getConnection();
		Owner owner = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			result = new ArrayList<Owner>();
			ps = con.prepareStatement("SELECT * FROM owner");
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				owner = new Owner();
				owner.setEmail(rs.getString("email"));
				owner.setPassword(rs.getString("password"));
				result.add(owner);
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
	 * Find owner from database based on email
	 * @param email
	 * @return
	 */
	public Owner getOwner(String email)
	{
		Owner result = null;
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			
			ps = con.prepareStatement("SELECT * FROM owner WHERE email = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				result = new Owner();
				result.setEmail(rs.getString("email"));
				result.setPassword(rs.getString("password"));
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
	 * Edit the owner in database
	 * @param owner
	 * @return
	 */
	public Owner editOwner(Owner owner)
	{
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//First check if this owner is already present in database
			ps = con.prepareStatement("UPDATE owner SET password = ?"
					+ " WHERE email = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, owner.getPassword());
			ps.setString(2, owner.getEmail());
			ps.executeQuery();
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
		
		return owner;
	}

	/**
	 * Delete a owner from database
	 * @param owner
	 */
	public void deleteOwner(String email)
	{
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			//First check if this owner is already present in database
			ps = con.prepareStatement("DELETE FROM owner WHERE "
					+ "email = ?");
			ps.setString(1, email);
			ps.executeQuery();
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
