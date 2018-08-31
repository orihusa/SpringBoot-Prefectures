package basic.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import basic.example.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
}