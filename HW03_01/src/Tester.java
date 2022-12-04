//-----------------------------------------------------
// Title: Tester class
// Author: Mehmet Fatih Ülker
// ID: 15431917506
// Section: 02
// Assignment: 03
// Description: This is our tester class which input data manipulated for the solution
//-----------------------------------------------------

import java.util.*;
import java.util.Map.Entry;

public class Tester {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int vertex = scan.nextInt();
		int edge = scan.nextInt();

		EdgeWeightedGraph g = new EdgeWeightedGraph(vertex);

		Map<Integer, String> hash = new HashMap<>();

		ArrayList<String>[] edgeList = new ArrayList[edge];

		for (int i = 0; i < edgeList.length; i++) {
			edgeList[i] = new ArrayList<String>();
		}

		// fill up edgeList
		for (int i = 0; i < edge; i++) {
			for (int j = 0; j < 3; j++) {
				String ip = scan.next();
				edgeList[i].add(ip);
			}
		}

		ArrayList<String> vtemp = new ArrayList<String>();

		for (int i = 0; i < edgeList.length; i++) {
			for (int j = 0; j < 2; j++) {
				vtemp.add(edgeList[i].get(j));
			}
		}

		//We use set the eliminate duplicates
		Set<String> vertexSet = new TreeSet<>(vtemp);

		ArrayList<String> vertexList = new ArrayList<>();
		vertexList.addAll(vertexSet);
		Collections.sort(vertexList);

		for (int i = 0; i < vertexList.size(); i++) {

			hash.put(i, vertexList.get(i));

		}

		// Here we create edges and add them to the weightedgraph
		for (int i = 0; i < edgeList.length; i++) {
			int v = 0, w = 0, weight = 0;

			for (int j = 0; j < 3; j++) {
				if (j == 0) {
					v = getKey(hash, edgeList[i].get(j));
				} else if (j == 1) {
					w = getKey(hash, edgeList[i].get(j));
				} else {
					weight = Integer.parseInt(edgeList[i].get(j));
				}
			}
			Edge e = new Edge(v, w, weight);
			g.addEdge(e);
		}

		//Here we use kruskal algorithm to have MST
		KruskalMST kruskal = new KruskalMST(g);

		GenQueue<Edge> mstq = kruskal.getMST();

		LinkedList<Edge> mst = mstq.list;

		g.mst = mst;

		UndirectedGraph ug = new UndirectedGraph(g.V);

		for (int i = 0; i < mst.size(); i++) {
			Edge e = mst.get(i);
			int v = e.either();
			int w = e.other(v);
			ug.addEdge(v, w);
		}
		
		ArrayList<Integer> dist = new ArrayList<Integer>();
		
		for (int i = 0; i < g.V - 1; i++) {
			ArrayList<String> where = new ArrayList<String>();
			for (int j = 0; j < 2; j++) {
				String s = scan.next();
				where.add(s);
			}
			
			int from = getKey(hash, where.get(0));
			int to = getKey(hash, where.get(1));
			
			ug.findPaths(from, to);
			
			int distance = 0;
			
			for(int k = 0; k < ug.paths.size() - 1; k++) {
				int v = ug.paths.get(k);
				int w  =ug.paths.get(k + 1);
				
				for(int m = 0; m < mst.size(); m++) {
					Edge e = mst.get(m);
					
					int ei = e.either();
					int ot = e.other(ei);
					
					if((ei == v || ei == w) && (ot == v || ot == w)) {
						distance = distance + e.weight;
					}
				}	
			}
			
			dist.add(distance);
			
		}
		
		for(int i = 0; i < dist.size(); i++) {
			System.out.println(dist.get(i));
		}

	}

	private static Integer getKey(Map<Integer, String> map, String value) {
		for (Entry<Integer, String> entry : map.entrySet()) {
			if (entry.getValue().equals(value)) {
				return entry.getKey();
			}
		}
		return null;
	}

}
