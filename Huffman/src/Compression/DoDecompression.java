package Compression;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DoDecompression {
    ArrayList<Character> chrs = new ArrayList<>();
    ArrayList<String> Coding = new ArrayList<>();
    String CompressdDta;
    public DoDecompression(String fn){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fn));
            boolean end = false;
            while (!end){
                String bfr = br.readLine();
                if(!bfr.equals("~")){
                    String []bfrarr = bfr.split(" ");
                    chrs.add(bfrarr[0].charAt(0));
                    Coding.add(bfrarr[1]);
                }else {
                    CompressdDta = br.readLine();
                    end = true;
                }

            }
            br.close();
        }catch (Exception e){}

    }

    public void ShowHeader(){
        for(int i=0; i<chrs.size(); i++){
            System.out.println(chrs.get(i) + " " + Coding.get(i));
        }
        System.out.println(CompressdDta);
    }

    public String Decompress(){
        String DecomData = "";
        int Cp = 0;
        String bfr = "";
        while (Cp < CompressdDta.length()){
            bfr += CompressdDta.charAt(Cp);
            for(int l=0; l<Coding.size(); l++){
                if(bfr.equals(Coding.get(l))){
                    DecomData += chrs.get(l);
                    bfr = "";
                }
            }
            Cp++;
        }
        return DecomData;
    }
}
