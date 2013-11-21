package ui.commandline;

import ui.UserInterface;
import filter.Filter;
import fotoshop.Editor;

/**
 * This class allows the user to type command lines. It creates the parser and
 * gives the commands returned by the parser to the CommandProcessor.
 */
public class CommandLineInterface extends UserInterface {

	private Parser parser;
	private CommandProcessor processor;

	public CommandLineInterface(Editor editor) {
		super(editor);
		parser = new Parser();
		processor = new CommandProcessor(editor, this);
	}

	/**
	 * Read commands and execute them
	 * 
	 * @return true if the user wants to quit
	 */
	@Override
	public boolean process() {
		Command command = parser.getCommand();
		return processor.processCommand(command);
	}

	// ----------------------------------
	// Print methods:
	// ----------------------------------

	/**
	 * Print out the opening message for the user.
	 */
	@Override
	public void printWelcome() {
		System.out.println();
		System.out.println(msg.getString("welcome"));
		System.out.println();
	}

	/**
	 * Print out the bye message for the user.
	 */
	@Override
	public void printBye() {
		System.out.println(msg.getString("bye"));
	}

	/**
	 * Print out some help information. Here we print some useless, cryptic
	 * message and a list of the command words.
	 */
	@Override
	public void printHelp() {
		System.out.println(msg.getString("help"));
		System.out.println("   " + parser.getAllCommands());
	}

	/**
	 * Inform the user that a file is loaded
	 * 
	 * @param name
	 *            the name of the file
	 */
	@Override
	public void printLoaded(String name) {
		System.out.println(msg.getString("loaded") + name);
	}

	/**
	 * Inform the user that a file is not found
	 * 
	 * @param name
	 *            the name of the file
	 * @param cwd
	 *            the current working directory
	 */
	@Override
	public void printNotFound(String name, String cwd) {
		System.out.println(msg.getString("notfound") + name);
		System.out.println(msg.getString("cwd") + cwd);
	}

	/**
	 * Inform the user that the image file is saved
	 * 
	 * @param outputName
	 *            The name of the file
	 */
	@Override
	public void printSaved(String outputName) {
		System.out.println(msg.getString("saved") + outputName);
	}

	/**
	 * Print out an Exception message
	 * 
	 * @param e
	 *            the Exception
	 */
	@Override
	public void printExceptionMsg(Exception e) {
		System.out.println(e.getMessage());
	}

	@Override
	public void printOpenWhat() {
		System.out.println(msg.getString("openwhat"));
	}

	@Override
	public void printSaveWhere() {
		System.out.println(msg.getString("savewhere"));
	}

	/**
	 * Print out the status of the work bench.
	 * 
	 * @param imageStatus
	 */
	@Override
	public void printLook(String imageName, Filter[] filters, String[] cacheNames) {
		System.out.print(msg.getString("currentimage"));
		System.out.println(imageName);
		System.out.print(msg.getString("filters"));
		for (Filter filter : filters) {
			System.out.print(filter + " ");
		}
		System.out.println();
		System.out.print(msg.getString("cache"));
		for (String cacheName : cacheNames) {
			System.out.print(cacheName + " ");
		}
		System.out.println();
	}

	@Override
	public void printQuitWhat() {
		System.out.println(msg.getString("quitwhat"));
	}

	@Override
	public void printWhichScript() {
		System.out.println(msg.getString("whichscript"));
	}

	@Override
	public void printPutWhat() {
		System.out.println(msg.getString("putwhat"));
	}
	
	@Override
	public void printGetWhat() {
		System.out.println(msg.getString("getwhat"));
	}

	@Override
	public void printNoImage() {
		System.out.println(msg.getString("noimage"));
	}

	@Override
	public void printCantUndo() {
		System.out.println(msg.getString("cantundo"));
	}

	@Override
	public void printNoImageCache(String name) {
		System.out.println(msg.getString("noimagecache") + name);
	}

	@Override
	public void printScriptNotFound(String scriptName) {
		System.out.println(msg.getString("scriptnotfound") + scriptName);
	}

	@Override
	public void printDontKnow() {
		System.out.println(msg.getString("dontknow"));
	}
}
