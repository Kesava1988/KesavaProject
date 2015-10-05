/**
 * 
 */
package solutions.egen.rrs.model;

/**
 * @author Kesava
 *	Helper method to assist in knowing if a table is available for reservation
 */
public class TableDetails
{
	private int tableNo = -1;
	private int tableSize = 0;
	private boolean isTableAvailable = false;
	
	/**
	 * @param tableNo
	 * @param tableSize
	 * @param isTableAvailable
	 */
	public TableDetails(int tableNo, int tableSize, boolean isTableAvailable)
	{
		this.tableNo = tableNo;
		this.tableSize = tableSize;
		this.isTableAvailable = isTableAvailable;
	}

	/**
	 * @return the tableNo
	 */
	public int getTableNo() {
		return tableNo;
	}

	/**
	 * @param tableNo the tableNo to set
	 */
	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	/**
	 * @return the tableSize
	 */
	public int getTableSize() {
		return tableSize;
	}

	/**
	 * @param tableSize the tableSize to set
	 */
	public void setTableSize(int tableSize) {
		this.tableSize = tableSize;
	}

	/**
	 * @return the isTableAvailable
	 */
	public boolean isTableAvailable() {
		return isTableAvailable;
	}

	/**
	 * @param isTableAvailable the isTableAvailable to set
	 */
	public void setTableAvailable(boolean isTableAvailable) {
		this.isTableAvailable = isTableAvailable;
	}
	
	
}
