package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

public class ClockVector {
    //fields
    VectorQuestionGenerator TheGenerator;
    CrystalBall Crystal;
    private float StartTheta;
    private float CurrentTheta;
    private float PotentialGain;    //360 to -360
    private float X;
    private float Y;
    private float Norm;   //or stage?
    private float Speed;

    //Constructor
    public ClockVector(CrystalBall _crystalball, float _starttheta, int _time) {
        Crystal = _crystalball;

        StartTheta = _starttheta;
        CurrentTheta = StartTheta;
        setX();
        setY();
        Norm = (Crystal.getMass() + 360) * Crystal.getShellWidth() / 360;   //radius of clock vector for potential gain > 0
        Speed = - 360 / ((float) _time * 20);   //increment every 20th of a second or 50 ms
    }

    //Private Methods
    private void incrementAngle() {
        PotentialGain += Speed;
        CurrentTheta = StartTheta + PotentialGain;
        setX();
        setY();
    }

    private void setX() {
        //implement
        X = (float) (Math.cos( (double) CurrentTheta ) * Norm);
    }

    private void setY() {
        //implement
        Y = (float) (Math.sin( (double) CurrentTheta ) * Norm);
    }
    //Public Methods
    public float getX() {
        return X;
    }

    public float getY() {
        return Y;
    }

    public float getCurrentTheta() {
        return CurrentTheta;
    }

    public float getNorm() {
        return Norm;
    }

    public float getInnerRadius() {
        if (PotentialGain>0) {
            return Crystal.getMass() * Crystal.getShellWidth() / 360;
        }
        else {
            float InnerRadius = (Crystal.getMass() - 360) * Crystal.getShellWidth() / 360;
            if (InnerRadius>0)
                return InnerRadius;
            else return 0;
        }
    }
}