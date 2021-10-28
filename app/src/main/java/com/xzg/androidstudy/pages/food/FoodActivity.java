package com.xzg.androidstudy.pages.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xzg.androidstudy.R;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        // 给按钮绑定点击事件
        Button addFoodBtn = findViewById(R.id.add_food);

        addFoodBtn.setOnClickListener(new ClickHandler());
    }


    public class ClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.add_food) {
                Intent intent = new Intent(FoodActivity.this, AddFood.class);
                startActivity(intent);
            }
        }
    }
}