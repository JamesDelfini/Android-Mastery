package com.delfini.android_mastery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView lvUsers;
    RecyclerView rvUsers;
    RecyclerView.Adapter rvUsersAdapter;
    List<SampleRecyclerViewItems> sampleItems = new ArrayList<>();

    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvUsers = findViewById(R.id.usersLV);
        rvUsers = findViewById(R.id.usersRV);

        btnAdd = findViewById(R.id.addBTN);

        // Sample for using ListView with dynamic data
        final ArrayList<String> dataList = new ArrayList<>();
        dataList.add("LoL");
        dataList.add("WTF");
        ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, R.layout.txt_lv_dynamic, R.id.dynamicTXTLV, dataList);
        lvUsers.setAdapter(adapter);

        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast(dataList.get(position));
            }
        });

        // Sample for using RecyclerView with dynamic data
        // Setting up RV
        rvUsers.setHasFixedSize(true);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));

        for(int i=1; i<=5; i++){
            SampleRecyclerViewItems sampleItem = new SampleRecyclerViewItems("lol " + i, "lel");
            sampleItems.add(sampleItem);
            rvUsersAdapter = new SampleRecyclerViewAdapter(sampleItems, MainActivity.this);
            rvUsers.setAdapter(rvUsersAdapter);
        }

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addBTN:

                break;
        }
    }

    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
