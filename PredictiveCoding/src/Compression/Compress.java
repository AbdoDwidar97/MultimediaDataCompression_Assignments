package Compression;

import Initialization.RangeNode;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Compress {

    private ArrayList<RangeNode> Schedule = new ArrayList<>();
    private ArrayList<Integer> ImgPixels = new ArrayList<>();

    private int ImgWidth;
    private int ImgHeight;

    private String CompFn;

    public Compress(ArrayList<RangeNode> S , ArrayList<Integer> Impxls , int ImgWdth , int ImgHight , String compFn){
        this.Schedule = S;
        this.ImgPixels = Impxls;
        this.ImgWidth = ImgWdth;
        this.ImgHeight = ImgHight;
        this.CompFn = compFn;
    }


    public void CompressImage(){

        ArrayList<Integer> CompPixels = new ArrayList<>();
        CompPixels.add(this.ImgPixels.get(0));
        for(int ik=0; ik < ImgPixels.size(); ik++){
            if(ImgPixels.get(ik) < 0) ImgPixels.set(ik,0);
            if(ImgPixels.get(ik) > 255) ImgPixels.set(ik,255);
        }
        for(int indx=1; indx < ImgPixels.size(); indx++){
            int itr = ImgPixels.get(indx);
            for(int idx=0; idx < Schedule.size(); idx++){
                if(itr >= Schedule.get(idx).getMin() && itr < Schedule.get(idx).getMax()) CompPixels.add(idx);
            }
        }

        SavetoFile(CompPixels);
    }


    private void SavetoFile(ArrayList<Integer> Compxls){

        try {
            PrintWriter pr = new PrintWriter(this.CompFn + ".Pr");

            pr.println(this.ImgWidth + " " + this.ImgHeight);
            for(RangeNode itr : this.Schedule){
                pr.println(itr.getMin() + " " + itr.getMax() + " " + itr.get_Q());
            }
            pr.println("~");
            for (int itr: Compxls) pr.println(itr);
            pr.close();

        } catch (FileNotFoundException e) {

        }
    }

}
