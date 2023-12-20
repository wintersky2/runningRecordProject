package org.example.follow;

import java.util.List;

public class FollowService {
    FollowRepository followRepository;
    public FollowService(){
        followRepository = new FollowRepository();
    }
    public List<FollowDTO> searchUserByName(String searchName){
        return this.followRepository.searchUserByName(searchName);
    }
    public List<FollowDTO> findByShowWhenSearch(){
        List<>
    }
}
