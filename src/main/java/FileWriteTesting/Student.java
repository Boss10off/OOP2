package FileWriteTesting;

import java.io.*;

public class Student {

    String name;
    int matriculationNumber;

    Student(String name, int matriculationNumber) {
        this.name = name;
        this.matriculationNumber = matriculationNumber;
    }

    void readFromFile(String fileName) {
        DataInputStream in = null;
        try{
            in = new DataInputStream(new FileInputStream(fileName));
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
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new FileOutputStream(fileName));
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
        Student s = new Student("Mariane", 1);
        s.writeToFile("student.txt");
        Student t = new Student("John Doe", 2);
        t.readFromFile("student.txt");
        System.out.println(s.toString() +", \n"+ t.toString());
    }
}
