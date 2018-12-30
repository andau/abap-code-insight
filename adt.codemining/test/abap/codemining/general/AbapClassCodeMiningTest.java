package abap.codemining.general;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.codemining.ICodeMining;
import org.eclipse.jface.text.codemining.ICodeMiningProvider;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;
import org.mockito.Mockito;

import com.sap.adt.destinations.model.IDestinationData;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.utils.StringUtils;

public class AbapClassCodeMiningTest {

	private static final String DESTINATION_DATA_ID = "destination_data_id";
	private final ITextEditor textEditor = Mockito.mock(ITextEditor.class);
	private final IEditorInput editorInput = Mockito.mock(IEditorInput.class);

	private final IProject project = Mockito.mock(IProject.class);
	private final IAbapProject abapProject = Mockito.mock(IAbapProject.class);
	private final IDestinationData destinationData = Mockito.mock(IDestinationData.class);

	private final ITextViewer viewer = Mockito.mock(ITextViewer.class);
	private final ICodeMiningProvider provider = Mockito.mock(ICodeMiningProvider.class);
	private final IDocument doc = Mockito.mock(IDocument.class);

	public void testNoMinings() {
		AbapClassCodeMining cut = new AbapClassCodeMining();
		Mockito.when(doc.get()).thenReturn(StringUtils.EMPTY);
		Mockito.when(textEditor.getEditorInput()).thenReturn(editorInput);
		Mockito.when(editorInput.getAdapter(IProject.class)).thenReturn(project);

		Mockito.when(project.getAdapter(IAbapProject.class)).thenReturn(abapProject);
		Mockito.when(abapProject.getDestinationData()).thenReturn(destinationData);
		Mockito.when(destinationData.getId()).thenReturn(DESTINATION_DATA_ID);

		List<ICodeMining> expectedMinings = new ArrayList<>();
		List<ICodeMining> minings = new ArrayList<>();
		cut.evaluateCodeMinings(minings, textEditor, viewer, provider, doc);
		assertEquals(expectedMinings, minings);
	}

}
