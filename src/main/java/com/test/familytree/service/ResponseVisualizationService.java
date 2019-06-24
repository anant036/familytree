package com.test.familytree.service;

import static com.test.familytree.util.Constant.PATH_DELIMITER;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.view.mxGraph;
import com.test.familytree.model.RelationshipEdge;
import com.test.familytree.model.Relative;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.stereotype.Service;

@Service
public class ResponseVisualizationService {

  private static final String FILE_PATH = System.getProperty("user.dir");
  private static final String FILE_NAME = "responseFamilyTree.png";

  public String createFamilyTreeImage(String personName, Set<Relative> relatives) {

    try{
      Graph<String, DefaultEdge> graph
          = new DefaultDirectedGraph<>(DefaultEdge.class);
      graph.addVertex(personName);
      for(Relative relative: relatives) {
        graph.addVertex(relative.getPerson().getName());
        graph.addEdge(personName, relative.getPerson().getName(), new RelationshipEdge(relative.getRelationship().name()));
      }

      JGraphXAdapter<String, DefaultEdge> graphAdapter = new JGraphXAdapter<>(graph);
      mxCircleLayout layout = new mxCircleLayout(graphAdapter);
      layout.execute(graphAdapter.getDefaultParent());
      BufferedImage image =
          mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);
      File imgFile = new File(FILE_PATH+PATH_DELIMITER+FILE_NAME);
      ImageIO.write(image, "PNG", imgFile);
    }
    catch(Exception e){
      System.out.println("Exception in creating image");
      throw new RuntimeException();
    }
    return FILE_PATH+PATH_DELIMITER+FILE_NAME;

  }

}
