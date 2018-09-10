import Compression.DoCompress;
import Compression.DoDecompression;
import Main.Optmization;
import binarytree.Node;
import binarytree.TreeBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class HuffFrm {
    private JButton Compress;
    private JButton Decompress;
    private JTextField FnTxt;
    private JTextField DecomData;
    private JLabel Lbldecom;
    private JLabel lblcom;
    private JPanel panel1;

    public HuffFrm() {
        Compress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String data = ReadOriginalFile();

                //data optmization & count each character
                Optmization opt = new Optmization();
                ArrayList<Character> S = opt.DataOptmize(data);
                ArrayList<Integer> Coding = new ArrayList<>();
                for(int i = 0; i< S.size(); i++){
                    Coding.add(opt.CharCount(S.get(i),data));
                }

                //convert ArrayLists to Arrays to make the Tree
                char []Crs = new char[S.size()];
                for(int i=0;i<S.size();i++) Crs[i] = S.get(i);
                int []Frq = new int[Coding.size()];
                for(int i=0;i<Coding.size();i++) Frq[i] = Coding.get(i);

                //char [] chrs = ReadOriginalFile().toCharArray();
                //int []frq = {15,6,7,12,25,4,6,1,15};

                TreeBuilder MyTree = new TreeBuilder(Crs,Frq);
                Node MyHead = MyTree.BuildTree();
                DoCompress comp = new DoCompress(MyHead,data);
                comp.CodeGeneration(MyHead);
                comp.Compress();
            }
        });

        Decompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DoDecompression Decom = new DoDecompression("Huff.txt");
                String dDta = Decom.Decompress();
                DecomData.setText(dDta);
            }
        });
    }

    public static void main(String[] args){
        JFrame jfrm = new JFrame("HuffFrm");
        jfrm.setContentPane(new HuffFrm().panel1);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();
        jfrm.setVisible(true);
    }

   public String ReadOriginalFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(FnTxt.getText()));
            String dta = br.readLine();
            br.close();
            return dta;
        }catch (Exception e){}
        return "";
   }
}
