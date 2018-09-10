package Compression;
import Initialization.RangeNode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Decompress {

    private int Wdth;
    private int Hight;

    private ArrayList<RangeNode> Schedule = new ArrayList<>();
    private int [][] CompressedPxls;
    private String FileNm;

    public Decompress(String Fn){

        this.FileNm = Fn;
        try {

            BufferedReader br = new BufferedReader(new FileReader(Fn));
            String Size = br.readLine();
            String [] sz = Size.split(" ");
            this.Wdth =Integer.parseInt(sz[0]);
            this.Hight =Integer.parseInt(sz[1]);
            String itr;
            boolean END = false;
            itr = br.readLine();
            while (!END){

                if(!itr.equals("~")){

                    int Min=0; int Max=0; int Q=0; int _Q=0;
                    String []bfr = itr.split(" ");
                    Min = Integer.parseInt(bfr[0]);
                    Max = Integer.parseInt(bfr[1]);
                    Q = Integer.parseInt(bfr[2]);
                    _Q = Integer.parseInt(bfr[3]);
                    RangeNode NwRn = new RangeNode(Min,Max,Q,_Q);
                    Schedule.add(NwRn);
                    itr = br.readLine();
                }else {
                    END = true;
                }

            }

            CompressedPxls = new int [Hight][Wdth];
            for(int x = 0; x< Wdth; x++){
                for(int y=0;y< Hight;y++){

                    String bfr = br.readLine();
                    int nP = Integer.parseInt(bfr);
                    CompressedPxls[y][x] = nP;
                }
            }
            br.close();

        } catch (IOException e) {

        }

    }

    public void Decomp(){

        int [][]DecomImg = new int[this.Hight][this.Wdth];

        for(int x = 0;x< this.Wdth; x++){
            for(int y=0;y< this.Hight;y++){

                int CurPxl = CompressedPxls[y][x];

                RangeNode Rnd = Schedule.get(0);
                for(RangeNode itr:Schedule){
                    if(CurPxl == itr.getQ()) Rnd = itr;
                }

                DecomImg[y][x] = Rnd.get_Q();

            }
        }

        writeImage(DecomImg);
    }



    private void writeImage(int[][] imagePixels){

        BufferedImage image = new BufferedImage(this.Wdth, this.Hight, BufferedImage.TYPE_INT_RGB);
        System.out.println(image.getWidth());
        System.out.println(image.getHeight());

        for (int y= 0; y < this.Hight; y++) {
            for (int x = 0; x < this.Wdth; x++) {
                int value =-1 << 24;
                value= 0xff000000 | (imagePixels[y][x]<<16) | (imagePixels[y][x]<<8) | (imagePixels[y][x]);
                image.setRGB(x, y, value);
            }
        }

        String ComFn = "";
        String DSF = this.FileNm;
        for(int i=0;i<DSF.length();i++) {
            if(DSF.charAt(i) != '.') ComFn += DSF.charAt(i);
            else break;
        }
        ComFn += "_Decompressed";
        File ImageFile = new File(ComFn);
        try {
            ImageIO.write(image, "jpg", ImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public ArrayList<RangeNode> getSchedule() {
        return Schedule;
    }

    public int getWdth() {
        return Wdth;
    }

    public int getHight() {
        return Hight;
    }
}
