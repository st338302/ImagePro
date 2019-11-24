package project.task.saurabh.imagepro.viewholders;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import project.task.saurabh.imagepro.model.ImageModel;

public class ImageViewModel extends ViewModel {

    private MutableLiveData<List<ImageModel>> mutableImageList = new MutableLiveData<>();

    public void setMutableImageList(List<ImageModel> uriList) {
        mutableImageList.setValue(uriList);
    }

    public MutableLiveData<List<ImageModel>> getSelectedImage() {
        return mutableImageList;
    }
}