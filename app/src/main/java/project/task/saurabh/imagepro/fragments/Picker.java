package project.task.saurabh.imagepro.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import project.task.saurabh.imagepro.R;
import project.task.saurabh.imagepro.utils.StaticUtils;

public class Picker extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_picker, container, false);
        init(root);

        return root;
    }

    private void init(View root) {

        LinearLayout llParent = root.findViewById(R.id.llParent);
        llParent.setOnClickListener(v -> {
            if (StaticUtils.isReadPermissionGranted(getActivity())) {
                StaticUtils.startGallery(Objects.requireNonNull(getActivity()));
            } else {
                StaticUtils.requestReadPermission(getActivity());
            }
        });
    }
}