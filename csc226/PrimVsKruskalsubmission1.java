/* PrimVsKruskal.java
   CSC 226 - Summer 2021
   Assignment 3 - Prim MST versus Kruskal MST Template
   
   The file includes the "import edu.princeton.cs.algs4.*;" so that you can use
   any of the code in the algs4.jar file. You should be able to compile your program
   with the command
   
	javac -cp .;algs4.jar PrimVsKruskal.java
	
   To conveniently test the algorithm with a large input, create a text file
   containing a test graphs (in the format described below) and run
   the program with
   
	java -cp .;algs4.jar PrimVsKruskal file.txt
	
   where file.txt is replaced by the name of the text file. Note: different operating systems have different commands.
   You should all know how to run the algs4.jar file from lab work.
   
   The input consists of a graph (as an adjacency matrix) in the following format:
   
    <number of vertices>
	<adjacency matrix row 1>
	...
	<adjacency matrix row n>
	
   Entry G[i][j] >= 0.0 of the adjacency matrix gives the weight (as type double) of the edge from 
   vertex i to vertex j (if G[i][j] is 0.0, then the edge does not exist).
   Note that since the graph is undirected, it is assumed that G[i][j]
   is always equal to G[j][i].
*/

 import edu.princeton.cs.algs4.*;
 import java.util.Scanner;
 import java.io.File;
 import java.io.FileWriter;
 import java.io.IOException;

//Do not change the name of the PrimVsKruskal class
public class PrimVsKruskal{

	/* PrimVsKruskal(G)
		Given an adjacency matrix for connected graph G, with no self-loops or parallel edges,
		determine if the minimum spanning tree of G found by Prim's algorithm is equal to 
		the minimum spanning tree of G found by Kruskal's algorithm.
		
		If G[i][j] == 0.0, there is no edge between vertex i and vertex j
		If G[i][j] > 0.0, there is an edge between vertices i and j, and the
		value of G[i][j] gives the weight of the edge.
		No entries of G will be negative.
	*/
	static boolean PrimVsKruskal(double[][] G) throws IOException{
		int n = G.length;
		EdgeWeightedGraph EWG = new EdgeWeightedGraph(n);
		
		String s = "";
		int e = 0;
		for(int i=0; i < n; i++){
		    for(int j=0; j < i; j++){
			if(G[i][j] != 0){
			    e++;
			    s = s + i + " " + j + " " + G[i][j] + "\n";
			}
		    }
		}
		s = n + "\n" + e + "\n" + s;
		File tempEWG = new File("tempEWG.txt");
		if(tempEWG.exists())tempEWG.delete();

		if(tempEWG.createNewFile()){
		    FileWriter FW = new FileWriter("tempEWG.txt");
		    FW.write(s);
		    FW.close();
		}
		EdgeWeightedGraph EWG1 = new EdgeWeightedGraph(new In("tempEWG.txt"));
		
		PrimMST prims = new PrimMST(EWG1);
		KruskalMST kruskals = new KruskalMST(EWG1);
		/*
		for (Edge e1 : prims.edges()) {
		    StdOut.println(e1);
		}
		System.out.println("\n");
		for (Edge e2 : kruskals.edges()) {
		    StdOut.println(e2);
		}
		*/
		
		boolean containsThisEdge = false;
		for(Edge e1 : prims.edges()){
		    containsThisEdge = false;
		    for(Edge e2 : kruskals.edges()){
			if(e1.toString().equals(e2.toString())){
			    containsThisEdge = true;
			}
		    }
		    if(!containsThisEdge) return false;
		}
	        
		return true;
		
		//System.out.println(prims.toString() + "\n" + kruskals.toString());
		//System.out.println(EWG1.toString());
		
		/* Build the MST by Prim's and the MST by Kruskal's */
		/* (You may add extra methods if necessary) */
		
		/* ... Your code here ... */
		
		
		/* Determine if the MST by Prim equals the MST by Kruskal */
		//boolean pvk = true;
		/* ... Your code here ... */

		//return pvk;	
	}
		
	/* main()
	   Contains code to test the PrimVsKruskal function. You may modify the
	   testing code if needed, but nothing in this function will be considered
	   during marking, and the testing process used for marking will not
	   execute any of the code below. 
	*/
   public static void main(String[] args) {
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		int n = s.nextInt();
		double[][] G = new double[n][n];
		int valuesRead = 0;
		for (int i = 0; i < n && s.hasNextDouble(); i++){
			for (int j = 0; j < n && s.hasNextDouble(); j++){
				G[i][j] = s.nextDouble();
				if (i == j && G[i][j] != 0.0) {
					System.out.printf("Adjacency matrix contains self-loops.\n");
					return;
				}
				if (G[i][j] < 0.0) {
					System.out.printf("Adjacency matrix contains negative values.\n");
					return;
				}
				if (j < i && G[i][j] != G[j][i]) {
					System.out.printf("Adjacency matrix is not symmetric.\n");
					return;
				}
				valuesRead++;
			}
		}
		
		if (valuesRead < n*n){
			System.out.printf("Adjacency matrix for the graph contains too few values.\n");
			return;
		}	
		boolean pvk = false;
		try{	
		    pvk = PrimVsKruskal(G);
		}catch(IOException e){}
		System.out.printf("Does Prim MST = Kruskal MST? %b\n", pvk);
    }
}
