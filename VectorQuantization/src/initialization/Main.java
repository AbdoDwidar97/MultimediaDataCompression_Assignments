package initialization;

import Compression.Compress;
import Compression.Decompress;

import java.util.ArrayList;

public class Main {
    public static void main(String []args){


        String Fn = "gbCat.jpg";
        String ComFn = "";
        for (int i=0; i<Fn.length(); i++) {
            if(Fn.charAt(i) != '.') ComFn += Fn.charAt(i);
            else break;
        }
        int [][] pxls = Optmization.readImage(Fn);
        int Vec_Wdth = 2; int Vec_Higt = 2;
        int NumOfVectorsInCodeBook = 32;

        int [][]OptmizedPxls = Optmization.PxlsOptmize(Vec_Wdth,Vec_Higt);

        //int [][] Arr = {{1,2,7,9,4,11},{3,4,6,6,12,12},{4,9,15,14,9,9},{10,10,20,18,8,8},{4,3,17,16,1,4},{4,5,18,18,5,6}};
        //int Virtual_ImgWidth = 6; int Virtual_ImgHit = 6;

        ScheduleBuilder Sb = new ScheduleBuilder(OptmizedPxls,Optmization.getWdth(),Optmization.getHigt(),Vec_Wdth,Vec_Higt);
        //ScheduleBuilder Sb = new ScheduleBuilder(Arr,Virtual_ImgWidth,Virtual_ImgHit,Vec_Wdth,Vec_Higt);

        ArrayList<Integer[]> Vecs = Sb.getVectors();
        Sb.MakeCodeBook(Sb.Log2(NumOfVectorsInCodeBook),Vecs);
        ArrayList<Integer[]> Cbook = Sb.getCodeBook();

        Compress com = new Compress(Vecs,Cbook,Optmization.getWdth(),Optmization.getHigt(),Vec_Wdth,Vec_Higt);
        //Compress com = new Compress(Vecs,Cbook,Virtual_ImgWidth,Virtual_ImgHit,Vec_Wdth,Vec_Higt);
        com.CompressImage(ComFn);

        Decompress decom = new Decompress(ComFn + ".Vcq");
        decom.DecompressImage();








        //ArrayList<Integer[]> CompreesedCbook = decom.getCodeBook();
        //ArrayList<Integer> CompreesedVecs = decom.getCompressedVectors();
        //System.out.println(CompreesedCbook.size() + "   " + CompreesedVecs.size());
        //ArrayList<Integer> CompVecs = com.getCompressedVectors();
        //System.out.println(CompVecs.size());
        //System.out.println(Cbook.size());
        //System.out.println(Vecs.size());
        //System.out.println(Optmization.getWdth() + " , " + Optmization.getHigt());
        //System.out.println(Optmization.Log2(8));

        /*
        Integer x=14;
        float y = x;
        int z=8;
        int t = Math.round(y/z);
        System.out.println(t);*/

    }
}
