/*
 * Lab 3: Summarize digraph --> get DAG
 *
 * no need to import other classes
 *
 * Compile : javac *.java
 * Run : java SummarizedGraph tinyDG.txt
 *
 */
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Topological;
import edu.princeton.cs.algs4.KosarajuSharirSCC;
import edu.princeton.cs.algs4.In;
import java.util.HashSet;
import java.io.FileWriter;
import java.io.IOException;
/*
 * Name:Nick Wurzer 
 * VNum:V00958568
 */
public class SummarizedGraph{
    private final Digraph G; // input graph
    private final KosarajuSharirSCC scc; // strongly connected components of G
    private final String filename; // input filename of G
    private final int n; // # vertices
    private int m; // # edges
    //----------------------------------------------------------
    // UNCOMMENT when BuildSG is implenented
    
    private final Digraph DAG; // summarized graph
    private Topological top; //object for topological sorting
    
    //----------------------------------------------------------
    /**
     * Python style print function, here if you want it..
     */
    private static <S> void print(S s){
	System.out.println(s);
    }
    /**
     * Constructor.
     * Do NOT modify parameters for this or any method!
     */
    public SummarizedGraph(Digraph G, String filename){
	this.G = G; // saves graph to object
	this.scc = new KosarajuSharirSCC(G); // creates and saves scc
	this.filename = "summarized_"+filename; // name of summary graph file
	this.n = scc.count(); //number nodes in summary graph

	// ----- add to this method -----
	BuildSG();
	//-------------------------------
	
	// !!!!!!!uncomment once BuildSG implenented!!!!!!!!
	
	this.DAG = new Digraph(new In(this.filename));
	this.top = new Topological(DAG);
	
	
	// ----- add to this method -----
	printTopOrder(); // prints the topological sorted order
	//-------------------------------
    }
    public void printTopOrder(){
	// FORMAT example:
	// "Topological sorted order of summary graph: 4 3 2 1 0 "
	// ADD YOUR code here
	Iterable<Integer> i = this.top.order();
	for(Integer v: i){
		print(v);
	}
	
	
    }
    /**
     * DO NOT MOD EXISTING CODE.
     */
    private void BuildSG(){
	// find the edges in SG
	// output the edgelist to a txt file s.t.
	// In can interpret the Digraph
	// print graph to file
	try {
	    FileWriter writer = new FileWriter(filename);
	    HashSet<Pair> edge_list = new HashSet<Pair>(); // Pair is a homebrew class for edges thats comparable, not in algs4
	    //--------
	    // loop over edges in G and find the edges in the summarized graph
	    // ADD YOUR code here
	    for(int v = 0; v < G.V(); v++){
	        for(Integer u: G.adj(v)){
			int c_v = scc.id(v);
			int c_u = scc.id(u);
			Pair edge = new Pair(c_v,c_u);
			if(c_v != c_u){
				edge_list.add(edge);
			}
		}
	    }
	    
	    //--------
	    // DO NOT MOD: saves the graph to file
	    this.m = edge_list.size();
	    writer.write(n+"\n");
	    writer.write(m+"\n");
	    for(Pair edge: edge_list)
		writer.write(edge.i+" "+edge.j+"\n");
	    writer.close();
	}
	catch (IOException e ){
	    e.printStackTrace();
	}
    }
    /**
     * Main Method: Constructs SummarizedGraph Object and times run.
     */
    public static void main(String [] args){
	long time = System.currentTimeMillis();
	if(args.length < 1){
	    print("include graph filename");
	    System.exit(1);
	}
	In in = new In(args[0]); // this object reads and interprets the graph .txt files
	String filename = args[0];
	Digraph G = new Digraph(in); // object to hold the digraph read from In.
	SummarizedGraph SG = new SummarizedGraph(G, filename); // object instance of our class
	print("Time elapsed: "+(System.currentTimeMillis()-time)/1000.0+ " seconds");
    }
}
