package Compression;

import Initialization.DataInitialize;
import Initialization.RangeNode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Compress {

    private String FileName;
    private ArrayList<RangeNode> Schedule = new ArrayList<>();

    public Compress(ArrayList<RangeNode> S, String fn){

        this.Schedule = S;
        this.FileName = fn;
    }

    public void comp(int [][]pxlss){

        int [][]CompressedImg = new int[DataInitialize.getHiht()][DataInitialize.getWdth()];

        for(int x = 0;x< DataInitialize.getWdth(); x++){
            for(int y=0;y< DataInitialize.getHiht();y++){

                int CurPxl = pxlss[y][x];

                RangeNode Rnd = Schedule.get(0);
                for(RangeNode itr:Schedule){
                    if(CurPxl >= itr.getRmin() && CurPxl < itr.getRmax()) Rnd = itr;
                }

                CompressedImg[y][x] = Rnd.getQ();

            }
        }

        WriteToFile(CompressedImg);
    }



    private void WriteToFile(int [][] arr){

        String ComFn = "";
        String DSF = this.FileName;
        for(int i=0;i<DSF.length();i++) {
            if(DSF.charAt(i) != '.') ComFn += DSF.charAt(i);
            else break;
        }
        try {
            PrintWriter pr = new PrintWriter(ComFn + ".nq");

            pr.println(DataInitialize.getWdth() + " " + DataInitialize.getHiht());
            for(RangeNode itr:Schedule) pr.println(itr.getRmin() + " " + itr.getRmax() + " " + itr.getQ() + " " + itr.get_Q());
            pr.println("~");

            for(int x = 0;x< DataInitialize.getWdth(); x++){
                for(int y=0;y< DataInitialize.getHiht();y++){

                    pr.println(arr[y][x]);
                }
            }

            pr.close();
            } catch (FileNotFoundException e) {

        }

    }


}
