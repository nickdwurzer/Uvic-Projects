//import jdk.javadoc.internal.doclets.formats.html.markup.Table;

public class Hashtable{
    
    private static final int TABLE_SZ = 7; // a prime number
    
    Student[] table;
    int count;  // number of Students in the table
    
    public Hashtable() {
        table = new Student[TABLE_SZ];
        count = 0;
    }
    

    
    /* MethodName: insertCollisions
     * Purpose: insert s into this Hashtable with no collision handling strategy
     * Parameters: Student - s
     * Throws:  TableFullException - if inserting into a full table
     *          CollisionException - if inserting a new key into table at index that is full
     * Returns: nothing
     */
    public void insertCollisions(Student s) throws TableFullException, CollisionException{
        if(count == TABLE_SZ){
            throw new TableFullException();
        }
        else if(table[s.hashCode() % TABLE_SZ] != null){
            if(table[s.hashCode() % TABLE_SZ].getSID() == s.getSID()){
                table[s.hashCode() % TABLE_SZ].setGrade(s.getGrade());
            }
            else{
                throw new CollisionException();
            }
        }
        else{
            table[s.hashCode() % TABLE_SZ] = s;
            count++;
        }
    }
    
    /* MethodName: getCollisions
     * Purpose: find Student with sid in this Hashtable with no collision handling and returns their grade
     * Parameters: String - sid
     * Throws:  KeyNotFoundException  - if Student with sid is not found in table
     * Returns: int - the grade of Student with sid
     */
    public int getCollisions(String sid) throws KeyNotFoundException{
        if(table[sid.hashCode() % 7].getSID() == sid){
            return table[sid.hashCode() % 7].getGrade();
        }
        else{
            throw new KeyNotFoundException();
        }
    }
    
    /* MethodName: insertLinearProbing
     * Purpose: insert s into this Hashtable with Linear Probing to handle collisions
     * Parameters: Student - s
     * Throws: TableFullException  - if inserting into a full table
     * Returns: nothing
     */
    public void insertLinearProbing(Student s) throws TableFullException {
        if(count == TABLE_SZ){
            throw new TableFullException();
        }
        else{
            int index = s.hashCode() % TABLE_SZ;
            for(int i = 0; i < TABLE_SZ; i++){
                if(table[(index + i) % TABLE_SZ] == null){
                    table[(index + i) % TABLE_SZ] = s;
                    i = TABLE_SZ;
                    count++;
                }
                else if(table[(index + i) % TABLE_SZ].getSID() == s.getSID()){
                    table[(index + i) % TABLE_SZ].setGrade(s.getGrade());
                    i = TABLE_SZ;
                }
            }
        }
    }
    
    /* getLinearProbing
     * Purpose: find Student with sid in this Hashtable that uses Linear Probing and returns their grade
     * Parameters: String - sid
     * Throws:  KeyNotFoundException  - if Student with sid is not found in table
     * Returns: int - the grade of Student with sid
     */
    public int getLinearProbing(String sid) throws KeyNotFoundException{
        int index = sid.hashCode() % TABLE_SZ;
        for(int i = 0; i < TABLE_SZ; i ++){
            if(table[(index + i) % TABLE_SZ] != null){
                if(table[(index + i) % TABLE_SZ].getSID() == sid){
                    return table[(index + i) % TABLE_SZ].getGrade();
                }
            }
        }
        throw new KeyNotFoundException();
    }
    
    
    
    
    /*
     * Purpose: returns the number of elements in this Hashtable
     * Parameters: none
     * Returns: int - the number of elements
     */
    public int size() {
        return count;
    }
    
    /*
     * Purpose: returns a String reprensentation of elements
     *      in this Hashtable separated by newlines
     * Parameters: none
     * Returns: String - the representation
     */
    public String toString() {
        String s = "";
        
        for(int i=0; i<TABLE_SZ; i++)
            if (table[i] != null)
                s  += table[i] + "\n";
        
        return s;
    }
    

}
