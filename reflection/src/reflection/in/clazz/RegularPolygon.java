package reflection.in.clazz;

public class RegularPolygon extends Point implements TwoDimensional {

    private int numOfVertices;
    private double side;
    
    public RegularPolygon(double x, double y, int numOfVertices, double side) {
        super(x, y);
        this.numOfVertices = numOfVertices;
        this.side = side;
    }

    @Override
    public double area() {
        double perimeter = side * numOfVertices;
        double apothem = side / 2 / Math.tan(Math.PI / numOfVertices);
        return apothem * perimeter / 2;
    }

    public int getNumOfVertices() {
        return numOfVertices;
    }

    public void setNumOfVertices(int numOfVertices) {
        this.numOfVertices = numOfVertices;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + numOfVertices;
        long temp;
        temp = Double.doubleToLongBits(side);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        RegularPolygon other = (RegularPolygon) obj;
        if (numOfVertices != other.numOfVertices)
            return false;
        if (Double.doubleToLongBits(side) != Double.doubleToLongBits(other.side))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RegularPolygon [numOfVertices=").append(numOfVertices).append(", side=").append(side)
                .append("]");
        return builder.toString();
    }
    
    
}
