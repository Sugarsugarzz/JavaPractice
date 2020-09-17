package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.Books;
import service.BookService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    // controller层 调 service层
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    // 查询全部的书籍，并返回到一个书籍展示页面
    @RequestMapping("/allBook")
    public String list(Model model) {

        List<Books> books = bookService.queryAllBook();

        model.addAttribute("books", books);
        return "allBook";
    }

    // 跳转到增加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddBook() {
        return "addBook";
    }

    // 添加书籍的请求
    @RequestMapping("/addBook")
    public String addBook(Books books) {
        bookService.addBook(books);
        return "redirect:/book/allBook";  // 重定向到 /allBook 请求
    }

    // 跳转到书籍修改页面
    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(int id, Model model) {
        Books books = bookService.queryBookById(id);
        model.addAttribute("books", books);
        return "updateBook";
    }

    // 修改书籍的请求
    @RequestMapping("/updateBook")
    public String updateBook(Books books) {
        bookService.updateBook(books);
        return "redirect:/book/allBook";  // 重定向到 /allBook 请求
    }

    // 删除书籍的请求
    // RestFul风格
    @RequestMapping("/deleteBook/{bookID}")
    public String deleteBook(@PathVariable("bookID") int id) {
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    // 查询书籍
    @RequestMapping("/queryBook")
    public String queryBook(String queryBookName, Model model) {
        Books book = bookService.queryBookByName(queryBookName);
        List<Books> books = new ArrayList<>();
        books.add(book);

        if (book == null) {
            books = bookService.queryAllBook();
            model.addAttribute("error", "未查到");
        }
        model.addAttribute("books", books);
        return "allBook";
    }
    
}
