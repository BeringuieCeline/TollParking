package tollparking;
import java.util.*;

import exception.*;

import java.time.*;

public abstract class Parking {
	// list of all the available slots for & parking std, 20kw or 50kw)
	private List<ParkingSlot> parkingslots;
	private String parkingName;
	
	static String slotNotUnique="Parking slots list contains mutliple slots with same name";
	static String full="Parking full: no place available";
	static String slotExisting="Slot parking cannot be added: already existing";
	static String noSlotExisting="Slot parking cannot be removed: not existing";
	static String wrongCartype="Wrong car type: should be part of the followong list:\"std\", \"20kw\", \"50kw\"";
	
	public Parking(String name) {
		parkingslots = new ArrayList<>();
		parkingName = name;
	}
	
	public Parking(String name, List<ParkingSlot> newparking) throws NoUniqueParkingSlotException {
		if (checkparkingSlotOccurence(newparking)) {
			parkingslots = newparking;
			parkingName = name;
		} 
		else {
			throw new NoUniqueParkingSlotException(slotNotUnique);
		}
	}
	
	public String getParkingName() {
		return parkingName;
	}
	
	/**
	 * Check if every 
	 * @param newparking
	 * @return
	 */
	private boolean checkparkingSlotOccurence(List<ParkingSlot> newparking) {
		
		boolean parkingSlotUnique = true;
		List<String> temp = new ArrayList<>();
		
		for ( ParkingSlot parkslot:newparking) {
			if (temp.contains(parkslot.getslotname())) {
				parkingSlotUnique = false;
			}
			else {
				temp.add(parkslot.getslotname());
			}
		}
		
		return parkingSlotUnique;
	}
	
	/**
	 * Calculate bill according the pricing policy of the choosen parking
	 * @param ticket
	 * 		Parkingticket which contains the entry time and the parkingslot 
	 * @param exitTime 
	 * 		Local Date Time when the car leaves
	 * @return Price
	 * 		Price in double format
	 */
	protected abstract Double billing(ParkingTicket ticket, LocalDateTime exitTime);

	/**
	 * Give the bill and free the parking slot when the car leaves.
	 * 
	 * @param carticket
	 * 		Parking ticket which contains parking slot and entry time
	 * @param exitTime 
	 * 		Local Date Time when the car leaves
	 * @return Price
	 * 		Price in double format
	 */
	public Double checkoutParkingSlot(ParkingTicket carticket, LocalDateTime exitTime) {
		Double bill =  billing(carticket, exitTime);
		freeSlot(carticket);
		
		return bill;
		
	}
	
	/**
	 * Set a list which contains the parking slots	
	 * @param newparkingslots
	 * 		List of parking slots
	 */
	public void setParkingSlots(List<ParkingSlot> newparkingslots) throws NoUniqueParkingSlotException {
		if(checkparkingSlotOccurence(newparkingslots)) {
			parkingslots = newparkingslots;
		} 
		else{
			
		}
		
	}
	
	/**
	 * Return a list which contains the parking slots
	 * @return
	 * 		parkingslots
	 */	
	public List<ParkingSlot> getParkingSlots(){
		return parkingslots;
	}
	
	/**
	 * Add a new parking slot to parking
	 * @param newparkslot
	 * 		New parking slot to be added
	 * @throws ParkingSlotExistingException in case the slot to be added already exists int he parking
	 */
	
	public void addparkSlot(ParkingSlot newparkslot)  throws ParkingSlotExistingException{
		boolean alreadyExisting = false;
		
		for ( ParkingSlot parkslot:parkingslots) {
			if (parkslot.getslotname().equals(newparkslot.getslotname())) {
				alreadyExisting = true;
				break;
			}	
		} 
		if ( !alreadyExisting ) {
			parkingslots.add(newparkslot);
		}
		else{
			throw new ParkingSlotExistingException(slotExisting);
		}
	}
	/**
	 * Delete existing parking slot from parking
	 * @param oldparkslot
	 * 		Parking slot to be deleted
	 * @throws NoParkingSlotException in case the parking slot does not exist in the parking
	 */
	public void removeparkSlot(ParkingSlot oldparkslot) throws NoParkingSlotException {
		boolean alreadyExisting = false;
		
		for ( ParkingSlot parkslot:parkingslots) {
			if (parkslot.getslotname().equals(oldparkslot.getslotname()) ) {
				alreadyExisting = true;
				parkingslots.remove(oldparkslot);
				break;
			}	
		}
		if(!alreadyExisting) {
			throw new NoParkingSlotException(noSlotExisting);
		}
	
	}
	/**
	 * Find the parking slot corresponding to the entry car according is type (std, 20kw or 50kw)
	 * @param car
	 * 		Entry car; designed by is type:
	 * 	 	- "std" : standard parking slot for sedan cars (gasoline-powered)
	 * 		- "20kw": parking slot with 20kw power supply for electric cars
	 * 		- "50kw": parking slot with 50kw power supply for electric cars
	 * @return Parking slot
	 * 		Corresponding parking slot
	 * @throws ParkingFullException in case the parking is full
	 * @throws ParkingSlotTypesNotExistingException in case of wrong car type: not existing in the parking slot type list
	 */
	private ParkingSlot findSlot(String car) throws ParkingSlotTypesNotExistingException,ParkingFullException{
		if (!(ParkingSlot.AVAILABLESLOTTYPES.contains(car))) {
			throw new ParkingSlotTypesNotExistingException(wrongCartype);
		}
		for ( ParkingSlot parkslot:parkingslots) {
			if ((car.equals(parkslot.getparktype())) && (parkslot.getStatus())){
				parkslot.setStatus(false);
				return parkslot;
				}	
		}throw new ParkingFullException(full);	
	} 
	/**
	 * Free parking slot when car leaves
	 * @param carticket
	 * 		Ticket given to the car during slot attribution, contains parking slot
	 * 		
	 */
	private void freeSlot(ParkingTicket carticket) {
		String carSlotName = carticket.getSlotname();
		for ( ParkingSlot parkslot:parkingslots) {
			if (carSlotName.equals(parkslot.getslotname()) ) {
				parkslot.setStatus(true);
			}
		}				
	}
	
	/**
	 * Creation of the parking ticket which will be given to the car during slot attribution
	 * @param parkslot
	 * 		Parking slot given to the car
	 * @param entryTime 
	 * 		Local date time when car enters in the parking
	 * @return Ticket
	 * 		Parking ticket which contains parking slot and entry time
	 */
	private ParkingTicket createTicket(ParkingSlot parkslot, LocalDateTime entryTime) {
		return new ParkingTicket(parkslot, entryTime);
	}
	
	/**
	 *  Calculate spent time in the parking
	 * @param entryTime 
	 * 		Local date time when car enters in the parking
	 * @param exitTime 
	 * 		Local date time when car leaves the parking
	 * @return Duration
	 */
	public Duration spentTimeinParking(LocalDateTime entryTime, LocalDateTime exitTime) {
		
		return Duration.between(entryTime, exitTime); 
	}
	/**
	 * Give a ticket to the entry car according is type, indicating the given parking slot and the entry time
	 * 
	 * @param car
	 * 		Entry car; designed by is type:
	 * 	 	- "std" : standard parking slot for sedan cars (gasoline-powered)
	 * 		- "20kw": parking slot with 20kw power supply for electric cars
	 * 		- "50kw": parking slot with 50kw power supply for electric cars
	 * @param entryTime 
	 * 		Local date time when car enters in the parking
	 * @return Ticket
	 * 		Parking ticket which contains parking slot and entry time
	 * @throws ParkingFullException in case the parking is full
	 * @throws ParkingSlotTypesNotExistingException in case of wrong car type: not existing in the parking slot type list
	 */
	public ParkingTicket checkinParkingSlot(String car, LocalDateTime entryTime) throws ParkingSlotTypesNotExistingException,ParkingFullException{
		ParkingSlot carSlot = findSlot(car);
		ParkingTicket carTicket = createTicket(carSlot, entryTime);
		
		return carTicket;
	}
	
	/**
	 * To print the exit ticket
	 * 
	 * @param carticket
	 * 		Parking ticket
	 * @param bill
	 * 		Bill to pay
	 */
	public String exitTicket(ParkingTicket carticket, Double bill) {
		String slotname = carticket.getSlotname();
		String entrytime = carticket.entryTime.toString();
		
		String exitTicket = "Ticket for " + slotname + ". Entry date:" + entrytime + " Bill: " +bill.toString()+ " €"; 
		
		return exitTicket;
	}
	
	public String toString() {
		
		String parkingslotsString = "Parking slots for "+ parkingName +" : ";
		for ( ParkingSlot parkslot:parkingslots) {
			parkingslotsString = parkingslotsString + parkslot + " - ";
			}
		
		return parkingslotsString;
	}
	
}


