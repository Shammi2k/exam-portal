package org.exam.examserver.service.impl;

import java.util.Set;

import org.exam.examserver.model.Role;
import org.exam.examserver.model.User;
import org.exam.examserver.model.UserRole;
import org.exam.examserver.repo.RoleRepository;
import org.exam.examserver.repo.UserRepository;
import org.exam.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
  implements UserService
{
  private UserRepository userRepository;
  private RoleRepository roleRepository;

  @Autowired
  public void setUserRepository(UserRepository userRepository)
  {
    this.userRepository = userRepository;
  }

  @Autowired
  public void setRoleRepository(RoleRepository roleRepository)
  {
    this.roleRepository = roleRepository;
  }

  @Override
  public User createUser(User user, Set<UserRole> userRoles)
    throws Exception
  {
    User dbUser = userRepository.findByUsername(user.getUsername());
    if (dbUser != null)
    {
      throw new Exception("User already exists in db");
    }
    for (UserRole userRole : userRoles)
    {
      // Check if role already exists, update the userRole value
      Role role = roleRepository.findByName(userRole.getRole().getName());
      if (role == null)
      {
        roleRepository.save(userRole.getRole());
      }
      else
      {
        userRole.setRole(role);
      }
    }
    user.getUserRoles().addAll(userRoles);
    dbUser = userRepository.save(user);
    return dbUser;
  }
}
