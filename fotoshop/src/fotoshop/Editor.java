package fotoshop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import ui.UserInterface;
import ui.commandline.Command;
import ui.commandline.CommandProcessor;
import ui.commandline.Parser;
import ui.gui.GUI;
import filter.Filter;
import filter.MonoFilter;
import filter.RotFilter;

/**
 * This class is the main processing class of the Fotoshop application. Fotoshop
 * is a very simple image editing tool. Users can apply a number of filters to
 * an image. That's all. It should really be extended to make it more useful!
 * 
 * To edit an image, create an instance of this class and call the "edit"
 * method.
 * 
 * This main class creates and initialises all the others: it creates the UI and
 * interacts with it.
 * 
 * @author Richard Jones
 * @version 2013.09.10
 */

public class Editor {
	private UserInterface ui;
	private ProcessedImage currentImage;
	private Cache cache;

	/**
	 * Create the editor and initialise its UI.
	 */
	public Editor() {
//		ui = new CommandLineInterface(this);
		ui = new GUI(this);
		cache = new Cache();

		// runScript("script.txt");
	}

	/**
	 * Main edit routine. Loops until the end of the editing session.
	 */
	public void edit() {
		ui.printWelcome();

		// Shows the workbench status
		ui.printLook(getImageName(), getFilters(), getCacheNames());

		// Enter the main command loop. Here we repeatedly prompt the ui
		// until the editing session is over.
		boolean finished = false;
		while (!finished) {
			finished = ui.process();
		}
		ui.printBye();
	}

	/**
	 * Apply a filter to the current image
	 * 
	 * @param filter
	 *            the filter about to be applied
	 * @return false if no current image
	 */
	private boolean applyFilter(Filter filter) {
		if (currentImage == null)
			return false;
		currentImage.addFilter(filter);
		filter.apply(currentImage);
		return true;
	}

	/**
	 * @return the name of the current image, null if there is no current image
	 */
	public String getImageName() {
		if (currentImage != null)
			return currentImage.getName();
		return null;
	}

	/**
	 * @return an array containing the filters applied to the current image, an
	 *         empty array if there is no current image.
	 */
	public Filter[] getFilters() {
		if (currentImage != null)
			return currentImage.getFilters();
		return new Filter[] {};
	}

	// ----------------------------------
	// Implementations of user commands:
	// ----------------------------------

	/**
	 * Load an image from a file and handle a possible error.
	 * 
	 * @param name
	 *            The name of the image file
	 */
	public void loadImage(String name) {
		ColorImage img = null;
		try {
			img = new ColorImage(ImageIO.read(new File(name)));
		} catch (IOException e) {
			ui.printNotFound(name, System.getProperty("user.dir"));
		}

		if (img == null) {
			ui.printHelp();
		} else {
			currentImage = new ProcessedImage(img, name);
			ui.printLoaded(currentImage.getName());
		}
	}

	/**
	 * Load an image from a file and handle a possible error.
	 * 
	 * @param imageFile
	 *            The image File
	 */
	public void loadImage(File imageFile) {
		ColorImage img = null;
		try {
			img = new ColorImage(ImageIO.read(imageFile));
		} catch (IOException e) {
			ui.printNotFound(imageFile.getAbsolutePath(),
					System.getProperty("user.dir"));
		}

		//TODO handle errors in loadImage
//		if (img == null) {
//			ui.printHelp();
//		} else {
			currentImage = new ProcessedImage(img, imageFile.getName());
			ui.printLoaded(currentImage.getName());
			ui.updateImage(currentImage);
//		}
	}

	/**
	 * Save an image to a file and handle a possible error
	 * 
	 * @param outputName
	 *            The name of the new image file
	 */
	public void saveImage(String outputName) {
		if (currentImage == null) {
			ui.printNoImage();
			return;
		}

		try {
			File outputFile = new File(outputName);
			ImageIO.write(currentImage.getInternal(), "jpg", outputFile);
			ui.printSaved(outputName);
		} catch (IOException e) {
			ui.printExceptionMsg(e);
			ui.printHelp();
		}
	}
	
	/**
	 * Save an image to a file and handle a possible error
	 * 
	 * @param outputFile
	 *            The new image file
	 */
	public void saveImage(File outputFile) {
		//TODO: handle errors in saveImage
		if (currentImage == null) {
			ui.printNoImage();
			return;
		}

		try {
			ImageIO.write(currentImage.getInternal(), "jpg", outputFile);
//			ui.printSaved(output);
		} catch (IOException e) {
			e.printStackTrace();
//			ui.printExceptionMsg(e);
//			ui.printHelp();
		}
	}

	/**
	 * Convert the current image to monochrome.
	 * 
	 * @return false if no current image
	 */
	public boolean mono() {
		return applyFilter(new MonoFilter());
	}

	/**
	 * Rotate the current image 90 degrees.
	 * 
	 * @return false if no current image
	 */
	public boolean rot90() {
		return applyFilter(new RotFilter());
	}

	/**
	 * The 'script' command runs a sequence of commands from a text file.
	 * 
	 * IT IS IMPORTANT THAT THIS COMMAND WORKS AS IT CAN BE USED FOR TESTING
	 * 
	 * @param command
	 *            the name of the script file.
	 * @return whether to quit.
	 */
	public boolean runScript(String scriptName) {
		Parser scriptParser = new Parser();
		try (FileInputStream inputStream = new FileInputStream(scriptName)) {
			scriptParser.setInputStream(inputStream);
			boolean finished = false;
			while (!finished) {
				try {
					Command cmd = scriptParser.getCommand();
					// process the command
					CommandProcessor processor = new CommandProcessor(this, ui);
					finished = processor.processCommand(cmd);
				} catch (Exception ex) {
					return finished;
				}
			}
			return finished;
		} catch (FileNotFoundException ex) {
			ui.printScriptNotFound(scriptName);
			return false;
		} catch (IOException ex) {
			throw new RuntimeException("Panic: script barfed!");
		}
	}

	/**
	 * Add a copy of the current image to the cache as name.
	 * 
	 * @param name
	 *            a name of the image being store
	 */
	public void putImage(String name) {
		if (currentImage == null) {
			ui.printNoImage();
			return;
		}
		cache.put(currentImage, name);
	}

	/**
	 * Replace the current image with a copy of the image named name from the
	 * cache.
	 * 
	 * @param name
	 *            the name of the image to retrieve
	 */
	public void get(String name) {
		ProcessedImage imageFromCache = cache.get(name);
		if (imageFromCache == null) {
			ui.printNoImageCache(name);
			return;
		}
		currentImage = imageFromCache;
	}

	/**
	 * @return an array containing the names of all the images of the cache
	 */
	public String[] getCacheNames() {
		return cache.getImageNames();
	}

	/**
	 * Remove the last filter (where applicable) from the current image
	 * 
	 * @return true if succeeded
	 */
	public boolean undo() {
		if (currentImage == null || !currentImage.undo()) {
			return false;
		}
		return true;
	}
}
