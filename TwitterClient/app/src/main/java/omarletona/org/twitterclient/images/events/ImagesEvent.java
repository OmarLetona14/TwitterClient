package omarletona.org.twitterclient.images.events;

import java.util.List;

import omarletona.org.twitterclient.images.entities.Image;

/**
 * Created by Omar on 07/07/2016.
 */
public class ImagesEvent {
    private String error;
    private List<Image> images;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
