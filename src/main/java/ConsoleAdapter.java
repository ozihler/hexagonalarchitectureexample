import java.io.PrintStream;

public class ConsoleAdapter {
    private PrintStream consoleWriter;
    private ICountSteps dailyStepCounter;

    public ConsoleAdapter(PrintStream consoleWriter, ICountSteps dailyStepCounter) {
        this.consoleWriter = consoleWriter;
        this.dailyStepCounter = dailyStepCounter;
    }

    public void showTodaysStepCount() {
        consoleWriter.println("Today, you walked " + dailyStepCounter.todaysStepCount() + " steps.");
    }
}
