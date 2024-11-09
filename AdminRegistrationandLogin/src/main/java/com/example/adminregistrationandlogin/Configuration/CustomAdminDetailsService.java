package com.example.adminregistrationandlogin.Configuration;

import java.util.Arrays;
import java.util.Collection;

import com.example.adminregistrationandlogin.Entity.Admin;
import com.example.adminregistrationandlogin.Repository.AdminRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomAdminDetailsService implements UserDetailsService {

    private AdminRepository adminRepository;

    public CustomAdminDetailsService(AdminRepository adminRepository) {
        super();
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = adminRepository.findByUsername(username);
        if (admin == null) {
            throw new UsernameNotFoundException("Username or Password not found");
        }
        return new CustomAdminDetails(admin.getUsername(), admin.getPassword(),
                authorities(), admin.getFullname());
    }

    public Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
    }
}