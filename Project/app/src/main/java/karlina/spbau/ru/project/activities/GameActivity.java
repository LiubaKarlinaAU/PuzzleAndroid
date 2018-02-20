package karlina.spbau.ru.project.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import karlina.spbau.ru.project.Controller;
import karlina.spbau.ru.project.R;
import karlina.spbau.ru.project.storageClasses.SettingStorage;

/**
 * This class is representation of game activity.
 * Contains button to control music and play greed.
 */

public class GameActivity extends AppCompatActivity {
    private SettingStorage storage;
    private final MediaPlayer media = new MediaPlayer();
    private Controller controller;
    private final int PICK_IMAGE = 2;

    /**
     * Method allow user to pick image and sets audio if it was chosen before
     *
     * @param savedInstanceState - to load previous data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        storage = new SettingStorage(this.getIntent());

        Intent imagePicker = new Intent(Intent.ACTION_PICK);
        imagePicker.setType("image/*");
        startActivityForResult(imagePicker, PICK_IMAGE);

        if (storage.getAudioUri() != null) {
            media.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                media.setDataSource(getApplicationContext(), storage.getAudioUri());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                media.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            media.start();
            media.setLooping(true);

            Switch switchButton = (Switch) findViewById(R.id.switch_button);

            //Set a CheckedChange Listener for Switch Button
            switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton cb, boolean on) {
                    if (on) {
                        //Do something when Switch button is on/checked
                        if (media != null && !media.isPlaying()) {
                            media.start();
                            media.setLooping(true);
                        }
                    } else {
                        //Do something when Switch is off/unchecked
                        if (media != null && media.isPlaying()) {
                            media.stop();
                            media.release();
                        }
                    }
                }
            });
        }
    }

    public static Bitmap getScaleBitmap(Bitmap bitmap, int x1, int y1, int x2, int y2) {

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

    /**
     * Starts when activity give a result
     * Get image uri to make a bitmap
     *
     * @param requestCode    - code of what we need
     * @param resultCode     - code of result
     * @param returnedIntent - an intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnedIntent) {
        super.onActivityResult(requestCode, resultCode, returnedIntent);

        switch (requestCode) {
            case PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    Uri imageUri = returnedIntent.getData();
                    final InputStream imageStream;
                    //ImageView imageView = (ImageView) findViewById(R.id.imageView);
                    try {
                        imageStream = getContentResolver().openInputStream(imageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                        controller = new Controller(storage.getComplexity(), bitmap, this);
                        controller.Draw();
//                        imageView.setImageBitmap(getScaleBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight()/2));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }
}