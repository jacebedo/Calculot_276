package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

public class CrystalBall {
    //fields
    private int Mass = 360; //initial shell filled
    private int ShellLevel = 1;
    private int ShellLevelMax;
    int ShellWidth;

    //Constructor
    CrystalBall(int _ShellLevelMax, int _sidelength) {
        ShellLevelMax = _ShellLevelMax;
        ShellWidth = _sidelength / (_ShellLevelMax + 1);
    }

    //Private Methods
    private int setShellLevel() {
        return (int) Math.floor(Mass / 360);
    }

    //Public Methods
    public void changeMass(int _mass) {
        Mass += _mass;
        ShellLevel = setShellLevel();
    }

    public int getMass() {
        return Mass;
    }

    public int getShellLevel() {
        return ShellLevel;
    }

    public int getShellWidth() {
        return ShellWidth;
    }
}