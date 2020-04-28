package tollparking;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.NotAvailableSlotTypeException;

class ParkingTicketTest {

	static String STDCAR = "std";
	static String TKWCAR = "20kw";
	static String FKWCAR = "50kw";
	
	static LocalDateTime entryTime = LocalDateTime.of(2020,Month.APRIL,27,6,30,40,50000);
	static LocalDateTime exitTime = LocalDateTime.of(2020,Month.APRIL,27,10,30,40,50000);
	
	@Test
	void testParkingTicket() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingTicket carticket = new ParkingTicket(stdslot, entryTime);
			carticket.toString();
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}	
	}

	@Test
	void testGetEntryTime() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingTicket carticket = new ParkingTicket(stdslot, entryTime);
			assertEquals(entryTime,carticket.getEntryTime());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}	
	}

	@Test
	void testGetParkSlot() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingTicket carticket = new ParkingTicket(stdslot, entryTime);
			assertEquals(stdslot,carticket.getParkSlot());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}	
	}

	@Test
	void testGetSlotname() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingTicket carticket = new ParkingTicket(stdslot, entryTime);
			assertEquals("A1",carticket.getSlotname());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}	
	}

}
