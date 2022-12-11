package com.example.rgr;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.example.rgr.data.structure.CircularList;

class JSONHelper {
    private static final String FILE_NAME = "save.dat";

    static public Object readItems(Context context) {

        CircularList result = null;
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(fis);
            result = (CircularList) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    static public void writeItems(Context context, Object input) {

        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
