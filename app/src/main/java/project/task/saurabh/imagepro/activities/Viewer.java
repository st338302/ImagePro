package project.task.saurabh.imagepro.activities;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import project.task.saurabh.imagepro.R;
import project.task.saurabh.imagepro.utils.StaticUtils;
import project.task.saurabh.imagepro.utils.UtilsActivity;
import project.task.saurabh.imagepro.utils.ZoomableImage;

public class Viewer extends UtilsActivity {

    private static final int UI_ANIMATION_DELAY = 300;

    private static final int AUTO_HIDE_DELAY_MILLIS = 5000;
    private final Handler mHideHandler = new Handler();
    private TextView tvDesc;
    private boolean mVisible;
    private final Runnable mHideRunnable = this::hide;
    private final Runnable mShowDescRunnable = new Runnable() {
        @Override
        public void run() {
            tvDesc.setVisibility(View.VISIBLE);

            delayedHide(AUTO_HIDE_DELAY_MILLIS);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setViews();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setViews() {
        setContentView(R.layout.activity_viewer);

        Uri uri = Uri.parse(getIntent().getStringExtra(StaticUtils.ARG_URI));
        String desc = getIntent().getStringExtra(StaticUtils.ARG_DESC);

        mVisible = true;
        ZoomableImage ivPicture = findViewById(R.id.ivPic);

        Glide.with(this)
                .load(uri)
                .fitCenter()
                .into(ivPicture);

        ivPicture.setOnClickListener(view -> toggle());

        tvDesc = findViewById(R.id.tvDesc);

        if (desc != null && !desc.equalsIgnoreCase("")) {
            tvDesc.setText(desc);
        } else {
            tvDesc.setText(getString(R.string.no_description));
        }

        tvDesc.setOnClickListener(v -> delayedHide(UI_ANIMATION_DELAY));

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        delayedHide(AUTO_HIDE_DELAY_MILLIS);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {

        tvDesc.setVisibility(View.GONE);
        mVisible = false;

        mHideHandler.removeCallbacks(mShowDescRunnable);
    }

    @SuppressLint("InlinedApi")
    private void show() {

        mVisible = true;

        mHideHandler.postDelayed(mShowDescRunnable, UI_ANIMATION_DELAY);
    }

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
