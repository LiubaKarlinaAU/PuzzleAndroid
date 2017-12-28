package karlina.spbau.ru.project.storageClasses;

import android.graphics.Rect;

/**
 * Created by liuba on 28.12.17.
 */

public class Navigator {
    private int greedW, greedH;
    private int canvasW, canvasH;

    public Navigator(int size, int w, int h){
        greedW = size;
        greedH = size;
        canvasW = w;
        canvasH = h;
    }

    public int getX(int i){
        return i * canvasW / greedW;
    }

    public int getI(float x){
        return (int) (greedW * x / canvasW);
    }

    public int getJ(float y){
        return (int) (greedH * y / canvasH);
    }

    public int getY(int j) {
        return j * canvasH / greedH;
    }

    public int getGreedSize(){
        return greedH;
    }

    public Rect makeCell(int i, int j){
        return new Rect(0,0,i, j);
    }
}