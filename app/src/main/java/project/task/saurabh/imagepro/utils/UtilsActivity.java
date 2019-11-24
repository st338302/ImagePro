package project.task.saurabh.imagepro.utils;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import project.task.saurabh.imagepro.R;
import project.task.saurabh.imagepro.interfaces.ObjCallBack;
import project.task.saurabh.imagepro.model.AddImageModel;

public class UtilsActivity extends AppCompatActivity {

    protected void showMessage(String title, String message) {

        AlertDialog customBuilder = new AlertDialog.Builder(this).create();
        customBuilder.setCancelable(true);
        customBuilder.setCanceledOnTouchOutside(false);

        LayoutInflater inflater = this.getLayoutInflater();

        @SuppressLint("InflateParams") final View dialogView = inflater.inflate(R.layout.dialog_message, null);

        customBuilder.setView(dialogView);

        TextView tvOk, tvMessage, tvTitle;
        tvOk = dialogView.findViewById(R.id.tvOk);
        tvTitle = dialogView.findViewById(R.id.tvHeader);
        tvMessage = dialogView.findViewById(R.id.tvMessage);

        tvTitle.setText(title);
        tvMessage.setText(message);

        tvOk.setOnClickListener(view -> customBuilder.dismiss());

        customBuilder.show();
    }

    protected void showImage(String title, Uri uri, String btnYes, String btnNo, ObjCallBack objCallBack) {
        final AlertDialog customBuilder = new AlertDialog.Builder(this).create();
        customBuilder.setCancelable(true);
        customBuilder.setCanceledOnTouchOutside(false);

        LayoutInflater inflater = getLayoutInflater();

        @SuppressLint("InflateParams") final View dialogView = inflater.inflate(R.layout.dialog_image, null);

        customBuilder.setView(dialogView);

        TextView tvTitle, tvAdd, tvCancel;
        ImageView ivDisplay;
        tvTitle = dialogView.findViewById(R.id.tvTitle);
        tvAdd = dialogView.findViewById(R.id.tvAdd);
        tvCancel = dialogView.findViewById(R.id.tvCancel);
        ivDisplay = dialogView.findViewById(R.id.ivDisp);


        tvTitle.setText(title);
        tvAdd.setText(btnYes);
        tvCancel.setText(btnNo);

        ivDisplay.setImageURI(uri);

        EditText etDesc = dialogView.findViewById(R.id.etDesc);

        tvAdd.setOnClickListener(view -> {
            objCallBack.onResult(new AddImageModel(1, etDesc.getText().toString()));
            customBuilder.dismiss();
        });

        tvCancel.setOnClickListener(view -> {
            objCallBack.onResult(new AddImageModel(2, ""));
            customBuilder.dismiss();
        });

        customBuilder.show();
    }


}
