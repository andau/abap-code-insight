package abap.codemining.plugin;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.codemining.CodeMiningReconciler;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class AbapCodeMiningPlugin extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "abap-code-insight-plugin"; //$NON-NLS-1$

	private static AbapCodeMiningPlugin plugin;

	private static CodeMiningReconciler codeMiningReconciler;

	public AbapCodeMiningPlugin() {
		codeMiningReconciler = new CodeMiningReconciler();
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

	public static AbapCodeMiningPlugin getDefault() {
		return plugin;
	}

	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public static CodeMiningReconciler getCodeMiningReconciler() {
		return codeMiningReconciler;

	}

}
