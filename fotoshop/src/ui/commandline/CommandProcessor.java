package ui.commandline;

import ui.UserInterface;
import fotoshop.Editor;

/**
 * This class evaluates commands and let the editor execute it.
 */
public class CommandProcessor {
	private Editor editor;

	private UserInterface ui;

	public CommandProcessor(Editor editor, UserInterface ui) {
		this.editor = editor;
		this.ui = ui;
	}

	/**
	 * Given a command, edit (that is: execute) the command.
	 * 
	 * @param command
	 *            The command to be processed.
	 * @return true If the command ends the editing session, false otherwise.
	 */
	public boolean processCommand(Command command) {
		boolean wantToQuit = false;

		if (command.isUnknown()) {
			ui.printDontKnow();
			return false;
		}

		String commandWord = command.getCommandWord();
		if (commandWord.equals("help")) {
			help(command);
		} else if (commandWord.equals("open")) {
			open(command);
		} else if (commandWord.equals("save")) {
			save(command);
		} else if (commandWord.equals("mono")) {
			mono(command);
		} else if (commandWord.equals("rot90")) {
			rot90(command);
		} else if (commandWord.equals("look")) {
			look(command);
		} else if (commandWord.equals("put")) {
			put(command);
		} else if (commandWord.equals("get")) {
			get(command);
		} else if (commandWord.equals("undo")) {
			undo(command);
		} else if (commandWord.equals("script")) {
			wantToQuit = script(command);
		} else if (commandWord.equals("quit")) {
			wantToQuit = quit(command);
		}
		return wantToQuit;
	}

	// ----------------------------------
	// Implementations of user commands:
	// ----------------------------------

	/**
	 * Print out some help information. Here we print some useless, cryptic
	 * message and a list of the command words.
	 * 
	 * @param command
	 *            the command given.
	 */
	private void help(Command command) {
		ui.printHelp();
	}

	/**
	 * "open" was entered. Open the file given as the second word of the command
	 * and use as the current image.
	 * 
	 * @param command
	 *            the command given.
	 */
	private void open(Command command) {
		if (!command.hasWordNumber(1)) {
			// if there is no second word, we don't know what to open...
			ui.printOpenWhat();
			return;
		}

		// Load the image
		String inputName = command.getSecondWord();
		editor.loadImage(inputName);
	}

	/**
	 * "save" was entered. Save the current image to the file given as the
	 * second word of the command.
	 * 
	 * @param command
	 *            the command given
	 */
	private void save(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to save...
			ui.printSaveWhere();
			return;
		}

		editor.saveImage(command.getSecondWord());
	}

	/**
	 * "look" was entered. Report the status of the work bench.
	 * 
	 * @param command
	 *            the command given.
	 */
	private void look(Command command) {
		ui.printLook(editor.getImageName(), editor.getFilters(),
				editor.getCacheNames());
	}

	/**
	 * "mono" was entered. Convert the current image to monochrome.
	 * 
	 * @param command
	 *            the command given.
	 */
	private void mono(Command command) {
		if (!editor.mono()) {
			ui.printNoImage();
		}
	}

	/**
	 * "rot90" was entered. Rotate the current image 90 degrees.
	 * 
	 * @param command
	 *            the command given.
	 */
	private void rot90(Command command) {
		if (!editor.rot90()) {
			ui.printNoImage();
		}
	}

	/**
	 * "put" was entered. Add a copy of the current image to the cache as the
	 * name given in the second word of the command.
	 * 
	 * @param command
	 *            the command given.
	 */
	private void put(Command command) {
		if (!command.hasSecondWord()) {
			ui.printPutWhat();
			return;
		}
		editor.putImage(command.getSecondWord());
	}

	/**
	 * "get" was entered. Replace the current image with a copy of the image
	 * from the cache, named after the second word of the command.
	 * 
	 * @param command
	 *            the command given.
	 */
	private void get(Command command) {
		if (!command.hasSecondWord()) {
			ui.printGetWhat();
			return;
		}
		editor.get(command.getSecondWord());
	}

	/**
	 * "undo" was entered. Remove the last filter (where applicable) from the
	 * current image
	 * 
	 * @param command
	 *            the command given.
	 * @return true if succeeded
	 */
	private void undo(Command command) {
		// if failed
		if (!editor.undo()) {
			// informs the user it cannot be undone
			ui.printCantUndo();
		}
	}

	/**
	 * "Quit" was entered. Check the rest of the command to see whether we
	 * really quit the editor.
	 * 
	 * @param command
	 *            the command given.
	 * @return true, if this command quits the editor, false otherwise.
	 */
	private boolean quit(Command command) {
		if (command.hasSecondWord()) {
			ui.printQuitWhat();
			return false;
		} else {
			return true; // signal that we want to quit
		}
	}

	/**
	 * The 'script' command runs a sequence of commands from a text file.
	 * 
	 * IT IS IMPORTANT THAT THIS COMMAND WORKS AS IT CAN BE USED FOR TESTING
	 * 
	 * @param command
	 *            the script command, second word of which is the name of the
	 *            script file.
	 * @return whether to quit.
	 */
	private boolean script(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know what to open...
			ui.printWhichScript();
			return false;
		}

		return editor.runScript(command.getSecondWord());
	}
}
