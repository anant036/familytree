package com.test.familytree.service;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.util.mxCellRenderer;
import com.test.familytree.data.PersonData;
import com.test.familytree.model.Relative;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyTreeService {

  private PersonData personData;
  private ResponseVisualizationService responseVisualizationService;

  @Autowired
  public FamilyTreeService(PersonData personData, ResponseVisualizationService responseVisualizationService) {
    this.personData = personData;
    this.responseVisualizationService = responseVisualizationService;
  }

  public String getFamilyTree(String personName) {
    Set<Relative> relatives;
    try {
      relatives = personData.getPersonByName(personName).getRelatives();
    }
    catch (Exception e) {
      System.out.println("invalid person name: "+personName+". \n Available person namesa are: "+personData.getAllPersonNames());
      throw new RuntimeException("invalid person name: "+personName+". \n Available person namesa are: "+personData.getAllPersonNames());
    }
    if(relatives.size() == 0) {
      return personName+" has no relatives";
    }


    String relationList = relatives.toString().substring(1, relatives.toString().length()-1).replaceAll(",", "\n");
    String response = personName+" is \n  "+relationList;
    String filePath = responseVisualizationService.createFamilyTreeImage(personName, relatives);
    return  response+"\n result is available at location: "+filePath;

  }

}
