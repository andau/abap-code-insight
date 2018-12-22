package abap.codemining.utils;

import static org.junit.Assert.assertEquals;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.junit.Test;
import org.mockito.Mockito;

import abap.codemining.utils.EditorPartProjectAdapter;

public class EditorPartProjectAdapterTest {

	private final IEditorPart editorPart = Mockito.mock(IEditorPart.class);
	private final IEditorInput editorInput = Mockito.mock(IEditorInput.class);
	private final IProject project = Mockito.mock(IProject.class);

	@Test
	public void testEditor() {
		Mockito.when(editorPart.getEditorInput()).thenReturn(editorInput);
		Mockito.when(editorInput.getAdapter(IProject.class)).thenReturn(project);
		EditorPartProjectAdapter cut = new EditorPartProjectAdapter(editorPart);
		assertEquals(project, cut.getProject());
	}

	@Test
	public void testResource() {
		IResource resource = Mockito.mock(IResource.class);
		Mockito.when(editorPart.getEditorInput()).thenReturn(editorInput);
		Mockito.when(editorInput.getAdapter(IResource.class)).thenReturn(resource);
		Mockito.when(resource.getProject()).thenReturn(project);
		EditorPartProjectAdapter cut = new EditorPartProjectAdapter(editorPart);
		assertEquals(project, cut.getProject());
	}

}
