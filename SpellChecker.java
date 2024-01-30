
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		String tailStr = str.substring(1);
		return tailStr;
	}

	public static int levenshtein(String word1, String word2) {
		String a = word1.toLowerCase();
		String b = word2.toLowerCase();

		if (b.length() == 0) {
			return a.length();
		}
		if (a.length() == 0) {
			return b.length();
		}
		if (a.charAt(0) == b.charAt(0)) {
			return levenshtein(tail(a),tail(b));
		}
		else{
				return (1 + Math.min(Math.min(levenshtein(tail(a), b), levenshtein(a, tail(b))), levenshtein(tail(a), tail(b))));
			}
		}
	

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String loweCaseWord = word.toLowerCase();
		int checker = levenshtein(loweCaseWord, dictionary[0]);
		String correctWord = dictionary[0];
		if (HashTagTokenizer.existInDictionary(loweCaseWord, dictionary)) {
			return word;
		}
		for (int i = 1; i < dictionary.length; i++) {
			if ((levenshtein(loweCaseWord, dictionary[i]) < checker)) {
				checker = levenshtein(loweCaseWord, dictionary[i]);
				correctWord = dictionary[i];
			}
		}
		if (checker <= threshold) {
			return correctWord;
		}
		
		return word;
	}

}
