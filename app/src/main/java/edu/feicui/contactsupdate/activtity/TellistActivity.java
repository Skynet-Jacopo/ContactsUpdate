package edu.feicui.contactsupdate.activtity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.feicui.contactsupdate.R;
import edu.feicui.contactsupdate.adapter.TelNumberAdapter;
import edu.feicui.contactsupdate.base.BaseActionbarActivity;
import edu.feicui.contactsupdate.bean.TelNumberInfo;
import edu.feicui.contactsupdate.db.DBRead;

public class TellistActivity extends BaseActionbarActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;

    private TelNumberAdapter mAdapter;

    int idx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tellist);

        initView();

    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview);

        mAdapter = new TelNumberAdapter(this);

        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(this);

        idx = getIntent().getIntExtra(TelmsgAcitivity.IDX, -1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            mAdapter.addDataToAdapter(DBRead.readTeldbTable(idx));

        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            mAdapter.addDataToAdapter(DBRead.readTeldbTable(idx));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String name = mAdapter.getItem(position).name;
        String number = mAdapter.getItem(position).number;

        showCallDialog(name, number);
    }

    private void showCallDialog(String name, final String number) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("警告!")

                .setMessage("您将要拨打" + name + "的电话号码\n\nTel:" + number).setMessage("刘承宇比较花心")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number));
                startActivity(intent);
            }
        }).create();
        builder.show();
    }
}
