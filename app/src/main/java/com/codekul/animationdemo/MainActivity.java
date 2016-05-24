package com.codekul.animationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnRotate).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.startAnimation(animation);

                //findViewById(R.id.imageView).startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate));
            }
        });

        findViewById(R.id.btnFade).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                findViewById(R.id.imageView).startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade));
            }
        });

        findViewById(R.id.btnMixed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animRotate = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate);
                Animation animFade = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade);

                AnimationSet animSet = new AnimationSet(false);
                animSet.addAnimation(animFade);
                animFade.setStartOffset(2000);
                animSet.addAnimation(animRotate);

                findViewById(R.id.imageView).startAnimation(animSet);
            }
        });
    }
}
