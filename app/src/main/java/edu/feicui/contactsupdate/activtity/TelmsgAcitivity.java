package edu.feicui.contactsupdate.activtity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

import edu.feicui.contactsupdate.R;
import edu.feicui.contactsupdate.adapter.TelClassAdapter;
import edu.feicui.contactsupdate.base.BaseActionbarActivity;
import edu.feicui.contactsupdate.base.BaseActivity;
import edu.feicui.contactsupdate.bean.TelclassInfo;
import edu.feicui.contactsupdate.db.AssetsDBManager;
import edu.feicui.contactsupdate.db.DBRead;
import edu.feicui.contactsupdate.utils.ToastUtil;

/**
 * Created by ☆刘群☆ on 2016/5/3.
 */
public class TelmsgAcitivity extends BaseActionbarActivity implements AdapterView.OnItemClickListener {

    public static final String IDX = "idx";
    private ListView        mListView;
    private TelClassAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telmsg);
        setActionBar(R.string.tel_title, R.drawable.btn_homeasup_default, NULL_ID);

        initView();
    }

    protected void initView() {
        mListView = (ListView) findViewById(R.id.lv_list);

        mAdapter = new TelClassAdapter(this);

        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(this);
    }

    private void initAppDBFile() {
        if (!DBRead.isExitsTeldbFile()) {
            try {
                AssetsDBManager.copyAssetsFileToFile(getApplicationContext(), "db/commonnum.db", DBRead.telFile);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(TelmsgAcitivity.this, "初始化通讯大全数据库文件异常", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        initAppDBFile();
        mAdapter.ClearDataToAdapter();
        mAdapter.addDataToAdapter1(new TelclassInfo("本地电话", 0));

        try {
            mAdapter.addDataToAdapter(DBRead.readTeldbClasslist());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (position == 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL);
            startActivity(intent);
            return;

        }

        TelclassInfo telclassInfo = mAdapter.getItem(position);

//        Intent intent=new Intent(this,TellistActivity.class);
//
//        intent.putExtra(IDX,telclassInfo.idx);
//        startActivity(intent);

        Bundle bundle = new Bundle();

        bundle.putInt(IDX, telclassInfo.idx);

        startActivity(TellistActivity.class, bundle);
    }

    private long preTime = 0;
    private long curTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            curTime = System.currentTimeMillis();
            if (curTime - preTime <= 800) {
                finish();
                System.exit(0);
            }
            ToastUtil.show(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT);
            preTime = curTime;
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
