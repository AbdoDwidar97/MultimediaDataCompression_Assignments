package Compression;

import Intialization.Symbol;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Compress {

    String Data;
    ArrayList<Symbol> Schedule = new ArrayList<>();

    public Compress(String Dta,ArrayList<Symbol> S){
        this.Data = Dta;
        this.Schedule = S;
    }

    public void DoComp(){

        double Lower = 0.0; double Higher = 1.0;

        for(int i=0; i<Data.length(); i++){
            Symbol Current = null;

            for(int j=0; j<Schedule.size(); j++){

                if(Data.charAt(i) == Schedule.get(j).GetChar()) Current = Schedule.get(j);

            }

            double NLower = Lower + (Higher - Lower) * Current.GetLow();
            double NHigher = Lower + (Higher - Lower) * Current.GetHigh();
            Lower = NLower; Higher = NHigher;

        }

        Double Comp = (Higher + Lower)/2;
        WriteToFile(Comp.toString());
    }

    private void WriteToFile(String CompressedDta){

        try {
                PrintWriter Fw = new PrintWriter("Compressed.Art");

                for (Symbol itr : Schedule){
                    Fw.println(itr.GetChar() + " " + itr.GetLow() + " " + itr.GetHigh());
                }
                Fw.println("~");
                Fw.println(CompressedDta);
                Fw.println(Data.length());
                Fw.close();

        } catch (IOException e) {

        }
    }
}
