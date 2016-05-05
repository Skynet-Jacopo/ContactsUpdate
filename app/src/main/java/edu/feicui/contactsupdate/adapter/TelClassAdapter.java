package edu.feicui.contactsupdate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.contactsupdate.R;
import edu.feicui.contactsupdate.base.adapter.BaseDataAdapter;
import edu.feicui.contactsupdate.bean.TelclassInfo;

/**
 * Created by ☆刘群☆ on 2016/5/3.
 */
public class TelClassAdapter extends BaseDataAdapter<TelclassInfo> {

    public TelClassAdapter(Context context) {
        super(context);
    }

    private ArrayList<TelclassInfo> adapterDatas=new ArrayList<>();

    public void addDataToAdapter1(TelclassInfo e){
        adapterDatas.add(e);
    }
    public void addDataToAdapter(List<TelclassInfo> e){
        adapterDatas.addAll(e);
    }

    public ArrayList<TelclassInfo> getDataFromAdapter(){
        return adapterDatas;
    }

    public void ClearDataToAdapter(){
        adapterDatas.clear();
    }


    @Override
    public int getCount() {
        if (adapterDatas!=null){
            return adapterDatas.size();
        }
        return 0;
    }

    @Override
    public TelclassInfo getItem(int position) {
        return adapterDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView=mInflater.inflate(R.layout.itemlist_inflate_telmsg,null);

        }
        TextView textView= (TextView) convertView.findViewById(R.id.tv_title);

        textView.setText(getItem(position).name);
        return convertView;
    }
}
