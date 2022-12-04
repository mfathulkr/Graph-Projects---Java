//-----------------------------------------------------
// Title: Graph class
// Author: Mehmet Fatih Ülker
// ID: 15431917506
// Section: 02
// Assignment: 01
// Description: This is the class that our algorithm reside
//-----------------------------------------------------
import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
	
	//Here, we create some values to store our number of vertices, cycle numbers,
	//adjacency list, cycle that we found and some integer values helps us to find cycles
	static int white = 0, gray = 1, black = 2;

	int V;
	LinkedList<Integer> adj[];
	LinkedList<Integer> cycles[];
	static int num_cycles = 0;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	//Here our graph constructor
	Graph(int v) {
		V = v;
		adj = new LinkedList[V];
		cycles = new LinkedList[V];

		for (int i = 0; i < V; i++) {
			adj[i] = new LinkedList();
			cycles[i] = new LinkedList();
		}

	}

	//Explained step by step inside of the method
	void DFSCycleUtil(int source, int parent, int[] color, int parents[]) {

		//Here vertex is gray so we have seen it before 
		//Also it means we found a cycle because our source value is next value
		//in the supervisor method
		if (color[source] == gray) {
			int curr_parent = parent;
			cycles[num_cycles].add(source);
			
			//Here we backtrack the parent array to find the complete cycle
			while (curr_parent != source) {
				cycles[num_cycles].add(curr_parent);
				curr_parent = parents[curr_parent];

			}
			//We put the cycle we found to cycle list of the class
			for (int i = source; i < color.length; i++) {
				if (color[i] == gray) {
					cycles[num_cycles].add(i);
				}
			}
			num_cycles++;
			return;
		}

		//Here completely visited vertex we return
		else if (color[source] == black) {
			return;
		}

		//From this part we consider if the source vertex is white so unvisited
		//We make it gray that means seen before 
		//We put the parent of the source as needed to the parent array
		parents[source] = parent;
		color[source] = gray;

		//Here is an iterator in order to move on to the next of the vertex on adjacency list
		Iterator<Integer> i = adj[source].listIterator();

		//We move go through it until we faced with the parent and call this helper method recursively
		while (i.hasNext()) {
			int n = i.next();
			if (n != parent) {

				DFSCycleUtil(n, source, color, parents);
			}

		}

		//This is for the last standing vertex. We mark it visited at the end
		color[source] = black;

	}
	
	//This method helps us to find the all circles and put them in a list
	//And it has a helper method that it calls 
	void DFSCycle() {

		//Here is two arrays to store visited vertices and their parents
		int color[] = new int[V];
		int parents[] = new int[V];

		//Here we make the all the vertices unvisited for the start
		for (int i = 0; i < V; i++) {
			color[i] = white;
		}
		
		//here we call the helper method for the all unvisited vertices
		for (int i = 0; i < V; i++) {

			if (color[i] == white) {

				DFSCycleUtil(i, -1, color, parents);
			}

		}

	}
	//Here we add the edges
	void addEdge(int u, int v) {

		adj[u].add(v);
		adj[v].add(u);

	}
}