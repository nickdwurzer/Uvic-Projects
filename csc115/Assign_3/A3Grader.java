package Assign_3;
/*
 * A3Grader
 *
 * A class to test the methods required for Assignment 3
 *
 */
public class A3Grader {
    
	private static int testPassCount = 0;
	private static int testCount = 0;

    public static void main(String[] args) {
		
		// Uncomment each method one at a time, 
		// and implement the method in A3LinkedList.java 
		// until all tests pass. It is strongly
		// recommended that you add additional tests 
		// to ensure the correctness of your implementation.
		
		testAddFront();
		testAddBack();
		testAddBoth();
		testRemoveFront();
		testRemoveBack();
		testSizeAndIsEmpty();
		testRotate();
		testInterleave();
		
		System.out.println("Passed " + testPassCount + " / " + testCount + " tests");
    }
	
		
	public static void testAddFront() {
		String result = "";
		A3LinkedList list1 = null;
		try{
			list1 = new A3LinkedList();
		}catch(Exception e){
			System.out.println("Error with constructor, list1 testAddFront" + e);
		}
		try{
			list1.addFront("S");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addFront("C");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addFront("I");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addFront("E");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addFront("N");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addFront("C");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addFront("E");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			result = list1.frontToBack();
			displayResults(result.equals("{ECNEICS}"), "testAddFront1");
		}catch(Exception e){
			System.out.println("display result" + e);
			displayResults(false, "testAddfront2");
		}try{
			result = list1.backToFront();
			displayResults(result.equals("{SCIENCE}"), "testAddFront2");
		}catch(Exception e){
			System.out.println("display result" + e);
			displayResults(false, "testAddfront2");
		}
	}
	
	public static void testAddBack() {
		String result = "";
		A3LinkedList list1 = null;
		
		try{
			list1= new A3LinkedList();
		}catch(Exception e){
			System.out.println("Error with constructor" + e);
		}try{
			list1.addBack("L");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addBack("I");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addBack("N");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addBack("K");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addBack("E");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addBack("D");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			result = list1.frontToBack();
			displayResults(result.equals("{LINKED}"), "testAddBack1");
		}catch(Exception e){
			displayResults(false, "testAddBack1");
			System.out.println("front to back" + e);
		}try{
			result = list1.backToFront();
			displayResults(result.equals("{DEKNIL}"), "testAddBack2");
		}catch(Exception e){
			displayResults(false, "testAddBack2");
			System.out.println("front to back" + e);
		}
	}
	
	public static void testAddBoth() {
		String result = "";
		A3LinkedList list1 = null;
		 
		try{
			list1 = new A3LinkedList();
		}catch(Exception e){
			System.out.println("Error with constructor" + e);
		}
		try{
			list1.addBack("K");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addBack("I");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addBack("N");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addBack("G");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addFront("R");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addFront("O");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addFront("W");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}
		
		try{
			result = list1.frontToBack();
			displayResults(result.equals("{WORKING}"), "testAddBoth1");
		}catch(Exception e){
			displayResults(false, "testAddBoth");
			System.out.println("front to back" + e);
		}try{
			result = list1.backToFront();
			displayResults(result.equals("{GNIKROW}"), "testAddBoth2");
		}catch(Exception e){
			displayResults(false, "testAddBoth");
			System.out.println("front to back" + e);
		}
		
		A3LinkedList list2 = null;
		try{
			list2= new A3LinkedList();
		}catch(Exception e){
			System.out.println("Error with constructor" + e);
		}try{
			list2.addFront("S");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list2.addFront("E");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list2.addFront("T");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{		
			list2.addBack("T");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}
		try{
			list2.addBack("I");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}
		try{
			list2.addBack("N");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list2.addBack("G");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			result = list2.frontToBack();
			displayResults(result.equals("{TESTING}"), "testAddBoth3");
		}catch(Exception e){
			displayResults(false, "testAddBoth");
			System.out.println("front to back" + e);
		}try{
			result = list2.backToFront();
			displayResults(result.equals("{GNITSET}"), "testAddBoth4");
		}catch(Exception e){
			displayResults(false, "testAddBoth");
			System.out.println("front to back" + e);
		}
	}
		
	public static void testRemoveFront() {
		String result = "";
		A3LinkedList list1 = null;
		try{
			list1 = new A3LinkedList();
		}catch(Exception e){
			System.out.println("Error with constructor" + e);
		}
		try{
			list1.addFront("A");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addFront("B");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addFront("C");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.removeFront();
			result = list1.frontToBack();
			displayResults(result.equals("{BA}"), "testRemoveFront1");
		}catch(Exception e){
			displayResults(false, "testRemoveFront");
			System.out.println("testRemoveFront" + e);
		}try{
			result = list1.backToFront();
			displayResults(result.equals("{AB}"), "testRemoveFront2");
		}catch(Exception e){
			displayResults(false, "testRemoveFront");
			System.out.println("testRemoveFront" + e);
		}try{
			list1.removeFront();
		}catch(Exception e){
			System.out.println("error with remove");
		}try{
			list1.removeFront();
		}catch(Exception e){
			System.out.println("error with remove");
		}try{	
			result = list1.frontToBack();
			displayResults(result.equals("{}"), "testRemoveFront3");
		}catch(Exception e){
			displayResults(false, "testRemoveFront");
			System.out.println("testRemoveFront" + e);
		}try{
			result = list1.backToFront();
			displayResults(result.equals("{}"), "testRemoveFront4");
		}catch(Exception e){
			displayResults(false, "testRemoveFront");
			System.out.println("testRemoveFront" + e);
		}
		try{
			list1.addBack("D");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addBack("E");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.addBack("F");
		}catch(Exception e){
			System.out.println("error with add" + e);
		}try{
			list1.removeFront();
			result = list1.frontToBack();
			displayResults(result.equals("{EF}"), "testRemoveFront5");
		}catch(Exception e){
			displayResults(false, "testRemoveFront5");
			System.out.println("testRemoveFront5" + e);
		}try{
			result = list1.backToFront();
			displayResults(result.equals("{FE}"), "testRemoveFront6");
		}catch(Exception e){
			displayResults(false, "testRemoveFront6");
			System.out.println("testRemoveFront6" + e);
		}
		
		try{
			list1.removeFront();
		}catch(Exception e){
			System.out.println("error with remove");
		}try{
			list1.removeFront();
		}catch(Exception e){
			System.out.println("error with remove");
		}try{
			list1.addFront("G");
		}catch(Exception e){
			System.out.println("error with add");
		}try{
			list1.addBack("H");
		}catch(Exception e){
			System.out.println("error with add");
		}try{
			list1.addFront("I");
		}catch(Exception e){
			System.out.println("error with add");
		}try{
			list1.addBack("J");
		}catch(Exception e){
			System.out.println("error with add");
		}try{
			list1.removeFront();
		}catch(Exception e){
			System.out.println("error with add");
		}try{
			result = list1.frontToBack();
			displayResults(result.equals("{GHJ}"), "testRemoveFront7");
		}catch(Exception e){
			displayResults(false, "testRemoveFront7");
		}try{
			result = list1.backToFront();
			displayResults(result.equals("{JHG}"), "testRemoveFront8");
		}catch(Exception e){
			displayResults(false, "testRemoveFront8");
		}
	}
		
	public static void testRemoveBack() {
		String result = "";
		A3LinkedList list1 = null;
		try{
			list1 = new A3LinkedList();
		}catch(Exception e){
			System.out.println(e);
		}
		try{
			list1.addFront("A");
		}catch(Exception e){
			System.out.println("error with add");
		}try{
			list1.addFront("B");
		}catch(Exception e){
			System.out.println("error with add");
		}try{
			list1.addFront("C");
		}catch(Exception e){
			System.out.println("error with add");
		}try{
			list1.removeBack();
			result = list1.frontToBack();
			displayResults(result.equals("{CB}"), "testRemoveBack");
		}catch(Exception e){
			displayResults(false, "testRemoveBack");
		}try{
			result = list1.backToFront();
			displayResults(result.equals("{BC}"), "testRemoveBack2");
		}catch(Exception e){
			displayResults(false, "testRemoveBack");
		}

		try{
			list1.removeBack();
		}catch(Exception e){
			System.out.println("error with remove");
		}try{	
			list1.removeBack();
		}catch(Exception e){
			System.out.println("error with remove");
		}try{
			result = list1.frontToBack();
			displayResults(result.equals("{}"), "testRemoveBack3");
		}catch(Exception e){
			displayResults(false, "testRemoveBack3");
		}try{
			result = list1.backToFront();
			displayResults(result.equals("{}"), "testRemoveBack4");
		}catch(Exception e){
			displayResults(false, "testRemoveBack3");
		}
		
		try{
			list1.addBack("D");
		}catch(Exception e){
			System.out.println("error with add");
		}try{
			list1.addBack("E");
		}catch(Exception e){
			System.out.println("error with add");
		}try{
			list1.addBack("F");
		}catch(Exception e){
			System.out.println("error with add");
		}try{
			list1.removeBack();
		}catch(Exception e){
			System.out.println("error with remove");
		}try{
			result = list1.frontToBack();
			displayResults(result.equals("{DE}"), "testRemoveBack5");
		}catch(Exception e){
			displayResults(false, "testRemoveBack5");
		}try{
			result = list1.backToFront();
			displayResults(result.equals("{ED}"), "testRemoveBack6");
		}catch(Exception e){
			displayResults(false, "testRemoveBack6");
		}
		
		try{	
			list1.removeBack();
		}catch(Exception e){
			System.out.println("error with remove");
		}try{
			list1.removeBack();
		}catch(Exception e){
			System.out.println("error with remove");
		}try{
			list1.addFront("G");
		}catch(Exception e){
			System.out.println("error with add");
		}try{
			list1.addBack("H");
		}catch(Exception e){
			System.out.println("error with add");
		}try{
			list1.addFront("I");
		}catch(Exception e){
			System.out.println("error with add");
		}try{
			list1.addBack("J");
		}catch(Exception e){
			System.out.println("error with add");
		}try{
			list1.removeBack();
		}catch(Exception e){
			System.out.println("error with remove");
		}try{
			result = list1.frontToBack();
			displayResults(result.equals("{IGH}"), "testRemoveBack7");
		}catch(Exception e){
			displayResults(false, "testRemoveBack7");
		}try{
			result = list1.backToFront();
			displayResults(result.equals("{HGI}"), "testRemoveBack8");
		}catch(Exception e){
			displayResults(false, "testRemoveBack8");
		}
		
	}
	
	public static void testSizeAndIsEmpty() {
		int result = 0;
		A3LinkedList list1 = null;
		try{
			list1 = new A3LinkedList();
		}catch(Exception e){
			System.out.println(e);
		}

		try{
			result = list1.size();
			displayResults(result==0, "testSizeAndIsEmpty1");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty1");
		}try{
			displayResults(list1.isEmpty()==true, "testSizeAndIsEmpty2");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty2");
		}
		
		try{
			list1.addFront("C");
		}catch(Exception e){
			System.out.println("error with add");
		}try{
			result = list1.size();
			displayResults(result==1, "testSizeAndIsEmpty4");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty4");
		}try{
			displayResults(list1.isEmpty()==false, "testSizeAndIsEmpty4");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty4");
		}try{
			list1.removeFront();
			result = list1.size();
			displayResults(result==0, "testSizeAndIsEmpty5");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty5");
		}try{
			displayResults(list1.isEmpty()==true, "testSizeAndIsEmpty6");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty6");
		}try{
			list1 = new A3LinkedList();
		}catch(Exception e){
			System.out.println("Error with new list construtor");
		}
		try{
			list1.addFront("S");
		}catch(Exception e){
			System.out.println("error with add");
		}try{
			result = list1.size();
			displayResults(result==1, "testSizeAndIsEmpty7");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty7");
		}try{
			displayResults(list1.isEmpty()==false, "testSizeAndIsEmpty8");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty8");
		}try{
			list1.removeBack();
		}catch(Exception e){
			System.out.println("error remove back");
		}try{
			result = list1.size();
			displayResults(result==0, "testSizeAndIsEmpty9");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty9");
		}try{
			displayResults(list1.isEmpty()==true, "testSizeAndIsEmpty10");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty10");
		}
		
		try{
			list1 = new A3LinkedList();
		}catch(Exception e){
			System.out.println("Error with constructor");
		}
		try{
			list1.addBack("U");
		}catch(Exception e){
			System.out.println("Error with add");
		}try{
			result = list1.size();
			displayResults(result==1, "testSizeAndIsEmpty11");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty11");
		}try{
			displayResults(list1.isEmpty()==false, "testSizeAndIsEmpty12");
		}catch(Exception e){
			displayResults(false, "testSizeAndISEmpty12");
		}
		try{
			list1.removeFront();
		}catch(Exception e){
			System.out.println("Error with remove front");
		}
		try{
			result = list1.size();
			displayResults(result==0, "testSizeAndIsEmpty13");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty13");
		}try{
			displayResults(list1.isEmpty()==true, "testSizeAndIsEmpty14");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty14");
		}
		
		try{
			list1 = new A3LinkedList();
		}catch(Exception e){
			System.out.println("error with constructor");
		}try{
			list1.addBack("S");
		}catch(Exception e){
			System.out.println("Error with remove");
		}
		try{
			result = list1.size();
			displayResults(result==1, "testSizeAndIsEmpty15");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty15");
		}
		try{
			displayResults(list1.isEmpty()==false, "testSizeAndIsEmpty16");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty16");
		}
		try{
			list1.removeBack();
			result = list1.size();
			displayResults(result==0, "testSizeAndIsEmpty17");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty17");
		}try{
			displayResults(list1.isEmpty()==true, "testSizeAndIsEmpty18");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty18");
		}
		
		try{
			list1 = new A3LinkedList();
		}catch(Exception e){
			System.out.println("Error with constructor");
		}
		try{
			list1.addBack("A");
			list1.addFront("B");
			list1.addFront("C");
			list1.addFront("D");
			list1.addBack("E");
		}catch(Exception e){
			System.out.println("Error with add");
		}
		try{
			result = list1.size();
			displayResults(result==5, "testSizeAndIsEmpty");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty");
		}
		try{
			list1.removeBack();
			list1.removeBack();
			list1.removeBack();
			list1.removeFront();
		}catch(Exception e){
			System.out.println("Error with remove");
		}
		try{
			result = list1.size();
			displayResults(result==1, "testSizeAndIsEmpty");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty");
		}try{
			displayResults(list1.isEmpty()==false, "testSizeAndIsEmpty11");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty11");
		}
		
		try{
			list1.removeBack();
		}catch(Exception e){
			System.out.println("Error with remove");
		}try{
			result = list1.size();
			displayResults(result==0, "testSizeAndIsEmpty");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty");
		}
		try{
			displayResults(list1.isEmpty()==true, "testSizeAndIsEmpty12");
		}catch(Exception e){
			displayResults(false, "testSizeAndIsEmpty12");
		}
	}
	
	public static void testRotate() {
		String result = "";
		A3LinkedList list1 = null;
		try{
			list1 = new A3LinkedList();
		}catch(Exception e){
			System.out.println("Error with construction");
		}
		try{
			list1.addBack("E");
			list1.addBack("F");
			list1.addBack("G");
			list1.addBack("H");
			list1.addBack("I");
			list1.addFront("D");
			list1.addFront("C");		
			list1.addFront("B");
			list1.addFront("A");
		}catch(Exception e){
			System.out.println("Error with Add");
		}
		
		try{
			list1.rotate(3);
			result = list1.frontToBack();
			displayResults(result.equals("{DEFGHIABC}"), "testRotate1");
		}catch(Exception e){
			displayResults(false, "testRotate1");
		}try{
			result = list1.backToFront();
			displayResults(result.equals("{CBAIHGFED}"), "testRotate2");
		}catch(Exception e){
			displayResults(false, "testRotate2");
		}
		
		try{
			list1.rotate(2);
			result = list1.frontToBack();
			displayResults(result.equals("{FGHIABCDE}"), "testRotate3");
		}catch(Exception e){
			displayResults(false, "testRotate3");
		}
		try{
			result = list1.backToFront();
			displayResults(result.equals("{EDCBAIHGF}"), "testRotate4");	
		}catch(Exception e){
			displayResults(false, "testRorate4");
		}

		A3LinkedList list2 = null;
		try{
			list2 = new A3LinkedList();
		}catch(Exception e){
			System.out.println("error with constructor");
		}
		
		try{
			list2.addBack("E");
			list2.addBack("F");
			list2.addBack("G");
			list2.addBack("H");
			list2.addBack("I");
			list2.addFront("D");
			list2.addFront("C");		
			list2.addFront("B");
			list2.addFront("A");
		}catch(Exception e){
			System.out.println("error with add");
		}
		
		try{
			list2.rotate(30);
			result = list2.frontToBack();
			displayResults(result.equals("{DEFGHIABC}"), "testRotate5");
		}catch(Exception e){
			displayResults(false, "testRotate5");
		}try{
			result = list2.backToFront();
			displayResults(result.equals("{CBAIHGFED}"), "testRotate6");
		}catch(Exception e){
			displayResults(false, "testRotate6");
		}	
	}

	public static void testInterleave() {
		A3LinkedList list1 = null;
		A3LinkedList list2 = null;
		try{
			list1 = new A3LinkedList();
			list2 = new A3LinkedList();
		}catch(Exception e){
			System.out.println("error with constructor");
		}
		String result1 = "";
		String result2 = "";
		
		try{
			list1.addBack("A");
			list1.addBack("B");
			list1.addBack("C");
			list1.addBack("D");
			
			list2.addBack("W");
			list2.addBack("X");
			list2.addBack("Y");
			list2.addBack("Z");
		}catch(Exception e){
			System.out.println("Error with Add");
		}
		try{
			list1.interleave(list2);
			result1 = list1.frontToBack();
			result2 = list1.backToFront();
			displayResults(result1.equals("{AXCZ}"), "testInterleave1");
		}catch(Exception e){
			displayResults(false, "testInterleave1");
		}try{
			displayResults(result2.equals("{ZCXA}"), "testInterleave2");
		}catch(Exception e){
			displayResults(false, "testInterleave2");
		}
		
		try{
			result1 = list2.frontToBack();
			result2 = list2.backToFront();
			displayResults(result1.equals("{WBYD}"), "testInterleave3");
		}catch(Exception e){
			displayResults(false, "testinterleave3");
		}try{
			displayResults(result2.equals("{DYBW}"), "testInterleave4");
		}catch(Exception e){
			displayResults(false, "testInterleave4");
		}

		A3LinkedList list3 = null;
		A3LinkedList list4 =null;
		
		try{
			list3 = new A3LinkedList();
			list4 = new A3LinkedList();
		}catch(Exception e){
			System.out.println("Error with constructor");
		}try{
			list3.addBack("A");
			list3.addBack("B");
			list3.addBack("C");
			list3.addBack("D");
		
			list4.addBack("W");
			list4.addBack("X");
			list4.addBack("Y");
			list4.addBack("Z");
		}catch(Exception e){
			System.out.println("error with add");
		}
		try{
			list4.interleave(list3);
			result1 = list3.frontToBack();
			result2 = list3.backToFront();
			displayResults(result1.equals("{AXCZ}"), "testInterleave5");
		}catch(Exception e){
			displayResults(false, "testinterleave5");
		}
		try{
			displayResults(result2.equals("{ZCXA}"), "testInterleave6");
		}catch(Exception e){
			displayResults(false, "testInterleave6");
		}
		
		try{
			result1 = list4.frontToBack();
			result2 = list4.backToFront();
			displayResults(result1.equals("{WBYD}"), "testInterleave7");
		}catch(Exception e){
			displayResults(false, "testInterleave7");
		}
		try{
			displayResults(result2.equals("{DYBW}"), "testInterleave8");
		}catch(Exception e){
			displayResults(false, "testInterleave8");
		}
		
		
		A3LinkedList list5 = null; 
		A3LinkedList list6 = null; 
		
		try{
			list5 = new A3LinkedList();
			list6 = new A3LinkedList();
		}catch(Exception e){
			System.out.println("Error with constructor");
		}
		try{	
			list5.addBack("A");
			list5.addBack("B");
			list5.addBack("C");
			list5.addBack("D");
			list5.addBack("E");
			
			list6.addBack("V");
			list6.addBack("W");
			list6.addBack("X");
			list6.addBack("Y");
			list6.addBack("Z");
		}catch(Exception e){
			System.out.println("Error with Add");
		}
		try{
			list5.interleave(list6);
			result1 = list5.frontToBack();
			result2 = list5.backToFront();
			displayResults(result1.equals("{AWCYE}"), "testInterleave9");
		}catch(Exception e){
			displayResults(false, "testInterleave9");
		}try{
			displayResults(result2.equals("{EYCWA}"), "testInterleave10");
		}catch(Exception e){
			displayResults(false, "testInterleave10");
		}
		try{
			result1 = list6.frontToBack();
			result2 = list6.backToFront();
			displayResults(result1.equals("{VBXDZ}"), "testInterleave11");
		}catch(Exception e){
			displayResults(false, "testInterleave11");
		}
		try{
			displayResults(result2.equals("{ZDXBV}"), "testInterleave12");
		}catch(Exception e){
			displayResults(false, "testInterleave12");
		}

		A3LinkedList list7 = null;

		A3LinkedList list8 = null;
		
		try{
			list7 = new A3LinkedList();
			list8 = new A3LinkedList();
		}catch(Exception e){
			System.out.println("Error with constructor");
		}try{

			list7.addBack("A");
			list7.addBack("B");
			list7.addBack("C");
			list7.addBack("D");
			list7.addBack("E");
			
			list8.addBack("V");
			list8.addBack("W");
			list8.addBack("X");
			list8.addBack("Y");
			list8.addBack("Z");
		}catch(Exception e){
			System.out.println("Error with Add");
		}
		
		try{
			list8.interleave(list7);
			result1 = list7.frontToBack();
			result2 = list7.backToFront();
			displayResults(result1.equals("{AWCYE}"), "testInterleave13");
		}catch(Exception e){
			displayResults(false, "testInterleave13");
		}
		try{
			displayResults(result2.equals("{EYCWA}"), "testInterleave14");
		}catch(Exception e){
			displayResults(false, "testInterleave14");
		}
		
		try{
			result1 = list8.frontToBack();
			result2 = list8.backToFront();
			displayResults(result1.equals("{VBXDZ}"), "testInterleave15");
		}catch(Exception e){
			displayResults(false, "testInterleave15");
		}try{
			displayResults(result2.equals("{ZDXBV}"), "testInterleave16");
		}catch(Exception e){
			displayResults(false, "testInterleave16");
		}
	}
	
	public static void displayResults (boolean passed, String testName) {
       /* There is some magic going on here getting the line number
        * Borrowed from:
        * http://blog.taragana.com/index.php/archive/core-java-how-to-get-java-source-code-line-number-file-name-in-code/
        */
        
        testCount++;
        if (passed)
        {
            System.out.println ("Passed test: " + testName);
            testPassCount++;
        }
        else
        {
            System.out.println ("Failed test: " + testName + " at line "
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
	
}