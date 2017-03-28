package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

import android.graphics.Canvas;
import android.util.Log;

/**
 * This class is intended for the monster class in calculot. it will draw the monster on canvas and be assigned it's own question, in which the user can use.
 */


public class Monster extends CalcQuestion{

    // Monster information
    private float X_coordinate;
    private int Y_coordinate;
    private float X_spawnCoordinate;
    private int rateofSpeed = 720;
    private int monster_width;
    private int monster_height;
    private boolean monster_isSelected;

    // Question Information
    private int topic;
    private int difficulty;



    // Default Constructor
    public Monster() {
        super(0,1);
        topic = 0;
        difficulty = 1;
        X_coordinate = 0;
        Y_coordinate = 0;
        monster_isSelected = false;
    }

    // Customized Constructor
    public Monster(int _topic, int _difficulty){
        super(_topic,_difficulty);
        topic = _topic;
        difficulty = _difficulty;
        X_coordinate = 0;
        Y_coordinate = 0;
        monster_isSelected = false;
    }

    //GET METHODS
    public int getXCoord() { return (int) X_coordinate; }
    public int getYCoord() { return Y_coordinate; }
    public int getTopic() { return topic; }
    public int getDifficulty() { return difficulty; }
    public int getMonster_width() { return monster_width; }
    public int getMonster_height() { return monster_height; }
    public boolean getMonster_selected() { return monster_isSelected; }

    public void setMonster_width(int _width){ monster_width = _width; }
    public void setMonster_height(int _height){ monster_height = _height; }
    public void setMonster_Y(int _y) { Y_coordinate = _y; }
    public void selectMonster() { monster_isSelected = true; }
    public void deselectMonster() { monster_isSelected = false; }

    // COORDINATE MOVE METHODS
    public void moveMonster(int _width) {
        X_coordinate-= _width/rateofSpeed;

    }

    // QUESTION CORRECTNESS CHECKING
    public boolean isQuestionCorrect(String _answer) {
        if (this.getCorrect().equals(_answer)) { return true; }
        return false;
    }
    public void getNewParams(int _topic, int _difficulty) {
        topic = _topic;
        difficulty = _difficulty;
    }

    // SPAWN METHODS
    public void spawnMonster(int _x) {
        X_coordinate = _x;
        X_spawnCoordinate = _x;
    }

    public void respawnMonster(int _difficulty) {
        this.deselectMonster();
        this.getNewQuestion(topic,_difficulty);
        X_coordinate = X_spawnCoordinate;
    }


}
