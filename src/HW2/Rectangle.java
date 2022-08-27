package HW2;

public class Rectangle implements RectangleInterface, Comparable<Rectangle> {

    private double width;
    private double length;

    public Rectangle(double w, double l){
        width = w;
        length = l;
    }

    @Override
    public double getLength(){
        return length;
    }

    @Override
    public double getWidth(){
        return width;
    }

    public double getPerimeter(){
        return (2*width)+(2*length);
    }

    @Override
    public int compareTo(Rectangle to){
        return Double.compare(this.getPerimeter(), to.getPerimeter());
    }

    @Override
    public String toString() {
        return "{" +
                 + (int)width +
                "x" + (int)length +
                '}';
    }
}
