package abap.codemining.element;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.abapsource.sources.objectstructure.IObjectStructureElement;
import com.sap.adt.tools.abapsource.sources.objectstructure.IObjectStructureService;
import com.sap.adt.tools.core.model.adtcore.AdtVersionEnum;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.adt.AbapCodeServiceFactory;
import abap.codemining.element.domain.AbapClassBody;
import abap.codemining.element.domain.IAbapElement;
import abap.codemining.element.extractor.ClassBodyElementExtractor;
import abap.codemining.element.extractor.ClassHeaderElementExtractor;
import abap.codemining.element.extractor.ElementLineInfoExtractor;
import abap.codemining.element.extractor.IAbapElementExtractor;
import abap.codemining.element.extractor.MethodBodyElementExtractor;
import abap.codemining.feature.FeatureFacade;
import abap.codemining.label.ClassMiningLabelBuilder;

public class AbapClassElementParser implements IAbapElementParser {

	boolean publicSector = false;
	FeatureFacade featureFacade;

	IAbapProject abapProject;
	URI uri;

	public AbapClassElementParser(FeatureFacade featureFacade, URI uri, IAbapProject abapProject) {
		this.featureFacade = featureFacade;
		this.uri = uri;
		this.abapProject = abapProject;
	}

	@Override
	public AbapElementInformation getElementInformation(IDocument doc) {
		final Set<IAbapElement> abapElements = new HashSet<>();

		try {
			final AbapCodeServiceFactory abapCodeServiceFactory = new AbapCodeServiceFactory();
			final IObjectStructureService objectStructureService = abapCodeServiceFactory
					.createObjectStructureService(abapProject.getDestinationId());
			final URI baseUri = URI.create(uri.toString().replace("source/main", "objectstructure"));
			final IObjectStructureElement objectStructureElement = objectStructureService
					.getObjectStructureTree(baseUri, AdtVersionEnum.ACTIVE, true, true, new NullProgressMonitor());

			for (final IObjectStructureElement childObjectStructureElement : objectStructureElement.getChildren()) {

				final ElementLineInfoExtractor elementLineInfoExtractor = new ElementLineInfoExtractor();
				final int linenumber = elementLineInfoExtractor.extractLinenumber(childObjectStructureElement);
				final int lineEndposition = doc.getLineLength(linenumber);
				abapElements.add(new AbapClassBody(childObjectStructureElement.getName(), linenumber, lineEndposition,
						new ClassMiningLabelBuilder(featureFacade.getClassHeaderMiningFeature())));
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

	@Deprecated
	Set<IAbapElement> getClassElements(String content) {
		final Set<IAbapElement> abapElements = new HashSet<>();

		final Scanner scanner = new Scanner(content);

		final List<IAbapElementExtractor> elementExtractors = new ArrayList<>();
		elementExtractors.add(new ClassHeaderElementExtractor(featureFacade.getClassHeaderMiningFeature()));
		elementExtractors.add(new ClassBodyElementExtractor(featureFacade.getClassBodyMiningFeature()));
		elementExtractors.add(new MethodBodyElementExtractor(featureFacade.getMethodBodyMiningFeature()));

		int linenumber = 0;
		while (scanner.hasNextLine()) {

			linenumber++;

			final String line = scanner.nextLine();

			for (final IAbapElementExtractor elementExtractor : elementExtractors) {
				final IAbapElement abapElement = elementExtractor.extractFromLine(line, linenumber);
				if (abapElement != null) {
					abapElements.add(abapElement);
				}
			}

		}

		scanner.close();

		return abapElements;
	}

}
