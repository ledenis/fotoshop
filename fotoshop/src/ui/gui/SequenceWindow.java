package ui.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fotoshop.Editor;

/**
 * A window that enables the user to edit a sequence of filters
 */
public class SequenceWindow extends JDialog {
	private static final long serialVersionUID = 1L;

	private static final String TITLE = "Sequences", NAME = "Sequence name:",
			CHOOSE = "Choose filters to insert", CONTENT = "Sequence content";

	private JList<String> contentList;

	private JTextField nameField;

	public SequenceWindow(Editor editor) {
		super((Frame) null, true); // modal
		setTitle(TITLE);

		// Containers
		JPanel topPanel = new JPanel();
		JPanel centrePanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		initContainers(topPanel, centrePanel, bottomPanel);

		// Components
		initComponents(topPanel, centrePanel, bottomPanel);

		pack();
	}

	private void initContainers(JPanel topPanel, JPanel centrePanel,
			JPanel bottomPanel) {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		((JComponent) contentPane).setBorder(BorderFactory.createEmptyBorder(
				10, 10, 10, 10));

		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.X_AXIS));
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

		contentPane.add(topPanel);
		contentPane.add(createSpace());
		contentPane.add(centrePanel);
		contentPane.add(createSpace());
		contentPane.add(bottomPanel);
	}

	private void initComponents(JPanel topPanel, JPanel centrePanel,
			JPanel bottomPanel) {
		int fieldSize = 100;
		
		// Top
		nameField = new JTextField();
		nameField.setMaximumSize(new Dimension(fieldSize, nameField
				.getPreferredSize().height));
		nameField.setPreferredSize(new Dimension(fieldSize, nameField.getPreferredSize().height));
		
		topPanel.add(createSpace());
		topPanel.add(new JLabel(NAME));
		topPanel.add(createSpace());
		topPanel.add(nameField);
		topPanel.add(Box.createHorizontalGlue());

		// Centre
		initCentreComponents(centrePanel);

		// Bottom
		JButton okButton = new JButton("Ok");

		bottomPanel.add(Box.createHorizontalGlue());
		bottomPanel.add(okButton);
	}

	private void initCentreComponents(JPanel centrePanel) {
		// Containers
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));

		centrePanel.add(leftPanel);
		centrePanel.add(createSpace());
		centrePanel.add(Box.createHorizontalGlue());
		centrePanel.add(rightPanel);

		// listPanel
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

		rightPanel.add(listPanel);

		// Components
		// left
		leftPanel.add(new JLabel(CHOOSE));
		leftPanel.add(new JScrollPane(new JPanel()));

		// right
		contentList = new JList<>();
		JButton deleteButton = new JButton("Delete");

		listPanel.add(new JLabel(CONTENT));
		listPanel.add(new JScrollPane(contentList));
		rightPanel.add(deleteButton);
	}

	/**
	 * @return A little space for the form layout
	 */
	private Component createSpace() {
		return Box.createRigidArea(new Dimension(10, 10));
	}
}
