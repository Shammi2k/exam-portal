package org.exam.examserver.controller;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.exam.examserver.model.Role;
import org.exam.examserver.model.User;
import org.exam.examserver.model.UserRole;
import org.exam.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController
{
  private UserService userService;

  @PostMapping("/")
  public User createUserWithNormalRole(@RequestBody User user)
    throws Exception
  {
    Role role = new Role("NORMAL");
    Set<UserRole> userRoles = new HashSet<>(Collections.singleton(new UserRole(user, role)));
    return userService.createUser(user, userRoles);
  }

  @GetMapping("/{username}")
  public User getUserByUsername(@PathVariable String username)
  {
    return userService.getUserByUsername(username);
  }

  @DeleteMapping("/{userId}")
  public void deleteUserById(@PathVariable Long userId)
  {
    userService.deleteUserById(userId);
  }

  @DeleteMapping("/all")
  public void deleteAllUsers()
  {
    userService.deleteAllUsers();
  }

  @PutMapping("/{userId}")
  public User updateUserById(@PathVariable Long userId, @RequestBody User user)
  {
    user.setId(userId);
    return userService.updateUserById(user);
  }

  @Autowired
  public void setUserService(UserService userService)
  {
    this.userService = userService;
  }
}
