package com.xzg.androidstudy.pages.student_room_example;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.xzg.androidstudy.R;
import com.xzg.androidstudy.adapter.StudentAdapter;
import com.xzg.androidstudy.database.MyDatabase;
import com.xzg.androidstudy.entity.Student;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

    private MyDatabase myDatabase;
    private List<Student> studentList;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);

        // 显示弹窗
        findViewById(R.id.btnInsertStudent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddStudentDialog();
            }
        });

        // 点击每一个同学
        ListView listView = findViewById(R.id.lvStudent);
        studentList = new ArrayList<>();
        studentAdapter = new StudentAdapter(StudentActivity.this, studentList);
        listView.setAdapter(studentAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                updateOrDeleteDialog(studentList.get(position));
                return false;
            }
        });
        myDatabase = MyDatabase.getInstance(this);
        new QueryStudentTask().execute();
    }


    // 更新或删除
    private void updateOrDeleteDialog(final Student student) {
        final String[] options = new String[]{"更新", "删除"};
        new AlertDialog.Builder(StudentActivity.this).setTitle("").setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    // 更新
                    openUpdateStudentDialog(student);
                } else if (which == 1) {
                    // 删除
                    new DeleteStudentTask(student).execute();
                }
            }
        }).show();
    }

    // 添加学生对话框
    private void openUpdateStudentDialog(final Student student) {
        if (student == null) {
            return;
        }
        View customView = this.getLayoutInflater().inflate(R.layout.dialog_layout_student, null);
        final EditText etName = customView.findViewById(R.id.etName);
        final EditText etAge = customView.findViewById(R.id.etAge);

        etName.setText(student.name);
        etAge.setText(student.age);

        final AlertDialog.Builder builder = new AlertDialog.Builder(StudentActivity.this);
        AlertDialog dialog = builder.create();

        dialog.setTitle("更新同学信息");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etAge.getText().toString())) {
                    Toast.makeText(StudentActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("------","UpdateStudentTask");
                    new UpdateStudentTask(student.id, etName.getText().toString(), etAge.getText().toString());
                }
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setView(customView);
        dialog.show();
    }

    private void openAddStudentDialog() {
        View customView = this.getLayoutInflater().inflate(R.layout.dialog_layout_student, null);
        final EditText etName = customView.findViewById(R.id.etName);
        final EditText etAge = customView.findViewById(R.id.etAge);

        final AlertDialog.Builder builder = new AlertDialog.Builder(StudentActivity.this);
        AlertDialog dialog = builder.create();

        dialog.setTitle("Add Student");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 输入的同学名称或者年龄为空则弹出提示
                if (TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etAge.getText().toString())) {
                    Toast.makeText(StudentActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    // 更新同学信息
                    new InsertStudentTask(etName.getText().toString(), etAge.getText().toString()).execute();
                }
            }
        });

        // 点击取消隐藏弹窗
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setView(customView);
        dialog.show();
    }


    private class InsertStudentTask extends AsyncTask<Void, Void, Void> {
        String name;
        String age;

        public InsertStudentTask(final String name, final String age) {
            this.name = name;
            this.age = age;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            myDatabase.studentDao().insertStudent(new Student(name, age));
            studentList.clear();
            studentList.addAll(myDatabase.studentDao().getStudentList());
            return null;
        }
    }

    // 更新同学信息
    private class UpdateStudentTask extends AsyncTask<Void, Void, Void> {

        int id;
        String name;
        String age;
        Student student;
        public UpdateStudentTask(final int id, final String name, final String age) {
            Log.d("------","执行UpdateStudentTask方法");
            this.id = id;
            this.name = name;
            this.age = age;
            student = new Student(id,name,age);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("*******","UpdateStudentTask");
            myDatabase.studentDao().updateStudent(student);
            studentList.clear();
            studentList.addAll(myDatabase.studentDao().getStudentList());
            return null;
        }
    }


    private class DeleteStudentTask extends AsyncTask<Void, Void, Void> {
        Student student;

        public DeleteStudentTask(Student student) {
            Log.d("------","删除");
            this.student = student;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            myDatabase.studentDao().deleteStudent(student);
            studentList.clear();
            studentList.addAll(myDatabase.studentDao().getStudentList());
            return null;
        }
    }


    private class QueryStudentTask extends AsyncTask<Void, Void, Void> {

        public QueryStudentTask() {

        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentList.clear();
            studentList.addAll(myDatabase.studentDao().getStudentList());
            return null;
        }
    }
}