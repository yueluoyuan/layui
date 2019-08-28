package com.jiangguang.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jiangguang.entity.User;
import com.jiangguang.repository.UserRepository;
import com.jiangguang.service.UserService;
import com.jiangguang.util.StringUtils;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUserList(User user , Integer page, Integer limit) {

        Pageable pageable = PageRequest.of(page-1, limit);

        Page<User> userPage = userRepository.findAll(new Specification<User>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if(user != null) {
                    if(StringUtils.isNotEmpty(user.getUserName())) {
                        predicate.getExpressions().add(criteriaBuilder.like(root.get("userName"), "%"+user.getUserName()+"%"));
                    }
                }
                return predicate;
            }
        },pageable);

        return userPage.getContent();
    }

    @Override
    public long count(User user) {

        long count = userRepository.count(new Specification<User>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Predicate predicate = criteriaBuilder.conjunction();
                if(user!=null) {
                    if(StringUtils.isNotEmpty(user.getUserName())) {
                        predicate.getExpressions().add(criteriaBuilder.like(root.get("userName"), "%"+user.getUserName()+"%"));
                    }
                }
                return predicate;
            }

        });

        return count;
    }



    @Transactional
    @Override
    public void saveUser(User user) {

        userRepository.save(user);

    }

    @Override
    public User findUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

}