package jp.co.mo.sqlitedatabasesample;

import android.app.Activity;
import android.content.ContentValues;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

public class UserItemAdapter extends BaseAdapter {

    List<Users> mListData;
    WeakReference<Activity> mActivity;
    DBManager mDBManager;

    public UserItemAdapter(Activity activity, List<Users> listData) {
        this.mActivity = new WeakReference<>(activity);
        this.mListData = listData;
        this.mDBManager = new DBManager(activity);
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = mActivity.get().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_userlist, null);
        final Users users = mListData.get(position);
        final TextView userId = view.findViewById(R.id.userId);
        userId.setText(String.valueOf(users.getId()));
        final EditText showUserName = view.findViewById(R.id.showUserName);
        showUserName.setText(users.getUserName());
        final EditText showUserPassword = view.findViewById(R.id.showUserPassword);
        showUserPassword.setText(users.getUserPassword());

        view.findViewById(R.id.deleteBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] args = {String.valueOf(users.getId())};
                mDBManager.delete(DBManager.COL_ID + " = ? ", args);
                ((MainActivity) mActivity.get()).loadElements();
            }
        });

        view.findViewById(R.id.editBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(DBManager.COL_ID, users.getId());
                values.put(DBManager.COL_USER_NAME, showUserName.getText().toString());
                values.put(DBManager.COL_USER_PASSWOED, showUserPassword.getText().toString());
                String[] args = {String.valueOf(users.getId())};
                mDBManager.update(values, DBManager.COL_ID + " = ? ", args);
            }
        });

        return view;
    }
}
