import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Solution {

	static String encryption(String s) {

		s = s.trim();
		int ceil = (int) Math.ceil(Math.sqrt(s.length()));
		String[] words = new String[ceil];
		for (int i = 0; i < ceil; i++) {
			words[i] = "";
		}

		for (int k = 0; k < s.length(); k++)
			words[k % ceil] += String.valueOf(s.charAt(k));

		return String.join(" ", words);
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String s = scanner.nextLine();

		String result = encryption(s);

		bufferedWriter.write(result);
		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}
