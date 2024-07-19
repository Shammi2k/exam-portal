package org.exam.examserver.repo;

import org.exam.examserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
  extends JpaRepository<User, Long>
{
  User findByUsername(String username);
}
