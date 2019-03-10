package abap.codemining.preferences;

import org.eclipse.core.expressions.PropertyTester;

import abap.codemining.plugin.AbapCodeInsightPluginHelper;

/**
 * Property tester which checks that a given preference is true from the Java
 * preference store.
 *
 * @since 3.15
 */
public class AbapPreferencesPropertyTester extends PropertyTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		final String preferenceName = expectedValue.toString();
		return isEnabled(preferenceName);
	}

	public static boolean isEnabled(String preferenceName) {
		final AbapCodeInsightPluginHelper abapCodeInsightPluginHelper = new AbapCodeInsightPluginHelper();
		return abapCodeInsightPluginHelper.getPreferenceStore().getBoolean(preferenceName);
	}

}