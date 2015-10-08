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
import solutions.egen.rrs.dao.RestaurantDao;
import solutions.egen.rrs.model.Restaurant;

/**
 * @author Kesava
 *
 */
@Path("/restaurant")
@Api(tags = {"/restaurant"})
public class RestaurantController
{
	/**
	 * Get all restaurants in the database
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Find all restaurants",
			notes = "Find all restaurants in the database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public List<Restaurant> getAllRestaurants()
	{
		RestaurantDao restDao = new RestaurantDao();
		return restDao.getAllRestaurants();
	}
	
	/**
	 * Get restaurant in the database
	 * based on confirmation Code
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Find one restaurant",
			notes = "Find restaurant in the database for a specific id")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=404, message="Not Found"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public Restaurant getRestaurant(@PathParam("id") int id)
	{
		RestaurantDao restDao = new RestaurantDao();
		return restDao.getRestaurant(id);
	}
	
	/**
	 * Create a new restaurant
	 * Returns restaurant with confirmation number and status
	 */
	@POST
	@Path("/newrestaurant")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Create a new restaurant",
			notes = "Create a new restaurant in the database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public Restaurant createRestaurant(Restaurant restaurant)
	{
		//Add the restaurant into database
		RestaurantDao restDao = new RestaurantDao();
		return restDao.createRestaurant(restaurant);
	}
	
	/**
	 * Edit the restaurant by providing a combination of
	 * new date, new time, new party size
	 * @param restaurant
	 * @return restaurant
	 */
	@PUT
	@Path("/editrestaurant")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Edit the restaurant",
			notes = "Edit the restaurant in database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=404, message="Not Found"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public Restaurant editRestaurant(Restaurant restaurant)
	{
		RestaurantDao restDao = new RestaurantDao();
		return restDao.editRestaurant(restaurant);
	}
	
	
	/**
	 * Delete the restaurant in database
	 * @param restaurant
	 */
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value = "Delete the restaurant",
			notes = "Delete the restaurant in database")
	@ApiResponses( value = {
			@ApiResponse (code=200, message="Success"),
			@ApiResponse (code=404, message="Not Found"),
			@ApiResponse (code=500, message="Internal Server Error")
			})
	public void deleteRestaurant(@PathParam("id") int id)
	{
		RestaurantDao restDao = new RestaurantDao();
		restDao.deleteRestaurant(id);
	}

}
