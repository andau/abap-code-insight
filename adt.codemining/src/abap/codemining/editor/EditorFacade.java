package abap.codemining.editor;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.texteditor.ITextEditor;

import com.sap.adt.tools.abapsource.AbapSource;
import com.sap.adt.tools.abapsource.IAdtObjectLoader;
import com.sap.adt.tools.core.IAdtObjectReference;
import com.sap.adt.tools.core.model.adtcore.IAdtObject;
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

	public IAdtObjectReference getAdtObjectReference() {
		final IFile file = textEditorUtil.getFile();
		return Adapters.adapt((Object) file, IAdtObjectReference.class);
	}

	public IAdtObject getAdtObject() {
		final IAdtObjectLoader loader = AbapSource.getInstance().getAdtObjectLoader(textEditorUtil.getFile());
		final List<IAdtObject> adtObjects = loader.getAdtComponentObjects(textEditorUtil.getFile());
		if (adtObjects != null && adtObjects.size() > 0) {
			return adtObjects.get(0);
		}

		final IFile file = textEditorUtil.getFile();
		return Adapters.adapt((Object) file, IAdtObject.class);
	}

	public IDocument getDocument() {
		return textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());
	}
}
