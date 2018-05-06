package sample1.carousell_first;

/**
 * Created by harish on 24/4/18.
 */

public class DataModel {

    String name;
    int val;
 //   int image;
    int plusimagebutton;
    int minusimageButton;

    public DataModel(String name, int minusimageButton, int plusImageButton, int val) {
        this.name = name;
        this.val = val;
        //this.image = image;
        this.plusimagebutton = plusImageButton;
        this.minusimageButton = minusimageButton;
    }

    public String getName() {
        return name;
    }

//    public int getImage() {
//        return image;
//    }

    public int getplusImageButton() {
        return plusimagebutton;
    }

    public int getminusImageButton() {
        return minusimageButton;
    }

    public int getValue() {
        return val;
    }
}
