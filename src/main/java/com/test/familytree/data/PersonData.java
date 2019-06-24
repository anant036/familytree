package com.test.familytree.data;

import com.test.familytree.model.Person;
import com.test.familytree.model.Relationship;
import com.test.familytree.model.Relative;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class PersonData {

  private Map<String, Person> personNameAndDetailMap = new HashMap<>(1);

  public Person createPerson(Person person) {
    personNameAndDetailMap.put(person.getName(), person);
    return person;
  }

  public Person getPersonByName(String personName) {
    return personNameAndDetailMap.get(personName);
  }

  public Person addRelationToPerson(Person person, Relationship relationship, Person relativePerson) {
    Relative relative = new Relative(relationship, relativePerson);
    personNameAndDetailMap.get(person.getName()).addRelatives(relative);
    return person;
  }

  public String getAllPersonNames() {
    return personNameAndDetailMap.keySet().toString();
  }

}
