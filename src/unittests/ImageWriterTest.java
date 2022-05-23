/**
 * 
 */
package unittests;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import renderer.ImageWriter;
import primitives.Color;

/**
 * @author Shilat and Avigail
 *
 */
class ImageWriterTest {

	/**
	 * Test method for {@link renderer.ImageWriter#writeToImage()}.
	 */
	@Test
	void testWriteToImage() {
		
		ImageWriter image = new ImageWriter("firstImage",800,500);

		for (int i = 0; i<800; i++)
		{
			for (int j = 0; j<500;j++)
			{
				if(i%50 == 0 || j%50 == 0)
					image.writePixel(i, j, new Color(200,56,56));
				else
					image.writePixel(i, j, new Color(220,220,220));
			}
		}
		image.writeToImage();
	}

}
