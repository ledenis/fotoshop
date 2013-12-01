package ui.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import ui.gui.action.ApplySequenceAction;
import ui.gui.action.EditSequenceAction;
import ui.gui.action.LoadAction;
import ui.gui.action.MonoAction;
import ui.gui.action.RotAction;
import ui.gui.action.SaveAction;
import ui.gui.action.ShowBrightAction;
import filter.Filter;
import fotoshop.Editor;
import fotoshop.ProcessedImage;
import fotoshop.Sequence;

/**
 * Main window of the Fotoshop application. It has 3 parts: top, side and main.
 * The "top" panel contains some components, including the name. The "side"
 * panel contains 2 parts: filters and sequences. The "main" panel displays the
 * current image.
 */
public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private Editor editor;

	private JPanel mainPanel;
	private JPanel filtersPanel;

	private JLabel nameLabel;
	private DefaultComboBoxModel<Filter> historyModel;
	private JComboBox<Filter> historyCombo;

	private static final String TITLE = "Fotoshop";
	private static final int PREF_WIDTH = 800, PREF_HEIGHT = 600;

	private JComboBox<Sequence> sequencesCombo;

	/** The main window of the Fotoshop application in GUI mode */
	public MainWindow(Editor editor) {
		this.editor = editor;

		// Use the same theme as the system
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
		setTitle(TITLE);

		initMenuBar();

		JPanel topPanel = new JPanel();
		JPanel sidePanel = new JPanel();
		mainPanel = new JPanel();

		initContainers(topPanel, sidePanel, mainPanel);
		initComponents(topPanel, sidePanel, mainPanel);

		pack();
	}

	private void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// Add menu bar
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic('E');

		menuBar.add(fileMenu);
		menuBar.add(editMenu);

		// Add menu items
		JMenuItem loadItem = new JMenuItem(new LoadAction(editor));
		JMenuItem saveItem = new JMenuItem(new SaveAction(editor));
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		exitItem.setMnemonic(KeyEvent.VK_X);

		fileMenu.add(loadItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);

		// editMenu
		String[] filtersNames = Filter.getFiltersNames();

		for (String filter : filtersNames) {
			if (filter.equals("mono")) {
				editMenu.add(new JMenuItem(new MonoAction(editor)));
			} else if (filter.equals("rot90")) {
				editMenu.add(new JMenuItem(new RotAction(editor)));
			} else if (filter.equals("bright")) {
				editMenu.add(new JMenuItem(new ShowBrightAction(editor, this)));
			} else {
				System.out.println("Filter " + filter
						+ " not yet implemented in menu");
			}
		}
	}

	private void initContainers(JPanel topPanel, JPanel sidePanel,
			JPanel mainPanel) {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		// Add Top container
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		contentPane.add(topPanel, BorderLayout.NORTH);

		// Side container
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

		// Main container
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		// Add SplitPane with side and main (+scrolling) container
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				sidePanel, new JScrollPane(mainPanel));
		contentPane.add(splitPane, BorderLayout.CENTER);
	}

	private void initComponents(JPanel topPanel, JPanel sidePanel,
			JPanel mainPanel) {
		// Top container
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new LoadAction(editor));
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveAction(editor));
		nameLabel = new JLabel("<No image>");
		nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));
		JLabel historyLabel = new JLabel("History:");
		historyCombo = new JComboBox<>();
		historyModel = (DefaultComboBoxModel<Filter>) historyCombo.getModel();
		historyCombo.setMaximumSize(historyCombo.getPreferredSize());
		JButton revertButton = new JButton("Revert");
		revertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editor.revert(historyCombo.getItemCount()
						- historyCombo.getSelectedIndex() - 1);
			}
		});

		topPanel.add(loadButton);
		topPanel.add(saveButton);
		topPanel.add(Box.createHorizontalGlue());
		topPanel.add(nameLabel);
		topPanel.add(Box.createHorizontalGlue());
		topPanel.add(historyLabel);
		topPanel.add(historyCombo);
		topPanel.add(revertButton);

		// Side container
		initSideComponents(sidePanel);

		// Main container
		// ...
	}

	private void initSideComponents(JPanel sidePanel) {
		filtersPanel = new JPanel();
		filtersPanel.setLayout(new BoxLayout(filtersPanel, BoxLayout.Y_AXIS));
		filtersPanel.setBorder(BorderFactory.createTitledBorder("Filters"));
		addFiltersButtons();

		// Sequences panel
		JPanel sequencesPanel = new JPanel();
		sequencesPanel
				.setLayout(new BoxLayout(sequencesPanel, BoxLayout.Y_AXIS));
		sequencesPanel.setBorder(BorderFactory.createTitledBorder("Sequences"));

		// create 2 lines
		JPanel sequencesFirstLine = new JPanel();
		JPanel sequencesSecondLine = new JPanel();
		sequencesFirstLine.setLayout(new BoxLayout(sequencesFirstLine,
				BoxLayout.X_AXIS));
		sequencesSecondLine.setLayout(new BoxLayout(sequencesSecondLine,
				BoxLayout.X_AXIS));
		sequencesPanel.add(sequencesFirstLine);
		sequencesPanel.add(sequencesSecondLine);

		sequencesCombo = new JComboBox<>();
		Dimension seqSize = sequencesCombo.getPreferredSize();
		seqSize.width = Integer.MAX_VALUE;
		sequencesCombo.setMaximumSize(seqSize);
		sequencesCombo.getMaximumSize().height = sequencesCombo
				.getPreferredSize().height;
		JButton applySeqButton = new JButton("Apply");
		applySeqButton.addActionListener(new ApplySequenceAction(editor,
				sequencesCombo));
		sequencesFirstLine.add(sequencesCombo);
		sequencesFirstLine.add(applySeqButton);

		// init second line
		JButton editSeqButton = new JButton("Edit");
		JButton newSeqButton = new JButton("New");
		editSeqButton.addActionListener(new EditSequenceAction(editor,
				sequencesCombo));
		newSeqButton.addActionListener(new EditSequenceAction(editor));
		sequencesSecondLine.add(editSeqButton);
		sequencesSecondLine.add(newSeqButton);

		// Add them to Side container
		sidePanel.add(new JScrollPane(filtersPanel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)); // with scrolling
		sidePanel.add(new JScrollPane(sequencesPanel,
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	}

	/**
	 * Add a button to the Filters panel for each filter available
	 */
	private void addFiltersButtons() {
		String[] filtersNames = Filter.getFiltersNames();

		for (String filter : filtersNames) {
			if (filter.equals("mono")) {
				filtersPanel.add(new JButton(new MonoAction(editor)));
			} else if (filter.equals("rot90")) {
				filtersPanel.add(new JButton(new RotAction(editor)));
			} else if (filter.equals("bright")) {
				filtersPanel.add(new JButton(new ShowBrightAction(editor, this)));
			} else {
				System.out.println("Filter " + filter
						+ " not yet implemented in GUI");
			}
		}
	}

	public void updateImage(ProcessedImage currentImage) {
		JLabel picLabel = new JLabel(new ImageIcon(currentImage.getInternal()));
		mainPanel.removeAll();
		mainPanel.add(picLabel);
		mainPanel.validate();
		mainPanel.repaint();

		// Name
		nameLabel.setText(currentImage.getName());

		// History
		// update model
		historyModel = new DefaultComboBoxModel<>(currentImage.getFilters());
		historyCombo.setModel(historyModel);

		// selected value
		historyCombo.setSelectedIndex(historyModel.getSize() - 1);
	}

	/**
	 * Update the sequence combobox
	 * 
	 * @param oldName
	 *            The old name of the updated sequence
	 * @param sequence
	 *            The updated sequence
	 */
	public void updateSequence(String oldName, Sequence sequence) {
		if (oldName == null)
			oldName = sequence.getName();

		// Find it, and replace it
		for (int i = 0; i < sequencesCombo.getItemCount(); i++) {
			if (sequencesCombo.getItemAt(i).getName().equals(oldName)) {
				sequencesCombo.removeItemAt(i);
				sequencesCombo.insertItemAt(sequence, i);
				return;
			}
		}

		// If not found, add it
		sequencesCombo.addItem(sequence);
	}
}
