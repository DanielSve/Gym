package com.company;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void hasActiveMembership() {
        Customer customer1 = new Customer();
        customer1.setName("Pelle Andersson");
        customer1.setMembershipDate("" + java.time.LocalDate.now().minusYears(1).minusMonths(1));
        assertFalse(customer1.hasActiveMembership(customer1));

        Customer customer2 = new Customer();
        customer2.setName("Lina Lind");
        customer2.setMembershipDate("" + java.time.LocalDate.now().minusMonths(10).minusDays(20));
        assertTrue(customer2.hasActiveMembership(customer2));
    }

    @Test
    void createOrUpdateWorkoutFile(){
        Customer customer = new Customer();
        customer.setName("Daniel Svensson");
        customer.setPersNr("8410265550");
        customer.createOrUpdateWorkoutFile(customer);

        String textInFile = "";
        String tempLine = "";
        try (BufferedReader bufIn = new BufferedReader(new FileReader("Daniel_Svensson.txt"));){
            while((tempLine = bufIn.readLine()) != null){
                textInFile = textInFile + tempLine + "\n";
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        assertTrue(textInFile.equals("Daniel Svensson 8410265550\nLista över träningstillfällen\n" + java.time.LocalDate.now() + "\n"));

        customer.createOrUpdateWorkoutFile(customer);
        textInFile = "";

        try (BufferedReader bufIn = new BufferedReader(new FileReader("Daniel_Svensson.txt"));){
            while((tempLine = bufIn.readLine()) != null){
                textInFile = textInFile + tempLine + "\n";
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        assertTrue(textInFile.equals("Daniel Svensson 8410265550\nLista över träningstillfällen\n" + java.time.LocalDate.now() + "\n" +
                java.time.LocalDate.now() + "\n"));

        try {
            Files.delete(Path.of("Daniel_Svensson.txt"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateHasFile() {
        Customer customer = new Customer();
        customer.setName("Johan Johansson");
        customer.setPersNr("8510265550");
        customer.setMembershipDate("" + java.time.LocalDate.now().minusMonths(4).minusDays(20));
        assertFalse(customer.getHasFile());

        customer.createOrUpdateWorkoutFile(customer);
        customer.updateHasFile(customer);
        assertTrue(customer.getHasFile());

        try {
            Files.delete(Path.of("Johan_Johansson.txt"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void printToFile() {
        Customer customer1 = new Customer();
        customer1.setPersNr("841026-5550");
        customer1.setName("Daniel Svensson");
        customer1.setHasFile(false);
        assertTrue(customer1.printToFile(customer1).equals("Daniel Svensson 841026-5550\nLista över träningstillfällen\n"
                +java.time.LocalDate.now() + "\n"));

        customer1.setHasFile(true);
        assertTrue(customer1.printToFile(customer1).equals("" + java.time.LocalDate.now() + "\n"));

        Customer customer2 = new Customer();
        customer2.setPersNr("851203-5555");
        customer2.setName("Stina Andersson");
        assertTrue(customer2.printToFile(customer2).equals("Stina Andersson 851203-5555\nLista över träningstillfällen\n"
                +java.time.LocalDate.now() +"\n"));

        customer2.setHasFile(true);
        assertFalse(customer2.printToFile(customer2).equals("Stina Andersson 851203-5555\nLista över träningstillfällen\n"
                +java.time.LocalDate.now() +"\n"));
    }

    @Test
    void setPersNr() {

    }

    @Test
    void setName() {
    }

    @Test
    void setMembershipDate() {
    }

    @Test
    void getPersNr() {
    }

    @Test
    void getName() {
    }

    @Test
    void getMembershipDate() {
    }

    @Test
    void testToString() {
    }
}