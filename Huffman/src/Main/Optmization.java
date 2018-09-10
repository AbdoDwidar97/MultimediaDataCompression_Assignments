package Main;

import java.util.ArrayList;

public class Optmization {
    public int CharCount(char c, String s){
        int counter = 0;
        for(int i=0; i< s.length(); i++){
            if(c == s.charAt(i)) counter++;
        }
        return counter;
    }

    public ArrayList<Character> DataOptmize(String str){
        ArrayList<Character> chrs = new ArrayList<>();
        for(int i =0; i< str.length(); i++){
            chrs.add(str.charAt(i));
        }


        for(int l=0; l < str.length(); l++){
            for(int n=0; n<chrs.size(); n++){
                if(str.charAt(l) == chrs.get(n)){
                    chrs.remove(n);
                }
            }
            chrs.add(str.charAt(l));
        }

        return chrs;
    }
}
