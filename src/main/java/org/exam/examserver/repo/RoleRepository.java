package org.exam.examserver.repo;

import org.exam.examserver.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository
  extends JpaRepository<Role, Long>
{
  Role findByName(String name);
}
