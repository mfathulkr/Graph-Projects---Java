//-----------------------------------------------------
// Title: PathFinder class
// Author: Mehmet Fatih Ülker
// ID: 15431917506
// Section: 02
// Assignment: 01
// Description: This class is a runner class that program is executed. It handles the with input in order to make it ready for the graph and algorithm class.
//-----------------------------------------------------
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

public class PathFinder {

	public static void main(String[] args) {

		// Here we create some string that contains letters of the alphabet in order to
		// pull the city names from the given input.
		Scanner scan = new Scanner(System.in);

		String alpha = "abcdefghijklmnopqrstuvwxyz";

		String road = scan.next();

		// We put alphabet and the input to lists
		ArrayList<Character> alp = strToCharList(alpha);

		ArrayList<Character> roadList = strToCharList(road);

		// We create list for the road information but only letter just as we need
		ArrayList<Character> onlyLetter = new ArrayList<>();

		for (int i = 0; i < roadList.size(); i++) {
			for (int j = 0; j < alp.size(); j++) {
				if (roadList.get(i).equals(alp.get(j))) {
					onlyLetter.add(roadList.get(i));
				}
			}
		}

		// Here we use set to kill duplicates because we want a list that contains all
		// the cities
		// To have that we need to kill duplicates from the roads list that we have
		Set<Character> cityset = new TreeSet<>(onlyLetter);

		ArrayList<Character> AllCity = new ArrayList<>();

		AllCity.addAll(cityset);

		int V = AllCity.size();

		// Here is a critical part. Since our algorithm is designed for integers in
		// order to have a clear
		// and understandable code we use a hash map to store the cities and move on
		// with their key values
		Map<Integer, Character> cityhash = new HashMap<>();
		ArrayList<Integer> hashedroad = new ArrayList<>();

		for (int i = 0; i < AllCity.size(); i++) {

			cityhash.put(i, AllCity.get(i));

		}
		// We convert the road list to key list
		for (int i = 0; i < onlyLetter.size(); i++) {
			for (int j = 0; j < cityhash.size(); j++) {

				Character c = onlyLetter.get(i);

				if (c.equals(cityhash.get(j))) {
					hashedroad.add(j);
				}

			}
		}

		// We create our graph
		Graph g = new Graph(V);

		// We add the edges
		for (int i = 0; i < hashedroad.size() - 1; i++) {

			if (i % 2 == 0)
				g.addEdge(hashedroad.get(i), hashedroad.get(i + 1));

		}

		// We get source
		String s = scan.next();
		// We get destination
		String s1 = scan.next();
		scan.close();

		char source = s.charAt(0);

		Character src = Character.valueOf(source);
		
		char destination = s1.charAt(0);

		Character dest = Character.valueOf(destination);
		
		//We get the key values of the cities
		Integer startKey = getKey(cityhash, src);
		Integer destKey = getKey(cityhash, dest);

		//We create list to store the paths
		ArrayList<ArrayList<Character>> pathCity = new ArrayList<ArrayList<Character>>();

		//Here we call our algorithm
		g.findPaths(startKey, destKey);

		
		//We pull the data of the paths from the graph class to our pathCity list
		for (int i = 0; i < g.paths.size(); i++) {
			ArrayList<Character> list = new ArrayList<>();
			for (int j = 0; j < g.paths.get(i).size(); j++) {

				Character ch = cityhash.get(g.paths.get(i).get(j));

				list.add(ch);

			}
			Collections.sort(list);
			pathCity.add(list);
		}
		
		// We remove the paths that have more than 4 vertices
		for (int i = 0; i < pathCity.size(); i++) {

			if (pathCity.get(i).size() > 4)
				pathCity.remove(i);

		}

		//Here  we print no path if there is no path between source and destination
		if (pathCity.size() == 0) {
			System.out.println("No path");
			return;
		}
		
		//Here below calls helps us to get the paths to demanded order
		
		Collections.sort(pathCity, new Comparator<ArrayList<Character>> () {
		    @Override
		    public int compare(ArrayList<Character> a, ArrayList<Character> b) {
		        return a.get(0).compareTo(b.get(0));
		    }
		});
		
		Collections.sort(pathCity, new Comparator<ArrayList<Character>> () {
		    @Override
		    public int compare(ArrayList<Character> a, ArrayList<Character> b) {
		        return a.get(1).compareTo(b.get(1));
		    }
		});
		
		Collections.sort(pathCity, new Comparator<ArrayList<Character>>(){
		    public int compare(ArrayList<Character> a1, ArrayList<Character> a2) {
		        return a1.size() - a2.size();
		    }
		});

		// We print paths
		for (int i = 0; i < pathCity.size(); i++) {
			for (int j = 0; j < pathCity.get(i).size(); j++) {
				if (j != pathCity.get(i).size() - 1) {
					System.out.print(pathCity.get(i).get(j) + "-");
				} else {
					System.out.print(pathCity.get(i).get(j));
				}

			}
			System.out.println();
		}

	}

	// Since we are not able to key value of the values that easily, I come up with
	// that method which does this.
	private static Integer getKey(Map<Integer, Character> map, Character value) {
		for (Entry<Integer, Character> entry : map.entrySet()) {
			if (entry.getValue().equals(value)) {
				return entry.getKey();
			}
		}
		return null;
	}

	// This method helps us to convert strings to character list
	private static ArrayList<Character> strToCharList(String str) {

		ArrayList<Character> list = new ArrayList<>();

		for (int i = 0; i < str.length(); i++) {

			Character c = str.charAt(i);

			list.add(c);

		}

		return list;
	}

}
