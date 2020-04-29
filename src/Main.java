/**
 * 
 */

import exception.*;
import tollparking.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




/**
 * @author proes
 *
 */

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String stdcar = "std";
			String e20car = "20kw";
			String e50car = "50kw";
			
			LocalDateTime carEntryTime = LocalDateTime.of(2020, Month.APRIL, 27, 6, 30, 40, 50000);
			LocalDateTime carExitTime = LocalDateTime.of(2020, Month.APRIL, 27, 10, 30, 40, 50000);
			LocalDateTime carEntryTime2 = LocalDateTime.of(2020, Month.APRIL, 27, 11, 30, 40, 50000);
			LocalDateTime carExitTime2 = LocalDateTime.of(2020, Month.APRIL, 27, 15, 30, 40, 50000);

			ParkingSlot stdslot = new ParkingSlot("std", "A1");
			ParkingSlot e20slot = new ParkingSlot("20kw", "A2");
			ParkingSlot e50slot = new ParkingSlot("50kw", "A3");
			List<ParkingSlot> slotsparkinglist = new ArrayList<>(Arrays.asList(stdslot, e20slot, e50slot)); 	

			FixedAmountParking faParking = new FixedAmountParking("Fixed Amount parking",slotsparkinglist);
			
			System.out.print(faParking.toString());
			
			ParkingTicket stdticket = faParking.checkinParkingSlot(stdcar, carEntryTime);
			System.out.println(stdticket);	
			
			Double bill  = faParking.checkoutParkingSlot(stdticket, carExitTime);
			
			System.out.println(faParking.exitTicket(stdticket, bill));
					
			SpentTimeParking stParking = new SpentTimeParking("Spent Time parking");
			
			for(int i = 1; i < 11; i++) {
				String stdname="A" + i;
				stParking.addparkSlot(new ParkingSlot("std", stdname));
			}
			for(int i = 1; i < 11; i++) {
				String e20name="B" + i;
				stParking.addparkSlot(new ParkingSlot("20kw", e20name));
			}
			for(int i = 1; i< 11; i++) {
				String e50name="C" + i;
				stParking.addparkSlot(new ParkingSlot("50kw", e50name));
			}
			
			System.out.print(stParking.toString());
			
			ParkingTicket e20ticket1 = stParking.checkinParkingSlot(e20car, carEntryTime);
			System.out.println(e20ticket1);			
			
			ParkingTicket e20ticket2 = stParking.checkinParkingSlot(e20car, carEntryTime);
			System.out.println(e20ticket2);			
			
			Double bill1  = stParking.checkoutParkingSlot(e20ticket1, carExitTime);
			
			System.out.println(stParking.exitTicket(e20ticket1, bill1));
			
			Double bill2  = stParking.checkoutParkingSlot(e20ticket2, carExitTime);
			
			System.out.println(stParking.exitTicket(e20ticket2, bill2));
			
			ParkingTicket e20ticket3 = stParking.checkinParkingSlot(e20car, carEntryTime2);
			System.out.println(e20ticket3);			
			
			Double bill3  = stParking.checkoutParkingSlot(e20ticket3, carExitTime2);
			
			System.out.println(stParking.exitTicket(e20ticket3, bill3));
			
		} 
		catch (NotAvailableSlotTypeException|NoUniqueParkingSlotException|ParkingSlotTypesNotExistingException|ParkingFullException|ParkingSlotExistingException err) {
			System.out.println(err);
		}

	}

}