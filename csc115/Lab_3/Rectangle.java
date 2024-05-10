package Lab_3;

public class Rectangle implements Shape{
    private int length;
    private int width;
    private Point position;

    public Rectangle(){
        this.length = 0;
        this.width = 0;
        position = new Point();
    }
    public Rectangle(int width, int length){
        this.length = length;
        this.width = width;
        position = new Point();
    }
    public Rectangle(int width, int length, Point position){
        this.length = length;
        this.width = width;
        this.position = position;
    }
    public double area(){
        return  width * length;
    }
    public double perimeter(){
        return 2 * length + 2 * width;
    }
    public boolean contains(Point p){
        if((p.getX() >= position.getX() && p.getX() <= (position.getX() + width)) 
        && (p.getY() >= position.getY() && p.getY() <= (position.getY() + length))){
            return true;
        }
        return false;
    }
    public String toString(){
        return "Rectangle of dimensions: " + width +" by " + length + " at Point: " + position;
    }
}