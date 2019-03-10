package abap.codemining.element.domain;

import abap.codemining.label.IMiningLabelBuilder;

public interface IAbapElement {

	int getLinenumber();

	int getElementPosition();

	int getMiningStartindex();

	String getElementname();

	IMiningLabelBuilder getMiningLabelBuilder();

}
