package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

public class CrystalBall {
    //fields
    private int Mass = 360; //initial shell filled
    private int ShellLevel = 1;
    private int ShellLevelMax;

    //Constructor
    CrystalBall(int _ShellLevelMax) {
        ShellLevelMax = _ShellLevelMax;
    }

    //methods
    public void changeMass(int _mass) {
        Mass += _mass;
    }

    public int getMass() {
        return Mass;
    }

    public int getShellLevel() {
        return ShellLevel;
    }
}