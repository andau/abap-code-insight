package abap.codemining.adt;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.sap.adt.communication.exceptions.OutOfSessionsException;

public class AbapCodeServiceFactoryTest {

	@Test
	public void testCreateAbapCodeElementInformation() throws OutOfSessionsException {
		AbapCodeServiceFactory cut = new AbapCodeServiceFactory();
		AbapCodeElementInformationService abapCodeElementInformation = cut.createAbapCodeElementInformation("test");
		assertNotNull(abapCodeElementInformation);
	}

}
