package binarytree;

import java.util.PriorityQueue;

public class TreeBuilder {
    PriorityQueue<Node> HuffmanTree = new PriorityQueue<Node>();


    public TreeBuilder(char []Chrs,int []freqs){
        for(int i=0; i<freqs.length;i++){
            Node Newnode = new Node(freqs[i],Chrs[i],null,null,null);
            HuffmanTree.add(Newnode);
        }

    }


    public Node BuildTree(){
        while (!HuffmanTree.isEmpty()){

            if(HuffmanTree.size() == 1) return HuffmanTree.poll();

            Node lchld = HuffmanTree.poll();
            Node rchld = HuffmanTree.poll();

            int prntFreq = lchld.Freq + rchld.Freq;
            Node Prnt = new Node(prntFreq,'~',null,lchld,rchld);
            lchld.Parent = Prnt; rchld.Parent = Prnt;
            HuffmanTree.add(Prnt);
        }
        return null;
    }


    public void ShowNodes(){

        while (!HuffmanTree.isEmpty()){
            System.out.println(HuffmanTree.poll());

        }
    }

}


