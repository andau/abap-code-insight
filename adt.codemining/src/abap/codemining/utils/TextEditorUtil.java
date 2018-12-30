package abap.codemining.utils;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;

public class TextEditorUtil {

	private final ITextEditor textEditor;

	public TextEditorUtil(ITextEditor textEditor) {
		this.textEditor = textEditor;
	}

	public IFile getFile() {
		IEditorInput editorInput = textEditor.getEditorInput();
		IFile file = ((IFileEditorInput) editorInput).getFile();
		return file;
	}

}
