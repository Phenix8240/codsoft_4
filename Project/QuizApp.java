package Project;

import java.util.*;

class QuizQuestion {
  private String question;
  private List<String> options;
  private int correctOptionIndex;

  public QuizQuestion(String question, List<String> options, int correctOptionIndex) {
    this.question = question;
    this.options = options;
    this.correctOptionIndex = correctOptionIndex;
  }

  public String getQuestion() {
    return question;
  }

  public List<String> getOptions() {
    return options;
  }

  public int getCorrectOptionIndex() {
    return correctOptionIndex;
  }
}

public class QuizApp {
  private List<QuizQuestion> questions;
  private int score;
  private Scanner scanner;
  private final int timePerQuestion = 30; 
  private String userName;

  public QuizApp(List<QuizQuestion> questions) {
    this.questions = questions;
    this.score = 0;
    this.scanner = new Scanner(System.in);
  }

  public void startQuiz() {
    System.out.println("Welcome to the Quiz!");

    System.out.print("Enter your name: ");
    userName = scanner.nextLine();

    int questionsToShow = Math.min(5, questions.size()); // Limit to 5 questions
    for (int i = 0; i < questionsToShow; i++) {
      QuizQuestion question = getRandomQuestion();
      displayQuestion(question);
      startTimer(question);
      int userAnswer = getUserAnswer();
      if (userAnswer == question.getCorrectOptionIndex()) {
        System.out.println("Correct!");
        score++;
      } else {
        System.out.println("Incorrect! The correct answer was: " + question.getOptions().get(question.getCorrectOptionIndex()));
      }
      
      System.out.println("Your Score: " + score + "/" + (i + 1));
    }

    System.out.println("\nQuiz Finished!");
    System.out.println("Final Score: " + score + "/" + questionsToShow);
    System.out.println("Thank you for playing, " + userName + "!");

    // **Result Screen (Improved)**
    System.out.println("\nDetailed Results:");
    int correct = 0;
    int incorrect = 0;
    for (int i = 0; i < questionsToShow; i++) {
      QuizQuestion question = questions.get(i);
      if (score > i) {
        correct++;
        System.out.println("  Question " + (i + 1) + ": Correct");
      } else {
        incorrect++;
        System.out.println("  Question " + (i + 1) + ": Incorrect (Correct answer: " + question.getOptions().get(question.getCorrectOptionIndex()) + ")");
      }
    }
    System.out.println("  Correct Answers: " + correct);
    System.out.println("  Incorrect Answers: " + incorrect);
  }


  private QuizQuestion getRandomQuestion() {
    Random random = new Random();
    int index = random.nextInt(questions.size());
    return questions.get(index);
  }

  private void displayQuestion(QuizQuestion question) {
    System.out.println("\n" + question.getQuestion());
    List<String> options = question.getOptions();
    for (int i = 0; i < options.size(); i++) {
      System.out.println((i + 1) + ". " + options.get(i));
    }
  }

  private int getUserAnswer() {
    System.out.print("Your Answer (Enter option number): ");
    int userAnswer = scanner.nextInt();
    return userAnswer - 1;
  }

  private void startTimer(QuizQuestion question) {
  Timer timer = new Timer();
  timer.schedule(new TimerTask() {
    @Override
    public void run() {
      System.out.println("Time's Up!");
      timer.cancel(); 
    }
  }, timePerQuestion * 1000); 
  }

  public static void main(String[] args) {
    // Sample quiz questions
    List<QuizQuestion> questions = new ArrayList<>();
    questions.add(new QuizQuestion("What is the powerhouse of the cell?",
        Arrays.asList("Nucleus", "Ribosome", "Mitochondria", "Endoplasmic Reticulum"), 2));
    questions.add(new QuizQuestion("Who developed the theory of relativity?",
        Arrays.asList("Isaac Newton", "Albert Einstein", "Stephen Hawking", "Galileo Galilei"), 1));
    questions.add(new QuizQuestion("What is the largest ocean on Earth?",
        Arrays.asList("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"), 3));
    questions.add(new QuizQuestion("Who painted the Mona Lisa?",
        Arrays.asList("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"), 2));
    questions.add(new QuizQuestion("Which gas is most abundant in Earth's atmosphere?",
        Arrays.asList("Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen"), 2));
    questions.add(new QuizQuestion("What is the chemical symbol for water?",
        Arrays.asList("H2O", "CO2", "O2", "NaCl"), 0));
    questions.add(new QuizQuestion("Who wrote 'The Great Gatsby'?",
    Arrays.asList("Ernest Hemingway", "F. Scott Fitzgerald", "Mark Twain", "George Orwell"), 1));
questions.add(new QuizQuestion("What is the capital of Japan?",
    Arrays.asList("Seoul", "Shanghai", "Beijing", "Tokyo"), 3));
questions.add(new QuizQuestion("Which planet is known as the 'Morning Star'?",
    Arrays.asList("Mars", "Venus", "Jupiter", "Mercury"), 1));
questions.add(new QuizQuestion("Who is known as the 'Father of Computer Science'?",
    Arrays.asList("Alan Turing", "Bill Gates", "Steve Jobs", "Tim Berners-Lee"), 0));



    QuizApp quizApp = new QuizApp(questions);
    quizApp.startQuiz();
  }
}



