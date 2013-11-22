package ui.gui.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;

import fotoshop.Editor;

/** Load an image */
public class LoadAction extends GUIAction {
	private static final long serialVersionUID = 1L;

	public LoadAction(Component parent, Editor editor) {
		super(parent, editor);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("debug load editor");
		JFileChooser fileChooser = new JFileChooser();

		int result = fileChooser.showOpenDialog(parent);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			editor.loadImage(file);

		}
	}
}
