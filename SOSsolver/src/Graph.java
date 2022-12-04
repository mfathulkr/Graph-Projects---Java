//-----------------------------------------------------
// Title: Graph class
// Author: Mehmet Fatih Ülker
// ID: 15431917506
// Section: 02
// Assignment: 02
// Description: This class is where our algorithm is residing. It has the graph data structure for Node objects.
//-----------------------------------------------------
import java.util.*;

class Graph {

	// We use Hashmap to store the edges in the graph
	private Map<Node, List<Node>> map = new HashMap<>();
	
	LinkedList<Node> xler = new LinkedList<Node>();

	// This function adds a new vertex to the graph
	public void addVertex(Node s) {
		map.put(s, new LinkedList<Node>());
	}

	// This function adds the edge
	// between source to destination
	public void addEdge(Node source, Node destination) {
		if (!map.containsKey(source))
			addVertex(source);

		if (!map.containsKey(destination))
			addVertex(destination);

		map.get(source).add(destination);
		map.get(destination).add(source);
	}

	//This method is important. We iterate through the map then if we found any x we put it to the xler list.
	public void findX() {	
		
        for (Node v : map.keySet()) {
        	if(v.data.equalsIgnoreCase("S"))continue;
        	if(map.get(v).size() < 8) continue;
        	boolean ekle = true;
            for (Node w : map.get(v)) {
            	if(!w.data.equalsIgnoreCase("S")) {
            		ekle = false;
            		break;
            	}
            }
            if(ekle) {
            	xler.add(v);
            }
        }
	}
	
	
}