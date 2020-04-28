package tollparking;
import java.time.*;
import java.util.List;

import exception.NoUniqueParkingSlotException;

public class FixedAmountParking extends Parking{

	private Double fixedAmount;
	private Double hourPrice;
	
	static Double parkFixedAmount = 5.3;
	static Double parkHourPrice = 2.0;
	
	public FixedAmountParking (String name) {
		super(name);
		fixedAmount = parkFixedAmount;	
		hourPrice = parkHourPrice;
	}
	
	public FixedAmountParking (String name, Double newFixedAmount, Double newHourPrice) {
		super(name);
		fixedAmount = newFixedAmount;
		hourPrice = newHourPrice;
	}
	
	public FixedAmountParking (String name, List<ParkingSlot> newparking) throws NoUniqueParkingSlotException {
		super(name, newparking);
		fixedAmount = parkFixedAmount;	
		hourPrice = parkHourPrice;
	}
	
	public FixedAmountParking (String name, List<ParkingSlot> newparking, Double newFixedAmount, Double newHourPrice) throws NoUniqueParkingSlotException {
		super(name, newparking);
		fixedAmount = newFixedAmount;
		hourPrice = newHourPrice;
	}
	/**
	 * Set a new fixed amount
	 * @param newfixedAmount
	 */		
	public void setfixedAmount(Double newfixedAmount) {
		fixedAmount = newfixedAmount;
	}
	/**
	 * Set a new park Hour Price
	 * @param newparkHourPrice
	 */		
	public void setparkHourPrice(Double newparkHourPrice) {
		parkHourPrice = newparkHourPrice;
	}
	
	/**
	 * Calculate bill according the pricing policy: 
	 * 		fixed amount + nb hours * hour price
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
		
		return fixedAmount + spentHours*hourPrice;
	}
	
}


