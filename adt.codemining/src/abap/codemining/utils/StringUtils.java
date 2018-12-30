package abap.codemining.utils;

public class StringUtils {

	public static final String SPACE = " ";
	public static String EMPTY = "";

	public static boolean IsNullOrEmpty(String string) {
		return (string == null || string.length() == 0);
	}

}
