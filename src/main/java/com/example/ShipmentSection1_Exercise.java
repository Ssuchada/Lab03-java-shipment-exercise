package com.example;

import java.util.ArrayList;
import java.util.List;

enum ShipmentType {
    EXPRESS,
    STANDARD
    // เพิ่ม STANDARD ตรงนี้
}

// ──────────────────────────────────────────────────────────
//  PART B : Class Shipment — ข้อมูลพัสดุแต่ละรายการ
// ──────────────────────────────────────────────────────────
class Shipment {

    private String       trackingNumber;
    private double       weightKg;
    private ShipmentType type;

    public Shipment(String trackingNumber, double weightKg,ShipmentType type) {
        this.trackingNumber = trackingNumber;
        this.weightKg       = weightKg;
        this.type           = type;
    }

    public String       getTrackingNumber() { return trackingNumber; }
    public double       getWeightKg()       { return weightKg;       }
    public ShipmentType getType()           { return type;           }

    public double calculateCost() {
        final double STANDARD_RATE = 40.0;   // ← ผิด
        final double EXPRESS_RATE  =  100.0;   // ← ผิด
        if (type == ShipmentType.STANDARD) {
            return weightKg * STANDARD_RATE;
        } else {
            return weightKg * EXPRESS_RATE;
        }
    }


@Override
public String toString() {
    return String.format("[%s] %5.2f กก. | %-9s | %9.2f บาท",
            trackingNumber,
            weightKg,
            type,
            calculateCost());
}
}

// ──────────────────────────────────────────────────────────
//  PART C : Class ShippingCompany — บริษัทขนส่ง
// ──────────────────────────────────────────────────────────
class ShippingCompany {

    private String         name;
    private List<Shipment> shipments;

    public ShippingCompany(String name) {
        this.name = name;
        // เพิ่มบรรทัด initialize ตรงนี้
        shipments = new ArrayList<>();
    }

    public void addShipment(Shipment s) {
        shipments.add(s);
    }


    public double getTotalCost() {
        double total = 0;
        // วนลูปรวม cost ของแต่ละ shipment ตรงนี้
        for (Shipment s:shipments){
            total += s.calculateCost();
        }
        return total;
    }

    public void printSummary() {
        System.out.println("========================================");
        System.out.printf ("  บริษัท        : %s%n",   name);
        System.out.printf ("  จำนวน Shipment : %d รายการ%n", shipments.size());
        System.out.println("========================================");

        // 1) วนลูปแสดงแต่ละ shipment ตรงนี้
        for (Shipment s:shipments){
            System.out.println(s);
        }

        System.out.println("----------------------------------------");
        // 2) แสดงยอดรวมตรงนี้
System.out.printf("  ยอดรวมทั้งหมด : %,.2f บาท%n", getTotalCost());
System.out.println("========================================");
    }
}

// ──────────────────────────────────────────────────────────
//  PART D : Main
// ──────────────────────────────────────────────────────────
public class ShipmentSection1_Exercise {
    public static void main(String[] args) {

        ShippingCompany company = new ShippingCompany("SpeedEx Logistics");

        // (trackingNumber, weightKg, type)
        company.addShipment(new Shipment("TH001",  3.0,  ShipmentType.STANDARD));
        company.addShipment(new Shipment("TH002",  1.5,  ShipmentType.EXPRESS));
        company.addShipment(new Shipment("TH003",  5.0,  ShipmentType.STANDARD));
        company.addShipment(new Shipment("TH004",  2.0,  ShipmentType.EXPRESS));
        company.addShipment(new Shipment("TH005", 10.0,  ShipmentType.STANDARD));

        company.printSummary();
    }
}
