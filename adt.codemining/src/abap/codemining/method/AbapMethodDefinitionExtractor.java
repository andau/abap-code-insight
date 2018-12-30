package abap.codemining.method;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.jface.text.IDocument;

public class AbapMethodDefinitionExtractor {

	boolean publicSector = false;

	public AbapMethodInformation getMethodInformation(IDocument doc) {

		String content = setContentToLower(doc);

		Set<AbapMethodBody> methodBodies = getMethodBodies(content);
		return new AbapMethodInformation(methodBodies);
	}

	Set<AbapMethodBody> getMethodBodies(String content) {
		Set<AbapMethodBody> abapMethodBodies = new HashSet<>();

		Scanner scanner = new Scanner(content);

		AbapMethodBodyExtractor abapmethodBodyExtractor = new AbapMethodBodyExtractor();

		int linenumber = 0;
		while (scanner.hasNextLine()) {
			linenumber++;

			AbapMethodBody abapMethodBody = abapmethodBodyExtractor.extract(scanner.nextLine(), linenumber);

			if (abapMethodBody != null) {
				abapMethodBodies.add(abapMethodBody);
			}

		}

		scanner.close();

		return abapMethodBodies;
	}

	private String setContentToLower(IDocument doc) {
		String content = doc.get();
		content = content.toLowerCase().trim();
		return content;
	}

}
