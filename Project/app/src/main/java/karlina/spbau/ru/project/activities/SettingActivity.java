package karlina.spbau.ru.project.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;


import java.io.FileNotFoundException;
import java.io.InputStream;

import karlina.spbau.ru.project.R;
import karlina.spbau.ru.project.storageClasses.ActivityStorage;

public class SettingActivity extends AppCompatActivity {
    private final int Pick_audio = 1;

    private ActivityStorage storage = new ActivityStorage();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        intent = new Intent(SettingActivity.this, GameActivity.class);

        Button musicButton = (Button) findViewById(R.id.music_button);

        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent musicPicker = new Intent();
                musicPicker.setType("audio/*");
                musicPicker.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(musicPicker, Pick_audio);
            }
        });

        Button playButton = (Button) findViewById(R.id.play_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (storage.readyForGame()) {
                    storage.putDataToIntent(intent);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Choose all settings", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.level10:
                if (checked)
                    storage.setComlexity(10);
                break;
            case R.id.level15:
                if (checked)
                    storage.setComlexity(15);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnedIntent) {
        super.onActivityResult(requestCode, resultCode, returnedIntent);

        switch (requestCode) {
            case Pick_audio:
                if (resultCode == RESULT_OK) {
                    storage.setAudioUri(returnedIntent.getData());
                }
                break;
        }
    }
}