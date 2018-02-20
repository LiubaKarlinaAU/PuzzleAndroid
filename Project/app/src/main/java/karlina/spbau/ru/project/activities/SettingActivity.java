package karlina.spbau.ru.project.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import karlina.spbau.ru.project.R;
import karlina.spbau.ru.project.storageClasses.SettingStorage;

/**
 * This class is representation of setting activity.
 * Contains setting level and music and play button.
 */
public class SettingActivity extends AppCompatActivity {
    private static final int PICK_AUDIO = 1;
    private final SettingStorage storage = new SettingStorage();

    /**
     * Method sets listeners on activity buttons
     *
     * @param savedInstanceState - to load previous data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        final Intent intent = new Intent(SettingActivity.this, GameActivity.class);

        Button musicButton = (Button) findViewById(R.id.music_button);

        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent musicPicker = new Intent();
                musicPicker.setType("audio/*");
                musicPicker.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(musicPicker, PICK_AUDIO);
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
                            "Choose level, please", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    /**
     * Starts when radio button is pressed
     * Set complexity level to setting storage
     *
     * @param view - to get result
     */
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.level5:
                if (checked)
                    storage.setComlexity(5);
                break;
            case R.id.level10:
                if (checked)
                    storage.setComlexity(10);
                break;
        }
    }

    /**
     * Starts when activity give a result
     * Set audio uri to setting storage
     *
     * @param requestCode    - code of what we need
     * @param resultCode     - code of result
     * @param returnedIntent - an intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnedIntent) {
        super.onActivityResult(requestCode, resultCode, returnedIntent);

        switch (requestCode) {
            case PICK_AUDIO:
                if (resultCode == RESULT_OK) {
                    storage.setAudioUri(returnedIntent.getData());
                }
                break;
        }
    }
}