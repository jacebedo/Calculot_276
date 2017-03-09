package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

public class QuestionVector {
    //fields
    private int Theta = 360;
    private int X;
    private int Y;
    private int Norm;

    //Constructor
    QuestionVector(int _theta, int _x, int _y, int _norm) {
        Theta = _theta;
        X = _x;
        Y = _y;
        Norm = _norm;
    }

    //methods
    public int getTheta() {
        return Theta;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getNorm() {
        return Norm;
    }
}