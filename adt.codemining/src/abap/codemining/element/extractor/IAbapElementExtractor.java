package abap.codemining.element.extractor;

import abap.codemining.element.domain.IAbapElement;

public interface IAbapElementExtractor {

	IAbapElement extractFromLine(String line, int linenumber);
}
