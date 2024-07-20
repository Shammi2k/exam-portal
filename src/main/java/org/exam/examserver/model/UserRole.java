package org.exam.examserver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UserRole
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  //User mapping. Fetch type defaults to EAGER
  @ManyToOne
  private User user;
  //Role mapping. Fetch type defaults to EAGER
  @ManyToOne
  private Role role;

  public UserRole()
  {
  }

  public UserRole(User user, Role role)
  {
    this.user = user;
    this.role = role;
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  public Role getRole()
  {
    return role;
  }

  public void setRole(Role role)
  {
    this.role = role;
  }
}
