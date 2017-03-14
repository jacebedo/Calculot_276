package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

import java.util.Random;

public class VectorQuestionGenerator {    //random question generator for the Crystal Ball Game
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
    boolean IsComplex;
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

    private String ToComplex(String _real) {    //turns a string component into an imaginary number string if vector is complex
        String Complex = _real;

        if (IsComplex) {
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
        if (_scalar == -1)
            return "-("+_string+")";
        else
        return "("+_scalar+")( "+_string+" )";
    }

    private String applyPower(String _string, int _powernumerator, int _powerdenominator, boolean _reform) {
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

        if (FractionDenominator == 1 && !_reform)   //if reform is true we may have x^(y/1) to vary the answers a bit more
            return "( "+_string+" )^("+FractionNumerator+") ";
        else if (FractionNumerator == 1 && FractionDenominator == 2 && !_reform)
            return "sqrt( "+_string+" )";
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
                    Result = "(1/("+_stringb+"))("+_stringa+")";
                    break;
                case 3:
                    Result = applyPower(_stringb, -1, 1, true); //use decideComplexity?
                    Result = commutativeOperation(_stringa, _stringb, '*'); //recursive call to the '*' case
                    break;
            }
        }
        return Result;
    }

    private boolean decideComplexity(int _level, int _componentnumber) {  //some way to decide if we increase complexity or not
        //level may be easylevel, mediumlevel, or hardlevel
        // componentnumber-1 represents the amount of changes we have already applied
        return getRandomInt( (int)(_level*0.5) , _level+10)>(_componentnumber*2);
    }

    //Borrowed from CalcQuestion - create static class with public random methods?
    private int getRandomInt(int min,int max) {
        Random RandomInteger = new Random();
        return RandomInteger.nextInt((max - min) + 1) + min;
    }

    //Generate Questions
    private void generateQuestion() {
        IsComplex = generateRandomBoolean();

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
        int RandomChoice = getRandomInt(0,2);   //decides if user should find norm, x, or y

        XComponent = Integer.toString(generateRandomX());
        YComponent = Integer.toString(generateRandomY());

        if (RandomChoice == 0) {
            //find norm     given x and y
            if (IsComplex) {
                Question = "Find the modulus of the complex number c = Re+Im (Shown as a vector)";     //currently hardcoded -> we may use strings.xml or database instead
                QuestionInfo = "given Re = "+XComponent+", Im = "+iYComponent;
            }
            else {
                Question = "Find the norm of the vector v = (x,y)";     //currently hardcoded -> we may use strings.xml or database instead
                QuestionInfo = "given x = "+XComponent+", y = "+YComponent;
            }
            //implement random answer generator
            generateEasyAnswerArray(0);
        }
        else if (RandomChoice == 1) {
            //find x    given norm and y
            if (IsComplex) {
                Question = "Find the real part of the complex number c = Re+Im (Shown as a vector)";
                QuestionInfo = "given modulus = "+NormComponent+", Im = "+iYComponent;
            }
            else {
                Question = "Find the x component of the vector v = (x,y)";
                QuestionInfo = "given ||v|| = "+NormComponent+", y = "+YComponent;
            }

            generateEasyAnswerArray(1);
        }
        else {
            //find y    given norm and x
            if (IsComplex) {
                Question = "Find the Imaginary part of the complex number c = Re+Im (Shown as a vector)";
                QuestionInfo = "given modulus = "+NormComponent+", Re = "+XComponent;
            }
            else {
                Question = "Find the y component of the vector v = (x,y)";
                QuestionInfo = "given ||v|| = "+NormComponent+", x = "+XComponent;
            }

            generateEasyAnswerArray(2);
        }

        questionVector = new QuestionVector( Integer.parseInt(XComponent), Integer.parseInt(YComponent));
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

        iYComponent = ToComplex(iYComponent);

        return RandomY;
    }

    //Generate correct answers and random answers
    private void generateEasyAnswerArray(int _type) {
        AnswerArrayIndex = getRandomInt(0, AnswerArraySize - 1);
        for (int i = 0; i < AnswerArraySize; i++) {
            if (i==AnswerArrayIndex)
                AnswerArray[i] = generateEasyRightAnswer(_type);
            else
                AnswerArray[i] = generateEasyWrongAnswer(_type);
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

            ComponentA = applyPower(ComponentA, 2, 1, decideComplexity(EasyLevel, 1));
            ComponentB = applyPower(ComponentB, 2, 1, decideComplexity(EasyLevel, 2));
            Answer = commutativeOperation(ComponentA, ComponentB, '+');
            Answer = applyPower(Answer, 1, 2, decideComplexity(EasyLevel, 3));

        }
        else if (_type == 1) {
            //generate answer to question asking for: x     given norm and y
            //answer form: sqrt ( N^2 - Y^2 )
            ComponentA = NormComponent;
            ComponentB = YComponent;

            ComponentA = applyPower(ComponentA, 2, 1, decideComplexity(EasyLevel, 1));
            ComponentB = applyPower(ComponentB, 2, 1, decideComplexity(EasyLevel, 2));
            Answer = commutativeOperation(ComponentA, ComponentB, '-');
            Answer = applyPower(Answer, 1, 2, decideComplexity(EasyLevel, 3));
        }
        else {//_type == 2 (if complex) or 3 (if not complex)
            //generate answer to question asking for: y     given norm and x
            //answer form: sqrt ( N^2 - X^2 )
            ComponentA = NormComponent;
            ComponentB = XComponent;

            ComponentA = applyPower(ComponentA, 2, 1, decideComplexity(EasyLevel, 1));
            ComponentB = applyPower(ComponentB, 2, 1, decideComplexity(EasyLevel, 2));
            Answer = commutativeOperation(ComponentA, ComponentB, '-');
            Answer = applyPower(Answer, 1, 2, decideComplexity(EasyLevel, 3));
        }
        return Answer;
    }

    private String generateEasyWrongAnswer(int _type) { //generate a random wrong answer with a similar form of a correct answer
        String RandomAnswer = "";
        int RandomForm;

        if (_type == 0) {   // need some more structure -> similar to generateEasyRightAnswer
            RandomForm = getRandomInt(0, 8);    //need to avoid duplicate random answers - scan through AnswerArray for equal strings
            switch (RandomForm) {
                case 0:
                    RandomAnswer = "sqrt( " + XComponent + "^2 - " + YComponent + "^2 )";
                    break;
                case 1:
                    RandomAnswer = "sqrt( " + XComponent + "^2 * " + YComponent + "^2 )";
                    break;
                case 2:
                    RandomAnswer = "sqrt( " + XComponent + "^2 / " + YComponent + "^2 )";
                    break;

                case 3:
                    RandomAnswer = "sqrt( " + YComponent + "^2 - " + XComponent + "^2 )";
                    break;
                case 4:
                    RandomAnswer = "sqrt( " + YComponent + "^2 * " + XComponent + "^2 )";
                    break;
                case 5:
                    RandomAnswer = "sqrt( " + YComponent + "^2 / " + XComponent + "^2 )";
                    break;

                case 6:
                    RandomAnswer = "( " + XComponent + "^2 - " + YComponent + "^2 )^(0.5)";
                    break;
                case 7:
                    RandomAnswer = "( " + XComponent + "^2 + " + YComponent + "^2 )^(-1/2)";
                    break;
                case 8:
                    RandomAnswer = "( " + XComponent + "^2 / " + YComponent + "^2 )^(1/2)";
                    break;
            }
        }
        else if (_type == 1) {
            //implement
        }
        else if (_type == 2) {
            //implement
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