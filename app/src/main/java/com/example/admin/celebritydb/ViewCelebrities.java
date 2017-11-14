package com.example.admin.celebritydb;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ViewCelebrities extends AppCompatActivity implements OnItemClickListener{
    DatabaseHelper myDb;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_celebrities);
        myDb = new DatabaseHelper(this);
         listView = findViewById(R.id.listView);
         myDb.getPersonsAsList();
        String[] fromColumns = {"_id",
                "LAST_NAME", "Favorite"};
        int[] toViews = {R.id.viewFirstName, R.id.viewLastName, R.id.viewFavorite};
        Cursor celebs = myDb.getPersonsAsCursor();
        if(celebs.moveToFirst())
        {
            do {
                Celebrity person = new Celebrity(celebs.getString(0), celebs.getString(1), celebs.getString(2));
                Log.d("ViewCelebrities", "getPersonsAsCursor: " + celebs.getString(0) + " " + celebs.getString(1) + " " + celebs.getString(2));
               // personList.add(person);
            }while(celebs.moveToNext());

        }
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.row, celebs, fromColumns, toViews, 0);
        listView.setAdapter(adapter);

         OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Log.d("ViewCelebrities", "onItemClick: position is  " + position + " and id is " + id);
            }
        };
        listView.setOnItemClickListener(mMessageClickedHandler);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("ViewCelebrities", "onItemClick: i is " + i + " and l is " + l);
    }
}
