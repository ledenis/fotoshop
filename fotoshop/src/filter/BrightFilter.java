package filter;

import java.awt.image.RescaleOp;

import fotoshop.ColorImage;
import fotoshop.ProcessedImage;

/**
 * A filter that changes the brightness of an image. A value > 1.0 makes the
 * image brighter and vice-versa.
 */
public class BrightFilter extends Filter {
	private float brightValue;

	public BrightFilter(float brightValue) {
		super("bright");
		this.brightValue = brightValue;
	}

	@Override
	public void apply(ProcessedImage image) {
		ColorImage img = image.getInternal();

		RescaleOp op = new RescaleOp(brightValue, 0, null);
		image.update((ColorImage) op.filter(img, img));
	}

	@Override
	public void undo(ProcessedImage image) {
		throw new UnsupportedOperationException("cantundo");
	}

	@Override
	public boolean canBeUndone() {
		return false;
	}

}
