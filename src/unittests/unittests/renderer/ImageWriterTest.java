package unittests.renderer;

import org.junit.Test;
import renderer.ImageWriter;

import java.awt.*;

public class ImageWriterTest {

    @Test
    public void writeToImage() {
        String imageName = "try 1";
        int width = 2000;
        int height = 1000;
        int nx =200;
        int ny =100;
        ImageWriter imageWriter = new ImageWriter(imageName, width, height, nx, ny);
        for (int col = 0; col < ny; col++) {
            for (int row = 0; row < nx; row++) {
                if (col % 10 == 0 || row % 10 == 0) {
                    imageWriter.writePixel(row, col, Color.WHITE);
                }
            }
        }
        imageWriter.writeToImage();
    }
}