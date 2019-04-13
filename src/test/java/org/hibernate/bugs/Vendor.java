package org.hibernate.bugs;

import javax.persistence.*;

@Entity
@Table(name = "vendor")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private VendorAddress address;

    private Vendor() { }

    public Vendor(String name, VendorAddress address) {
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public VendorAddress getAddress() {
        return address;
    }
}
