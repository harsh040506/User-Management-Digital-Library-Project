package com.example.adminfilterusers.Service;

import com.example.adminfilterusers.Entity.Users;
import com.example.adminfilterusers.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Users> findAllUsers() {
        return userRepository.findAll();}

    public Users findUserByUsername(String username) {
        return userRepository.findByUsername(username);}

    public List<Users> findUserByFullname(String fullname) {
        return userRepository.findByFullname(fullname);}

    public List<Users> findUsersByFullnameLike(String name) {
        return userRepository.findByFullnameLike(name);}

    public List<Users> findUsersByUsernameLike(String name) {
        return userRepository.findByUsernameLike(name);}

    public long findCountByFullname(String fullname) {
        return userRepository.countByFullname(fullname);}
}