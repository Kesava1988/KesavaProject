/**
 * 
 */
package solutions.egen.rrs.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import solutions.egen.rrs.dao.CustomerDao;
import solutions.egen.rrs.model.Customer;

/**
 * @author Kesava
 *
 */
@Path("/customer")
@Api(tags = {"/customer"})
public class CustomerController
{
	/**
	 * Get all customers in the database
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Find all customers",
			notes = "Find all customers in the database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public List<Customer> getAllCustomers()
	{
		CustomerDao custDao = new CustomerDao();
		return custDao.getAllCustomers();
	}
	
	/**
	 * Get customer in the database
	 * based on confirmation Code
	 */
	@GET
	@Path("/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Find one customer",
			notes = "Find customer in the database for a specific email")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=404, message="Not Found"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public Customer getCustomer(@PathParam("email") String email)
	{
		CustomerDao custDao = new CustomerDao();
		return custDao.getCustomer(email);
	}
	
	/**
	 * Create a new customer
	 * Returns customer with confirmation number and status
	 */
	@POST
	@Path("/newcustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Create a new customer",
			notes = "Create a new customer in the database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public Customer createCustomer(Customer customer)
	{
		//Add the customer into database
		CustomerDao custDao = new CustomerDao();
		return custDao.createCustomer(customer);
	}
	
	/**
	 * Edit the customer by providing a combination of
	 * new date, new time, new party size
	 * @param customer
	 * @return customer
	 */
	@PUT
	@Path("/editcustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Edit the customer",
			notes = "Edit the customer in database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=404, message="Not Found"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public Customer editCustomer(Customer customer)
	{
		CustomerDao custDao = new CustomerDao();
		return custDao.editCustomer(customer);
	}
	
	
	/**
	 * Delete the customer in database
	 * @param customer
	 */
	@DELETE
	@Path("/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Delete the customer",
			notes = "Delete the customer in database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=404, message="Not Found"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public void deleteCustomer(@PathParam("email") String email)
	{
		CustomerDao custDao = new CustomerDao();
		custDao.deleteCustomer(email);
	}
}
