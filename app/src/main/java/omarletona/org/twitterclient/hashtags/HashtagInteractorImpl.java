package omarletona.org.twitterclient.hashtags;

/**
 * Created by Omar on 08/07/2016.
 */
public class HashtagInteractorImpl implements HashtagInteractor {
    private HashtagRepository hashtagsRepository;

    public HashtagInteractorImpl(HashtagRepository hashtagsRepository) {
        this.hashtagsRepository = hashtagsRepository;
    }

    @Override
    public void getHashtagItemsList() {
        hashtagsRepository.getHashtags();
    }
}
