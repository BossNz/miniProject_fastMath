package fastmath;

import java.util.Random;

public class script {
    private String question;
    private int answer;
    private int number1;
    private int number2;

    public String generateQuestion(int lv) {
        switch (lv) {
            case 3:
                number1 = randomNumber(10, 300);
                number2 = randomNumber(10, 300);
                break;
            case 4:
                number1 = randomNumber(200, 500);
                number2 = randomNumber(200, 500);
                break;
            default:
                number1 = randomNumber(1, 100);
                number2 = randomNumber(1, 100);
        }

        int operationLevel = randomNumber(1, lv);
        switch (operationLevel) {
            case 1:
                answer = number1 + number2;
                question = number1 + " + " + number2;
                break;
            case 2:
                answer = number1 - number2;
                question = number1 + " - " + number2;
                break;
            case 3:
                number2 = randomNumber(1, 9);
                answer = number1 * number2;
                question = number1 + " x " + number2;
                break;
            case 4:
                number2 = randomNumber(1, 9);
                answer = number1 / number2;
                question = number1 + " ÷ " + number2;
                break;
            default:
                answer = number1 + number2;
                question = number1 + " + " + number2;
        }
        return question;
    }

    public int[] getAnswerList() {
        int[] answerList = { 0, 0, 0 };
        answerList[0] = answer + randomNumber(1, 10);
        answerList[1] = answer - randomNumber(1, 10);
        answerList[2] = answer + randomNumber(1, 10);

        int randomAnswer = randomNumber(1, 3);
        if (randomAnswer == 1)
            answerList[0] = answer;
        if (randomAnswer == 2)
            answerList[1] = answer;
        if (randomAnswer == 3)
            answerList[2] = answer;
        return answerList;
    }

    public boolean checkSolve(int answer) {
        if (this.answer == answer)
            return true;
        return false;
    }

    private int randomNumber(int min, int max) {
        return (new Random()).nextInt(max - min + 1) + min;
    }
}
