package tollparking;
import exception.*;

import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpentTimeParkingTest {
	
	static String parkingName = "Spent time parking";
	static Double parkHourPrice = 2.0;
	
	static LocalDateTime entryTime = LocalDateTime.of(2020,Month.APRIL,27,6,30,40,50000);
	static LocalDateTime exitTime = LocalDateTime.of(2020,Month.APRIL,27,10,30,40,50000);
	
	@Test
	public void testCreateParkingwithDublicateSlots(){
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			ParkingSlot tkwslot1 = new ParkingSlot(CarType.E20CAR,"B1");
			ParkingSlot tkwslot2 = new ParkingSlot(CarType.E20CAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot, tkwslot1, tkwslot2)); 	
			SpentTimeParking testparking = new SpentTimeParking (parkingName,slotsparkingtest);
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		} catch (NoUniqueParkingSlotException err) {
			assertTrue(true);
		};
	}
	
	@Test
	public void testBilling() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(CarType.E20CAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot, tkwslot)); 
			SpentTimeParking testparking = new SpentTimeParking(parkingName,slotsparkingtest, parkHourPrice);
					
			ParkingTicket ticket = new ParkingTicket(stdslot, entryTime);
			Double price = testparking.billing(ticket, exitTime);
			long spentTime = (Duration.between(entryTime, exitTime)).toHours();
			Double priceTest = spentTime*parkHourPrice;
			assertEquals(priceTest,price);
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		} catch (NoUniqueParkingSlotException err) {
			assertTrue(true);
		}
		
	}

	@Test
	public void testCheckoutParkingSlot() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(CarType.E20CAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot, tkwslot)); 
			SpentTimeParking testparking = new SpentTimeParking (parkingName,slotsparkingtest, parkHourPrice);
	
			ParkingTicket ticket = new ParkingTicket(stdslot, entryTime);
			Double price = testparking.checkoutParkingSlot(ticket, exitTime);
			long spentTime = (Duration.between(entryTime, exitTime)).toHours();
			Double priceTest = spentTime*parkHourPrice;
			assertEquals(priceTest,price);
			assertTrue(stdslot.getStatus());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		} catch (NoUniqueParkingSlotException err) {
			assertTrue(true);
		}
		
	}

	@Test
	public void testGetParkingName(){
			SpentTimeParking testparking = new SpentTimeParking (parkingName);
			assertEquals(parkingName,testparking.getParkingName());
	}

	@Test
	public void testAddparkSlot() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(CarType.E20CAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot)); 
			SpentTimeParking testparking = new SpentTimeParking (parkingName,slotsparkingtest, parkHourPrice);
			
			testparking.addparkSlot(tkwslot);
			assertEquals(tkwslot,testparking.getParkingSlots().get(1));
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
		catch (ParkingSlotExistingException err) {
			fail("ParkingSlotExistingException thrown");
		} catch (NoUniqueParkingSlotException err) {
			assertTrue(true);
		}
		
	}

	@Test
	public void testAddExistingparkSlot() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(CarType.E20CAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot,tkwslot)); 
			SpentTimeParking testparking = new SpentTimeParking (parkingName,slotsparkingtest, parkHourPrice);
			
			testparking.addparkSlot(tkwslot);
			fail("Exception not thrown");
		} catch (ParkingSlotExistingException|NoUniqueParkingSlotException err) {
			assertTrue(true);
		}
		catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		} 
	}
	
	@Test
	public void testRemoveparkSlot(){
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(CarType.E20CAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot, tkwslot)); 
			SpentTimeParking testparking = new SpentTimeParking (parkingName,slotsparkingtest, parkHourPrice);
			
			testparking.removeparkSlot(stdslot);
			assertEquals(tkwslot,testparking.getParkingSlots().get(0));
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
		catch (NoParkingSlotException err) {
			fail("NoParkingSlotException thrown");
		} catch (NoUniqueParkingSlotException err) {
			assertTrue(true);
		};

	}

	@Test
	public void testRemoveEmptyparkSlot(){
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(CarType.E20CAR,"B1");			
			ParkingSlot fkwslot = new ParkingSlot(CarType.E50CAR,"C1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot, fkwslot)); 
			SpentTimeParking testparking = new SpentTimeParking (parkingName,slotsparkingtest, parkHourPrice);
			
			testparking.removeparkSlot(tkwslot);
			fail("Exception not thrown");
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
		catch (NoParkingSlotException|NoUniqueParkingSlotException err) {
			assertTrue(true);
		} 

	}
	@Test
	public void testSpentTimeinParking() {
		try {
			ParkingSlot tkwslot = new ParkingSlot(CarType.E20CAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(tkwslot)); 
			SpentTimeParking testparking = new SpentTimeParking (parkingName,slotsparkingtest, parkHourPrice);
					
			Duration spentTime = testparking.spentTimeinParking(entryTime, exitTime);
			Duration spentTimeTest = (Duration.between(entryTime, exitTime));
			
			assertEquals(spentTimeTest, spentTime);
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		} catch (NoUniqueParkingSlotException err) {
			assertTrue(true);
		};
	}

	@Test
	public void testCheckinParkingSlot() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot));
			SpentTimeParking testparking = new SpentTimeParking (parkingName,slotsparkingtest);
			ParkingTicket carticket =  testparking.checkinParkingSlot(CarType.STDCAR, entryTime);
			ParkingTicket ticketTest = new ParkingTicket(stdslot, entryTime);
			
			assertEquals(ticketTest,carticket);
			assertFalse(stdslot.getStatus());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
		catch (ParkingSlotTypesNotExistingException err) {
			fail("ParkingSlotTypesNotExistingException thrown");
		}
		catch (ParkingFullException err) {
			fail("ParkingFullException thrown");
		} catch (NoUniqueParkingSlotException err) {
			assertTrue(true);
		};
	}
	
	@Test
	public void testCheckinFullParkingSlot() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot));
			SpentTimeParking testparking = new SpentTimeParking (parkingName,slotsparkingtest);
			stdslot.setStatus(false);
			ParkingTicket carticket = testparking.checkinParkingSlot(CarType.STDCAR, entryTime);
			fail("Exception not thrown");

		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
		catch (ParkingSlotTypesNotExistingException err) {
			fail("ParkingSlotTypesNotExistingException thrown");
		}
		catch (ParkingFullException|NoUniqueParkingSlotException err) {
			assertTrue(true);
		} 
	}
	
	@Test	
	public void testCheckinWrongTypeParkingSlot1() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			String newCar = "TEST";
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot));
			SpentTimeParking testparking = new SpentTimeParking (parkingName,slotsparkingtest);
			ParkingTicket carticket = testparking.checkinParkingSlot(newCar, entryTime);
			fail("Exception not thrown");

		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
		catch (ParkingSlotTypesNotExistingException|NoUniqueParkingSlotException err) {
			assertTrue(true);
		}
		catch (ParkingFullException err) {
			fail("ParkingFullException thrown");
		} 
	}
	
	@Test	
	public void testCheckinWrongTypeParkingSlot2() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot));
			SpentTimeParking testparking = new SpentTimeParking (parkingName,slotsparkingtest);
			ParkingTicket carticket = testparking.checkinParkingSlot(CarType.E20CAR, entryTime);
			fail("Exception not thrown");

		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
		catch (ParkingSlotTypesNotExistingException err) {
			fail("ParkingSlotTypesNotExistingException thrown");
		}
		catch (ParkingFullException|NoUniqueParkingSlotException err) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testSetParkingSlots() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(CarType.E20CAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot, tkwslot)); 	
			SpentTimeParking testparking = new SpentTimeParking (parkingName);
			testparking.setParkingSlots(slotsparkingtest);
			
			assertEquals(slotsparkingtest,testparking.getParkingSlots());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		} catch (NoUniqueParkingSlotException err) {
			assertTrue(true);
		}
		
	}

	@Test
	public void testGetParkingSlots() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(CarType.E20CAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot, tkwslot)); 		
			SpentTimeParking testparking = new SpentTimeParking (parkingName,slotsparkingtest);
			
			assertEquals(slotsparkingtest,testparking.getParkingSlots());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		} catch (NoUniqueParkingSlotException err) {
			assertTrue(true);
		}
	}

	@Test
	public void testPrintExitTicket() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(CarType.E20CAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot, tkwslot)); 		
			SpentTimeParking testparking = new SpentTimeParking (parkingName,slotsparkingtest);
			ParkingTicket ticket = new ParkingTicket(stdslot, entryTime);
			Double price = testparking.billing(ticket, exitTime);
			String exitTicket = "Ticket for " + stdslot.getslotname() + ". Entry date:" + entryTime + " Bill: " +price.toString()+ " euros";
		
			assertEquals(exitTicket,testparking.exitTicket(ticket, price));
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		} catch (NoUniqueParkingSlotException err) {
			assertTrue(true);
		}
	}
}
