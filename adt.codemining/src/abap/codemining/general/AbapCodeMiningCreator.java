package abap.codemining.general;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.codemining.ICodeMining;
import org.eclipse.jface.text.codemining.ICodeMiningProvider;

public class AbapCodeMiningCreator {

	public ICodeMining create(int linenumber, IDocument document, ICodeMiningProvider provider, String miningLabel)
			throws BadLocationException {
		return new AbapMethodHeaderCodeMining(linenumber, document, provider, miningLabel);
	}

}
