package FileWriteTesting;

import java.io.FileReader;
import java.io.FileWriter;

public class Students {

    String name;
    int matriculationNumber;

    Students(String name, int matriculationNumber) {
        this.name = name;
        this.matriculationNumber = matriculationNumber;
    }

    void readFromFile(String fileName) {
        FileReader fr = null;
        try{
            fr = new FileReader(fileName);
            fr.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void writeToFile(String fileName) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName);
            fw.write(name + matriculationNumber + "\n");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String toString(){
        return name + " " + matriculationNumber;
    }

    public static void main(String[] args) {
        Students s = new Students("Mariane", 1);
        s.writeToFile("students.txt");
        Students t = new Students("John Doe", 2);
        t.readFromFile("students.txt");
        System.out.println(s.toString() +", \n"+ t.toString());
    }
}
