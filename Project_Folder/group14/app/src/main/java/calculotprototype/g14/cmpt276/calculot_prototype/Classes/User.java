package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

import android.text.TextUtils;

/**
 * Created by ephronax on 3/7/2017.
 */

public class User {

    // Fields for user class
    private String username;
    private String firstname;
    private String password;
    private int TotalXP;
    private int LearningXP;
    private int PracticeXP;

    // Add int array for achievements? or topics completed?

    // CONSTRUCTOR METHOD
    public User(String _username, String _firstname, String _password) {
        username = _username;
        firstname= _firstname;
        password = _password;
        TotalXP = 0;
        LearningXP = 0;
        PracticeXP = 0;
    }

    // GET METHODS;

    // Levels go in increments of 500 XP.
    public int getlevel() {
        return TotalXP/500;
    }

    // GET REMAINDER XP
    public int getRemainderXP() {
        return TotalXP%500;
    }

    // GET USERNAME
    public String getUsername() {
        return username;
    }

    // GET FIRST NAME
    public String getFirstname() {
        return firstname;
    }

    // Private String for security.
    public String getPassword() {
        return password;
    }

    // GET TOTAL XP
    public int getTotalXP() {
        return TotalXP;
    }

    // GET LEARNING XP
    public int getLearningXP() {
        return LearningXP;
    }

    // GET PRACTICE XP
    public int getPracticeXP() {
        return PracticeXP;
    }

    // SET METHODS:

    // SET USERNAME
    public void setUsername(String _username) {
        username = _username;
    }

    // SET FIRST NAME
    public void setFirstname(String _firstname) {
        firstname = _firstname;
    }

    // SET PASSWORD (ONLY IF VALID)
    public boolean setPassword(String _password) {
        boolean isValid = checkPassword(_password);
        if (isValid) {
            password = _password;
            return true;
        }
        else {
            return false;
        }

    }

    // SET TOTAL XP
    public void setTotalXP(int _TotalXP) {
        TotalXP = _TotalXP;
    }

    // SET LEARNING XP
    public void setLearningXP(int _LearningXP){
        LearningXP = _LearningXP;
    }

    // SET PRACTICE XP
    public void setPracticeXP(int _PracticeXP){
        PracticeXP = _PracticeXP;
    }


    // OTHER FUNCTIONS;

    // Check the password if it contains at least 1 uppercase and 1 number
    private boolean checkPassword(String password) {
        // TO IMPLEMENT
    //import needed
        if (    TextUtils.isEmpty(password)
            ||  !(password.matches(".*[0-9].*"))
            || !(password.matches(".*[A-Z].*")))return false;
        return true;
    }
}