package karlina.spbau.ru.project.storageClasses;

import android.graphics.Canvas;

/**
 * Created by liuba on 28.12.17.
 */

class Puzzle {
    private int i;
    private int j;

    private int expectedI;
    private int expectedJ;

    private int size;

    public Puzzle(int correctX, int correctY, int size) {
        this.expectedI = correctX;
        this.expectedJ = correctY;

        this.size = size;
    }

    public void Draw(Canvas canvas)
    {

    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean isCorrect(){
        return i == expectedI && j == expectedJ;
    }
}