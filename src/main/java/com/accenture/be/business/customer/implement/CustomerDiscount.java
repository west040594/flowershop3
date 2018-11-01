package com.accenture.be.business.customer.implement;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customer-discount")
public class CustomerDiscount {

    @XmlElement(name = "customer-id")
    long customerId ;
    @XmlElement(name = "new-discount")
    int newDiscount;

    public CustomerDiscount() {
    }

    public CustomerDiscount(long customerId, int newDiscount) {
        this.customerId = customerId;
        this.newDiscount = newDiscount;
    }

    public long getCustomerId() {
        return customerId;
    }

    public int getNewDiscount() {
        return newDiscount;
    }
}
