package Lab_3;

public class ShapeList {
    
    private static final int INIT_SZ = 2;
    
    Shape[] elements;
    int count;
    
    public ShapeList() {
        elements = new Shape[INIT_SZ];
    }
    
    /*
     * Purpose: returns the number of elements in list
     * Parameters: none
     * Returns: int - the number of elements
     */
    public int size() {
        count = 0;
        if( elements == null){
            return count;
        }
        for(int i = 0; i < elements.length; i++){
            if(elements[i] != null){
                count++;
            }
        }
        return count;
    }
    
    /*
     * Purpose: adds Shape s to back of this list
     * Parameters: Shape - s
     * Returns: nothing
     */
    public void add(Shape s) {
        if(this.size() < elements.length){
            elements[this.size()] = s;
        }
        else{
            incListLength();
            this.add(s);
        }
        
    }
    /*
     * Purpose: doubles the size of elements[] so that more shapes can be added
     * Parameters: none
     * Returns: none
     */
    public void incListLength(){
        Shape[] tempArray = new Shape[elements.length*2];
        for(int i = 0; i < elements.length; i++){
            tempArray[i] = elements[i];
        }
        elements = tempArray;
    }

    /*
     * Purpose: returns a String reprensentation of the elements
     *      in this list separated by newlines
     * Parameters: none
     * Returns: String - the representation
     */
    public String toString() {
        String result = "";
        for(int i = 0; i < this.size(); i++){
            if(elements[i] != null){
                result += elements[i].toString() + "\n";
            }
        }
        return result;
    }
    
    /*
     * Purpose: removes the first element in this list
     * Parameters: none
     * Returns: nothing
     */
    public void removeFront() {
        for(int i = 0; i < this.size()-1; i ++){
            elements[i] = elements[i+1];
        }
        elements[this.size()-1] = null;
    }
    
    
}