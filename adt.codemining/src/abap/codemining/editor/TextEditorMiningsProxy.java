package abap.codemining.editor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.text.codemining.ICodeMining;

import com.sap.adt.tools.core.project.IAbapProject;

public class TextEditorMiningsProxy {

	Map<TextEditorMiningEntryKey, TextEditorMiningsEntry> texteditorMinings = new HashMap<>();

	public void addOrUpdate(IAbapProject abapProject, String title, List<ICodeMining> minings) {
		addOrUpdate(new TextEditorMiningsEntry(abapProject.getProject().getName(), title, minings));
	}

	public void addOrUpdate(TextEditorMiningsEntry textEditorMiningsEntry) {
		texteditorMinings.put(textEditorMiningsEntry.getTextEditorMiningEntryKey(), textEditorMiningsEntry);
	}

	public TextEditorMiningsEntry get(TextEditorMiningEntryKey textEditorMiningEntryKey) {
		return texteditorMinings.containsKey(textEditorMiningEntryKey) ? texteditorMinings.get(textEditorMiningEntryKey)
				: null;
	}
}
