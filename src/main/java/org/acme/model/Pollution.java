package org.acme.model;

public class Pollution {
    private int[] parts = new int[10];
    private long timestamp;
    
    public Pollution setPart(int index, int value){
        this.parts[index] = value;
        return this;
    }

    public int getPart(int index){
        return this.parts[index];
    }

    public Pollution setParts(int[] parts){
        this.parts = parts;
        return this;
    }

    public int[] getParts(){
        return this.parts;
    }

    public Pollution setTimestamp(long timestamp){
        this.timestamp = timestamp;
        return this;
    }

    public long getTimestamp(){
        return this.timestamp;
    }
}
