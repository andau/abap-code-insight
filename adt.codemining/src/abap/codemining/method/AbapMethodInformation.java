package abap.codemining.method;

import java.util.Set;

import abap.codemining.element.IAbapElement;

public class AbapMethodInformation {
	private final Set<IAbapElement> abapMethodBodies;

	public AbapMethodInformation(Set<IAbapElement> abapElements) {
		this.abapMethodBodies = abapElements;
	}

	public Set<IAbapElement> getAbapElements() {
		return abapMethodBodies;
	}
}
