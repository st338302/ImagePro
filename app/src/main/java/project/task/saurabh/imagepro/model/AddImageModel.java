package project.task.saurabh.imagepro.model;

public class AddImageModel {
    private int buttonClicked;
    private String mageDesc;

    public AddImageModel(int buttonClicked, String mageDesc) {
        this.buttonClicked = buttonClicked;
        this.mageDesc = mageDesc;
    }

    public int getButtonClicked() {
        return buttonClicked;
    }

    public String getMageDesc() {
        return mageDesc;
    }
}
