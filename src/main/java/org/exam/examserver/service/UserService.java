package org.exam.examserver.service;

import java.util.Set;

import org.exam.examserver.model.User;
import org.exam.examserver.model.UserRole;

public interface UserService
{
  User createUser(User user, Set<UserRole> userRoles)
    throws Exception;

  User getUserByUsername(String username);

  User getUserById(Long id);

  void deleteUserById(Long id);

  User updateUserById(User user);

  void deleteAllUsers();
}
