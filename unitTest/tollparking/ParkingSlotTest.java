package tollparking;

import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import exception.NotAvailableSlotTypeException;

public class ParkingSlotTest {

	@Test
	public void testParkingSlot() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
		} catch (NotAvailableSlotTypeException err) {
			fail("Exception thrown");
			System.out.println(err);
		}	
	}
 
	@Test
	public void testWrongParkingSlot() {
		try {
			ParkingSlot stdslot = new ParkingSlot("test","A1");
			fail("Exception not thrown");
		} catch (NotAvailableSlotTypeException err) {
			assertTrue(true);
		}	
	}
	
	@Test
	public void testGetStatus() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			assertTrue(stdslot.getStatus());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
	}

	@Test
	public void testSetStatus() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			stdslot.setStatus(false);
			assertFalse(stdslot.getStatus());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
	}

	@Test
	public void testGetparktype() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			assertEquals(CarType.STDCAR,stdslot.getparktype());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
	}

	@Test
	public void testSetparktype() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			stdslot.setparktype(CarType.E20CAR);
			assertEquals(CarType.E20CAR,stdslot.getparktype());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");;
		}
	}
	
	@Test
	public void testSetWrongparktype() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			stdslot.setparktype("test");
			fail("Exception not thrown");
		} catch (NotAvailableSlotTypeException err) {
			assertTrue(true);
		}	
	}

	@Test
	public void testGetslotname() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			assertEquals("A1",stdslot.getslotname());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
	}

	@Test
	public void testSetslotname() {
		try {
			ParkingSlot stdslot = new ParkingSlot(CarType.STDCAR,"A1");
			stdslot.setslotname("D7");
			assertEquals("D7",stdslot.getslotname());
		} catch (NotAvailableSlotTypeException err) {
			fail("NotAvailableSlotTypeException thrown");
		}
	}

}
