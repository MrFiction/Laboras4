/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import laborai.studijosktu.KTUable;

/**
 *
 * @author Laptopas
 */
public class Kazkas implements KTUable, Comparable<Kazkas> {

    final static private LocalDate curentYearLocalVar = LocalDate.now();
    private double price;
    private LocalDate manifacturingDate;
    private String model;
    private int serialNumber;

    public Kazkas() {
    }

    public Kazkas(double price, LocalDate manifacuturingDate, String model, int serialNumber) {
        this.price = price;
        this.manifacturingDate = manifacuturingDate;
        this.model = model;
        this.serialNumber = serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getManifacuturingDate() {
        return manifacturingDate;
    }

    public void setManifacuturingDate(LocalDate manifacuturingDate) {
        this.manifacturingDate = manifacuturingDate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "Model = " + model + ", serialNumber = " + serialNumber +", Price = " + String.format("%.2f", price) + ", Manifacturing date = " + manifacturingDate ;
    }

    @Override
    public KTUable create(String dataString) {
        Kazkas tempObj = new Kazkas();
        tempObj.parse(dataString);
        return tempObj;
    }

    @Override
    public String validate() {
        if (manifacturingDate.compareTo(curentYearLocalVar.minusYears(10)) < 0 || manifacturingDate.compareTo(curentYearLocalVar) > 0) {
            return "netinkama data";
        }
        return "";
    }

    @Override
    public void parse(String dataString) {
        try {
            Scanner data = new Scanner(dataString);
            price = data.nextDouble();
            manifacturingDate = LocalDate.parse(data.next());
            model = data.next();
            serialNumber = data.nextInt();

        } catch (InputMismatchException e) {
            System.out.print("Netinkamas formatas");

        } catch (NoSuchElementException e) {
            System.out.print("Nuztenka duomenu");

        }
    }

    @Override
    public int compareTo(Kazkas e) {
        if (e == null) {
            return 1;
        }
        if (serialNumber > e.serialNumber) {
            return 1;
        }
        if (serialNumber < e.serialNumber) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kazkas other = (Kazkas) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (this.serialNumber != other.serialNumber) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.manifacturingDate, other.manifacturingDate)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 79 * hash + Objects.hashCode(this.manifacturingDate);
        hash = 79 * hash + Objects.hashCode(this.model);
        hash = 79 * hash + this.serialNumber;
        return hash;
    }

    public final static Comparator byManufacturingDate = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Kazkas a1 = (Kazkas) o1;
            Kazkas a2 = (Kazkas) o2;
            int compare = a1.getManifacuturingDate().compareTo(a2.getManifacuturingDate());
            return compare;
        }
    };

    public final static Comparator byPrice = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Kazkas a1 = (Kazkas) o1;
            Kazkas a2 = (Kazkas) o2;
            return Double.compare(a1.getPrice(), a2.getPrice());

        }
    };

}
