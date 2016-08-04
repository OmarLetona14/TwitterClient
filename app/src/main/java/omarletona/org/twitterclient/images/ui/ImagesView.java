package omarletona.org.twitterclient.images.ui;

import java.util.List;

import omarletona.org.twitterclient.images.entities.Image;

/**
 * Created by Omar on 07/07/2016.
 */
public interface ImagesView {
    void showImages();
    void hideImages();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Image> items);
}
