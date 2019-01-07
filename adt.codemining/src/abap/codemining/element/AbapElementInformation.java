package abap.codemining.element;

import java.util.Set;

import abap.codemining.element.domain.IAbapElement;

public class AbapElementInformation {
	private final Set<IAbapElement> abapElements;

	public AbapElementInformation(Set<IAbapElement> abapElements) {
		this.abapElements = abapElements;
	}

	public Set<IAbapElement> getAbapElements() {
		return abapElements;
	}
}
