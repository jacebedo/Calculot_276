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
    public ClockVector(CrystalBall crystalBall, int _starttheta, int _speed) {
        StartTheta = _starttheta;
        CurrentTheta = StartTheta;
        setX(CurrentTheta);
        setY(CurrentTheta);
        //Norm = _norm; crystalBall.getShellLevel/getMass
        Speed = _speed;
    }

    //methods
    public int getCurrentTheta() {
        return CurrentTheta;
    }

    public void setX(int _theta) {
        //implement
        X = (float) (Math.cos( (double) _theta ) * Norm);
    }

    public void setY(int _theta) {
        //implement
        Y = (float) (Math.sin( (double) _theta ) * Norm);
    }

    public int getNorm() {
        return Norm;
    }

}