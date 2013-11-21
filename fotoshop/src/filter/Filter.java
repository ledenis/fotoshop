package filter;

import fotoshop.ProcessedImage;

/** Generic class that manage a image filter */
public abstract class Filter {
	private String name;

	public Filter(String name) {
		this.name = name;
	}

	/**
	 * Apply the filter
	 * 
	 * @param image
	 *            The image to apply the filter on
	 */
	public abstract void apply(ProcessedImage image);

	/**
	 * Apply the reversed filter
	 * 
	 * @param image
	 *            the image to apply the filter on
	 */
	public abstract void undo(ProcessedImage image);

	/**
	 * @return true if the filter can be undone
	 */
	public abstract boolean canBeUndone();

	@Override
	public String toString() {
		return name;
	}
}
