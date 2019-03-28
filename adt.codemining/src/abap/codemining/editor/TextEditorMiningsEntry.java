package abap.codemining.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.codemining.ICodeMining;

import abap.codemining.general.AbapHeaderCodeMining;

public class TextEditorMiningsEntry {

	private final TextEditorMiningEntryKey textEditorMiningEntryKey;
	private final List<ICodeMining> minings;

	public TextEditorMiningsEntry(String abapProjectName, String title, List<ICodeMining> minings) {
		this.textEditorMiningEntryKey = new TextEditorMiningEntryKey(abapProjectName, title);
		this.minings = minings;
	}

	public TextEditorMiningEntryKey getTextEditorMiningEntryKey() {
		return textEditorMiningEntryKey;
	}

	public List<ICodeMining> calculateEditedMinings(IDocument document) {
		final List<ICodeMining> editedMinings = new ArrayList<>();
		for (final ICodeMining mining : minings) {
			if (mining.getClass().equals(AbapHeaderCodeMining.class)) {
				final AbapHeaderCodeMining abapMining = (AbapHeaderCodeMining) mining;
				final Scanner scanner = new Scanner(document.get());

				int linenumber = 0;
				while (scanner.hasNextLine()) {

					linenumber++;

					final String line = scanner.nextLine();

					if (line.length() > 0 && line.equals(abapMining.getLineText())) {
						try {
							editedMinings
									.add(new AbapHeaderCodeMining(linenumber - 1, document, abapMining.getProvider(),
											abapMining.getLabel(), abapMining.getLineText(), abapMining.getAction()));
						} catch (final BadLocationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}

				scanner.close();

			}

		}
		return editedMinings;
	}

}
