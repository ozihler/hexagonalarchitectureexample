import org.junit.Test;

import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StepCounterTest {
    @Test
    public void testStepCounter() {
        //DomainPort x = new DomainAdapter()
        ICountSteps stepCounter = new StepCounter();
        for (int i = 0; i < 1000; ++i) {
            stepCounter.step();
            assertEquals((i + 1), stepCounter.todaysStepCount());
        }
    }

    @Test
    public void testGetStepCountHistory() {
        IObtainStepCounts stepCountHistory = mock(IObtainStepCounts.class);
        when(stepCountHistory.loadTodaysStepCount()).thenReturn(4000);
        ICountSteps dailyStepCounter = new StepCounter(stepCountHistory);
        assertEquals(4000, dailyStepCounter.todaysStepCount());
        dailyStepCounter.step();
        assertEquals(4001, dailyStepCounter.todaysStepCount());
    }


    @Test
    public void askForDailyStepCount() {
        // Right side
        IObtainStepCounts stepCountHistory = mock(IObtainStepCounts.class);
        when(stepCountHistory.loadTodaysStepCount()).thenReturn(4000);

        // Hexagon
        ICountSteps stepCounter = new StepCounter(stepCountHistory);

        // Left side
        PrintStream consoleWriter = mock(PrintStream.class);
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(consoleWriter, stepCounter);
        consoleAdapter.showTodaysStepCount();

        verify(consoleWriter, times(1)).println("Today, you walked " + 4000 + " steps.");
    }

    @Test
    public void storeStepCountHistory() {
        IObtainStepCounts stepCountHistory = new FileStepCountHistory("daily-step-counts.txt");
        StepCounter stepCounter = new StepCounter(stepCountHistory);

        assertEquals(3456, stepCounter.todaysStepCount());
        stepCounter.step();
        assertEquals(3457, stepCounter.todaysStepCount());
    }

    @Test
    public void persistStepCountHistory() {
        IObtainStepCounts stepCountHistory = new FileStepCountHistory("daily-step-counts.txt");
        StepCounter stepCounter = new StepCounter(stepCountHistory);

        assertEquals(3456, stepCounter.todaysStepCount());
        stepCounter.step();
        assertEquals(3457, stepCounter.todaysStepCount());

        FileStepCountHistory newStepCountHistory = new FileStepCountHistory("daily-step-counts.txt");
        IStoreStepCounts stepCountsStorage = newStepCountHistory;
        stepCounter = new StepCounter(newStepCountHistory, stepCountsStorage);


        assertEquals(3456, stepCounter.todaysStepCount());
        stepCounter.step();
        newStepCountHistory = new FileStepCountHistory("daily-step-counts.txt");
        stepCountsStorage = newStepCountHistory;
        stepCounter = new StepCounter(newStepCountHistory, stepCountsStorage);

        assertEquals(3457, stepCounter.todaysStepCount());
        stepCountsStorage.updateTodaysStepCount(3456);
    }

    @Test
    public void integrate() {
        //Right side
        IObtainStepCounts stepCountHistory = new FileStepCountHistory("daily-step-counts.txt");

        //Hexagon
        ICountSteps stepCounter = new StepCounter(stepCountHistory);

        //Left side
        PrintStream consoleWriter = mock(PrintStream.class);
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(consoleWriter, stepCounter);
        consoleAdapter.showTodaysStepCount();

        verify(consoleWriter, times(1)).println("Today, you walked " + stepCountHistory.loadTodaysStepCount() + " steps.");
    }

    @Test
    public void print() {
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(System.out, new StepCounter(new FileStepCountHistory("daily-step-counts.txt")));
        consoleAdapter.showTodaysStepCount();
    }

    @Test
    public void storeTodaysStepCount() {

    }
}
