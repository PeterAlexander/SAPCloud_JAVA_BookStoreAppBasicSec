package sap.education.model;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface BookStoreRepository extends JpaRepository<Book, Long> {	
	
	public Book findByIsbn(String isbn);
	@Modifying
	@Transactional
	public void deleteByIsbn(String isbn);
	
}
