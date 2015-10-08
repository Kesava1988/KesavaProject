/**
 * 
 */
package solutions.egen.rrs.controllers;

import java.sql.Date;
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
import solutions.egen.rrs.dao.ReservationDao;
import solutions.egen.rrs.model.Reservation;

/**
 * @author Kesava
 * Reservation controller for creating, updating, deleting reservations
 */

@Path("/reservation")
@Api(tags = {"/reservation"})
public class ReservationController
{
	/**
	 * Get all reservations in the database
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Find all reservations",
			notes = "Find all reservations in the database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public List<Reservation> getAllReservations()
	{
		ReservationDao rDao = new ReservationDao();
		return rDao.getAllReservations();
	}
	
	/**
	 * Get all reservations in the database
	 * on specific date
	 */
	@GET
	@Path("/ondate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Find reservations for a specific date",
			notes = "Find all reservations in the database for a specific date")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=404, message="Not Found"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public List<Reservation> getAllReservations(Date date)
	{
		ReservationDao rDao = new ReservationDao();
		return rDao.getAllReservations(date);
	}
	
	/**
	 * Get reservation in the database
	 * based on confirmation Code
	 */
	@GET
	@Path("/{conf_no}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Find one reservation",
			notes = "Find reservation in the database for a specific confirmation number")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=404, message="Not Found"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public Reservation getReservation(@PathParam("conf_no") int conf_no)
	{
		ReservationDao rDao = new ReservationDao();
		return rDao.getReservation(conf_no);
	}
	
	/**
	 * Create a new reservation
	 * Returns reservation with confirmation number and status
	 */
	@POST
	@Path("/reserve")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Create a new reservation",
			notes = "Create a new reservation in the database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public Reservation createReservation(Reservation reservation)
	{
		//Add the customer into database
		CustomerDao custDao = new CustomerDao();
		custDao.addCustomer(reservation);
		ReservationDao rDao = new ReservationDao();
		return rDao.createReservation(reservation);
	}
	
	/**
	 * Confirm the reservation if it is in waiting status
	 */
	@PUT
	@Path("/confirm")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Confirm the reservation",
			notes = "Confirm the reservation in database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=404, message="Not Found"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public Reservation confirmReservation(Reservation reservation)
	{
		ReservationDao rDao = new ReservationDao();
		return rDao.confirmReservation(reservation);
	}
	
	/**
	 * Edit the reservation by providing a combination of
	 * new date, new time, new party size
	 * @param reservation
	 * @return reservation
	 */
	@PUT
	@Path("/editreservation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Edit the reservation",
			notes = "Edit the reservation in database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=404, message="Not Found"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public Reservation editReservation(Reservation reservation)
	{
		ReservationDao rDao = new ReservationDao();
		return rDao.editReservation(reservation);
	}
	
	
	/**
	 * Delete the reservation in database
	 * @param reservation
	 */
	@DELETE
	@Path("/{conf_no}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Delete the reservation",
			notes = "Delete the reservation in database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=404, message="Not Found"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public void deleteReservation(@PathParam("id") int conf_no)
	{
		ReservationDao rDao = new ReservationDao();
		rDao.deleteReservation(conf_no);
	}
}
