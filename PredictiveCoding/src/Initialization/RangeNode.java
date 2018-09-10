package Initialization;

public class RangeNode {

    private int Min;
    private int Max;
    private int _Q;

    public RangeNode(int mn , int mx , int _q){
        this.Min = mn;
        this.Max = mx;
        this._Q = _q;
    }

    public int getMin() {
        return Min;
    }

    public int getMax() {
        return Max;
    }

    public int get_Q() {
        return _Q;
    }

    @Override
    public String toString() {
        return "Min : " + this.Min + " , Max : " + this.Max + " , _Q : " + this._Q;
    }
}
