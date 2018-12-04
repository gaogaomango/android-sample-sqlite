package jp.co.mo.sqlitedatabasesample;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUserdName;
    private EditText mUserdPassword;
    private Button mSaveBtn;
    private Button mLoadBtn;
    private ListView listView;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(this);

        mUserdName = findViewById(R.id.userName);
        mUserdPassword = findViewById(R.id.userPassword);
        mSaveBtn = findViewById(R.id.saveBtn);
        mSaveBtn.setOnClickListener(this);
        mLoadBtn = findViewById(R.id.loadBtn);
        mLoadBtn.setOnClickListener(this);
        listView = findViewById(R.id.userList);
    }


    @Override
    public void onClick(View v) {
        if (dbManager != null) {
            switch (v.getId()) {
                case R.id.saveBtn:
                    if (!TextUtils.isEmpty(mUserdName.getText()) && !TextUtils.isEmpty(mUserdPassword.getText())) {
                        ContentValues values = new ContentValues();
                        values.put(DBManager.COL_USER_NAME, mUserdName.getText().toString());
                        values.put(DBManager.COL_USER_PASSWOED, mUserdPassword.getText().toString());
                        long id = dbManager.insertData(values);
                        if (id > 0) {
                            Toast.makeText(getApplicationContext(), "save complete! id: " + String.valueOf(id), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "save failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                    break;
                case R.id.loadBtn:
                    loadElements();
                    break;
            }
        }
    }

    public void loadElements() {
        List<Users> list = new ArrayList<>();
        Cursor cursor = dbManager.query(null, null, null, DBManager.COL_USER_NAME);
        if (cursor.moveToFirst()) {
            do {
                list.add(new Users(Integer.valueOf(cursor.getString(cursor.getColumnIndex(DBManager.COL_ID))).intValue(),
                        cursor.getString(cursor.getColumnIndex(DBManager.COL_USER_NAME)),
                        cursor.getString(cursor.getColumnIndex(DBManager.COL_USER_PASSWOED))));
            } while (cursor.moveToNext());
        }
        UserItemAdapter userItemAdapter = new UserItemAdapter(this, list);
        listView.setAdapter(userItemAdapter);
    }
}
