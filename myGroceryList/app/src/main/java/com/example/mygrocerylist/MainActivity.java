package com.example.mygrocerylist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    ListView listView; //listView is
    String names []={
            "1. Coffee                          rs. 48",
            "2. Dal                              rs. 148",
            "3. Milk                               rs. 63",
            "4. Oreo Biscuit                 rs. 30",
            "5. Chips                            rs. 20",
            "6. Maggie                         rs. 40",
            "7. Ketchup                     rs. 125",
            "8. Shampoo                   rs. 105"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.lpu);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.activity_adapter_file, R.id.textview, names);
        //creating an object of the array adapter
        listView.setAdapter(arrayAdapter); //making the view listen
    }
}