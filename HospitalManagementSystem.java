import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalManagementSystem {
    private List<Person> persons = new ArrayList<>();

    public void addDoctor(Doctor doctor) {
        persons.add(doctor);
    }

    public void registerPatient(Patient patient) {
        persons.add(patient);
    }

    public void displayAllDoctorsAndPatients() {
        for (Person person : persons) {
            person.displayInfo();
            System.out.println("------------------------");
        }
    }

    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(persons);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            persons = (List<Person>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException("Failed to load data from file: " + filename, e);
        }
    }

    public static void main(String[] args) {
        HospitalManagementSystem hospital = new HospitalManagementSystem();

        // Adding a doctor
        Doctor doctor1 = new Doctor("Uwera", "claire", 45, "Male", "D001", "Cardiology");
        hospital.addDoctor(doctor1);

        // Registering a patient
        Patient patient1 = new Patient("Manzi", "Prince", 32, "Male", "P001", "Flu", "2023-04-01");
        hospital.registerPatient(patient1);

        // Display all doctors and patients
        System.out.println("List of All Doctors and Patients:");
        hospital.displayAllDoctorsAndPatients();

        try {
            // Save to file
            hospital.saveToFile("hospital_data.dat");

            // Clear the list for demonstration purposes
            hospital.persons.clear();

            // Load from file
            hospital.loadFromFile("hospital_data.dat");

            // Display all doctors and patients loaded from the file
            System.out.println("\nList of All Doctors and Patients (Loaded from File):");
            hospital.displayAllDoctorsAndPatients();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

abstract class Person implements Serializable {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;

    public Person(String firstName, String lastName, int age, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    // Abstract method to display information
    public abstract void displayInfo();
}

class Doctor extends Person implements Serializable {
    private String doctorID;
    private String specialization;

    public Doctor(String firstName, String lastName, int age, String gender, String doctorID, String specialization) {
        super(firstName, lastName, age, gender);
        this.doctorID = doctorID;
        this.specialization = specialization;
    }

    // Getters and Setters
    public String getDoctorID() { return doctorID; }
    public void setDoctorID(String doctorID) { this.doctorID = doctorID; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    @Override
    public void displayInfo() {
        System.out.println("Doctor ID: " + doctorID);
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Age: " + getAge());
        System.out.println("Gender: " + getGender());
        System.out.println("Specialization: " + specialization);
    }
}

class Patient extends Person implements Serializable {
    private String patientID;
    private String diagnosis;
    private String admittedDate;

    public Patient(String firstName, String lastName, int age, String gender, String patientID, String diagnosis, String admittedDate) {
        super(firstName, lastName, age, gender);
        this.patientID = patientID;
        this.diagnosis = diagnosis;
        this.admittedDate = admittedDate;
    }

    // Getters and Setters
    public String getPatientID() { return patientID; }
    public void setPatientID(String patientID) { this.patientID = patientID; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getAdmittedDate() { return admittedDate; }
    public void setAdmittedDate(String admittedDate) { this.admittedDate = admittedDate; }

    @Override
    public void displayInfo() {
        System.out.println("Patient ID: " + patientID);
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Age: " + getAge());
        System.out.println("Gender: " + getGender());
        System.out.println("Diagnosis: " + diagnosis);
        System.out.println("Admitted Date: " + admittedDate);
    }
}
