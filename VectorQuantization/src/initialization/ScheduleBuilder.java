package initialization;

import java.util.ArrayList;

public class ScheduleBuilder {

    private int ImageWidth;
    private int ImageHeight;
    private int VecWidth;
    private int VecHeight;

    private ArrayList<Integer[]> CodeBook = new ArrayList<>();

    private ArrayList<Integer[]> Vectors = new ArrayList<>();

    public ScheduleBuilder(int [][] pxlss , int ImgWdth , int ImgHigt , int Vwdth , int Vhight){

        this.ImageWidth = ImgWdth;
        this.ImageHeight = ImgHigt;
        this.VecWidth = Vwdth;
        this.VecHeight = Vhight;

        ArrayList<Integer> pxls1D = new ArrayList<>();
        for(int x=0; x < ImgHigt; x++){
            for(int y=0; y < ImgWdth; y++){
                pxls1D.add(pxlss[x][y]);
            }
        }


        int GoRound = (Vhight -1)*ImgWdth;
        int VecsWidthValid = ImgWdth/Vwdth;
        int ITRT = 0;

            for(int i=0; i<pxls1D.size(); i+=Vwdth){

                if (ITRT < VecsWidthValid){

                    int rrt = 0;
                    Integer [] NVec = new Integer[Vwdth*Vhight];
                    while (rrt < Vwdth){
                        NVec[rrt] = pxls1D.get(i+rrt);
                        rrt++;
                    }
                    int rtr = 0;
                    if(Vhight > 1){
                        int BBC = 1;
                        while (BBC < Vhight){

                            while (rtr < Vwdth){

                                NVec[rrt] = pxls1D.get((i+rtr)+(ImgWdth*BBC));
                                rrt++;
                                rtr++;
                            }
                            BBC++;
                            rtr = 0;
                        }

                    }


                    Vectors.add(NVec);
                    ITRT++;

                }else {
                    ITRT = 0;
                    i-= Vwdth;
                    i+= GoRound;
                }

            }


    }


    public void MakeCodeBook(int NumOfBits,ArrayList<Integer[]> Vecs){

        if(NumOfBits >= 0){

            // get average of whole vectors
            Integer[] VecAvg = new Integer[this.VecHeight*this.VecWidth];
            for(int i=0; i < VecAvg.length; i++) {
                VecAvg[i] = 0;
            }
            for(int i=0; i < VecAvg.length; i++){

                for(Integer[] itr: Vecs){
                    VecAvg[i] += itr[i];
                }

            }
            for(int i=0; i<VecAvg.length; i++){
                float Virtual = VecAvg[i];
                int NewVir = Math.round(Virtual/Vecs.size());
                VecAvg[i] = NewVir;
            }

            if(NumOfBits == 0){
                CodeBook.add(VecAvg);
                return;
            }


            //initialize Left & Right Vectors
            ArrayList<Integer[]> LVectors = new ArrayList<>();
            ArrayList<Integer[]> RVectors = new ArrayList<>();


            //get LAvg vector & RAvg vector
            Integer[] LAvg = new Integer[VecAvg.length];
            Integer[] RAvg = new Integer[VecAvg.length];

            for(int i=0; i<LAvg.length; i++){
                LAvg[i] = VecAvg[i] - 1;
            }

            for(int i=0; i<RAvg.length; i++){
                RAvg[i] = VecAvg[i] + 1;
            }


            //Splitting
            for(int indx=0; indx < Vectors.size(); indx++){

                Integer[] CurVec = Vectors.get(indx);

                Integer[] LeftDiff = new Integer[CurVec.length];
                Integer[] RightDiff = new Integer[CurVec.length];

                for(int iii = 0; iii<CurVec.length; iii++){
                    LeftDiff[iii] = GetAbsolute(CurVec[iii] - LAvg[iii]);
                }

                for(int iii = 0; iii<CurVec.length; iii++){
                    RightDiff[iii] = GetAbsolute(CurVec[iii] - RAvg[iii]);
                }

                Integer[] DiffResult = new Integer[LeftDiff.length];
                for (int i=0; i<DiffResult.length; i++){
                    DiffResult[i] = RightDiff[i] - LeftDiff[i];
                }

                int Vld = 0;
                for(int itr:DiffResult){
                    if(itr > 0) Vld++;
                }
                if(Vld >= (DiffResult.length/2)) LVectors.add(CurVec);
                else RVectors.add(CurVec);
            }

            MakeCodeBook(NumOfBits-1,LVectors);
            MakeCodeBook(NumOfBits-1,RVectors);

        }

    }

    public ArrayList<Integer[]> getVectors() {
        return Vectors;
    }

    public int Log2(int n){

        int val = 0;
        while (n > 1){
            n/=2;
            val++;
        }

        return val;
    }

    private int GetAbsolute(int num){
        if(num < 0) num*=-1;
        return num;
    }

    public ArrayList<Integer[]> getCodeBook() {
        return CodeBook;
    }
}
