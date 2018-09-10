package Initialization;

import java.util.ArrayList;

public class ScheduleBuilder {

    private ArrayList<Integer> Ranges = new ArrayList<>();
    private ArrayList<RangeNode> Nonuniform_Schedule = new ArrayList<>();

    private void RangeBuilder(ArrayList<Integer> pxls, int lvel){

        if(lvel >= 0){

            if(lvel == 0) Ranges.add(getAverageOfList(pxls));

            ArrayList<Integer> Lpxls = new ArrayList<>();
            ArrayList<Integer> Rpxls = new ArrayList<>();

            int LAvrg = getAverageOfList(pxls) - 1;
            int RAvrg = getAverageOfList(pxls) + 1;

            for(int itr : pxls){

                if(itr < LAvrg) Lpxls.add(itr);
                else if(itr > RAvrg) Rpxls.add(itr);
                else if(itr > LAvrg && itr < RAvrg){
                    int Ldiff = itr - LAvrg;
                    int Rdiff = RAvrg - itr;
                    if(Ldiff >= Rdiff) Rpxls.add(itr);
                    else Lpxls.add(itr);
                }

            }


            RangeBuilder(Lpxls,lvel-1);
            RangeBuilder(Rpxls,lvel-1);

        }

    }


    private int getAverageOfList(ArrayList<Integer> lst){
        int ttal = 0;
        for(int itr:lst) ttal += itr;
        return Math.round(ttal/lst.size());
    }


    public void ScheduleCreate(ArrayList<Integer> pxls, int lvel){

        RangeBuilder(pxls,lvel);

        int Min = 0;
        for(int i=0; i<Ranges.size(); i++){

            if(i < Ranges.size()-1){

                int fst = Ranges.get(i);
                int snd = Ranges.get(i+1);

                int NewMax = Math.round((fst + snd)/2);
                RangeNode Rn = new RangeNode(Min,NewMax,i,Ranges.get(i));
                Nonuniform_Schedule.add(Rn);
                Min = NewMax;

            }else {

                int SpecialMin = Nonuniform_Schedule.get(Nonuniform_Schedule.size()-1).Rmax;
                RangeNode Rn = new RangeNode(Min,255,i,Ranges.get(i));
                Nonuniform_Schedule.add(Rn);
            }

        }
    }



    public ArrayList<RangeNode> getNonuniform_Schedule() {
        return Nonuniform_Schedule;
    }

    public ArrayList<Integer> getRanges() {
        return Ranges;
    }


}
