package org.acme.model;

public class Sample {
    private int[] values = new int[50];
    private long startTime;
    private long endTime;

    public long getEndTime() {
        return endTime;
    }

    public int[] getValues() {
        return values;
    }

    public void setValue(int index, int value) {
        this.values[index] = value;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    
}
