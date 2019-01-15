package abap.codemining.element;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.eclipse.jdt.experimental.ui.javaeditor.codemining.SampleDocumentContent;
import org.eclipse.jface.text.IDocument;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.FeatureFacade;

public class AbapElementParserTest {

	AbapClassElementParser cut;
	IDocument document = Mockito.mock(IDocument.class);
	private SampleDocumentContent sampleDocumentContents;

	FeatureFacade featureFacade = Mockito.mock(FeatureFacade.class);

	@Before
	public void before() {
		cut = new AbapClassElementParser(featureFacade);
		sampleDocumentContents = new SampleDocumentContent();

	}

	@Test
	public void testGetMethodBodies() {
		final Set<IAbapElement> abapElements = cut
				.getClassElements(sampleDocumentContents.getOneSimpleMethodContent().toLowerCase());
		assertEquals(3, abapElements.size());
		assertTrue(abapElements.stream().anyMatch(item -> item.getElementname().equals("samplemethod2")));
	}

}
