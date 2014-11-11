package com.marakana.android.yamba;

import android.app.Activity;
import android.os.Bundle;
/**
 * Created by DapurMasak07 on 11/10/14.
 */
public class SettingsActivity extends Activity{

    @Override
        protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            SettingsFragment fragment = new SettingsFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }

}
