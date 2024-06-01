package com.bookstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.bookstore.entity.TaiKhoan;
import com.bookstore.repository.TaiKhoanRepository;

public class SecurityUtil {
	@Autowired
    private static TaiKhoanRepository taiKhoanRepository;
    public SecurityUtil(TaiKhoanRepository taiKhoanRepository) {
        SecurityUtil.taiKhoanRepository = taiKhoanRepository;
    }
	
	public static TaiKhoan getMyUser()
	{
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	     Object principal = authentication.getPrincipal();
	        
	        if (principal instanceof UserDetailsImpl) {
	            return ((UserDetailsImpl) principal).getUser();
	        } else if (principal instanceof UserDetails) {
	            // In case some other UserDetails implementation is returned
	            String username = ((UserDetails) principal).getUsername();
	            // Retrieve your TaiKhoan instance by username from the database or cache
	            // Assuming you have a method to do so
	            TaiKhoan tk=new TaiKhoan();
	            tk.setUsername("x");
	            return tk;
	        } else {
	            throw new IllegalStateException("Unexpected principal type");
	        }
	}
	 private static TaiKhoan findTaiKhoanByUsername(String username) {
	        return taiKhoanRepository.findByUsername(username);
	    }

}
