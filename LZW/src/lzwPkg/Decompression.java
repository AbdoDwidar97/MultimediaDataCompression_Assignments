package lzwPkg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Decompression {

    private HashMap<Integer, String> lzw_Lst_Inverse = new HashMap();

    private ArrayList<Integer> Tag = new ArrayList<>();

    Decompression(ArrayList<Integer> T) {
        this.Tag = T;
    }

    private void AddNewTag(int t) {
        this.Tag.add(t);
    }

    private void AddToDictionary(String bfr, int coding) {

        this.lzw_Lst_Inverse.put(coding,bfr);
    }


    public String Decompress() {

        String Data = "";
        int Coding = 128;

        int chrInt = Tag.get(0);
        char chr = (char) chrInt;
        Data += chr;

        for(int i=1;i<Tag.size();i++){

            if(Tag.get(i) < 128){
                chrInt = Tag.get(i);
                chr = (char) chrInt;
                Data+=chr;

                int sub1Int = Tag.get(i-1);
                char sub1 = (char) sub1Int;

                String Bfr = "";
                Bfr += sub1;
                Bfr += chr;
                AddToDictionary(Bfr,Coding);
                Coding++;

            }else{
                if(lzw_Lst_Inverse.containsKey(Tag.get(i))){
                    String Bfr = lzw_Lst_Inverse.get(Tag.get(i));
                    Data+= Bfr;
                    String Wbfr = "";
                    if(Tag.get(i-1) < 128){
                        int sub1Int = Tag.get(i-1);
                        char sub1 = (char) sub1Int;
                        Wbfr += sub1;
                        Wbfr += Bfr.charAt(0);
                        AddToDictionary(Wbfr,Coding);
                        Coding++;
                    }else{
                        String Bfr2 = lzw_Lst_Inverse.get(Tag.get(i-1));
                        String Tst = "";
                        Tst +=Bfr2;
                        Tst +=Bfr.charAt(0);
                        AddToDictionary(Tst,Coding);
                        Coding++;
                    }
                }else{
                    String SubString ="";
                    if(Tag.get(i-1) < 128){
                        int sub1Int = Tag.get(i-1);
                        char sub1 = (char) sub1Int;
                        SubString += sub1;
                        SubString += sub1;
                        Data+=SubString;
                        AddToDictionary(SubString,Coding);
                        Coding++;
                    }else{
                        SubString = lzw_Lst_Inverse.get(Tag.get(i-1));
                        String Tst = "";
                        Tst +=SubString;
                        Tst +=SubString.charAt(0);
                        Data += Tst;
                        AddToDictionary(Tst,Coding);
                        Coding++;
                    }
                }
            }
        }
        return Data;
    }


}