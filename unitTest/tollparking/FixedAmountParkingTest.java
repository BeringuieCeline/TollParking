package tollparking;
import exception.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FixedAmountParkingTest {

	static String STDCAR = "std";
	static String TKWCAR = "20kw";
	static String FKWCAR = "50kw";
	
	static String parkingName = "Fixed amount parking";
	static Double parkFixedAmount = 5.3;
	static Double parkHourPrice = 2.0;
	
	static LocalDateTime entryTime = LocalDateTime.of(2020,Month.APRIL,27,6,30,40,50000);
	static LocalDateTime exitTime = LocalDateTime.of(2020,Month.APRIL,27,10,30,40,50000);
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
	@Test
	void testCreateParkingwithDublicateSlots(){
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingSlot tkwslot1 = new ParkingSlot(TKWCAR,"B1");
			ParkingSlot tkwslot2 = new ParkingSlot(TKWCAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot, tkwslot1, tkwslot2)); 	
			FixedAmountParking testparking = new FixedAmountParking (parkingName,slotsparkingtest);
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		} catch (NoUniqueParkingSlotException err) {
			assertTrue(true);
		};
	}
	
	@Test
	void testBilling() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(TKWCAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot, tkwslot)); 
			FixedAmountParking testparking = new FixedAmountParking (parkingName,slotsparkingtest, parkFixedAmount, parkHourPrice);
					
			ParkingTicket ticket = new ParkingTicket(stdslot, entryTime);
			Double price = testparking.billing(ticket, exitTime);
			long spentTime = (Duration.between(entryTime, exitTime)).toHours();
			Double priceTest = parkFixedAmount + spentTime*parkHourPrice;
			assertEquals(priceTest,price);
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		} catch (NoUniqueParkingSlotException err) {
			fail("NoUniqueParkingSlotException thrown");
		};
		
	}

	@Test
	void testCheckoutParkingSlot() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(TKWCAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot, tkwslot)); 
			FixedAmountParking testparking = new FixedAmountParking (parkingName,slotsparkingtest, parkFixedAmount, parkHourPrice);
	
			ParkingTicket ticket = new ParkingTicket(stdslot, entryTime);
			Double price = testparking.checkoutParkingSlot(ticket, exitTime);
			long spentTime = (Duration.between(entryTime, exitTime)).toHours();
			Double priceTest = parkFixedAmount + spentTime*parkHourPrice;
			assertEquals(priceTest,price);
			assertTrue(stdslot.getStatus());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		} catch (NoUniqueParkingSlotException err) {
			fail("NoUniqueParkingSlotException thrown");
		};
		
	}

	@Test
	void testGetParkingName(){
			FixedAmountParking testparking = new FixedAmountParking (parkingName);
			assertEquals(parkingName,testparking.getParkingName());
	}

	@Test
	void testAddparkSlot() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(TKWCAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot)); 
			FixedAmountParking testparking = new FixedAmountParking (parkingName,slotsparkingtest, parkFixedAmount, parkHourPrice);
			
			testparking.addparkSlot(tkwslot);
			assertEquals(tkwslot,testparking.getParkingSlots().get(1));
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
		catch (ParkingSlotExistingException err) {
			fail("ParkingSlotExistingException thrown");
		} catch (NoUniqueParkingSlotException err) {
			fail("NoUniqueParkingSlotException thrown");
		};
		
	}

	@Test
	void testAddExistingparkSlot() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(TKWCAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot,tkwslot)); 
			FixedAmountParking testparking = new FixedAmountParking (parkingName,slotsparkingtest, parkFixedAmount, parkHourPrice);
			
			testparking.addparkSlot(tkwslot);
			fail("Exception not thrown");
		} catch (ParkingSlotExistingException err) {
			assertTrue(true);
		}
		catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		} catch (NoUniqueParkingSlotException err) {
			fail("NoUniqueParkingSlotException thrown");
		};
	}
	
	@Test
	void testRemoveparkSlot(){
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(TKWCAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot, tkwslot)); 
			FixedAmountParking testparking = new FixedAmountParking (parkingName,slotsparkingtest, parkFixedAmount, parkHourPrice);
			
			testparking.removeparkSlot(stdslot);
			assertEquals(tkwslot,testparking.getParkingSlots().get(0));
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
		catch (NoParkingSlotException err) {
			fail("NoParkingSlotException thrown");
		} catch (NoUniqueParkingSlotException err) {
			fail("NoUniqueParkingSlotException thrown");
		};

	}

	@Test
	void testRemoveEmptyparkSlot(){
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(TKWCAR,"B1");			
			ParkingSlot fkwslot = new ParkingSlot(FKWCAR,"C1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot, fkwslot)); 
			FixedAmountParking testparking = new FixedAmountParking (parkingName,slotsparkingtest, parkFixedAmount, parkHourPrice);
			
			testparking.removeparkSlot(tkwslot);
			fail("Exception not thrown");
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
		catch (NoParkingSlotException err) {
			assertTrue(true);
		} catch (NoUniqueParkingSlotException err) {
			fail("NoUniqueParkingSlotException thrown");
		};

	}
	@Test
	void testSpentTimeinParking() {
		try {
			ParkingSlot tkwslot = new ParkingSlot(TKWCAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(tkwslot)); 
			FixedAmountParking testparking = new FixedAmountParking (parkingName,slotsparkingtest, parkFixedAmount, parkHourPrice);
					
			Duration spentTime = testparking.spentTimeinParking(entryTime, exitTime);
			Duration spentTimeTest = (Duration.between(entryTime, exitTime));
			
			assertEquals(spentTimeTest, spentTime);
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		} catch (NoUniqueParkingSlotException err) {
			fail("NoUniqueParkingSlotException thrown");
		};
	}

	@Test
	void testCheckinParkingSlot() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			String newCar = STDCAR;
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot));
			FixedAmountParking testparking = new FixedAmountParking (parkingName,slotsparkingtest);
			ParkingTicket carticket =  testparking.checkinParkingSlot(newCar, entryTime);
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
			fail("NoUniqueParkingSlotException thrown");
		};
	}
	@Test
	void testCheckinFullParkingSlot() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			String newCar = STDCAR;
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot));
			FixedAmountParking testparking = new FixedAmountParking (parkingName,slotsparkingtest);
			stdslot.setStatus(false);
			ParkingTicket carticket =  testparking.checkinParkingSlot(newCar, entryTime);
			fail("Exception not thrown");

		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
		catch (ParkingSlotTypesNotExistingException err) {
			fail("ParkingSlotTypesNotExistingException thrown");
		}
		catch (ParkingFullException err) {
			assertTrue(true);
		} catch (NoUniqueParkingSlotException err) {
			fail("NoUniqueParkingSlotException thrown");
		};
	}
	
	@Test
	void testCheckinWrongTypeParkingSlot1() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			String newCar = "TEST";
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot));
			FixedAmountParking testparking = new FixedAmountParking (parkingName,slotsparkingtest);
			ParkingTicket carticket = testparking.checkinParkingSlot(newCar, entryTime);
			fail("Exception not thrown");

		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
		catch (ParkingSlotTypesNotExistingException err) {
			assertTrue(true);
		}
		catch (ParkingFullException err) {
			fail("ParkingFullException thrown");
		} catch (NoUniqueParkingSlotException err) {
			fail("NoUniqueParkingSlotException thrown");
		};
	}
	
	@Test
	void testCheckinWrongTypeParkingSlot2() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			String newCar = TKWCAR;
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot));
			FixedAmountParking testparking = new FixedAmountParking (parkingName,slotsparkingtest);
			ParkingTicket carticket = testparking.checkinParkingSlot(newCar, entryTime);
			fail("Exception not thrown");

		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
		catch (ParkingSlotTypesNotExistingException err) {
			fail("ParkingSlotTypesNotExistingException thrown");
		}
		catch (ParkingFullException err) {
			assertTrue(true);
		} catch (NoUniqueParkingSlotException err) {
			fail("NoUniqueParkingSlotException thrown");
		};
	}
	
	@Test
	void testSetParkingSlots() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(TKWCAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot, tkwslot)); 	
			FixedAmountParking testparking = new FixedAmountParking (parkingName);
			testparking.setParkingSlots(slotsparkingtest);
			
			assertEquals(slotsparkingtest,testparking.getParkingSlots());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		} catch (NoUniqueParkingSlotException err) {
			fail("NoUniqueParkingSlotException thrown");
		};
		
	}

	@Test
	void testSetWrongParkingSlots() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingSlot tkwslot1 = new ParkingSlot(TKWCAR,"B1");
			ParkingSlot tkwslot2 = new ParkingSlot(TKWCAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot, tkwslot1, tkwslot2)); 	
			FixedAmountParking testparking = new FixedAmountParking (parkingName);
			testparking.setParkingSlots(slotsparkingtest);
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		} catch (NoUniqueParkingSlotException err) {
			assertTrue(true);
		};
		
	}
	
	@Test
	void testGetParkingSlots() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(TKWCAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot, tkwslot)); 		
			FixedAmountParking testparking = new FixedAmountParking (parkingName,slotsparkingtest);
			
			assertEquals(slotsparkingtest,testparking.getParkingSlots());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		} catch (NoUniqueParkingSlotException err) {
			fail("NoUniqueParkingSlotException thrown");
		};
	}

	@Test
	void testPrintExitTicket() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingSlot tkwslot = new ParkingSlot(TKWCAR,"B1");
			List<ParkingSlot> slotsparkingtest = new ArrayList<>(Arrays.asList(stdslot, tkwslot)); 		
			FixedAmountParking testparking = new FixedAmountParking (parkingName,slotsparkingtest);
			ParkingTicket ticket = new ParkingTicket(stdslot, entryTime);
			Double price = testparking.billing(ticket, exitTime);
			String exitTicket = "Ticket for " + stdslot.getslotname() + ". Entry date:" + entryTime + "Bill: " +price.toString()+ " �";
		
			assertEquals(exitTicket,testparking.exitTicket(ticket, price));
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		} catch (NoUniqueParkingSlotException err) {
			fail("NoUniqueParkingSlotException thrown");
		};
	}
}