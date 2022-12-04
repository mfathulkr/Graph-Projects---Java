//-----------------------------------------------------
// Title: Tester class
// Author: Mehmet Fatih Ülker
// ID: 15431917506
// Section: 02
// Assignment: 02
// Description: This class is a runner class that program is executed. It handles the with input in order to make it ready for the graph and algorithm class.
//-----------------------------------------------------
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Tester {

	public static void main(String[] args) {

		//here is course list
		ArrayList<String> course_list = new ArrayList<>();

		Scanner scan = new Scanner(System.in);

		//we get vertex number
		int vertex = scan.nextInt();

		Integer v = Integer.valueOf(vertex);

		String courses_str = scan.next();

		//we handle the data to store them correctly
		for (int i = 0, j = 3; i < courses_str.length() - 2; i = i + 4, j = j + 4) {

			String temp;

			temp = courses_str.substring(i, j);

			course_list.add(temp);

		}

		int edge_num = scan.nextInt();

		//we use hashmap to deal with only key values of the courses
		Map<Integer, String> hash = new HashMap<>();

		for (int i = 0; i < course_list.size(); i++) {

			hash.put(i, course_list.get(i));

		}

		ArrayList<Integer> hashedEdges = new ArrayList<>();

		//we put key edge data to list
		for (int i = 0; i < edge_num; i++) {
			String inp = scan.next();

			for (int j = 0, k = 3; j < 5; j = j + 4, k = k + 4) {
				String temp;
				temp = inp.substring(j, k);
				hashedEdges.add(getKey(hash, temp));
			}
		}

		// here we add the edges
		Graph g = new Graph(v);

		for (int i = 0; i < hashedEdges.size() - 1; i++) {
			if (i % 2 == 0)
				g.addEdge(hashedEdges.get(i), hashedEdges.get(i + 1));
		}

		//here we getting asked course
		String who = scan.next();

		//here we find the demanded courses key value
		Integer w = getKey(hash, who);

		ArrayList<String> preq = new ArrayList<>();

		//Here we run our algorithm. If the condition is true we take the reachable course as a prequisite to list
		for (int i = 0; i < v; i++) {

			boolean b = g.reach(i, w);

			if (b) {
				String p = hash.get(i);
				preq.add(p);
			}

		}

		Collections.sort(preq);

		//if no preq
		if (preq.size() == 0) {
			System.out.println("There is no prerequisite for this course!");
			return;
		}

		//We print the preqs
		for (int i = 0; i < preq.size(); i++) {
			if (i == preq.size() - 1) {
				System.out.println(preq.get(i));
				return;
			}
			System.out.print(preq.get(i) + ",");
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
