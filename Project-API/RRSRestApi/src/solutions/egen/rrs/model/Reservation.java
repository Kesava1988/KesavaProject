/**
 * 
 */
package solutions.egen.rrs.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author Kesava
 *
 */
public class Reservation
{
	private String customerEmail = "";
	private String first_name = "";
	private String last_name = "";
	private String phone = "";
	private int confNo = -1;
	private String datetime = "";
	private int partySize = 0;
	private int status = 0;
	private int tableID = -1;
	
	/**
	 * @param customerEmail
	 * @param first_name
	 * @param last_name
	 * @param phone
	 * @param confNo
	 * @param date
	 * @param time
	 * @param partySize
	 */
	public Reservation(String customerEmail, String first_name, String last_name, 
			String phone, int confNo, Timestamp time, int partySize) {
		this.customerEmail = customerEmail;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone = phone;
		this.datetime = time;
		this.partySize = partySize;
	}

	
	/**
	 * @param customerEmail
	 * @param first_name
	 * @param last_name
	 * @param phone
	 * @param confNo
	 * @param date
	 * @param time
	 * @param partySize
	 * @param status
	 * @param tableID
	 */
	public Reservation(String customerEmail, String first_name, 
			String last_name, String phone, int confNo,
			Timestamp time, int partySize, int status, int tableID) {
		this.customerEmail = customerEmail;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone = phone;
		this.confNo = confNo;
		this.datetime = time;
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
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}


	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}


	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * @return the time
	 */
	public Timestamp getTime() {
		return datetime;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Timestamp time) {
		this.datetime = time;
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
