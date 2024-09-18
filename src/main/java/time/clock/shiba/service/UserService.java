package time.clock.shiba.service;

import time.clock.shiba.domain.User;
import time.clock.shiba.record.request.CreateUser;

public interface UserService {

    User getUser(String userId);

    void createUser(CreateUser request);

    void deleteUser();

}
