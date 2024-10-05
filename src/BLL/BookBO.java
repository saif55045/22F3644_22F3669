package BLL;

//BookBO.java
import java.util.List;

import DAL.IBookDAO;
import DTO.Book;

public class BookBO implements IBookBo{
private IBookDAO bookDAO;
public BookBO(IBookDAO bookDAO)
{
	this.bookDAO=bookDAO;
}
 public List<Book> searchBooksByTitle(String title) {
     return bookDAO.searchBooksByTitle(title);
 }
}
