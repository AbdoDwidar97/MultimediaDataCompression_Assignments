package Intialization;

import java.util.ArrayList;

public class ScheduleBuilder {

    String Data = "";


    public ScheduleBuilder(String dta){
        this.Data = dta;
    }

    private int CharCounter(char c){
        int Counting = 0;
        for(int i=0; i<Data.length(); i++){
            if(c == Data.charAt(i)) Counting++;
        }
        return Counting;
    }

    private String DataOptmization(){
        String OpData = "";
        ArrayList<Character> OtherDta = new ArrayList<>();
        for(int l=0; l<Data.length(); l++) OtherDta.add(Data.charAt(l));

        for(int i=0; i<Data.length(); i++){
            for(int j=0; j<OtherDta.size(); j++){

                if(Data.charAt(i) == OtherDta.get(j)){
                    OtherDta.remove(j);
                }

            }
            OtherDta.add(Data.charAt(i));
        }
        for(int u=0; u<OtherDta.size();u++) OpData += OtherDta.get(u);
        return OpData;
    }

    public ArrayList<Symbol> ScheduleCreate(){

        ArrayList<Symbol> Scheduling = new ArrayList<>();

        double LOW = 0;
        String OpDta = DataOptmization();
        for (int i=0; i<OpDta.length(); i++){
            double freq = CharCounter(OpDta.charAt(i));
            double prob = freq / Data.length();

            Symbol smb = new Symbol(OpDta.charAt(i),LOW,LOW + prob);
            Scheduling.add(smb);
            LOW += prob;
        }
        return Scheduling;
    }
}
