package com.cfk.animationstudy.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.cfk.animationstudy.R;

/**
 * 主活动类，展示多种动画效果
 * @Author : B_T_D-btd_
 * @Version : 0.0.1
 * @Date : 2024/1/10 15:56
 * @E-Mail : cui.fk@neusoft.com
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置布局文件
        setContentView(R.layout.activity_main);

        // 获取按钮和图片视图的实例
        Button btnAnimate = findViewById(R.id.btnAnimate);
        ImageView imageView = findViewById(R.id.imageView);

        // 设置按钮点击事件监听器
        btnAnimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. 使用视图动画（View Animation）
                Animation combinedAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.combined_animation);
                imageView.startAnimation(combinedAnimation);

                // 2. 使用属性动画（Property Animation）
                // 创建向右平移的动画，将 imageView 从当前位置水平移动到右侧100个像素的位置
                ObjectAnimator translateRightAnimator = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 100f);
                translateRightAnimator.setDuration(2000);  // 设置动画持续时间为2000毫秒（2秒）

                // 创建向左平移的动画，将 imageView 从右侧100个像素的位置平移到当前位置
                ObjectAnimator translateLeftAnimator = ObjectAnimator.ofFloat(imageView, "translationX", 100f, 0f);
                translateLeftAnimator.setDuration(2000);  // 设置动画持续时间为2000毫秒（2秒）

                // 创建AnimatorSet，将两个动画组合起来
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(translateRightAnimator, translateLeftAnimator);
                // 启动动画
                animatorSet.start();

                // 3. 使用过渡动画
                TransitionManager.beginDelayedTransition((ConstraintLayout) findViewById(R.id.constraintLayout), new Fade());
                // 布局变化，例如图片视图的显示或隐藏
                imageView.setVisibility(imageView.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);

                // 4. 使用帧动画（Frame Animation）
                imageView.setImageResource(R.drawable.animation_list);
                AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
                animationDrawable.start();
            }
        });
    }
}
