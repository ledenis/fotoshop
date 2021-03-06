package fotoshop.ui.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import fotoshop.Editor;
import fotoshop.Sequence;
import fotoshop.filter.Filter;
import fotoshop.filter.MonoFilter;
import fotoshop.filter.RotFilter;

/**
 * A window that enables the user to edit a sequence of filters
 */
public class SequenceWindow extends JDialog {
	private static final long serialVersionUID = 1L;

	private static final String TITLE = "Sequences", NAME = "Sequence name:",
			CHOOSE = "Choose filters to insert", CONTENT = "Sequence content";

	private Editor editor;

	private String oldName;
	private JTextField nameField;
	private JPanel filtersPanel;
	private JList<Filter> contentList;

	private DefaultListModel<Filter> contentModel;

	public SequenceWindow(Editor editor) {
		super((Frame) null, true); // modal
		this.editor = editor;
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
		nameField.setPreferredSize(new Dimension(fieldSize, nameField
				.getPreferredSize().height));

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

		// ActionListener
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText().equals("")) {
					JOptionPane.showMessageDialog(SequenceWindow.this,
							"Please enter a sequence name", null,
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				Filter[] filters = new Filter[contentModel.getSize()];
				contentModel.copyInto(filters);

				if (oldName == null)
					oldName = nameField.getText();
				editor.updateSequence(oldName, nameField.getText(), filters);
				dispose();
			}
		});
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
		filtersPanel = new JPanel();
		filtersPanel.setLayout(new BoxLayout(filtersPanel, BoxLayout.Y_AXIS));
		addFiltersButtons();

		leftPanel.add(new JLabel(CHOOSE));
		leftPanel.add(new JScrollPane(filtersPanel));

		// right
		contentModel = new DefaultListModel<Filter>();
		contentList = new JList<>(contentModel);
		contentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JButton deleteButton = new JButton("Delete");

		listPanel.add(new JLabel(CONTENT));
		listPanel.add(new JScrollPane(contentList));
		rightPanel.add(deleteButton);

		// Action Listener
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = contentList.getSelectedIndex();
				if (selectedIndex != -1) {
					contentModel.remove(selectedIndex);
				}
			}
		});
	}

	/**
	 * @return A little space for the form layout
	 */
	private Component createSpace() {
		return Box.createRigidArea(new Dimension(10, 10));
	}

	/**
	 * Add a button to the Filters panel for each filter available
	 */
	private void addFiltersButtons() {
		String[] filtersNames = Filter.getFiltersNames();

		for (String filter : filtersNames) {
			if (filter.equals("mono")) {
				addFilterButton("Monochrome", new MonoFilter());
			} else if (filter.equals("rot90")) {
				addFilterButton("Rotate by 90�", new RotFilter());
			} else if (filter.equals("bright")) {
				addFilterButton("Brightness...", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new BrightnessWindow(SequenceWindow.this)
								.setVisible(true);
					}
				});
			} else {
				System.out.println("Filter " + filter
						+ " not yet implemented in Sequence GUI");
			}
		}
	}

	/**
	 * Add a single button to the Filter panel
	 * 
	 * @param buttonText
	 *            The displayed text of the button
	 * @param filter
	 *            The filter corresponding to the button
	 */
	private void addFilterButton(String buttonText, final Filter filter) {
		JButton button = new JButton(buttonText);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertFilter(filter);
			}
		});
		filtersPanel.add(button);
	}

	/**
	 * Add a single button to the Filter panel
	 * 
	 * @param buttonText
	 *            The displayed text of the button
	 * @param action
	 *            The action attached to the button
	 */
	private void addFilterButton(String buttonText, ActionListener action) {
		JButton button = new JButton(buttonText);
		button.addActionListener(action);
		filtersPanel.add(button);
	}

	public void insertFilter(final Filter filter) {
		int selectedIndex = contentList.getSelectedIndex();
		if (selectedIndex == -1) {
			contentModel.addElement(filter);
		} else {
			contentModel.add(selectedIndex, filter);
		}
	}

	/**
	 * Fill the form with the sequence informations: name and filters
	 * 
	 * @param sequence
	 */
	public void fillForm(Sequence sequence) {
		oldName = sequence.getName();
		nameField.setText(oldName);

		Filter[] filters = sequence.getFilters();
		for (Filter f : filters) {
			contentModel.addElement(f);
		}
	}
}
