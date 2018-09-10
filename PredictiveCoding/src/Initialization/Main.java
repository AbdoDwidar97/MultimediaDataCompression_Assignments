package Initialization;

import Compression.Compress;
import Compression.Decompress;

import java.util.ArrayList;

public class Main {
    public static void main(String []args){

        String FN = "gbCat.jpg";
        int [][] ImagePixels = Optmization.readImage(FN);

        int NumOfLevls = 16;

        ArrayList<Integer> ImagePxls = new ArrayList<>();
        for(int x=0; x < Optmization.getHigt(); x++){
            for(int y=0; y < Optmization.getWdth(); y++){
                ImagePxls.add(ImagePixels[x][y]);
            }
        }

        String Cfn = "";
        for(int i=0; i < FN.length(); i++){
            if(FN.charAt(i) != '.') Cfn += FN.charAt(i);
            else break;
        }

        ScheduleBuilder Sb = new ScheduleBuilder(ImagePxls,Optmization.getWdth(),Optmization.getHigt(),NumOfLevls);
        Sb.MakeSchedule();
        ArrayList<RangeNode> Ranges = Sb.getRangeSchedule();

        Compress comp = new Compress(Ranges,ImagePxls,Optmization.getWdth(),Optmization.getHigt(),Cfn);
        comp.CompressImage();

        Decompress decomp = new Decompress("gbCat.Pr");
        decomp.DecompressImage();



        //for(RangeNode itr : Ranges) System.out.println(itr.toString());
        /*
        double x = Math.pow(2,3);
        int y = (int) x;
        System.out.println(y);*/

        /*
        double yl = 20;
        int y = 8;
        double xxl = Math.round(yl/y);
        int res = (int) xxl;
        System.out.println(res); */


        /*

        */
    }
}
