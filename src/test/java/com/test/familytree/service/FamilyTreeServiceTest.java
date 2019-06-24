package com.test.familytree.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.test.familytree.data.PersonData;
import com.test.familytree.model.Person;
import com.test.familytree.model.Relationship;
import com.test.familytree.model.Relative;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FamilyTreeServiceTest {

  private static final String PERSON_NAME = "personName";
  private static final String INVALID_PERSON_NAME = "invalidPersonName";
  private static final String RELATIVE_PERSON_NAME = "relativePersonName";
  private static final String FILE_PATH = "filePath";

  @Mock
  private PersonData personData;
  @Mock
  private ResponseVisualizationService responseVisualizationService;

  @InjectMocks
  private FamilyTreeService underTest;

  private Person person = new Person();
  private Person relativePerson = new Person();

  @Before
  public void setUp() {
    person.setName(PERSON_NAME);
    when(personData.getPersonByName(PERSON_NAME)).thenReturn(person);

    relativePerson.setName(RELATIVE_PERSON_NAME);
    when(personData.getAllPersonNames()).thenReturn(PERSON_NAME);
    when(responseVisualizationService.createFamilyTreeImage(anyString(), anySet())).thenReturn(FILE_PATH);
  }


  @Test
  public void testGetFamilyTreeForValidPersonNameAndRelatives() {
    Relative relative = new Relative(Relationship.SIBLING, relativePerson);
    person.addRelatives(relative);

    String responseString = underTest.getFamilyTree(PERSON_NAME);
    assertThat(responseString).isEqualTo("Relatives of "+PERSON_NAME+" is/are "
        + Arrays.asList(relative).toString()+"\n result is available at location: "+FILE_PATH);
  }

  @Test
  public void testGetFamilyTreeForValidPersonNameAndNoRelative() {
    String responseString = underTest.getFamilyTree(PERSON_NAME);
    assertThat(responseString).isEqualTo(PERSON_NAME+" has no relatives");
  }

  @Test(expected = RuntimeException.class)
  public void testGetFamilyTreeForInalidPersonNameTest() {
    underTest.getFamilyTree(INVALID_PERSON_NAME);
  }
}