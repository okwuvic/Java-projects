package assignmentUnit5;

/**
 * 
 * @author Victor Nwankpa
 * 
 * @since 12/05/2020 This class reads a file, checks its words for error and
 * 
 *        suggests a list of probable corrections for the bad words.
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.JFileChooser;

public class SpellCheck {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner words = new Scanner(
				new File("C:/Users/Nwankpa/eclipse-workspace/assignmentUnit5/src/assignmentUnit5/words.txt"));
		;

		HashSet<String> dict = new HashSet<String>();

		Scanner textFile = new Scanner(getInputFileNameFromUser());
		;

		while (words.hasNext()) {

			String word = words.next();

			dict.add(word.toLowerCase());

		}

		// Skip over any non-letter characters in the file.

		textFile.useDelimiter("[^a-zA-Z]+");

		HashSet<String> badWords = new HashSet<String>();

		while (textFile.hasNext()) {

			String userWord = textFile.next();

			userWord = userWord.toLowerCase();

			if (!dict.contains(userWord)

			) {

				badWords.add(userWord);

				TreeSet<String> validWords = new TreeSet<String>();

				validWords = corrections(userWord, dict);

				System.out.print(userWord + ": ");

				if (validWords.isEmpty())

					System.out.println("(no suggestions)");

				else {

					int count = 0;

					for (String Word : validWords) {

						System.out.print(Word);

						if (count < validWords.size() - 1)

							System.out.print(",");

						else

							System.out.print("\n");

						count++;

					}

				}

			}

		}

		System.exit(0);

	}

	// end main()

	static File getInputFileNameFromUser() {

		JFileChooser fileDialog = new JFileChooser();

		fileDialog.setDialogTitle("Select File for Input");

		int option = fileDialog.showOpenDialog(null);

		if (option != JFileChooser.APPROVE_OPTION)

			return null;

		else

			return fileDialog.getSelectedFile();

	} // end getInputFileNameFromUser()

	static TreeSet<String> corrections(String badWord, HashSet<String> dictionary) {

		TreeSet<String> suggestions = new TreeSet<String>();

		String w, s, suggest;

		for (int i = 0; i < badWord.length(); i++) {

			// Remove character i from the word.

			w = badWord.substring(0, i);

			s = badWord.substring(i + 1);

			// Delete any one of the letters from the misspelled word.

			suggest = w + s;

			if (dictionary.contains(suggest))

				suggestions.add(suggest);

			// Change any letter in the misspelled word into any other

			// letter.

			for (char ch = 'a'; ch <= 'z'; ch++) {

				suggest = w + ch + s;

				if (dictionary.contains(suggest))

					suggestions.add(suggest);

			}

			// Divide the word into two substrings.

			w = badWord.substring(0, i);

			s = badWord.substring(i);

			// Insert any letter at any point in the misspelled word.

			for (char ch = 'a'; ch <= 'z'; ch++) {

				suggest = w + ch + s;

				if (dictionary.contains(suggest))

					suggestions.add(suggest);

			}

			// Insert a space at any point in the misspelled word and check

			// that both of the words that are produced are in the dictionary.

			char ch = ' ';

			suggest = w + ch + s;

			if (dictionary.contains(w) && dictionary.contains(s))

				suggestions.add(suggest);

		}

		// Swap any two neighbouring characters in the misspelled word.

		for (int i = 1; i < badWord.length(); i++) {

			w = badWord.substring(0, i - 1);

			char ch1 = badWord.charAt(i - 1);

			char ch2 = badWord.charAt(i);

			s = badWord.substring(i + 1);

			suggest = w + ch2 + ch1 + s;

			if (dictionary.contains(suggest))

				suggestions.add(suggest);

		}

		return suggestions;

	}

}