package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

/**
 * This class is intended for the monster class in calculot. it will draw the monster on canvas and be assigned it's own question, in which the user can use.
 */

// Implement canvas class!
public class Monster{

    private float X_coordinate;
    private float Y_coordinate;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correct;
    private boolean isAlive;

    public Monster() {
        CalcQuestion assignedQuestion = new CalcQuestion(0,1);
        question = assignedQuestion.getQuestion();
        answer1 = assignedQuestion.getAnswer_1();
        answer2 = assignedQuestion.getAnswer_2();
        answer3 = assignedQuestion.getAnswer_3();
        answer4 = assignedQuestion.getAnswer_4();
        correct = assignedQuestion.getCorrect();
        X_coordinate = 0;
        Y_coordinate = 0;
        isAlive = true;
    }

    public Monster(int topic, int difficulty, int _x, int _y){
        CalcQuestion assignedQuestion = new CalcQuestion(topic,difficulty);

        question = assignedQuestion.getQuestion();
        answer1 = assignedQuestion.getAnswer_1();
        answer2 = assignedQuestion.getAnswer_2();
        answer3 = assignedQuestion.getAnswer_3();
        answer4 = assignedQuestion.getAnswer_4();
        correct = assignedQuestion.getCorrect();

        X_coordinate = _x;
        Y_coordinate = _y;
        isAlive = true;

    }

    // COORINATE GET METHODS
    public float getXCoord() { return X_coordinate; }
    public float getYCoord() { return Y_coordinate; }


    // QUESTION GET METHODS
    public String getQuestion(){ return question; }

    public String getAnswer1(){ return answer1; }
    public String getAnswer2(){ return answer2; }
    public String getAnswer3(){ return answer3; }
    public String getAnswer4(){ return answer4; }

    public String getCorrect() { return correct; }

    // If the question is correct, destroy the monster.
    public boolean isQuestionCorrect(String _answer) {
        if (correct.equals(_answer)) {
            isAlive = false;
            return true;
        }
        return false;
    }

    public void moveMonster(int _x, int _y) {
        X_coordinate = _x;
        Y_coordinate = _y;

        if (isAlive) {
            //draw the monster
        }
    }

}
