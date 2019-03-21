package abap.codemining.plugin;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.ITextViewer;

import abap.codemining.editor.TextEditorMiningsProxy;

public class AbapCodeInsightPluginHelper {

	public IPreferenceStore getPreferenceStore() {
		return AbapCodeInsightPlugin.getDefault().getPreferenceStore();
	}

	public ImageDescriptor getImageDescriptor(String imageString) {
		return AbapCodeInsightPlugin.getImageDescriptor(imageString);
	}

	public void reinitViewInReconcilers(ITextViewer viewer) {
		AbapCodeInsightPlugin.reinitViewInReconcilers(viewer);
	}

	public int getCurrentLinenumber() {
		return AbapCodeInsightPlugin.getLinenumber();
	}

	public void setCurrentLinenumber(int linenumber) {
		AbapCodeInsightPlugin.setCurrentLinenumber(linenumber);
	}

	public TextEditorMiningsProxy getTextEditorMiningsProxy() {
		return AbapCodeInsightPlugin.getTextEditorMiningsProxy();
	}

}
