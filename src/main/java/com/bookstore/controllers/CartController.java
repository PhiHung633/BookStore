package com.bookstore.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookstore.entity.Book;
import com.bookstore.entity.Cart;
import com.bookstore.entity.TaiKhoan;
import com.bookstore.service.CartService;
import com.bookstore.service.TaiKhoanService;

@Controller
public class CartController {

	@Autowired
	private CartService cartSer;
	
	@Autowired
	private TaiKhoanService tkSer;
	
	@GetMapping("/Cart")
    public String showCartPage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	TaiKhoan tk=tkSer.findByUserName(username);
        model.addAttribute("tk", tk);
        List<Book> listBook=cartSer.getBookByTaiKhoan(tk.getId());
        List<Cart> listCart = cartSer.getCartByTaiKhoan(tk.getId());

        Map<Integer, Integer> bookQuantityMap = new HashMap<>();
        for (Cart cart : listCart) {
            Book book = cart.getBook();
            int bookId = book.getId();
            int quantity = bookQuantityMap.getOrDefault(bookId, 0);
            bookQuantityMap.put(bookId, quantity + 1);
        }
        model.addAttribute("bookQuantityMap", bookQuantityMap);
        model.addAttribute("book", listBook);
        return "Cart"; // This should match the name of your HTML file in the templates folder (cart.html)
    }
	
	@GetMapping({"/addToCart/{id}"})
	public String addToCart(@PathVariable(name="id")int id,Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	TaiKhoan tk=tkSer.findByUserName(username);
        model.addAttribute("tk", tk);
		cartSer.addToCart(id);
		List<Book> listBook=cartSer.getBookByTaiKhoan(tk.getId());
		List<Cart> listCart = cartSer.getCartByTaiKhoan(tk.getId());

        Map<Integer, Integer> bookQuantityMap = new HashMap<>();
        for (Cart cart : listCart) {
            Book book = cart.getBook();
            int bookId = book.getId();
            int quantity = bookQuantityMap.getOrDefault(bookId, 0);
            bookQuantityMap.put(bookId, quantity + 1);
        }
        model.addAttribute("bookQuantityMap", bookQuantityMap);
        model.addAttribute("book", listBook);
		return "Cart";
	}
	@RequestMapping("/deleteCart/{id}")
    public String deleteMyList(@PathVariable("id") int id) {
        cartSer.deleteCartByTaiKhoan(id);
        return "redirect:/Cart";
    }
}
