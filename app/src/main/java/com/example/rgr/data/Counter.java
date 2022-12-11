package com.example.rgr.data;

public class Counter {
    private long count = 0;

    public void inc(){
        count += 1;
    }

    public void inc(long n){
        count += n;
    }

    public long getCount() {
        return count;
    }

    public void reset() {
        count = 0;
    }
}
