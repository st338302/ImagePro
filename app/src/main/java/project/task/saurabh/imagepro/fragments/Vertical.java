package project.task.saurabh.imagepro.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import project.task.saurabh.imagepro.R;
import project.task.saurabh.imagepro.activities.MainActivity;
import project.task.saurabh.imagepro.activities.Viewer;
import project.task.saurabh.imagepro.adapter.ImageAdapter;
import project.task.saurabh.imagepro.interfaces.ObjCallBack;
import project.task.saurabh.imagepro.model.ImageModel;
import project.task.saurabh.imagepro.utils.StaticUtils;
import project.task.saurabh.imagepro.viewholders.ImageViewModel;

public class Vertical extends Fragment {

    private ImageViewModel imageViewModel;
    private ImageAdapter imageAdapter;

    private ObjCallBack objCallBack = object -> {
        ImageModel imageModel = (ImageModel) object;
        Intent intent = new Intent(getActivity(), Viewer.class);
        intent.putExtra(StaticUtils.ARG_URI, imageModel.getUri().toString());
        intent.putExtra(StaticUtils.ARG_DESC, imageModel.getDescription());
        startActivity(intent);
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageViewModel = ((MainActivity) Objects.requireNonNull(getActivity())).getViewModel();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_vertical, container, false);
        init(root);

        return root;
    }

    private void init(View root) {

        RecyclerView rvPicutres = root.findViewById(R.id.rvPics);
        rvPicutres.setNestedScrollingEnabled(false);
        imageAdapter = new ImageAdapter(objCallBack);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvPicutres.setLayoutManager(layoutManager);
        rvPicutres.setAdapter(imageAdapter);

        imageViewModel.getSelectedImage().observe(this,
                listUri -> imageAdapter.updateImages(listUri));

    }
}