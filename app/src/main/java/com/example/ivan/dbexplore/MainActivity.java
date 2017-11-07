package com.example.ivan.dbexplore;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ivan.dbexplore.data.PersonContract;
import com.example.ivan.dbexplore.data.PersonHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.ivan.dbexplore.data.PersonContract.*;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.data_display_txtV)
    TextView dataDisplayTextV;
    @BindView(R.id.first_name_etx)
    EditText firstNameEtx;
    @BindView(R.id.second_name_etx)
    EditText secondNameEtx;
    @BindView(R.id.Age_etx)
    EditText ageEdtx;
    @BindView(R.id.save_data_btn)
    Button saveDataBtn;
    @BindView(R.id.get_data_btn)
    Button getDataBtn;


    SQLiteDatabase sqLiteDatabase;
    PersonHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        helper = new PersonHelper(this);
        sqLiteDatabase = helper.getWritableDatabase();

        getDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataDisplayTextV.setText("");
                retrieveData();
            }
        });

        saveDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void retrieveData() {

        Cursor cursor = sqLiteDatabase.query(PersonEntry.TABLE_NAME,
                null, null, null, null, null, null);
        if (cursor != null) {
            StringBuilder sb = new StringBuilder();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(PersonEntry._ID));
                String fname = cursor.getString(cursor.getColumnIndex(PersonEntry.COLUMN_PERSON_FIRST_NAME));
                String sname = cursor.getString(cursor.getColumnIndex(PersonEntry.COLUMN_PERSON_SECOND_NAME));
                int age = cursor.getInt(cursor.getColumnIndex(PersonEntry.COLUMN_PERSON_AGE));
                sb.append(id + ", ");
                sb.append(fname + ", ");
                sb.append(sname + ", ");
                sb.append(age + "\n\n");
            }
            dataDisplayTextV.setText(sb.toString());
        }
        cursor.close();
    }

    private long saveData() {
        long rec = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put(PersonEntry.COLUMN_PERSON_FIRST_NAME, firstNameEtx.getText().toString());
        contentValues.put(PersonEntry.COLUMN_PERSON_SECOND_NAME, secondNameEtx.getText().toString());
        contentValues.put(PersonEntry.COLUMN_PERSON_AGE, Integer.valueOf(ageEdtx.getText().toString()));
        try {
            rec = sqLiteDatabase.insert(PersonEntry.TABLE_NAME, null, contentValues);
        } catch (SQLException ex) {
            Log.d("MySqliteException", ex.toString());
        }
        return rec;
    }
}
