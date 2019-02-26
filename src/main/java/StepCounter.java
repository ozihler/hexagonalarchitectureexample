public class StepCounter implements ICountSteps {
    private IObtainStepCounts stepCountsObtainer;
    private IStoreStepCounts stepCountsStorage;
    private int stepCount;

    public StepCounter() {
        this.stepCount = 0;
    }

    public StepCounter(IObtainStepCounts stepCountHistory) {
        this.stepCount = stepCountHistory.loadTodaysStepCount();
    }

    public StepCounter(IObtainStepCounts stepCountsObtainer, IStoreStepCounts stepCountsStorage) {
        this.stepCountsObtainer = stepCountsObtainer;
        this.stepCountsStorage = stepCountsStorage;
        this.stepCount = stepCountsObtainer.loadTodaysStepCount();
    }

    @Override
    public void step() {
        stepCount++;
        if (stepCountsStorage != null) {
            stepCountsStorage.updateTodaysStepCount(stepCount);
        }
    }

    @Override
    public int todaysStepCount() {
        return stepCount;
    }
}
