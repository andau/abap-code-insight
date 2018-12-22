package abap.codemining.section;

import java.util.Scanner;

import org.apache.tools.ant.util.StringUtils;

import abap.codemining.state.AbapCodeParsingStartableState;
import abap.codemining.state.AbapCodeParsingState;
import abap.codemining.state.IAbapCodeParsingState;

public class AbapSectionExtractor {

	private final IAbapSectionDefinitionRegex abapSectionDefinition;
	private IAbapCodeParsingState parsingState;

	public AbapSectionExtractor(IAbapSectionDefinitionRegex abapSectionDefinition) {
		this.abapSectionDefinition = abapSectionDefinition;
		parsingState = new AbapCodeParsingStartableState();
	}

	public String extract(String content) {
		Scanner scanner = new Scanner(content);

		StringBuilder sectionStringBuilder = new StringBuilder();

		while (scanner.hasNextLine()) {
			String currentLine = scanner.nextLine();
			if (parsingState.getState().equals(AbapCodeParsingState.STARTABLE)
					&& abapSectionDefinition.matchesStartpoint(currentLine)) {
				switchToNextState();
			}

			if (parsingState.getState().equals(AbapCodeParsingState.ACTIVE)) {
				sectionStringBuilder.append(currentLine + StringUtils.LINE_SEP);
			}

			if (parsingState.getState().equals(AbapCodeParsingState.ACTIVE)
					&& abapSectionDefinition.matchesEndpoint(currentLine)) {
				switchToNextState();
			}

		}

		scanner.close();

		return sectionStringBuilder.toString();

	}

	private void switchToNextState() {
		parsingState = parsingState.getNextState();
	}

}
