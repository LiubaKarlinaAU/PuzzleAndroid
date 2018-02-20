package karlina.spbau.ru.project.storageClasses;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * This class is representation of puzzle play greed
 * It contains array of puzzles with array size and size of each element(of puzzle).
 */
public class Greed {
    private Puzzle greed[][];
    private int puzzleSize;
    private int greedSize;

    /**
     * Constructor that initialize all class fields
     *
     * @param count - greed size in puzzles
     * @param greedS - greed size in pixels
     */
    public Greed(int count, int greedS) {
        greedSize = count;
        puzzleSize = greedS / count;
        //puzzleSize++;
        greed = new Puzzle[count][count];

        for (int i = 0; i < count; ++i)
            for (int j = 0; j < count; ++j) {
                greed[i][j] = new Puzzle(count - j - 1, count - i - 1);
            }
    }

    /**
     * Method give a bitmap that is part of picture with given coordinate.
     *
     * @param first - first coordinate
     * @param second - second coordinate
     * @param bitmap - picture to create part of image bitmap
     *
     * @return Bitmap part of picture with given coordinates
     */
    public Bitmap getImageBitmap(int first, int second, Bitmap bitmap) {
        int i = greed[first][second].getX() * puzzleSize;
        int j = greed[first][second].getY() * puzzleSize;

        return getPartBitmap(bitmap, i, j, i + puzzleSize, j + puzzleSize);
    }

    /**
     * Get greed puzzle size
     *
     * @return int size in puzzles
     */
    public int getSize() {
        return greedSize;
    }


    private static Bitmap getPartBitmap(Bitmap bitmap, int x1, int y1, int x2, int y2) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(x1, y1, x2, y2);
        final RectF rectF = new RectF(rect);
        final float roundPx = 0;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}