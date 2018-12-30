package abap.codemining.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.texteditor.ITextEditor;

import com.sap.adt.tools.core.IAdtObjectReference;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.utils.EditorPartProjectAdapter;
import abap.codemining.utils.TextEditorUtil;

public class EditorFacade {

	private final ITextEditor textEditor;
	private final EditorPartProjectAdapter adapter;
	private final TextEditorUtil textEditorUtil;

	public EditorFacade(ITextEditor textEditor) {
		this.textEditor = textEditor;
		adapter = new EditorPartProjectAdapter(textEditor);
		textEditorUtil = new TextEditorUtil(textEditor);
	}

	public IProject getProject() {
		return adapter.getProject();
	}

	public IAbapProject getAbapProject() {
		return getProject().getAdapter(IAbapProject.class);
	}

	public IAdtObjectReference getAdtObject() {
		IFile file = textEditorUtil.getFile();
		return Adapters.adapt((Object) file, IAdtObjectReference.class);
	}

	public IDocument getDocument() {
		return textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());
	}
}
