
package abap.code.insight.debug;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.contexts.DebugContextEvent;
import org.eclipse.debug.ui.contexts.IDebugContextListener;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.codemining.AbstractCodeMiningProvider;
import org.eclipse.jface.text.codemining.ICodeMining;
import org.eclipse.jface.text.source.ISourceViewerExtension5;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.texteditor.ITextEditor;

import abap.codemining.plugin.AbapCodeInsightPluginHelper;

/**
 * Abstract class mining provider to display debug variable value in a given
 * position.
 *
 * @since 3.15
 *
 */
public abstract class AbstractDebugVariableCodeInsightProvider<T extends IStackFrame>
		extends AbstractCodeMiningProvider {

	private IDebugContextListener fContextListener;

	private final Map<RGB, Color> fColorTable;
	private final AbapCodeInsightPluginHelper abapCodeInsightPluginHelper;

	public AbstractDebugVariableCodeInsightProvider() {
		fColorTable = new HashMap<>();
		abapCodeInsightPluginHelper = new AbapCodeInsightPluginHelper();

	}

	@Override
	public final CompletableFuture<List<? extends ICodeMining>> provideCodeMinings(ITextViewer viewer,
			IProgressMonitor monitor) {
		return CompletableFuture.supplyAsync(() -> {
			//monitor.isCanceled();
			final ITextEditor textEditor = super.getAdapter(ITextEditor.class);
			final T stackFrame = getStackFrame(viewer, textEditor);
			if (stackFrame == null) {
				return Collections.emptyList();
			} else {
				try {
					addDebugListener(viewer, stackFrame.getLineNumber());
					return provideCodeMinings(viewer, stackFrame, monitor);
				} catch (final DebugException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return Collections.emptyList();
				}

			}
		});
	}

	private void addDebugListener(ITextViewer viewer, int currentLinenumber) {
		if (fContextListener == null) {
			addSynchronizedDebugListener(viewer, currentLinenumber);
		}
	}

	private synchronized void addSynchronizedDebugListener(ITextViewer viewer, int currentLinenumber) {
		if (fContextListener != null) {
			return;
		}

		if (abapCodeInsightPluginHelper.getCurrentLinenumber() != currentLinenumber) {
			abapCodeInsightPluginHelper.setCurrentLinenumber(currentLinenumber);
			fContextListener = event -> {
				if ((event.getFlags() & DebugContextEvent.ACTIVATED) > 0 && viewer != null) {
					((ISourceViewerExtension5) viewer).updateCodeMinings();
				}
			};
			DebugUITools.addPartDebugContextListener(getSite(), fContextListener);

		}
	}

	private void removeDebugListener() {
		if (fContextListener != null) {
			DebugUITools.removePartDebugContextListener(getSite(), fContextListener);
		}
	}

	private IWorkbenchPartSite getSite() {
		final ITextEditor textEditor = super.getAdapter(ITextEditor.class);
		return textEditor.getSite();
	}

	@Override
	public void dispose() {
		removeDebugListener();
		super.dispose();
		fColorTable.values().forEach(Color::dispose);
	}

	/**
	 * Returns the stack frame in which to search for variables, or
	 * <code>null</code> if none.
	 *
	 * @param viewer
	 * @param stackFrame
	 * @param monitor
	 * @return
	 */
	protected abstract T getStackFrame(ITextViewer viewer, ITextEditor textEditor);

	/**
	 * Collection minings included inside variables of the given stack frame.
	 *
	 * @param viewer
	 * @param stackFrame
	 * @param monitor
	 * @return
	 */
	protected abstract List<? extends ICodeMining> provideCodeMinings(ITextViewer viewer, T stackFrame,
			IProgressMonitor monitor);
}