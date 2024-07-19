package org.exam.examserver.service;

import java.util.Set;

import org.exam.examserver.model.User;
import org.exam.examserver.model.UserRole;

public interface UserService
{
  User createUser(User user, Set<UserRole> userRoles)
    throws Exception;
}
