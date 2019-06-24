package com.test.familytree.model;

import org.jgrapht.graph.DefaultEdge;

public class RelationshipEdge extends DefaultEdge {
  private String relation;

  public RelationshipEdge(String relation)
  {
    this.relation = relation;
  }


  @Override
  public String toString()
  {
    return " is " + relation + " of";
  }
}
