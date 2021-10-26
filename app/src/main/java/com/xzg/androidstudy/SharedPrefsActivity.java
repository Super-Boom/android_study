package com.xzg.androidstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xzg.androidstudy.ui.LoginFragment;

public class SharedPrefsActivity extends AppCompatActivity {

    private EditText accEdt, pwdEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefs);

        Button sharePrefsBtn = findViewById(R.id.share_preference_show);
        Button storageOutBtn = findViewById(R.id.storage_out);
        Button InternalBtn = findViewById(R.id.storage_inner);
        sharePrefsBtn.setOnClickListener(new ClickHandler());
        storageOutBtn.setOnClickListener(new ClickHandler());
        InternalBtn.setOnClickListener(new ClickHandler());
    }


    class ClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            LoginFragment loginFragment = new LoginFragment();
            ExternalFragment externalFragment = new ExternalFragment();
            InternalFragment internalFragment = new InternalFragment();

            if (id == R.id.share_preference_show) {
                fragmentTransaction.replace(R.id.share_preference, loginFragment).commit();
            } else if (id == R.id.storage_out) {
                fragmentTransaction.replace(R.id.share_preference, externalFragment).commit();
            } else if(id==R.id.storage_inner){
                fragmentTransaction.replace(R.id.share_preference, internalFragment).commit();
            }
        }
    }
}