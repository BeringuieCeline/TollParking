package tollparking;
import java.time.*;

public class ParkingTicket {
   
	public LocalDateTime entryTime;
	public ParkingSlot parkslot;
	
	public ParkingTicket(ParkingSlot newparkslot, LocalDateTime newentryTime) {
		entryTime = newentryTime;
		parkslot = newparkslot;
	}
	/**
	 * Get the entry time
	 * @return Local date time  
	 */
	public LocalDateTime getEntryTime() {
		return entryTime;
	}
	/**
	 * Get parking slot atteached to the ticket
	 * @return Parking slot
	 */
	public ParkingSlot getParkSlot() {
		return parkslot;
	}
	/**
	 * Get slot name
	 * @return slot name as a string
	 */
	public String getSlotname() {
		return parkslot.getslotname();
	}
	
	public String toString() {
		
		return "Ticket for " + parkslot.getslotname() + ". Entry date:" + entryTime.toString();
		
	}
	
	@Override
	public boolean equals(Object o) {
		
		ParkingTicket ticket = (ParkingTicket) o;
		return ticket.entryTime.equals(entryTime) && ticket.parkslot.equals(parkslot);
		
	}
	

}
