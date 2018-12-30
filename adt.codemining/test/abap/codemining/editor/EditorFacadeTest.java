package abap.codemining.editor;

import static org.junit.Assert.assertEquals;

import org.eclipse.core.resources.IProject;
import org.eclipse.ui.texteditor.ITextEditor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.utils.EditorPartProjectAdapter;
import abap.codemining.utils.TextEditorUtil;

public class EditorFacadeTest {

	EditorFacade cut;
	private final ITextEditor editor = Mockito.mock(ITextEditor.class);
	private final EditorPartProjectAdapter adapter = Mockito.mock(EditorPartProjectAdapter.class);
	private final TextEditorUtil textEditorUtil = Mockito.mock(TextEditorUtil.class);
	private final IProject project = Mockito.mock(IProject.class);
	private final IAbapProject abapProject = Mockito.mock(IAbapProject.class);

	@Before
	public void before() {
		cut = new EditorFacade(editor);
		Whitebox.setInternalState(cut, "adapter", adapter);
		Whitebox.setInternalState(cut, "textEditorUtil", textEditorUtil);
		Mockito.when(adapter.getProject()).thenReturn(project);
		Mockito.when(project.getAdapter(IAbapProject.class)).thenReturn(abapProject);
	}

	@Test
	public void testGetProject() {
		assertEquals(project, cut.getProject());
	}

	public void testGetAbapProject() {
		assertEquals(abapProject, cut.getAbapProject());
	}

}
