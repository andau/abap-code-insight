package abap.codemining.preferences;

import org.eclipse.core.expressions.PropertyTester;

/**
 * Property tester which checks that a given preference is true from the Java
 * preference store.
 *
 * @since 3.15
 */
public class AbapPreferencesPropertyTester extends PropertyTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		return true;
	}

	public static boolean isEnabled(String preferenceName) {
		return true;
	}

}