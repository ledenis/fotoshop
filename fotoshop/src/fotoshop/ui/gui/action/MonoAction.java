package fotoshop.ui.gui.action;

import java.awt.event.ActionEvent;

import fotoshop.Editor;

public class MonoAction extends GUIAction {
	private static final long serialVersionUID = 1L;

	public MonoAction(Editor editor) {
		super(editor);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		editor.mono();
	}

	@Override
	protected String getName() {
		return "Monochrome";
	}
	

}
