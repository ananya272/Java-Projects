import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String questionText;
    String[] options;
    char correctAnswer;

    public Question(String questionText, String[] options, char correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizApplication {

    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static boolean answerSubmitted = false;
    private static char userAnswer = ' ';
    private static Timer timer = new Timer();

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?",
                new String[] { "A. Paris", "B. London", "C. Berlin", "D. Madrid" }, 'A'));
        questions.add(new Question("Which planet is known as the Red Planet?",
                new String[] { "A. Earth", "B. Mars", "C. Jupiter", "D. Venus" }, 'B'));
        questions.add(new Question("Who wrote 'Romeo and Juliet'?",
                new String[] { "A. William Shakespeare", "B. Charles Dickens", "C. Mark Twain", "D. Jane Austen" },
                'A'));

        Scanner scanner = new Scanner(System.in);

        while (currentQuestionIndex < questions.size()) {
            displayQuestion(questions.get(currentQuestionIndex));
            startTimer(scanner, 10); // 10 seconds per question
            if (userAnswer == questions.get(currentQuestionIndex).correctAnswer) {
                score++;
            }
            currentQuestionIndex++;
        }

        displayResult(questions);
        scanner.close();
    }

    private static void displayQuestion(Question question) {
        System.out.println("Question: " + question.questionText);
        for (String option : question.options) {
            System.out.println(option);
        }
        System.out.println("You have 10 seconds to answer.");
    }

    private static void startTimer(Scanner scanner, int seconds) {
        answerSubmitted = false;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!answerSubmitted) {
                    System.out.println("\nTime's up!");
                }
            }
        }, seconds * 1000);

        System.out.print("Enter your answer (A/B/C/D): ");
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < (seconds * 1000) && !answerSubmitted) {
            if (scanner.hasNextLine()) {
                userAnswer = scanner.nextLine().trim().toUpperCase().charAt(0);
                answerSubmitted = true;
                timer.cancel();
                timer = new Timer();
            }
        }
    }

    private static void displayResult(List<Question> questions) {
        System.out.println("\nQuiz Over!");
        System.out.println("Your score: " + score + "/" + questions.size());
        System.out.println("\nSummary:");
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Q" + (i + 1) + ": " + question.questionText);
            System.out.println("Correct Answer: " + question.correctAnswer);
            if (userAnswer == question.correctAnswer) {
                System.out.println("Your Answer: " + userAnswer + " (Correct)");
            } else {
                System.out.println("Your Answer: " + userAnswer + " (Incorrect)");
            }
            System.out.println();
        }
    }
}
