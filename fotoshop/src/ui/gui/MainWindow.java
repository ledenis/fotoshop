package ui.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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

import ui.gui.action.LoadAction;
import ui.gui.action.MonoAction;
import ui.gui.action.RotAction;
import ui.gui.action.SaveAction;
import filter.Filter;
import filter.Sequence;
import fotoshop.Editor;
import fotoshop.ProcessedImage;

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

	private static final String TITLE = "Fotoshop";
	private static final int PREF_WIDTH = 800, PREF_HEIGHT = 600;

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
		JMenuItem loadItem = new JMenuItem("Load");
		loadItem.setMnemonic(KeyEvent.VK_L);
		JMenuItem saveItem = new JMenuItem("Save");
		loadItem.setMnemonic(KeyEvent.VK_S);
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_X);

		fileMenu.add(loadItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
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
		loadButton.addActionListener(new LoadAction(this, editor));
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveAction(this, editor));
		nameLabel = new JLabel("<No image>");
		Font font = nameLabel.getFont();
		font = font.deriveFont(Font.BOLD);
		nameLabel.setFont(font);
		JLabel historyLabel = new JLabel("History:");
		JComboBox<Filter> historyCombo = new JComboBox<>();
		historyCombo.setMaximumSize(historyCombo.getPreferredSize());
		JButton revertButton = new JButton("Revert");

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
		// mainPanel.add(new JButton("teeeeest"));
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

		// init first line
		JComboBox<Sequence> sequencesCombo = new JComboBox<>();
		sequencesCombo.setMaximumSize(sequencesCombo.getPreferredSize());
		JButton applySeqButton = new JButton("Apply");
		sequencesFirstLine.add(sequencesCombo);
		sequencesFirstLine.add(applySeqButton);

		// init second line
		JButton editSeqButton = new JButton("Edit");
		JButton newSeqButton = new JButton("New");
		sequencesSecondLine.add(editSeqButton);
		sequencesSecondLine.add(newSeqButton);

		// Add them to Side container
		sidePanel.add(new JScrollPane(filtersPanel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)); // with scrolling
		sidePanel.add(sequencesPanel);
	}

	private void addFiltersButtons() {
		String[] filtersNames = Filter.getFiltersNames();

		for (String filter : filtersNames) {
			if (filter.equals("mono")) {
				addFilterButton("Monochrome", new MonoAction(this, editor));
			} else if (filter.equals("rot90")) {
				addFilterButton("Rotate by 90°", new RotAction(this, editor));
			} else {
				System.out.println("Filter " + filter
						+ " not yet implemented in GUI");
			}
		}
	}

	private void addFilterButton(String buttonText, ActionListener action) {
		JButton button = new JButton(buttonText);
		button.addActionListener(action);
		filtersPanel.add(button);
	}

	public void updateImage(ProcessedImage currentImage) {
		JLabel picLabel = new JLabel(new ImageIcon(currentImage.getInternal()));
		mainPanel.removeAll();
		mainPanel.add(picLabel);
		mainPanel.validate();
		mainPanel.repaint();

		// name
		nameLabel.setText(currentImage.getName());
	}

}
