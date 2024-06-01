package com.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.entity.Book;
import com.bookstore.entity.Cart;
import com.bookstore.entity.TaiKhoan;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private TaiKhoanService tkSer;
    
	@Transactional
	public void addToCart(int id)
	{
		Book book=bookRepo.findById(id).get();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	TaiKhoan tk=null;
    	if(username!=null)
    	{
    		tk=tkSer.findByUserName(username);
    	}
		if(book!=null && tk!=null)
		{
			Cart cart=new Cart(book,tk);
			cartRepo.save(cart);
		}
	}
	
    @Transactional
	public List<Book> getBookByTaiKhoan(int id)
	{
		return cartRepo.getBookByTaiKhoan(id);
	}
    
    @Transactional
	public List<Cart> getCartByTaiKhoan(int taiKhoanId) {
        return cartRepo.getCartByTaiKhoan(taiKhoanId);
    }
	
    @Transactional
	public void deleteCartByTaiKhoan(int id)
	{
		cartRepo.deleteByTaiKhoan_Id(id);
	}
}
