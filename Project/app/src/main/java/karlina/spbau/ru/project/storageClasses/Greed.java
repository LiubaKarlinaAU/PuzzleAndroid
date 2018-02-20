package karlina.spbau.ru.project.storageClasses;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by liuba on 28.12.17.
 */

public class Greed {
    private Puzzle greed[][];
    private int puzzleSize;
    private int greedSize;

    public Greed(int count, int greedS) {
        greedSize = count;
        puzzleSize = greedS / count;
        greed = new Puzzle[count][count];

        for (int i = 0; i < count; ++i)
            for (int j = 0; j < count; ++j) {
                greed[i][j] = new Puzzle(count - j, count - i);
            }
    }

    public Bitmap getImageBitmap(int viewI, int viewJ, Bitmap bitmap) {
        int i = viewI * puzzleSize;
        int j = viewJ * puzzleSize;

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