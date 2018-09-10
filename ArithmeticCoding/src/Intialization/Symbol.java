package Intialization;

public class Symbol {

    char Chr = '~';
    double low = 0;
    double high= 0;

    public Symbol(char c, double lw , double hi){
        this.Chr = c; this.high = hi; this.low = lw;
    }

    public char GetChar(){return Chr;}
    public double GetLow(){return low;}
    public double GetHigh(){return high;}

    @Override
    public String toString() {
        return "char: " + GetChar() + " ,Low: " + GetLow() + " ,High: " + GetHigh();
    }
}
