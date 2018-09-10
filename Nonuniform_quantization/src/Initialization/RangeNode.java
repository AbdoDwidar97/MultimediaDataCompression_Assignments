package Initialization;

public class RangeNode {

    int Rmin = 0;
    int Rmax = 0;

    int Q = 0;
    int _Q = 0;

    public RangeNode(int rmin,int rmax , int q , int _q){
        this.Rmin = rmin;
        this.Rmax = rmax;
        this.Q = q;
        this._Q = _q;
    }


    public int get_Q() {
        return _Q;
    }

    public int getQ() {
        return Q;
    }

    public int getRmax() {
        return Rmax;
    }

    public int getRmin() {
        return Rmin;
    }

    @Override
    public String toString() {
        return "Range =[" + this.Rmin + " ... " + this.Rmax + "] ,Q = " + this.Q + " ,Q-1 = " + this._Q;
    }

}
