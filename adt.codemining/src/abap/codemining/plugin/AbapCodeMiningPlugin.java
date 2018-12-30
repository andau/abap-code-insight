package abap.codemining.plugin;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.codemining.CodeMiningReconciler;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class AbapCodeMiningPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "abap.codemining"; //$NON-NLS-1$

	// The shared instance
	private static AbapCodeMiningPlugin plugin;

	private static CodeMiningReconciler codeMiningReconciler;

	/**
	 * The constructor
	 */
	public AbapCodeMiningPlugin() {
		codeMiningReconciler = new CodeMiningReconciler();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 * BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static AbapCodeMiningPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in relative
	 * path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public static CodeMiningReconciler getCodeMiningReconciler() {
		// TODO Auto-generated method stub
		return codeMiningReconciler;

	}

}
