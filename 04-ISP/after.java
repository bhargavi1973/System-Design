// Here, the classes of 2D shapes cannot access the volume method
// but the classes of 3D shapes can access botj area() and volume() methods

interface Shape2D{
    void area();
}

interface Shape3D extends Shape2D{
    void volume();
}
class square implements Shape2D{
    @Override
    public void area(){}
}
class rectangle implements Shape2D{
    @Override 
    public void area(){}
}
class cube implements Shape3D{
    @Override
    public void area(){}

    @Override
    public void volume(){}
}
public class after {
    public static void main(String[] args){
        Shape2D s1 = new square();
        Shape3D s2 = new cube();

        s1.area();
        s1.volume();  // throws Compile time error

        s2.area();
        s2.volume();
    }
}
