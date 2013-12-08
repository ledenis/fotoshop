package fotoshop;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import fotoshop.filter.Filter;

/**
 * Class that encapsulates ColorImage. It extends it by adding a name and the
 * list of filters applied to it
 */
public class ProcessedImage {
	private ColorImage image;

	private String name;
	private List<Filter> filters;

	public ProcessedImage(ColorImage image, String name) {
		this.image = image;
		this.name = name;

		filters = new ArrayList<>();
	}

	/**
	 * @return the width of the image.
	 */
	public int getWidth() {
		return image.getWidth();
	}

	/**
	 * @return the height of the image.
	 */
	public int getHeight() {
		return image.getHeight();
	}

	/**
	 * Get the color value at a specified pixel position
	 * 
	 * @param x
	 *            The x position of the pixel
	 * @param y
	 *            The y position of the pixel
	 * @return The color of the pixel at the given position
	 */
	public Color getPixel(int x, int y) {
		return image.getPixel(x, y);
	}

	/**
	 * Update the internal image with a new ColorImage
	 * 
	 * @param image
	 *            The new ColorImage
	 */
	public void update(ColorImage image) {
		this.image = image;
	}

	/**
	 * @return a copy of the internal ColorImage
	 */
	public ColorImage getInternal() {
		return new ColorImage(image);
	}

	public void addFilter(Filter filter) {
		filters.add(filter);
	}

	/**
	 * @return the name of the image
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return an array containing the filters applied to the image.
	 */
	public Filter[] getFilters() {
		return filters.toArray(new Filter[filters.size()]);
	}

	/**
	 * @param name
	 *            the new name
	 * @return return a copy of the image with the new given name
	 */
	public ProcessedImage copy(String name) {
		ProcessedImage copy = new ProcessedImage(getInternal(), name);
		for (Filter filter : filters) {
			copy.addFilter(filter);
		}

		return copy;
	}

	/**
	 * @return return a copy of the image
	 */
	public ProcessedImage copy() {
		return copy(name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(name + " ");
		for (Filter filter : filters) {
			sb.append(filter + " ");
		}

		return sb.toString();
	}

	/**
	 * Try to undo the last filter. It also removes it if succeeded.
	 * @return true if succeeded
	 */
	public boolean undo() {
		// if nothing to undo
		if (filters.size() == 0) {
			return false;
		}
		
		// if the last filter cannot be undone
		Filter lastFilter = filters.get(filters.size() - 1);
		if (!lastFilter.canBeUndone()) {
			return false;
		}
		
		// undo and remove it
		lastFilter.undo(this);
		filters.remove(filters.size() - 1);
		return true;
	}
}
