package abap.codemining.method;

import java.util.Set;

public class AbapMethodInformation {
	private final Set<AbapMethodHeader> abapMethodHeaders;
	private final Set<AbapMethodBody> abapMethodBodies;

	public AbapMethodInformation(Set<AbapMethodHeader> abapMethodHeaders, Set<AbapMethodBody> abapMethodBodies) {
		this.abapMethodHeaders = abapMethodHeaders;
		this.abapMethodBodies = abapMethodBodies;
	}

	public Set<AbapMethodHeader> getAbapMethodHeaders() {
		return abapMethodHeaders;
	}

	public Set<AbapMethodBody> getAbapMethodBodies() {
		return abapMethodBodies;
	}
}
