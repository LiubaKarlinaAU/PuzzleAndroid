package karlina.spbau.ru.project;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;


import karlina.spbau.ru.project.storageClasses.Greed;

/**
 * Created by liuba on 15.11.17.
 */

public class Controller {
    private Bitmap bitmap;
    private Greed greed;
    private ImageView imageGreed[][];

    public Controller(int count, Bitmap picture, Activity activity) {
        bitmap = picture;
        imageGreed = new ImageView[count][count];
        greed = new Greed(count, bitmap.getWidth());
        createImageViews(activity);
    }

    public void createImageViews(Activity activity) {
        LinearLayout layout = (LinearLayout) activity.findViewById(R.id.imageLayout);
        for (int i = 0; i < greed.getSize(); ++i) {
            LinearLayout currentLayout = new LinearLayout(activity);
            LayoutParams currentLP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
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

    public void Draw() {
        for (int i = 0; i < greed.getSize(); ++i)
            for (int j = 0; j < greed.getSize(); ++j)
                imageGreed[i][j].setImageBitmap(greed.getImageBitmap(i, j, bitmap));

        // imageGreed[3][3].setImageBitmap(greed.getImageBitmap(4, 4, bitmap));
    }
}
