/**
 * 
 */
package solutions.egen.rrs.model;

import java.sql.Date;
import java.sql.Time;

/**
 * @author Kesava
 *
 */
public class Reservation
{
	private String customerEmail = "";
	private int confNo = -1;
	private Date date = null;
	private Time time = null;
	private int partySize = 0;
	private int status = -1;
	private int tableID = -1;
	
	/**
	 * @param customerEmail
	 * @param date
	 * @param time
	 * @param partySize
	 */
	public Reservation(String customerEmail, Date date, Time time, int partySize)
	{
		this.customerEmail = customerEmail;
		this.date = date;
		this.time = time;
		this.partySize = partySize;
	}
	
	

	/**
	 * @param customerEmail
	 * @param confNo
	 * @param date
	 * @param time
	 * @param partySize
	 * @param status
	 * @param tableID
	 */
	public Reservation(String customerEmail, int confNo, Date date, Time time, int partySize, int status, int tableID)
	{
		this.customerEmail = customerEmail;
		this.confNo = confNo;
		this.date = date;
		this.time = time;
		this.partySize = partySize;
		this.status = status;
		this.tableID = tableID;
	}



	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}



	/**
	 * @param customerEmail the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}



	/**
	 * @return the confNo
	 */
	public int getConfNo() {
		return confNo;
	}



	/**
	 * @param confNo the confNo to set
	 */
	public void setConfNo(int confNo) {
		this.confNo = confNo;
	}



	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}



	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}



	/**
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}



	/**
	 * @param time the time to set
	 */
	public void setTime(Time time) {
		this.time = time;
	}



	/**
	 * @return the partySize
	 */
	public int getPartySize() {
		return partySize;
	}



	/**
	 * @param partySize the partySize to set
	 */
	public void setPartySize(int partySize) {
		this.partySize = partySize;
	}



	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}



	/**
	 * @return the tableID
	 */
	public int getTableID() {
		return tableID;
	}



	/**
	 * @param tableID the tableID to set
	 */
	public void setTableID(int tableID) {
		this.tableID = tableID;
	}
	
	
}
