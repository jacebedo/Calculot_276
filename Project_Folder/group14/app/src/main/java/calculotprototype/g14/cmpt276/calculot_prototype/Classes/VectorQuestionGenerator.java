package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

import java.util.Arrays;
import java.util.Random;

public class VectorQuestionGenerator {
    //random question generator for the Crystal Ball Game
    /* responsible for receiving the difficulty (Acts as the Topic): Easy, Medium, Hard upon game start (decided on previous activity)
        also receives the player level in each difficulty setting

        responsible for generating questionvector and clockvector as well as generating the correct/random answers

        medium is locked until player reached level X in easy
        hard is locked until player reaches level X in both easy and medium

        In each Topic:
            levels represent current question level (randomly generated) and increment by 1 if completed successfully
            player may progress through levels 1 to N as we alternate between introducing new structures to the questions and being tested on old question forms
            If the player progresses past level N then they will be practicing questions with forms already introduced. No limit on level number.
            Note: each level involves user filling up crystal ball

        Actual difficulty level decided by EasyLevel/MediumLevel/HardLevel for each Topic
            Increase difficulty by: speeding up clock vector, increasing number of choices, complexity of component compositions, increasing stages per level?
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
        Phase D is the end stage of the hard levels - introduce "extreme" variability in the composition of answers
            ie. by displaying components as compositions of numbers ie. 50 may be shown as "(5*10)" or "(37+13)" in the answers
            Ways to increase Component complexity:
                addition, subtraction, multiplication, division -> avoid combining multiple operations to produce a component expression
                as it may change the focus from the learned material too much as calculations become more difficult under time pressure
        As hardlevel increases -> user progresses through Phases from A to D
    */
    //Fields
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
    final int PhaseCLastLevel = 20;
    //PhaseD has no last level

    //View - Question, info and answers
    String Question;
    String QuestionInfo;
    String[] AnswerArray;
    int AnswerArrayIndex;   //Which one is the right answer?
    boolean QuestionComplex;
    boolean AnswerComplex;
    boolean QuestionPolar;
    boolean AnswerPolar;
    int AnswerArraySize;    //# of choices changes depending on difficulty?

    //Vector components
    String XComponent;
    String YComponent;  //y component without "i" regardless if imaginary
    String iYComponent; //if "i" is included then Y is imaginary
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

    //Private Methods
    //Utility Methods
    private boolean generateRandomBoolean() {
        //create random boolean static class?
        Random Rand = new Random();
        return Rand.nextBoolean();
    }

    private String toComplex(String _real) {    //turns a string component into an imaginary number string if vector is complex
        String Complex = _real;

        if (AnswerComplex) {
            if (generateRandomBoolean())
                Complex = "i* " + Complex;
            else
                Complex = Complex + " *i";
        }
        return Complex;
    }

    private String applyTrig(String _string, int _functiontype) {
        String Result = _string;
        switch (_functiontype) {
            case 0:
                Result = "Sin( "+_string+" )";
                break;
            case 1:
                Result = "Cos( "+_string+" )";
                break;
            case 2:
                Result = "Tan( "+_string+" )";
                break;
            case 10:
                Result = "Arcsin( "+_string+" )";
                break;
            case 11:
                Result = "Arccos( "+_string+" )";
                break;
            case 12:
                Result = "Arctan( "+_string+" )";
                break;
        }
        return Result;
    }

    private String applyScalar(String _string, int _scalar) {
        if (_scalar == 1)   //enclose in brackets
            return "("+_string+")";
        if (_scalar == -1)  //random sign -> pull number if not a composition and multiply by -1 turning int back to string
            return "-("+_string+")";
        else
        return "("+_scalar+")( "+_string+" )";
    }

    private String applyPower(String _string, int _powernumerator, int _powerdenominator, boolean _reform, boolean _iswrong) {  //implement iswrong boolean to change power (use !decideComplexity on functioncall) use applyscalar to vary powers
        int FractionNumerator = _powernumerator;
        int FractionDenominator = _powerdenominator;
        int RandomScalar = 1;

        if (_reform) {  //reform complexity level?
            if (generateRandomBoolean())
                RandomScalar = getRandomInt(1,4);
            else RandomScalar = 10 * getRandomInt(1,2);

            FractionNumerator *= RandomScalar;
            FractionDenominator *= RandomScalar;
        }

        if (FractionNumerator == 1 && FractionDenominator == 1 && _reform == false) //if we do not reform ^1 simply return the string in its original state
            return _string;
        if (FractionDenominator == 1 && !_reform)   //if reform is true we may have x^(y/1) to vary the answers a bit more -> also allow decimal exponents
            return "( "+_string+" )^("+FractionNumerator+") ";
        else if (FractionNumerator == 1 && FractionDenominator == 2 && !_reform)
            return "sqrt( "+_string+" )";
        else if (_reform && generateRandomBoolean())
            return "( "+_string+" )^("+(float) FractionNumerator/FractionDenominator+") ";
        else
            return "( "+_string+" )^("+FractionNumerator+"/"+FractionDenominator+") ";
    }

    private String complexComposition(String _number, int _complexity) {
        int Number = Integer.parseInt(_number);
        //implement

        return Integer.toString(Number);
    }

    private String commutativeOperation(String _stringa, String _stringb, char _operation) {
        String Result = "";
        int Choice;

        if (_operation == '+') {
            if (generateRandomBoolean())
                Result = _stringa+" + "+_stringb;
            else Result = _stringb+" + "+_stringa;
        }
        else if (_operation == '-') {
            if (generateRandomBoolean())
                Result = _stringa+" - "+_stringb;
            else Result = " - "+_stringb+" + "+_stringa;
        }
        else if (_operation == '*') {
            Choice = getRandomInt(0,3);
            switch (Choice) {
                case 0:
                    Result = _stringa+" * "+_stringb;
                    break;
                case 1:
                    Result = _stringb+" * "+_stringa;
                    break;
                case 2:
                    Result = "("+_stringa+")( "+_stringb+")";
                    break;
                case 3:
                    Result = "("+_stringb+")("+_stringa+")";
                    break;
            }
        }
        else if (_operation == '/') {
            Choice = getRandomInt(0,2);
            switch (Choice) {
                case 0:
                    Result = _stringa+" / ("+_stringb+")";
                    break;
                case 1:
                    Result = "(1/("+_stringb+")) * "+_stringa;
                    break;
                case 2:
                    Result = "(1/("+_stringb+"))("+_stringa+")";    //too many brackets? :P
                    break;
                case 3:
                    Result = applyPower(_stringb, -1, 1, true, false); //use decideComplexity?
                    Result = commutativeOperation(_stringa, _stringb, '*'); //recursive call to the '*' case
                    break;
            }
        }
        return Result;
    }

    private char randomCharOperation(char _notchar) {
        int Random;
        char Operation = _notchar;

        while (Operation == _notchar) {
            Random = getRandomInt(0,3);
            if (Random == 0)
                Operation = '+';
            else if (Random == 1)
                Operation = '-';
            else if (Random == 2)
                Operation = '*';
            else Operation = '/';   //Random == 3
        }
        return Operation;
    }

    private int randomTrigOperation(int _nottrig) {
        int Random;
        int Operation = _nottrig;

        while (Operation == _nottrig) {
            Operation = getRandomInt(0,2);
            if (generateRandomBoolean())
                Operation += 10;
        }
        return Operation;
    }

    private boolean decideComplexity(int _level, int _componentnumber) {  //some way to decide if we increase complexity or not
        //level may be easylevel, mediumlevel, or hardlevel
        // componentnumber-1 represents the amount of changes we have already applied
        return getRandomInt( (int)(_level*0.5) , _level+10)>(_componentnumber*2);
    }

    private String findAnswer(int _type) {
        String FindAnswer = "";

        if (_type == 0 || _type == 8 || _type == 10) {
            //find radius/norm/modulus
            if (AnswerPolar)
                FindAnswer = "Find the Radius r of the vector in polar form (r, theta)";
            else if (AnswerComplex)
                FindAnswer = "Find the Modulus |c| of the complex number c = Re+Im";
            else FindAnswer = "Find the Norm ||v|| of the vector v = (x,y)";
        }
        else if (_type == 2 || _type == 7 || _type == 11) {
            //find Im/y
            if (AnswerComplex)
                FindAnswer = "Find the Imaginary Component of the complex number c = Re+Im";
            else FindAnswer = "Find the Y Component of the vector v = (x,y)";
        }
        else if (_type == 4 || _type == 6 || _type == 9) {
            //find Re/x
            if (AnswerComplex)
                FindAnswer = "Find the Real Component of the complex number c = Re+Im";
            else FindAnswer = "Find the X Component of the vector v = (x,y)";
        }
        else {  //_type == 1, 3, 5
            //find theta
            FindAnswer = "Find the angle Theta of the vector in polar form (r, theta)";
        }
        return FindAnswer;
    }

    //Borrowed from CalcQuestion - create static class with public random methods?
    private int getRandomInt(int min,int max) {
        Random RandomInteger = new Random();
        return RandomInteger.nextInt((max - min) + 1) + min;
    }

    //Generate Questions
    private void generateQuestion() {

        if (Topic == 0) {
            ScoreMultiplier = 1 + (EasyLevel)/20;

            AnswerArraySize = Math.max(getRandomInt(2, EasyLevel),5);
            generateEasyQuestion();
        }
        else if (Topic == 1) {
            ScoreMultiplier = 1.5 + (MediumLevel)/10;

            AnswerArraySize = Math.max(getRandomInt(2, MediumLevel),5);
            generateMediumQuestion();
        }
        else {  //Topic == 2
            ScoreMultiplier = 2 + (HardLevel)/5;

            AnswerArraySize = Math.max(getRandomInt(2, HardLevel),5);
            generateHardQuestion();
        }
    }

    private void generateEasyQuestion() {
        //implement
        QuestionComplex = generateRandomBoolean();
        AnswerComplex = QuestionComplex;
        QuestionPolar = false;
        AnswerPolar = false;

        int RandomChoice = getRandomInt(0,2);   //decides if user should find norm, x, or y

        XComponent = Integer.toString(generateRandomX());
        YComponent = Integer.toString(generateRandomY());

        if (RandomChoice == 0) {
            //find norm     given x and y
            if (AnswerComplex) {
                Question = "Find the modulus of the complex number c = Re+Im (Shown as a vector)";     //currently hardcoded -> we may use strings.xml or database instead
                QuestionInfo = "given Re = "+XComponent+", Im = "+iYComponent;
            }
            else {
                Question = "Find the norm of the vector v = (x,y)";     //currently hardcoded -> we may use strings.xml or database instead
                QuestionInfo = "given x = "+XComponent+", y = "+YComponent;
            }
        }
        else if (RandomChoice == 1) {
            //find x    given norm and y
            if (AnswerComplex) {
                Question = "Find the real part of the complex number c = Re+Im (Shown as a vector)";
                QuestionInfo = "given modulus = "+NormComponent+", Im = "+iYComponent;
            }
            else {
                Question = "Find the x component of the vector v = (x,y)";
                QuestionInfo = "given ||v|| = "+NormComponent+", y = "+YComponent;
            }
        }
        else {
            //find y    given norm and x
            if (AnswerComplex) {
                Question = "Find the Imaginary part of the complex number c = Re+Im (Shown as a vector)";
                QuestionInfo = "given modulus = "+NormComponent+", Re = "+XComponent;
            }
            else {
                Question = "Find the y component of the vector v = (x,y)";
                QuestionInfo = "given ||v|| = "+NormComponent+", x = "+XComponent;
            }
        }

        generateEasyAnswerArray(RandomChoice);
        questionVector = new QuestionVector( Integer.parseInt(XComponent), Integer.parseInt(YComponent));
    }

    private void generateMediumQuestion() {
        //method is long -> refactor?
        int QuestionType = getRandomInt(0,11); //range from 0-11
        int TempRandom;

        if (QuestionType <= 5) {
            QuestionPolar = false;
            QuestionComplex = generateRandomBoolean();

            if (QuestionType % 2 == 1) {    //questiontype = 1, 3, 5 -> find theta
                AnswerPolar = true;
                AnswerComplex = false;
            }
            else {  //questiontype 0, 2, 4 -> find Re/x, Im/y, radius/norm/modulus
                TempRandom = getRandomInt(0,2); // 1/3 chance to find radius, norm, or modulus -> turn into method?
                switch (TempRandom) {
                    case 0:
                        AnswerPolar = true;
                        AnswerComplex = false;
                        break;
                    case 1:
                        AnswerPolar = false;
                        AnswerComplex = false;
                        break;
                    case 2:
                        AnswerPolar = false;
                        AnswerComplex = true;
                }
            }
        }
        else {
            //QuestionType 6-11
            QuestionPolar = true;

            if (QuestionType == 6 || QuestionType == 7) {
                QuestionComplex = false;    //radius is given
                AnswerPolar = false;
                AnswerComplex = generateRandomBoolean();
            }
            else {
                QuestionComplex = generateRandomBoolean();
                if (QuestionType == 8 || QuestionType == 10) {
                    TempRandom = getRandomInt(0,2); // 1/3 chance to find radius, norm, or modulus
                    switch (TempRandom) {
                        case 0:
                            AnswerPolar = true;
                            AnswerComplex = false;
                            break;
                        case 1:
                            AnswerPolar = false;
                            AnswerComplex = false;
                            break;
                        case 2:
                            AnswerPolar = false;
                            AnswerComplex = true;
                    }
                }
                else {
                    //questiontype 9, 11
                    AnswerPolar = false;
                    AnswerComplex = generateRandomBoolean();
                }
            }
        }

        XComponent = Integer.toString(generateRandomX());
        YComponent = Integer.toString(generateRandomY());

        //Ask Question
        Question = findAnswer(QuestionType);

        if (QuestionType <= 1) {
            //given Re/x,Im/y find radius/norm/modulus or theta
            if (QuestionComplex)
                QuestionInfo = "given the Real Component = "+XComponent+", Imaginary Component = "+iYComponent;
            else QuestionInfo = "given X = "+XComponent+", Y = "+YComponent;
        }
        else if (QuestionType <= 3) {
            //given modulus/norm, Re/x find Im/y or theta
            if (QuestionComplex)
                QuestionInfo = "given modulus = "+NormComponent+", Re = "+XComponent;
            else QuestionInfo = "given ||v|| = "+NormComponent+", X = "+XComponent;
        }
        else if (QuestionType <= 5) {
            //given modulus/norm, Im/y find Re/x or theta
            if (QuestionComplex)
                QuestionInfo = "given modulus = "+NormComponent+", Im = "+iYComponent;
            else QuestionInfo = "given ||v|| = "+NormComponent+", Y = "+YComponent;
        }
        else if (QuestionType <= 7) {
            //given theta, radius (norm/modulus?) find Re/x or Im/y
            //if (QuestionComplex)
                QuestionInfo = "given Theta = "+ThetaComponent+", Radius = "+NormComponent;
            //else QuestionInfo = "given Theta = "+ThetaComponent+", Re = "+XComponent;
        }
        else if (QuestionType <= 9) {
            //given theta, Im/y find (radius/norm/modulus) or Re/x
            if (QuestionComplex)
                QuestionInfo = "given Theta = "+ThetaComponent+", Im = "+iYComponent;
            else QuestionInfo = "given Theta = "+ThetaComponent+", Y = "+YComponent;
        }
        else if (QuestionType <= 11) {
            //given theta, Re/x find (radius/norm/modulus) or Im/y
            if (QuestionComplex)
                QuestionInfo = "given Theta = "+ThetaComponent+", Re = "+XComponent;
            else QuestionInfo = "given Theta = "+ThetaComponent+", X = "+XComponent;
        }

        generateMediumAnswerArray(QuestionType);
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

        iYComponent = toComplex(iYComponent);

        return RandomY;
    }

    //Generate correct answers and random answers
    private void generateEasyAnswerArray(int _type) {
        String TempAnswer = "";
        AnswerArrayIndex = getRandomInt(0, AnswerArraySize - 1);
        for (int i = 0; i < AnswerArraySize; i++) {
            if (i==AnswerArrayIndex)
                AnswerArray[i] = generateEasyRightAnswer(_type);
            else {
                //check for duplicates as answers are being generated
                do {
                    TempAnswer = generateEasyWrongAnswer(_type);
                } while (Arrays.asList(AnswerArray).contains(TempAnswer));

                AnswerArray[i] = TempAnswer;
            }
        }
    }

    private String generateEasyRightAnswer(int _type) { //should generate a random correct answer
        String Answer = "";
        String ComponentA = "";
        String ComponentB = "";
        int RandomForm;

        if (_type == 0) {
            //generate answer to question asking for: norm  given x and y
            //no duplicates possible as we only call generateEasyRightAnswer once per questionvector
            //commutability of addition means we may randomly generate different forms of answers ie. X+Y = Y+X both correct
            //answer form: sqrt ( X^2 + Y^2 )
            ComponentA = XComponent;
            ComponentB = YComponent;

            ComponentA = applyPower(ComponentA, 2, 1, decideComplexity(EasyLevel, 1), false);
            ComponentB = applyPower(ComponentB, 2, 1, decideComplexity(EasyLevel, 2), false);
            Answer = commutativeOperation(ComponentA, ComponentB, '+');
            Answer = applyPower(Answer, 1, 2, decideComplexity(EasyLevel, 3), false);

        }
        else if (_type == 1) {
            //generate answer to question asking for: x     given norm and y
            //answer form: sqrt ( N^2 - Y^2 )
            ComponentA = NormComponent;
            ComponentB = YComponent;

            ComponentA = applyPower(ComponentA, 2, 1, decideComplexity(EasyLevel, 1), false);
            ComponentB = applyPower(ComponentB, 2, 1, decideComplexity(EasyLevel, 2), false);
            Answer = commutativeOperation(ComponentA, ComponentB, '-');
            Answer = applyPower(Answer, 1, 2, decideComplexity(EasyLevel, 3), false);
        }
        else {//_type == 2
            //generate answer to question asking for: y     given norm and x
            //answer form: sqrt ( N^2 - X^2 )
            ComponentA = NormComponent;
            ComponentB = XComponent;

            ComponentA = applyPower(ComponentA, 2, 1, decideComplexity(EasyLevel, 1), false);
            ComponentB = applyPower(ComponentB, 2, 1, decideComplexity(EasyLevel, 2), false);
            Answer = commutativeOperation(ComponentA, ComponentB, '-');
            Answer = applyPower(Answer, 1, 2, decideComplexity(EasyLevel, 3), false);

            if (AnswerComplex)
                Answer = toComplex(Answer);
        }
        return Answer;
    }

    private String generateEasyWrongAnswer(int _type) { //generate a random wrong answer with a similar form of a correct answer
        String RandomAnswer = "";
        String ComponentA = "";
        String ComponentB = "";
        int RandomForm;

        if (_type == 0) {   // need some more randomization of answers -> perhaps vary powers

            ComponentA = XComponent;
            ComponentB = YComponent;

            ComponentA = applyPower(ComponentA, 2, 1, decideComplexity(EasyLevel, 1), true);
            ComponentB = applyPower(ComponentB, 2, 1, decideComplexity(EasyLevel, 2), true);
            RandomAnswer = commutativeOperation(ComponentA, ComponentB, randomCharOperation('+'));
            RandomAnswer = applyPower(RandomAnswer, 1, 2, decideComplexity(EasyLevel, 3), true);
        }
        else if (_type == 1) {
            //implement
            ComponentA = NormComponent;
            ComponentB = YComponent;

            ComponentA = applyPower(ComponentA, 2, 1, decideComplexity(EasyLevel, 1), true);
            ComponentB = applyPower(ComponentB, 2, 1, decideComplexity(EasyLevel, 2), true);
            RandomAnswer = commutativeOperation(ComponentA, ComponentB, randomCharOperation('-'));
            RandomAnswer = applyPower(RandomAnswer, 1, 2, decideComplexity(EasyLevel, 3), true);
        }
        else {//if (_type == 2)
            //implement
            ComponentA = NormComponent;
            ComponentB = XComponent;

            ComponentA = applyPower(ComponentA, 2, 1, decideComplexity(EasyLevel, 1), true);
            ComponentB = applyPower(ComponentB, 2, 1, decideComplexity(EasyLevel, 2), true);
            RandomAnswer = commutativeOperation(ComponentA, ComponentB, '-');
            RandomAnswer = applyPower(RandomAnswer, 1, 2, decideComplexity(EasyLevel, 3), true);

            if (AnswerComplex)
                RandomAnswer = toComplex(RandomAnswer);
        }
        return RandomAnswer;
    }

    //generating medium questions
    private void generateMediumAnswerArray(int _type) {
        String TempAnswer = "";
        AnswerArrayIndex = getRandomInt(0, AnswerArraySize - 1);
        for (int i = 0; i < AnswerArraySize; i++) {
            if (i==AnswerArrayIndex)
                AnswerArray[i] = generateMediumRightAnswer(_type);
            else {
                //check for duplicates as answers are being generated
                do {
                    TempAnswer = generateMediumWrongAnswer(_type);
                } while (Arrays.asList(AnswerArray).contains(TempAnswer));

                AnswerArray[i] = TempAnswer;
            }
        }
    }

    private String generateMediumRightAnswer(int _type) { //should generate a random correct answer
        String Answer = "";
        String ComponentA = "";
        String ComponentB = "";
        int RandomForm;

        if (_type == 0) {   //actually an EasyQuestion
            //given x, y    find Norm
            Answer = generateEasyRightAnswer(0);
        }
        else if (_type == 2) {    //also an EasyQuestion
            //given Norm, x    find y
            Answer = generateEasyRightAnswer(2);
        }
        else if (_type == 4) {  //last EasyQuestion type
            //given Norm, y    find x
            Answer = generateEasyRightAnswer(1);
        }
        else if (_type == 1) {
            //given y,x find theta
            ComponentA = YComponent;
            ComponentB = XComponent;

            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), false);
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), false);
            Answer = commutativeOperation(ComponentA, ComponentB, '/');
            Answer = applyTrig(Answer, 12); //arctan

            //Answer = applyPower(Answer, 1, 1, decideComplexity(MediumLevel,3), false);  //optional
        }
        else if (_type == 3) {
            //given x,Norm  find theta
            ComponentA = XComponent;
            ComponentB = NormComponent;

            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), false);
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), false);
            Answer = commutativeOperation(ComponentA, ComponentB, '/');
            Answer = applyTrig(Answer, 11); //arccos
        }
        else if (_type == 5) {
            //given y,Norm  find theta
            ComponentA = YComponent;
            ComponentB = NormComponent;

            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), false);
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), false);
            Answer = commutativeOperation(ComponentA, ComponentB, '/');
            Answer = applyTrig(Answer, 10); //arcsin
        }
        else if (_type == 6) {
            //given theta,Norm  find x
            ComponentA = ThetaComponent;
            ComponentB = NormComponent;

            ComponentA = applyTrig(ComponentA, 1);  //cos
            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), false);
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), false);
            Answer = commutativeOperation(ComponentA, ComponentB, '*');
        }
        else if (_type == 7) {
            //given theta,Norm  find y
            ComponentA = ThetaComponent;
            ComponentB = NormComponent;

            ComponentA = applyTrig(ComponentA, 0);  //sin
            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), false);
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), false);
            Answer = commutativeOperation(ComponentA, ComponentB, '*');
        }
        else if (_type == 8) {
            //given y,theta find Norm
            ComponentA = YComponent;
            ComponentB = ThetaComponent;

            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), false);
            ComponentB = applyTrig(ComponentB, 0);  //sin
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), false);
            Answer = commutativeOperation(ComponentA, ComponentB, '/');
        }
        else if (_type == 10) {
            //given x,theta find Norm
            ComponentA = XComponent;
            ComponentB = ThetaComponent;

            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), false);
            ComponentB = applyTrig(ComponentB, 1);  //cos
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), false);
            Answer = commutativeOperation(ComponentA, ComponentB, '/');
        }
        else if (_type == 9) {
            //given y,theta find x
            ComponentA = YComponent;
            ComponentB = ThetaComponent;

            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), false);
            ComponentB = applyTrig(ComponentB, 2);  //tan
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), false);
            Answer = commutativeOperation(ComponentA, ComponentB, '/');
        }
        else {
            //_type == 11
            //given x,theta find y
            ComponentA = XComponent;
            ComponentB = ThetaComponent;

            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), false);
            ComponentB = applyTrig(ComponentB, 2);  //tan
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), false);
            Answer = commutativeOperation(ComponentA, ComponentB, '*');
        }
        return Answer;
    }

    private String generateMediumWrongAnswer(int _type) { //generate a random wrong answer with a similar form of a correct answer
        //counter for # wrong components? check that it is at least 1
        String RandomAnswer = "";
        String ComponentA = "";
        String ComponentB = "";
        int RandomForm;

        if (_type == 0) {   //actually an EasyQuestion
            //given x, y    find Norm
            RandomAnswer = generateEasyWrongAnswer(0);
        }
        else if (_type == 2) {    //also an EasyQuestion
            //given Norm, x    find y
            RandomAnswer = generateEasyWrongAnswer(2);
        }
        else if (_type == 4) {  //last EasyQuestion type
            //given Norm, y    find x
            RandomAnswer = generateEasyWrongAnswer(1);
        }
        else if (_type == 1) {
            //given y,x find theta
            ComponentA = YComponent;
            ComponentB = XComponent;

            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), true);
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), true);
            RandomAnswer = commutativeOperation(ComponentA, ComponentB, randomCharOperation('/'));
            RandomAnswer = applyTrig(RandomAnswer, randomTrigOperation(12)); //arctan

            //Answer = applyPower(Answer, 1, 1, decideComplexity(MediumLevel,3), false);  //optional
        }
        else if (_type == 3) {
            //given x,Norm  find theta
            ComponentA = XComponent;
            ComponentB = NormComponent;

            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), true);
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), true);
            RandomAnswer = commutativeOperation(ComponentA, ComponentB, randomCharOperation('/'));
            RandomAnswer = applyTrig(RandomAnswer, randomTrigOperation(11)); //arccos
        }
        else if (_type == 5) {
            //given y,Norm  find theta
            ComponentA = YComponent;
            ComponentB = NormComponent;

            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), true);
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), true);
            RandomAnswer = commutativeOperation(ComponentA, ComponentB, randomCharOperation('/'));
            RandomAnswer = applyTrig(RandomAnswer, randomTrigOperation(10)); //arcsin
        }
        else if (_type == 6) {
            //given theta,Norm  find x
            ComponentA = ThetaComponent;
            ComponentB = NormComponent;

            ComponentA = applyTrig(ComponentA, randomTrigOperation(1));  //cos
            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), true);
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), true);
            RandomAnswer = commutativeOperation(ComponentA, ComponentB, randomCharOperation('*'));
        }
        else if (_type == 7) {
            //given theta,Norm  find y
            ComponentA = ThetaComponent;
            ComponentB = NormComponent;

            ComponentA = applyTrig(ComponentA, randomTrigOperation(0));  //sin
            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), true);
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), true);
            RandomAnswer = commutativeOperation(ComponentA, ComponentB, randomCharOperation('*'));
        }
        else if (_type == 8) {
            //given y,theta find Norm
            ComponentA = YComponent;
            ComponentB = ThetaComponent;

            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), true);
            ComponentB = applyTrig(ComponentB, randomTrigOperation(0));  //sin
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), true);
            RandomAnswer = commutativeOperation(ComponentA, ComponentB, randomCharOperation('/'));
        }
        else if (_type == 10) {
            //given x,theta find Norm
            ComponentA = XComponent;
            ComponentB = ThetaComponent;

            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), true);
            ComponentB = applyTrig(ComponentB, randomTrigOperation(1));  //cos
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), true);
            RandomAnswer = commutativeOperation(ComponentA, ComponentB, randomCharOperation('/'));
        }
        else if (_type == 9) {
            //given y,theta find x
            ComponentA = YComponent;
            ComponentB = ThetaComponent;

            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), true);
            ComponentB = applyTrig(ComponentB, randomTrigOperation(2));  //tan
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), true);
            RandomAnswer = commutativeOperation(ComponentA, ComponentB, randomCharOperation('/'));
        }
        else {
            //_type == 11
            //given x,theta find y
            ComponentA = XComponent;
            ComponentB = ThetaComponent;

            ComponentA = applyPower(ComponentA, 1, 1, decideComplexity(MediumLevel,1), true);
            ComponentB = applyTrig(ComponentB, randomTrigOperation(2));  //tan
            ComponentB = applyPower(ComponentB, 1, 1, decideComplexity(MediumLevel,2), true);
            RandomAnswer = commutativeOperation(ComponentA, ComponentB, randomCharOperation('*'));
        }
        return RandomAnswer;
    }

    //Public Methods
    public String getQuestion() {
        return Question;    //ie. String "Find the x component of the presented vector"
    }

    public String getQuestionInfo() {
        return QuestionInfo;    //ie. "v = (?, 150) theta = 50 degrees"
    }

    public String[] getAnswerArray() {  //for generating buttons
        return AnswerArray;
    }

    public int getAnswerArrayIndex() {  //for deciding which answer is correct
        return AnswerArrayIndex;
    }
}