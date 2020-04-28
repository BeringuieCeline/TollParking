package tollparking;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.NotAvailableSlotTypeException;

class ParkingSlotTest {
	
	static String STDCAR = "std";
	static String TKWCAR = "20kw";
	static String FKWCAR = "50kw";


	@Test
	void testParkingSlot() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
		} catch (NotAvailableSlotTypeException err) {
			fail("Exception thrown");
			System.out.println(err);
		}	
	}
 
	@Test
	void testWrongParkingSlot() {
		try {
			ParkingSlot stdslot = new ParkingSlot("test","A1");
			fail("Exception not thrown");
		} catch (NotAvailableSlotTypeException err) {
			assertTrue(true);
		}	
	}
	
	@Test
	void testGetStatus() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			assertTrue(stdslot.getStatus());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
	}

	@Test
	void testSetStatus() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			stdslot.setStatus(false);
			assertFalse(stdslot.getStatus());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
	}

	@Test
	void testGetparktype() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			assertEquals(STDCAR,stdslot.getparktype());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		};
	}

	@Test
	void testSetparktype() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			stdslot.setparktype(TKWCAR);
			assertEquals(TKWCAR,stdslot.getparktype());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");;
		};
	}
	
	@Test
	void testSetWrongparktype() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			stdslot.setparktype("test");
			fail("Exception not thrown");
		} catch (NotAvailableSlotTypeException err) {
			assertTrue(true);
		}	
	}

	@Test
	void testGetslotname() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			assertEquals("A1",stdslot.getslotname());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		};
	}

	@Test
	void testSetslotname() {
		try {
			ParkingSlot stdslot = new ParkingSlot(STDCAR,"A1");
			stdslot.setslotname("D7");
			assertEquals("D7",stdslot.getslotname());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		};
	}

}
