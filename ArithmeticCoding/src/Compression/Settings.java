package Compression;

import java.io.BufferedReader;
import java.io.FileReader;

public class Settings {
    public static String ReadOriginalFile(String FN){

        String OriginalData = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(FN));
            OriginalData =  br.readLine();
            br.close();
        }catch (Exception e){}

        return OriginalData;
    }
}
