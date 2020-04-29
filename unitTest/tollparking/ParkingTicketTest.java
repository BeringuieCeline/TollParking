package tollparking;

import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.time.LocalDateTime;
import java.time.Month;

import exception.NotAvailableSlotTypeException;

public class ParkingTicketTest {

	static String STDCAR = "std";
	static String TKWCAR = "20kw";
	static String FKWCAR = "50kw";
	
	static LocalDateTime entryTime = LocalDateTime.of(2020,Month.APRIL,27,6,30,40,50000);
	static LocalDateTime exitTime = LocalDateTime.of(2020,Month.APRIL,27,10,30,40,50000);
	
	@Test
	public void testParkingTicket() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingTicket carticket = new ParkingTicket(stdslot, entryTime);
			carticket.toString();
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}	
	}

	@Test
	public void testGetEntryTime() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingTicket carticket = new ParkingTicket(stdslot, entryTime);
			assertEquals(entryTime,carticket.getEntryTime());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}	
	}

	@Test
	public void testGetParkSlot() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingTicket carticket = new ParkingTicket(stdslot, entryTime);
			assertEquals(stdslot,carticket.getParkSlot());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}	
	}

	@Test
	public void testGetSlotname() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			ParkingTicket carticket = new ParkingTicket(stdslot, entryTime);
			assertEquals("A1",carticket.getSlotname());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}	
	}

}
