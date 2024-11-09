package com.example.exportuserdata.Service;

import com.example.exportuserdata.Entity.Users;
import com.example.exportuserdata.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<Users> listAll() {
        return repository.findAll(Sort.by("id").ascending());
    }

    public List<Users> listAllByFullnameSubstring(String fullname) {
        return repository.findByFullnameLike(fullname);
    }

    public List<Users> listAllByUsernameSubstring(String username) {
        return repository.findByUsernameLike(username);
    }
    
}