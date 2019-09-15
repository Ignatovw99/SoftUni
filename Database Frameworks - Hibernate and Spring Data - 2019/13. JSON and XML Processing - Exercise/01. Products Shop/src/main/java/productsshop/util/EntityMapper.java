package productsshop.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class EntityMapper {

    private static ModelMapper MODEL_MAPPER;

    @Autowired
    private EntityMapper(ModelMapper modelMapper) {
        EntityMapper.MODEL_MAPPER = modelMapper;
    }

    public static <S, D> D map(S source, Class<D> classDestination) {
        return EntityMapper.MODEL_MAPPER.map(source, classDestination);
    }
}
