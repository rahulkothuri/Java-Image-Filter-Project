import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class mainImg {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java ImageProcessor [filter] [infile] [outfile]");
            System.exit(1);
        }

        String filter = args[0];
        String infile = args[1];
        String outfile = args[2];

        try {
            BufferedImage image = ImageIO.read(new File(infile));
            rgbcolours[][] imageArray = convertToRGBTripleArray(image);

            switch (filter) {
                case "grayscale":
                    filters.grayscale(imageArray);
                    break;
                case "sepia":
                    filters.sepia(imageArray);
                    break;
                case "reflect":
                    filters.reflect(imageArray);
                    break;
                case "blur":
                    filters.blur(imageArray);
                    break;
                case "invertColors":
                    filters.invertColors(imageArray);
                    break;
                case "edgeDetect":
                    filters.edgeDetect(imageArray);
                    break;
                case "pixelate":
                    filters.pixelate(imageArray);
                    break;  
                case "posterize":
                    filters.posterize(imageArray);
                    break;
                case "noise":
                    filters.noise(imageArray);
                    break;
                default:
                    System.out.println("Unsupported filter.");
                    System.exit(2);
            }

            BufferedImage filteredImage = convertToBufferedImage(imageArray, image.getWidth(), image.getHeight());
            ImageIO.write(filteredImage, "bmp", new File(outfile));
            System.out.println("Filter applied.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static rgbcolours[][] convertToRGBTripleArray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        rgbcolours[][] imageArray = new rgbcolours[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = image.getRGB(j, i);
                imageArray[i][j] = new rgbcolours((byte) ((rgb >> 16) & 0xFF), (byte) ((rgb >> 8) & 0xFF), (byte) (rgb & 0xFF));
            }
        }

        return imageArray;
    }

    public static BufferedImage convertToBufferedImage(rgbcolours[][] imageArray, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rgbcolours pixel = imageArray[i][j];
                int rgb = ((pixel.rgbtRed & 0xFF) << 16) | ((pixel.rgbtGreen & 0xFF) << 8) | (pixel.rgbtBlue & 0xFF);
                image.setRGB(j, i, rgb);
            }
        }

        return image;
    }
}
