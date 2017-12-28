package karlina.spbau.ru.project.storageClasses;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by liuba on 28.12.17.
 */

public class Greed {
    private int[][] greed;
    private int size = 0;
    private Navigator nav;

    public Greed(Navigator n) {
        nav = n;
        size = nav.getGreedSize();
        greed = new int[size][size];

        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                greed[i][j] = -1;
    }

    public boolean isEmpty(float x, float y) {
        return greed[nav.getI(x)][nav.getJ(y)] == -1;
    }

    public boolean setPuzzle(int number, float x, float y) {
        if (isEmpty(x, y)) {
            greed[nav.getI(x)][nav.getJ(y)] = number;
            return true;
        }

        return false;
    }

    public boolean setPuzzle(int number, int i, int j, float x, float y) {
        if (isEmpty(x, y)) {
            int i2 = nav.getI(x), j2 = nav.getJ(y);
            greed[i2][j2] = number;
            greed[i][j] = -1;
            //drawCell((int) x, (int) y);
            //drawCell(i, j);
            return true;
        }

        return false;
    }

    public int getPuzzle(float x, float y) {
        return greed[nav.getI(x)][nav.getJ(y)];
    }

    public int getIntPuzzle(int i, int j) {
        return greed[i][j];
    }

    public int size() {
        return size;
    }
}
