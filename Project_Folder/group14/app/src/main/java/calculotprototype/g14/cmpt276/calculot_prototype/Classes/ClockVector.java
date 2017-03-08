package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

public class ClockVector {
    //fields
    private int StartTheta;
    private int CurrentTheta;
    private float X;
    private float Y;
    private int Norm;   //or stage?
    private int Speed;

    //Constructor
    public QuestionVector(int _starttheta, int _norm, int _speed) {
        StartTheta = _starttheta;
        CurrentTheta = StartTheta;
        setX(CurrentTheta);
        setY(CurrentTheta);
        Norm = _norm;
        Speed = _speed;
    }

    //methods
    public int getCurrentTheta() {
        return CurrentTheta;
    }

    public void setX(int _theta) {
        //implement
    }

    public void setY(int _theta) {
        //implement
    }

    public int getNorm() {
        return Norm;
    }

}