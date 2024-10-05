package BLL;

import java.util.List;

import DTO.Book;

public interface IBookBo {
	List<Book> searchBooksByTitle(String title);
}
