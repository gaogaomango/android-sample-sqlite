package jp.co.mo.sqlitedatabasesample;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

public class UserItemAdapter extends BaseAdapter {

    List<Users> mListData;
    WeakReference<Activity> mActivity;

    public UserItemAdapter(Activity activity, List<Users> listData) {
        this.mActivity = new WeakReference<>(activity);
        this.mListData = listData;
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

        ((TextView) view.findViewById(R.id.userId)).setText(String.valueOf(users.getId()));
        ((TextView) view.findViewById(R.id.showUserName)).setText(users.getUserName());
        ((TextView) view.findViewById(R.id.showUserPassword)).setText(users.getUserPassword());

        return view;
    }
}
