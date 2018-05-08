import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {


        BufferedImage image = ImageIO.read(new File("input.png"));
        int[][] pixels = new int[image.getWidth()][image.getHeight()];
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                int r = getPixelColor(image, j, i).getRed();
                int b = getPixelColor(image, j, i).getBlue();
                int g = getPixelColor(image, j, i).getGreen();
                int rgb = 65536 * r + 256 * g + b;
                pixels[j][i] = rgb;
            }
        }
        ArrayList<Integer> len_list = new ArrayList<>();
        int white = 65536 * 255 + 256 * 255 + 255;
        int col;
        for (int i = 0; i < image.getHeight(); i++) {
            int len = 0;
            for (int j = 0; j < image.getWidth(); j++) {
                col = pixels[j][i];
                if (pixels[j][i] != white & j == 0) {
                    len++;
                } else if (j != 0 & j != image.getWidth() - 1) {
                    if (col != white & (pixels[j - 1][i] == col | len == 0))
                        len++;
                    else {
                        if (len > 1) {
                            len_list.add(len);
                            len = 0;
                        } else if (col != white) len = 1;
                        else len = 0;
                    }
                } else if (j == image.getWidth() - 1) {
                    if (col != white & pixels[j - 1][i] == col)
                        len++;
                    if (len > 1) {
                        len_list.add(len);
                        len = 0;
                    }
                }
            }
        }
        for (int j = 0; j < image.getWidth(); j++) {
            int len = 0;
            for (int i = 0; i < image.getHeight(); i++) {
                col = pixels[j][i];
                if (pixels[j][i] != white & i == 0) {
                    len++;
                } else if (i != 0 & i != image.getHeight() - 1) {
                    if (col != white & (pixels[j][i - 1] == col | len == 0))
                        len++;
                    else {
                        if (len > 1) {
                            len_list.add(len);
                            len = 0;
                        } else if (len == 1) {
                            if (j == 0) {
                                if (pixels[j + 1][i - 1] != pixels[j][i - 1]) {
                                    len_list.add(1);
                                    len = 0;
                                } else if (col != white) len = 1;
                                else len = 0;
                            } else if (j == image.getWidth() - 1) {
                                if (pixels[j - 1][i - 1] != pixels[j][i - 1]) {
                                    len_list.add(1);
                                    len = 0;
                                } else if (col != white) len = 1;
                                else len = 0;
                            } else {
                                if (pixels[j - 1][i - 1] != pixels[j][i - 1] & pixels[j + 1][i - 1] != pixels[j][i - 1]) {
                                    len_list.add(1);
                                    len = 0;
                                } else if (col != white) len = 1;
                                else len = 0;

                            }
                        }
                    }
                } else if (i == image.getHeight() - 1) {
                    if (col != white & pixels[j][i - 1] == col)
                        len++;
                    if (len > 1) {
                        len_list.add(len);
                        len = 0;
                    } else if (len == 1) {
                        if (j == 0) {
                            if (pixels[j + 1][i - 1] != pixels[j][i - 1]) {
                                len_list.add(1);
                                len = 0;
                            } else if (col != white) len = 1;
                            else len = 0;
                        } else if (j == image.getWidth() - 1) {
                            if (pixels[j - 1][i - 1] != pixels[j][i - 1]) {
                                len_list.add(1);
                                len = 0;
                            } else if (col != white) len = 1;
                            else len = 0;
                        } else {
                            if (pixels[j - 1][i - 1] != pixels[j][i - 1] & pixels[j + 1][i - 1] != pixels[j][i - 1]) {
                                len_list.add(1);
                                len = 0;
                            } else if (col != white) len = 1;
                            else len = 0;
                        }
                    }
                }
            }
        }
        System.out.println("number of lines: " + len_list.size());
        System.out.println("length of each line: " + len_list);
        int sum = 0;
        for (int i = 0; i < len_list.size(); i++) {
            sum += len_list.get(i);
        }
        System.out.println("total length: " + sum);
    }


    private static Color getPixelColor(BufferedImage bi, int x, int y) {
        Object colorData = bi.getRaster().getDataElements(x, y, null);//данные о пикселе
        int argb = bi.getColorModel().getRGB(colorData);//преобразование данных в цветовое значение
        return new Color(argb, true);
    }
}
