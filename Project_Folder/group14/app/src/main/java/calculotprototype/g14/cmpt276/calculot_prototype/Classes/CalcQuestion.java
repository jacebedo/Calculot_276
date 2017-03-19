package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

import android.view.View;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by jacebedo on 3/11/2017.
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
    public  boolean isCorrect(TextView v) {
        if (correct.equals(v.getText().toString())) {
            return true;
        }
        else { return false; }
    }


    // Question generators

    // Question interface - 0 for differentiation, 1 for integration, 2 for random (50/50 chance)
    private void getQuestion(int topic, int difficulty) {
        if (topic == 0) {
            getDiffQuestion(difficulty);
        }
        else if (topic == 1) {
            getIntQuestion(difficulty);
        }
        else if (topic == 2) {
            int random = getRandomInt(0,1);
            if (random == 0) {
                getDiffQuestion(difficulty);
            }
            if (random == 1) {
                getIntQuestion(difficulty);
            }
        }
    }


    // Figure out case when exponent = 1 ; CLEAR BUG INBOUND
    private void getDiffQuestion(int difficulty) {
        // Topic is based on differentiation
        int constant;
        int exponent;
        int randomCorrect;
        int DiffClass;
        //Randomly choose position of correct answer
        randomCorrect = getRandomInt(1, 4);
        DiffClass = getRandomInt(0, 3);

        // If the type of question is 0, make a polynomial type question
        if (DiffClass == 0 || DiffClass == 3) {

            if (difficulty == 1) {
                // Difficulty = 1 ; 1 Term Question
                constant = getRandomInt(1, 3);
                exponent = getRandomInt(1, 3);
                question = "Differentiate: \n" + Integer.toString(constant) + "x^" + Integer.toString(exponent);
                correct = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent - 1);
                if (randomCorrect == 1) {
                    answer_1 = correct;

                    answer_2 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent + 1) + ") x^" + Integer.toString(exponent + 1);

                    if (exponent == 1) {
                        answer_3 = "0";
                    } else {
                        answer_3 = Integer.toString(constant * (exponent - 1)) + "x^" + Integer.toString(exponent - 1);
                    }


                    answer_4 = Integer.toString(exponent) + "x^" + Integer.toString(constant);
                    return;
                }
                if (randomCorrect == 2) {
                    answer_2 = correct;

                    answer_3 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent + 1) + ") x^" + Integer.toString(exponent + 1);

                    if (exponent == 1) {
                        answer_4 = "0";
                    } else {
                        answer_4 = Integer.toString(constant * (exponent - 1)) + "x^" + Integer.toString(exponent - 1);
                    }

                    answer_1 = Integer.toString(exponent) + "x^" + Integer.toString(constant);
                    return;
                }
                if (randomCorrect == 3) {
                    answer_3 = correct;

                    answer_4 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent + 1) + ") x^" + Integer.toString(exponent + 1);

                    if (exponent == 1) {
                        answer_1 = "0";
                    } else {
                        answer_1 = Integer.toString(constant * (exponent - 1)) + "x^" + Integer.toString(exponent - 1);
                    }
                    answer_2 = Integer.toString(exponent) + "x^" + Integer.toString(constant);

                    return;
                }
                if (randomCorrect == 4) {
                    answer_4 = correct;

                    answer_1 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent + 1) + ") x^" + Integer.toString(exponent + 1);

                    if (exponent == 1) {
                        answer_2 = "0";
                    } else {
                        answer_2 = Integer.toString(constant * (exponent - 1)) + "x^" + Integer.toString(exponent - 1);
                    }
                    answer_3 = Integer.toString(exponent) + "x^" + Integer.toString(constant);

                    return;
                }
            } else if (difficulty == 2) {
                // Difficulty = 2 ; increased range for constant
                // Difficulty = 1 ; 1 Term Question
                constant = getRandomInt(-5, 5);
                exponent = getRandomInt(-5, 5);

                question = "Differentiate: \n" + Integer.toString(constant) + "x^" + Integer.toString(exponent);
                correct = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent - 1);

                if (exponent == 0 || constant == 0) {
                    correct = "0";
                }

                if (randomCorrect == 1) {
                    answer_1 = correct;
                    answer_2 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent + 1) + ") x^" + Integer.toString(exponent + 1);
                    if (exponent == 1) {
                        answer_3 = "0";
                    } else {
                        answer_3 = Integer.toString(constant * (exponent - 1)) + "x^" + Integer.toString(exponent - 1);
                    }
                    answer_4 = Integer.toString(exponent) + "x^" + Integer.toString(constant);
                    return;
                }
                if (randomCorrect == 2) {
                    answer_2 = correct;
                    answer_3 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent + 1) + ") x^" + Integer.toString(exponent + 1);
                    if (exponent == 1) {
                        answer_4 = "0";
                    } else {
                        answer_4 = Integer.toString(constant * (exponent - 1)) + "x^" + Integer.toString(exponent - 1);
                    }
                    answer_1 = Integer.toString(exponent) + "x^" + Integer.toString(constant);
                    return;
                }

                if (randomCorrect == 3) {
                    answer_3 = correct;
                    answer_4 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent + 1) + ") x^" + Integer.toString(exponent + 1);
                    if (exponent == 1) {
                        answer_1 = "0";
                    } else {
                        answer_1 = Integer.toString(constant * (exponent - 1)) + "x^" + Integer.toString(exponent - 1);
                    }
                    answer_2 = Integer.toString(exponent) + "x^" + Integer.toString(constant);
                    return;
                }
                if (randomCorrect == 4) {
                    answer_4 = correct;
                    answer_1 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent + 1) + ") x^" + Integer.toString(exponent + 1);
                    if (exponent == 1) {
                        answer_2 = "0";
                    } else {
                        answer_2 = Integer.toString(constant * (exponent - 1)) + "x^" + Integer.toString(exponent - 1);
                    }
                    answer_3 = Integer.toString(exponent) + "x^" + Integer.toString(constant);
                    return;
                }

            } else {
                // Difficulty = 3 ; 2 terms with increased range

                int constant_1 = getRandomInt(1, 3);
                int constant_2 = getRandomInt(1, 5);
                int exponent_1 = getRandomInt(2, 4);
                int exponent_2 = getRandomInt(2, 5);
                question = "Differentiate: \n" + Integer.toString(constant_1) + "x^" + Integer.toString(exponent_1) + " + " +
                        Integer.toString(constant_2) + "x^" + Integer.toString(exponent_2);

                correct = Integer.toString(constant_1 * exponent_1) + "x^" + Integer.toString(exponent_1 - 1) + " + " +
                        Integer.toString(constant_2 * exponent_2) + "x^" + Integer.toString(exponent_2 - 1);
                if (randomCorrect == 1) {
                    answer_1 = correct;

                    answer_2 = "(" + Integer.toString(constant_1) + "/" + Integer.toString(exponent_1) + ")x^" + Integer.toString(exponent_1 + 1) + " + " +
                            Integer.toString(constant_2 * (exponent_2 + 1)) + "x^" + Integer.toString(exponent_2 - 1);

                    answer_3 = Integer.toString(constant_1) + "x^" + Integer.toString(exponent_1 - 1) + " + " +
                            Integer.toString(constant_2) + "x^" + Integer.toString(exponent_2 - 1);

                    answer_4 = "(" + Integer.toString(constant_1) + " * " + Integer.toString(constant_2) + ")x^" + Integer.toString(exponent_1 + exponent_2);
                    return;
                }
                if (randomCorrect == 2) {
                    answer_2 = correct;

                    answer_3 = "(" + Integer.toString(constant_1) + "/" + Integer.toString(exponent_1) + ")x^" + Integer.toString(exponent_1 + 1) + " + " +
                            Integer.toString(constant_2 * (exponent_2 + 1)) + "x^" + Integer.toString(exponent_2 - 1);

                    answer_4 = Integer.toString(constant_1) + "x^" + Integer.toString(exponent_1 - 1) + " + " +
                            Integer.toString(constant_2) + "x^" + Integer.toString(exponent_2 - 1);

                    answer_1 = "(" + Integer.toString(constant_1) + " * " + Integer.toString(constant_2) + ")x^" + Integer.toString(exponent_1 + exponent_2);
                    return;
                }
                if (randomCorrect == 3) {
                    answer_3 = correct;

                    answer_4 = "(" + Integer.toString(constant_1) + "/" + Integer.toString(exponent_1) + ")x^" + Integer.toString(exponent_1 + 1) + " + " +
                            Integer.toString(constant_2 * (exponent_2 + 1)) + "x^" + Integer.toString(exponent_2 - 1);

                    answer_1 = Integer.toString(constant_1) + "x^" + Integer.toString(exponent_1 - 1) + " + " +
                            Integer.toString(constant_2) + "x^" + Integer.toString(exponent_2 - 1);

                    answer_2 = "(" + Integer.toString(constant_1) + " * " + Integer.toString(constant_2) + ")x^" + Integer.toString(exponent_1 + exponent_2);
                    return;
                }
                if (randomCorrect == 4) {
                    answer_4 = correct;

                    answer_1 = "(" + Integer.toString(constant_1) + "/" + Integer.toString(exponent_1) + ")x^" + Integer.toString(exponent_1 + 1) + " + " +
                            Integer.toString(constant_2 * (exponent_2 + 1)) + "x^" + Integer.toString(exponent_2 - 1);

                    answer_2 = Integer.toString(constant_1) + "x^" + Integer.toString(exponent_1 - 1) + " + " +
                            Integer.toString(constant_2) + "x^" + Integer.toString(exponent_2 - 1);

                    answer_3 = "(" + Integer.toString(constant_1) + " * " + Integer.toString(constant_2) + ")x^" + Integer.toString(exponent_1 + exponent_2);
                    return;
                }


            }
        }

        // If DiffClass is 1, make a question based on the natural exponential, e^x
        else if (DiffClass == 1) {
            if (difficulty == 1) {
                question = "Differentiate: \n e^x";
                correct = "e^x";

                if (randomCorrect == 1) {
                    answer_1 = correct;
                    answer_2 = "xe^x";
                    answer_3 = "1/x";
                    answer_4 = "(x)e^x-1";
                    return;
                }

                if (randomCorrect == 2) {
                    answer_2 = correct;

                    answer_3 = "1/x";
                    answer_4 = "xe^x";
                    answer_1 = "(1/x)e^x";
                    return;
                }

                if (randomCorrect == 3) {
                    answer_3 = correct;
                    answer_4 = "1/x";
                    answer_1 = "xe^x";
                    answer_2 = "xe^x-1";
                    return;
                }

                if (randomCorrect == 4) {
                    answer_4 = correct;
                    answer_1 = "1/x";
                    answer_2 = "xe^x";
                    answer_3 = "(1/x)e^x";
                    return;
                }
            }
            else if (difficulty == 2) {
                constant = getRandomInt(2, 5);
                String s_constant = Integer.toString(constant);
                question = "Differentiate: \n" + s_constant + "e^x";
                correct = s_constant + "e^x";

                if (randomCorrect == 1) {
                    answer_1 = correct;
                    answer_2 = s_constant + "xe^x";
                    answer_3 = s_constant + "/x";
                    answer_4 = "(" + s_constant + "/x" + ")e^x";
                    return;
                } else if (randomCorrect == 2) {
                    answer_2 = correct;
                    answer_3 = s_constant + "xe^x";
                    answer_4 = s_constant + "/x";
                    answer_1 = "(" + s_constant + "/x" + ")e^x";
                    return;
                } else if (randomCorrect == 3) {
                    answer_3 = correct;
                    answer_4 = s_constant + "xe^x";
                    answer_1 = s_constant + "/x";
                    answer_2 = "(" + s_constant + "/x" + ")e^x";
                    return;
                } else {
                    answer_4 = correct;
                    answer_1 = s_constant + "xe^x";
                    answer_2 = s_constant + "/x";
                    answer_3 = "(" + s_constant + "/x" + ")e^x";
                    return;
                }


            } else {
                int constant_1 = getRandomInt(2,5);
                int constant_2 = getRandomInt(2,5);

                question = "Differentiate: \n " + Integer.toString(constant_1) + "e^" + Integer.toString(constant_2) + "x";
                correct = Integer.toString(constant_1 * constant_2) + "e^" + Integer.toString(constant_2) + "x";

                if (randomCorrect == 1) {
                    answer_1 = correct;
                    answer_2 = Integer.toString(constant_1 * constant_2) + "e^x";
                    answer_3 = Integer.toString(constant_1 * constant_2) + "/x";
                    answer_4 = Integer.toString(constant_1 * constant_2) + "xe^" + Integer.toString(constant_2)+ "x";
                    return;
                } else if (randomCorrect == 2) {
                    answer_2 = correct;
                    answer_1 = Integer.toString(constant_1 * constant_2) + "e^x";
                    answer_4 = Integer.toString(constant_1 * constant_2) + "/x";
                    answer_3 = Integer.toString(constant_1 * constant_2) + "xe^" + Integer.toString(constant_2)+ "x";
                } else if (randomCorrect == 3) {
                    answer_3 = correct;
                    answer_1 = Integer.toString(constant_1 * constant_2) + "e^x";
                    answer_2 = Integer.toString(constant_1 * constant_2) + "/x";
                    answer_4 = Integer.toString(constant_1 * constant_2) + "xe^" + Integer.toString(constant_2)+ "x";
                    return;
                } else {
                    answer_4 = correct;
                    answer_1 = Integer.toString(constant_1 * constant_2) + "e^x";
                    answer_2 = Integer.toString(constant_1 * constant_2) + "/x";
                    answer_3 = Integer.toString(constant_1 * constant_2) + "xe^" + Integer.toString(constant_2)+ "x";
                    return;
                }

            }
        } else {
            int TrigFuncBase = getRandomInt(0,1);

            // Question will be based on sine function
            if (TrigFuncBase == 0) {
                if (difficulty == 1) {
                    question = "Differentiate: \n sin(x)";
                    correct = "cos(x)";

                    if (randomCorrect == 1) {
                        answer_1 = correct;
                        answer_2 = "-sin(x)";
                        answer_3 = "-cos(x)";
                        answer_4 = "sin(x)";
                        return;
                    } else if (randomCorrect == 2) {
                        answer_2 = correct;
                        answer_3 = "-sin(x)";
                        answer_1 = "-cos(x)";
                        answer_4 = "sin(x)";
                        return;
                    } else if (randomCorrect == 3) {
                        answer_3 = correct;
                        answer_4 = "-sin(x)";
                        answer_1 = "-cos(x)";
                        answer_2 = "sin(x)";
                        return;
                    } else {
                        answer_4 = correct;
                        answer_2 = "-sin(x)";
                        answer_1 = "-cos(x)";
                        answer_3 = "sin(x)";
                        return;
                    }
                } else if (difficulty == 2){
                    constant = getRandomInt(2,5);
                    String s_constant = Integer.toString(constant);
                    question = "Differentiate: \n" + s_constant + "sin(x)";
                    correct = s_constant + "cos(x)";

                    if (randomCorrect == 1) {
                        answer_1 = correct;
                        answer_2 = "-" + s_constant + "sin(x)";
                        answer_3 = "-" + s_constant + "cos(x)";
                        answer_4 = s_constant + "sin(x)";
                    } else if (randomCorrect == 2) {
                        answer_2 = correct;
                        answer_4 = "-" + s_constant + "sin(x)";
                        answer_1 = "-" + s_constant + "cos(x)";
                        answer_3 = s_constant + "sin(x)";
                        return;
                    } else if (randomCorrect == 3) {
                        answer_3 = correct;
                        answer_4 = "-" + s_constant + "sin(x)";
                        answer_1 = "-" + s_constant + "cos(x)";
                        answer_2 = s_constant + "sin(x)";
                        return;
                    } else {
                        answer_4 = correct;
                        answer_2 = "-" + s_constant + "sin(x)";
                        answer_1 = "-" + s_constant + "cos(x)";
                        answer_3 = s_constant + "sin(x)";
                        return;
                    }
                } else {
                    int constant_1 = getRandomInt(2,4);
                    int constant_2 = getRandomInt(3,6);

                    question = "Differentiate: \n" + Integer.toString(constant_1) + "sin(" + Integer.toString(constant_2) +  "x)";
                    correct = Integer.toString(constant_1 * constant_2) +  "cos(" + Integer.toString(constant_2) + "x)";

                    if (randomCorrect == 1) {
                        answer_1 = correct;
                        answer_2 = "-" + Integer.toString(constant_1 * constant_2) + "sin(" + Integer.toString(constant_2) + "x)";
                        answer_3 = Integer.toString(constant_1 * constant_2) + "cos(x)";
                        answer_4 = "-" + Integer.toString(constant_1 * constant_2) + "cos(" + Integer.toString(constant_2) + "x)";
                        return;
                    } else if (randomCorrect == 2) {
                        answer_2 = correct;
                        answer_1 = "-" + Integer.toString(constant_1 * constant_2) + "sin(" + Integer.toString(constant_2) + "x)";
                        answer_3 = Integer.toString(constant_1 * constant_2) + "cos(x)";
                        answer_4 = "-" + Integer.toString(constant_1 * constant_2) + "cos(" + Integer.toString(constant_2) + "x)";
                        return;
                    } else if (randomCorrect == 3) {
                        answer_3 = correct;
                        answer_2 = "-" + Integer.toString(constant_1 * constant_2) + "sin(" + Integer.toString(constant_2) + "x)";
                        answer_1 = Integer.toString(constant_1 * constant_2) + "cos(x)";
                        answer_4 = "-" + Integer.toString(constant_1 * constant_2) + "cos(" + Integer.toString(constant_2) + "x)";
                        return;
                    } else {
                        answer_4 = correct;
                        answer_2 = "-" + Integer.toString(constant_1 * constant_2) + "sin(" + Integer.toString(constant_2) + "x)";
                        answer_3 = Integer.toString(constant_1 * constant_2) + "cos(x)";
                        answer_1 = "-" + Integer.toString(constant_1 * constant_2) + "cos(" + Integer.toString(constant_2) + "x)";
                        return;
                    }


                }
            } else {
                // Question is based off of the cosine function.
                if (difficulty == 1) {
                    question = "Differentiate: \n" + "cos(x)";
                    correct = "-sin(x)";

                    if (randomCorrect == 1) {
                        answer_1 = correct;
                        answer_2 = "sin(x)";
                        answer_3 = "cos(x)";
                        answer_4 = "-cos(x)";
                        return;
                    } else if (randomCorrect == 2) {
                        answer_2 = correct;
                        answer_3 = "sin(x)";
                        answer_1 = "cos(x)";
                        answer_4 = "-cos(x)";
                        return;
                    } else if (randomCorrect == 3) {
                        answer_3 = correct;
                        answer_4 = "sin(x)";
                        answer_1 = "cos(x)";
                        answer_2 = "-cos(x)";
                        return;
                    } else {
                        answer_4 = correct;
                        answer_2 = "sin(x)";
                        answer_3 = "cos(x)";
                        answer_1 = "-cos(x)";
                        return;
                    }
                } else if (difficulty == 2) {
                    String s_constant = Integer.toString(getRandomInt(2, 5));
                    question = "Differentiate: \n" + s_constant + "cos(x)";
                    correct = "-" + s_constant + "sin(x)";

                    if (randomCorrect == 1) {
                        answer_1 = correct;
                        answer_2 = s_constant + "sin(x)";
                        answer_3 = s_constant + "cos(x)";
                        answer_4 = "-" + s_constant + "cos(x)";
                        return;
                    } else if (randomCorrect == 2) {
                        answer_2 = correct;
                        answer_3 = s_constant + "sin(x)";
                        answer_4 = s_constant + "cos(x)";
                        answer_1 = "-" + s_constant + "cos(x)";
                        return;
                    } else if (randomCorrect == 3) {
                        answer_3 = correct;
                        answer_4 = s_constant + "sin(x)";
                        answer_1 = s_constant + "cos(x)";
                        answer_2 = "-" + s_constant + "cos(x)";
                        return;
                    } else {
                        answer_4 = correct;
                        answer_1 = s_constant + "sin(x)";
                        answer_3 = s_constant + "cos(x)";
                        answer_2 = "-" + s_constant + "cos(x)";
                        return;
                    }
                } else {
                    int constant_1 = getRandomInt(2, 4);
                    int constant_2 = getRandomInt(3, 6);

                    question = "Differentiate: \n" + Integer.toString(constant_1) + "cos(" + Integer.toString(constant_2) + "x)";
                    correct = "-" + Integer.toString(constant_1 * constant_2) + "sin(" + Integer.toString(constant_2) + "x)";

                    if (randomCorrect == 1) {
                        answer_1 = correct;
                        answer_2 = Integer.toString(constant_1 * constant_2) + "sin(" + Integer.toString(constant_2) + "x)";
                        answer_3 = "-" + Integer.toString(constant_1 * constant_2) + "sin(x)";
                        answer_4 = "-" + Integer.toString(constant_1 * constant_2) + "cos(" + Integer.toString(constant_2) + "x)";
                        return;
                    } else if (randomCorrect == 2) {
                        answer_2 = correct;
                        answer_3 = Integer.toString(constant_1 * constant_2) + "sin(" + Integer.toString(constant_2) + "x)";
                        answer_4 = "-" + Integer.toString(constant_1 * constant_2) + "sin(x)";
                        answer_1 = "-" + Integer.toString(constant_1 * constant_2) + "cos(" + Integer.toString(constant_2) + "x)";
                        return;
                    } else if (randomCorrect == 3) {
                        answer_3 = correct;
                        answer_1 = Integer.toString(constant_1 * constant_2) + "sin(" + Integer.toString(constant_2) + "x)";
                        answer_4 = "-" + Integer.toString(constant_1 * constant_2) + "sin(x)";
                        answer_2 = "-" + Integer.toString(constant_1 * constant_2) + "cos(" + Integer.toString(constant_2) + "x)";
                        return;
                    } else {
                        answer_4 = correct;
                        answer_3 = Integer.toString(constant_1 * constant_2) + "sin(" + Integer.toString(constant_2) + "x)";
                        answer_1 = "-" + Integer.toString(constant_1 * constant_2) + "sin(x)";
                        answer_2 = "-" + Integer.toString(constant_1 * constant_2) + "cos(" + Integer.toString(constant_2) + "x)";
                        return;
                    }
                }
            }
        }
    }
////////////////////////////////////////////////////////////////////// GET DIFFERENTIATION FUNCTION ENDS HERE /////////////////////////////////////////////////////////////////////////

    private void getIntQuestion(int difficulty) {
        int constant;
        int exponent;
        int randomCorrect;
        int IntClass;
        randomCorrect = getRandomInt(1,4);
        IntClass = getRandomInt(0,3);

        if (IntClass == 0 || IntClass == 3) {
            if (difficulty == 1) {
                if (randomCorrect == 1) {
                    exponent = getRandomInt(1, 5);

                    question = "Integrate: \n" + "x^" + Integer.toString(exponent);
                    correct = "(1/" + Integer.toString(exponent + 1) + ")x^" + Integer.toString(exponent + 1);

                    answer_1 = correct;
                    answer_2 = Integer.toString(exponent) + "x^" + Integer.toString(exponent - 1);
                    answer_3 = "x^" + Integer.toString(exponent + 1);
                    answer_4 = "(1/" + Integer.toString(exponent) + ")x^" + Integer.toString(exponent + 1);
                }
                if (randomCorrect == 2) {
                    exponent = getRandomInt(1, 5);

                    question = "Integrate: \n" + "x^" + Integer.toString(exponent);
                    correct = "(1/" + Integer.toString(exponent + 1) + ")x^" + Integer.toString(exponent + 1);

                    answer_2 = correct;
                    answer_3 = Integer.toString(exponent) + "x^" + Integer.toString(exponent - 1);
                    answer_4 = "x^" + Integer.toString(exponent + 1);
                    answer_1 = "(1/" + Integer.toString(exponent) + ")x^" + Integer.toString(exponent + 1);
                }
                if (randomCorrect == 3) {
                    exponent = getRandomInt(1, 5);

                    question = "Integrate: \n" + "x^" + Integer.toString(exponent);
                    correct = "(1/" + Integer.toString(exponent + 1) + ")x^" + Integer.toString(exponent + 1);

                    answer_3 = correct;
                    answer_4 = Integer.toString(exponent) + "x^" + Integer.toString(exponent - 1);
                    answer_1 = "x^" + Integer.toString(exponent + 1);
                    answer_2 = "(1/" + Integer.toString(exponent) + ")x^" + Integer.toString(exponent + 1);
                }
                if (randomCorrect == 4) {
                    exponent = getRandomInt(1, 5);

                    question = "Integrate: \n" + "x^" + Integer.toString(exponent);
                    correct = "(1/" + Integer.toString(exponent + 1) + ")x^" + Integer.toString(exponent + 1);

                    answer_4 = correct;
                    answer_1 = Integer.toString(exponent) + "x^" + Integer.toString(exponent - 1);
                    answer_2 = "x^" + Integer.toString(exponent + 1);
                    answer_3 = "(1/" + Integer.toString(exponent) + ")x^" + Integer.toString(exponent + 1);
                }
            } else if (difficulty == 2) {
                if (randomCorrect == 1) {
                    constant = getRandomInt(1, 3);
                    exponent = getRandomInt(1, 5);
                    question = "Integrate: \n" + Integer.toString(constant) + "x^" + Integer.toString(exponent);
                    correct = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent + 1) + ")x^" + Integer.toString(exponent + 1);

                    answer_1 = correct;
                    answer_2 = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent + 1);
                    answer_3 = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent - 1);
                    answer_4 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent) + ")x^" + Integer.toString(exponent);

                }
                if (randomCorrect == 2) {
                    constant = getRandomInt(1, 3);
                    exponent = getRandomInt(1, 5);
                    question = "Integrate: \n" + Integer.toString(constant) + "x^" + Integer.toString(exponent);
                    correct = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent + 1) + ")x^" + Integer.toString(exponent + 1);

                    answer_2 = correct;
                    answer_3 = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent + 1);
                    answer_4 = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent - 1);
                    answer_1 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent) + ")x^" + Integer.toString(exponent);

                }
                if (randomCorrect == 3) {
                    constant = getRandomInt(1, 3);
                    exponent = getRandomInt(1, 5);
                    question = "Integrate: \n" + Integer.toString(constant) + "x^" + Integer.toString(exponent);
                    correct = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent + 1) + ")x^" + Integer.toString(exponent + 1);

                    answer_3 = correct;
                    answer_4 = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent + 1);
                    answer_1 = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent - 1);
                    answer_2 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent) + ")x^" + Integer.toString(exponent);

                }
                if (randomCorrect == 4) {
                    constant = getRandomInt(1, 3);
                    exponent = getRandomInt(1, 5);
                    question = "Integrate: \n" + Integer.toString(constant) + "x^" + Integer.toString(exponent);
                    correct = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent + 1) + ")x^" + Integer.toString(exponent + 1);

                    answer_4 = correct;
                    answer_1 = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent + 1);
                    answer_2 = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent - 1);
                    answer_3 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent) + ")x^" + Integer.toString(exponent);

                }
            } else if (difficulty == 3) {
                if (randomCorrect == 1) {
                    constant = getRandomInt(1, 13);
                    exponent = getRandomInt(-6, -3);

                    question = "Integrate: \n" + Integer.toString(constant) + "/x^" + Integer.toString(-1 * exponent);
                    correct = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent + 1) + ")x^" + Integer.toString(exponent + 1);

                    answer_1 = correct;
                    answer_2 = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent + 1);
                    answer_3 = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent - 1);
                    answer_4 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent) + ")x^" + Integer.toString(exponent);

                }
                if (randomCorrect == 2) {
                    constant = getRandomInt(1, 13);
                    exponent = getRandomInt(-6, -3);

                    question = "Integrate: \n" + Integer.toString(constant) + "/x^" + Integer.toString(-1 * exponent);
                    correct = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent + 1) + ")x^" + Integer.toString(exponent + 1);

                    answer_2 = correct;
                    answer_3 = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent + 1);
                    answer_4 = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent - 1);
                    answer_1 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent) + ")x^" + Integer.toString(exponent);

                }
                if (randomCorrect == 3) {
                    constant = getRandomInt(1, 13);
                    exponent = getRandomInt(-6, -3);

                    question = "Integrate: \n" + Integer.toString(constant) + "/x^" + Integer.toString(-1 * exponent);
                    correct = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent + 1) + ")x^" + Integer.toString(exponent + 1);

                    answer_3 = correct;
                    answer_4 = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent + 1);
                    answer_1 = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent - 1);
                    answer_2 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent) + ")x^" + Integer.toString(exponent);

                }
                if (randomCorrect == 4) {
                    constant = getRandomInt(1, 13);
                    exponent = getRandomInt(-6, -3);

                    question = "Integrate: \n" + Integer.toString(constant) + "/x^" + Integer.toString(-1 * exponent);
                    correct = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent + 1) + ")x^" + Integer.toString(exponent + 1);

                    answer_4 = correct;
                    answer_1 = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent + 1);
                    answer_2 = Integer.toString(constant * exponent) + "x^" + Integer.toString(exponent - 1);
                    answer_3 = "(" + Integer.toString(constant) + "/" + Integer.toString(exponent) + ")x^" + Integer.toString(exponent);

                }
            }
        } else if (IntClass == 1) {
            if (difficulty == 1) {
                question = "Integrate: \n e^x";
                correct = "e^x";

                if (randomCorrect == 1) {
                    answer_1 = correct;
                    answer_2 = "xe^x";
                    answer_3 = "1/x";
                    answer_4 = "(x)e^x-1";
                    return;
                }

                if (randomCorrect == 2) {
                    answer_2 = correct;

                    answer_3 = "1/x";
                    answer_4 = "xe^x";
                    answer_1 = "(1/x)e^x";
                    return;
                }

                if (randomCorrect == 3) {
                    answer_3 = correct;
                    answer_4 = "1/x";
                    answer_1 = "xe^x";
                    answer_2 = "xe^x-1";
                    return;
                }

                if (randomCorrect == 4) {
                    answer_4 = correct;
                    answer_1 = "1/x";
                    answer_2 = "xe^x";
                    answer_3 = "(1/x)e^x";
                    return;
                }
            } else if (difficulty == 2) {
                constant = getRandomInt(2, 5);
                String s_constant = Integer.toString(constant);
                question = "Integrate: \n" + s_constant + "e^x";
                correct = s_constant + "e^x";

                if (randomCorrect == 1) {
                    answer_1 = correct;
                    answer_2 = s_constant + "xe^x";
                    answer_3 = s_constant + "/x";
                    answer_4 = "(" + s_constant + "/x" + ")e^x";
                    return;
                } else if (randomCorrect == 2) {
                    answer_2 = correct;
                    answer_3 = s_constant + "xe^x";
                    answer_4 = s_constant + "/x";
                    answer_1 = "(" + s_constant + "/x" + ")e^x";
                    return;
                } else if (randomCorrect == 3) {
                    answer_3 = correct;
                    answer_4 = s_constant + "xe^x";
                    answer_1 = s_constant + "/x";
                    answer_2 = "(" + s_constant + "/x" + ")e^x";
                    return;
                } else {
                    answer_4 = correct;
                    answer_1 = s_constant + "xe^x";
                    answer_2 = s_constant + "/x";
                    answer_3 = "(" + s_constant + "/x" + ")e^x";
                    return;
                }
            } else {
                int constant_1 = getRandomInt(2,5);
                int constant_2 = getRandomInt(3,6);

                question = "Integrate: \n" + Integer.toString(constant_1) + "e^" + Integer.toString(constant_2) + "x";
                correct  = "(" + Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")e^" + Integer.toString(constant_2) + "x";

                if (randomCorrect == 1) {
                    answer_1 = correct;
                    answer_2 = Integer.toString(constant_1 * constant_2) + "e^" + Integer.toString(constant_2) + "x";
                    answer_3 = "(" + Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")e^x";
                    answer_4 = Integer.toString(constant_1 * constant_2) + "e^x";
                    return;
                } else if (randomCorrect == 2) {
                    answer_2 = correct;
                    answer_3 = Integer.toString(constant_1 * constant_2) + "e^" + Integer.toString(constant_2) + "x";
                    answer_1 = "(" + Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")e^x";
                    answer_4 = Integer.toString(constant_1 * constant_2) + "e^x";
                    return;
                } else if (randomCorrect == 3) {
                    answer_3 = correct;
                    answer_2 = Integer.toString(constant_1 * constant_2) + "e^" + Integer.toString(constant_2) + "x";
                    answer_4 = "(" + Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")e^x";
                    answer_1 = Integer.toString(constant_1 * constant_2) + "e^x";
                } else {
                    answer_4 = correct;
                    answer_3 = Integer.toString(constant_1 * constant_2) + "e^" + Integer.toString(constant_2) + "x";
                    answer_2 = "(" + Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")e^x";
                    answer_1 = Integer.toString(constant_1 * constant_2) + "e^x";
                }
            }
        } else {
            // Trigonometry
            int TrigBase = getRandomInt(0,1);
            if (TrigBase == 0) {
                if (difficulty == 1) {
                    question = "Integrate: \n" + "sin(x)";
                    correct = "-cos(x)";

                    if (randomCorrect == 1) {
                        answer_1 = correct;
                        answer_2 = "cos(x)";
                        answer_3 = "-sin(x)";
                        answer_4 = "sin(x)";
                        return;
                    } else if (randomCorrect == 2) {
                        answer_2 = correct;
                        answer_3 = "cos(x)";
                        answer_1 = "-sin(x)";
                        answer_4 = "sin(x)";
                        return;
                    } else if (randomCorrect == 3) {
                        answer_3 = correct;
                        answer_4 = "cos(x)";
                        answer_1 = "-sin(x)";
                        answer_2 = "sin(x)";
                        return;
                    } else {
                        answer_4 = correct;
                        answer_2 = "cos(x)";
                        answer_1 = "-sin(x)";
                        answer_3 = "sin(x)";
                        return;
                    }
                } else if (difficulty == 2) {
                    String s_constant = Integer.toString(getRandomInt(2,5));
                    question = "Integrate: \n" + s_constant + "sin(x)";
                    correct = "-" + s_constant + "cos(x)";

                    if (randomCorrect == 1) {
                        answer_1 = correct;
                        answer_3 = s_constant + "cos(x)";
                        answer_4 = "-" + s_constant + "sin(x)";
                        answer_2 = s_constant + "sin(x)";
                        return;
                    } else if (randomCorrect == 2) {
                        answer_2 = correct;
                        answer_3 = s_constant + "cos(x)";
                        answer_1 = "-" + s_constant + "sin(x)";
                        answer_4 = s_constant + "sin(x)";
                        return;
                    } else if (randomCorrect == 3) {
                        answer_3 = correct;
                        answer_4 = s_constant + "cos(x)";
                        answer_2 = "-" + s_constant + "sin(x)";
                        answer_1 = s_constant + "sin(x)";
                        return;
                    } else {
                        answer_4 = correct;
                        answer_3 = s_constant + "cos(x)";
                        answer_1 = "-" + s_constant + "sin(x)";
                        answer_2 = s_constant + "sin(x)";
                        return;
                    }
                } else {
                    int constant_1 = getRandomInt(2,5);
                    int constant_2 = getRandomInt(2,6);

                    question = "Integrate: \n" + Integer.toString(constant_1) + "sin(" + Integer.toString(constant_2) + "x)";
                    correct = "(-" + Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")cos(" + Integer.toString(constant_2) + "x)";

                    if (randomCorrect == 1) {
                        answer_1 = correct;
                        answer_3 = "(" + Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")cos(" + Integer.toString(constant_2) + "x)";
                        answer_2 = "(-" + Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")cos(x)";
                        answer_4 = "-" + Integer.toString(constant_1 * constant_2) + "cos(" + Integer.toString(constant_2)+ "x)";
                        return;
                    } else if (randomCorrect == 2) {
                        answer_2 = correct;
                        answer_4 = Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")cos(" + Integer.toString(constant_2) + "x)";
                        answer_1 = "(-" + Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")cos(x)";
                        answer_3 = "-" + Integer.toString(constant_1 * constant_2) + "cos(" + Integer.toString(constant_2)+ "x)";
                        return;
                    } else if (randomCorrect == 3) {
                        answer_3 = correct;
                        answer_1 = Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")cos(" + Integer.toString(constant_2) + "x)";
                        answer_2 = "(-" + Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")cos(x)";
                        answer_4 = "-" + Integer.toString(constant_1 * constant_2) + "cos(" + Integer.toString(constant_2)+ "x)";
                        return;
                    } else {
                        answer_4 = correct;
                        answer_3 = Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")cos(" + Integer.toString(constant_2) + "x)";
                        answer_1 = "(-" + Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")cos(x)";
                        answer_2 = "-" + Integer.toString(constant_1 * constant_2) + "cos(" + Integer.toString(constant_2)+ "x)";
                        return;
                    }
                }
            } else {
                // Cosine base
                if (difficulty == 1) {
                    question = "Integrate: \n" + "cos(x)";
                    correct = "sin(x)";

                    if (randomCorrect == 1) {
                        answer_1 = correct;
                        answer_2 = "-cos(x)";
                        answer_3 = "-sin(x)";
                        answer_4 = "cos(x)";
                        return;
                    } else if (randomCorrect == 2) {
                        answer_2 = correct;
                        answer_3 = "-cos(x)";
                        answer_1 = "-sin(x)";
                        answer_4 = "cos(x)";
                        return;
                    } else if (randomCorrect == 3) {
                        answer_3 = correct;
                        answer_1 = "-cos(x)";
                        answer_4 = "-sin(x)";
                        answer_2 = "cos(x)";
                        return;
                    } else {
                        answer_4 = correct;
                        answer_1 = "-cos(x)";
                        answer_2 = "-sin(x)";
                        answer_3 = "cos(x)";
                        return;
                    }
                } else if (difficulty == 2) {
                    String s_constant = Integer.toString(getRandomInt(2,5));
                    question = "Integrate: \n" + s_constant + "cos(x)";
                    correct = s_constant + "sin(x)";

                    if (randomCorrect == 1) {
                        answer_1 = correct;
                        answer_4 = "-" + s_constant + "cos(x)";
                        answer_2 = "-" + s_constant + "sin(x)";
                        answer_3 = s_constant + "cos(x)";
                        return;
                    } else if (randomCorrect == 2) {
                        answer_2 = correct;
                        answer_1 = "-" + s_constant + "cos(x)";
                        answer_4 = "-" + s_constant + "sin(x)";
                        answer_3 = s_constant + "cos(x)";
                        return;
                    } else if (randomCorrect == 3) {
                        answer_3 = correct;
                        answer_4 = "-" + s_constant + "cos(x)";
                        answer_1 = "-" + s_constant + "sin(x)";
                        answer_2 = s_constant + "cos(x)";
                        return;
                    } else {
                        answer_4 = correct;
                        answer_1 = "-" + s_constant + "cos(x)";
                        answer_3 = "-" + s_constant + "sin(x)";
                        answer_2 = s_constant + "cos(x)";
                        return;
                    }
                } else {
                    int constant_1 = getRandomInt(2,5);
                    int constant_2 = getRandomInt(2,6);

                    question = "Integrate: \n" + Integer.toString(constant_1) + "cos(" + Integer.toString(constant_2) + "x)";
                    correct = "(" + Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")sin(" + Integer.toString(constant_2) + "x)";

                    if (randomCorrect == 1) {
                        answer_1 = correct;
                        answer_3 ="(" + Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")cos(" + Integer.toString(constant_2) + "x)";
                        answer_2 = "(" + Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")sin(x)";
                        answer_4 = Integer.toString(constant_1 * constant_2) + "sin(" + Integer.toString(constant_2)+ "x)";
                        return;
                    } else if (randomCorrect == 2) {
                        answer_2 = correct;
                        answer_4 = Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")cos(" + Integer.toString(constant_2) + "x)";
                        answer_1 = "(-" + Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")cos(x)";
                        answer_3 = "-" + Integer.toString(constant_1 * constant_2) + "cos(" + Integer.toString(constant_2)+ "x)";
                        return;
                    } else if (randomCorrect == 3) {
                        answer_3 = correct;
                        answer_1 = Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")cos(" + Integer.toString(constant_2) + "x)";
                        answer_2 = "(-" + Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")cos(x)";
                        answer_4 = "-" + Integer.toString(constant_1 * constant_2) + "cos(" + Integer.toString(constant_2)+ "x)";
                        return;
                    } else {
                        answer_4 = correct;
                        answer_3 = Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")cos(" + Integer.toString(constant_2) + "x)";
                        answer_1 = "(-" + Integer.toString(constant_1) + "/" + Integer.toString(constant_2) + ")cos(x)";
                        answer_2 = "-" + Integer.toString(constant_1 * constant_2) + "cos(" + Integer.toString(constant_2)+ "x)";
                        return;
                    }
                }
            }
        }
    }



    private int getRandomInt(int min,int max) {
        Random randominteger = new Random();
        return randominteger.nextInt((max - min) + 1) + min;
    }
}

