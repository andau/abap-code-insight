package abap.codemining.label;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.eclipse.jface.text.BadLocationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.adt.AbapCodeServiceFactory;
import abap.codemining.adt.method.AbapMethodInformation;
import abap.codemining.adt.method.AbapMethodInformationService;
import abap.codemining.feature.MethodBodyMiningFeature;
import abap.codemining.general.ReferencesEvaluator;

public class MethodMiningLabelBuilderTest {

	private static final String RETURNING_PARAMETER_NAME = "R_PARAM_NAME";

	private static final String TEST_SIGNATURE_LABEL = "TEST_SIGNATURE_LABEL";

	MethodMiningLabelBuilder cut;

	private final AbapCodeServiceFactory abapCodeServiceFactory = Mockito.mock(AbapCodeServiceFactory.class);
	private final ReferencesEvaluator referencesEvaluator = Mockito.mock(ReferencesEvaluator.class);

	private final AbapMethodInformationService abapCodeElementInformationService = Mockito
			.mock(AbapMethodInformationService.class);

	private final IAbapProject abapProject = Mockito.mock(IAbapProject.class);
	private final URI uri = URI.create("");
	private final String doc = "";

	private final AbapMethodInformation abapCodeElementInformation = Mockito.mock(AbapMethodInformation.class);

	MethodBodyMiningFeature methodBodyMiningFeature = Mockito.mock(MethodBodyMiningFeature.class);

	@Before
	public void before() throws OutOfSessionsException, BadLocationException {

		Mockito.when(methodBodyMiningFeature.isReferenceCountActive()).thenReturn(true);
		Mockito.when(methodBodyMiningFeature.isSignatureActive()).thenReturn(true);

		cut = new MethodMiningLabelBuilder(methodBodyMiningFeature);

		Mockito.when(abapCodeServiceFactory.createAbapCodeElementInformation(Mockito.anyString()))
				.thenReturn(abapCodeElementInformationService);
		Mockito.when(abapCodeElementInformationService.getInfo(Mockito.any(), Mockito.any()))
				.thenReturn(abapCodeElementInformation);
		Mockito.when(abapCodeElementInformation.getSignatureLabel()).thenReturn(TEST_SIGNATURE_LABEL);

		Whitebox.setInternalState(cut, "abapCodeServiceFactory", abapCodeServiceFactory);
		Whitebox.setInternalState(cut, "referencesEvaluator", referencesEvaluator);

	}

	@Test
	public void testSignatureLabel() throws MiningLabelBuildingException {
		String miningLabel = cut.buildSignatureLabel(abapProject, uri, doc);

		assertEquals(TEST_SIGNATURE_LABEL, miningLabel);

	}

}
