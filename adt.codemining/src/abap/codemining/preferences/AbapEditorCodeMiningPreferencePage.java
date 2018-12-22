package abap.codemining.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import abap.codemining.plugin.AbapCodeMiningPlugin;

public class AbapEditorCodeMiningPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	final static Color HEADER_COLOR = new Color(Display.getCurrent(), 0, 0, 255);
	PreferencesUiHelper preferencesUiHelper;

	public AbapEditorCodeMiningPreferencePage() {
		super(GRID);
		preferencesUiHelper = new PreferencesUiHelper();
	}

	@Override
	public void createFieldEditors() {

		preferencesUiHelper.addHeaderLabelWithSpaceBefore(getFieldEditorParent(), "Method information");
		createMethodInformationChapter();

	}

	private void createMethodInformationChapter() {
		addField(new BooleanFieldEditor(PreferenceConstants.SHOW_METHOD_VISIBILITY,
				"Show visibility (public, protected, ...) of methods", getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(AbapCodeMiningPlugin.getDefault().getPreferenceStore());
		setDescription("General settings for ABAP Continuous Integration");
	}

}