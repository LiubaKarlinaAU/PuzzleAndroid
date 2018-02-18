package karlina.spbau.ru.project.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import karlina.spbau.ru.project.Controller;
import karlina.spbau.ru.project.R;
import karlina.spbau.ru.project.storageClasses.ActivityStorage;

import static android.provider.MediaStore.Images.Thumbnails.getThumbnail;

public class GameActivity extends AppCompatActivity {
    private Bitmap bitmap;
    private Bitmap imageBitmap;
    private Canvas canvas;
    private View myCanvasView;
    private ActivityStorage storage = new ActivityStorage();
    private ImageView imageView;
    private MediaPlayer media = new MediaPlayer();
    private Controller controller;
    private static boolean firstTime = true;
    private final int Pick_image = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        storage.loadData(this.getIntent());

        Intent imagePicker = new Intent(Intent.ACTION_PICK);
        imagePicker.setType("image/*");
        startActivityForResult(imagePicker, Pick_image);

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

    public class MyCanvas extends View {
        public MyCanvas(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            super.onDraw(canvas);
            /*
            Paint pBackground = new Paint();
            pBackground.setColor(Color.WHITE);
            canvas.drawRect(0, 0, 512, 512, pBackground);
            Paint pText = new Paint();
            pText.setColor(Color.BLACK);
            pText.setTextSize(20);
            canvas.drawText("Sample Text", 100, 100, pText);
            */
            Rect rectangle = new Rect(0, 0, 100, 100);
            //canvas.drawBitmap(imageBitmap, null, rectangle, null);
        }
    }
/*
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (firstTime) {
            //do anything you want here
            myCanvasView = new MyCanvas(getApplicationContext());
            bitmap = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(bitmap);
            myCanvasView.draw(canvas);
            //imageView.setImageBitmap(bitmap);
            controller = new Controller(storage.getImageUri(), storage.getComplexity(), canvas);
            firstTime = false;
        }
    }*/

/*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bitmap.recycle();
        bitmap = null;
        media.release();
    }

    private void redraw() {
        myCanvasView.draw(canvas);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        if (controller.touchAt(ev.getX(), ev.getY())) {
            redraw();
        }

        return true;
    } */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnedIntent) {
        super.onActivityResult(requestCode, resultCode, returnedIntent);

        switch (requestCode) {
            case Pick_image:
                if (resultCode == RESULT_OK) {
                    Uri imageUri = returnedIntent.getData();
                    final InputStream imageStream;
                    ImageView imageView = (ImageView) findViewById(R.id.imageView);
                    try {
                        imageStream = getContentResolver().openInputStream(imageUri);
                        bitmap = BitmapFactory.decodeStream(imageStream);
                        imageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }
}
