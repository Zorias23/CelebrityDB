package com.example.admin.celebritydb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    Button btnAdd,btnView;
    EditText etFirstName;
    EditText etLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //String sql = "update Celebs set favorites = 'Y' where First = 'Kobe'";
        etFirstName = findViewById(R.id.firstName);
        etLastName = findViewById(R.id.lastName);
        btnAdd = findViewById(R.id.addCeleb);
        btnView = findViewById(R.id.viewCelebs);
        myDB = new DatabaseHelper(this);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewCelebrities.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String favorite = "N"; //this is the defaul value we're saving for now
                Celebrity c = new Celebrity(firstName, lastName, favorite);
                long returnVal = 0;
               returnVal = myDB.savePerson(c);
               if (returnVal > 0)
               {
                   Toast.makeText(MainActivity.this, "Successfully entered data!", Toast.LENGTH_LONG).show();
               }
               else
               {
                   Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
               }
                etFirstName.setText("");
                etLastName.setText("");
            }
        });
    }
}
