package edu.umich.dstudio.model;

import android.location.Location;

import com.firebase.client.Firebase;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import edu.umich.dstudio.utils.Constants;
import edu.umich.dstudio.utils.GSharedPreferences;
import edu.umich.dstudio.utils.Utils;

/**
 * Created by neera_000 on 3/27/2016.
 */
public class FirebaseWrapper {

    private GoogleApiClient mGoogleApiClient;
    private static FirebaseWrapper mInstance;

    private FirebaseWrapper(GoogleApiClient aGoogleApiClient) {
        mGoogleApiClient = aGoogleApiClient;
    }

    public static FirebaseWrapper getInstance(GoogleApiClient aGoogleApiClient) {
        if(mInstance == null) {
            mInstance = new FirebaseWrapper(aGoogleApiClient);
        }
        return mInstance;
    }

    public static void uploadMood(Mood m) {
        String userEmail = Utils.encodeEmail(GSharedPreferences.getInstance().getPreference(Constants.ID_SHAREDPREF_EMAIL));
        Firebase moodListRef = new Firebase(Constants.FIREBASE_URL_MOODS).child(userEmail);
        /* Create a new node and get a random id */
        Firebase newMoodRef = moodListRef.push();
        newMoodRef.setValue(m);
    }

    public static void uploadNote(Note n) {
        String userEmail = Utils.encodeEmail(GSharedPreferences.getInstance().getPreference(Constants.ID_SHAREDPREF_EMAIL));
        Firebase notesListRef = new Firebase(Constants.FIREBASE_URL_NOTES).child(userEmail);
        /* Create a new node and get a random id */
        Firebase newNoteRef = notesListRef.push();
        newNoteRef.setValue(n);
    }

    public static void uploadPhoto(Photo p) {
        String userEmail = Utils.encodeEmail(GSharedPreferences.getInstance().getPreference(Constants.ID_SHAREDPREF_EMAIL));
        Firebase photosListRef = new Firebase(Constants.FIREBASE_URL_PHOTOS).child(userEmail);
        /* Create a new node and get a random id */
        Firebase newPhotoRef = photosListRef.push();
        newPhotoRef.setValue(p);
    }
}