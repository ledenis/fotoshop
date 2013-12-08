package fotoshop;

import java.util.Arrays;
import java.util.List;

import fotoshop.filter.Filter;

/**
 * It is a sequence of filters. Applying a sequence to an image means applying
 * all the filters of this sequence in order.
 */
public class Sequence {
	String name;
	List<Filter> filters;

	public Sequence(String name, Filter[] filters) {
		this.name = name;
		this.filters = Arrays.asList(filters);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the filters as the filters to the sequence
	 * 
	 * @param filters
	 *            The new filters to be set
	 */
	public void setFilters(Filter[] filters) {
		this.filters = Arrays.asList(filters);
	}

	/**
	 * @return An array containing the filters in order
	 */
	public Filter[] getFilters() {
		return filters.toArray(new Filter[filters.size()]);
	}

	/**
	 * Apply all the filters to the image
	 * 
	 * @param image
	 *            The image to apply the filters on
	 */
	public void apply(ProcessedImage image) {
		for (Filter f : filters) {
			image.addFilter(f);
			f.apply(image);
		}
	}

	@Override
	public String toString() {
		return name;
	}
}
