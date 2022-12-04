//-----------------------------------------------------
// Title: CycleFinder class
// Author: Mehmet Fatih Ülker
// ID: 15431917506
// Section: 02
// Assignment: 01
// Description: This class is a runner class that program is executed. It handles the with input in order to make it ready for the graph and algorithm class.
//-----------------------------------------------------
import java.util.*;
import java.util.Map.Entry;

public class CycleFinder {

	public static void main(String[] args) {

		//Here we create some string that contains letters of the alphabet in order to pull the city names from the given input.
		Scanner scan = new Scanner(System.in);

		String alpha = "abcdefghijklmnopqrstuvwxyz";

		String road = scan.next();

		// We put alphabet and the input to lists
		ArrayList<Character> alp = strToCharList(alpha);

		ArrayList<Character> roadList = strToCharList(road);
		
		//We create list for the road information but only letter just as we need
		ArrayList<Character> onlyLetter = new ArrayList<>();

		for (int i = 0; i < roadList.size(); i++) {
			for (int j = 0; j < alp.size(); j++) {
				if (roadList.get(i).equals(alp.get(j))) {
					onlyLetter.add(roadList.get(i));
				}
			}
		}
		
		//Here we use set to kill duplicates because we want a list that contains all the cities
		//To have that we need to kill duplicates from the roads list that we have
		Set<Character> cityset = new TreeSet<>(onlyLetter);

		ArrayList<Character> AllCity = new ArrayList<>();

		AllCity.addAll(cityset);

		int V = AllCity.size();
		
		//Here is a critical part. Since our algorithm is designed for integers in order to have a clear 
		//and understandable code we use a hash map to store the cities and move on with their key values 
		Map<Integer, Character> cityhash = new HashMap<>();
		ArrayList<Integer> hashedroad = new ArrayList<>();

		for (int i = 0; i < AllCity.size(); i++) {

			cityhash.put(i, AllCity.get(i));

		}
		//We convert the road list to key list
		for (int i = 0; i < onlyLetter.size(); i++) {
			for (int j = 0; j < cityhash.size(); j++) {

				Character c = onlyLetter.get(i);

				if (c.equals(cityhash.get(j))) {
					hashedroad.add(j);
				}

			}
		}

		//We create our graph
		Graph g = new Graph(V);

		//We add the edges
		for (int i = 0; i < hashedroad.size() - 1; i++) {

			if (i % 2 == 0)
				g.addEdge(hashedroad.get(i), hashedroad.get(i + 1));

		}

		//We get input of the city 
		String s = scan.next();
		scan.close();

		char cha = s.charAt(0);

		Character c = Character.valueOf(cha);
		
		//We move on with key value of the demanded city's key value
		Integer startKey = getKey(cityhash, c);

		LinkedList<LinkedList<Integer>> cyc = new LinkedList<LinkedList<Integer>>();

		//Here our algorithm does its job
		g.DFSCycle();
		
		//We pull the data of the cycles from the graph class
		for(int i = 0; i < g.cycles.length; i++) {
			
			for(int j = 0; j < g.cycles[i].size(); j++) {
				
				if(g.cycles[i].get(j).equals(startKey)) {
					cyc.add(g.cycles[i]);
					continue;
				}
				
			}
			
		}
		
		//Here we convert the cycles data key to city name then we sort them
		ArrayList<ArrayList<Character>> cycleroads = new ArrayList<ArrayList<Character>>();

		for (int i = 0; i < cyc.size(); i++) {
			ArrayList<Character> list = new ArrayList<>();
			for (int j = 0; j < cyc.get(i).size(); j++) {

				Character ch = cityhash.get(cyc.get(i).get(j));

				list.add(ch);

			}
			Collections.sort(list);
			cycleroads.add(list);
		}
		
		//We kill the duplicates of the circles that we found
		Collection<ArrayList<Character>> duplicatekiller = new HashSet<>(cycleroads);
		cycleroads.clear();
		cycleroads.addAll(duplicatekiller);
		
		//We kill the repeated vertex in data of each circle since it is not an big issue we can handle it in runner class
		for(int i = 0; i < cycleroads.size(); i ++) {
			
			Collection<Character> killer = new HashSet<>(cycleroads.get(i));
			cycleroads.get(i).clear();
			cycleroads.get(i).addAll(killer);
			
		}
		
		//We remove the cycles that have less vertex than 5 as asked
		for (int i = 0; i < cycleroads.size(); i++) {
			if(cycleroads.get(i).size()<5)cycleroads.remove(i);
		}
		
		if(cycleroads.size() == 0) {
			System.out.println("No cycle");
			return;
		}
		
		// We print the cycles of the given vertex
		for (int i = 0; i < cycleroads.size(); i++) {
			for (int j = 0; j < cycleroads.get(i).size(); j++) {
				if (j != cycleroads.get(i).size() - 1) {
					System.out.print(cycleroads.get(i).get(j) + "-");
				} else {
					System.out.print(cycleroads.get(i).get(j));
				}

			}
			System.out.println();
		}

	}

	//Since we are not able to key value of the values that easily, I come up with that method which does this.
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