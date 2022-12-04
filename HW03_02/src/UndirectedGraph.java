//-----------------------------------------------------
// Title: UndirectedGraph class
// Author: Mehmet Fatih Ülker
// ID: 15431917506
// Section: 02
// Assignment: 03
// Description: This class is for store the our MST so we can find needed paths. It has an DFS based algorithm inside which I used in HW1
//-----------------------------------------------------

import java.util.*;

public class UndirectedGraph {

	int vertex;
	LinkedList<Integer> adj[];
	ArrayList<Integer> paths;

	public UndirectedGraph(int vertex) {
		this.vertex = vertex;
		adj = new LinkedList[vertex];
		paths = new ArrayList<Integer>();
		for (int i = 0; i < vertex; i++) {
			adj[i] = new LinkedList<>();
		}
	}

	public void addEdge(int source, int destination) {

		// add edge
		adj[source].addFirst(destination);

		// add back edge ((for undirected)
		adj[destination].addFirst(source);
	}

	/*
	 * Our algorithm starts with this method. Here we get 2 input parameter which are source and destination. We create
	 * a boolean array to find out if the vertices we considering are visited or not. And a list to store the path that we found.
	 * As soon as we start the algorithm we store the source value as the first element of the path. Then we call our  helper method.
	 */
	public void findPaths(Integer s, Integer d) {
		boolean[] visited = new boolean[vertex];
		ArrayList<Integer> pathList = new ArrayList<>();

		// add source to path[]
		pathList.add(s);

		// Call recursive utility
		findPathsHelper(s, d, visited, pathList);
	}

	/*
	 * Helper method runs with a recursive way. Here we mark the considered vertex(source) as visited at that time. Then we move through to 
	 * edges of the source vertex and we add it to path list. Then we keep calling the helper method in order to move edges of the source 
	 * until we faced with the destination vertex. At that point we return and finish the method every time. While doing that we remove the last vertex 
	 * of the path in order to chase any other paths when we are backtracking the path that we find. Any time we find a path again we are putting it to the 
	 * path list with the help of a temp list.
	 */
	// A recursive function to print
	// all paths from 'u' to 'd'.
	// isVisited[] keeps track of
	// vertices in current path.
	// localPathList<> stores actual
	// vertices in the current path
	void findPathsHelper(Integer s, Integer d, boolean[] visited, ArrayList<Integer> local) {

		if (s.equals(d)) {
			
			ArrayList<Integer> temp = new ArrayList<Integer>();
			
			temp.addAll(local); 
			
			paths = temp;
			
			return;
		}

		// Mark the current node
		visited[s] = true;

		// Recur for all the vertices
		// adjacent to current vertex
		for (Integer i : adj[s]) {
			if (!visited[i]) {
				// store current node
				// in path[]
				local.add(i);
				findPathsHelper(i, d, visited, local);

				// remove current node
				// in path[]
				local.remove(i);
			}
		}

		// Mark the current node
		visited[s] = false;
	}

}
