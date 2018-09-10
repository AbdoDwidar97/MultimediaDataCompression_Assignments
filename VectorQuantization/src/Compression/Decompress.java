package Compression;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Decompress {

    private ArrayList<Integer> CompressedVectors = new ArrayList<>();
    private ArrayList<Integer[]> CodeBook = new ArrayList<>();
    private ArrayList<Integer[]> DecompVectors = new ArrayList<>();
    private int ImageWidth;
    private int ImageHeight;
    private int VecWidth;
    private int VecHeight;
    private String DecopfileName;

    public Decompress(String fn){

        String ComFn = "";
        for (int i=0; i<fn.length(); i++) {
            if(fn.charAt(i) != '.') ComFn += fn.charAt(i);
            else break;
        }
        this.DecopfileName = ComFn;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fn));

            String ImgSz = br.readLine();
            String [] imgsz = ImgSz.split(" ");
            this.ImageWidth = Integer.parseInt(imgsz[0]);
            this.ImageHeight = Integer.parseInt(imgsz[1]);

            String VSz = br.readLine();
            String [] vsz = VSz.split(" ");
            this.VecWidth = Integer.parseInt(vsz[0]);
            this.VecHeight = Integer.parseInt(vsz[1]);

            boolean Hdd = false;
            while (!Hdd){
                String bfr = br.readLine();
                if(!bfr.equals("~")){
                    String[] Vector = bfr.split("/");
                    Integer[] Vecs = new Integer[this.VecWidth*this.VecHeight];
                    for(int i=0; i<Vector.length; i++) Vecs[i] = Integer.parseInt(Vector[i]);
                    CodeBook.add(Vecs);
                }else Hdd = true;
            }

            int VecsNums = (this.ImageHeight*this.ImageWidth)/(this.VecHeight*this.VecWidth);

            for(int i=0; i<VecsNums; i++) {
                String bfr = br.readLine();
                int NUM = Integer.parseInt(bfr);
                this.CompressedVectors.add(NUM);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void DecompressImage(){

        ArrayList<Integer> ComVecs = this.CompressedVectors;
        for(Integer itr:ComVecs){

            this.DecompVectors.add(CodeBook.get(itr));

        }

        ConvertToPixels();
    }

    private void ConvertToPixels(){

        Integer[] DecompPixels = new Integer[this.ImageWidth*this.ImageHeight];


        int GoRound = (this.VecHeight -1)*this.ImageWidth;
        int VecsWidthValid = this.ImageWidth/this.VecWidth;
        int ITRT = 0;

        int DecomVecsIndx = 0;
        for(int i=0; i<DecompPixels.length; i+=this.VecWidth){

            if (ITRT < VecsWidthValid){

                int rrt = 0;
                Integer [] NVec = this.DecompVectors.get(DecomVecsIndx);
                while (rrt < this.VecWidth){
                    DecompPixels[i+rrt] = NVec[rrt];
                    rrt++;
                }
                int rtr = 0;
                if(this.VecHeight > 1){
                    int BBC = 1;
                    while (BBC < this.VecHeight){

                        while (rtr < this.VecWidth){
                            DecompPixels[(i+rtr)+(this.ImageWidth*BBC)] = NVec[rrt];
                            rrt++;
                            rtr++;
                        }
                        BBC++;
                        rtr = 0;
                    }

                }
                ITRT++;
                DecomVecsIndx++;
            }else {
                ITRT = 0;
                i-= this.VecWidth;
                i+= GoRound;
            }


        }

        int H = 0;
        int [][] DecompressedImage = new int[this.ImageHeight][this.ImageWidth];
        for(int x=0; x < this.ImageHeight; x++){
            for(int y=0; y < this.ImageWidth; y++){
                DecompressedImage[x][y] = DecompPixels[H];
                H++;
            }
        }

        writeImage(DecompressedImage);

    }


    private void writeImage(int[][] imagePixels){

        BufferedImage image = new BufferedImage(this.ImageWidth, this.ImageHeight, BufferedImage.TYPE_INT_RGB);
        for (int y= 0; y < this.ImageHeight; y++) {
            for (int x = 0; x < this.ImageWidth; x++) {
                int value =-1 << 24;
                value= 0xff000000 | (imagePixels[y][x]<<16) | (imagePixels[y][x]<<8) | (imagePixels[y][x]);
                image.setRGB(x, y, value);

            }
        }

        File ImageFile = new File(this.DecopfileName + "_Decompressed");
        try {
            ImageIO.write(image, "jpg", ImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public ArrayList<Integer> getCompressedVectors() {
        return CompressedVectors;
    }

    public ArrayList<Integer[]> getCodeBook() {
        return CodeBook;
    }

}
