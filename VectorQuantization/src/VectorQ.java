import Compression.Compress;
import Compression.Decompress;
import initialization.Optmization;
import initialization.ScheduleBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VectorQ {
    private JPanel panel;
    private JTextField TxtVecHeight;
    private JTextField TxtVecWidth;
    private JTextField TxtNumOfVecs;
    private JButton BtnCompress;
    private JButton BtnDecompress;
    private JTextField TxtFileName;
    private JTextField TxtCompressedFName;

    public VectorQ() {
        BtnCompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Fn = TxtFileName.getText().toString();
                String ComFn = "";
                for (int i=0; i<Fn.length(); i++) {
                    if(Fn.charAt(i) != '.') ComFn += Fn.charAt(i);
                    else break;
                }
                int [][] pxls = Optmization.readImage(Fn);
                int Vec_Wdth = Integer.parseInt(TxtVecWidth.getText().toString());
                int Vec_Higt = Integer.parseInt(TxtVecHeight.getText().toString());
                int NumOfVectorsInCodeBook = Integer.parseInt(TxtNumOfVecs.getText().toString());

                int [][]OptmizedPxls = Optmization.PxlsOptmize(Vec_Wdth,Vec_Higt);

                ScheduleBuilder Sb = new ScheduleBuilder(OptmizedPxls,Optmization.getWdth(),Optmization.getHigt(),Vec_Wdth,Vec_Higt);

                ArrayList<Integer[]> Vecs = Sb.getVectors();
                Sb.MakeCodeBook(Sb.Log2(NumOfVectorsInCodeBook),Vecs);
                ArrayList<Integer[]> Cbook = Sb.getCodeBook();

                Compress com = new Compress(Vecs,Cbook,Optmization.getWdth(),Optmization.getHigt(),Vec_Wdth,Vec_Higt);
                com.CompressImage(ComFn);
                JOptionPane.showMessageDialog(null,"File Compressed successfully !");
            }
        });
        BtnDecompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Decompress decom = new Decompress(TxtCompressedFName.getText().toString());
                decom.DecompressImage();
                JOptionPane.showMessageDialog(null,"File Decompressed successfully !");

            }
        });
    }



    public static void main(String[] args){
        JFrame jfrm = new JFrame("VectorQ");
        jfrm.setContentPane(new VectorQ().panel);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();
        jfrm.setVisible(true);
    }

}
