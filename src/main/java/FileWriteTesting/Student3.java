package FileWriteTesting;

import java.io.*;

public class Student3 {

    String name;
    int matriculationNumber;

    Student3(String name, int matriculationNumber) {
        this.name = name;
        this.matriculationNumber = matriculationNumber;
    }

    void readFromFile(String fileName) {
        ObjectInputStream in = null;
        try{
            in = new ObjectInputStream(new FileInputStream(fileName));
            name = in.readUTF();
            matriculationNumber = in.readInt();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void writeToFile(String fileName) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeUTF(this.name);
            out.writeInt(this.matriculationNumber);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
            out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String toString(){
        return name + " " + matriculationNumber;
    }

    public static void main(String[] args) {
        Student3 s = new Student3("Mariane", 1);
        s.writeToFile("student3.txt");
        Student3 t = new Student3("John Doe", 2);
//        t.readFromFile("student3.txt");
        System.out.println(s.toString() +", \n"+ t.toString());
    }
}
