package ui;

import java.util.Locale;
import java.util.ResourceBundle;

import filter.Filter;
import fotoshop.Editor;
import fotoshop.ProcessedImage;
import fotoshop.Sequence;

/**
 * An user interface manager that abstracts the implementation of the user
 * interaction. It is an abstract class for GUI and command line interface
 */
public abstract class UserInterface {
	protected Editor editor;
	protected ResourceBundle msg;

	public UserInterface(Editor editor) {
		this.editor = editor;
		loadLocalisedMessages();
	}

	/**
	 * Load the localised messages
	 */
	private void loadLocalisedMessages() {
		String language = "en";
		String country = "GB";

		msg = ResourceBundle.getBundle("messages", new Locale(language,
				country));
	}

	public abstract boolean process();

	public abstract void printWelcome();

	public abstract void printBye();

	public abstract void printHelp();

	public abstract void printLoaded(String name);

	public abstract void printNotFound(String name, String cwd);

	public abstract void printSaved(String outputName);

	public abstract void printExceptionMsg(Exception e);
	
	public abstract void printOpenWhat();
	
	public abstract void printSaveWhere();
	
	public abstract void printLook(String imageName, Filter[] filters, String[] cacheNames);
	
	public abstract void printWhichScript();
	
	public abstract void printPutWhat();
	
	public abstract void printGetWhat();

	public abstract void printQuitWhat();

	public abstract void printNoImage();

	public abstract void printCantUndo();

	public abstract void printNoImageCache(String name);

	public abstract void printScriptNotFound(String scriptName);

	public abstract void printDontKnow();

	public void updateImage(ProcessedImage currentImage) {
	}

	public void updateSequence(String name, Sequence sequence) {
	}
}
