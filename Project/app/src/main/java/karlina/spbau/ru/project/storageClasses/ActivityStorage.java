package karlina.spbau.ru.project.storageClasses;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by liuba on 27.12.17.
 */

public class ActivityStorage {
    private static final String compHash = "complexity";
    private static final String audioHash = "audioUri";
    private int complexity = -1;
    private Uri audioUri = null;

    public void setComlexity(int value) {
        complexity = value;
    }

    public void setAudioUri(Uri uri){
        audioUri = uri;
    }

    public boolean readyForGame() {
        return audioUri != null && complexity != -1;
    }

    public void putDataToIntent(Intent intent) {
        intent.putExtra(compHash, complexity);
        intent.putExtra(audioHash, audioUri);
    }

    public boolean loadData(Intent intent) {
        complexity = intent.getIntExtra(compHash, -1);
        audioUri = intent.getParcelableExtra(audioHash);

        return audioUri != null && complexity != -1;
    }

    public int getComplexity() {
        return complexity;
    }

    public Uri getAudioUri(){
        return audioUri;
    }
}
