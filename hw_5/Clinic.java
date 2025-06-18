import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Clinic {
    private File patientFile;
    private int day;

    public Clinic(File file) {
      this.patientFile = file;
      this.day = 1;
    }

    public Clinic(String fileName) {
      this(new File(fileName));
    }

    public String nextDay(File f) throws FileNotFoundException {
      Scanner fileScan = null;
      String[] tokens = null;
      String output = "";
      try {
        fileScan = new Scanner(f);
        String line = null;
        while (fileScan.hasNextLine()) {
          line = fileScan.nextLine();
          tokens = line.split(",");

          System.out.println("Consultation for " + tokens[0] + " the " + tokens[1] + " at " + tokens[3] + ".");
          System.out.println("What is the health of " + tokens[0] + "?");

          if (!tokens[1].equals("Dog") && !tokens[1].equals("Cat")) {
            throw new InvalidPetException();
          }

          double health = 0.0;
          while (true) {
            try {
              Scanner inputScanner = new Scanner(System.in);
              health = inputScanner.nextDouble();
              break;
            } catch (Exception e) {
              System.out.println("Invalid input. Please enter a double for the animal's health.");
            }
          }

          System.out.println("On a scale of 1 to 10, how much pain is " + tokens[0] + " in right now?");

          int painLevel = 0;
          while (true) {
            try {
              Scanner inputScanner = new Scanner(System.in);
              painLevel = inputScanner.nextInt();
              break;
            } catch (Exception e) {
              System.out.println("Invalid input. Please enter an integer between 1 and 10 for the animal's pain level.");
            }
          }

          String timeIn = tokens[3];
          String timeOut = "";
          String newLine = "";

          if (tokens[1].equals("Dog")) {
            Dog dog = new Dog(tokens[0], health, painLevel, Double.parseDouble(tokens[2]));
            dog.speak();
            int healTime = dog.treat();
            timeOut = addTime(timeIn, healTime);
            newLine = String.format("%s,%s,%.2f,%s,%s,%s,%.2f,%d", tokens[0], tokens[1], dog.getDroolRate(), "Day " + Integer.toString(day), timeIn, timeOut, health, painLevel);
          } else if (tokens[1].equals("Cat")) {
            Cat cat = new Cat(tokens[0], health, painLevel, Integer.parseInt(tokens[2]));
            cat.speak();
            int healTime = cat.treat();
            timeOut = addTime(timeIn, healTime);
            newLine = String.format("%s,%s,%d,%s,%s,%s,%.2f,%d", tokens[0], tokens[1], cat.getMiceCaught(), "Day " + Integer.toString(day), timeIn, timeOut, health, painLevel);
          }

          output += newLine + "\n";
          day += 1;
        }
      } catch (FileNotFoundException e) {
        System.out.println("File not found: " + patientFile.getName());
        throw e;
      } catch (InvalidPetException e) {
        System.out.println(e.getMessage());
      } finally {
        if (fileScan != null) {
          fileScan.close();
        }
      }

      return output;
    }

    public String nextDay(String fileName) throws FileNotFoundException {
      return nextDay(new File(fileName));
    }

    public boolean addToFile(String patientInfo) {
      Scanner fileScan = null;
      PrintWriter filePrint = null;
      String fileString = "";


      try  {
       fileScan = new Scanner(patientFile);
       boolean newPatient = true;
       String[] infoArray = patientInfo.split(",");
       String patientName = infoArray[0];

      while (fileScan.hasNextLine()) {
        String scannedLine = fileScan.nextLine();
        if (scannedLine.startsWith(patientName)) {
          System.out.println("Patient " + patientName + " already exists in the file.");
          newPatient = false;
          String[] lineParts = scannedLine.split(",");
          String[] newLineParts = new String[lineParts.length + 5];
          for (int i = 0; i < lineParts.length; i++) {
            newLineParts[i] = lineParts[i];
          }
          newLineParts[newLineParts.length - 5] = infoArray[3]; // Update the day
          newLineParts[newLineParts.length - 4] = infoArray[4]; // Update the time in
          newLineParts[newLineParts.length - 3] = infoArray[5]; // Update the time ou
          newLineParts[newLineParts.length - 2] = infoArray[6]; // Update the health
          newLineParts[newLineParts.length - 1] = infoArray[7]; // Update the pain level
          scannedLine = String.join(",", newLineParts);
        }
        fileString += scannedLine + "\n";
      }
      if (newPatient) {
        fileString += patientInfo + "\n";
      }
      fileScan.close();
      filePrint = new PrintWriter(patientFile);
      filePrint.print(fileString);
      return true;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        return false;
      } finally {
        if (fileScan != null) {
            fileScan.close();
        }
        if (filePrint != null) {
            filePrint.close();
        }
      }
    }

    private String addTime(String timein, int treatmentTime) {
      int hour = Integer.parseInt(timein.substring(0, 2));
      int minute = Integer.parseInt(timein.substring(2, 4));

      minute += treatmentTime;
      if (minute >= 60) {
        hour += minute / 60;
        minute = minute % 60;
      }
      if (hour >= 24) {
        hour = hour % 24;
      }

      return String.format("%02d%02d", hour, minute);
    }

}