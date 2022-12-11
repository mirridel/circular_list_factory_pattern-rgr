package com.example.rgr.data.types;

import java.io.InputStreamReader;
import java.util.Random;

public class UserInteger implements UserType, Cloneable, Comparable<UserInteger>{
    @Override
    public String typeName() {
        return "Integer";
    }

    @Override
    public Object create() {
        return new Random().nextInt(100);
    }

    @Override
    public Object clone() { return null;}

    @Override
    public Object readValue(InputStreamReader in) {
        return null;
    }

    @Override
    public Object parseValue(String ss) {
        int result = 0;
        try {
            result = Integer.parseInt(ss);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public Comparator getTypeComparator() {
        return (o1, o2) -> (Integer) o1 - (Integer) o2;
    }

    @Override
    public int compareTo(UserInteger o) {
        return 0;
    }
}
