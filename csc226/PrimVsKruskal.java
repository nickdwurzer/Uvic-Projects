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
		/*
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
		//System.out.println(s);
		File tempEWG = new File("tempEWG.txt");
		if(tempEWG.exists())tempEWG.delete();

		if(tempEWG.createNewFile()){
		    FileWriter FW = new FileWriter("tempEWG.txt");
		    FW.write(s);
		    FW.close();
		}
		EdgeWeightedGraph EWG1 = new EdgeWeightedGraph(new In("tempEWG.txt"));
		*/
		/**                                                                                                                                                                                  
		 *  The {@code PrimMST} class represents a data type for computing a                                                                                                                 
		 *  <em>minimum spanning tree</em> in an edge-weighted graph.                                                                                                                        
		 *  The edge weights can be positive, zero, or negative and need not                                                                                                                 
		 *  be distinct. If the graph is not connected, it computes a <em>minimum                                                                                                            
		 *  spanning forest</em>, which is the union of minimum spanning trees                                                                                                               
		 *  in each connected component. The {@code weight()} method returns the                                                                                                             
		 *  weight of a minimum spanning tree and the {@code edges()} method                                                                                                                 
		 *  returns its edges.                                                                                                                                                               
		 *  <p>                                                                                                                                                                              
		 *  This implementation uses <em>Prim's algorithm</em> with an indexed                                                                                                               
		 *  binary heap.                                                                                                                                                                     
		 *  The constructor takes &Theta;(<em>E</em> log <em>V</em>) time in                                                                                                                 
		 *  the worst case, where <em>V</em> is the number of                                                                                                                                
		 *  vertices and <em>E</em> is the number of edges.                                                                                                                                  
		 *  Each instance method takes &Theta;(1) time.                                                                                                                                      
		 *  It uses &Theta;(<em>V</em>) extra space (not including the                                                                                                                       
		 *  edge-weighted graph).                                                                                                                                                            
		 *  <p>                                                                                                                                                                              
		 *  This {@code weight()} method correctly computes the weight of the MST                                                                                                            
		 *  if all arithmetic performed is without floating-point rounding error                                                                                                             
		 *  or arithmetic overflow.                                                                                                                                                          
		 *  This is the case if all edge weights are non-negative integers                                                                                                                   
		 *  and the weight of the MST does not exceed 2<sup>52</sup>.                                                                                                                        
		 *  <p>                                                                                                                                                                              
		 *  For additional documentation,                                                                                                                                                    
		 *  see <a href="https://algs4.cs.princeton.edu/43mst">Section 4.3</a> of                                                                                                            
		 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.                                                                                                              
		 *  For alternate implementations, see {@link LazyPrimMST}, {@link KruskalMST},                                                                                                      
		 *  and {@link BoruvkaMST}.                                                                                                                                                          
		 *                                                                                                                                                                                   
		 *  @author Robert Sedgewick                                                                                                                                                         
		 *  @author Kevin Wayne                                                                                                                                                              
		 */
		public class PrimMST {
		    private static final double FLOATING_POINT_EPSILON = 1E-12;

		    private Edge[] edgeTo;        // edgeTo[v] = shortest edge from tree vertex to non-tree vertex                                                                                   
		    private double[] distTo;      // distTo[v] = weight of shortest such edge                                                                                                        
		    private boolean[] marked;     // marked[v] = true if v on tree, false otherwise                                                                                                  
		    private IndexMinPQ<Double> pq;

		    /**                                                                                                                                                                              
		     * Compute a minimum spanning tree (or forest) of an edge-weighted graph.                                                                                                        
		     * @param G the edge-weighted graph                                                                                                                                              
		     */

		    public PrimMST(EdgeWeightedGraph G) {
			edgeTo = new Edge[G.V()];
			distTo = new double[G.V()];
			marked = new boolean[G.V()];
			pq = new IndexMinPQ<Double>(G.V());
			for (int v = 0; v < G.V(); v++)
			    distTo[v] = Double.POSITIVE_INFINITY;

			for (int v = 0; v < G.V(); v++)      // run from each vertex to find                                                                                                         
			    if (!marked[v]) prim(G, v);      // minimum spanning forest                                                                                                              

			// check optimality conditions                                                                                                                                               
			assert check(G);
		    }

		    // run Prim's algorithm in graph G, starting from vertex s                                                                                                                       
		    private void prim(EdgeWeightedGraph G, int s) {
			distTo[s] = 0.0;
			pq.insert(s, distTo[s]);
			while (!pq.isEmpty()) {
			    int v = pq.delMin();
			    scan(G, v);
			}
		    }

		    // scan vertex v                                                                                                                                                                 
		    private void scan(EdgeWeightedGraph G, int v) {
			marked[v] = true;
			for (Edge e : G.adj(v)) {
			    int w = e.other(v);
			    if (marked[w]) continue;         // v-w is obsolete edge                                                                                                                 
			    if (e.weight() < distTo[w]) {
				distTo[w] = e.weight();
				edgeTo[w] = e;
				if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
				else                pq.insert(w, distTo[w]);
			    }
			}
		    }
		    /**                                                                                                                                                                              
		     * Returns the edges in a minimum spanning tree (or forest).                                                                                                                     
		     * @return the edges in a minimum spanning tree (or forest) as                                                                                                                   
		     *    an iterable of edges                                                                                                                                                       
		     */
		    public Iterable<Edge> edges() {
			Queue<Edge> mst = new Queue<Edge>();
			for (int v = 0; v < edgeTo.length; v++) {
			    Edge e = edgeTo[v];
			    if (e != null) {
				mst.enqueue(e);
			    }
			}
			return mst;
		    }

		    /**                                                                                                                                                                              
		     * Returns the sum of the edge weights in a minimum spanning tree (or forest).                                                                                                   
		     * @return the sum of the edge weights in a minimum spanning tree (or forest)                                                                                                    
		     */
		    public double weight() {
			double weight = 0.0;
			for (Edge e : edges())
			    weight += e.weight();
			return weight;
		    }


		    // check optimality conditions (takes time proportional to E V lg* V)                                                                                                            
		    private boolean check(EdgeWeightedGraph G) {

			// check weight                                                                                                                                                              
			double totalWeight = 0.0;
			for (Edge e : edges()) {
			    totalWeight += e.weight();
			}
			if (Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
			    System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", totalWeight, weight());
			    return false;
			}

			// check that it is acyclic                                                                                                                                                  
			UF uf = new UF(G.V());
			for (Edge e : edges()) {
			    int v = e.either(), w = e.other(v);
			    if (uf.find(v) == uf.find(w)) {
				System.err.println("Not a forest");
				return false;
			    }
			    uf.union(v, w);
			}

			// check that it is a spanning forest                                                                                                                                        
			for (Edge e : G.edges()) {
			    int v = e.either(), w = e.other(v);
			    if (uf.find(v) != uf.find(w)) {
				System.err.println("Not a spanning forest");
				return false;
			    }
			}

			// check that it is a minimal spanning forest (cut optimality conditions)                                                                                                    
			for (Edge e : edges()) {

			    // all edges in MST except e                                                                                                                                             
			    uf = new UF(G.V());
			    for (Edge f : edges()) {
				int x = f.either(), y = f.other(x);
				if (f != e) uf.union(x, y);
			    }
			    // check that e is min weight edge in crossing cut                                                                                                                       
			    for (Edge f : G.edges()) {
				int x = f.either(), y = f.other(x);
				if (uf.find(x) != uf.find(y)) {
				    if (f.weight() < e.weight()) {
					System.err.println("Edge " + f + " violates cut optimality conditions");
					return false;
				    }
				}
			    }

			}

			return true;
		    }
		    /**                                                                                                                                                                              
		     * Unit tests the {@code PrimMST} data type.                                                                                                                                     
		     *                                                                                                                                                                               
		     * @param args the command-line arguments                                                                                                                                        
		     */
		    public static void main(String[] args) {
			In in = new In(args[0]);
			EdgeWeightedGraph G = new EdgeWeightedGraph(in);
			PrimMST mst = new PrimMST(G);
			for (Edge e : mst.edges()) {
			    StdOut.println(e);
			}
			StdOut.printf("%.5f\n", mst.weight());
		    }


		}
		/*My code starts here**********************************/
		PrimMST prims = new PrimMST(EWG1);
		KruskalMST kruskals = new KruskalMST(EWG1);
		
		for (Edge e1 : prims.edges()) {
		    StdOut.println(e1);
		}
		System.out.println("\n");
		for (Edge e2 : kruskals.edges()) {
		    StdOut.println(e2);
		}
		
		
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