package abap.codemining.element;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.jface.text.IDocument;

import abap.codemining.element.domain.IAbapElement;
import abap.codemining.element.extractor.ClassBodyElementExtractor;
import abap.codemining.element.extractor.ClassHeaderElementExtractor;
import abap.codemining.element.extractor.IAbapElementExtractor;
import abap.codemining.element.extractor.MethodBodyElementExtractor;
import abap.codemining.feature.FeatureFacade;

public class AbapElementParser {

	boolean publicSector = false;
	FeatureFacade featureFacade;

	public AbapElementParser(FeatureFacade featureFacade) {
		this.featureFacade = featureFacade;
	}

	public AbapElementInformation getMethodInformation(IDocument doc) {

		String content = setContentToLower(doc);

		Set<IAbapElement> abapElements = getMethodBodies(content);
		return new AbapElementInformation(abapElements);
	}

	Set<IAbapElement> getMethodBodies(String content) {
		Set<IAbapElement> abapElements = new HashSet<>();

		Scanner scanner = new Scanner(content);

		List<IAbapElementExtractor> elementExtractors = new ArrayList<>();
		elementExtractors.add(new ClassHeaderElementExtractor(featureFacade.getClassHeaderMiningFeature()));
		elementExtractors.add(new ClassBodyElementExtractor(featureFacade.getClassBodyMiningFeature()));
		elementExtractors.add(new MethodBodyElementExtractor(featureFacade.getMethodBodyMiningFeature()));

		int linenumber = 0;
		while (scanner.hasNextLine()) {

			linenumber++;

			String line = scanner.nextLine();

			for (IAbapElementExtractor elementExtractor : elementExtractors) {
				IAbapElement abapElement = elementExtractor.extractFromLine(line, linenumber);
				if (abapElement != null) {
					abapElements.add(abapElement);
				}
			}

		}

		scanner.close();

		return abapElements;
	}

	private String setContentToLower(IDocument doc) {
		String content = doc.get();
		content = content.toLowerCase().trim();
		return content;
	}

}
