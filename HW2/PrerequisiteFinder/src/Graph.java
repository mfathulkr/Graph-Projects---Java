//-----------------------------------------------------
// Title: Graph class
// Author: Mehmet Fatih Ülker
// ID: 15431917506
// Section: 02
// Assignment: 02
// Description: This class is where our algorithm is residing. This class also have DiGraph data structure to handle given data. Also have a method that check if the vertex is reachable or not.
//-----------------------------------------------------
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;

public class Graph {

	Integer V;
	ArrayList<Integer> adj[];

	//Here is constructor
	Graph(Integer v) {
		V = v;
		adj = new ArrayList[V];

		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList();
		}
	}

	//Here we add edge
	void addEdge(Integer u, Integer v) {
		adj[u].add(v);
	}

	// This is the method that our main algorithm reside. Method take 2 integers. These integers
	//are here as key value of the courses that we have.
	// The main idea here is come up with breadth first search algorithm. We take bfs as main 
	//algorithm and we make a couple fixes to have what we want.
	Boolean reach(int source, Integer dw) {

		int destination = dw;

		// Mark all the vertices as not visited(By default set
		// as false)
		boolean vis[] = new boolean[V];

		// Create a queue for BFS
		LinkedList<Integer> list = new LinkedList<Integer>();

		// Mark the current node as visited and enqueue it
		vis[source] = true;
		list.add(source);

		// 'i' will be used to get all adjacent vertices of a vertex
		Iterator<Integer> i;
		while (list.size() != 0) {
			// Dequeue a vertex from queue and print it
			source = list.poll();

			int n;
			i = adj[source].listIterator();

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			while (i.hasNext()) {
				n = i.next();

				// If this adjacent node is the destination node,
				// then return true
				if (n == destination)
					return true;

				// Else, continue to do BFS
				if (!vis[n]) {
					vis[n] = true;
					list.add(n);
				}
			}
		}
		return false;
	}

}
