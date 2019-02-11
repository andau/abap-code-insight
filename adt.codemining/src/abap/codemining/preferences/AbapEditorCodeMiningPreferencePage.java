package abap.codemining.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import abap.codemining.plugin.AbapCodeInsightPlugin;

public class AbapEditorCodeMiningPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	final static Color HEADER_COLOR = new Color(Display.getCurrent(), 0, 0, 255);
	PreferencesUiHelper preferencesUiHelper;

	public AbapEditorCodeMiningPreferencePage() {
		super(GRID);
		preferencesUiHelper = new PreferencesUiHelper();
	}

	@Override
	public void createFieldEditors() {
		preferencesUiHelper.addBigHeaderLabelWithoutSpaceBefore(getFieldEditorParent(),
				"if code insights are not shown after an update ");
		preferencesUiHelper.addBigHeaderLabelWithoutSpaceBefore(getFieldEditorParent(),
				"start eclipse onces from the command line with: eclipse.exe -clean ");

		preferencesUiHelper.addHeaderLabelWithSpaceBefore(getFieldEditorParent(), "Show reference count information");
		createReferenceCountSection();

		preferencesUiHelper.addHeaderLabelWithSpaceBefore(getFieldEditorParent(), "Method signature");
		createMethodSignatureSection();

		preferencesUiHelper.addHeaderLabelWithSpaceBefore(getFieldEditorParent(), "Debug view");
		createDebugSection();

		preferencesUiHelper.addHeaderLabelWithSpaceBefore(getFieldEditorParent(), "Update behaviour");
		createPerformanceSection();

	}

	private void createReferenceCountSection() {

		addField(new BooleanFieldEditor(PreferenceConstants.SHOW_CLASS_HEADER_REFERENCE_COUNT, "Class headers",
				getFieldEditorParent()));

		addField(new BooleanFieldEditor(PreferenceConstants.SHOW_CLASS_BODY_REFERENCE_COUNT, "Class bodies",
				getFieldEditorParent()));

		addField(new BooleanFieldEditor(PreferenceConstants.SHOW_METHOD_BODY_REFERENCE_COUNT, "Method bodies",
				getFieldEditorParent()));

		addField(new BooleanFieldEditor(PreferenceConstants.SHOW_INTERFACE_HEADER_REFERENCE_COUNT, "Interfaces",
				getFieldEditorParent()));

		addField(new BooleanFieldEditor(PreferenceConstants.SHOW_CDS_HEADER_REFERENCE_COUNT, "CDS views",
				getFieldEditorParent()));

		addField(new BooleanFieldEditor(PreferenceConstants.SHOW_FM_HEADER_REFERENCE_COUNT, "Function modules",
				getFieldEditorParent()));

		addField(new BooleanFieldEditor(PreferenceConstants.SHOW_REPORT_HEADER_REFERENCE_COUNT, "Reports",
				getFieldEditorParent()));

		addField(new BooleanFieldEditor(PreferenceConstants.SHOW_TEST_REFERENCE_COUNT_SEPARATELY,
				"Show count for references in test separately", getFieldEditorParent()));

	}

	private void createMethodSignatureSection() {

		addField(new BooleanFieldEditor(PreferenceConstants.SHOW_METHOD_BODY_SIGNATURE,
				"Show signature on method bodies", getFieldEditorParent()));

	}

	private void createDebugSection() {
		addField(new BooleanFieldEditor(PreferenceConstants.SHOW_DEBUG_VARIABLE_VALUES,
				"Show values of variables in debug view (experimental)", getFieldEditorParent()));

	}

	private void createPerformanceSection() {
		addField(new BooleanFieldEditor(PreferenceConstants.UPDATE_CHANGED_EDITORS,
				"Refresh code insight information when editors are saved", getFieldEditorParent()));
		addField(new IntegerFieldEditor(PreferenceConstants.OMIT_CODE_INSIGHT_FOR_MORE_LINES_THAN,
				"Omit code insight for editors with more lines than", getFieldEditorParent()));

	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(AbapCodeInsightPlugin.getDefault().getPreferenceStore());
		setDescription("General settings for ABAP Code Insight");
	}

}