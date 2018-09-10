package Initialization;

import Compression.Compress;
import Compression.Decompress;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        //int []Arr ={6,15,17,60,100,90,66,59,18,3,5,16,14,67,63,2,98,92};
        //ArrayList<Integer> Pxls = new ArrayList<>();
        //for(int itr:Arr) Pxls.add(itr);

        ArrayList<Integer> Pxls = new ArrayList<>();
        int [][] pxlsArr = DataInitialize.GetPixels("Org.jpg");

        //initialize 2D array into ArrayList
        for(int x = 0;x< DataInitialize.getWdth(); x++){
            for(int y=0;y< DataInitialize.getHiht();y++){
                    Pxls.add(pxlsArr[y][x]);
                }
            }


        int NumOfBits = 5;
        ScheduleBuilder Sb = new ScheduleBuilder();

        Sb.ScheduleCreate(Pxls,NumOfBits);
        ArrayList<RangeNode> Rnd = Sb.getNonuniform_Schedule();

        Compress comp = new Compress(Rnd,"Org.jpg");
        comp.comp(pxlsArr);
        System.out.println("file compressed successfully !");

        //Decompress
        Decompress Dcomp = new Decompress("Org.nq");
        ArrayList<RangeNode> NSc = Dcomp.getSchedule();
        for(RangeNode itr : NSc) System.out.println(itr.toString());
        System.out.println("Image size : Width= " + Dcomp.getWdth() + " ,Height= " + Dcomp.getHight());
        Dcomp.Decomp();
        System.out.println("Decompressed!");
    }

}
