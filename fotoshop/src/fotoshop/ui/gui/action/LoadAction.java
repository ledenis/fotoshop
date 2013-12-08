package fotoshop.ui.gui.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;

import fotoshop.Editor;

/** Load an image using a file chooser dialog */
public class LoadAction extends GUIAction {
	private static final long serialVersionUID = 1L;

	public LoadAction(Editor editor) {
		super(editor);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		JFileChooser fileChooser = new JFileChooser();

		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			editor.loadImage(file);
		}
	}
	
	@Override
	protected String getName() {
		return "Load";
	}

	@Override
	protected int getMnemonic() {
		return KeyEvent.VK_L;
	}
}
