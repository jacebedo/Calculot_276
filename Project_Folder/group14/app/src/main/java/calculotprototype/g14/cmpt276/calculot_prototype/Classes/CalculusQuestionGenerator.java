package calculotprototype.g14.cmpt276.calculot_prototype.Classes;

public class CalculusQuestionGenerator {    //random question generator for the Defence of Calculot Game
    //fields
    int Topic;  //0 differentiation, 1 integration, 2 combined
    int ScoreMultiplier = 1;    //bonus for combined x1.1?
    int DiffLevel;  //Differentiation level
    int IntLevel;   //Integration level

    //Constructor
    public CalculusQuestionGenerator(int _topic, int _difflevel, int _intlevel) {
        DiffLevel = _difflevel;
        IntLevel = _intlevel;
        Topic = _topic;

        if (Topic == 0) {
            generateDiffQuestion();
        }
        else if (Topic == 1) {
            generateIntQuestion();
        }
        else {
            generateRandomQuestion();
        }
    }

    //private methods
    private void generateRandomQuestion() {
        //create random boolean static class?
        Random Rand = new Random();
        int RandomBoolean = Rand.nextBoolean();

        if (RandomBoolean == 0) {
            generateDiffQuestion();
        }
        else {
            generateIntQuestion();
        }
    }

    private void generateDiffQuestion() {
        //implement - instantiates an object of type CalculusQuestion
    }

    private void generateIntQuestion() {
        //implement - instantiates an object of type CalculusQuestion
    }

    //public methods
}