package omarletona.org.twitterclient.images;

import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import omarletona.org.twitterclient.api.CustomTwitterApiClient;
import omarletona.org.twitterclient.hashtags.entities.CustomTweet;
import omarletona.org.twitterclient.images.entities.Image;
import omarletona.org.twitterclient.images.events.ImagesEvent;
import omarletona.org.twitterclient.lib.base.EventBus;
import omarletona.org.twitterclient.main.ui.MainActivity;

/**
 * Created by Omar on 07/07/2016.
 */
public class ImagesRepositoryImpl implements ImageRepository {
    private final EventBus eventBus;
    private final CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 100;
    private String searchText;

    public ImagesRepositoryImpl(CustomTwitterApiClient client, EventBus eventBus) {
        this.client = client;
        this.eventBus = eventBus;
    }

    public void getImages() {
        searchText = MainActivity.getSearch();
        client.getSearchService().tweets(searchText, null, null, null, null, null, null,null, null, null,
                new Callback<Search>() {

                    @Override
                    public void success(Result<Search> result) {
                        List<CustomTweet> items = new ArrayList<CustomTweet>();
                        List<Tweet> tweets = result.data.tweets;
                        for (Tweet tweet: tweets){
                            if (checkIfTweetHasImage(tweet)) {
                                CustomTweet tweetModel = new CustomTweet();

                                tweetModel.setId(tweet.idStr);
                                tweetModel.setTweetText(tweet.text);
                                tweetModel.setImageURL(tweet.source);
                                tweetModel.setFavoriteCount(tweet.favoriteCount);

                                MediaEntity currentPhoto = tweet.entities.media.get(0);
                                String imageURL = currentPhoto.mediaUrl;
                                tweetModel.setImageURL(imageURL);

                                List<String> hashtags = new ArrayList<String>();
                                for (HashtagEntity hashtag : tweet.entities.hashtags) {
                                    hashtags.add(hashtag.text);
                                }
                                tweetModel.setHashtags(hashtags);

                                items.add(tweetModel);
                            }

                        }
                        postEvent(items);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        Log.e("Fallo", "Error al recibir los datos");
                    }
                });

       /* client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        List<Image> items = new ArrayList<Image>();
                        for (Tweet tweet : result.data) {
                            if (checkIfTweetHasImage(tweet)) {
                                Image tweetModel = new Image();

                                tweetModel.setId(tweet.idStr);
                                tweetModel.setFavoriteCount(tweet.favoriteCount);

                                String tweetText = tweet.text;
                                int index = tweetText.indexOf("http");
                                if (index > 0) {
                                    tweetText = tweetText.substring(0, index);
                                }
                                tweetModel.setTweetText(tweetText);

                                MediaEntity currentPhoto = tweet.entities.media.get(0);
                                String imageURL = currentPhoto.mediaUrl;
                                tweetModel.setImageURL(imageURL);

                                items.add(tweetModel);
                            }
                        }
                        Collections.sort(items, new Comparator<Image>() {
                            public int compare(Image t1, Image t2) {
                                return t2.getFavoriteCount() - t1.getFavoriteCount();
                            }
                        });
                        postEvent(items);

                    }

                    @Override
                    public void failure(TwitterException e) {
                        postEvent(e.getMessage());
                    }
                }
        );*/
    }

    private boolean checkIfTweetHasImage(Tweet tweet) {
        return  tweet.entities != null &&
                tweet.entities.media != null &&
                !tweet.entities.media.isEmpty();
    }

    private void postEvent(String error) {
        ImagesEvent event = new ImagesEvent();
        event.setError(error);
        eventBus.post(event);
    }

    private void postEvent(List<CustomTweet> items) {
        ImagesEvent event = new ImagesEvent();
        event.setImages(items);
        eventBus.post(event);
    }

}
