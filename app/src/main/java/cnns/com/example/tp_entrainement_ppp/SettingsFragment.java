package cnns.com.example.tp_entrainement_ppp;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey) {
        addPreferencesFromResource(R.xml.pref_exemple);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

        // RESULT ON LOAD
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int count = preferenceScreen.getPreferenceCount();

        //Parcours des références
        for (int i = 0; i < count; i++) {
            Preference p = preferenceScreen.getPreference(i);
            //METTRE UN RESUME AUX EDIT TEXTS
            if (p instanceof EditTextPreference) {
                String value = sharedPreferences.getString(p.getKey(), "");
                p.setSummary(value);
            }
        }
    }
    @Override
    public void onPause(){
        super.onPause();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
    @Override
    public void onResume(){
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        System.out.println("PASSE ECOUTEUR PREF");
        // RESULT ON EDIT

        sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int count = preferenceScreen.getPreferenceCount();

        //Parcours des références
        for (int i = 0; i < count; i++) {
            Preference p = preferenceScreen.getPreference(i);
            //METTRE UN RESUME AUX EDIT TEXTS
            if (p instanceof EditTextPreference) {
                String value = sharedPreferences.getString(p.getKey(), "");
                p.setSummary(value);
            }
        }
    }

}
