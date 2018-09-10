package lzwPkg;


import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args){

        /*

        String D1 = "ABAABABBAABAABAAAABABBBBBBBB";
        String D2 = "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC";
        Compression com = new Compression();
        com.Compress(D1);
        //com.ShowTags();

        Decompression Decomp = new Decompression(com.GetCompressedData());
        String DecompData =  Decomp.Decompress();
        System.out.println(DecompData);
        System.out.println(DecompData.equals(D1)); */

/*
        HashMap<String,Integer> lzw_Lst = new HashMap<>();
        lzw_Lst.put("AAA",0);
        lzw_Lst.put("BBB",1);
        lzw_Lst.put("CCC",2);
        lzw_Lst.put("DDD",3);
*/



        /* some test about HashMap:
        int H = lzw_Lst.get("AAA");
        System.out.println(H);
        if(lzw_Lst.containsKey("BBB")) System.out.println(lzw_Lst.get("BBB")); */

        /* //to get ascii code for any char !
        String Vr = "aAbBCcDd";
        char v = Vr.charAt(1);
        int vint = v;
        System.out.println(vint); */

    }
}
