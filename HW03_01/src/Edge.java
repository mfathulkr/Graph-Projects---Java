//-----------------------------------------------------
// Title: Edge class
// Author: Mehmet Fatih Ülker
// ID: 15431917506
// Section: 02
// Assignment: 03
// Description: This class is for store the data of our edges. It has the fundamental methods of the edge class for a edge weighted graph
//-----------------------------------------------------

public class Edge implements Comparable<Edge> {
	public final int weight;
	public final int v, w;

	public Edge(int v, int w, int weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public int either() {
		return v;
	}

	public int other(int vertex) {
		if (vertex == v)
			return w;
		else
			return v;
	}

	public int compareTo(Edge that) {
		if (this.weight < that.weight)
			return -1;
		else if (this.weight > that.weight)
			return +1;
		else
			return 0;
	}
}