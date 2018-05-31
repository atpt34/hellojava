package inet.clone.example;

public class CloneExp implements Cloneable {
    
    private String name;
    private String address;
    private int age;
    private Department depart;
    public CloneExp(){
       
    }
    public CloneExp(String aName, int aAge, Department aDepart) {
   
      this.name = aName;
      this.age = aAge;
      this.depart = aDepart;
    }
   
    protected Object clone() throws CloneNotSupportedException {
   
      CloneExp clone=(CloneExp)super.clone();
   
      // make the shallow copy of the object of type Department
//      clone.depart=(Department)depart.clone();
      clone.depart = depart;
      return clone;
   
    }
    public static void main(String[] args) {
   
      CloneExp ce=new CloneExp();
   
      try {
        // make deep copy of the object of type CloneExp
        CloneExp cloned=(CloneExp)ce.clone();
      } catch (CloneNotSupportedException e) {
        e.printStackTrace();
      }
       
    }
  }
