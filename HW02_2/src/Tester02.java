//-----------------------------------------------------
// Title: Tester02 class
// Author: Mehmet Fatih Ülker
// ID: 15431917506
// Section: 02
// Assignment: 02
// Description: This class is where we handle the input and output.
//-----------------------------------------------------
import java.util.*;

public class Tester02 {

	public static void main(String[] args) {

		//Here we take values
		Scanner scan = new Scanner(System.in);

		int row = scan.nextInt();
		int column = scan.nextInt();

		//List to store vertices
		Node[][] list = new Node[row][column];

		int idcount = 0;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				String who = scan.next();
				Node n = new Node(idcount, who);
				list[i][j] = n;
				idcount ++;
			}
		}

		//We create graph
		Graph g = new Graph();

		//we add edges
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {

				Node s = list[i][j];

				if (i == row - 1 && j == column - 1)
					continue;

				if (j == column - 1 && i != row - 1) {
					g.addEdge(s, list[i + 1][j]);
				} else if (i == row - 1 && j != column - 1) {
					g.addEdge(s, list[i][j + 1]);
				} else {
					g.addEdge(s, list[i][j + 1]);
					g.addEdge(s, list[i + 1][j]);
				}

				if (i != row - 1 && j != column - 1) {
					g.addEdge(s, list[i + 1][j + 1]);
				}

				if (i != row - 1 && j != 0) {
					g.addEdge(s, list[i + 1][j - 1]);
				}
			}
		}

		System.out.println();

		//Here we call the algorithm method
		g.findX();

		//We take x list from the graph class
		LinkedList<Node> xs = new LinkedList<Node>();

		xs = g.xler;
		//We make them X.
		for (int x = 0; x < xs.size(); x++) {
			
			Node xnode = xs.get(x);
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					if(xnode == list[i][j]) {
						list[i][j].data = "X";
					}
				}
			}
		}
		//We print
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				System.out.print(list[i][j].data + " ");
			}
			System.out.println();
		}

	}
}
