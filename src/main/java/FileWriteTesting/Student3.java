package FileWriteTesting;

import java.io.*;

public class Student3 implements Serializable{

    String name;
    int matriculationNumber;

    long serialVersionUID = 125;

    Student3(String name, int matriculationNumber) {
        this.name = name;
        this.matriculationNumber = matriculationNumber;
    }

    void readFromFile(String fileName) {
        ObjectInputStream in = null;
        try{
            in = new ObjectInputStream(new FileInputStream(fileName));
            Student3 s = (Student3) in.readObject();
            this.name = s.name;
            this.matriculationNumber = s.matriculationNumber;
            this.serialVersionUID = s.serialVersionUID;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void writeToFile(String fileName) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(this);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
            out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String toString(){
        return name + " " + matriculationNumber + " " + serialVersionUID;
    }

    public static void main(String[] args) {
        Student3 s = new Student3("Mariane", 1);
//        s.writeToFile("student3.txt");
        Student3 t = new Student3("John Doe", 2);
        t.readFromFile("student3.txt");
        System.out.println(s.toString() +", \n"+ t.toString());
    }
}
