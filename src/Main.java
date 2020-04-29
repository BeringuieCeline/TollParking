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
			
			// Define EntryTime and ExitTime for test purpose
			LocalDateTime carEntryTime = LocalDateTime.of(2020, Month.APRIL, 27, 6, 30, 40, 50000);
			LocalDateTime carExitTime = LocalDateTime.of(2020, Month.APRIL, 27, 10, 30, 40, 50000);
			LocalDateTime carEntryTime2 = LocalDateTime.of(2020, Month.APRIL, 27, 11, 30, 40, 50000);
			LocalDateTime carExitTime2 = LocalDateTime.of(2020, Month.APRIL, 27, 15, 30, 40, 50000);

			// Create a list of Parking slots
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR, "A1");
			ParkingSlot e20slot = new ParkingSlot(CarType.E20CAR, "A2");
			ParkingSlot e50slot = new ParkingSlot(CarType.E50CAR, "A3");
			List<ParkingSlot> slotsparkinglist = new ArrayList<>(Arrays.asList(stdslot, e20slot, e50slot)); 	

			//Create a Fixed Amount policy parking using the list of parking slots
			FixedAmountParking faParking = new FixedAmountParking("Fixed Amount parking",slotsparkinglist);
			
			System.out.print(faParking.toString());
			
			// Check in a car
			ParkingTicket stdticket = faParking.checkinParkingSlot(CarType.STDCAR, carEntryTime);
			System.out.println(stdticket);	
			
			// Check out the car
			Double bill  = faParking.checkoutParkingSlot(stdticket, carExitTime);
			
			System.out.println(faParking.exitTicket(stdticket, bill));
					
			// Create an empty Spent time policy parking
			SpentTimeParking stParking = new SpentTimeParking("Spent Time parking");
			
			// Fill it with different types of parking slots
			for(int i = 1; i < 11; i++) {
				String stdname="A" + i;
				stParking.addparkSlot(new ParkingSlot(CarType.STDCAR, stdname));
			}
			for(int i = 1; i < 11; i++) {
				String e20name="B" + i;
				stParking.addparkSlot(new ParkingSlot(CarType.E20CAR, e20name));
			}
			for(int i = 1; i< 11; i++) {
				String e50name="C" + i;
				stParking.addparkSlot(new ParkingSlot(CarType.E50CAR, e50name));
			}
			
			System.out.print(stParking.toString());
			
			// Check in a first car
			ParkingTicket e20ticket1 = stParking.checkinParkingSlot(CarType.E20CAR, carEntryTime);
			System.out.println(e20ticket1);			
			
			// Check in a second car
			ParkingTicket e20ticket2 = stParking.checkinParkingSlot(CarType.E20CAR, carEntryTime);
			System.out.println(e20ticket2);			
			
			// Check out the first car
			Double bill1  = stParking.checkoutParkingSlot(e20ticket1, carExitTime);
			System.out.println(stParking.exitTicket(e20ticket1, bill1));
			
			// Check in a third car
			ParkingTicket e20ticket3 = stParking.checkinParkingSlot(CarType.E20CAR, carEntryTime2);
			System.out.println(e20ticket3);	
			
			// Check out the second car
			Double bill2  = stParking.checkoutParkingSlot(e20ticket2, carExitTime);
			System.out.println(stParking.exitTicket(e20ticket2, bill2));
			
			// Check out the third car			
			Double bill3  = stParking.checkoutParkingSlot(e20ticket3, carExitTime2);
			System.out.println(stParking.exitTicket(e20ticket3, bill3));
			
		} 
		catch (NotAvailableSlotTypeException|NoUniqueParkingSlotException|ParkingSlotTypesNotExistingException|ParkingFullException|ParkingSlotExistingException err) {
			System.out.println(err);
		}

	}

}
