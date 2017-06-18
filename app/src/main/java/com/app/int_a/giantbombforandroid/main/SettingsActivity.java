package com.app.int_a.giantbombforandroid.main;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.app.int_a.giantbombforandroid.R;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_settings);
        //setupActionBar();

        // Display the fragment as the main content.
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }


    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            super.onActivityCreated(savedInstanceState);

            // Load the preferences from an XML resource
            setPreferencesFromResource(R.xml.preferences, rootKey);
        }
    }

}