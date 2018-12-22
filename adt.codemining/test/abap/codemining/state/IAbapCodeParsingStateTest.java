package abap.codemining.state;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IAbapCodeParsingStateTest {

	@Test
	public void testGetNextState() {
		IAbapCodeParsingState parsingState = new AbapCodeParsingStartableState();
		assertEquals(AbapCodeParsingState.STARTABLE, parsingState.getState());

		parsingState = parsingState.getNextState();
		assertEquals(AbapCodeParsingState.ACTIVE, parsingState.getState());

		parsingState = parsingState.getNextState();
		assertEquals(AbapCodeParsingState.FINISHED, parsingState.getState());
	}

}
