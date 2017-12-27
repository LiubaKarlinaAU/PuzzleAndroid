package karlina.spbau.ru.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.provider.MediaStore.Images.Thumbnails.getThumbnail;

public class GameActivity extends AppCompatActivity {
    private final int Pick_image = 1;
    private Bitmap bitmap;
    private ImageView imageView;
    private String path;
    private Uri imageUri;
/*
    public GameActivity() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, Pick_image);
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // TextView textView = (TextView) findViewById(R.id.textView2);
        imageView = (ImageView)findViewById(R.id.imageView);

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, Pick_image);


    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent)
        {
            super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

            switch (requestCode) {
                case Pick_image:

                    if (resultCode == RESULT_OK) {
                        try {
                            imageUri = imageReturnedIntent.getData();
                            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                            bitmap = BitmapFactory.decodeStream(imageStream);
                            //bitmap = getThumbnail(imageUri);
                            imageView.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
            }
        }
}
