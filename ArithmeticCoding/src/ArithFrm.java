import Compression.Compress;
import Compression.Decompress;
import Compression.Settings;
import Intialization.ScheduleBuilder;
import Intialization.Symbol;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ArithFrm {
    private JPanel Panel1;
    private JTextField TxtOriginal;
    private JButton Compress;
    private JTextField TxtDecompress;
    private JButton Decompress;

    public ArithFrm() {
        Compress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Data = Settings.ReadOriginalFile(TxtOriginal.getText());
                ScheduleBuilder ScBuild = new ScheduleBuilder(Data);
                ArrayList<Symbol> S =  ScBuild.ScheduleCreate();

                Compression.Compress Comp = new Compress(Data,S);
                Comp.DoComp();
                JOptionPane.showMessageDialog(null,"File Compressed Successfully !");
            }
        });
        Decompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Compression.Decompress Decomp = new Decompress();
                String DecompDta = Decomp.DoDecompress();
                TxtDecompress.setText(DecompDta);
            }
        });
    }


    public static void main(String[] args){
        JFrame jfrm = new JFrame("ArithFrm");
        jfrm.setContentPane(new ArithFrm().Panel1);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();
        jfrm.setVisible(true);
    }
}
