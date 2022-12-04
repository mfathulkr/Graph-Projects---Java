//-----------------------------------------------------
// Title: EdgeWeightedGraph class
// Author: Mehmet Fatih Ülker
// ID: 15431917506
// Section: 02
// Assignment: 03
// Description: This class is for our edge weighted graph 
//-----------------------------------------------------
import java.util.*;

public class EdgeWeightedGraph {
	public int V;
	public LinkedList<Edge>[] adj;
	public LinkedList<Edge> edges;
	public LinkedList<Edge> mst =new LinkedList<Edge>();
	
	public EdgeWeightedGraph(int V) {
		this.V = V;
		adj = new LinkedList[V];
		edges = new LinkedList<Edge>();
		for (int v = 0; v < V; v++)
			adj[v] = new LinkedList<Edge>();
		
	}

	public void addEdge(Edge e) {
		edges.add(e);
		int v = e.either(), w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
	}
	
	

}
