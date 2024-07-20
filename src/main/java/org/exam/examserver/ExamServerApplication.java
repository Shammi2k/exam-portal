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
//    user.setFirstName("Shammi");
//    user.setLastName("Agarwal");
//    user.setUsername("sam");
//    user.setPassword("1234");
//    user.setEmail("abc@gmail.com");
//    user.setPhone("12345678");
//    user.setProfile("default.png");
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
