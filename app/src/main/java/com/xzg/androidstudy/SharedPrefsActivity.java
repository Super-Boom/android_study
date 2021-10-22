package com.xzg.androidstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
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
        sharePrefsBtn.setOnClickListener(new ClickHandler());
    }


    class ClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if(!fragmentTransaction.isEmpty()){
                fragmentTransaction.remove();
            }
            if (id == R.id.share_preference_show) {


                LoginFragment loginFragment = new LoginFragment();
                fragmentTransaction.add(R.id.share_preference, loginFragment).commit();
            } else if(id==R.id.storage_out){

            }
        }
    }
}