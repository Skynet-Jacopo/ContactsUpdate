package edu.feicui.contactsupdate.base;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import edu.feicui.contactsupdate.R;

/**
 * Created by ☆刘群☆ on 2016/5/4.
 *
 * 创建抽象类BaseActionbarActivity继承BaseActivity:固定有ActionBar的Activity,便于使用
 *
 * 带ActionBar控件数据初始化方法及onClick监听的基础Activity
 */
public abstract class BaseActionbarActivity extends BaseActivity {

    public static final int NULL_ID = -1;

    private static final String TAG = "BaseActionbarActivity";

    /**
     * 设置ActionBar控件上的数据
     * @param resIdTitle
     *          中间主标题文本id,没有标题时可使用(@link #NULL_ID)
     * @param resIdLeft
     *          左侧图标资源id,没有图标是可使用(@link #NULL_ID)
     * @param resIdRight
     *          右侧图标资源id,没有图标时可使用(@link #NULL_ID)
     */
    public void setActionBar(int resIdTitle, int resIdLeft, int resIdRight) {

        try {
            TextView tv_action_title= (TextView) findViewById(R.id.tv_actionbar_title);
            ImageView iv_action_left= (ImageView) findViewById(R.id.iv_action_left);
            ImageView iv_action_right= (ImageView) findViewById(R.id.iv_action_right);
            if (resIdLeft!= NULL_ID){
                iv_action_left.setImageResource(resIdLeft);
            }else {
                iv_action_left.setVisibility(ImageView.INVISIBLE);
            }
            if (resIdRight!=NULL_ID){
                iv_action_right.setImageResource(resIdRight);
            }else {
                iv_action_right.setVisibility(View.INVISIBLE);
            }
            if (resIdTitle!=NULL_ID){
                tv_action_title.setText(resIdTitle);
            }
        }catch (Exception e){
            Log.w(TAG, "ActionBar有异常,是否当前页面并没有include==>include_actionbar.xml布局?");
        }
    }
}
