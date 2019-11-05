package com.example.alcoholview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.name";
    public static final String EXTRA_MESSAGE2 = "com.example.myfirstapp.phoneno";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void sendMessage(View view) {

        EditText Name = (EditText) findViewById(R.id.editText);
        EditText Phoneno = (EditText) findViewById(R.id.editText2);
        String name = Name.getText().toString();
        String phoneno = Phoneno.getText().toString();


        Intent intent = new Intent(this, DisplayValues.class);
        intent.putExtra(EXTRA_MESSAGE, name);
        intent.putExtra(EXTRA_MESSAGE2, phoneno);
        startActivity(intent);
    }
}
