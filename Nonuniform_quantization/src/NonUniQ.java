import Compression.Compress;
import Compression.Decompress;
import Initialization.DataInitialize;
import Initialization.RangeNode;
import Initialization.ScheduleBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class NonUniQ {
    private JPanel Jpanel;
    private JTextField TxtFileNameComp;
    private JTextField TxtNumOfBits;
    private JTextField TxtFileNameDecomp;
    private JButton BtnCompress;
    private JButton BtnDecompress;

    public NonUniQ() {
        BtnCompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Integer> Pxls = new ArrayList<>();
                int [][] pxlsArr = DataInitialize.GetPixels(TxtFileNameComp.getText());

                //initialize 2D array into ArrayList
                for(int x = 0;x< DataInitialize.getWdth(); x++){
                    for(int y=0;y< DataInitialize.getHiht();y++){
                        Pxls.add(pxlsArr[y][x]);
                    }
                }


                int NumOfBits = Integer.parseInt(TxtNumOfBits.getText().toString());
                ScheduleBuilder Sb = new ScheduleBuilder();

                Sb.ScheduleCreate(Pxls,NumOfBits);
                ArrayList<RangeNode> Rnd = Sb.getNonuniform_Schedule();

                Compress comp = new Compress(Rnd,TxtFileNameComp.getText());
                comp.comp(pxlsArr);
                JOptionPane.showMessageDialog(null,"File compressed successfully !");

            }
        });
        BtnDecompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Decompress Dcomp = new Decompress(TxtFileNameDecomp.getText());
                Dcomp.Decomp();
                JOptionPane.showMessageDialog(null,"Decompressed!");

            }
        });
    }


    public static void main(String[] args){
        JFrame jfrm = new JFrame("NonUniQ");
        jfrm.setContentPane(new NonUniQ().Jpanel);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();
        jfrm.setVisible(true);
    }
}
