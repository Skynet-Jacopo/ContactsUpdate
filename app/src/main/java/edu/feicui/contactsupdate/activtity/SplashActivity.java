package edu.feicui.contactsupdate.activtity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import edu.feicui.contactsupdate.R;
import edu.feicui.contactsupdate.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    private ImageView mImageView;
    private Animation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initview();
    }

    private void initview() {
        mImageView= (ImageView) findViewById(R.id.iv_splash);

        mAnimation= AnimationUtils.loadAnimation(this,R.anim.anim_splash);

        mAnimation.setAnimationListener(myListener);

        mImageView.setAnimation(mAnimation);

    }

    private Animation.AnimationListener myListener=new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Intent intent=new Intent(SplashActivity.this, TelmsgAcitivity.class);

            startActivity(intent);

            finish();//结束当前Activity

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
}
