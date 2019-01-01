package abap.codemining.general;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;

import org.eclipse.jface.text.BadLocationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.core.model.util.ServiceNotAvailableException;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.adt.AbapCodeElementInformation;
import abap.codemining.adt.AbapCodeElementInformationService;
import abap.codemining.adt.AbapCodeServiceFactory;

public class MiningLabelBuilderTest {

	MiningLabelBuilder cut;

	private final AbapCodeServiceFactory abapCodeServiceFactory = Mockito.mock(AbapCodeServiceFactory.class);
	private final ReferencesEvaluator referencesEvaluator = Mockito.mock(ReferencesEvaluator.class);

	private final AbapCodeElementInformationService abapCodeElementInformationService = Mockito
			.mock(AbapCodeElementInformationService.class);

	private final IAbapProject abapProject = Mockito.mock(IAbapProject.class);
	private final URI uri = URI.create("");
	private final String doc = "";

	private AbapCodeElementInformation abapCodeElementInformation = Mockito.mock(AbapCodeElementInformation.class);

	@Before
	public void before() throws OutOfSessionsException, BadLocationException {
		cut = new MiningLabelBuilder();

		Mockito.when(abapCodeServiceFactory.createAbapCodeElementInformation(Mockito.anyString()))
				.thenReturn(abapCodeElementInformationService);
		Mockito.when(abapCodeElementInformationService.getInfo(Mockito.any(), Mockito.any())).thenReturn(abapCodeElementInformation);
		Mockito.when(abapCodeElementInformation.getVisibility()).thenReturn("public"); 
		Mockito.when(abapCodeElementInformation.getVisibility()).thenReturn("static"); 

		Whitebox.setInternalState(cut, "abapCodeServiceFactory", abapCodeServiceFactory);
		Whitebox.setInternalState(cut, "referencesEvaluator", referencesEvaluator);

	}

	@Test
	public void test() throws OutOfSessionsException, ServiceNotAvailableException, IOException {
		String miningLabel = cut.build(abapProject, uri, doc);

		String expectedMiningLabel = "0 references; public static";
		assertEquals(expectedMiningLabel, miningLabel);

	}

}
