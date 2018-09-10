public class Main {
    public static void main(String[] args){

        String Data = "AABABBBABAAABABAABBAABAA";
        String Data2 = "ERRMNERMMNNRREMRENNR";
        String Data3 = "ABCAABCBCBABBBCABCCBAB";
        String D4 = "ABAABABAABBBBBBBBBBBBA";
        String Df = "TAWFIKABTAWABBBBBBBBBBBBBBB";
        String Tst = "BBBBBBBBBBBBBBBBBBBBBBBBB";
        Compression com = new Compression();
        com.Compress(Df);
        String Decom =  com.Decompress();
        System.out.println(Decom.equals(Df));

        //com.ShowMyTagsDetails();


        //int count = com.CountOfTags();
        //System.out.println(count);


    }
}
