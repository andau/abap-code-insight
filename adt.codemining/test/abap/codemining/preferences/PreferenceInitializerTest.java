package abap.codemining.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import abap.codemining.plugin.AbapCodeInsightPluginHelper;

public class PreferenceInitializerTest {

	PreferenceInitializer cut;
	AbapCodeInsightPluginHelper pluginHelper = Mockito.mock(AbapCodeInsightPluginHelper.class);
	private final IPreferenceStore preferenceStore = Mockito.mock(IPreferenceStore.class);

	@Before
	public void before() {
		cut = new PreferenceInitializer();
		Whitebox.setInternalState(cut, "abapCodeMiningPluginHelper", pluginHelper);
		Mockito.when(pluginHelper.getPreferenceStore()).thenReturn(preferenceStore);
	}

	@Test
	public void test() {
		cut.initializeDefaultPreferences();
		Mockito.verify(preferenceStore).setDefault(PreferenceConstants.SHOW_CLASS_DEFINITION_REFERENCE_ENABLED, true);
		Mockito.verify(preferenceStore).setDefault(PreferenceConstants.SHOW_CLASS_IMPLEMENTATION_REFERENCE_ENABLED,
				true);
		Mockito.verify(preferenceStore).setDefault(PreferenceConstants.SHOW_INTERFACE_HEADER_REFERENCE_COUNT, true);

		Mockito.verify(preferenceStore).setDefault(PreferenceConstants.SHOW_CLASS_METHOD_IMPLEMENTATION_SIGNATURE,
				true);

		Mockito.verify(preferenceStore).setDefault(PreferenceConstants.SHOW_CDS_HEADER_REFERENCE_COUNT, true);
		Mockito.verify(preferenceStore).setDefault(PreferenceConstants.SHOW_FM_HEADER_REFERENCE_COUNT, true);
		Mockito.verify(preferenceStore).setDefault(PreferenceConstants.SHOW_REPORT_HEADER_REFERENCE_COUNT, true);
		Mockito.verify(preferenceStore).setDefault(PreferenceConstants.SHOW_TEST_REFERENCE_COUNT_SEPARATELY, true);
	}

}
