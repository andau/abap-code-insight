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

@Deprecated
public class DeprecatedAbapClassElementParser implements IAbapElementParser {

	boolean publicSector = false;
	FeatureFacade featureFacade;

	public DeprecatedAbapClassElementParser(FeatureFacade featureFacade) {
		this.featureFacade = featureFacade;
	}

	@Override
	public AbapElementInformation getElementInformation(IDocument doc) {

		final String content = setContentToLower(doc);

		final Set<IAbapElement> abapElements = getClassElements(content);
		return new AbapElementInformation(abapElements);
	}

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

	private String setContentToLower(IDocument doc) {
		String content = doc.get();
		content = content.toLowerCase().trim();
		return content;
	}

}
