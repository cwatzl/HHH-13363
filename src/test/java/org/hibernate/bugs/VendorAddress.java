package org.hibernate.bugs;

import javax.persistence.*;

@Entity
@Table(name = "vendor_address")
public class VendorAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "city")
    private String city;
    @Column(name = "post_code")
    private String postCode;
    @Column(name = "street")
    private String street;
    @Column(name = "street_number")
    private String streetNumber;

    private VendorAddress() { }

    public VendorAddress(String city, String postCode, String street, String streetNumber) {
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }
}
