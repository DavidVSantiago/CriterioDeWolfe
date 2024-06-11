package utils;

public class Clock {
    private long startTime;
    private long endTime;

    public Clock(){
        startTime=0;
        endTime=0;
    }

    private long getTime(){
        return System.nanoTime();
    }
    public void setStartTime(){
        startTime = getTime();
    }
    public void setEndTime(){
        endTime = getTime();
    }
    public void reset(){
        startTime = 0;
        endTime=0;
    }
    public double getDeltaTimeInSeconds(){
        return (double)(endTime-startTime)/1_000_000_000.00;
    }
}
