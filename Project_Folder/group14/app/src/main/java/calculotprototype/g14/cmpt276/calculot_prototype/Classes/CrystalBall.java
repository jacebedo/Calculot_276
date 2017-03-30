package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

public class CrystalBall {
    //fields
    private float Mass = 360; //initial shell filled (+1%)? for second chance
    private int ShellLevel = 1;
    private int ShellLevelMax;
    float ShellWidth;

    //Constructor
    CrystalBall(int _ShellLevelMax, int _sidelength) {
        ShellLevelMax = _ShellLevelMax;
        ShellWidth = _sidelength / (_ShellLevelMax + 1);
        setShellLevel();
    }

    //Private Methods
    private void setShellLevel() {
        ShellLevel = (int) Math.floor(Mass / 360);
    }

    //Public Methods
    public void changeMass(int _mass) {
        Mass += (float) _mass;
        setShellLevel();
    }

    public float getMass() {
        return Mass;
    }

    public int getShellLevel() {
        return ShellLevel;
    }

    public float getShellWidth() {
        return ShellWidth;
    }

    public int getShellLevelMax() {
        return ShellLevelMax;
    }
}