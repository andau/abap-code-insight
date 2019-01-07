package abap.codemining.adt;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.sap.adt.communication.exceptions.OutOfSessionsException;

import abap.codemining.adt.method.AbapMethodInformationService;

public class AbapCodeServiceFactoryTest {

	@Test
	public void testCreateAbapCodeElementInformation() throws OutOfSessionsException {
		AbapCodeServiceFactory cut = new AbapCodeServiceFactory();
		AbapMethodInformationService abapCodeElementInformation = cut.createAbapCodeElementInformation("test");
		assertNotNull(abapCodeElementInformation);
	}

}
