package filter;

import java.awt.Color;

import fotoshop.ColorImage;
import fotoshop.ProcessedImage;

/** A filter that converts an image to monochrome. It cannot be undone */
public class MonoFilter extends Filter {

	public MonoFilter() {
		super("mono");
	}

	@Override
	public void apply(ProcessedImage procImage) {
		ColorImage tmpImage = procImage.getInternal();
		// Graphics2D g2 = currentImage.createGraphics();
		int height = tmpImage.getHeight();
		int width = tmpImage.getWidth();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color pix = tmpImage.getPixel(x, y);
				int lum = (int) Math.round(0.299 * pix.getRed() + 0.587
						* pix.getGreen() + 0.114 * pix.getBlue());
				tmpImage.setPixel(x, y, new Color(lum, lum, lum));
			}
		}
		procImage.update(tmpImage);
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
