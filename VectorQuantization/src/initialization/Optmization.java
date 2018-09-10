package initialization;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Optmization {

    private static int Higt; private static int Wdth;
    private static int [][]ImagePxls;

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
            ImagePxls = imagePixels;
            return imagePixels;
        } catch (IOException e) {

            return null;
        }

    }


    public static void writeImage(int[][] imagePixels,String outPath){

        BufferedImage image = new BufferedImage(Wdth, Higt, BufferedImage.TYPE_INT_RGB);
        for (int y= 0; y < Higt; y++) {
            for (int x = 0; x < Wdth; x++) {
                int value =-1 << 24;
                value= 0xff000000 | (imagePixels[y][x]<<16) | (imagePixels[y][x]<<8) | (imagePixels[y][x]);
                image.setRGB(x, y, value);

            }
        }

        File ImageFile = new File(outPath);
        try {
            ImageIO.write(image, "jpg", ImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static int[][] PxlsOptmize(int vec_wdth, int vec_higt) {

        int NwWdth = Wdth; int NwHigt = Higt;

        while (NwWdth % vec_wdth != 0) NwWdth++;
        while (NwHigt % vec_higt != 0) NwHigt++;

        int OpPxls[][] = new int [NwHigt][NwWdth];

        for(int x=0;x<NwWdth;x++){
            for(int y=0;y<NwHigt;y++){

                try {
                    OpPxls[y][x]= ImagePxls[y][x];
                }catch (Exception e){
                    OpPxls[y][x] = 0;
                }

            }
        }

        Wdth = NwWdth; Higt = NwHigt;
        return OpPxls;
    }


    public static int getWdth() {
        return Wdth;
    }

    public static int getHigt() {
        return Higt;
    }
}
