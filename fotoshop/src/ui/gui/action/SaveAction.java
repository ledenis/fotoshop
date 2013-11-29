package ui.gui.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;

import fotoshop.Editor;

/** Save an image to a file using a save file dialog */
public class SaveAction extends GUIAction {
	private static final long serialVersionUID = 1L;

	public SaveAction(Editor editor) {
		super(editor);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		JFileChooser fileChooser = new JFileChooser();

		int result = fileChooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			editor.saveImage(file);
		}
	}

	@Override
	protected String getName() {
		return "Save";
	}

	@Override
	protected int getMnemonic() {
		return KeyEvent.VK_L;
	}
}
