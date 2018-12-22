package abap.codemining.utils;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

public class EditorPartProjectAdapter {

	private final IEditorPart editorPart;

	public EditorPartProjectAdapter(IEditorPart editorPart) {
		this.editorPart = editorPart;
	}

	public IProject getProject() {
		IProject project = null;

		if (editorPart != null) {
			IEditorInput input = editorPart.getEditorInput();
			project = input.getAdapter(IProject.class);
			if (project == null) {
				IResource resource = input.getAdapter(IResource.class);
				if (resource != null) {
					project = resource.getProject();
				}
			}
		}

		return project;

	}

}
