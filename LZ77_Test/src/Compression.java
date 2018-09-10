
import java.util.ArrayList;

public class Compression {

    private ArrayList<Tag> Taglst = new ArrayList<Tag>();

    public void AddTag(int indx, int lnth, char Nxt){
        Tag t1 = new Tag();
        t1.Index = indx;
        t1.Length = lnth;
        t1.Next = Nxt;
        Taglst.add(t1);
    }

    public void ShowAllTags(){
        for (int i=0; i<Taglst.size(); i++){
            Tag TT = Taglst.get(i);
            System.out.print("index is : " +  TT.Index);
            System.out.print(" ,length is : " + TT.Length);
            System.out.println(" ,Next is : " + TT.Next);

        }
    }

    public void Compress(String dta){

        try{
            String bfr = "";
            int VC = 0;

            for (int i=0; i<=dta.length(); i++){

                if(i == dta.length() && bfr.length() > 0){
                    int srch = dta.lastIndexOf(bfr,VC-1);
                    AddTag(VC-srch,bfr.length(),'|');
                    return;
                }

                if(i == 0) {
                    AddTag(0,0,dta.charAt(i));
                    VC = i+1;
                }
                else{
                    bfr += dta.charAt(i) + "";
                    int srch = dta.lastIndexOf(bfr,VC-1);
                    if(srch == -1){
                        if(bfr.length() == 1) {
                            AddTag(0,0,bfr.charAt(0));
                            bfr = "";
                            VC= i+1;
                        }
                        else {
                            String vl = bfr.substring(0,bfr.length()-1);
                            int sa = dta.lastIndexOf(vl,VC-1);
                            AddTag(VC-sa,vl.length(),bfr.charAt(bfr.length()-1));
                            bfr = "";
                            VC = i+1;
                        }
                    }
                }

            }

        }catch (Exception e){

        }


    }

    public int CountOfTags(){
        return Taglst.size();
    }
    public void ShowMyTagsDetails(){
        for(Tag T : Taglst){
            System.out.println(T.Index + " " + T.Length + " " + T.Next);
        }
    }




    public String Decompress(){

        String DecompressedData = "";

        for(Tag tr : Taglst){

            if(tr.Index == 0 && tr.Length ==0) DecompressedData += tr.Next;
            else if(tr.Next == '|'){

                if(tr.Length > tr.Index){
                    int Beginindx = DecompressedData.length() - tr.Index;
                    String bfr;
                    bfr = DecompressedData.substring(Beginindx);
                    String Vrr ="";
                    if(bfr.length() ==1){
                        Vrr += bfr.charAt(0);
                    }else{
                        for(int i=0;i<tr.Length;i++) {
                            Vrr+=bfr.charAt(i);
                        }

                    }
                    for(int i=0;i<tr.Length;i++) DecompressedData+=Vrr;

                }else{
                    int Beginindx = DecompressedData.length() - tr.Index;
                    String bfr;
                    bfr = DecompressedData.substring(Beginindx);
                    String Vrr ="";
                    for(int i=0;i<tr.Length;i++) Vrr+=bfr.charAt(i);
                    DecompressedData += Vrr;
                }



            }
            else if(tr.Length > tr.Index){
                int Beginindx = DecompressedData.length() - tr.Index;
                String bfr;
                bfr = DecompressedData.substring(Beginindx);
                String Vrr ="";
                for(int i=0;i<tr.Length;i++) Vrr+=bfr.charAt(i);
                for(int i=0;i<tr.Length;i++) DecompressedData+=Vrr;
            }
            else {

                int Beginindx = DecompressedData.length() - tr.Index;
                String bfr;
                bfr = DecompressedData.substring(Beginindx);
                String Vrr ="";
                for(int i=0;i<tr.Length;i++) Vrr+=bfr.charAt(i);
                DecompressedData += Vrr + tr.Next;

            }
        }

        return DecompressedData;
    }
}
