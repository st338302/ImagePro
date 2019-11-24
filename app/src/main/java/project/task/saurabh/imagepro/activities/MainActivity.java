package project.task.saurabh.imagepro.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import project.task.saurabh.imagepro.R;
import project.task.saurabh.imagepro.adapter.FragPagerAdapter;
import project.task.saurabh.imagepro.model.AddImageModel;
import project.task.saurabh.imagepro.model.ImageModel;
import project.task.saurabh.imagepro.utils.StaticUtils;
import project.task.saurabh.imagepro.utils.UtilsActivity;
import project.task.saurabh.imagepro.viewholders.ImageViewModel;

public class MainActivity extends UtilsActivity {

    List<ImageModel> imageList = new ArrayList<>();

    ImageViewModel imageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setViews();
    }

    private void setViews() {

        setContentView(R.layout.activity_main);

        imageViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);

        FragPagerAdapter fragPagerAdapter = new FragPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(fragPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(2);

        ViewGroup viewGroup = (ViewGroup) tabs.getChildAt(0);
        View view = viewGroup.getChildAt(1);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.weight = 1f;
        view.setLayoutParams(layoutParams);
    }

    public ImageViewModel getViewModel() {
        return imageViewModel;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (data != null && requestCode == StaticUtils.PICKER_CODE) {

                Uri uri = data.getData();

                showImage(getString(R.string.selected_pic),
                        uri,
                        getString(R.string.add),
                        getString(R.string.cancel),
                        object -> {
                            AddImageModel addImageModel = (AddImageModel) object;

                            if (addImageModel.getButtonClicked() == 1) {
                                String desc = addImageModel.getMageDesc();
                                imageList.add(new ImageModel(uri, desc));
                                imageViewModel.setMutableImageList(imageList);
                            }
                        });
            } else
                showMessage(getString(R.string.oops), getString(R.string.something_went_wrong_picking_image));

        } else if (data == null) {
            showMessage(getString(R.string.oops), getString(R.string.something_went_wrong_picking_image));
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == StaticUtils.PICKER_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                StaticUtils.startGallery(this);
            } else {
                showMessage(getString(R.string.alert), getString(R.string.pls_grant_permission_to_pik));
            }
        }
    }
}