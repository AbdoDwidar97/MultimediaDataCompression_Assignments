package Initialization;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class DataInitialize {

    private static int Wdth;
    private static int Hiht;

    public static int[][] GetPixels(String path){

        BufferedImage img;
        try {
            img = ImageIO.read(new File(path));

            int hieght=img.getHeight();
            int width=img.getWidth();

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

            Wdth = width; Hiht = hieght;
            return imagePixels;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            return null;
        }

    }


    public static int getHiht() {
        return Hiht;
    }

    public static int getWdth() {
        return Wdth;
    }
}
