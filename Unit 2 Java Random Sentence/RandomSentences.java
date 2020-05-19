package AssignmentUnit1;

import java.util.Random;

public class RandomSentences {

	private static final String[] conjunctions = { "and", "or", "but", "because", "since", "when" };

	private static final String[] properNouns = { "Fred", "Jane", "Richard Nixon", "Miss America", "Donald Trump",
			"Hillary Clinton" };

	private static final String[] commonNouns = { "man", "woman", "fish", "elephant", "unicorn", "lamp", "trident",
			"stunt-man" };

	private static final String[] determiners = { "a", "the", "every", "some", "each", "neither", "either" };

	private static final String[] adjectives = { "big", "tiny", "pretty", "bald", "strange", "wacky", "gross", "cool",
			"silly", "careless" };

	private static final String[] intransitiveVerbs = { "runs", "jumps", "talks", "sleeps", "agrees", "lies",
			"laughs" };

	private static final String[] transitiveVerbs = { "loves", "hates", "sees", "knows", "looks for", "finds",
			"admires", "bathes" };

	private static Random rGen = new Random();

	private static final double SMALL_CHANCE = 0.1;
	private static final double MEDIUM_CHANCE = 0.5;
	private static final double LARGE_CHANCE = 0.75;

	private static final double INTRANSITIVE_CHANCE = 0.4;
	private static final double TRANSITIVE_CHANCE = 0.1;
	private static final double ADJECTIVE_CHANCE = 0.4;

	private static final int PAUSE_MILLISECONDS = 2000;

	public static void main(String[] args) {

		while (true) {
			randomSentence();
			System.out.println(".\n\n");
			try {
				Thread.sleep(PAUSE_MILLISECONDS);
			} catch (InterruptedException e) {

			}
		}
	}

	/**
	 * This routine creates one random sentence following this rule:
	 * 
	 * <sentence> ::= <simple_sentence> [ <conjunction> <sentence> ]
	 */
	private static void randomSentence() {

		simpleSentence();

		if (chanceToBeTrue(SMALL_CHANCE)) {
			randomItem(conjunctions);
			System.out.print(" ");
			randomSentence();
		}
	}

	/**
	 * This routine creates a simple sentence following this rule:
	 * 
	 * <simple_sentence> ::= this is a <adjective> project <conjunction>
	 * <noun_phrase> <verb_phrase>
	 */
	private static void simpleSentence() {

		makeItInteresting();
		nounPhrase();
		verbPhrase();
	}

	/**
	 * This routine adds an interesting beginning to each simpleSentence.
	 */
	private static void makeItInteresting() {
		System.out.print("this is a");
		randomItem(adjectives);
		System.out.print(" project");
		randomItem(conjunctions);
	}

	/**
	 * This routine creates a noun phrase following this rule:
	 * 
	 * <noun_phrase> ::= <proper_noun> | <determiner> [ <adjective> ] <common_noun>
	 * [ who <verb_phrase> ]
	 */
	private static void nounPhrase() {

		if (chanceToBeTrue(MEDIUM_CHANCE)) {
			randomItem(properNouns);
		} else {
			randomItem(determiners);
			if (chanceToBeTrue(LARGE_CHANCE)) {
				randomItem(adjectives);
			}
			randomItem(commonNouns);
			if (chanceToBeTrue(SMALL_CHANCE)) {
				System.out.print(" who");
				verbPhrase();
			}
		}
	}

	/**
	 * This routine creates a verb phrase following this rule:
	 * 
	 * <verb_phrase> ::= <intransitive_verb> | <transitive_verb> <noun_phrase> | is
	 * <adjective> | believes that <simple_sentence>
	 */
	private static void verbPhrase() {

		// Instantiate a random double between 0.0 (inclusive)
		// and 0.1 (exclusive).
		double chance = rGen.nextDouble();

		if (chance < INTRANSITIVE_CHANCE) {
			randomItem(intransitiveVerbs);
		} else if (chance < (INTRANSITIVE_CHANCE + TRANSITIVE_CHANCE)) {
			randomItem(transitiveVerbs);
			nounPhrase();
		} else if (chance < (INTRANSITIVE_CHANCE + TRANSITIVE_CHANCE + ADJECTIVE_CHANCE)) {
			System.out.print(" is");
			randomItem(adjectives);
		} else {
			System.out.print(" believes that ");
			simpleSentence();
		}
	}

	/**
	 * This routine randomly chooses an item from an array of strings.
	 */
	private static void randomItem(String[] listOfStrings) {

		/*
		 * Get next integer from rGen that is between 0 (inclusive) and
		 * listOfStrings.length (exclusive) and print that randomly chosen element of
		 * the list of Strings.
		 */
		int choice = rGen.nextInt(listOfStrings.length);
		System.out.print(" " + listOfStrings[choice]);
	}

	/**
	 * This routine uses rGen to return true percentChance amount of the time.
	 * 
	 * @param percentChance double value between 0.0 (inclusive) and 1.0
	 *                      (exclusive).
	 * @return true if a randomly chosen double value is less than percentChance.
	 */
	private static boolean chanceToBeTrue(double percentChance) {

		double number = rGen.nextDouble();
		if (number < percentChance) {
			return true;
		} else {
			return false;
		}
	}
}
