package kayzer.services;

import kayzer.domain.models.service.WatchServiceModel;

import java.util.Set;

public interface WatchService {

    boolean createWatch(WatchServiceModel watchServiceModel);

    Set<WatchServiceModel> getAllWatches();

    Set<WatchServiceModel> getTopFourWatchesByViews();

    WatchServiceModel getWatchById(String id);
}
