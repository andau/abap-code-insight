package abap.codemining.general;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.codemining.ICodeMining;
import org.eclipse.jface.text.codemining.ICodeMiningProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.core.IAdtObjectReference;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.adt.AbapCodeElementInformation;
import abap.codemining.adt.AbapCodeServiceFactory;
import abap.codemining.editor.EditorFacade;
import abap.codemining.utils.StringUtils;

public class AbapEditorCodeMiningTest {

	private static final String TEST_DESTINATION_DATA_ID = "destination_data_id";
	private static final URI TEST_URI = URI.create("test/uri");
	private final ITextEditor textEditor = Mockito.mock(ITextEditor.class);

	private final IAbapProject abapProject = Mockito.mock(IAbapProject.class);
	private final IDocument doc = Mockito.mock(IDocument.class);

	private final EditorFacade textEditorFacade = Mockito.mock(EditorFacade.class);
	private final IAdtObjectReference adtObject = Mockito.mock(IAdtObjectReference.class);
	private final ITextViewer viewer = Mockito.mock(ITextViewer.class);
	private final ICodeMiningProvider provider = Mockito.mock(ICodeMiningProvider.class);

	private final AbapCodeServiceFactory abapCodeServiceFactory = Mockito.mock(AbapCodeServiceFactory.class);
	private final AbapCodeElementInformation abapCodeElementInformation = Mockito
			.mock(AbapCodeElementInformation.class);
	private final ICodeMining firstCodeMining = Mockito.mock(ICodeMining.class);

	private final AbapCodeMiningCreator abapCodeMiningCreator = Mockito.mock(AbapCodeMiningCreator.class);

	AbapEditorCodeMining cut;

	@Before
	public void before() throws OutOfSessionsException, BadLocationException {
		cut = new AbapEditorCodeMining(textEditor);

		Whitebox.setInternalState(cut, "textEditorFacade", textEditorFacade);
		Whitebox.setInternalState(cut, "abapCodeServiceFactory", abapCodeServiceFactory);
		Whitebox.setInternalState(cut, "abapCodeMiningCreator", abapCodeMiningCreator);

		Mockito.when(textEditorFacade.getAbapProject()).thenReturn(abapProject);
		Mockito.when(textEditorFacade.getAdtObject()).thenReturn(adtObject);
		Mockito.when(adtObject.getUri()).thenReturn(TEST_URI);
		Mockito.when(textEditorFacade.getDocument()).thenReturn(doc);

		Mockito.when(abapCodeServiceFactory.createAbapCodeElementInformation(Mockito.anyString()))
				.thenReturn(abapCodeElementInformation);
		Mockito.when(abapCodeElementInformation.getVisibility(Mockito.any(), Mockito.any())).thenReturn("public");
		Mockito.when(abapProject.getDestinationId()).thenReturn(TEST_DESTINATION_DATA_ID);

		Mockito.when(abapCodeMiningCreator.create(Mockito.anyInt(), Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(firstCodeMining);

	}

	@Test
	public void testNoMinings() {

		Mockito.when(doc.get()).thenReturn(StringUtils.EMPTY);

		List<ICodeMining> expectedMinings = new ArrayList<>();
		List<ICodeMining> minings = new ArrayList<>();
		cut.evaluateCodeMinings(minings, provider);
		assertEquals(expectedMinings, minings);
	}

	@Test
	public void testOneMinings() throws BadLocationException {

		Mockito.when(doc.get()).thenReturn("method testmethod.");

		List<ICodeMining> expectedMinings = Arrays.asList(firstCodeMining);

		List<ICodeMining> minings = new ArrayList<>();
		cut.evaluateCodeMinings(minings, provider);

		assertEquals(expectedMinings.size(), minings.size());

		Mockito.verify(abapCodeMiningCreator).create(Mockito.eq(1), Mockito.any(), Mockito.any(),
				Mockito.matches("public"));
	}

}
