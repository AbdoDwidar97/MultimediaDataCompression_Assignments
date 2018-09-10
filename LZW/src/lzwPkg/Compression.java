package lzwPkg;

import java.util.ArrayList;
import java.util.HashMap;

public class Compression {

    private HashMap<String,Integer> lzw_Lst = new HashMap<>();
    private ArrayList<Integer> Tag = new ArrayList<>();


    public void AddNewTag(int t){
        this.Tag.add(t);
    }

    public void AddToDictionary(String bfr, int coding){
        this.lzw_Lst.put(bfr,coding);
    }

    public void Compress(String Data){
        int Coding = 128;
        String Bfr = "";
        boolean isAddOneChar = false;
        for(int i=1; i<Data.length(); i++){

            if(!isAddOneChar){
                Bfr+= Data.charAt(i-1);
                Bfr+= Data.charAt(i);
            }else{
                Bfr+= Data.charAt(i);
                isAddOneChar = false;
            }

            if(i==Data.length()-1 && Bfr.length()>0){
                if(lzw_Lst.containsKey(Bfr) == false){
                    String Fn = Bfr.substring(0,Bfr.length()-1);
                    int FnInt = lzw_Lst.get(Fn);
                    if(Fn.length() > 1) {
                        AddToDictionary(Bfr,Coding);
                        AddNewTag(FnInt);
                        Coding++;
                        Bfr = "";
                    }else{
                        char chr = Fn.charAt(0);
                        int Fchr = chr;
                        AddNewTag(Fchr);
                        AddToDictionary(Bfr,Coding);
                        Coding++;
                        Bfr = "";
                    }
                }else{
                    int FnInt = lzw_Lst.get(Bfr);
                    if(Bfr.length() > 1) {
                        AddToDictionary(Bfr,Coding);
                        AddNewTag(FnInt);
                        Coding++;
                        Bfr = "";
                    }else{
                        char chr = Bfr.charAt(0);
                        int Fchr = chr;
                        AddNewTag(Fchr);
                        AddToDictionary(Bfr,Coding);
                        Coding++;
                        Bfr = "";
                    }
                }
                return;
            }


            if(lzw_Lst.containsKey(Bfr) == false){
                String substr = Bfr.substring(0,Bfr.length()-1);
                if(lzw_Lst.get(substr)!=null){

                    int cdng = lzw_Lst.get(substr);
                    if(substr.length() > 1) {
                        AddToDictionary(Bfr,Coding);
                        AddNewTag(cdng);
                        Coding++;
                        Bfr = "";
                    }else{
                        char chr = substr.charAt(0);
                        int Fchr = chr;
                        AddNewTag(Fchr);
                        AddToDictionary(Bfr,Coding);
                        Coding++;
                        Bfr = "";
                    }


                }else{
                    if(substr.length()==1) {

                        char chr = substr.charAt(0);
                        int Fchr = chr;
                        AddNewTag(Fchr);
                        AddToDictionary(Bfr,Coding);
                        Coding++;
                        Bfr = "";

                    }
                }

            }else{
                isAddOneChar = true;
            }
        }
    }

    public void ShowTags(){
        for(int itr : Tag){
            System.out.println(itr);
        }
    }

    public ArrayList<Integer> GetCompressedData(){
        return Tag;
    }

}
