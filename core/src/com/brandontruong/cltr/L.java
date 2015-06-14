package com.brandontruong.cltr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by btru on 6/3/15.
 */
public class L {
    private static final int SMALL = 1;
    private static final int REGULAR = 2;
    private static final int MEDIUM = 3;
    private static final int LARGE = 4;

    public static void CLTR(String input){
        Gdx.app.log("CLTR", input);
    }

    public static void CLTR(int input){
        Gdx.app.log("CLTR", Integer.toString(input));
    }

    public static void CLTR(Double input){
        Gdx.app.log("CLTR", Double.toString(input));
    }

    public static void CLTR(Float input){
        Gdx.app.log("CLTR", Float.toString(input));
    }

    public static void CLTR(boolean input){
        Gdx.app.log("CLTR", Boolean.toString(input));
    }

    public static void line(){
        Gdx.app.log("----", "----");
    }

//    public static FileHandle getCorrectFontSize(int size){
//        switch(size){
//            case(SMALL):
//                return Gdx.files.internal("");
//                break;
//            case(REGULAR):
//                break;
//            case(MEDIUM):
//                break;
//            case(LARGE):
//                break;
//        }
//    }
}
