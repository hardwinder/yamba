package com.marakana.android.yamba;

import android.app.Fragment;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.Intent;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;


public class StatusFragment extends Fragment implements OnClickListener {
    private static final String TAG = "StatusFragment";
    private EditText editStatus;
    private Button buttonTweet;
    private TextView textCount;
    private int defaultTextColor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_status, container, false);

        editStatus = (EditText) view.findViewById(R.id.editStatus);
        buttonTweet = (Button) view.findViewById(R.id.buttonTweet);
        textCount = (TextView) view.findViewById(R.id.textCount);

        buttonTweet.setOnClickListener(this);

        defaultTextColor = textCount.getTextColors().getDefaultColor();
        editStatus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int count = 140 - editStatus.length();
                textCount.setText(Integer.toString(count));
                textCount.setTextColor(Color.GREEN);
                if (count > 10)
                {
                    textCount.setTextColor(Color.RED);
                }
                else{
                    textCount.setTextColor(defaultTextColor);
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View view){
        String status = editStatus.getText().toString();
        Log.d(TAG, "onClicked with status: " + status);

        new PostTask().execute(status);
    }

    @Override
    protected String doInBackground(String... params){
        try{
            SharedPreferences prefs = PreferenceManager .getDefaultSharedPreferences(getActivity()); //
            String username = prefs.getString("username", ""); //
            String password = prefs.getString("password", "");
            // Check that username and password are not empty.
            // If empty, Toast a message to set login info and bounce
            // to SettingActivity.
            // Hint: TextUtils.
            if (TextUtils.isEmpty(username) ||
                    TextUtils.isEmpty(password)) { //
                    getActivity().startActivity(
                new Intent(getActivity(), SettingsActivity.class)); return "Please update your username and password";
            }
            YambaClient cloud = new YambaClient(username, password);
            cloud.postStatus(params[0]);
        } catch(YambaClientException e){
            e.printStackTrace();
            return "Failed to post yamba service";
        }
    }
    private final class PostTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params){
            YambaClient yambaCloud = new YambaClient("student", "password");
            try{
                yambaCloud.postStatus( params[0] );
                return "Successfully posted";
            } catch(YambaClientException e){
                e.printStackTrace();
                return "Failed to post yamba service";
            }
        }
        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            Toast.makeText(StatusFragment.this.getActivity(), result, Toast.LENGTH_LONG).show();
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
