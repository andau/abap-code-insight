package abap.codemining.utils;

import static org.junit.Assert.assertEquals;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TextEditorUtilTest {

	ITextEditor textEditor = Mockito.mock(ITextEditor.class);
	IFile file = Mockito.mock(IFile.class);
	private final IFileEditorInput editorInput = Mockito.mock(IFileEditorInput.class);

	TextEditorUtil cut;

	@Before
	public void before() {
		cut = new TextEditorUtil(textEditor);
	}

	@Test
	public void testGetFile() {
		Mockito.when(textEditor.getEditorInput()).thenReturn(editorInput);
		Mockito.when(editorInput.getFile()).thenReturn(file);
		assertEquals(file, cut.getFile());
	}

}
