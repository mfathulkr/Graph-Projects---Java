//-----------------------------------------------------
// Title: KruskalMST class
// Author: Mehmet Fatih Ülker
// ID: 15431917506
// Section: 02
// Assignment: 01
// Description: This is the class that our algorithm reside. Thanks to this class we can have our MST. In order to do that algorithm adds
//				the edges from low to high weight in to MST unless it causes any cycles.
//-----------------------------------------------------
public class KruskalMST {
	private GenQueue<Edge> mst = new GenQueue<Edge>();

	public KruskalMST(EdgeWeightedGraph G) {
		
		Edge[]edges = new Edge [G.edges.size()];
		for(int i = 0; i < edges.length; i++) {
			edges[i] = G.edges.get(i);
		}
		MinPQ<Edge> pq = new MinPQ<Edge>(edges);
		UF uf = new UF(G.V);
		while (!pq.isEmpty() && mst.size() < G.V - 1) {
			Edge e = pq.delMin();
			int v = e.either(), w = e.other(v);
			if (!uf.connected(v, w)) {
				uf.union(v, w);
				mst.enqueue(e);
			}
		}
	}

	public GenQueue<Edge> getMST(){
		return this.mst;
	}
}
