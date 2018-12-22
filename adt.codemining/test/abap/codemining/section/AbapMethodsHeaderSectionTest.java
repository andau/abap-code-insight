package abap.codemining.section;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AbapMethodsHeaderSectionTest {

	AbapMethodsHeaderSection abapMethodHeaderSection;

	@Before
	public void before() {
		abapMethodHeaderSection = new AbapMethodsHeaderSection();
	}

	@Test
	public void testStartpoint() {
		assertTrue(abapMethodHeaderSection.matchesStartpoint("methods:"));
		assertTrue(abapMethodHeaderSection.matchesStartpoint("methods: first method, "));

	}

}
