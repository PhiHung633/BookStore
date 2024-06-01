package com.bookstore.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookstore.entity.TaiKhoan;
import com.bookstore.repository.TaiKhoanRepository;
import com.bookstore.web.dto.UserRegistrationDto;

import jakarta.transaction.Transactional;

@Service
public class TaiKhoanService implements ITaiKhoanService, UserDetailsService {

    @Autowired
    private TaiKhoanRepository taikhoanrepo;
    
    public TaiKhoanService(TaiKhoanRepository taiKhoanRepository)
    {
    	this.taikhoanrepo=taiKhoanRepository;
    }

    @Transactional
    public TaiKhoan save(UserRegistrationDto registrationDto) {
        if (usernameOrEmailExists(registrationDto.getUsername(), registrationDto.getEmail())) {
            return null; // Handle this case appropriately in your controller
        }
        
        TaiKhoan tk = new TaiKhoan(
            registrationDto.getUsername(),
            new BCryptPasswordEncoder().encode(registrationDto.getPassword()),
            registrationDto.getHovaten(),
            registrationDto.getEmail(),
            registrationDto.getIsSell(),
            registrationDto.getIsAdmin()
        );
        return taikhoanrepo.save(tk);
    }

    public boolean usernameOrEmailExists(String username, String email) {
        TaiKhoan existingByUsername = taikhoanrepo.findByUsername(username);
        TaiKhoan existingByEmail = taikhoanrepo.findByEmail(email);
        return existingByUsername != null || existingByEmail != null;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan tk = taikhoanrepo.findByUsername(username);
        if (tk == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(
            tk.getUsername(),
            tk.getPassword(),
            new ArrayList<>()
        );
    }
    public TaiKhoan findByUserName(String username)
    {
    	return taikhoanrepo.findByUsername(username);
    }
}
