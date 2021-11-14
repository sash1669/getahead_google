import java.util.Collection;
import java.util.HashSet;
import java.util.*;


//TODO: create a representation of the grid in a graph- no need since I have grid already on input
//implement the algorithm according to slack
// implement depth first search on the grid - but how to find neighbours- done
// TODO: implement Dictionary class - how the fuck ?
// 


class Dictionary {
	public Collection<String> words;
	
  public Dictionary(Collection<String> words) {
	  this.words=words;
	  }

  public boolean isWord(String word) {
    for(String w:this.words) {
    	if(w.equals(word)) {
    		return true;
    	}
    }
    return false;
  }
  
  public boolean isPrefix(String prefix) {
	  for(String w:this.words) {
		  if(w.startsWith(prefix)) {
			  return true;
		  }
	  }
	  return false;
  }
}

public class WordSearch {
	
	public static boolean isInBounds(int row, int col, char[][] grid) {
		return (0<=row && row<=grid.length-1)
				&& (0<=col && col<=grid[row].length-1);
	}
	
	public static boolean rec(String prefix,int row, int col, Dictionary dictionary,HashSet<String> words, char[][] grid,boolean[][] visited ) {
			
		if(dictionary.isPrefix(prefix)) {
    		
			List<int[]> neighbors =Arrays.asList(
					new int[] {row-1,col}, //above
					new int[] {row+1,col}, //below
					new int[] {row,col-1}, //left
					new int[] {row, col+1}, //right
					new int[] {row-1,col-1}, //up left
					new int[] {row+1,col-1}, //down left
					new int[] {row-1,col+1}, //up right
					new int[] {row+1,col+1}); //downright
			
			for(int[] neighbor: neighbors) {
				int neiRow=neighbor[0];
				int neiCol=neighbor[1];
				
				if(isInBounds(neiRow,neiCol,grid) && !visited[neiRow][neiCol]) {
					visited[neiRow][neiCol]=true;
					rec(prefix+grid[neiRow][neiCol],neiRow,neiCol,dictionary,words,grid,visited);
					visited[neiRow][neiCol]=false;
				}	
			}
		}
		
		if(dictionary.isWord(prefix)) {
			words.add(prefix);
			visited= new boolean[grid.length][grid[0].length];
		}
		
		return true;
	}
	
	public static HashSet<String> findWords(char[][] grid, Dictionary dictionary) {
	    // all words found
		HashSet<String> words= new HashSet<>();  
		
		for(int row=0; row<grid.length; row++) {
			for(int col=0; col<grid[row].length; col++) {
				//call recursive word search function on 1 letter
				String letter="";
				letter+=grid[row][col];
	    		boolean[][] visited= new boolean[grid.length][grid[0].length];
	    		visited[row][col]=true;
		    	rec(letter,row, col, dictionary,words,grid,visited);
		    	visited[row][col]=false;
	    		}
			}
	    return words;
	}
}
/*	
public static void main(String[] args) {
	HashSet<String> words= new HashSet<>();
	char[][] grid= {{'A','A','R'},
					{'T','C','D'}};
	var<String> collection = new ArrayList<String>();
	String[] dict={"DAT"};
	for(String w:dict) {
		collection.add(w);
	}
	Dictionary dictionary= new Dictionary(collection);
	words=WordSearch.findWords(grid, dictionary);
	
	System.out.println(words);
	
	
	}
}
*/