package project.task.saurabh.imagepro.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.Objects;

import project.task.saurabh.imagepro.R;
import project.task.saurabh.imagepro.activities.MainActivity;
import project.task.saurabh.imagepro.activities.Viewer;
import project.task.saurabh.imagepro.adapter.ImagePagerAdapterFrag;
import project.task.saurabh.imagepro.interfaces.ObjCallBack;
import project.task.saurabh.imagepro.model.ImageModel;
import project.task.saurabh.imagepro.utils.StaticUtils;
import project.task.saurabh.imagepro.viewholders.ImageViewModel;

public class Horizontal extends Fragment {

    private ImageViewModel imageViewModel;

    private ObjCallBack objCallBack = object -> {
        ImageModel imageModel = (ImageModel) object;
        Intent intent = new Intent(getActivity(), Viewer.class);
        intent.putExtra(StaticUtils.ARG_URI, imageModel.getUri().toString());
        intent.putExtra(StaticUtils.ARG_DESC, imageModel.getDescription());
        startActivity(intent);
    };

    public Horizontal() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageViewModel = ((MainActivity) Objects.requireNonNull(getActivity())).getViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_horizontal, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View rootView) {

        ImagePagerAdapterFrag vpImageAdapter = new ImagePagerAdapterFrag(getActivity(), objCallBack);
        ViewPager viewPager = rootView.findViewById(R.id.vpPicture);

        viewPager.setAdapter(vpImageAdapter);


        imageViewModel.getSelectedImage().observe(this, vpImageAdapter::updateImages);
    }
}
