

public class MazeRunner {
	Maze mazeToSolve;
	A4Stack<MazeLocation> path;
	FilePrinter fileWriter;
	boolean solved = false;
	
	public MazeRunner(Maze aMaze) {
		mazeToSolve = aMaze;
		path = new A4Stack<MazeLocation>();
		fileWriter = new FilePrinter();
	}
	
	/*
	 * Purpose: Determines whether there is a path from start to finish in this maze
	 * Parameters: MazeLocation start - starting coordinates of this maze
	 *			   MazeLocation finish - finish coordinates of this maze
	 * Returns: true if there is a path from start to finish
	 */
	public boolean solve(MazeLocation start, MazeLocation finish) {
		fileWriter.println("Searching maze from start: "+start+" to finish: "+finish);
		path.push(start);
		mazeToSolve.setChar(start.getRow(), start.getCol(), 'o');
		try{
			solved = findPath(start, finish);
		}
		catch(IndexOutOfBoundsException e){
		}
		catch(EmptyStackException e){
			//System.out.println(e);
		}
		return solved;
	}
	
	private boolean startBottom(MazeLocation cur, MazeLocation finish){
		MazeLocation nextLoc = new MazeLocation(0, 0);
		if(mazeToSolve.getFinish().equals(cur)){
			solved = true;
			return true;
		}
		else if(path.isEmpty()){
			return false;
		}
		
		else if(mazeToSolve.getChar(cur.row, cur.col - 1) == ' '){
			mazeToSolve.setChar(cur.row, cur.col - 1, 'o');
			fileWriter.println("\n" + mazeToSolve.toString());
			path.push(new MazeLocation(cur.row, cur.col - 1));
			nextLoc = new MazeLocation(cur.row, cur.col - 1);
				
		}
		else if(mazeToSolve.getChar(cur.row, cur.col + 1) == ' '){
			mazeToSolve.setChar(cur.row, cur.col + 1, 'o');
			fileWriter.println("\n" + mazeToSolve.toString());
			path.push(new MazeLocation(cur.row, cur.col +1 ));
			nextLoc = new MazeLocation(cur.row, cur.col + 1);
		}
			
		else if(mazeToSolve.getChar(cur.row - 1, cur.col) == ' '){
			mazeToSolve.setChar(cur.row - 1, cur.col, 'o');
			fileWriter.println("\n" + mazeToSolve.toString());
			path.push(new MazeLocation(cur.row - 1, cur.col));
			nextLoc = new MazeLocation(cur.row - 1, cur.col);
		}
		else{
			mazeToSolve.setChar(cur.row, cur.col, 'x');
			fileWriter.println("\n" + mazeToSolve.toString());
			path.pop();
			nextLoc = path.top();
		}
		return findPath(nextLoc, finish);
	}
	private boolean startLeft(MazeLocation cur, MazeLocation finish){
		MazeLocation nextLoc = new MazeLocation(0, 0);
		if(mazeToSolve.getFinish().equals(cur)){
			solved = true;
			return true;
		}
		else if(path.isEmpty()){
			return false;
		}
		else if(mazeToSolve.getChar(cur.row + 1, cur.col) == ' '){
			mazeToSolve.setChar(cur.row + 1, cur.col, 'o');
			fileWriter.println("\n" + mazeToSolve.toString());
			path.push(new MazeLocation(cur.row + 1, cur.col));
			nextLoc = new MazeLocation(cur.row + 1, cur.col);
		}
		else if(mazeToSolve.getChar(cur.row, cur.col + 1) == ' '){
			mazeToSolve.setChar(cur.row, cur.col + 1, 'o');
			fileWriter.println("\n" + mazeToSolve.toString());
			path.push(new MazeLocation(cur.row, cur.col +1 ));
			nextLoc = new MazeLocation(cur.row, cur.col + 1);
		}
			
		else if(mazeToSolve.getChar(cur.row - 1, cur.col) == ' '){
			mazeToSolve.setChar(cur.row - 1, cur.col, 'o');
			fileWriter.println("\n" + mazeToSolve.toString());
			path.push(new MazeLocation(cur.row - 1, cur.col));
			nextLoc = new MazeLocation(cur.row - 1, cur.col);
		}
		else{
			mazeToSolve.setChar(cur.row, cur.col, 'x');
			fileWriter.println("\n" + mazeToSolve.toString());
			path.pop();
			nextLoc = path.top();
		}
		return findPath(nextLoc, finish);
	}
	private boolean startRight(MazeLocation cur, MazeLocation finish){
		MazeLocation nextLoc = new MazeLocation(0, 0);
		if(mazeToSolve.getFinish().equals(cur)){
			solved = true;
			return true;
		}
		else if(path.isEmpty()){
			return false;
		}
		else if(mazeToSolve.getChar(cur.row + 1, cur.col) == ' '){
			mazeToSolve.setChar(cur.row + 1, cur.col, 'o');
			fileWriter.println("\n" + mazeToSolve.toString());
			path.push(new MazeLocation(cur.row + 1, cur.col));
			nextLoc = new MazeLocation(cur.row + 1, cur.col);
		}
		else if(mazeToSolve.getChar(cur.row, cur.col - 1) == ' '){
			mazeToSolve.setChar(cur.row, cur.col - 1, 'o');
			fileWriter.println("\n" + mazeToSolve.toString());
			path.push(new MazeLocation(cur.row, cur.col - 1));
			nextLoc = new MazeLocation(cur.row, cur.col - 1);
				
		}	
		else if(mazeToSolve.getChar(cur.row - 1, cur.col) == ' '){
			mazeToSolve.setChar(cur.row - 1, cur.col, 'o');
			fileWriter.println("\n" + mazeToSolve.toString());
			path.push(new MazeLocation(cur.row - 1, cur.col));
			nextLoc = new MazeLocation(cur.row - 1, cur.col);
		}
		else{
			mazeToSolve.setChar(cur.row, cur.col, 'x');
			fileWriter.println("\n" + mazeToSolve.toString());
			path.pop();
			nextLoc = path.top();
		}
		return findPath(nextLoc, finish);
	}
	private boolean startTop(MazeLocation cur, MazeLocation finish){
		MazeLocation nextLoc = new MazeLocation(0, 0);
		//if(mazeToSolve.getFinish().equals(cur)){
		//	solved = true;
		//	return true;
		//}
		//else if(path.isEmpty()){
		//	return false;
		//}
		if(mazeToSolve.getChar(cur.row + 1, cur.col) == ' '){
			mazeToSolve.setChar(cur.row + 1, cur.col, 'o');
			fileWriter.println("\n" + mazeToSolve.toString());
			path.push(new MazeLocation(cur.row + 1, cur.col));
			nextLoc = new MazeLocation(cur.row + 1, cur.col);
			
				
			//System.out.println("entered");
		}
		else if(mazeToSolve.getChar(cur.row, cur.col - 1) == ' '){
			mazeToSolve.setChar(cur.row, cur.col - 1, 'o');
			fileWriter.println("\n" + mazeToSolve.toString());
			path.push(new MazeLocation(cur.row, cur.col - 1));
			nextLoc = new MazeLocation(cur.row, cur.col - 1);	
		}
		else if(mazeToSolve.getChar(cur.row, cur.col + 1) == ' '){
			mazeToSolve.setChar(cur.row, cur.col + 1, 'o');
			fileWriter.println("\n" + mazeToSolve.toString());
			path.push(new MazeLocation(cur.row, cur.col +1 ));
			nextLoc = new MazeLocation(cur.row, cur.col + 1);
		}
		else{
			mazeToSolve.setChar(cur.row, cur.col, 'x');
			fileWriter.println("\n" + mazeToSolve.toString());
			path.pop();
			nextLoc = path.top();
		}
		return findPath(nextLoc, finish);
	}
	/*
	 * Purpose: Recursively determines if there is a path from cur to finish
	 * Parameters: MazeLocation cur - current cordinates in this maze
	 *			   MazeLocation finish - goal coordinates of this maze
	 * Returns: true if there is a path from cur to finish
	 *
	 * NOTE: This method updates the Maze's mazeData array when locations
	 *       are visited to an 'o', and further updates locations to an 'x'
	 *       if it is determined they lead to dead ends. If the finish 
	 *       location is found, the Stack named path should contain all of 
	 *       loations visited from the start location to the finish. 
	 */
	private boolean findPath(MazeLocation cur, MazeLocation finish)throws IndexOutOfBoundsException, EmptyStackException{
		MazeLocation nextLoc = new MazeLocation(0, 0);
		
		if(mazeToSolve.getFinish().equals(cur)){
			solved = true;
			//System.out.println("returned true");
			return true;
			
		}
		else if(path.isEmpty()){
			//System.out.println("returned false");
			return false;
		}



		else if(cur.getCol() == mazeToSolve.getCols() - 1){
			startRight(cur, finish);
		}
		else if(cur.getRow() == 0){
			startTop(cur, finish);
		}
		else if(cur.getRow() == mazeToSolve.getRows() - 1){
			startBottom(cur, finish);
		}
		else if(cur.getCol() == 0){
			startLeft(cur, finish);
		}
		else{
			if(mazeToSolve.getChar(cur.row + 1, cur.col) == ' '){
				mazeToSolve.setChar(cur.row + 1, cur.col, 'o');
				fileWriter.println("\n" + mazeToSolve.toString());
				path.push(new MazeLocation(cur.row + 1, cur.col));
				nextLoc = new MazeLocation(cur.row + 1, cur.col);
			}
			else if(mazeToSolve.getChar(cur.row, cur.col - 1) == ' '){
				mazeToSolve.setChar(cur.row, cur.col - 1, 'o');
				fileWriter.println("\n" + mazeToSolve.toString());
				path.push(new MazeLocation(cur.row, cur.col - 1));
				nextLoc = new MazeLocation(cur.row, cur.col - 1);	
			}
			else if(mazeToSolve.getChar(cur.row, cur.col + 1) == ' '){
				mazeToSolve.setChar(cur.row, cur.col + 1, 'o');
				fileWriter.println("\n" + mazeToSolve.toString());
				path.push(new MazeLocation(cur.row, cur.col +1 ));
				nextLoc = new MazeLocation(cur.row, cur.col + 1);
			}
				
			else if(mazeToSolve.getChar(cur.row - 1, cur.col) == ' '){
				mazeToSolve.setChar(cur.row - 1, cur.col, 'o');
				fileWriter.println("\n" + mazeToSolve.toString());
				path.push(new MazeLocation(cur.row - 1, cur.col));
				nextLoc = new MazeLocation(cur.row - 1, cur.col);
			}
			else{
				mazeToSolve.setChar(cur.row, cur.col, 'x');
				fileWriter.println("\n" + mazeToSolve.toString());
				path.pop();
				nextLoc = path.top();
			}
			return findPath(nextLoc, finish);
		}
		return solved;
	}
	

	/*
	 * Purpose: Creates a string of maze locations found in the stack 
	 * Parameters: None
	 * Returns: A String representation of maze locations
	 */
	public String getPathToSolution() {
		String details = "";
		while(!path.isEmpty()) {
			details = path.pop() + "\n" + details;
		}	
		return details;
	}
	
	/*
	 * Purpose: Print the results of the maze run. Outputs the locations 
	 *          visited on the path from start to finish if the maze is 
	 *          solvable, or that no path was found if it is not.
	 * Parameters: boolean - whether or not the maze was solved
	 * Returns void - nothing
	 */
	public void printResults(boolean solved) {
		if (solved) {
			fileWriter.println("\n*** Maze Solved ***");
			fileWriter.println(getPathToSolution());
		} else {
			fileWriter.println("\n--- No path to solution found ---");
		}
		fileWriter.close();
	}
}