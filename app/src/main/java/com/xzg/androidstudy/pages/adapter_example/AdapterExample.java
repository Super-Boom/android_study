package com.xzg.androidstudy.pages.adapter_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.xzg.androidstudy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * adapter demo例子
 */

public class AdapterExample extends AppCompatActivity {

    // arrayAdapter 例子数据
    String[] strs = {"西门吹学", "小鱼儿", "李寻欢", "白玉京", "叶孤城"};

    // simpleAdapter 例子数据
    private String[] names = new String[]{"沈浪", "燕南天", "李寻欢", "西门吹雪", "楚留香"};
    private String[] says = new String[]{"浪子", "大侠", "情种", "杀手", "盗帅"};
    private int[] imgIds = new int[]{R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3, R.drawable.avatar4, R.drawable.avatar5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_example);


        // ArrayAdapter 例子
        // 创建 ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, strs);
        // 获取ListView对象，通过调用setAdapter方法为ListView设置Adapter设置适配器
        ListView listView = (ListView) findViewById(R.id.arrayAdapter);
        listView.setAdapter(adapter);

        // SimpleAdapter 例子
        List<Map<String, Object>> listMan = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("avatar", imgIds[i]);
            showitem.put("name", names[i]);
            showitem.put("says", says[i]);
            listMan.add(showitem);
        }
        // 创建一个simpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), listMan, R.layout.simple_adapter_list_item, new String[]{"avatar", "name", "says"}, new int[]{R.id.avatar, R.id.name, R.id.says});
        ListView simpleAdapterList = (ListView) findViewById(R.id.simpleAdapter);
        simpleAdapterList.setAdapter(simpleAdapter);
    }
}