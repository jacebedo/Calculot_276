package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

import java.util.Random;

public class VectorQuestionGenerator {    //random question generator for the Crystal Ball Game
    /* responsible for receiving the difficulty (Acts as the topic): Easy, Medium, Hard upon game start (decided on previous activity)
        also receives the player level in each difficulty setting

        responsible for generating questionvector and clockvector as well as generating the correct/random answers

        medium is locked until player reached level X in easy
        hard is locked until player reaches level X in both easy and medium

        In each difficulty:
            levels represent current question level (randomly generated) and increment by 1 if completed successfully
            player may progress through levels 1 to N as we alternate between introducing new structures to the questions and being tested on old question forms
            If the player progresses past level N then they will be practicing questions with forms already introduced. No limit on level number.
            Note: each level involves user filling up crystal ball
     */

    //fields
    /*(User sees Topic as the difficulty setting)
    0 Easy - only vectors/complex #s using pythagorean theorem,

    1 Medium - find a component of a vector or the real/imaginary part of the complex # which describes the question vector,

    2 Hard - find the vector v=(x,y) which matches the question vector -> user needs to find both x,y components of the vector or the x+iy parts of a complex #
        Phase A may have both components generated from the Easy section
        Phase B may have one component from the Easy section and the other from the Medium section
        Phase C may be either a more complex Phase 2 question or have both components from the Medium section
        (Phases represent a range of levels)
        As hardlevel increases -> user progresses through Phases from A to C
    */
    int Topic;
    double ScoreMultiplier = 1;    //multiplier bonus for increasing level difficulty/difficulty setting+phase
    int EasyLevel;
    int MediumLevel;
    int HardLevel;

    int HalfWidth = 250;    //set as the x origin of the grid
    int HalfHeight = 250;   //set as the y origin of the grid
    int MinimumAbsX = 50;   //minimum absolute x value of the vector
    int MinimumAbsY = 50;   //minimum absolute y value of the vector

    final int PhaseALastLevel = 5;
    final int PhaseBLastLevel = 10;
    //PhaseC has no last level

    //View - Question, info and answers
    String Question;
    String QuestionInfo;
    String[] AnswerArray;
    int AnswerArrayIndex;   //Which one is the right answer?
    boolean IsComplex;

    //Vector components
    String XComponent;
    String YComponent;  //if "i" is included then Y is imaginary
    String ThetaComponent;
    String NormComponent;

    //my children
    CrystalBall crystalBall = new CrystalBall(4);   //max shell level currently 4
    QuestionVector questionVector;
    ClockVector clockVector;

    //Constructor
    public VectorQuestionGenerator(int _difficulty, int _easylevel, int _mediumlevel, int _hardlevel) {
        Topic = _difficulty;
        EasyLevel = _easylevel;
        MediumLevel = _mediumlevel;
        HardLevel = _hardlevel;

        generateQuestion(); //generate the first question
    }

    //private methods
    private boolean generateRandomBoolean() {
        //create random boolean static class?
        Random Rand = new Random();
        boolean RandomBoolean = Rand.nextBoolean();

        return RandomBoolean;
    }

    //Borrowed from CalcQuestion - create static class with public random methods?
    private int getRandomInt(int min,int max) {
        Random randominteger = new Random();
        return randominteger.nextInt((max - min) + 1) + min;
    }

    //generate questions
    private void generateQuestion() {
        if (Topic == 0) {
            ScoreMultiplier = 1 + (EasyLevel)/20;

            generateEasyQuestion();
        }
        else if (Topic == 1) {
            ScoreMultiplier = 1.5 + (MediumLevel)/10;

            generateMediumQuestion();
        }
        else {  //Topic == 2
            ScoreMultiplier = 2 + (HardLevel)/5;

            generateHardQuestion();
        }
    }

    private void generateEasyQuestion() {
        //implement
        IsComplex = generateRandomBoolean();
        int RandomChoice = getRandomInt(0,2);   //decides if user should find norm, x, or y

        int XComponent = generateRandomX();
        int YComponent = generateRandomY();

        if (RandomChoice == 0) {
            //find norm

        }
        else if (RandomChoice == 1) {
            //find x
        }
        else {
            //find y
        }

        questionVector = new QuestionVector(XComponent, YComponent);
    }

    private void generateMediumQuestion() {
        //implement
    }

    private void generateHardQuestion() {
        //implement
    }

    //Generating components of QuestionVector
    private int generateRandomX() {
        int RandomX = getRandomInt(MinimumAbsX, HalfWidth);
        if (generateRandomBoolean()) {
            RandomX *= -1;
        }
        XComponent = String.valueOf(RandomX);

        return RandomX;
    }

    private int generateRandomY() {
        int RandomY = getRandomInt(MinimumAbsY, HalfHeight);
        if (generateRandomBoolean()) {
            RandomY *= -1;
        }
        YComponent = String.valueOf(RandomY);
        if (IsComplex) {
            if (generateRandomBoolean())
                YComponent = "i* " + YComponent;
            else
                YComponent = YComponent + " *i";
        }

        return RandomY;
    }

    //public methods
}