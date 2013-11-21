package fotoshop;

import java.util.HashMap;

/**
 * A cache of images being worked on.
 */
public class Cache {
	private HashMap<String, ProcessedImage> imagesMap;

	public Cache() {
		imagesMap = new HashMap<>();
	}

	/**
	 * Add a copy of the current image to the cache as name.
	 * 
	 * @param image
	 *            The image about to bu put to the cache
	 * @param name
	 *            The name of the image
	 */
	public void put(ProcessedImage image, String name) {
		imagesMap.put(name, image.copy(name));
	}

	/**
	 * @param name
	 *            The name of the image
	 * @return a copy of the image named name from the cache, or null of name
	 *         not found
	 */
	public ProcessedImage get(String name) {
		ProcessedImage imageFromCache = imagesMap.get(name);
		if (imageFromCache == null)
			return null;
		return imageFromCache.copy();
	}

	/**
	 * @return an array containing the names of all the images
	 */
	public String[] getImageNames() {
		return imagesMap.keySet().toArray(new String[imagesMap.size()]);
	}
}
