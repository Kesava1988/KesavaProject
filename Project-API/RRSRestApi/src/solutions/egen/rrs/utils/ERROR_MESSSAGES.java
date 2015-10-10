/**
 * 
 */
package solutions.egen.rrs.utils;

/**
 * @author kesava
 *
 */
public class ERROR_MESSSAGES
{
	public static ERROR_CODES lastKnownError = ERROR_CODES.NO_ERROR;
	
	private static final String INVALID_DATE_TIME_MESSAGE = "Entered date time is not valid, "
			+ "please enter in YYYY-MM-DD-HH-MIN format";
	private static final String INVALID_RESTAURANT_ID_MESSAGE = "There is no restaurant with the provided ID";

	private static final String INVALID_OPEN_CLOSE_TIMES_MESSAGE = "Open and close timings are not valid. Both should be on same day"
			+ " and Open time should be before close time";

	private static final String INVALID_RESERVATION_TIME_MESSAGE = "Reservation should be in restaurant operating times only. "
			+ "Enter a valid reservation time between restaurant open and close times. Reservation time can not be in the past";

	private static final String INVALID_PARTY_SIZE_MESSAGE = "Party size should be a positive integer value";

	private static final String INVALID_CONF_NO_MESSAGE = "There is no reservation with the provided confirmation number";
	
	
	public static String getErrorMessage(ERROR_CODES errorCode)
	{
		lastKnownError = errorCode;
		
		switch(errorCode)
		{
			case INVALID_DATE_TIME:
				return INVALID_DATE_TIME_MESSAGE;
			case INVALID_RESTAURANT_ID:
				return INVALID_RESTAURANT_ID_MESSAGE;
			case INVALID_OPEN_CLOSE_TIMES:
				return INVALID_OPEN_CLOSE_TIMES_MESSAGE;
			case INVALID_RESERVATION_TIME:
				return INVALID_RESERVATION_TIME_MESSAGE;
			case INVALID_PARTY_SIZE:
				return INVALID_PARTY_SIZE_MESSAGE;
			case INVALID_CONF_NO:
				return INVALID_CONF_NO_MESSAGE;
			default:
				return "NOT A VALID ERROR CODE";
		}
	}
}
