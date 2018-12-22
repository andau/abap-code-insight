package abap.codemining.state;

public class AbapCodeParsingStartableState implements IAbapCodeParsingState {

	@Override
	public IAbapCodeParsingState getNextState() {
		return new AbapCodeParsingActiveState();
	}

	@Override
	public AbapCodeParsingState getState() {
		return AbapCodeParsingState.STARTABLE;
	}

}
