package tollparking;
import java.time.*;
import java.util.List;

import exception.NoUniqueParkingSlotException;

public class SpentTimeParking extends Parking{

	public Double hourPrice;
	
	static Double parkHourPrice = 2.0;
	
	public SpentTimeParking (String name) {
		super(name);	
		hourPrice = parkHourPrice;
	}
	
	public SpentTimeParking (String name, Double newHourPrice) {
		super(name);
		hourPrice = newHourPrice;
	}
	public SpentTimeParking (String name, List<ParkingSlot> newparking) throws NoUniqueParkingSlotException {
		super(name, newparking);	
		hourPrice = parkHourPrice;
	}
	
	public SpentTimeParking (String name, List<ParkingSlot> newparking, Double newHourPrice) throws NoUniqueParkingSlotException {
		super(name, newparking);
		hourPrice = newHourPrice;
	}
	/**
	 * Calculate bill according the pricing policy: 
	 * 		nb hours * hour price
	 * @param ticket
	 * 		Parkingticket which contains the entry time and the parkingslot 
	 * @param exitTime 
	 * 		Local Date Time when the car leaves
	 * @return Price
	 * 		Price in double format
	 */
	protected Double billing(ParkingTicket ticket, LocalDateTime exitTime) {
		LocalDateTime entryTime = ticket.getEntryTime();
		
		Duration spentTime = super.spentTimeinParking(entryTime, exitTime);
		long spentHours =  spentTime.toHours();
		
		return spentHours*hourPrice;
	}


}
