package com.xzg.androidstudy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xzg.androidstudy.utils.DataReadAndWrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExternalFragment extends Fragment {

    DataReadAndWrite dataReadAndWrite = new DataReadAndWrite();
    EditText infoEdt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_external, container, false);

        Button saveBtn = view.findViewById(R.id.save);
        Button readBtn = view.findViewById(R.id.read);
        // 点击储存按钮保存文本
        saveBtn.setOnClickListener(new ClickHandler());
        // 点击读取按钮读取文本
        readBtn.setOnClickListener(new ClickHandler());
        infoEdt = view.findViewById(R.id.external_txt);
        return view;
    }

    class ClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(folder, "xzg.txt");
            if (id == R.id.save) {
                String txt = infoEdt.getText().toString();
                dataReadAndWrite.writeTextData(file, txt);
            } else if (id == R.id.read) {
                String data = dataReadAndWrite.getData(file);
                if (data != null) {
                    infoEdt.setText(data);
                }
            }
        }
    }



}