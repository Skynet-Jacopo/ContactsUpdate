package edu.feicui.contactsupdate.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.contactsupdate.R;
import edu.feicui.contactsupdate.base.adapter.BaseDataAdapter;
import edu.feicui.contactsupdate.bean.TelNumberInfo;

/**
 * Created by ☆刘群☆ on 2016/5/4.
 */
public class TelNumberAdapter extends BaseDataAdapter<TelNumberInfo> {
    private static final String TAG = "TelNumberAdapter";

    private ArrayList<TelNumberInfo> adapterDatas = new ArrayList<>();

    public void addDataToAdapter(TelNumberInfo e) {
        adapterDatas.add(e);
    }

    public void addDataToAdapter(List<TelNumberInfo> e) {
        if (e != null) {
            Log.d(TAG, "addDataToAdapter: " + e);
            adapterDatas.addAll(e);
        }
    }

    public void replaceDataToAdapter(List<TelNumberInfo> e) {
        adapterDatas.clear();
        adapterDatas.addAll(e);
    }

    public ArrayList<TelNumberInfo> getDataFromAdapter() {
        return adapterDatas;
    }

    public TelNumberAdapter(Context context) {
        super(context);
    }

    @Override
    public int getCount() {
        if (adapterDatas != null) {
            return adapterDatas.size();
        }
        return 0;
    }

    @Override
    public TelNumberInfo getItem(int position) {
        return adapterDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.itemlist_inflate_tellist, null);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvNumber = (TextView) convertView.findViewById(R.id.tv_number);

        tvName.setText(getItem(position).name);
        tvNumber.setText(getItem(position).number);
        return convertView;
    }
}
