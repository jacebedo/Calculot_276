package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

import java.util.Random;

/**
 * Created by ephronax on 3/11/2017.
 */

public class CalcQuestion {

    // THIS CLASS IS A MULTIPLE CHOICE QUESTION THAT WILL BE USED FOR THE WIZARD TOWER GAME. IT CONTAINS THE FOLLOWING FIELDS:
    //
    // QUESTION: PRESENTS THE QUESTION
    // ANSWERS 1-4: 4 POSSIBLE ANSWERS TO THE QUESTION
    // CORRECT: THE CORRECT ANSWER TO THE QUESTION
    //
    //

    // Initialize fields
    private String question;
    private String answer_1;
    private String answer_2;
    private String answer_3;
    private String answer_4;
    private String correct;

    public CalcQuestion(int topic, int difficulty) {
        getQuestion(topic, difficulty);
    }

    public void getNewQuestion(int topic, int difficulty) {
        getQuestion(topic, difficulty);
    }
    // GET METHODS;
    public String getQuestion() {
        return question;
    }

    public String getAnswer_1() {
        return answer_1;
    }

    public String getAnswer_2() {
        return answer_2;
    }

    public String getAnswer_3() {
        return answer_3;
    }

    public String getAnswer_4() {
        return answer_4;
    }

    public String getCorrect() {
        return correct;
    }

    // Checks if the answer entered by the user is correct.
    public  boolean isCorrect(String _answer) {
        if (correct.equals(_answer)) {
            return true;
        }
        else { return false; }
    }


    // Question generators

    private void getQuestion(int topic, int difficulty) {
        // Topic is based on differentiation
        int constant;
        int exponent;

        if (topic == 0) {
            if (difficulty == 1) {
                // Difficulty = 1 ; 1 Term Question
                constant = getRandomInt(1,3);
                exponent = getRandomInt(1,3);
                question = "Differentiate: \n" + Integer.toString(constant) + "x^" + Integer.toString(exponent);
                correct = Integer.toString(constant*exponent) + "x^" + Integer.toString(exponent-1);

                // find a way to randomize these fields
                answer_1 = correct;
                answer_2 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent+1) + ") x^" + Integer.toString(exponent+1);
                answer_3 = Integer.toString(constant * (exponent-1)) + "x^" + Integer.toString(exponent-1);
                answer_4 = Integer.toString(exponent) + "x^" + Integer.toString(constant);
            }
            else if (difficulty == 2) {
                // Difficulty = 2 ; increased range for constant
                // Difficulty = 1 ; 1 Term Question
                constant = getRandomInt(-5,5);
                exponent = getRandomInt(-5,5);
                question = "Differentiate: \n" + Integer.toString(constant) + "x^" + Integer.toString(exponent);
                correct = Integer.toString(constant*exponent) + "x^" + Integer.toString(exponent-1);
                if (exponent == 0 || constant == 0) { correct = "0"; }
                // find a way to randomize these fields
                answer_1 = correct;
                answer_2 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent+1) + ") x^" + Integer.toString(exponent+1);
                answer_3 = Integer.toString(constant * (exponent-1)) + "x^" + Integer.toString(exponent-1);
                answer_4 = Integer.toString(exponent) + "x^" + Integer.toString(constant);
            }
            else {
                // Difficulty = 3 ; 2 terms with increased range
            }
        }

    }


    private int getRandomInt(int min,int max) {
        Random randominteger = new Random();
        return randominteger.nextInt((max - min) + 1) + min;
    }
}

