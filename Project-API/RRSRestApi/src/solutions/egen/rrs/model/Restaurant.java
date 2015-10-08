/**
 * 
 */
package solutions.egen.rrs.model;

import java.sql.Time;

/**
 * @author Kesava
 *
 */
public class Restaurant
{
	private String name = "";
	private Time open_time = null;
	private Time close_time = null;
	private String address1 = "";
	private String address2 = "";
	private String city = "";
	private String state = "";
	private int zip = 0;
	private String email = "";
	private String phone = "";
	private int table_1 = 0;
	private int table_2 = 0;
	private int table_4 = 0;
	private int table_6 = 0;
	private int table_8 = 0;
	private int id = 0;
	
	//TODO make sure that this is set when auto assign is changes in restaurant Dao
	private static int AUTO_ASSIGN = 1; //1 is for true , 0 is for false

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the open_time
	 */
	public Time getOpen_time() {
		return open_time;
	}

	/**
	 * @param open_time the open_time to set
	 */
	public void setOpen_time(Time open_time) {
		this.open_time = open_time;
	}

	/**
	 * @return the close_time
	 */
	public Time getClose_time() {
		return close_time;
	}

	/**
	 * @param close_time the close_time to set
	 */
	public void setClose_time(Time close_time) {
		this.close_time = close_time;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public int getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(int zip) {
		this.zip = zip;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the table_1
	 */
	public int getTable_1() {
		return table_1;
	}

	/**
	 * @param table_1 the table_1 to set
	 */
	public void setTable_1(int table_1) {
		this.table_1 = table_1;
	}

	/**
	 * @return the table_2
	 */
	public int getTable_2() {
		return table_2;
	}

	/**
	 * @param table_2 the table_2 to set
	 */
	public void setTable_2(int table_2) {
		this.table_2 = table_2;
	}

	/**
	 * @return the table_4
	 */
	public int getTable_4() {
		return table_4;
	}

	/**
	 * @param table_4 the table_4 to set
	 */
	public void setTable_4(int table_4) {
		this.table_4 = table_4;
	}

	/**
	 * @return the table_6
	 */
	public int getTable_6() {
		return table_6;
	}

	/**
	 * @param table_6 the table_6 to set
	 */
	public void setTable_6(int table_6) {
		this.table_6 = table_6;
	}

	/**
	 * @return the table_8
	 */
	public int getTable_8() {
		return table_8;
	}

	/**
	 * @param table_8 the table_8 to set
	 */
	public void setTable_8(int table_8) {
		this.table_8 = table_8;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the aUTO_ASSIGN
	 */
	public static int getAUTO_ASSIGN() {
		return AUTO_ASSIGN;
	}

	/**
	 * @param aUTO_ASSIGN the aUTO_ASSIGN to set
	 */
	public static void setAUTO_ASSIGN(int aUTO_ASSIGN) {
		AUTO_ASSIGN = aUTO_ASSIGN;
	}
}
