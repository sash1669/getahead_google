/**
 * author: Alexandra Strompova
 * needs fix- sometimes exchanges x,y coords
 * not always returns the largest sum
 */

import java.util.ArrayList;
// Data structure for the result.
class Coord {
 int y = 0;
 int x = 0;
};

class Rect {
 Coord topLeft = new Coord();
 Coord bottomRight = new Coord();
};

class Result {
 int sum;
 Rect rectangle = new Rect();
};

// compute the highest rectangle sum
//possible solution: find every possible rectangle by fixing upper left and bottom right squares,
// then compute and find the largest sum 
// time complexity O(n^3)

public class RectangleSums {
  
	/**
	 * Computes and returns rectangle with the largest possible sum
	 * @param input
	 * @return r resulting object
	 */
    public static Result findLargestSum(int[][] input) {
      Result r = new Result();
      r.sum = 0;
      ArrayList<int[]> left_corners = new ArrayList<int[]>();
      ArrayList<int[]> corner_combinations = new ArrayList<int[]>();
      
      //store lengths of lists in input
      int[] lengths=new int[input.length];
      for(int y=0; y<input.length;y++) {
    	  lengths[y]=input[y].length;
      }
      
      //find all upper left squares and store their coords
      for(int x_left=0; x_left<input.length;x_left++) {
    	  for(int y_left=0; y_left<input[x_left].length;y_left++) {
    		  int[] list=new int[2];
    		  list[0]=x_left;
    		  list[1]=y_left;
    		  left_corners.add(list);
    	  }
      }
      
    //find all bottom right squares and store their coords
      for(int[] corners:left_corners) {
    	  int topleft_x=corners[0];
		  int topleft_y=corners[1];
	      for(int x_right=0; x_right<input.length;x_right++) {
			  for(int y_right=0; y_right<input[x_right].length;y_right++) {
					  if(x_right>=topleft_x && y_right>=topleft_y) {    
						  int[] list=new int[4];
						  list[0]=topleft_x;
						  list[1]=topleft_y;
						  list[2]=x_right;
						  list[3]=y_right;
						  corner_combinations.add(list);
		    		  }
				  } 	    		  
	    	  }
      }
      
      for(int[] corner:corner_combinations) {
    	  int topleft_x=corner[0];
    	  int topleft_y=corner[1];
    	  int bottomright_x=corner[2];
    	  int bottomright_y=corner[3];
    	  
    	//check if possible to create a rectangle with the fixed squares
		  boolean rectangle=true;
		  for(int i=topleft_x; i<bottomright_x; i++) {
			  if(lengths[bottomright_x]>lengths[i]) {
				  rectangle=false;
			  }
		  }
		  
		//calculate the sum
		  if(rectangle) {
			  int rec_sum=0;
			  for(int x=topleft_x; x<=bottomright_x;x++) {
				  for(int y=topleft_y;y<=bottomright_y;y++) {
					  rec_sum+=input[x][y];
				  }
			  }
			  if(rec_sum>r.sum) {
				  r.sum=rec_sum;
				  r.rectangle.bottomRight.x=bottomright_x;
				  r.rectangle.bottomRight.y=bottomright_y;
				  r.rectangle.topLeft.x=topleft_y;
				  r.rectangle.topLeft.y=topleft_x;
				  
			  }
		  }   
      }
      
      return r;
    }
    
    /**
     * Returns the topleft and bottomright coordinates of a rectangle 
     * @param r 
     * @return output list of lists
     */
    public static int[][] output(Result r){
    	int[][] output={{r.rectangle.topLeft.x,r.rectangle.topLeft.y},
    					{r.rectangle.bottomRight.x,r.rectangle.bottomRight.y}};
    	return output;
    }
    
    
    public static void main(String[] args) {
    	int[][] matrix={{2, 3, 4, 5},
    		      {3},
    		      {5},
    		      {5, 3, 4, 2},};
    	
    	Result res=findLargestSum(matrix);
    	System.out.println(res.sum);
    	System.out.println(res.rectangle.topLeft.x);
    	System.out.println(res.rectangle.topLeft.y);
    	System.out.println(res.rectangle.bottomRight.x);
    	System.out.println(res.rectangle.bottomRight.y);
   
    	
    }
}

/*
 * Working solution O(n^6)
 * 
 * public class RectangleSums {
  
    public static Result findLargestSum(int[][] input) {
      Result r = new Result();
      r.sum = 0;
      
      //store lengths of lists
      int[] lengths=new int[input.length];
      for(int y=0; y<input.length;y++) {
    	  lengths[y]=input[y].length;
      }

      
      for(int x_left=0; x_left<input.length;x_left++) {
    	  for(int y_left=0; y_left<input[x_left].length;y_left++) {
    		  
    		  // fix position of upper left square
    		  int topleft_x=x_left;
    		  int topleft_y=y_left;
    		  
    		  for(int x_right=0; x_right<input.length;x_right++) {
    			  for(int y_right=0; y_right<input[x_right].length;y_right++) {
    	    		  
    	    		  // fix position of bottom right square
    	    		  if(x_right>=topleft_x && y_right>=topleft_y) {
    	    			  int bottomright_x=x_right;
        	    		  int bottomright_y=y_right;
        	    		  
        	    		  //check if possible to create a rectangle with the fixed squares
        	    		  boolean rectangle=true;
        	    		  for(int i=topleft_x; i<bottomright_x; i++) {
        	    			  if(lengths[bottomright_x]>lengths[i]) {
        	    				  rectangle=false;
        	    			  }
        	    		  }
        	    		  
        	    		//calculate the sum
        	    		  //check if <= signs correct in loops
        	    		  if(rectangle) {
        	    			  int rec_sum=0;
        	    			  for(int x=topleft_x; x<=bottomright_x;x++) {
        	    				  for(int y=topleft_y;y<=bottomright_y;y++) {
        	    					  rec_sum+=input[x][y];
        	    				  }
        	    			  }
        	    			  if(rec_sum>r.sum) {
        	    				  r.sum=rec_sum;
        	    				  r.rectangle.bottomRight.x=bottomright_x;
        	    				  r.rectangle.bottomRight.y=bottomright_y;
        	    				  r.rectangle.topLeft.x=topleft_x;
        	    				  r.rectangle.topLeft.y=topleft_y;
        	    				  
        	    			  }
        	    		  }
        	    		  
    	    		  } 	    		  
    	    	  }
    	      }
    		  
    	  }
      }
      
      return r;
    }
  
 */

