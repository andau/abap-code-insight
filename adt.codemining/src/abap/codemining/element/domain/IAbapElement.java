package abap.codemining.element.domain;

import abap.codemining.label.IMiningLabelBuilder;

public interface IAbapElement {

	int getLinenumber();

	int getStartindex();

	String getElementname();

	IMiningLabelBuilder getMiningLabelBuilder();

}
