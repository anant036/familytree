package com.test.familytree.service;

import com.test.familytree.model.Person;
import com.test.familytree.model.Relationship;
import java.util.Arrays;
import java.util.Scanner;
import org.springframework.stereotype.Service;

@Service
public class UserInteractionService {

  private static final Scanner SCANNER = new Scanner(System.in);

  private PersonService personService;

  private FamilyTreeService familyTreeService;


  public UserInteractionService(PersonService personService,
      FamilyTreeService familyTreeService) {
    this.personService = personService;
    this.familyTreeService = familyTreeService;
  }

  public void interactWithUser() {
    String value = "0";
    while(value != "-1") {
      System.out.println("Select: \n"
          + " 1 to create persons. \n"
          + " 2 to add relative to person.\n"
          + " 3 to get family tree of person. \n"
          + " -1 to exit.");
      try {
        value = SCANNER.nextLine().trim();
        switch (value) {
          case "1":
            interactToCreatePerson();
            break;
          case "2":
            interactToAddRelativeToPerson();
            break;
          case "3":
            interactToGetFamilyTree();
            break;
          case "-1":
            value = "-1";
            System.out.println("Thanks for using the aplication.");
            break;
          default:
            System.out.println("invalid selection.");
        }
      }
      catch (Exception e) {
        System.out.println("Oops. Try again");
      }
    }

  }

  private void interactToCreatePerson() {
    System.out.println("Enter comma separated person names to create persons");
    String personNames = SCANNER.nextLine().trim();
    personService.createPerson(Arrays.asList(personNames.split(",")));
    System.out.println("Persons have been created. person names: "+personNames);
  }

  private void interactToAddRelativeToPerson() {
    System.out.println("Enter person name to add relationship");
    String personName = SCANNER.nextLine().trim();
    System.out.println("Enter relationship. Valid relations are: "+ Arrays.asList(
        Relationship.values()));
    String relationship = SCANNER.nextLine().trim();
    System.out.println("'"+personName+"' is '"+relationship+ "' of?");
    String relativePersonName = SCANNER.nextLine().trim();
    if(personName.equalsIgnoreCase(relativePersonName)) {
      System.out.println("Person name and Relative name can not be same");
      return;
    }

    Person person = personService.addRelativesToPerson(personName, relationship, relativePersonName);

    System.out.println("relative has been added to person. person name: "+person.getName()
        +", relative name: "+relativePersonName+", relationship: "+ relationship);
  }

  private void interactToGetFamilyTree() {
    System.out.println("Enter person name to get family tree");
    String personName = SCANNER.nextLine().trim();


    String familyTree = familyTreeService.getFamilyTree(personName);
    System.out.println(familyTree);
  }

}
