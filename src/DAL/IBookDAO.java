package DAL;

import java.util.List;

import DTO.Book;

public interface IBookDAO {
	List<Book> searchBooksByTitle(String title);
}
