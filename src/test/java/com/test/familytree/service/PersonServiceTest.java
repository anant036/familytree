package com.test.familytree.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.test.familytree.data.PersonData;
import com.test.familytree.model.Person;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

  private static final String PERSON_NAME = "personName";
  private static final String INVALID_PERSON_NAME = "invalidPersonName";
  private static final String RELATIVE_PERSON_NAME = "relativePersonName";
  private static final String RELATION_NAME = "SIBLING";
  private static final String INVALID_RELATION_NAME = "invalidRelationName";

  @Mock
  private PersonData personData;
  @InjectMocks
  private PersonService underTest;

  private Person person = new Person();
  private Person relativePerson = new Person();

  @Before
  public void setUp() {
    person.setName(PERSON_NAME);
    when(personData.getPersonByName(PERSON_NAME)).thenReturn(person);

    relativePerson.setName(RELATIVE_PERSON_NAME);
    when(personData.getPersonByName(RELATIVE_PERSON_NAME)).thenReturn(relativePerson);

    when(personData.getAllPersonNames()).thenReturn(PERSON_NAME);
  }

  @Test
  public void testCreatePerson() {
    List<Person> responsePerson = underTest.createPerson(Collections.singletonList(PERSON_NAME));
    assertThat(responsePerson).isNotNull();
    assertThat(responsePerson.get(0).getName()).isEqualTo(PERSON_NAME.toLowerCase());
  }

  @Test
  public void testAddRelativesToPerson() {
    underTest.addRelativesToPerson(PERSON_NAME, RELATION_NAME, RELATIVE_PERSON_NAME);
  }

  @Test(expected = RuntimeException.class)
  public void testAddRelativesToPersonWhenEitherOfPersonNameIsInvalid() {
    underTest.addRelativesToPerson(PERSON_NAME, RELATION_NAME, INVALID_PERSON_NAME);
  }

  @Test(expected = RuntimeException.class)
  public void testAddRelativesToPersonWhenRelationIsInvalid() {
    underTest.addRelativesToPerson(PERSON_NAME, INVALID_RELATION_NAME, RELATIVE_PERSON_NAME);
  }
}