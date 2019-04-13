package org.hibernate.bugs;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Embedded
    private VendorInfo vendorInfo;

    private Product() { }

    public Product(String name, VendorInfo vendorInfo) {
        this.name = name;
        this.vendorInfo = vendorInfo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public VendorInfo getVendorInfo() {
        return vendorInfo;
    }

    @Embeddable
    public static class VendorInfo {
        @ManyToOne
        private Vendor vendor;
        @Column(name = "vendor_relationship_description")
        private String vendorRelationshipDescription;

        private VendorInfo() {}

        public VendorInfo(Vendor vendor, String vendorRelationshipDescription) {
            this.vendor = vendor;
            this.vendorRelationshipDescription = vendorRelationshipDescription;
        }

        public Vendor getVendor() {
            return vendor;
        }
    }
}
