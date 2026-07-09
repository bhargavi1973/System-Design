interface Shapes {
    void area();
    void volume();
}
// Square, Rectangle are 2D shapes , so they throw an exception for volume method
// and we have to write extra code for that, which is not a good design
// This violates Interface Seggregation Principle (ISP)
class Square implements Shapes {
    @Override
    public void area(){}

    @Override
    public void volume(){
        throw new UnsupportedOperationException();
    }
}

class Rectangle implements Shapes {
    @Override
    public void area(){}

    @Override
    public void volume(){
        throw new UnsupportedOperationException();
    }
}
// Cube is 3D shape, so it defines both area and volume methods
class Cube implements Shapes {
    @Override
    public void area(){}

    @Override
    public void volume(){}
}
public class before {
    public static void main(String[] args){
        Shapes s1 = new Square();
        Shapes s2 = new Cube();

        s1.area();
        s1.volume(); // throws Runtime Error

        s2.area();
        s2.volume();
    }
}
