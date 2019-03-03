package abap.codemining.element;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.abapsource.sources.objectstructure.IObjectStructureElement;
import com.sap.adt.tools.abapsource.sources.objectstructure.IObjectStructureService;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.FeatureFacade;

public class AbapClassElementParser implements IAbapElementParser {

	FeatureFacade featureFacade;
	IAbapProject abapProject;
	URI uri;

	AbapClassElementParserFactory abapClassElementParserFactory;

	public AbapClassElementParser(FeatureFacade featureFacade, URI uri, IAbapProject abapProject) {
		this.featureFacade = featureFacade;
		this.uri = uri;
		this.abapProject = abapProject;
		this.abapClassElementParserFactory = new AbapClassElementParserFactory();
	}

	@Override
	public AbapElementInformation getElementInformation(IDocument doc) {
		final Set<IAbapElement> abapElements = new HashSet<>();

		try {
			final IObjectStructureService objectStructureService = abapClassElementParserFactory
					.createObjectStructureService(abapProject);
			final IObjectStructureElement objectStructureElement = abapClassElementParserFactory
					.createObjectStructureElementClass(objectStructureService, uri);

			abapElements.addAll(
					abapClassElementParserFactory.createAbapElements(doc, objectStructureElement, featureFacade));
			for (final IObjectStructureElement childObjectStructureElement : objectStructureElement.getChildren()) {
				abapElements.addAll(abapClassElementParserFactory.createAbapElements(doc, childObjectStructureElement,
						featureFacade));
			}

		} catch (final OutOfSessionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new AbapElementInformation(abapElements);
	}

}
