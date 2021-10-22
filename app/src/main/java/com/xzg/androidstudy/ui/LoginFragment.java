package com.xzg.androidstudy.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xzg.androidstudy.R;
import com.xzg.androidstudy.SharedPrefsActivity;

public class LoginFragment extends Fragment {

    private EditText accEdt, pwdEdt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button btn = view.findViewById(R.id.login);

        // 获取SharePreference对象(1:文件名，2:模式)
        Activity act = getActivity();
        SharedPreferences sharedPreferences = act.getSharedPreferences("myshare", act.MODE_PRIVATE);
        // 设置默认值
        String accStr = sharedPreferences.getString("account", "");
        String pwdStr = sharedPreferences.getString("pwd", "");
        // 1.获取两个输入框的内容
        accEdt = view.findViewById(R.id.account);
        pwdEdt = view.findViewById(R.id.pwd);
        accEdt.setText(accStr);
        pwdEdt.setText(pwdStr);

        // Inflate the layout for this fragment
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String account = accEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();
                // 2.验证(admin,123)
                if (account.equals("admin") && pwd.equals("123")) {
                    // 2.1存储信息到SharePreference

                    // 获取Editor对象
                    SharedPreferences.Editor edt = sharedPreferences.edit();
                    // 存储信息
                    edt.putString("account", account);
                    edt.putString("pwd", pwd);
                    edt.apply();
                } else {
                    // 2.2验证失败，提示用户
                    Toast.makeText(getActivity(), "帐号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}