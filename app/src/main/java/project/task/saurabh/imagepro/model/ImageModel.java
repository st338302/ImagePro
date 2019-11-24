package project.task.saurabh.imagepro.model;

import android.net.Uri;

import java.io.Serializable;

public class ImageModel implements Serializable {

    private Uri mUri;
    private String description;

    public ImageModel(Uri mUri, String description) {
        this.mUri = mUri;
        this.description = description;
    }

    public Uri getUri() {
        return mUri;
    }

    public String getDescription() {
        return description;
    }
}
