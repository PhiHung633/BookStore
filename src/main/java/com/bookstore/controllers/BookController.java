package com.bookstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.entity.TaiKhoan;
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;
import com.bookstore.service.TaiKhoanService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookController {
	@Autowired
	private BookService bookSer;


	@Autowired
	private CategoryService cateSer;
	
	@Autowired
	private TaiKhoanService tkSer;
	
	String username;

	@GetMapping("/")
	public String home(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	TaiKhoan tk=tkSer.findByUserName(username);
    	//System.out.println(tk.getIsSell());
//    	TaiKhoan tk=new TaiKhoan();
//    	tk=tkSer.findByUserName(username);
        model.addAttribute("tk", tk);
		return "home";
	}

	@GetMapping("/book_register")
	public String bookRegister() {
		return "bookRegister";
	}

	@GetMapping("/available_books")
	public ModelAndView getAllBook(HttpSession session,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	TaiKhoan tk=tkSer.findByUserName(username);
    	//System.out.println(tk.getIsSell());
//    	TaiKhoan tk=new TaiKhoan();
//    	tk=tkSer.findByUserName(username);
        model.addAttribute("tk", tk);
    	List<Book> list = bookSer.getAllBook();
		List<Category> list1 = cateSer.getAllCategory();
		Book latestBook = bookSer.getBookMaxId(); // Lấy ra sách mới nhất

		ModelAndView m = new ModelAndView("bookList");
		m.addObject("book", list);
		m.addObject("cate", list1);
		m.addObject("l", latestBook); // Truyền sách mới nhất vào model
		return m;
	}

	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	TaiKhoan tk=tkSer.findByUserName(username);
    	//System.out.println(tk.getIsSell());
//    	TaiKhoan tk=new TaiKhoan();
//    	tk=tkSer.findByUserName(username);
        model.addAttribute("tk", tk);
		bookSer.save(b);
		return "redirect:/available_books";
	}

	

	

	@RequestMapping("editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	TaiKhoan tk=tkSer.findByUserName(username);
    	//System.out.println(tk.getIsSell());
//    	TaiKhoan tk=new TaiKhoan();
//    	tk=tkSer.findByUserName(username);
        model.addAttribute("tk", tk);
		Book b = bookSer.getBookById(id);
		model.addAttribute("book", b);
		return "bookEdit";
	}

	@RequestMapping("deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	TaiKhoan tk=tkSer.findByUserName(username);
    	//System.out.println(tk.getIsSell());
//    	TaiKhoan tk=new TaiKhoan();
//    	tk=tkSer.findByUserName(username);
        model.addAttribute("tk", tk);
		bookSer.deleteBook(id);
		return "redirect:/available_books";
	}
}
