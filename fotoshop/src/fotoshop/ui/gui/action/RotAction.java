package fotoshop.ui.gui.action;

import java.awt.event.ActionEvent;

import fotoshop.Editor;


public class RotAction extends GUIAction {
	private static final long serialVersionUID = 1L;
	
	public RotAction(Editor editor) {
		super(editor);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		editor.rot90();
	}

	@Override
	protected String getName() {
		return "Rotate by 90°";
	}

}
