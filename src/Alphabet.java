import java.util.*;
	public class Alphabet {
	/**
	* Given a dictionary (a list of words sorted in lexicographic order)
	* of all words in a fictional language, finds the alphabet (an ordered list of
	* characters) of that language.
	*/
	public static Character[] findAlphabet(String[] words) {
	// Handle edge-case, only 1 word is supplied and the order is unknown:
	// return the list of chars in that word
	if (words.length == 1) {
	return words[0].chars().mapToObj(c -> (char) c).toArray(Character[]::new);
	}
	// Create a graph to represent the characters order
	Graph graph = new Graph();
	// Iterate the words to build the graph
	for (int i = 0; i < words.length - 1; i++) {
	// Take the current two words and find the first mismatching character
	String word1 = words[i];
	String word2 = words[i + 1];
	for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
	// If we find a mismatching character, add an edge
	// from character of word1 to that of word2
	if (word1.charAt(j) != word2.charAt(j)) {
	graph.addEdge(word1.charAt(j), word2.charAt(j));
	break;
	}
	}
	}
	// Topological sort the above created graph
	return graph.topologicalSort();
	}
	}
	class Graph {
	// A map of chars->lists representing the graph (like an adjacency list)
	private Map<Character, LinkedList<Character>> adjacencyList = new HashMap<>();
	// function to add an edge to graph
	void addEdge(char startVertex, char endVertex) {
	adjacencyList.computeIfAbsent(startVertex, l -> new LinkedList<>()).add(endVertex);
	}
	// Creates a Topological Sort of the graph
	Character[] topologicalSort() {
	Stack<Character> stack = new Stack<>();
	// Mark all the vertices as not visited
	Set<Character> visited = new HashSet<>();
	// Call the recursive helper function to store Topological
	// Sort starting from all vertices one by one
	for (Character c : adjacencyList.keySet()) {
	if (!visited.contains(c)) {
	topologicalSortUtil(c, visited, stack);
	}
	}
	// convert the stack to a char array
	int i = 0;
	Character[] sorted = new Character[stack.size()];
	while (!stack.isEmpty()) {
	sorted[i++] = stack.pop();
	}
	return sorted;
	}
	// A recursive function used by topologicalSort
	private void topologicalSortUtil(
	char currentVertex, Set<Character> visited, Stack<Character> stack) {
	// Mark the current node as visited.
	visited.add(currentVertex);
	// Recur for all the vertices adjacent to this vertex
	for (Character adjacentVertex : adjacencyList.getOrDefault(
	currentVertex, new LinkedList<>())) {
	if (!visited.contains(adjacentVertex)) {
	topologicalSortUtil(adjacentVertex, visited, stack);
	}
	}
	// Push current vertex to stack which stores the result
	stack.push(currentVertex);
	}
}

  

