package com.bookstore.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.entity.TaiKhoan;
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;
import com.bookstore.service.TaiKhoanService;

@Controller
public class ManageController {
    @Autowired
    private BookService bookser;
    
    @Autowired
    TaiKhoanService tkSer;
    
    @Autowired
    private CategoryService cateSer;
    
    @RequestMapping("/manageproduct")
    public ModelAndView getMyList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        TaiKhoan tk = tkSer.findByUserName(username);
        List<Book> list1 = bookser.getBookBySellId(tk.getId());
        List<Category> list = cateSer.getAllCategory();

        ModelAndView m = new ModelAndView("manageproduct");
        m.addObject("products", list1);
        m.addObject("categories", list);

        return m;
    }

    @RequestMapping("/deleteListProduct/{id}")
    public String deleteMyList(@PathVariable("id") int id) {
        bookser.deleteBook(id);
        return "redirect:/manageproduct";
    }

    @RequestMapping("/add")
    public String addMyList(@RequestParam("name") String tentruyen,
    		@RequestParam("file") MultipartFile image,
                            @RequestParam("price") String price,
                            @RequestParam("author") String author,
                            @RequestParam("category") Category category) {
        Book book = new Book();
        book.setName(tentruyen);
        String filename="";
		String projectRoot = System.getProperty("user.dir");

		// Specify the name of the folder you want to create inside the project root
		String folderName = "uploads";

		// Combine the project root and folder name to get the full path
		String fullPath = projectRoot + File.separator + folderName;

		// Create the folder if it doesn't exist
		File uploadRootDir = new File(fullPath);
		if (!uploadRootDir.exists()) {
		    uploadRootDir.mkdirs();
		}
		try {
			int extensionSTT=image.getOriginalFilename().indexOf(".");
			String extension=image.getOriginalFilename().substring(extensionSTT);
	        // Generate random integers in range 0 to 999
	        String stt = Integer.toString(LocalDateTime.now().getYear()) +Integer.toString(LocalDateTime.now().getDayOfYear())+Integer.toString(LocalDateTime.now().getHour())+Integer.toString(LocalDateTime.now().getMinute())+Integer.toString(LocalDateTime.now().getSecond());
			filename = stt+extension;
			
			File serverFile = new File(uploadRootDir.getAbsoluteFile() + File.separator + filename);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(image.getBytes());
			stream.close();

		} catch (Exception e) {	
		}
		String linkanh= filename;
		book.setImage(linkanh);
		book.setAuthor(author);
        book.setPrice(price);
        book.setCategory(category);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        TaiKhoan tk = tkSer.findByUserName(username);
        book.setTaiKhoan(tk);
        bookser.save(book);
        return "redirect:/manageproduct";
    }

    @RequestMapping("/loadProduct/{id}")
    public ModelAndView loadProduct(@PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        TaiKhoan tk = tkSer.findByUserName(username);
        Book b = bookser.getBookById(id);
        List<Category> list = cateSer.getAllCategory();
        ModelAndView modelAndView = new ModelAndView("bookEdit");
        modelAndView.addObject("tk", tk);
        modelAndView.addObject("book", b);
        modelAndView.addObject("categories", list);

        return modelAndView;
    }
    @RequestMapping("/editProduct/{id}")
    public String editMyList(
    						@RequestParam("file") MultipartFile image,
    						@PathVariable("id") int id,
    						@RequestParam("name") String tentruyen,
                            @RequestParam("price") String price,
                            @RequestParam("author") String author,
                            @RequestParam("category") Category category) {
        Book book = bookser.getBookById(id);   
        book.setName(tentruyen);
        String filename="";
		String projectRoot = System.getProperty("user.dir");

		// Specify the name of the folder you want to create inside the project root
		String folderName = "uploads";

		// Combine the project root and folder name to get the full path
		String fullPath = projectRoot + File.separator + folderName;

		// Create the folder if it doesn't exist
		File uploadRootDir = new File(fullPath);
		if (!uploadRootDir.exists()) {
		    uploadRootDir.mkdirs();
		}
		try {
			int extensionSTT=image.getOriginalFilename().indexOf(".");
			String extension=image.getOriginalFilename().substring(extensionSTT);
	        // Generate random integers in range 0 to 999
	        String stt = Integer.toString(LocalDateTime.now().getYear()) +Integer.toString(LocalDateTime.now().getDayOfYear())+Integer.toString(LocalDateTime.now().getHour())+Integer.toString(LocalDateTime.now().getMinute())+Integer.toString(LocalDateTime.now().getSecond());
			filename = stt+extension;
			
			File serverFile = new File(uploadRootDir.getAbsoluteFile() + File.separator + filename);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(image.getBytes());
			stream.close();

		} catch (Exception e) {	
		}
		String linkanh= filename;
		book.setImage(linkanh);
        book.setPrice(price);
        book.setCategory(category);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        TaiKhoan tk = tkSer.findByUserName(username);
        book.setTaiKhoan(tk);
        bookser.save(book);
        return "redirect:/manageproduct";
    }
}
