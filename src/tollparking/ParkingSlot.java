package tollparking;
import exception.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class ParkingSlot {
	// Parking slot status - true if free otherwise false
	private boolean status;
	private String parktype;
	private String slotname;
	protected static final List<String> AVAILABLESLOTTYPES = new ArrayList<>(Arrays.asList("std", "20kw", "50kw"));
	protected static String notAvailableType = "Slot type not available, please choose one in the following list: std, 20kw or 50kw ";
	
	/**
	 * Create a new parking slot
	 * 
	 * @param newparktype
	 * 		Type of the parking slot
	 * 		String of the following list: 
	 * 		- "std" : standard parking slot for sedan cars (gasoline-powered)
	 * 		- "20kw": parking slot with 20kw power supply for electric cars
	 * 		- "50kw": parking slot with 50kw power supply for electric cars
	 * @param newslotname
	 * 		Slot designation as a string
	 * @throws NotAvailableSlotTypeException if the entered newparktype is not part of the possible list
	 */
	public ParkingSlot(String newparktype, String newslotname) throws NotAvailableSlotTypeException {
		status=true;
		if (AVAILABLESLOTTYPES.contains(newparktype)) {
			parktype=newparktype;
		}
		else {
			throw new NotAvailableSlotTypeException(notAvailableType);
		}
		slotname = newslotname;
	}

	/**
	 * Get parking slot status
	 * @return True if free otherwise false
	 */
	public boolean getStatus(){return status;}
	/**
	 * Set parking slot status
	 * @param newStatus
	 * 		New status of the parking slot: true if free, false if occupied
	 */
	public void setStatus(boolean newStatus) {
		status=newStatus;
	}
	
	/**
	 * Get type of the parking slot
	 * @return parktype
	 * 		Type of the parking slot
	 * 		String of the following list: 
	 * 		- "std" : standard parking slot for sedan cars (gasoline-powered)
	 * 		- "20kw": parking slot with 20kw power supply for electric cars
	 * 		- "50kw": parking slot with 50kw power supply for electric cars
	 */
	public String getparktype(){return parktype;}
	/**
	 * Set type of the parking slot
	 * @param newparktype
	 * 		String of the following list: 
	 * 		- "std" : standard parking slot for sedan cars (gasoline-powered)
	 * 		- "20kw": parking slot with 20kw power supply for electric cars
	 * 		- "50kw": parking slot with 50kw power supply for electric cars
	 * @throws NotAvailableSlotTypeException if the entered newparktype is not part of the possible list
	 */
	public void setparktype(String newparktype) throws NotAvailableSlotTypeException {
		if (AVAILABLESLOTTYPES.contains(newparktype)) {
			parktype=newparktype;
		}
		else {
			throw new NotAvailableSlotTypeException(notAvailableType);
		}
		
	}
	/**
	 * Get parking slot name
	 * @return slotname
	 * 		Unique designation of the parking slot
	 */
	public String getslotname(){return slotname;}
	/**
	 * Set parking slot nqme
	 * @param newslotname
	 * 		String which contains the new name of the parking slot
	 */
	public void setslotname(String newslotname) {
		slotname=newslotname;
	}
	
	public String toString() {
		
		return "Parking slot " + slotname + ", type: " + parktype + " is: " + status;

	}
}

