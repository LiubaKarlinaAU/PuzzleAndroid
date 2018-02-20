package karlina.spbau.ru.project.storageClasses;

import android.content.Intent;
import android.net.Uri;

/**
 * This class is needed for making exchange between setting activity and game activity.
 * It contains complexity lexel and audio uri(if it was chosen).
 */
public class SettingStorage {
    private static final String complexityKey = "complexity";
    private static final String audioKey = "audioUri";
    private int complexity = -1;
    private Uri audioUri = null;

    /**
     * Default class constructor
     */
    public SettingStorage() {
    }

    /**
     * Constructor that load data from given intent
     *
     * @param intent - to load data
     */
    public SettingStorage(Intent intent) {
        complexity = intent.getIntExtra(complexityKey, -1);
        audioUri = intent.getParcelableExtra(audioKey);
    }

    /**
     * Set given complexity
     *
     * @param value - to be set
     */
    public void setComlexity(int value) {
        complexity = value;
    }


    /**
     * Set given uri
     *
     * @param uri  - to be set
     */
    public void setAudioUri(Uri uri) {
        audioUri = uri;
    }


    /**
     * Check is storage ready for game
     *
     * @return boolean if the complexity level chosen
     */
    public boolean readyForGame() {
        return complexity != -1;
    }


    public void putDataToIntent(Intent intent) {
        intent.putExtra(complexityKey, complexity);
        intent.putExtra(audioKey, audioUri);
    }

    /**
     * Get this storage complexity level
     *
     * @return the complexity level
     */
    public int getComplexity() {
        return complexity;
    }

    /**
     * Get this storage audio uri
     *
     * @return the audio uri that can be null
     */
    public Uri getAudioUri() {
        return audioUri;
    }
}