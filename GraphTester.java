import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class GraphTester {
	/**
	 * @author Nolan Wacker
	 */
	
	/**
	 * Returns true if a path exists on graph, from startVertex to endVertex; 
	 * otherwise returns false. Uses breadth-first search algorithm.
	 * @param graph
	 * @param startVertex
	 * @param endVertex
	 * @return
	 */
	private static boolean isPathBF(GraphInterface<String> graph,
			String startVertex, 
			String endVertex    ) {
		
		Queue<String> queue = new ArrayDeque<String>();
		Queue<String> vertexQueue = new ArrayDeque<String>();

		boolean found = false;
		String currVertex;      // vertex being processed
		String adjVertex;       // adjacent to currVertex

		graph.clearMarks();
		graph.markVertex(startVertex);
		queue.add(startVertex);

		do
		{
			currVertex = queue.remove();
			// System.out.println(currVertex);
			if (currVertex.equals(endVertex))
				found = true;
			else
			{
				vertexQueue = graph.getToVertices(currVertex); 
				while (!vertexQueue.isEmpty())
				{
					adjVertex = vertexQueue.remove();
					if (!graph.isMarked(adjVertex))
					{
						graph.markVertex(adjVertex);
						queue.add(adjVertex);
					}
				}
			}
		} while (!queue.isEmpty() && !found);

		return found;
	}


	public static void main(String[] args) {

		UndirectedGraph<String> graph = new UndirectedGraph<>();
		String s0 = new String("Bob");
	    String s1 = new String("Jack");
	    String s2 = new String("Cynthia");
	    String s3 = new String("Alex");
	    String s4 = new String("Cathy");
	    String s5 = new String("Kevin");
	    String s6 = new String("Colleen");
	    String s7 = new String("George");

	    //add cities as a vertex to the graph
	    graph.addVertex(s0);
	    graph.addVertex(s1);
	    graph.addVertex(s2);
	    graph.addVertex(s3);
	    graph.addVertex(s4);
	    graph.addVertex(s5);
	    graph.addVertex(s6);
	    graph.addVertex(s7);

	    //add edges
	    //edges will be represented in the adjacency matrix
	    //Try removing edges to see if you get a false returned
	    graph.addEdge(s0, s1);
	    graph.addEdge(s0, s2);
	    graph.addEdge(s0, s3);
	    graph.addEdge(s2, s3);
	    graph.addEdge(s3, s4);
	    graph.addEdge(s5, s6);
	    graph.addEdge(s5, s7);

	    ArrayList<String> vertex = new ArrayList<String>(); 
	    vertex.add(s0);
	    vertex.add(s1);
	    vertex.add(s2);
	    vertex.add(s3);
	    vertex.add(s4);
	    vertex.add(s5);
	    vertex.add(s6);
	    vertex.add(s7);
	    
	    ArrayList<String> friends = new ArrayList<String>();
	    ArrayList<String> friendsTwo = new ArrayList<String>();
	    ArrayList<String> friendsThree = new ArrayList<String>();
	    for(int i=1; i<vertex.size(); i++){
	    	if(graph.edgeExists(s0, vertex.get(i)) == true){
	    		friends.add(vertex.get(i));
	    	}
	    }
	    for(int i=0; i<vertex.indexOf("Alex"); i++){
	    	if(graph.edgeExists("Alex", vertex.get(i)) == true){
	    		friendsTwo.add(vertex.get(i));
	    	}
	    }
	    for(int i=vertex.indexOf("Alex")+1; i<vertex.size(); i++){
	    	if(graph.edgeExists("Alex", vertex.get(i)) == true){
	    		friendsTwo.add(vertex.get(i));
	    	}
	    }
	    for(int i=0; i<vertex.size()-1; i++){
	    	if(graph.edgeExists(s7, vertex.get(i)) == true){
	    		friendsThree.add(vertex.get(i));
	    	}
	    }
	    System.out.println("Bob's friends are: " + friends);
	    System.out.println("Alex's friends are: " +friendsTwo);
	    System.out.println("George's friends are: " +friendsThree);
	    
	    boolean result;
	    result = isPathBF(graph, s1, s4);
	    System.out.println("Are Jack and Cathy indirect friends?: " + result);
	    result = isPathBF(graph, s1, s7);
	    System.out.println("Are Jack and George indirect friends?: " +result);
	}




}
