package sasanit2d;

import static com.oracle.webservices.internal.api.databinding.DatabindingModeFeature.ID;
import java.util.InputMismatchException;
import java.util.Scanner;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

public class Prescription {
    
    public static void main (String args[]){
       
        

import java.util.Scanner;

public class Prescription {
    Scanner scan = new Scanner(System.in);
    Config conf = new Config() {};
    
    public void appointmentConfig(){
        int option;
        do {
            try {
                System.out.println("\n\t--- Appointments Menu ---\n");
                System.out.println("1. Schedule Appointment");
                System.out.println("2. View Appointments");
                System.out.println("3. Edit Appointment");
                System.out.println("4. Delete Appointment");
                System.out.println("5. Exit");
                
                System.out.print("\nChoose an option: ");
                option = scan.nextInt();
                scan.nextLine(); 

                switch (option) {
                    case 1:
                        System.out.println("\n   --- ADDING NEW APPOINTMENT ---\n");
                        scheduleAppointment();
                        break;
                    case 2:
                        System.out.println("\n\t\t\t\t\t\t\t\t   --- APPOINTMENTS LIST ---");
                        String query = "SELECT * FROM appointments";
                        viewAppointments(query);
                        break;
                    case 3:
                        System.out.println("\n   --- EDITING AN APPOINTMENT ---\n");
                        editAppointment();
                        break;
                    case 4:
                        System.out.println("\n   --- DELETING AN APPOINTMENT ---\n");
                        deleteAppointment();
                        break;
                    case 5:
                        System.out.println("Exiting Appointments Menu..");
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scan.nextLine();
                option = -1;
            }
        } while (option != 5);
    }

    public void scheduleAppointment() {
        System.out.println("Enter Appointment Details:");
        
        int p_id;
        do{
            System.out.print("\nPresciption ID: ");
            p_id = scan.nextInt();
            if(!conf.doesIDExist("Prescription", p_id)){
                System.out.println("Prescription ID Doesn't Exist.");
            }
        }while(!conf.doesIDExist("Prescription", p_id));
        scan.nextLine();
        
        System.out.print("Prescription ID: ");
        String type = scan.nextLine();
        
        System.out.print("Medication Name: ");
        String doc = scan.nextLine();
        
        System.out.print("Prescription Date: ");
        String date = scan.nextLine();
        
        System.out.print("Appointment Status: ");
        String stats = scan.nextLine();
        
        String sql = "INSERT INTO prescription (Prescription_id, prescription_date , p_medication, a_date, a_status) VALUES (?, ?, ?, ?, ?)";       
        conf.addRecord(sql, p_id, type, doc, date, stats);
    }

    public void viewAppointments(String query) {
         
        String[] Headers = {"ID", "Prescription_ID", "Prescription_date","Medication_date","Dosage"};
        String[] Columns = {""ID", "Prescription_ID", "Prescription_date" , "Medication_date" ,"Dosage"};
        
        conf.viewRecords(query, Headers, Columns);
    }

    public void editAppointment() {
        int id;        
        boolean idExists;
        do{
            System.out.print("Student ID you want to delete: ");
            id = scan.nextInt();
            
            idExists = conf.doesIDExist("students", id);
            if(!idExists){
                System.out.println("Student ID Doesn't Exist.\n");
            }
        }while(!idExists);
        
        String query = "SELECT * FROM appointments WHERE id = " + id;
        viewAppointments(query);
        
        System.out.println("Enter Appointment Details:");
        
        int p_id;
        do{
            System.out.print("\nNew Prescription_ID: ");
            p_id = scan.nextInt();
            if(!conf.doesIDExist("prescription", p_id)){
                System.out.println("Prescription_ID Doesn't Exist.");
            }
        }while(!conf.doesIDExist("Prescription", p_id));
        scan.nextLine();
        
        System.out.print("Prescription_id: ");
        String type = scan.nextLine();
        
        System.out.print("Prescription Name: ");
        String doc = scan.nextLine();
        
        System.out.print("medication name: ");
        String date = scan.nextLine();
        
        System.out.print("dosage Status: ");
        String stats = scan.nextLine();
        
        String sql = "UPDATE appointments SET patient_id = ?, Prescription_id = ?, prescription_date = ?, medication_name = ?, dosage = ? WHERE id = ?";       
        conf.updateRecord(sql, p_id, type, doc, date, stats, id); 
    }

    public void deleteAppointment() {
        System.out.print("Enter ID you want to delete: ");
        int id = scan.nextInt();
        
        String sql = "DELETE FROM appointments WHERE id = ?";
        conf.deleteRecord(sql, id);
    }
}        
        
    
    }
    
