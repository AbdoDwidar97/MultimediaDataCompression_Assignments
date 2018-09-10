package Main;

import Compression.DoCompress;
import Compression.DoDecompression;
import binarytree.Node;
import binarytree.TreeBuilder;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        /*
        char []chrs = {'A','B','C','D','E','F','G','H','I'};
        String data = String.valueOf(chrs);

        int []frq = {15,6,7,12,25,4,6,1,15};
        TreeBuilder MyTree = new TreeBuilder(chrs,frq);
        Node MyHead = MyTree.BuildTree();
        DoCompress comp = new DoCompress(MyHead,data);
        comp.CodeGeneration(MyHead);
        comp.Compress();

        DoDecompression Decom = new DoDecompression("Huff.txt");
        Decom.ShowHeader();
        String dDta = Decom.Decompress();
        System.out.println(dDta.equals(data));
        */


        String Bdt = "ABAAABBBBBCCCCDDBBBAAADD";
        Optmization opt = new Optmization();
        ArrayList<Character> S = opt.DataOptmize(Bdt);
        ArrayList<Integer> Coding = new ArrayList<>();
        for(int i = 0; i< S.size(); i++){
           Coding.add(opt.CharCount(S.get(i),Bdt));
        }
        for(int k=0; k<Coding.size(); k++){
            System.out.println(S.get(k) + " " + Coding.get(k));
        }

        Coding.toArray();
    }


}
