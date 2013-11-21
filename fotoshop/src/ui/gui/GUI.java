package ui.gui;

import filter.Filter;
import fotoshop.Editor;
import ui.UserInterface;

/** Not yet implemented */
public class GUI extends UserInterface {

	public GUI(Editor editor) {
		super(editor);
	}

	@Override
	public boolean process() {
		return false;
	}

	@Override
	public void printWelcome() {
	}

	@Override
	public void printBye() {
	}

	@Override
	public void printHelp() {
	}

	@Override
	public void printLoaded(String name) {
	}

	@Override
	public void printNotFound(String name, String cwd) {
	}

	@Override
	public void printSaved(String outputName) {
	}

	@Override
	public void printExceptionMsg(Exception e) {
	}

	@Override
	public void printLook(String imageName, Filter[] filters, String[] cacheNames) {
	}

	@Override
	public void printOpenWhat() {
	}

	@Override
	public void printSaveWhere() {
	}

	@Override
	public void printQuitWhat() {
	}

	@Override
	public void printWhichScript() {
	}

	@Override
	public void printPutWhat() {
	}

	@Override
	public void printGetWhat() {
	}

	@Override
	public void printNoImage() {
	}

	@Override
	public void printCantUndo() {
	}

	@Override
	public void printNoImageCache(String name) {
	}

	@Override
	public void printScriptNotFound(String scriptName) {
	}

	@Override
	public void printDontKnow() {
	}

}
