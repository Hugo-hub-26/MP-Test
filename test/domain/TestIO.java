package domain;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.nio.charset.StandardCharsets;

public class TestIO {

	public static void withStdIn(String input, Runnable body) {
		InputStream backup = System.in;
		try {
			System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
			body.run();
		} finally {
			System.setIn(backup);
		}
	}

	public static String lines(String... parts) {
		return String.join("\n", parts) + "\n";
	}
}
