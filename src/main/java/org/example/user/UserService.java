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

    public User userFindByUserId(String userId) {
        return userRepository.userFindByUserId(userId);
    }

    public List<User> findByShowWhenSearch(String showWhenSearchStatus, String searchName) {
        return userRepository.findByShowWhenSearch(showWhenSearchStatus, searchName);
    }

    public void updateUserName(String newUserName) {
        this.userRepository.updateUserName(newUserName);
    }

    public void updateUserWeight(double newUserWeight) {
        this.userRepository.updateUserWeight(newUserWeight);
    }

    public String updateShowWhenSearch(String showWhenSearchStatus) {
        return this.userRepository.updateShowWhenSearch(showWhenSearchStatus);
    }

    public String updateShowMyRecord(String showMyRecordStatus) {
        return this.userRepository.updateShowMyRecord(showMyRecordStatus);
    }

    public void updateUserPassword(String newUserPassword) {
        this.userRepository.updateUserPassword(newUserPassword);
    }

    public void deleteUser() {
        this.userRepository.deleteUser();
    }
}
