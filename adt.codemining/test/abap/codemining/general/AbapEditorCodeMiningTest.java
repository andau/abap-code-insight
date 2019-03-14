package abap.codemining.general;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.codemining.ICodeMining;
import org.eclipse.jface.text.codemining.ICodeMiningProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import com.sap.adt.tools.core.IAdtObjectReference;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.editor.EditorFacade;
import abap.codemining.element.AbapClassElementParser;
import abap.codemining.feature.ClassMiningFeature;
import abap.codemining.feature.FeatureFacade;
import abap.codemining.feature.MethodBodyMiningFeature;
import abap.codemining.label.MethodMiningLabelBuilder;
import abap.codemining.label.MiningLabelBuildingException;
import abap.codemining.utils.StringUtils;

public class AbapEditorCodeMiningTest {

	private static final String TEST_DESTINATION_DATA_ID = "destination_data_id";
	private static final URI TEST_URI = URI.create("test/uri");
	private static final String TEST_LABEL = "test_label";
	private final ITextEditor textEditor = Mockito.mock(ITextEditor.class);

	private final AbapCodeMiningCreator abapCodeMiningCreator = Mockito.mock(AbapCodeMiningCreator.class);
	private final MethodMiningLabelBuilder miningLabelBuilder = Mockito.mock(MethodMiningLabelBuilder.class);

	private final IAbapProject abapProject = Mockito.mock(IAbapProject.class);
	private final IProject project = Mockito.mock(IProject.class);
	private final IDocument doc = Mockito.mock(IDocument.class);

	private final EditorFacade textEditorFacade = Mockito.mock(EditorFacade.class);
	private final IAdtObjectReference adtObject = Mockito.mock(IAdtObjectReference.class);
	private final ICodeMiningProvider provider = Mockito.mock(ICodeMiningProvider.class);
	FeatureFacade featureFacade = Mockito.mock(FeatureFacade.class);

	AbapEditorCodeMining cut;
	private final ClassMiningFeature classHeaderMiningFeature = Mockito.mock(ClassMiningFeature.class);
	private final MethodBodyMiningFeature methodBodyMiningFeature = Mockito.mock(MethodBodyMiningFeature.class);

	@Before
	public void before() throws MiningLabelBuildingException {

		Mockito.when(textEditorFacade.getAbapProject()).thenReturn(abapProject);
		Mockito.when(textEditorFacade.getAdtObjectReference()).thenReturn(adtObject);
		Mockito.when(adtObject.getUri()).thenReturn(TEST_URI);
		Mockito.when(textEditorFacade.getDocument()).thenReturn(doc);
		Mockito.when(textEditorFacade.getProject()).thenReturn(project);

		Mockito.when(abapProject.getDestinationId()).thenReturn(TEST_DESTINATION_DATA_ID);

		Mockito.when(miningLabelBuilder.buildSignatureLabel(Mockito.eq(abapProject), Mockito.any(), Mockito.any()))
				.thenReturn(TEST_LABEL);

		Mockito.when(featureFacade.getClassHeaderMiningFeature()).thenReturn(classHeaderMiningFeature);
		Mockito.when(featureFacade.getMethodBodyMiningFeature()).thenReturn(methodBodyMiningFeature);

		Mockito.when(classHeaderMiningFeature.isReferenceCountActive()).thenReturn(false);
		Mockito.when(methodBodyMiningFeature.isReferenceCountActive()).thenReturn(false);
		Mockito.when(methodBodyMiningFeature.isSignatureActive()).thenReturn(true);

		cut = new AbapEditorCodeMining(textEditor, null, new AbapClassElementParser(featureFacade, null, null));
		Whitebox.setInternalState(cut, "textEditorFacade", textEditorFacade);
		Whitebox.setInternalState(cut, "abapCodeMiningCreator", abapCodeMiningCreator);
		// Whitebox.setInternalState(cut, "miningLabelBuilder", miningLabelBuilder);

	}

	@Test
	public void testNoMinings() {

		Mockito.when(doc.get()).thenReturn(StringUtils.EMPTY);

		final List<ICodeMining> expectedMinings = new ArrayList<>();
		final List<ICodeMining> minings = new ArrayList<>();
		cut.evaluateCodeMinings(minings, provider);
		assertEquals(expectedMinings, minings);
	}

	@Test
	public void testSimpleMining() throws BadLocationException {

		Mockito.when(doc.get()).thenReturn("method testmethod.");

		final List<ICodeMining> minings = new ArrayList<>();
		cut.evaluateCodeMinings(minings, provider);

		assertEquals(2, minings.size());

		Mockito.verify(abapCodeMiningCreator).create(Mockito.eq(0), Mockito.any(), Mockito.any(),
				Mockito.eq(TEST_LABEL));
	}

}
