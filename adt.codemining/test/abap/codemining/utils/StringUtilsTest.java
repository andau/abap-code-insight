package abap.codemining.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void testIsNullOrEmpty() {
		assertTrue(StringUtils.IsNullOrEmpty(null));
		assertTrue(StringUtils.IsNullOrEmpty(""));
		assertTrue(StringUtils.IsNullOrEmpty(StringUtils.EMPTY));

		assertFalse(StringUtils.IsNullOrEmpty("a"));
		assertFalse(StringUtils.IsNullOrEmpty(" "));

	}

}
