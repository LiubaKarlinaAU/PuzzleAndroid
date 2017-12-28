package karlina.spbau.ru.project;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;

/**
 * Created by liuba on 15.11.17.
 */

public class Controller {
    private GameLogic logic;
    private Bitmap bitmap;
    private Uri imageUri;
    private Canvas canvas;

    public Controller(Uri uri, int size, Canvas can){
        imageUri = uri;
        canvas = can;
        logic = new GameLogic(canvas, size);
    }

    public boolean touchAt(float x, float y) {
        return logic.touchAt(x, y);
    }



}
