package binarytree;

public class Node implements Comparable<Node> {

    public int Freq;
    public char Chr;
    public Node Parent;
    public Node Lchild;
    public Node Rchild;
    public boolean isVisited = false;

    Node(int freq,char chr,Node prnt,Node lchld,Node rchld){
        this.Freq = freq;
        this.Chr = chr;
        this.Parent = prnt;
        this.Lchild = lchld;
        this.Rchild = rchld;

    }

    public int compareTo(Node other){
        if(this.Freq == other.Freq) return 0;
        else if(this.Freq > other.Freq) return 1;
        else return -1;
    }

    @Override
    public String toString () {
        return "Freq = " + Freq;
    }
}
