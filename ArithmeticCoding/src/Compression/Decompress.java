package Compression;

import Intialization.Symbol;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Decompress {
    ArrayList<Symbol> Schedule = new ArrayList<>();
    Double CompressedDta = 0.0;
    int NumOfChars = 0;

    public Decompress(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("Compressed.Art"));
            boolean end = false;
            while (!end){
                String bfr = br.readLine();
                if(!bfr.equals("~")){
                    String[] bfrarr = bfr.split(" ");
                    Symbol sbl = new Symbol(bfrarr[0].charAt(0),Double.parseDouble(bfrarr[1]),Double.parseDouble(bfrarr[2]));
                    Schedule.add(sbl);
                }else {
                    CompressedDta = Double.parseDouble(br.readLine());
                    NumOfChars = Integer.parseInt(br.readLine());
                    end = true;
                }

            }
            br.close();
        }catch (Exception e){}
    }


    public String DoDecompress(){

        String DecomData = "";
        double Lower = 0.0; double Higher = 1.0;
        double Code = CompressedDta;

        int lop = NumOfChars;
        Symbol ITR = Schedule.get(0);
        while (lop > 0){

            for(Symbol itr:Schedule){
                if(Code > itr.GetLow() && Code < itr.GetHigh()){
                    ITR = itr;
                    break;
                }

            }

            DecomData += ITR.GetChar();

            double  NLower = Lower + ((Higher - Lower) * ITR.GetLow());
            double  NHigher = Lower + ((Higher - Lower) * ITR.GetHigh());

            Code = (CompressedDta - NLower)/(NHigher - NLower);
            Lower = NLower; Higher = NHigher;
            lop--;

        }


        return DecomData;
    }


    public void ShowDetails(){
        for(Symbol itr : Schedule) System.out.println("Char: " + itr.GetChar() + " ,Low: " + itr.GetLow() + " ,High: " + itr.GetHigh());
        System.out.println(CompressedDta);
    }
}
