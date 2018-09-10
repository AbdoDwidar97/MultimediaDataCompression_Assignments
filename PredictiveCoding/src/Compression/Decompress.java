package Compression;

import Initialization.RangeNode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Decompress {

    private ArrayList<RangeNode> schedule = new ArrayList<>();
    private ArrayList<Integer> CompressedPixels = new ArrayList<>();
    private int ImgWidth;
    private int ImgHeight;
    private String CompFn;

    public Decompress(String Compfn){

        String Cfn = "";
        for(int i=0; i < Compfn.length(); i++){
            if(Compfn.charAt(i) != '.') Cfn += Compfn.charAt(i);
            else break;
        }
        this.CompFn = Cfn;

        try {

            BufferedReader br = new BufferedReader( new FileReader(Compfn));
            String Sz = br.readLine();
            String []szz = Sz.split(" ");
            this.ImgWidth = Integer.parseInt(szz[0]);
            this.ImgHeight = Integer.parseInt(szz[1]);

            boolean Hdd = false;
            while (!Hdd){
                String bfr = br.readLine();
                if(!bfr.equals("~")){
                    String [] Rnge = bfr.split(" ");
                    int min = Integer.parseInt(Rnge[0]);
                    int mx = Integer.parseInt(Rnge[1]);
                    int _q = Integer.parseInt(Rnge[2]);
                    RangeNode NewRng = new RangeNode(min,mx,_q);
                    this.schedule.add(NewRng);
                }else Hdd = true;
            }

            int PxNum = this.ImgWidth*this.ImgHeight;
            for(int idx=0; idx < PxNum; idx++){
                String FstPxl = br.readLine();
                int Cb = 0;
                Cb = Integer.parseInt(FstPxl);
                this.CompressedPixels.add(Cb);
            }
            br.close();

        } catch (IOException e) {

        }

    }



    public void DecompressImage(){

        ArrayList<Integer> DecompPixels = new ArrayList<>();

        DecompPixels.add(this.CompressedPixels.get(0));

        for(int idx=1; idx < CompressedPixels.size(); idx++){
            int INDEX = CompressedPixels.get(idx);
            int Decomm = this.schedule.get(INDEX).get_Q() + DecompPixels.get(0);
            if(Decomm > 255) Decomm = 255;
            if(Decomm < 0) Decomm = 0;
            DecompPixels.add(Decomm);
        }

        int [][] DecompressedImage = new int[this.ImgHeight][this.ImgWidth];
        int H = 0;
        for(int x=0; x < this.ImgHeight; x++){
            for(int y=0; y < this.ImgWidth; y++){
                DecompressedImage[x][y] = DecompPixels.get(H);
                H++;
            }
        }

        writeImage(DecompressedImage);
    }


    private void writeImage(int[][] imagePixels){

        BufferedImage image = new BufferedImage(this.ImgWidth, this.ImgHeight, BufferedImage.TYPE_INT_RGB);
        for (int y= 0; y < this.ImgHeight; y++) {
            for (int x = 0; x < this.ImgWidth; x++) {
                int value =-1 << 24;
                value= 0xff000000 | (imagePixels[y][x]<<16) | (imagePixels[y][x]<<8) | (imagePixels[y][x]);
                image.setRGB(x, y, value);

            }
        }

        File ImageFile = new File(this.CompFn + "_Decompressed");
        try {
            ImageIO.write(image, "jpg", ImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
