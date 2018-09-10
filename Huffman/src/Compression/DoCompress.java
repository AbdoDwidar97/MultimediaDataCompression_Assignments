package Compression;

import binarytree.Node;
import javafx.scene.paint.Stop;

import java.io.PrintWriter;
import java.util.ArrayList;

public class DoCompress {
    Node HeadOfTree;
    String Data;
    ArrayList<Character> Chars = new ArrayList<>();
    ArrayList<String> Coding = new ArrayList<>();
    String CodeOdChars = "";
    boolean EndOfLife = false;

    public DoCompress(Node H,String Dta){

        this.HeadOfTree = H;
        this.Data = Dta;
    }

    public int CodeGeneration(Node Hdr){

        try{

            if(EndOfLife);
            else {

                if(Hdr.Rchild != null){
                    if(Hdr.isVisited){
                        if(Hdr.Parent == null) {
                            return 1;
                            //EndOfLife = true;
                            //CodeGeneration(Hdr);

                        }
                        Hdr = Hdr.Parent;
                        CodeOdChars = CodeOdChars.substring(0,CodeOdChars.length()-1);
                        CodeGeneration(Hdr);
                        if(Hdr.Parent == null && Hdr.isVisited) return 1;
                    }else{
                        if(!Hdr.Rchild.isVisited){
                            CodeOdChars += "1";
                            Hdr = Hdr.Rchild;
                            CodeGeneration(Hdr);
                            if(Hdr.Parent == null && Hdr.isVisited) return 1;
                        }
                    }

                }else {

                    Chars.add(Hdr.Chr);
                    Coding.add(CodeOdChars);
                    CodeOdChars = CodeOdChars.substring(0,CodeOdChars.length()-1);
                    Hdr.isVisited = true;
                    Hdr = Hdr.Parent;
                    CodeGeneration(Hdr);
                    if(Hdr.Parent == null && Hdr.isVisited) return 1;
                }

                if(Hdr.Lchild != null){
                    if(Hdr.isVisited){
                        Hdr = Hdr.Parent;
                        CodeOdChars = CodeOdChars.substring(0,CodeOdChars.length()-1);
                        CodeGeneration(Hdr);
                        if(Hdr.Parent == null && Hdr.isVisited) return 1;
                    }else {
                        if(!Hdr.Lchild.isVisited){
                            CodeOdChars += "0";
                            Hdr.isVisited = true;
                            Hdr = Hdr.Lchild;
                            CodeGeneration(Hdr);
                            if(Hdr.Parent == null && Hdr.isVisited) return 1;
                        }

                    }

                }else {
                    Chars.add(Hdr.Chr);
                    Coding.add(CodeOdChars);
                    CodeOdChars = CodeOdChars.substring(0,CodeOdChars.length()-1);
                    Hdr = Hdr.Parent;
                    CodeGeneration(Hdr);
                    if(Hdr.Parent == null && Hdr.isVisited) return 1;
                }


            }

        return 1;
        }catch (Exception e){return 1;}

    }


    public void Compress(){
        HeaderOptimization();
        String Dta = this.Data;
        String CompressedData = "";

        for(int j=0; j<Dta.length(); j++){
            for(int k=0; k<Chars.size(); k++){
                if(Chars.get(k).equals(Dta.charAt(j))){
                    CompressedData += Coding.get(k);
                }
            }
        }
        PrintHeader(CompressedData);
    }


    private void HeaderOptimization(){
        ArrayList<String> NewCoding = new ArrayList<>();
        ArrayList<Character> NewChars = new ArrayList<>();
        for(int i=0; i<Coding.size(); i++){
            if(!Coding.get(i).equals("")){
                NewChars.add(Chars.get(i));
                NewCoding.add(Coding.get(i));
            }
        }
        this.Chars = NewChars;
        this.Coding = NewCoding;

    }
    public void ShowHeader(){
        for(int i=0; i<Chars.size(); i++){
            System.out.println(Chars.get(i) + " " + Coding.get(i));
        }
    }

    private void PrintHeader(String Compriessed){
        try {
            PrintWriter pr = new PrintWriter("Huff.txt");
            for(int i=0; i<Coding.size(); i++){
                pr.println(Chars.get(i) + " " + Coding.get(i));
            }
            pr.println("~");
            pr.println(Compriessed);
            pr.close();
        }catch (Exception e){}

    }
}
