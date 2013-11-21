package ui.commandline;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * This class is taken from the "World of Zuul" application. "World of Zuul" is
 * a very simple, text based adventure game.
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and tries
 * to interpret the line as a three word command. It returns the command as an
 * object of class Command.
 * 
 * The parser has a set of known command words. It checks user input against the
 * known commands, and if the input is not one of the known commands, it returns
 * a command object that is marked as an unknown command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Parser {
	private CommandWords commands; // holds all valid command words
	private Scanner reader; // source of command input

	private final static int MAX_WORDS = 3;

	/**
	 * Create a parser to read from the terminal window.
	 */
	public Parser() {
		commands = new CommandWords();
		reader = new Scanner(System.in);
	}

	public void setInputStream(FileInputStream str) {
		reader = new Scanner(str);
	}

	/**
	 * @return The next command from the user.
	 */
	public Command getCommand() {
		String inputLine; // will hold the full input line
		String[] words = new String[MAX_WORDS];

		System.out.print("> "); // print prompt

		inputLine = reader.nextLine();

		// Find up to MAX_WORDS words on the line.
		Scanner tokenizer = new Scanner(inputLine);
		for (int i = 0; i < MAX_WORDS && tokenizer.hasNext(); i++) {
			words[i] = tokenizer.next(); // get word
		}
		// note: we just ignore the rest of the input line.
		tokenizer.close();

		// Now check whether this word is known. If so, create a command
		// with it. If not, create a "null" command (for unknown command).
		if (commands.isCommand(words[0])) {
			return new Command(words);
		} else {
			return new Command(null);
		}
	}

	/**
	 * @return a string containing the valid commands
	 */
	public String getAllCommands() {
		return commands.toString();
	}
}
