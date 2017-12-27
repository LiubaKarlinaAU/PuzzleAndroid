package karlina.spbau.ru.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import karlina.spbau.ru.project.storageClasses.ActivityStorage;

import static android.provider.MediaStore.Images.Thumbnails.getThumbnail;

public class GameActivity extends AppCompatActivity {
    private Bitmap bitmap;
    private ActivityStorage storage = new ActivityStorage();
    private ImageView imageView;
    private MediaPlayer media = new MediaPlayer();
    private Uri imageUri;
    private Uri audioUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        storage.loadData(this.getIntent());

        imageView = (ImageView) findViewById(R.id.imageView);

        imageUri = storage.getImageUri();
        audioUri = storage.getAudioUri();
        media.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            media.setDataSource(getApplicationContext(), audioUri);
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
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on)
                {
                    //Do something when Switch button is on/checked
                    if (media != null && !media.isPlaying()) {
                        media.start();
                        media.setLooping(true);
                    }
                }
                else
                {
                    //Do something when Switch is off/unchecked
                    if(media != null && media.isPlaying())
                    {
                        media.stop();
                        media.release();
                    }
                }
            }
        });
        
        final InputStream imageStream;
        try {
            imageStream = getContentResolver().openInputStream(imageUri);
            bitmap = BitmapFactory.decodeStream(imageStream);
            imageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
