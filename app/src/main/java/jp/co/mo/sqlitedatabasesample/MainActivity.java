package jp.co.mo.sqlitedatabasesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUserdName;
    private EditText mUserdPassword;
    private Button mSaveBtn;
    private Button mLoadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserdName = findViewById(R.id.userName);
        mUserdPassword = findViewById(R.id.userPassword);
        mSaveBtn = findViewById(R.id.saveBtn);
        mSaveBtn.setOnClickListener(this);
        mLoadBtn = findViewById(R.id.loadBtn);
        mLoadBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveBtn:



                break;
            case R.id.loadBtn:
                break;
        }
    }
}
