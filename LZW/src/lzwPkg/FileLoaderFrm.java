package lzwPkg;

import java.io.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileLoaderFrm {
    private JButton button1;
    private JPanel panel1;
    private JTextField TxtCompress;
    private JButton Decmop;
    private JTextField TxtFileName;

    public FileLoaderFrm() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Compression com = new Compression();

                com.Compress(ReadOriginalFile(TxtFileName.getText()));
                WriteInFile(com.GetCompressedData());

            }
        });

        Decmop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Decompression Decom = new Decompression(ReadCompressedData());
                String DecomData =  Decom.Decompress();
                TxtCompress.setText(DecomData);
                JOptionPane.showMessageDialog(null,DecomData.equals(ReadOriginalFile(TxtFileName.getText())));
            }
        });
    }


    public static void main(String[] args) {
        JFrame jfrm = new JFrame("FileLoaderFrm");
        jfrm.setContentPane(new FileLoaderFrm().panel1);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();
        jfrm.setVisible(true);
    }

    public void WriteInFile(ArrayList<Integer> T){
        try {

            FileWriter fr = new FileWriter("Comp.lzw");
            PrintWriter pw = new PrintWriter(fr);
            for(int itr : T){
                pw.println(itr);
            }
            pw.close();
            JOptionPane.showMessageDialog(null,"File Compressed successfully !");
        }catch (Exception e){}


    }

    public ArrayList<Integer> ReadCompressedData(){

        ArrayList<Integer> CompDta = new ArrayList<>();
        try {
            FileReader fr = new FileReader("Comp.lzw");
            BufferedReader Br = new BufferedReader(fr);

            String Str;
            while ((Str = Br.readLine()) != null){
                int newStr =  Integer.parseInt(Str);
                CompDta.add(newStr);
            }
            Br.close();
        }catch (Exception e){}

        return CompDta;
    }

    public String ReadOriginalFile(String Fn){
        String Data = "";
        try {

            FileReader fr = new FileReader(Fn);
            BufferedReader Br = new BufferedReader(fr);
            Data = Br.readLine();
        }catch (Exception e){}

        return Data;
    }

}


