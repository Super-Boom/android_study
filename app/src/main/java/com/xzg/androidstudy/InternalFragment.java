package com.xzg.androidstudy;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.xzg.androidstudy.utils.DataReadAndWrite;

import java.io.File;
import java.io.IOException;


public class InternalFragment extends Fragment {
    EditText editText;
    DataReadAndWrite dataReadAndWrite;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataReadAndWrite = new DataReadAndWrite();
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_internal, container, false);

        Button saveBtn = view.findViewById(R.id.internal_save);
        Button readBtn = view.findViewById(R.id.internal_read);

        saveBtn.setOnClickListener(new ClickHandler());
        readBtn.setOnClickListener(new ClickHandler());


        editText = view.findViewById(R.id.internal_txt);
        return view;
    }

    class ClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            File file = new File(context.getFilesDir(), "getFilesDir.txt");
            if (id == R.id.internal_save) {
                // 写入到内部存储
                String saveStr = editText.getText().toString();
                dataReadAndWrite.writeTextData(file, saveStr);
            } else if (id == R.id.internal_read) {
                String data = dataReadAndWrite.getData(file);
                if (data != null) {
                    editText.setText(data);
                }
            }
        }
    }
}