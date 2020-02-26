/**
	 * @author Nolan Wacker
	 */

import java.util.ArrayDeque;
import java.util.Queue;

public class UndirectedGraph<T> implements GraphInterface<T> {
	
	public static final int NULL_EDGE = 0;
	  private static final int DEFCAP = 50;  // default capacity
	  private int numVertices;
	  private int maxVertices;
	  private T[] vertices;
	  private int[][] edges;
	  private boolean[] marks;  // marks[i] is mark for vertices[i]

	  public UndirectedGraph()
	  // Instantiates a graph with capacity DEFCAP vertices.
	  {
	    numVertices = 0;
	    maxVertices = DEFCAP;
	    vertices = (T[]) new Object[DEFCAP];
	    marks = new boolean[DEFCAP];
	    edges = new int[DEFCAP][DEFCAP];
	  }
	 
	  public UndirectedGraph(int maxV)
	  // Instantiates a graph with capacity maxV.
	  {
	    numVertices = 0;
	    maxVertices = maxV;
	    vertices = (T[]) new Object[maxV];
	    marks = new boolean[maxV];
	    edges = new int[maxV][maxV];
	  }

	  public boolean isEmpty()
	  // Returns true if this graph is empty; otherwise, returns false.
	  {
		  return (numVertices == 0);
	  }

	  public boolean isFull()
	  // Returns true if this graph is full; otherwise, returns false.
	  {
		  return (numVertices==maxVertices);
	  }

	  public void addVertex(T vertex)
	  // Preconditions:   This graph is not full.
	  //                  vertex is not already in this graph.
	  //                  vertex is not null.
	  //
	  // Adds vertex to this graph.
	  {
		  vertices[numVertices] = vertex;
		  for(int i=0; i<numVertices;i++){
			  edges[numVertices][i] = NULL_EDGE;
			  edges[i][numVertices] = NULL_EDGE;
		  }
		  numVertices++;
	  }

	  public boolean hasVertex(T vertex)
	  // Returns true if this graph contains vertex; otherwise, returns false.
	  {
		  for(int i = 0;i<vertices.length;i++) {
			  if(vertices[i].equals(vertex)) {
				  return true;
			  }
		  }
		  return false;
	  }
	  
	  private int indexIs(T vertex)
	  // Returns the index of vertex in vertices.
	  {
	    int index = 0;
	    while (!vertex.equals(vertices[index]))
	      index++;
	    return index;
	  }
	  
	  public void addEdge(T vertex1, T vertex2)
	  // Adds an edge with the specified weight from fromVertex to toVertex.
	  //Because this graph is undirected, it adds an edge in both directions.
	  {
		  int row;
		  int col;
		  row = indexIs(vertex1);
		  col = indexIs(vertex2);
		  edges[row][col] = 1;
		  edges[col][row] = 1;
	  }

	  public Queue<T> getToVertices(T vertex)
	  // Returns a queue of the vertices that vertex is adjacent to.
	  {
	    Queue<T> adjVertices = new ArrayDeque<T>();
	    int fromIndex;
	    int toIndex;
	    fromIndex = indexIs(vertex);
	    for (toIndex = 0; toIndex < numVertices; toIndex++)
	      if (edges[fromIndex][toIndex] != NULL_EDGE)
	        adjVertices.add(vertices[toIndex]);
	    return adjVertices;
	  }

	  public void clearMarks()
	  // Unmarks all vertices.
	  {
		  marks = new boolean [maxVertices];
		  for(int i=0; i<maxVertices; i++){
			  marks[i] = false;
		  }
	  }

	  public void markVertex(T vertex)
	  // Marks vertex.
	  {
		  int i;
		  i = indexIs(vertex);
		  marks[i] = true;
	  }

	  public boolean isMarked(T vertex)
	  // Returns true if vertex is marked; otherwise, returns false.
	  {
		  int i = indexIs(vertex);
		  if(marks[i] == true){
			  return true;
		  }
		  return false;
		  
	  }
	  
	  public T getUnmarked()
	  // Returns an unmarked vertex if any exist; otherwise, returns null.
	  {
		  for(int i=0; i<maxVertices; i++){
			  if(marks[i] == false){
				  return vertices[i];//Get vertex of I don't return it
			  }
		  }
		  //search for unmarked index and then return at that index place
		  return null;
	  }
	  
	  public boolean edgeExists(T vertex1, T vertex2)
	  // Preconditions:  vertex1 and vertex2 are in the set of vertices
	  //
	  // Return value = (vertex1, vertex2) is in the set of edges
	  {
	    return (edges[indexIs(vertex1)][indexIs(vertex2)] != NULL_EDGE);
	  }

	  public boolean removeEdge(T vertex1, T vertex2)
	  // Preconditions:  vertex1 and vertex2 are in the set of vertices
	  //
	  // Return value = true if edge was in the graph (and has been removed)
	  //              = false if edge was not in the graph
	  {
	    boolean existed = edgeExists(vertex1, vertex2);
	    edges[indexIs(vertex1)][indexIs(vertex2)] = NULL_EDGE;
	    edges[indexIs(vertex2)][indexIs(vertex1)] = NULL_EDGE;
	    return existed;
	  }

}
