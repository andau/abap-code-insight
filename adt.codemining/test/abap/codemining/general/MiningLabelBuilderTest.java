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
import abap.codemining.method.MethodParam;
import abap.codemining.method.MethodParamType;

public class MiningLabelBuilderTest {

	private static final String RETURNING_PARAMETER_NAME = "R_PARAM_NAME";

	MiningLabelBuilder cut;

	private final AbapCodeServiceFactory abapCodeServiceFactory = Mockito.mock(AbapCodeServiceFactory.class);
	private final ReferencesEvaluator referencesEvaluator = Mockito.mock(ReferencesEvaluator.class);

	private final AbapCodeElementInformationService abapCodeElementInformationService = Mockito
			.mock(AbapCodeElementInformationService.class);

	private final IAbapProject abapProject = Mockito.mock(IAbapProject.class);
	private final URI uri = URI.create("");
	private final String doc = "";

	private AbapCodeElementInformation abapCodeElementInformation = Mockito.mock(AbapCodeElementInformation.class);

	private MethodParam methodParam = new MethodParam(RETURNING_PARAMETER_NAME, "TYPE REF TO ZCL_TYPE", MethodParamType.RETURNING);

	@Before
	public void before() throws OutOfSessionsException, BadLocationException {
		cut = new MiningLabelBuilder();

		Mockito.when(abapCodeServiceFactory.createAbapCodeElementInformation(Mockito.anyString()))
				.thenReturn(abapCodeElementInformationService);
		Mockito.when(abapCodeElementInformationService.getInfo(Mockito.any(), Mockito.any())).thenReturn(abapCodeElementInformation);
		Mockito.when(abapCodeElementInformation.getVisibility()).thenReturn("public"); 
		Mockito.when(abapCodeElementInformation.getLevel()).thenReturn("static"); 
		Mockito.when(abapCodeElementInformation.getReturnValue()).thenReturn(methodParam); 
		Mockito.when(abapCodeElementInformation.getName()).thenReturn("method_name"); 

		Whitebox.setInternalState(cut, "abapCodeServiceFactory", abapCodeServiceFactory);
		Whitebox.setInternalState(cut, "referencesEvaluator", referencesEvaluator);

	}

	@Test
	public void testStaticLabel() throws OutOfSessionsException, ServiceNotAvailableException, IOException {
		String miningLabel = cut.build(abapProject, uri, doc);

		String expectedMiningLabel = "public static " + RETURNING_PARAMETER_NAME.toLowerCase() +  ":ZCL_TYPE method_name ()";
		assertEquals(expectedMiningLabel, miningLabel);

	}

	@Test
	public void testInstanceLabel() throws OutOfSessionsException, ServiceNotAvailableException, IOException {
		Mockito.when(abapCodeElementInformation.getLevel()).thenReturn("instance"); 

		String miningLabel = cut.build(abapProject, uri, doc);

		String expectedMiningLabel = "public " + RETURNING_PARAMETER_NAME.toLowerCase() +  ":ZCL_TYPE method_name ()";
		assertEquals(expectedMiningLabel, miningLabel);

	}

}
