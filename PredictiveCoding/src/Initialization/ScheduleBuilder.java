package Initialization;

import java.util.ArrayList;

public class ScheduleBuilder {

    private ArrayList<RangeNode> RangeSchedule = new ArrayList<>();

    private ArrayList<Integer> ImgPixels = new ArrayList<>();
    private ArrayList<Integer> Diffs = new ArrayList<>();

    private int NumOfLevels;

    public ScheduleBuilder(ArrayList<Integer> pxls ,int Wdth ,int Hight, int NumOfLvels){

        this.NumOfLevels = NumOfLvels;
        this.ImgPixels = pxls;
    }


    public void MakeSchedule(){

        int min = 0;
        this.Diffs.add(this.ImgPixels.get(0));
        for(int i=1; i < this.ImgPixels.size(); i++){
            int diff = this.ImgPixels.get(i) - this.ImgPixels.get(i-1);
            this.Diffs.add(diff);
        }

        int Step = (int)GetAvaliableStep();
        for(int idx=0; idx < this.NumOfLevels; idx++){
            int NewMin = min;
            int NewMax = min + Step;
            double __Q = Math.round((NewMax+NewMin)/2);
            int _q = (int) __Q;
            RangeNode Rnd = new RangeNode(NewMin,NewMax,_q);
            this.RangeSchedule.add(Rnd);
            min = NewMax;
        }
    }

    private double GetAvaliableStep(){

        int AvailNum = 1;
        double AvailStep = 1;
        boolean END = false;
        while (!END){
            double Rslt = Math.pow(2,AvailNum);
            if(Rslt > GetMax()) {
                AvailStep = Rslt / this.NumOfLevels;
                return AvailStep;
            }
            else AvailNum++;
        }
        return AvailStep;
    }

    private int GetMax(){

        int Mx = Diffs.get(0);
        for (int itr: Diffs){
            if(itr > Mx) Mx = itr;
        }
        return Mx;
    }

    public ArrayList<RangeNode> getRangeSchedule() {
        return RangeSchedule;
    }

    public ArrayList<Integer> getDiffs() {
        return Diffs;
    }
}
