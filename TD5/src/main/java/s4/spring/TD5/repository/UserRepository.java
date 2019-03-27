package s4.spring.TD5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.TD5.entities.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	public User findByEmail(String email);

}
