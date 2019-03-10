package abap.codemining.element;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import abap.codemining.utils.StringUtils;

public class ElementLinkInfoTest {

	private static final String STANDARD_TESTLINK = "./source/main#start=4,6;end=4,20";

	@Test
	public void testEmptyElementlinkInfo() {
		final ElementLinkInfo cut = new ElementLinkInfo(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
		assertEquals(StringUtils.EMPTY, cut.getLink());
	}

	@Test
	public void testStandardElementlinkInfo() throws InvalidElementLinkException {
		final ElementLinkInfo cut = new ElementLinkInfo(STANDARD_TESTLINK, StringUtils.EMPTY, StringUtils.EMPTY);
		assertEquals(STANDARD_TESTLINK, cut.getLink());
		assertEquals(4, cut.getLinenumber());
		assertEquals(6, cut.getElementStartposition());
	}

}
