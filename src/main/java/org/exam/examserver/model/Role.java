package org.exam.examserver.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "roles")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
public class Role
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
  private Set<UserRole> userRoles = new HashSet<>();

  public Role()
  {
  }

  public Role(Long id, String name)
  {
    this.id = id;
    this.name = name;
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Set<UserRole> getUserRoles()
  {
    return userRoles;
  }

  public void setUserRoles(Set<UserRole> userRoles)
  {
    this.userRoles = userRoles;
  }
}
