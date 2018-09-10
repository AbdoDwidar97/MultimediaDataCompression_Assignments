package Intialization;

import Compression.Compress;
import Compression.Decompress;
import Compression.Settings;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        String Data = Settings.ReadOriginalFile("Org.txt");
        ScheduleBuilder ScBuild = new ScheduleBuilder(Data);
        ArrayList<Symbol> S =  ScBuild.ScheduleCreate();

        //Compress Comp = new Compress(Data,S);
        //Comp.DoComp();

       // Decompress Decomp = new Decompress();
       // String DecompDta = Decomp.DoDecompress();
       // System.out.println(DecompDta);



        //Decomp.ShowDetails();
        //for(Symbol s : S) System.out.println(s.toString());
    }
}
