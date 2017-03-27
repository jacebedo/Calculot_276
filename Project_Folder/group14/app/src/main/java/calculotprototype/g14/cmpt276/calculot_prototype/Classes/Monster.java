package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

import android.graphics.Canvas;
import android.util.Log;

/**
 * This class is intended for the monster class in calculot. it will draw the monster on canvas and be assigned it's own question, in which the user can use.
 */


public class Monster{

    // Monster information
    private float X_coordinate;
    private int Y_coordinate;
    private boolean isAlive;

    // Question Information
    private int topic;
    private int difficulty;
    CalcQuestion currentQuestion = new CalcQuestion(0,1);


    public Monster() {
        topic = 0;
        difficulty = 1;
        currentQuestion.getNewQuestion(0,1);
        X_coordinate = 0;
        Y_coordinate = 0;
    }

    public Monster(int _topic, int _difficulty){
        topic = _topic;
        difficulty = _difficulty;
        currentQuestion.getNewQuestion(topic,difficulty);
        X_coordinate = 0;
        Y_coordinate = 0;
    }

    //GET METHODS
    public int getXCoord() { return (int) X_coordinate; }
    public int getYCoord() { return Y_coordinate; }
    public int getTopic() { return topic; }
    public int getDifficulty() { return difficulty; }

    // COORDINATE MOVE METHODS
    public void moveMonster() {
        X_coordinate-= 0.8;

    }

    // QUESTION CORRECTNESS CHECKING
    public boolean isQuestionCorrect(String _answer) {
        if (currentQuestion.getCorrect().equals(_answer)) { return true; }
        return false;
    }
    public void getNewParams(int _topic, int _difficulty) {
        topic = _topic;
        difficulty = _difficulty;
    }

    // SPAWN METHODS
    public void spawnMonster(int _x) {
        X_coordinate = _x;
    }

    public void respawnMonster(int _x) {
        currentQuestion.getNewQuestion(topic,difficulty);
        X_coordinate = _x;
    }

}
