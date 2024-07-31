package org.exam.examserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamServerApplication
//  implements CommandLineRunner
{

  public static void main(String[] args)
  {
    SpringApplication.run(ExamServerApplication.class, args);
  }

//  @Override
//  public void run(String... args)
//    throws Exception
//  {
//    System.out.println("====================Starting CLI========================");
//    User user = new User();
//    user.setFirstName("Admin");
//    user.setLastName("Admin");
//    user.setUsername("admin");
//    user.setPassword("admin");
//    user.setEmail("admin@admin.com");
//    user.setPhone("0000000000");
//    user.setProfile("admin.png");
//
//    Role role = new Role();
//    role.setName("ADMIN");
//
//    UserRole userRole = new UserRole();
//    userRole.setUser(user);
//    userRole.setRole(role);
//
//    System.out.println(userService.createUser(user, new HashSet<>(Collections.singleton(userRole))).toString());
//  }
//
//  @Autowired
//  public void setUserService(UserService userService)
//  {
//    this.userService = userService;
//  }
//
//  private UserService userService;
}
