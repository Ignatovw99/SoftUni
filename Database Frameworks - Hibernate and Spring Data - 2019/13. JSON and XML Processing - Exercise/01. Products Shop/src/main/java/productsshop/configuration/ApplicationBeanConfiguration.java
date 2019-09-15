package productsshop.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import productsshop.domain.dto.*;
import productsshop.domain.entity.Product;
import productsshop.domain.entity.User;
import productsshop.util.EntityMapper;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory()
                .getValidator();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<User, String> userToFullNameConverter = new AbstractConverter<User, String>() {
            @Override
            protected String convert(User user) {
                return user == null
                        ? null
                        : user.getFirstName() == null ? user.getLastName() : user.getLastName() + " " + user.getLastName();
            }
        };

        Converter<Set<Product>, SoldProductsDto> convertProductsSetToSoldProductsDto = new AbstractConverter<Set<Product>, SoldProductsDto>() {
            @Override
            protected SoldProductsDto convert(Set<Product> products) {
                if (products == null) {
                    return null;
                }
                SoldProductsDto soldProductsDto = new SoldProductsDto();
                soldProductsDto.setCount(products.size());
                List<ProductSeedXmlDto> soldProducts = products.stream().map(product -> EntityMapper.map(product, ProductSeedXmlDto.class)).collect(Collectors.toList());
                soldProductsDto.setSoldProducts(soldProducts);
                return soldProductsDto;
            }
        };

        modelMapper.createTypeMap(Product.class, ProductInPriceRangeViewDto.class)
                .addMappings(
                        m -> m.using(userToFullNameConverter)
                            .map(
                                    Product::getSeller,
                                    ProductInPriceRangeViewDto::setSeller
                            )
                );

        modelMapper.createTypeMap(Product.class, ProductInRangeDto.class)
                .addMappings(
                        m -> m.using(userToFullNameConverter)
                        .map(
                                Product::getSeller,
                                ProductInRangeDto::setSeller
                        )
                );

        modelMapper.createTypeMap(User.class, UserWithAllSoldProductsDto.class)
                .addMappings(
                        m -> m.using(convertProductsSetToSoldProductsDto)
                        .map(
                                User::getSoldProducts,
                                UserWithAllSoldProductsDto::setSoldProducts
                        )
                );

        return modelMapper;
    }

    @Bean
    public Random random() {
        return new Random();
    }
}
