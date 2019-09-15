package productsshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productsshop.domain.dto.*;
import productsshop.domain.entity.User;
import productsshop.repository.UserRepository;
import productsshop.service.UserService;
import productsshop.util.EntityMapper;
import productsshop.util.ValidatorUtil;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isUsersTableEmpty() {
        return this.userRepository.count() == 0L;
    }

    @Override
    public String registerFromJson(UserSeedJsonDto userSeedJsonDtos) {
        if (!ValidatorUtil.isValid(userSeedJsonDtos)) {
            return ValidatorUtil.getErrorConstraintMessages(userSeedJsonDtos)
                    .stream()
                    .collect(Collectors.joining(System.lineSeparator()));
        }

        User user = EntityMapper.map(userSeedJsonDtos, User.class);
        this.userRepository.saveAndFlush(user);
        return user.getFirstName() + user.getLastName() + "was registered successfully.";
    }

    @Override
    public void registerFromXml(UserSeedXmlDto userSeedXmlDto) {
        User user = EntityMapper.map(userSeedXmlDto, User.class);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void addFriendsToAllUsers(Random random) {
        this.userRepository.findAll()
                .forEach(user -> {
                    Set<User> friends = this.getRandomFriends(random);
                    friends.forEach(friend -> friend.getFriends().add(user));
                    user.getFriends().addAll(friends);
                    this.userRepository.save(user);
                });
    }

    private Set<User> getRandomFriends(Random random) {
        int friendsCount = (int) this.userRepository.count();

        Set<User> friends = new HashSet<>();
        for (int i = 0; i < friendsCount / 2; i++) { //Divided by 2, because is is getting to many friends for one user.
            friends.add(this.getRandomUser(random));
        }
        return friends;
    }

    @Override
    public User getRandomUser(Random random) {
        long userId = random.nextInt((int) this.userRepository.count()) + 1;
        return this.userRepository.findById(userId)
                .orElse(null);
    }



    @Override
    public List<UserFirstAndLastNamesWithSoldProductsDto> getAllUsersWithAtLeastOneSoldProductForJsonExport() {
        return this.userRepository.findAllByAtLeastOneSoldProduct()
                .stream()
                .map(user -> {
                    UserFirstAndLastNamesWithSoldProductsDto userDto =
                            EntityMapper.map(user, UserFirstAndLastNamesWithSoldProductsDto.class);
                    Set<ProductNamePriceBuyerFirstAndLastNames> productsWithoutBuyer = new HashSet<>();
                    userDto.getSoldProducts()
                            .forEach(productDto -> {
                                if (productDto.getBuyerLastName() == null) {
                                    productsWithoutBuyer.add(productDto);
                                }
                            });
                    userDto.getSoldProducts().removeAll(productsWithoutBuyer);
                    return userDto;
                })
                .filter(userDto -> !userDto.getSoldProducts().isEmpty())
                .collect(Collectors.toList());
    }

    @Override
    public UsersWithSoldProductsRootDto getAllUsersWithAtLeastOneSoldProductForXmlExport() {
        UsersWithSoldProductsRootDto usersWithSoldProductsRootDto = new UsersWithSoldProductsRootDto();
        List<UserWithSoldProduct> userWithSoldProducts = this.userRepository.findAllByAtLeastOneSoldProduct()
                .stream()
                .map(user -> {
                    UserWithSoldProduct userWithSoldProduct = EntityMapper.map(user, UserWithSoldProduct.class);
                    Set<ProductWithBuyerDto> productsWithoutBuyer = new HashSet<>();
                    userWithSoldProduct.getSoldProducts()
                            .forEach(soldProduct -> {
                                if (soldProduct.getBuyerLastName() == null) {
                                    productsWithoutBuyer.add(soldProduct);
                                }
                            });
                    userWithSoldProduct.getSoldProducts().removeAll(productsWithoutBuyer);
                    return userWithSoldProduct;
                })
                .filter(userWithSoldProduct -> !userWithSoldProduct.getSoldProducts().isEmpty())
                .collect(Collectors.toList());
        usersWithSoldProductsRootDto.setUserWithSoldProducts(userWithSoldProducts);
        return usersWithSoldProductsRootDto;
    }

    @Override
    public UserCountSalesDto getUsersWithSalesForJsonExport() {
        Set<UserSalesDto> usersDto = this.userRepository.findAllByAtLeastOneSoldProduct()
                .stream()
                .map(user -> {
                    UserSalesDto userSalesDto = EntityMapper.map(user, UserSalesDto.class);
                    SoldProductsWithCountDto soldProductsWithCountDto = new SoldProductsWithCountDto();
                    soldProductsWithCountDto.setCount(user.getSoldProducts().size());
                    soldProductsWithCountDto.setProducts(
                            user.getSoldProducts()
                                    .stream()
                                    .map(product -> new ProductDto(product.getName(), product.getPrice()))
                                    .collect(Collectors.toSet())
                    );
                    userSalesDto.setSoldProducts(soldProductsWithCountDto);
                    return userSalesDto;
                })
                .collect(Collectors.toSet());

        UserCountSalesDto userCountSalesDto = new UserCountSalesDto();
        userCountSalesDto.setUsersCount(usersDto.size());
        userCountSalesDto.setUsers(usersDto);
        return userCountSalesDto;
    }

    @Override
    public UsersAndProductsRootDto getUsersWithSalesForXmlExport() {
        UsersAndProductsRootDto usersAndProductsRootDto = new UsersAndProductsRootDto();
        List<UserWithAllSoldProductsDto> users = this.userRepository.findAll()
                .stream()
                .map(user -> EntityMapper.map(user, UserWithAllSoldProductsDto.class))
                .collect(Collectors.toList());
        usersAndProductsRootDto.setCount(users.size());
        usersAndProductsRootDto.setUsers(users);
        return usersAndProductsRootDto;
    }
}
