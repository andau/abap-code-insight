package abap.codemining.element;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.sap.adt.tools.abapsource.sources.objectstructure.IObjectStructureElement;
import com.sap.adt.tools.core.model.atom.IAtomLink;

import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.FeatureFacade;

public class AbapClassElementParserFactoryTest {

	private final IAtomLink ONE_SIMPLE_LINK = Mockito.mock(IAtomLink.class);
	IDocument doc = Mockito.mock(IDocument.class);
	IObjectStructureElement objectStructureElement = Mockito.mock(IObjectStructureElement.class);
	FeatureFacade featureFacade = Mockito.mock(FeatureFacade.class);

	@Before
	public void Before() {
		Mockito.when(ONE_SIMPLE_LINK.toString()).thenReturn("#start=123,");
		Mockito.when(ONE_SIMPLE_LINK.getRel()).thenReturn("definitionIdentifier");
	}

	@Test
	public void createAbapElementsNullTest() throws BadLocationException {
		final AbapClassElementParserFactory cut = new AbapClassElementParserFactory();
		final Set<IAbapElement> abapElements = cut.createAbapElements(doc, objectStructureElement, featureFacade);
		assertTrue(abapElements.isEmpty());
	}

	@Test
	public void createAbapElementsSimpleTest() throws BadLocationException {
		final List<IAtomLink> oneSimpleLinkList = new ArrayList<>();
		oneSimpleLinkList.add(ONE_SIMPLE_LINK);

		Mockito.when(objectStructureElement.getLinks()).thenReturn(oneSimpleLinkList);
		final AbapClassElementParserFactory cut = new AbapClassElementParserFactory();
		final Set<IAbapElement> abapElements = cut.createAbapElements(doc, objectStructureElement, featureFacade);
		assertEquals(1, abapElements.size());
	}

}
