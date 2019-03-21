package abap.codemining.plugin;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.codemining.CodeMiningReconciler;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import abap.codemining.editor.TextEditorMiningsProxy;

/**
 * The activator class controls the plug-in life cycle
 */
public class AbapCodeInsightPlugin extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "abap.code.insight.plugin"; //$NON-NLS-1$

	private static AbapCodeInsightPlugin plugin;
	private static Map<ITextViewer, CodeMiningReconciler> reconcilerMap;

	private static TextEditorMiningsProxy textEditorMiningsProxy;
	private static int linenumber;

	public AbapCodeInsightPlugin() {
		reconcilerMap = new HashMap<>();
		textEditorMiningsProxy = new TextEditorMiningsProxy();
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static AbapCodeInsightPlugin getDefault() {
		return plugin;
	}

	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public static void reinitViewInReconcilers(ITextViewer textViewer) {
		if (reconcilerMap.containsKey(textViewer)) {
			reconcilerMap.get(textViewer).uninstall();
			reconcilerMap.get(textViewer).install(textViewer);
		} else {
			final CodeMiningReconciler codeMiningReconciler = new CodeMiningReconciler();
			codeMiningReconciler.install(textViewer);
			reconcilerMap.put(textViewer, codeMiningReconciler);
		}
	}

	public static int getLinenumber() {
		return linenumber;
	}

	public static void setCurrentLinenumber(int linenumber2) {
		linenumber = linenumber;
	}

	public static TextEditorMiningsProxy getTextEditorMiningsProxy() {
		return textEditorMiningsProxy;
	}

}
