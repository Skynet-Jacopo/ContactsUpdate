package edu.feicui.contactsupdate.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ☆刘群☆ on 2016/5/4.
 *
 * 创建抽象类BaseAdapter继承BaseAdapter泛型定义为<E>
 *
 *     基础适配器,根据集合内的数据,进行适配
 */
public abstract class BaseDataAdapter<E> extends BaseAdapter {

    private ArrayList<E> adapterDatas =new ArrayList<>();

    protected Context content;

    protected LayoutInflater mInflater;//布局加载器

    public BaseDataAdapter(Context content) {
        this.content = content;
        mInflater=LayoutInflater.from(content);
    }


    @Override
    public int getCount() {
        if (adapterDatas!=null){
            return adapterDatas.size();
        }
        return 0;
    }

    @Override
    public E getItem(int position) {
        return adapterDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        return null;
//    }

    //为ArrayList<E>添加get() set()方法
    public ArrayList<E> getDataFromAdapter() {
        return adapterDatas;
    }

    public void setDataToAdapter(List<E> e) {
        adapterDatas.clear();
        if (e != null) {
            adapterDatas.addAll(e);
        }
    }

    //添加数据到当前适配器集合
    public void addDataToAdapter(E e){
        if (e != null) {
            adapterDatas.add(e);
        }
    }
    public void addDataToAdapter(List<E> e){
        if (e != null) {
            adapterDatas.addAll(e);
        }
    }

    //删除当前适配器集合内的数据
    public void clearDataFromAdapter(E e){
        adapterDatas.remove(e);
    }
    public void clearDataFromAdapter(int index){
        adapterDatas.remove(index);
    }
}
