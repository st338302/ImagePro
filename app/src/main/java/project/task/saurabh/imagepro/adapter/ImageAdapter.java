package project.task.saurabh.imagepro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import project.task.saurabh.imagepro.R;
import project.task.saurabh.imagepro.interfaces.ObjCallBack;
import project.task.saurabh.imagepro.model.ImageModel;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.PickerViewModel> {

    private List<ImageModel> listImage = new ArrayList<>();

    private ObjCallBack objCallBack;

    private Context context;

    public ImageAdapter(ObjCallBack objCallBack) {
        this.objCallBack = objCallBack;
    }

    @NonNull
    @Override
    public PickerViewModel onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.card_image, parent, false);

        return new PickerViewModel(itemView);
    }

    @Override
    public void onBindViewHolder(final PickerViewModel holder, int position) {

        Glide.with(context)
                .load(listImage.get(position).getUri())
                .fitCenter()
                .into(holder.ivPic);

        String desc = listImage.get(position).getDescription();
        if (!desc.equalsIgnoreCase("")) {
            holder.tvDesc.setText(desc);
        } else {
            holder.tvDesc.setText(context.getString(R.string.no_description));
        }


        holder.itemView.setOnClickListener(v -> objCallBack.onResult(listImage.get(position)));
    }

    @Override
    public int getItemCount() {
        return listImage.size();
    }

    public void updateImages(List<ImageModel> imageModels) {
        this.listImage = imageModels;
        notifyDataSetChanged();
    }

    class PickerViewModel extends RecyclerView.ViewHolder {

        ImageView ivPic;
        TextView tvDesc;

        PickerViewModel(View itemView) {
            super(itemView);

            ivPic = itemView.findViewById(R.id.ivPic);
            tvDesc = itemView.findViewById(R.id.tvDesc);

        }
    }
}
