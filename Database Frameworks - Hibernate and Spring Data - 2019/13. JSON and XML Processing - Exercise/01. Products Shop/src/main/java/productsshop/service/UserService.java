package productsshop.service;

import productsshop.domain.dto.*;
import productsshop.domain.entity.User;

import java.util.List;
import java.util.Random;

public interface UserService {

    boolean isUsersTableEmpty();

    String registerFromJson(UserSeedJsonDto userSeedJsonDtos);

    void registerFromXml(UserSeedXmlDto userSeedXmlDto);

    User getRandomUser(Random random);

    void addFriendsToAllUsers(Random random);

    List<UserFirstAndLastNamesWithSoldProductsDto> getAllUsersWithAtLeastOneSoldProductForJsonExport();

    UsersWithSoldProductsRootDto getAllUsersWithAtLeastOneSoldProductForXmlExport();

    UserCountSalesDto getUsersWithSalesForJsonExport();

    UsersAndProductsRootDto getUsersWithSalesForXmlExport();
}
