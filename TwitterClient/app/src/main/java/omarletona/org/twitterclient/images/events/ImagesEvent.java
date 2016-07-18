package omarletona.org.twitterclient.images.events;

import java.util.List;

import omarletona.org.twitterclient.hashtags.entities.CustomTweet;
import omarletona.org.twitterclient.images.entities.Image;

/**
 * Created by Omar on 07/07/2016.
 */
public class ImagesEvent {
    private String error;
    private List<CustomTweet> images;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<CustomTweet> getImages() {
        return images;
    }

    public void setImages(List<CustomTweet> images) {
        this.images = images;
    }
}
