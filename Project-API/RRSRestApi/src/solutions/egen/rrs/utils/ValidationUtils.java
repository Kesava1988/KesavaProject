/**
 * 
 */
package solutions.egen.rrs.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

import solutions.egen.rrs.exceptions.RRSException;
import solutions.egen.rrs.model.Restaurant;

/**
 * @author kesava
 *
 */
public class ValidationUtils
{

	/**
	 * Validate open and close timings
	 * for simplicity we are only validating for timing on same day
	 * open time < close time and both should be on same day
	 * @param open_time
	 * @param close_time
	 * @return
	 */
	public static boolean validateOpenCloseTimes(String open_time, String close_time) throws RRSException
	{
		GregorianCalendar openTime = validateDatetime(open_time);
		GregorianCalendar closeTime = validateDatetime(close_time);
		
		if(openTime.before(closeTime) && 
			openTime.get(Calendar.YEAR) ==  closeTime.get(Calendar.YEAR) &&
			openTime.get(Calendar.MONTH) ==  closeTime.get(Calendar.MONTH) &&
			openTime.get(Calendar.DATE) ==  closeTime.get(Calendar.DATE) )
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Date time should be in format : YYYY-MM-DD-HH-MM
	 * @param datetime
	 * @return
	 * @throws RRSException 
	 */
	public static GregorianCalendar validateDatetime(String datetime) throws RRSException
	{
		String[] tokens = datetime.split("-");
		
		if(tokens.length == 5)
		{
			//Check if we can create a calendar day with these details
			try
			{
				
				int year = Integer.parseInt(tokens[0]);
				int month = Integer.parseInt(tokens[1]);
				int date = Integer.parseInt(tokens[2]);
				int hr = Integer.parseInt(tokens[3]);
				int min = Integer.parseInt(tokens[4]);
				
				GregorianCalendar cal = new  GregorianCalendar();
				cal.setLenient(false);
				cal.set(year, month - 1, date, hr, min);
				return cal;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw new RRSException(ERROR_MESSSAGES.getErrorMessage(ERROR_CODES.INVALID_DATE_TIME) , e.getCause());
			}
		}
		else
		{
			System.out.println(ERROR_MESSSAGES.getErrorMessage(ERROR_CODES.INVALID_DATE_TIME));
			throw new RRSException(ERROR_MESSSAGES.getErrorMessage(ERROR_CODES.INVALID_DATE_TIME));
		}
	}
	
	
	/**
	 * Validate the reservation time. Check if the time falls between restaurant open and close times as well
	 * @param datetime
	 * @throws RRSException 
	 */
	public static void validateReservationtime(String datetime) throws RRSException
	{
		GregorianCalendar openTime = validateDatetime(Restaurant.getOPEN_TIME());
		GregorianCalendar closeTime = validateDatetime(Restaurant.getCLOSE_TIME());
		GregorianCalendar reservationTime = validateDatetime(datetime);
		
		if(reservationTime.before(openTime) || reservationTime.after(closeTime))
		{
			throw new RRSException(ERROR_MESSSAGES.getErrorMessage(ERROR_CODES.INVALID_RESERVATION_TIME));
		}
	}

}
