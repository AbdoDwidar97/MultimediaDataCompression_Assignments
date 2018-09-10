import Compression.Compress;
import Compression.Decompress;
import Initialization.Optmization;
import Initialization.RangeNode;
import Initialization.ScheduleBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PreCding {
    private JPanel panel;
    private JTextField TxtFileName;
    private JTextField TxtLevelsNum;
    private JTextField TxtComFname;
    private JButton Compress;
    private JButton TxtDecompress;

    public PreCding() {
        Compress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String FN = TxtFileName.getText().toString();
                int [][] ImagePixels = Optmization.readImage(FN);

                int NumOfLevls = Integer.parseInt(TxtLevelsNum.getText().toString());

                ArrayList<Integer> ImagePxls = new ArrayList<>();
                for(int x=0; x < Optmization.getHigt(); x++){
                    for(int y=0; y < Optmization.getWdth(); y++){
                        ImagePxls.add(ImagePixels[x][y]);
                    }
                }

                String Cfn = "";
                for(int i=0; i < FN.length(); i++){
                    if(FN.charAt(i) != '.') Cfn += FN.charAt(i);
                    else break;
                }

                ScheduleBuilder Sb = new ScheduleBuilder(ImagePxls,Optmization.getWdth(),Optmization.getHigt(),NumOfLevls);
                Sb.MakeSchedule();
                ArrayList<RangeNode> Ranges = Sb.getRangeSchedule();
                ArrayList<Integer> Diff = Sb.getDiffs();
                Compression.Compress comp = new Compress(Ranges,Diff,Optmization.getWdth(),Optmization.getHigt(),Cfn);
                comp.CompressImage();
                JOptionPane.showMessageDialog(null,"File Compressed successfully !");
            }
        });
        TxtDecompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Compfn = TxtComFname.getText().toString();
                Decompress decomp = new Decompress(Compfn);
                decomp.DecompressImage();
                JOptionPane.showMessageDialog(null,"File Decompressed successfully !");

            }
        });
    }



    public static void main(String[] args){
        JFrame jfrm = new JFrame("PreCding");
        jfrm.setContentPane(new PreCding().panel);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();
        jfrm.setVisible(true);
    }

}
