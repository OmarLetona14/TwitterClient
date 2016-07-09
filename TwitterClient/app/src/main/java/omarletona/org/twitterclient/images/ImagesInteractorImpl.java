package omarletona.org.twitterclient.images;

import javax.inject.Inject;

/**
 * Created by Omar on 07/07/2016.
 */
public class ImagesInteractorImpl implements ImagesInteractor {
    private final ImageRepository imagesRepository;

    @Inject
    public ImagesInteractorImpl(ImageRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @Override
    public void execute() {
        this.imagesRepository.getImages();

    }
}
