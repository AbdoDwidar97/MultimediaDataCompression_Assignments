package Initialization;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Optmization {

    private static int Wdth;
    private static int Higt;

    public static int[][] readImage(String path){

        BufferedImage img;
        try {
            img = ImageIO.read(new File(path));

            int hieght=img.getHeight();
            int width=img.getWidth();

            Higt = hieght;
            Wdth = width;

            int[][] imagePixels=new int[hieght][width];
            for(int x=0;x<width;x++){
                for(int y=0;y<hieght;y++){

                    int pixel=img.getRGB(x, y);

                    int red=(pixel  & 0x00ff0000) >> 16;
                    int grean=(pixel  & 0x0000ff00) >> 8;
                    int blue=pixel  & 0x000000ff;
                    int alpha=(pixel & 0xff000000) >> 24;
                    imagePixels[y][x]=red;
                }
            }
            return imagePixels;
        } catch (IOException e) {

            return null;
        }

    }

    public static int getHigt() {
        return Higt;
    }

    public static int getWdth() {
        return Wdth;
    }

}
