package com.test.familytree.model;

public class Relative {

  private Relationship relationship;
  private Person person;

  public Relative(Relationship relationship, Person person) {
    this.relationship = relationship;
    this.person = person;
  }

  public Relationship getRelationship() {
    return relationship;
  }

  public void setRelationship(Relationship relationship) {
    this.relationship = relationship;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Relative) || object == null
        || ((Relative) object).getRelationship() == null
        || ((Relative) object).getPerson() == null) {
      return false;
    }
    return ((Relative) object).getRelationship().equals(this.getRelationship())
        && ((Relative) object).getPerson().equals(this.getPerson());
  }

  @Override
  public int hashCode() {
    return (relationship == null? 0: relationship.hashCode())*person.hashCode();
  }

  @Override
  public String toString() {
    return relationship.toString()+" of "+person.getName();
  }
}
