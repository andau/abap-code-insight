package abap.codemining.section;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AbapPublicClassHeaderSectionTest {

	private AbapPublicClassHeaderSection abapPublicClassHeaderSection;

	@Before
	public void before() {
		abapPublicClassHeaderSection = new AbapPublicClassHeaderSection();
	}

	@Test
	public void testStartpoint() {
		assertTrue(abapPublicClassHeaderSection.matchesStartpoint("public section."));
		assertTrue(abapPublicClassHeaderSection.matchesStartpoint(" public  section . \"comment "));
	}

	@Test
	public void testEndpoint() {
		assertTrue(abapPublicClassHeaderSection.matchesEndpoint("endclass."));
		assertTrue(abapPublicClassHeaderSection.matchesEndpoint(" endclass . \"comment "));
	}

}
