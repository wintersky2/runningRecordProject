package org.example.user;

import java.util.List;

public class UserService {
    UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public int createUser(String userId, String password, String name, double weight) {
        return userRepository.createUser(userId, password, name, weight);
    }

    public User userFindById(int id) {
        return userRepository.userFindById(id);
    }

    public User uerFindByUserId(String userId) {
        return userRepository.userFindByUserId(userId);
    }
    public List<User> FindByShowWhenSearch(String showWhenSearchStatus,String searchName){
        return userRepository.FindByShowWhenSearch(showWhenSearchStatus,searchName);
    }

}
