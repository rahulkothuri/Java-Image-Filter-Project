public class filters {

    public static void grayscale(rgbcolours[][] image) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                int avg = (image[i][j].rgbtRed + image[i][j].rgbtBlue + image[i][j].rgbtGreen) / 3;
                image[i][j].rgbtRed = (byte) avg;
                image[i][j].rgbtBlue = (byte) avg;
                image[i][j].rgbtGreen = (byte) avg;
            }
        }
    }

    public static void sepia(rgbcolours[][] image) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                int s_red = (int) (0.393 * (image[i][j].rgbtRed & 0xFF) + 0.769 * (image[i][j].rgbtGreen & 0xFF) + 0.189 * (image[i][j].rgbtBlue & 0xFF));
                int s_green = (int) (0.349 * (image[i][j].rgbtRed & 0xFF) + 0.686 * (image[i][j].rgbtGreen & 0xFF) + 0.168 * (image[i][j].rgbtBlue & 0xFF));
                int s_blue = (int) (0.272 * (image[i][j].rgbtRed & 0xFF) + 0.534 * (image[i][j].rgbtGreen & 0xFF) + 0.131 * (image[i][j].rgbtBlue & 0xFF));

                image[i][j].rgbtRed = (byte) Math.min(s_red, 255);
                image[i][j].rgbtGreen = (byte) Math.min(s_green, 255);
                image[i][j].rgbtBlue = (byte) Math.min(s_blue, 255);
            }
        }
    }

    public static void reflect(rgbcolours[][] image) {
        int height = image.length;
        int width = image[0].length;
        rgbcolours[][] temp = new rgbcolours[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                temp[i][j] = image[i][j];
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                image[i][j] = temp[i][width - 1 - j];
            }
        }
    }
    public static void invertColors(rgbcolours[][] image) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                image[i][j].rgbtRed = (byte) (255 - (image[i][j].rgbtRed & 0xFF));
                image[i][j].rgbtGreen = (byte) (255 - (image[i][j].rgbtGreen & 0xFF));
                image[i][j].rgbtBlue = (byte) (255 - (image[i][j].rgbtBlue & 0xFF));
            }
        }
    }
    public static void edgeDetect(rgbcolours[][] image) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
            int r = 0;
            int g = 0;
            int b = 0;

            for (int k = -1; k <= 1; k++) {
                for (int l = -1; l <= 1; l++) {
                if (i + k >= 0 && i + k < image.length && j + l >= 0 && j + l < image[i].length) {
                    r = image[i + k][j + l].rgbtRed - image[i][j].rgbtRed;
                    g = image[i + k][j + l].rgbtGreen - image[i][j].rgbtGreen;
                    b = image[i + k][j + l].rgbtBlue - image[i][j].rgbtBlue;
                }
                }
            }

            image[i][j].rgbtRed = (byte) (r > 0 ? r : 0);
            image[i][j].rgbtGreen = (byte) (g > 0 ? g : 0);
            image[i][j].rgbtBlue = (byte) (b > 0 ? b : 0);
            }
        }
    }
    public static void pixelate(rgbcolours[][] image) {
        int pixelSize =10;
        for (int i = 0; i < image.length - pixelSize; i += pixelSize) {
            for (int j = 0; j < image[i].length - pixelSize; j += pixelSize) {
            int r = 0;
            int g = 0;
            int b = 0;

            for (int k = i; k < i + pixelSize; k++) {
                for (int l = j; l < j + pixelSize; l++) {
                r += image[k][l].rgbtRed;
                    g += image[k][l].rgbtGreen;
                    b += image[k][l].rgbtBlue;
                }
            }

            r /= pixelSize * pixelSize;
            g /= pixelSize * pixelSize;
            b /= pixelSize * pixelSize;

            for (int k = i; k < i + pixelSize; k++) {
                for (int l = j; l < j + pixelSize; l++) {
                image[k][l].rgbtRed = (byte) r;
                image[k][l].rgbtGreen = (byte) g;
                image[k][l].rgbtBlue = (byte) b;
                }
            }
            }
        }
    }
    public static void posterize(rgbcolours[][] image) {
        int levels=40;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
            int r = image[i][j].rgbtRed / levels * levels;
            int g = image[i][j].rgbtGreen / levels * levels;
            int b = image[i][j].rgbtBlue / levels * levels;

            image[i][j].rgbtRed = (byte) r;
            image[i][j].rgbtGreen = (byte) g;
            image[i][j].rgbtBlue = (byte) b;
            }
        }
    }
    public static void noise(rgbcolours[][] image) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
            int r = image[i][j].rgbtRed + (int)(Math.random() * 255 - 125);
            int g = image[i][j].rgbtGreen + (int)(Math.random() * 255 - 125);
            int b = image[i][j].rgbtBlue + (int)(Math.random() * 255 - 125);

            image[i][j].rgbtRed = (byte) (r < 0 ? 0 : (r > 255 ? 255 : r));
            image[i][j].rgbtGreen = (byte) (g < 0 ? 0 : (g > 255 ? 255 : g));
            image[i][j].rgbtBlue = (byte) (b < 0 ? 0 : (b > 255 ? 255 : b));
            }
        }
    }
    

    public static void blur(rgbcolours[][] image) {
        int height = image.length;
        int width = image[0].length;
        rgbcolours[][] copy = new rgbcolours[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                copy[i][j] = image[i][j];
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int sumr = 0, sumg = 0, sumb = 0;
                float c = 0.0f;

                for (int m = (i - 1); m < (i + 2); m++) {
                    for (int k = (j - 1); k < (j + 2); k++) {
                        if (m < 0 || k < 0 || m >= height || k >= width) {
                            continue;
                        }
                        sumr += (copy[m][k].rgbtRed & 0xFF);
                        sumg += (copy[m][k].rgbtGreen & 0xFF);
                        sumb += (copy[m][k].rgbtBlue & 0xFF);
                        c += 1.0f;
                    }
                }

                int s_red = Math.round(sumr / c);
                int s_green = Math.round(sumg / c);
                int s_blue = Math.round(sumb / c);

                image[i][j].rgbtRed = (byte) s_red;
                image[i][j].rgbtGreen = (byte) s_green;
                image[i][j].rgbtBlue = (byte) s_blue;
            }
        }
    }
}
