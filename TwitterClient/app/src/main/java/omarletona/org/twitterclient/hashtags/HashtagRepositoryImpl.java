package omarletona.org.twitterclient.hashtags;

import android.support.design.widget.Snackbar;
import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import omarletona.org.twitterclient.api.CustomTwitterApiClient;
import omarletona.org.twitterclient.hashtags.entities.CustomTweet;
import omarletona.org.twitterclient.hashtags.entities.Hashtag;
import omarletona.org.twitterclient.hashtags.events.HashtagsEvent;
import omarletona.org.twitterclient.lib.base.EventBus;
import omarletona.org.twitterclient.main.ui.MainActivity;

/**
 * Created by Omar on 08/07/2016.
 */
public class HashtagRepositoryImpl implements HashtagRepository{
    private final EventBus eventBus;
    private final CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 100;
    private String searchText;

    public HashtagRepositoryImpl(CustomTwitterApiClient client, EventBus eventBus) {
        this.client = client;
        this.eventBus = eventBus;
    }

    public void getHashtags(){
        searchText = MainActivity.getSearch();
        client.getSearchService().tweets(searchText, null, null, null, null, null, null,null, null, null,
                new Callback<Search>() {

                    @Override
                    public void success(Result<Search> result) {
                        List<CustomTweet> items = new ArrayList<CustomTweet>();
                        List<Tweet> tweets = result.data.tweets;
                        for (Tweet tweet: tweets){
                            CustomTweet tweetModel = new CustomTweet();

                            tweetModel.setId(tweet.idStr);
                            tweetModel.setTweetText(tweet.text);
                            tweetModel.setImageURL(tweet.source);
                            tweetModel.setFavoriteCount(tweet.favoriteCount);

                            List<String> hashtags = new ArrayList<String>();
                            for (HashtagEntity hashtag : tweet.entities.hashtags) {
                                hashtags.add(hashtag.text);
                            }
                            tweetModel.setHashtags(hashtags);

                            items.add(tweetModel);

                        }
                        postEvent(items);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        Log.e("Fallo", "Error al recibir los datos");
                    }
                });



     /*   client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        List<Hashtag> items = new ArrayList<Hashtag>();
                        for (Tweet tweet : result.data) {

                            if (checkIfTweetHasHashtags(tweet)) {
                                Hashtag tweetModel = new Hashtag();

                                tweetModel.setId(tweet.idStr);
                                tweetModel.setTweetText(tweet.text);
                                tweetModel.setFavoriteCount(tweet.favoriteCount);

                                List<String> hashtags = new ArrayList<String>();
                                for (HashtagEntity hashtag : tweet.entities.hashtags) {
                                    hashtags.add(hashtag.text);
                                }
                                tweetModel.setHashtags(hashtags);

                                items.add(tweetModel);
                            }
                        }
                        Collections.sort(items, new Comparator<Hashtag>() {
                            public int compare(Hashtag t1, Hashtag t2) {
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

    private boolean checkIfTweetHasHashtags(Tweet tweet) {
        return  tweet.entities != null &&
                tweet.entities.hashtags != null &&
                !tweet.entities.hashtags.isEmpty();
    }

    private void postEvent(String error) {
        HashtagsEvent event = new HashtagsEvent();
        event.setError(error);
        eventBus.post(event);
    }

    private void postEvent(List<CustomTweet> items) {
        HashtagsEvent event = new HashtagsEvent();
        event.setHashtags(items);
        eventBus.post(event);
    }
}
