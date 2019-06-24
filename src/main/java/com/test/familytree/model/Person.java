package com.test.familytree.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Person {

  private String name;
  private Set<Relative> relatives;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Relative> getRelatives() {
    return relatives == null ? Collections.emptySet(): relatives;
  }

  public void setRelatives(Set<Relative> relatives) {
    this.relatives = relatives;
  }

  public void addRelatives(Relative relative) {
    if(relatives == null) {
      relatives = new HashSet<>();
    }
    this.relatives.add(relative);
  }


  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Person) || object == null || ((Person) object).getName() == null) {
      return false;
    }
    return ((Person) object).getName().equals(this.getName());
  }

  @Override
  public int hashCode() {
    return name == null? 0 :name.hashCode();
  }
}
