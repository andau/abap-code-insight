package abap.codemining.element;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.jface.text.IDocument;

import abap.codemining.element.domain.IAbapElement;
import abap.codemining.element.extractor.FunctionModuleElementExtractor;
import abap.codemining.element.extractor.IAbapElementExtractor;
import abap.codemining.feature.FeatureFacade;

public class FunctionModuleElementParser implements IAbapElementParser {

	FeatureFacade featureFacade;

	public FunctionModuleElementParser(FeatureFacade featureFacade) {
		this.featureFacade = featureFacade;
	}

	@Override
	public AbapElementInformation getElementInformation(IDocument doc) {

		final String content = setContentToLower(doc);

		final Set<IAbapElement> abapElements = getFunctionModuleElements(content);
		return new AbapElementInformation(abapElements);
	}

	Set<IAbapElement> getFunctionModuleElements(String content) {
		final Set<IAbapElement> abapElements = new HashSet<>();

		final Scanner scanner = new Scanner(content);

		final List<IAbapElementExtractor> elementExtractors = new ArrayList<>();
		elementExtractors.add(new FunctionModuleElementExtractor(featureFacade.getFunctionModuleMiningFeature()));

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
