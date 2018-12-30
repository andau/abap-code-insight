package abap.codemining.method;

import java.util.Set;

public class AbapMethodInformation {
	private final Set<AbapMethodBody> abapMethodBodies;

	public AbapMethodInformation(Set<AbapMethodBody> abapMethodBodies) {
		this.abapMethodBodies = abapMethodBodies;
	}

	public Set<AbapMethodBody> getAbapMethodBodies() {
		return abapMethodBodies;
	}
}
