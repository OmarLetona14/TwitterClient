package omarletona.org.twitterclient.hashtags.events;

import java.util.List;

import omarletona.org.twitterclient.hashtags.entities.Hashtag;

/**
 * Created by Omar on 08/07/2016.
 */
public class HashtagsEvent {
    private String error;
    private List<Hashtag> hashtags;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}
