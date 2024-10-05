package PD;

//BookSearchApp.java
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import DTO.Book;
import BLL.BookBO;
import BLL.IBookBo;
import DAL.BookDAO;
import DAL.IBookDAO;
public class BookSearchApp extends JFrame {
 private JTextField searchField;
 private JButton searchButton;
 private JTextArea resultArea;
 private IBookBo bookBO;

 public BookSearchApp(IBookBo bookBO) {
	 this.bookBO=bookBO;
     setTitle("Book Search Application");
     setSize(500, 400);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setLocationRelativeTo(null);
     setLayout(new BorderLayout());

     JPanel topPanel = new JPanel();
     topPanel.add(new JLabel("Search for a book by title: "));
     searchField = new JTextField(20);
     topPanel.add(searchField);
     searchButton = new JButton("Search");
     topPanel.add(searchButton);
     add(topPanel, BorderLayout.NORTH);

     resultArea = new JTextArea();
     resultArea.setEditable(false);
     add(new JScrollPane(resultArea), BorderLayout.CENTER);

     searchButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            String result = searchBooks(searchField.getText());
         }
     });
 }

 private String searchBooks(String string) {
     String title = searchField.getText();
     List<Book> books = bookBO.searchBooksByTitle(title);
     resultArea.setText(""); // Clear previous results

     if (books.isEmpty()) {
         resultArea.append("No books found.");
     } else {
         for (Book book : books) {
             resultArea.append(book.toString() + "\n");
         }
     }
	return title;
 }

 public static void main(String[] args) {
     SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
        	 IBookDAO d=new BookDAO();
        	 IBookBo b=new BookBO(d);
             new BookSearchApp(b).setVisible(true);
         }
     });
 }
}
