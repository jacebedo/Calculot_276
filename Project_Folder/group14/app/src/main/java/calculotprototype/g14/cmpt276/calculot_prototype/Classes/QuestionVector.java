package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

public class QuestionVector {
    //fields
    private int Theta = 360;
    private int X;
    private int Y;
    private int Norm;

    //Constructor
    QuestionVector(int _x, int _y) {
        X = _x;
        Y = _y;
        setTheta(X, Y);
        setNorm(X, Y);
    }

    //private methods
    private void setTheta(int X, int Y) {
        double YoverX = (double) Y/X;
        Theta = Math.toDegrees(Math.tan(YoverX));
    }

    private void setNorm(int X, int Y) {
        Norm = Math.sqrt((X*X) + (Y*Y));
    }

    //public methods
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