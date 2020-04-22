package com.team.app.backend.service.impl;

import com.team.app.backend.exception.UserAlreadyExistsException;
import com.team.app.backend.dto.UserRegistrationDto;
import com.team.app.backend.persistance.dao.UserDao;
import com.team.app.backend.persistance.model.Role;
import com.team.app.backend.persistance.model.User;
import com.team.app.backend.persistance.model.UserStatus;
import com.team.app.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public List<User> searchUsers(String string) {
        return userDao.searchByString(string);
    }

    @Override
    public void registerNewUserAccount(UserRegistrationDto userDto)
            throws UserAlreadyExistsException {

        if (isUserRegistered(userDto.getUsername())) {
            throw new UserAlreadyExistsException();
        }

        User user = new User();

        user.setFirstName(userDto.getFirstname());
        user.setLastName(userDto.getLastname());
        user.setFirstName(userDto.getFirstname());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        //user.setImage(userDto.getImage());
        user.setActivate_link("ttest");
        user.setRegistr_date(new Date());
        user.setRole(new Role(1L,"USER"));
        user.setStatus(new UserStatus(1L,"REGISTERED"));
        userDao.save(user);
    }

    /**
     * checks if user exists in the database
     * @param username user's username
     * @return true if user exists in the database; otherwise false
     */
    @Override
    public boolean isUserRegistered(String username) {
        return userDao.findByUsername(username) != null;
    }

    @Override
    public String getUserPassword(String username) {
        return userDao.getUserPasswordByUsername(username);
    }
}