import java.util.*;

enum QuestionType {
    MULTIPLE_CHOICE,
    TRUE_FALSE,
    OPEN_ENDED
}

class Question {
    String text;
    QuestionType type;
    String correctAnswer;

    public Question(String text, QuestionType type, String correctAnswer) {
        this.text = text;
        this.type = type;
        this.correctAnswer = correctAnswer;
    }
}

class Quiz {
    List<Question> questions;

    public Quiz() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}

class QuizSession {
    Map<Question, String> userAnswers;

    public QuizSession() {
        this.userAnswers = new HashMap<>();
    }

    public void answerQuestion(Question question, String answer) {
        userAnswers.put(question, answer);
    }
}

class QuizResult {
    int score;

    public QuizResult(int score) {
        this.score = score;
    }
}

class QuizResultService {
    public QuizResult calculateResult(QuizSession quizSession, Quiz quiz) {
        int score = 0;

        for (Question question : quizSession.userAnswers.keySet()) {
            String userAnswer = quizSession.userAnswers.get(question);
            if (userAnswer.equals(question.correctAnswer)) {
                score++;
            }
        }

        return new QuizResult(score);
    }
}

class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample quiz creation
        Quiz quiz = new Quiz();
        quiz.addQuestion(new Question("What country has the highest life expectancy?", QuestionType.OPEN_ENDED, "Hong Kong"));
        quiz.addQuestion(new Question("Where would you be if you were standing on the Spanish Steps?", QuestionType.TRUE_FALSE, "Rome"));
        quiz.addQuestion(new Question("Which language has the more native speakers: English or Spanish?",
                QuestionType.MULTIPLE_CHOICE, "Spanish"));
        quiz.addQuestion(
                new Question("What is the most common surname in the United States? ", QuestionType.OPEN_ENDED, "Smith"));
        quiz.addQuestion(new Question("What is the largest big cat in the world?", QuestionType.OPEN_ENDED, "Tiger"));
        quiz.addQuestion(
                new Question("How many minutes are in a full week? ", QuestionType.OPEN_ENDED, "10,080"));
        quiz.addQuestion(new Question("What company was originally called \"Cadabra\"?",
                QuestionType.OPEN_ENDED, "Amazon"));
        quiz.addQuestion(new Question("What software company is headquartered in Redmond, Washington? ",
                QuestionType.OPEN_ENDED, "Microsoft"));
        quiz.addQuestion(new Question("Who has won the most total Academy Awards? ", QuestionType.OPEN_ENDED, "Walt Disney"));
        quiz.addQuestion(new Question("How many ghosts chase Pac-Man at the start of each game?", QuestionType.OPEN_ENDED, "4"));

        // User authentication (simplified)
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Sample authentication (username: admin, password: admin)
        if (!username.equals("admin") || !password.equals("admin")) {
            System.out.println("Authentication failed. Exiting...");
            return;
        }

        // Quiz session
        QuizSession quizSession = new QuizSession();

        // Quiz taking
        for (Question question : quiz.questions) {
            System.out.println(question.text);

            // Get user's answer
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();

            quizSession.answerQuestion(question, userAnswer);
        }

        // Calculate and display results
        QuizResultService resultService = new QuizResultService();
        QuizResult result = resultService.calculateResult(quizSession, quiz);

        System.out.println("Quiz completed. Your score: " + result.score + "/" + quiz.questions.size());

        scanner.close();
    }
}
