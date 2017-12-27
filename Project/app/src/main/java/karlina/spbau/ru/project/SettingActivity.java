package karlina.spbau.ru.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SettingActivity extends AppCompatActivity {

    private ImageView imageView;
    private final int Pick_image = 1;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        imageView = (ImageView) findViewById(R.id.smallImageView);

        Button pickPicture = (Button) findViewById(R.id.picture);
/*
        pickPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(, GameActivity.class);
                startActivity(intent);

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, Pick_image);
            }
        });*/
        Button playButton = (Button) findViewById(R.id.play_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, GameActivity.class);

                String string = "abc";
                intent.putExtra("string", string);
                intent.putExtra("bitmap", bitmap);


                startActivity(intent);
            }
        });


    }

}
