package fotoshop.filter;

import java.awt.Color;

import fotoshop.ColorImage;
import fotoshop.ProcessedImage;

/** A filter that rotates an image 90 degrees */
public class RotFilter extends Filter {

	public RotFilter() {
		super("rot90");
	}

	@Override
	public void apply(ProcessedImage procImage) {
		// R90 = [0 -1, 1 0] rotates around origin
        // (x,y) -> (-y,x)
        // then translate -> (height-y, x)
        int height = procImage.getHeight();
        int width = procImage.getWidth();
        ColorImage rotImage = new ColorImage(height, width);
        for (int y=0; y<height; y++) { // in the rotated image
            for (int x=0; x<width; x++) {
                Color pix = procImage.getPixel(x,y);
                rotImage.setPixel(height-y-1,x, pix);
            }
        }

//        return rotImage;
        procImage.update(rotImage);
	}

	@Override
	public void undo(ProcessedImage image) {
		// applying 3 times = applying the reversed rotation
		apply(image);
		apply(image);
		apply(image);
	}

	@Override
	public boolean canBeUndone() {
		return true;
	}

}
