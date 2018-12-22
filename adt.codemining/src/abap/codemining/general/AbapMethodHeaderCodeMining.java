package abap.codemining.general;

import java.util.concurrent.CompletableFuture;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.codemining.ICodeMiningProvider;
import org.eclipse.jface.text.codemining.LineHeaderCodeMining;

public class AbapMethodHeaderCodeMining extends LineHeaderCodeMining {

	private final String label;

	public AbapMethodHeaderCodeMining(int beforeLineNumber, IDocument document, ICodeMiningProvider provider,
			String label) throws BadLocationException {
		super(beforeLineNumber, document, provider);
		this.label = label;
	}

	@Override
	protected CompletableFuture<Void> doResolve(ITextViewer viewer, IProgressMonitor monitor) {
		return CompletableFuture.runAsync(() -> {
			monitor.isCanceled();
			super.setLabel(label);
		});
	}
}
