package com.jiangguang.service;

import com.jiangguang.entity.User;

import java.util.List;

public interface UserService {

    public List<User> getUserList(User user, Integer page, Integer limit);

    public long count(User user);

    public void saveUser(User user);

    public User findUserById(Integer id);

    public void deleteUserById(Integer id);
}
