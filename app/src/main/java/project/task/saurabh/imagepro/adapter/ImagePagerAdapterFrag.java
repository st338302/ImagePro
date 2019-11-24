package project.task.saurabh.imagepro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import project.task.saurabh.imagepro.R;
import project.task.saurabh.imagepro.interfaces.ObjCallBack;
import project.task.saurabh.imagepro.model.ImageModel;

public class ImagePagerAdapterFrag extends PagerAdapter {

    private Context context;

    private List<ImageModel> listImage = new ArrayList<>();

    private ObjCallBack objCallBack;

    public ImagePagerAdapterFrag(Context context, ObjCallBack objCallBack) {
        this.context = context;
        this.objCallBack = objCallBack;
    }

    public void updateImages(List<ImageModel> imageModels) {
        this.listImage = imageModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_image_2, container, false);

        ImageView ivPic = view.findViewById(R.id.ivPic);
        TextView tvDesc = view.findViewById(R.id.tvDesc);

        Glide.with(context)
                .load(listImage.get(position).getUri())
                .fitCenter()
                .into(ivPic);

        String desc = listImage.get(position).getDescription();
        if (!desc.equalsIgnoreCase("")) {
            tvDesc.setText(desc);
        } else {
            tvDesc.setText(context.getString(R.string.no_description));
        }

        view.setOnClickListener(v -> objCallBack.onResult(listImage.get(position)));

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
        container.removeView((View) view);
    }

    @Override
    public int getCount() {
        return listImage.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
