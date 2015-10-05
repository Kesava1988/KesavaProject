/**
 * 
 */
package solutions.egen.rrs.controllers;

import java.sql.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import solutions.egen.rrs.dao.ReservationDao;
import solutions.egen.rrs.model.Reservation;

/**
 * @author Kesava
 * Reservation controller for creating, updating, deleting reservations
 */

@Path("/reservation")
public class ReservationController
{
	/**
	 * Get all reservations in the database
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
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
	@Path("/{confo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Reservation getReservation(@PathParam("confNo") int conf_no)
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
	public Reservation createReservation(Reservation reservation)
	{
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
	public Reservation editReservation(Reservation reservation)
	{
		ReservationDao rDao = new ReservationDao();
		return rDao.editReservation(reservation);
	}
}
