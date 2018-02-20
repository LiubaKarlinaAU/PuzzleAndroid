package karlina.spbau.ru.project;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;


import karlina.spbau.ru.project.storageClasses.Greed;

/**
 * This class is needed for managing all game.
 * It contains picture(bitmap) and array of image views and puzzle greed.
 */
public class Controller {
    private Bitmap bitmap;
    private Greed greed;
    private ImageView imageGreed[][];

    /**
     * Constructor that initialize correct all class fields
     *
     * @param sizeInPuzzle - to load data
     * @param picture - picture to store for making puzzles
     * @param activity - current activity to create image views array
     */
    public Controller(int sizeInPuzzle, Bitmap picture, Activity activity) {
        bitmap = picture;
        imageGreed = new ImageView[sizeInPuzzle][sizeInPuzzle];
        greed = new Greed(sizeInPuzzle, bitmap.getWidth());
        createImageViews(activity);
    }

    /**
     * Initialization of imageViews
     * Creats layouts in given activity and image views in them
     *
     * @param activity - current activity to create there
     */
    public void createImageViews(Activity activity) {
        LinearLayout layout = (LinearLayout) activity.findViewById(R.id.imageLayout);
        for (int i = 0; i < greed.getSize(); ++i) {
            LinearLayout currentLayout = new LinearLayout(activity);
            LinearLayout.LayoutParams currentLP = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            currentLayout.setLayoutParams(currentLP);
            layout.addView(currentLayout);

            for (int j = 0; j < greed.getSize(); ++j) {
                imageGreed[i][j] = new ImageView(activity);
                LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                imageGreed[i][j].setLayoutParams(lp);
                currentLayout.addView(imageGreed[i][j]);
            }
        }
    }

    /**
     * Draw all image views from array use Greed method setImageBitmap
     */
    public void Draw() {
        for (int i = 0; i < greed.getSize(); ++i)
            for (int j = 0; j < greed.getSize(); ++j)
                imageGreed[i][j].setImageBitmap(greed.getImageBitmap(i, j, bitmap));
/*
        imageGreed[1][1].setImageBitmap(greed.getImageBitmap(1, 1, bitmap));
        imageGreed[0][1].setImageBitmap(greed.getImageBitmap(0, 1, bitmap));
*/
    }
}
