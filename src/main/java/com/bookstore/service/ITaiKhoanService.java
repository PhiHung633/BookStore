package com.bookstore.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bookstore.entity.TaiKhoan;
import com.bookstore.web.dto.UserRegistrationDto;

public interface ITaiKhoanService extends UserDetailsService {

	TaiKhoan save(UserRegistrationDto registrationDto);
	
}
