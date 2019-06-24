package com.test.familytree.service;

import com.test.familytree.data.PersonData;
import com.test.familytree.model.Person;
import com.test.familytree.model.Relationship;
import com.test.familytree.model.Relative;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  private PersonData personData;

  public PersonService(PersonData personData) {
    this.personData = personData;
  }

  public List<Person> createPerson(List<String> personNameList) {

    List<Person> personList = new ArrayList<>();
    for(String personName: personNameList) {
      Person person = new Person();
      person.setName(personName.trim().toLowerCase());
      personData.createPerson(person);
      personList.add(person);
    }
    return personList;
  }

  public Person addRelativesToPerson(String personName, String relation, String relativeName) {

    Person person = personData.getPersonByName(personName);
    Person relativePerson = personData.getPersonByName(relativeName);
    String invalidName = person == null? personName : relativePerson == null? relativeName : null;
    if(invalidName != null) {
      System.out.println("invalid person name: "+invalidName+". \n Available person namesa are: "+personData.getAllPersonNames());
      throw new RuntimeException();
    }

    Relationship relationship;
    try {
      relationship = Relationship.valueOf(relation.toUpperCase());
    }
    catch (Exception e) {
      System.out.println("invalid value for relationship: "+ relation+
          "\n Please select one of :"+ Arrays.asList(Relationship.values()));
      throw new RuntimeException();
    }
    validateRelationshipIsNotAlreadyPresent(person, relativePerson);
    personData.addRelationToPerson(person, relationship, relativePerson);
    if(relationship == Relationship.SPOUSE || relationship == Relationship.SIBLING) {
      personData.addRelationToPerson(relativePerson, relationship, person);
    }
    else {
      personData.addRelationToPerson(relativePerson, Relationship.OTHER, person);
    }
    return person;
  }

  private void validateRelationshipIsNotAlreadyPresent(Person person, Person relativePerson) {
    for(Relative relative: person.getRelatives()) {
      if(relative.getPerson().equals(relativePerson)) {
        System.out.println("Relation between "+person.getName()+" and "+relativePerson.getName()+" is already present, so could not add this relation.");
        throw new RuntimeException();
      }
    }
  }
}
