package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class Customer {

    private String persNr;
    private String name;
    private String membershipDate;
    private String filename;
    private boolean hasFile = false;
    private Path workoutFilePath;

    public boolean hasActiveMembership(Customer c){
        LocalDate expiringDate = LocalDate.parse(membershipDate).plusYears(1);
        LocalDate currentDate = java.time.LocalDate.now();
        return (expiringDate.isAfter(currentDate));
    }

    public void createOrUpdateWorkoutFile(Customer customer) {
        filename = customer.getName() + ".txt";
        filename = filename.replace(" ", "_");
        workoutFilePath = Paths.get(filename);
        updateHasFile(customer);
        String toPrint = printToFile(customer);
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)))){
            writer.append(toPrint);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateHasFile(Customer customer){
        hasFile = (Files.exists(workoutFilePath));
    }

    public String printToFile(Customer customer){
        if(!hasFile){
            return customer.getName() + " " + customer.getPersNr() + "\nLista över träningstillfällen\n"
                    + java.time.LocalDate.now() + "\n";
        } else {
            return "" + java.time.LocalDate.now() + "\n";
        }
    }

    public void setHasFile(boolean b){
        hasFile = b;
    }

    public boolean getHasFile() {
        return hasFile;
    }

    public void setPersNr(String persNr) {
        this.persNr = persNr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembershipDate(String membershipDate) {
        this.membershipDate = membershipDate;
    }

    public String getPersNr() {
        return persNr;
    }

    public String getName() {
        return name;
    }

    public String getMembershipDate() {
        return membershipDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "persNr='" + persNr + '\'' +
                ", name='" + name + '\'' +
                ", membershipDate='" + membershipDate + '\'' +
                '}';
    }
}
