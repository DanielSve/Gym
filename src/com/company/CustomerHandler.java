package com.company;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class CustomerHandler {

    private ArrayList<Customer> list = new ArrayList<Customer>();
    private boolean isActiveMember = false;
    private String message;
    private boolean running = true;
    Customer customer = new Customer();

    public void mainTest(){
        list = convertTextToList("src/customers.txt");
        userInput();
    }

    public ArrayList<Customer> convertTextToList (String file){
        String tempString;
        try (BufferedReader bufIn = new BufferedReader(new FileReader(file))){
            for (int i = 0; ((tempString = bufIn.readLine())) != null && !tempString.isBlank(); i++ ) {
            list.add(new Customer());
            list.get(i).setPersNr(tempString.substring(0, tempString.indexOf(',')));
            list.get(i).setName(tempString.substring(tempString.indexOf(',') + 2));
            list.get(i).setMembershipDate(bufIn.readLine());
        }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean isOnList(String nameOrPersNr, ArrayList<Customer> list){
        this.list = list;
        for (Customer c : list) {
            if (nameOrPersNr!=null && (nameOrPersNr.equalsIgnoreCase(c.getName())
                    || nameOrPersNr.equalsIgnoreCase(c.getPersNr()))) {
                customer = c;
                return true;
            }
        }
        return false;
    }

    public String checkMembershipStatus(String nameOrPersNr, ArrayList<Customer> list) {
        if(isOnList(nameOrPersNr, list)) {
            if (customer.hasActiveMembership(customer)){
                isActiveMember = true;
                return "Giltigt medlemskap! Ditt pass registreras";
            } else if (!customer.hasActiveMembership(customer)) {
                return "Ditt medlemskap har gått ut!";
            }
        }
        return "Du är inte medlem, vänligen lämna gymmet.";
    }

    public void userInput() {
        try {
            String nameOrPersNr = "";
            while (running) {
                isActiveMember = false;
                nameOrPersNr = JOptionPane.showInputDialog("Skriv namn eller personnummer: ");
                message = checkMembershipStatus(nameOrPersNr, list);
                JOptionPane.showMessageDialog(null, message);
                if (isActiveMember) {
                    customer.createOrUpdateWorkoutFile(customer);
                }
                int knappnr = JOptionPane.showConfirmDialog(null, "Ny besökare?");
                if (knappnr != JOptionPane.YES_OPTION) {
                    running = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
