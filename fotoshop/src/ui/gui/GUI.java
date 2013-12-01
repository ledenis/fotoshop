package ui.gui;

import javax.swing.JOptionPane;

import ui.UserInterface;
import filter.Filter;
import fotoshop.Editor;
import fotoshop.ProcessedImage;
import fotoshop.Sequence;

/** It is the Swing graphical user interface */
public class GUI extends UserInterface {
	MainWindow window;

	public GUI(Editor editor) {
		super(editor);

		window = new MainWindow(editor);
		window.setVisible(true);
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
	public void printLook(String imageName, Filter[] filters,
			String[] cacheNames) {
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
		JOptionPane.showMessageDialog(window, msg.getString("noimage"), null,
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void printCantUndo() {
		JOptionPane.showMessageDialog(window, msg.getString("cantundo"), null,
				JOptionPane.ERROR_MESSAGE);
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

	@Override
	public void updateImage(ProcessedImage currentImage) {
		window.updateImage(currentImage);
	}

	@Override
	public void updateSequence(String name, Sequence sequence) {
		window.updateSequence(name, sequence);
	}
}
