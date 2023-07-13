package testFiles.ImageControl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class WindowImage extends JFrame implements ActionListener {
    JButton button;

    public  WindowImage() {
        System.out.println("ok");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        BufferedImage image = createImage2();
        JLabel label = new JLabel(new ImageIcon(image));
        this.setSize(500, 500);
        this.add(label);
//        BufferedImage bufferedImage = loadImage();
//        createButton();
    }

    public BufferedImage createImage2() {
        Random random = new Random();
        int rangeMin = random.nextInt(256 - 120);//0-136
        int rangeMax = rangeMin + random.nextInt(120, 256 - rangeMin);

        BufferedImage bufferedImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                int red = (int)((x / 256.0) * (rangeMax - rangeMin) + rangeMin);
                int green = (int)((y / 256.0) * (rangeMax - rangeMin) + rangeMin);
                int blue = (red + green) / 2;
                Color color = new Color(red, green, blue);
                bufferedImage.setRGB(x, y, color.getRGB());


            }

        }

        return bufferedImage;
    }

//    public void createButton() {
//        this.button = new JButton();
//        this.button.setIcon(new ImageIcon(Gradient(Color.MAGENTA, Color.CYAN)));
//        button.addActionListener(this);
//        this.add(button);
//    }

    public void showImage() {
        Random random = new Random();
//        int red = random.nextInt(256);
//        int green = random.nextInt(256);
//        int blue = random.nextInt(256);
//        Color color1 = new Color(red, green, blue);
//        red = random.nextInt(256);
//        green = random.nextInt(256);
//        blue = random.nextInt(256);
//        Color color2 = new Color(red, green, blue);
//
//        this.button.setIcon(new ImageIcon(Gradient(color1, color2)));
    }

    public void showWindow() {
        this.setVisible(true);
    }

    public BufferedImage Gradient(Color color1, Color color2) {
        int redMin = color1.getRed();
        int redMax = color2.getRed();
        int greenMin = color1.getGreen();
        int greenMax = color2.getGreen();
        int blueMin = color1.getBlue();
        int blueMax = color2.getBlue();
        BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int red = (int) (i / 255.0 * (redMax - redMin) + redMin);
                int green = (int) (j / 255.0 * (greenMax - greenMin) + greenMin);
                int blue = (int) ((i + j) / 2.0 / 255.0 * (blueMax - blueMin) + blueMin);

                Color color = new Color(red, green, blue);
                image.setRGB(i, j, color.getRGB());
            }
        }
        return image;
    }

    public BufferedImage Gradient() {
        Random random = new Random();
        int range = 120;
        int minRange = random.nextInt(256 - range);
        int maxRange = minRange + random.nextInt(range, 256 - minRange);

        BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int red = (int) (i / 256.0 * (maxRange - minRange) + minRange);
                int green = (int) (j / 256.0 * (maxRange - minRange) + minRange);
                int blue = (red + green) / 2;
                Color color = new Color(red, green, blue);
                image.setRGB(i, j, color.getRGB());
            }
        }
        return image;
    }


    public BufferedImage outLine(BufferedImage original) {
        BufferedImage output = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_BGR);
        for (int x = 0; x < original.getWidth() - 1; x++) {
            for (int y = 0; y < original.getHeight(); y++) {
                Color currentColor = new Color(original.getRGB(x, y));
                int red = currentColor.getRed();
                int green = currentColor.getGreen();
                int blue = currentColor.getBlue();
                int grayScale = red + green + blue;
                Color NextColor = new Color(original.getRGB(x + 1, y));
                int nextRed = NextColor.getRed();
                int nextGreen = NextColor.getGreen();
                int nextBlue = NextColor.getBlue();
                int nextGrayScale = nextRed + nextGreen + nextBlue;
                double multiplier = (grayScale + 1.0) / (nextGrayScale + 1);
//                if((grayScale - nextGrayScale) *(grayScale - nextGrayScale)>40*40){
                if (grayScale >= 383 && nextGrayScale < 383 || grayScale < 383 && nextGrayScale >= 383 /*|| grayScale == 765 && nextGrayScale < 765 || grayScale < 765 && nextGrayScale == 765*/) {
                    output.setRGB(x, y, new Color(0, 255, 0).getRGB());
                }
            }
        }
        for (int x = 0; x < original.getWidth() - 1; x++) {
            for (int y = 0; y < original.getHeight() - 1; y++) {
                Color currentColor = new Color(original.getRGB(x, y));
                int red = currentColor.getRed();
                int green = currentColor.getGreen();
                int blue = currentColor.getBlue();
                int grayScale = red + green + blue;
                Color NextColor = new Color(original.getRGB(x, y + 1));
                int nextRed = NextColor.getRed();
                int nextGreen = NextColor.getGreen();
                int nextBlue = NextColor.getBlue();
                int nextGrayScale = nextRed + nextGreen + nextBlue;
                double multiplier = (grayScale + 1.0) / (nextGrayScale + 1);
                if (grayScale >= 383 && nextGrayScale < 383 || grayScale < 383 && nextGrayScale >= 383) {
                    output.setRGB(x, y, new Color(0, 255, 0).getRGB());
                }
            }
        }
//        for (int x = 0; x < original.getWidth(); x++) {
//            for (int y = 0; y < original.getHeight() - 1; y++) {
//                Color currentColor = new Color(original.getRGB(x, y));
//                int red = currentColor.getRed();
//                int green = currentColor.getGreen();
//                int blue = currentColor.getBlue();
//                int grayScale = red + green + blue;
//                Color NextColor = new Color(original.getRGB(x, y + 1));
//                int NextRed = NextColor.getRed();
//                int NextGreen = NextColor.getGreen();
//                int NextBlue = NextColor.getBlue();
//                int NextGrayScale = NextRed + NextGreen + NextBlue;
//                if (grayScale >= 383 && NextGrayScale < 383 || grayScale < 383 && NextGrayScale >= 383) {
//                    output.setRGB(x, y, new Color(0, 255, 0).getRGB());
//                }
//            }
//        }
        return output;
    }

    public BufferedImage blackAndWhite(BufferedImage original) {
        BufferedImage output = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_BGR);
        for (int x = 0; x < original.getWidth(); x++) {
            for (int y = 0; y < original.getHeight(); y++) {
                Color currentColor = new Color(original.getRGB(x, y));
                int red = currentColor.getRed();
                int green = currentColor.getGreen();
                int blue = currentColor.getBlue();
                int grayScale = red + green + blue;
                if (grayScale >= 383)
                    output.setRGB(x, y, new Color(255, 255, 255).getRGB());
                else
                    output.setRGB(x, y, new Color(0, 0, 0).getRGB());
            }

        }
        return output;
    }

    public BufferedImage grayScale(BufferedImage original) {
        BufferedImage output = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_BGR);
        for (int x = 0; x < original.getWidth(); x++) {
            for (int y = 0; y < original.getHeight(); y++) {
                Color currentColor = new Color(original.getRGB(x, y));
                int red = currentColor.getRed();
                int green = currentColor.getGreen();
                int blue = currentColor.getBlue();
                int grayScale = (red + green + blue) / 3;
                Color newColor = new Color(grayScale, grayScale, grayScale);
                output.setRGB(x, y, newColor.getRGB());
            }

        }
        return output;

    }

    public BufferedImage negative(BufferedImage original) {
        BufferedImage output = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_BGR);
        for (int x = 0; x < original.getWidth(); x++) {
            for (int y = 0; y < original.getHeight(); y++) {
                Color currentColor = new Color(original.getRGB(x, y));
                int red = 255 - currentColor.getRed();
                int green = 255 - currentColor.getGreen();
                int blue = 255 - currentColor.getBlue();
                Color newColor = new Color(red, green, blue);
                output.setRGB(x, y, newColor.getRGB());
            }

        }
        return output;
    }

    public BufferedImage shuffleImage(BufferedImage original) {
        BufferedImage output = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_BGR);
        for (int x = 0; x < original.getWidth(); x++) {
            for (int y = 0; y < original.getHeight(); y++) {
                Color currentColor = new Color(original.getRGB(x, y));
                int red = currentColor.getRed();
                int green = currentColor.getGreen();
                int blue = currentColor.getBlue();
                Color newColor = new Color(green, blue, red);
                output.setRGB(x, y, newColor.getRGB());
            }

        }
        return output;

    }

    public BufferedImage loadImage() {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("gat.jpg"));

            if (bufferedImage != null) {
                this.setSize(bufferedImage.getWidth(), bufferedImage.getHeight());
                System.out.println("width: " + bufferedImage.getWidth());
                System.out.println("height: " + bufferedImage.getHeight());
                System.out.println("red: " + new Color(bufferedImage.getRGB(700, 700)).getRed());
                System.out.println("green: " + new Color(bufferedImage.getRGB(700, 700)).getGreen());
                System.out.println("blue: " + new Color(bufferedImage.getRGB(700, 700)).getBlue());
                return bufferedImage;


            }
        } catch (IOException e) {
            System.out.println("didn't worked");

        }

        return null;
    }

    public BufferedImage mirror(BufferedImage original) {
        BufferedImage output = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_BGR);
        for (int x = 0; x < original.getWidth(); x++) {
            for (int y = 0; y < original.getHeight(); y++) {
                output.setRGB(original.getWidth() - x - 1, y, original.getRGB(x, y));
            }

        }
        return output;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            System.out.println("button clicked");
            showImage();
        }

    }
}

