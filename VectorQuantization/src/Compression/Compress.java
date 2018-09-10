package Compression;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Compress {

    private ArrayList<Integer[]> CodeBook = new ArrayList<>();
    private ArrayList<Integer[]> Vectors = new ArrayList<>();
    private int VecWidth;
    private int VecHeight;
    private int ImageWidth;
    private int ImageHeight;

    public Compress(ArrayList<Integer[]> Vecs , ArrayList<Integer[]> cb , int imgwdth , int imghit , int vwdth , int vhit){
        this.Vectors = Vecs;
        this.CodeBook = cb;
        this.ImageWidth = imgwdth;
        this.ImageHeight = imghit;
        this.VecWidth = vwdth;
        this.VecHeight = vhit;
    }

    private int GetAbsolute(int num){
        if(num < 0) num*=-1;
        return num;
    }

    //ArrayList<Integer> CompressedVectors = new ArrayList<>();

    public void CompressImage(String CompressedFileName){

        ArrayList<Integer> CompressedVectors = new ArrayList<>();

        for(int i=0; i<this.Vectors.size(); i++){

            ArrayList<Integer[]> Diffs = new ArrayList<>();

            for(Integer[] itr : CodeBook){

                Integer[] DiffVec = new Integer[this.VecWidth*this.VecHeight];
                for(int s=0; s<DiffVec.length; s++){
                    DiffVec[s] = this.GetAbsolute(this.Vectors.get(i)[s] - itr[s]);
                }
                Diffs.add(DiffVec);
            }

            Integer[] ValidVec = Diffs.get(0); int ValidID = 0;
            for (int ps=1; ps<Diffs.size(); ps++){

                Integer[] VecRes = new Integer[this.VecHeight*this.VecWidth];
                for (int prr=0; prr<VecRes.length; prr++) {
                    VecRes[prr] = Diffs.get(ps)[prr] - ValidVec[prr];
                }
                int vld = 0;
                for (Integer itr:VecRes) {
                    if(itr < 0) vld++;
                }
                if(vld >= VecRes.length/2) {
                    ValidVec = Diffs.get(ps);
                    ValidID = ps;
                }
            }

            CompressedVectors.add(ValidID);


        }

        SaveTofile(CompressedFileName,CompressedVectors);
    }


    private void SaveTofile(String fn,ArrayList<Integer> CompVecs){

        try {

            PrintWriter pr = new PrintWriter(fn + ".Vcq");
            pr.println(this.ImageWidth + " " + this.ImageHeight);
            pr.println(this.VecWidth + " " + this.VecHeight);

            for(int indx=0; indx < this.CodeBook.size(); indx++) {
                for(int i=0; i < (this.VecWidth*this.VecHeight); i++) {
                    if(i == (this.VecWidth*this.VecHeight)-1) pr.print(this.CodeBook.get(indx)[i] + "\n");
                    else pr.print(this.CodeBook.get(indx)[i] + "/");
                }
            }
            pr.println("~");

            for(int idx=0; idx<CompVecs.size(); idx++) pr.println(CompVecs.get(idx));
            pr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*
    public ArrayList<Integer> getCompressedVectors() {
        return CompressedVectors;
    }*/
}
