package kayzer.services;

import kayzer.domain.entities.Watch;
import kayzer.domain.models.service.WatchServiceModel;
import kayzer.repositories.WatchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WatchServiceImpl implements WatchService {

    private final WatchRepository watchRepository;

    private final ModelMapper modelMapper;

    public WatchServiceImpl(WatchRepository watchRepository, ModelMapper modelMapper) {
        this.watchRepository = watchRepository;
        this.modelMapper = modelMapper;
    }

    private void viewWatch(Watch watch) {
        if (watch == null) {
            return;
        }

        watch.setViews(watch.getViews() + 1);

        this.watchRepository.saveAndFlush(watch);
    }

    @Override
    public boolean createWatch(WatchServiceModel watchServiceModel) {
        Watch watchEntity = this.modelMapper.map(watchServiceModel, Watch.class);

        return this.watchRepository.saveAndFlush(watchEntity) != null; //TODO CATCH THE PERSISTENT EXCEPTION, or check if it works this way
    }

    @Override
    public Set<WatchServiceModel> getAllWatches() {
        return this.watchRepository.findAll()
                .stream()
                .map(watch -> this.modelMapper.map(watch, WatchServiceModel.class))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Set<WatchServiceModel> getTopFourWatchesByViews() {
        return this.watchRepository.findTop4ByOrderByViewsDesc()
                .stream()
                .map(watch -> this.modelMapper.map(watch, WatchServiceModel.class))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public WatchServiceModel getWatchById(String id) {
        Optional<Watch> watchCandidate = this.watchRepository.findById(id);

        if (watchCandidate.isEmpty()) {
            return null;
        }

        Watch watchEntity = watchCandidate.get();

        this.viewWatch(watchEntity);

        return this.modelMapper.map(watchEntity, WatchServiceModel.class);
    }
}
