package karlina.spbau.ru.project.storageClasses;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by liuba on 28.12.17.
 */

public class Drawer {
    static private final int BackgroundColor = Color.argb(127, 255, 0, 0);
    static private Paint BackgroundPaint;
    private int size;
    private Navigator nav;
    private Canvas canvas;

    public Drawer(Navigator n, Canvas canv) {
        nav = n;
        size = nav.getGreedSize();
        canvas = canv;
        BackgroundPaint = new Paint();
        BackgroundPaint.setColor(BackgroundColor);
        BackgroundPaint.setStrokeWidth(10);
    }

    public void drawPuzzle(int i, int j, Greed greed) {
        int x = nav.getX(i), y = nav.getY(j);

        int puzzleNumber = greed.getIntPuzzle(i,j);
        if (puzzleNumber == -1) {
            canvas.drawRect(x - size/2, y + size / 2, x + size / 2, y - size/ 2, BackgroundPaint);
        } else {

        }
    }

    public void drawCell(int i, int j) {

    }

}
