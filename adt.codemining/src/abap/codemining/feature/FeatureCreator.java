package abap.codemining.feature;

import org.eclipse.jface.preference.IPreferenceStore;

import abap.codemining.plugin.AbapCodeInsightPluginHelper;
import abap.codemining.preferences.PreferenceConstants;

public class FeatureCreator {

	private IPreferenceStore prefs;
	AbapCodeInsightPluginHelper abapCodeMiningPluginHelper;

	public FeatureCreator() {
		abapCodeMiningPluginHelper = new AbapCodeInsightPluginHelper();
	}

	private void initPrefs() {
		prefs = (prefs == null) ? abapCodeMiningPluginHelper.getPreferenceStore() : prefs;
	}

	public ClassMiningFeature createClassMiningFeature() {
		initPrefs();
		final ClassMiningFeature classMiningFeature = new ClassMiningFeature();
		classMiningFeature.setClassDefinitionReferenceEnabled(
				prefs.getBoolean(PreferenceConstants.SHOW_CLASS_DEFINITION_REFERENCE_ENABLED));
		classMiningFeature.setClassImplemenationReferenceEnabled(
				prefs.getBoolean(PreferenceConstants.SHOW_CLASS_IMPLEMENTATION_REFERENCE_ENABLED));
		classMiningFeature.setMethodDefinitionReferenceEnabled(
				prefs.getBoolean(PreferenceConstants.SHOW_CLASS_METHOD_DEFINITION_REFERENCE_ENABLED));
		classMiningFeature.setMethodImplemenationReferenceEnabled(
				prefs.getBoolean(PreferenceConstants.SHOW_CLASS_METHOD_IMPLEMENTATION_REFERENCE_ENABLED));
		classMiningFeature.setMethodImplemenationSignatureEnabled(
				prefs.getBoolean(PreferenceConstants.SHOW_CLASS_METHOD_IMPLEMENTATION_SIGNATURE));
		classMiningFeature.setClassAttributeReferenceEnabled(
				prefs.getBoolean(PreferenceConstants.SHOW_CLASS_PROPERTIES_REFERENCE_ENABLED));
		classMiningFeature
				.setClassTypeElementEnabled(prefs.getBoolean(PreferenceConstants.SHOW_CLASS_TYPE_REFERENCE_ENABLED));

		classMiningFeature.setClassUnknownElementEnabled(
				prefs.getBoolean(PreferenceConstants.SHOW_CLASS_UNKNOWN_REFERENCE_ENABLED));

		return classMiningFeature;
	}

	public ReferenceMiningFeature createStructureMiningFeature() {
		initPrefs();
		return new ReferenceMiningFeature(prefs.getBoolean(PreferenceConstants.SHOW_STRUCTURE_HEADER_REFERENCE_COUNT));
	}

	public ReferenceMiningFeature createCdsMiningFeature() {
		initPrefs();
		return new ReferenceMiningFeature(prefs.getBoolean(PreferenceConstants.SHOW_CDS_HEADER_REFERENCE_COUNT));
	}

	public ReferenceMiningFeature createFmMiningFeature() {
		initPrefs();
		return new ReferenceMiningFeature(prefs.getBoolean(PreferenceConstants.SHOW_FM_HEADER_REFERENCE_COUNT));
	}

	public ReferenceMiningFeature createReportMiningFeature() {
		initPrefs();
		return new ReferenceMiningFeature(prefs.getBoolean(PreferenceConstants.SHOW_REPORT_HEADER_REFERENCE_COUNT));
	}

	public ReferenceMiningFeature createInterfaceMiningFeature() {
		initPrefs();
		return new ReferenceMiningFeature(prefs.getBoolean(PreferenceConstants.SHOW_INTERFACE_HEADER_REFERENCE_COUNT));
	}

	public TestReferencesFeature createTestReferencesFeature() {
		initPrefs();
		return new TestReferencesFeature(prefs.getBoolean(PreferenceConstants.SHOW_TEST_REFERENCE_COUNT_SEPARATELY));
	}

	public DebugCodeInsightFeature createDebugCodeInsightFeature() {
		initPrefs();
		return new DebugCodeInsightFeature(prefs.getBoolean(PreferenceConstants.SHOW_DEBUG_VARIABLE_VALUES));
	}

	public PerformanceFeature createPerformanceFeature() {
		initPrefs();
		final boolean activated = prefs.getBoolean(PreferenceConstants.UPDATE_CHANGED_EDITORS);
		final int maxLines = prefs.getInt(PreferenceConstants.OMIT_CODE_INSIGHT_FOR_MORE_LINES_THAN);
		return new PerformanceFeature(activated, maxLines);
	}

}
