package s4.spring.TD5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.TD5.entities.Category;

public interface HistoryRepository extends JpaRepository<Category,Integer>{

}
