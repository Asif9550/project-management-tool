package com.asif.projectmanagementsystem1.service;

import com.asif.projectmanagementsystem1.model.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User findUserProfileByJwt(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;
    User findUserById(Long id) throws Exception;
    User updateUsersProjectSize(User user,int number);

}
