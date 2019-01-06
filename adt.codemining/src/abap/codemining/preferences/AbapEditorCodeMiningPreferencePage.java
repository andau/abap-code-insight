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

		preferencesUiHelper.addHeaderLabelWithSpaceBefore(getFieldEditorParent(), "Class header");
		createClassHeaderSection();

		preferencesUiHelper.addHeaderLabelWithSpaceBefore(getFieldEditorParent(), "Class body");
		createClassBodySection();

	}

	private void createClassHeaderSection() {
		addField(new BooleanFieldEditor(PreferenceConstants.SHOW_CLASS_HEADER_REFERENCE_COUNT,
				"Show reference count on class headers", getFieldEditorParent()));

}
	private void createClassBodySection() {

		addField(new BooleanFieldEditor(PreferenceConstants.SHOW_CLASS_BODY_REFERENCE_COUNT,
				"Show reference count on class bodies", getFieldEditorParent()));

		addField(new BooleanFieldEditor(PreferenceConstants.SHOW_METHOD_BODY_REFERENCE_COUNT,
				"Show reference count on method bodies", getFieldEditorParent()));

		addField(new BooleanFieldEditor(PreferenceConstants.SHOW_METHOD_BODY_SIGNATURE,
				"Show signature on method bodies", getFieldEditorParent()));

	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(AbapCodeMiningPlugin.getDefault().getPreferenceStore());
		setDescription("General settings for ABAP Continuous Integration");
	}

}