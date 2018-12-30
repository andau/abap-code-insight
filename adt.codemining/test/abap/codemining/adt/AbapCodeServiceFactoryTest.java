package abap.codemining.adt;

import org.junit.Test;

import com.sap.adt.communication.exceptions.OutOfSessionsException;

public class AbapCodeServiceFactoryTest {

	@Test(expected = NoClassDefFoundError.class)
	public void testCreateAbapCodeElementInformation() throws OutOfSessionsException {
		AbapCodeServiceFactory cut = new AbapCodeServiceFactory();
		cut.createAbapCodeElementInformation("test");
	}

}
