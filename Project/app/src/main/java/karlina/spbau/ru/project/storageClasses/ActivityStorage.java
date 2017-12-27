package karlina.spbau.ru.project.storageClasses;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by liuba on 27.12.17.
 */

public class ActivityStorage {
    private static final String compHash = "complexity";
    private static final String audioHash = "audioUri";
    private static final String imageHash = "imageUri";
    private int complexity = -1;
    private Uri imageUri = null;
    private Uri audioUri = null;

    public void setComlexity(int value) {
        complexity = value;
    }

    public void setImageUri(Uri uri) {
        imageUri = uri;
    }

    public void setAudioUri(Uri uri){
        audioUri = uri;
    }

    public boolean readyForGame() {
        return audioUri != null && imageUri != null && complexity != -1;
    }

    public void putData(Intent intent) {
        intent.putExtra(compHash, complexity);
        intent.putExtra(audioHash, audioUri);
        intent.putExtra(imageHash, imageUri);
    }

    public boolean loadData(Intent intent) {
        complexity = intent.getIntExtra(compHash, -1);
        audioUri = intent.getParcelableExtra(audioHash);
        imageUri = intent.getParcelableExtra(imageHash);

        return audioUri != null && imageUri != null && complexity != -1;
    }

    public int getComplexity() {
        return complexity;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public Uri getAudioUri(){
        return audioUri;
    }
}
