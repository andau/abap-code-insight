package abap.codemining.general;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.codemining.ICodeMiningProvider;
import org.eclipse.jface.text.codemining.LineHeaderCodeMining;
import org.eclipse.swt.events.MouseEvent;

public class AbapHeaderCodeMining extends LineHeaderCodeMining {

	private String label;

	public AbapHeaderCodeMining(int beforeLineNumber, IDocument document, ICodeMiningProvider provider,
			String label, Consumer<MouseEvent> action) throws BadLocationException {
		super(beforeLineNumber, document, provider, action);
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
