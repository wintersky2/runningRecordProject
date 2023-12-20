package org.example.follow;

import org.example.user.User;

import java.util.List;

public class FollowService {
    FollowRepository followRepository;
    public FollowService(){
        followRepository = new FollowRepository();
    }
    public void createFollow (int id){
        this.followRepository.createFollow(id);
    }
    public void deleteFollow(int deleteId){
        this.followRepository.deleteFollow(deleteId);
    }
    public List<User> searchUserByName(String searchName){
        return this.followRepository.searchUserByName(searchName);
    }
    public List<FollowDTO> showFollowing(){
        return this.followRepository.showFollowing();
    }
    public List<FollowDTO> showFollower(){
        return this.followRepository.showFollower();
    }
}
