/**
 * 
 */
package tollparking;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exception.*;

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
			
			LocalDateTime carEntryTime = LocalDateTime.of(2020,Month.APRIL,27,6,30,40,50000);
			LocalDateTime carExitTime = LocalDateTime.of(2020,Month.APRIL,27,10,30,40,50000);
			LocalDateTime carEntryTime2 = LocalDateTime.of(2020,Month.APRIL,27,11,30,40,50000);
			LocalDateTime carExitTime2 = LocalDateTime.of(2020,Month.APRIL,27,15,30,40,50000);
			
			ParkingSlot stdslot = new ParkingSlot("std","A1");
			ParkingSlot e20slot = new ParkingSlot("20kw","A2");
			ParkingSlot e50slot = new ParkingSlot("50kw","A3");
			List<ParkingSlot> slotsparkinglist = new ArrayList<>(Arrays.asList(stdslot, e20slot, e50slot)); 	

			FixedAmountParking FAParking = new FixedAmountParking("Fixed Amount parking",slotsparkinglist);
			
			System.out.println(FAParking);
			
			ParkingTicket stdticket = FAParking.checkinParkingSlot(stdcar, carEntryTime);
			System.out.println(stdticket);			
			
			Double bill  = FAParking.checkoutParkingSlot(stdticket, carExitTime);
			
			System.out.println(FAParking.exitTicket(stdticket, bill));
					
			SpentTimeParking STParking = new SpentTimeParking("Spent Time parking");
			
			for(int i=1;i<11;i++) {
				String stdname="A"+i;
				STParking.addparkSlot(new ParkingSlot("std",stdname));
			}
			for(int i=1;i<11;i++) {
				String e20name="B"+i;;
				STParking.addparkSlot(new ParkingSlot("20kw",e20name));
			}
			for(int i=1;i<11;i++) {
				String e50name="C"+i;
				STParking.addparkSlot(new ParkingSlot("50kw",e50name));
			}
			
			System.out.println(STParking);
			
			ParkingTicket e20ticket1 = STParking.checkinParkingSlot(e20car, carEntryTime);
			System.out.println(e20ticket1);			
			
			ParkingTicket e20ticket2 = STParking.checkinParkingSlot(e20car, carEntryTime);
			System.out.println(e20ticket2);			
			
			Double bill1  = STParking.checkoutParkingSlot(e20ticket1, carExitTime);
			
			System.out.println(STParking.exitTicket(e20ticket1, bill1));
			
			Double bill2  = STParking.checkoutParkingSlot(e20ticket2, carExitTime);
			
			System.out.println(STParking.exitTicket(e20ticket2, bill2));
			
			ParkingTicket e20ticket3 = STParking.checkinParkingSlot(e20car, carEntryTime2);
			System.out.println(e20ticket3);			
			
			Double bill3  = STParking.checkoutParkingSlot(e20ticket3, carExitTime2);
			
			System.out.println(STParking.exitTicket(e20ticket3, bill3));
			
			


		} catch (NotAvailableSlotTypeException err) {
			System.out.println(err);
		}
		catch (NoUniqueParkingSlotException err){
			System.out.println(err);
		} catch (ParkingSlotTypesNotExistingException err) {
			System.out.println(err);
		} catch (ParkingFullException err) {
			System.out.println(err);
		} catch (ParkingSlotExistingException err) {
			System.out.println(err);
		}

	}

}
