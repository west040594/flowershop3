package com.accenture.be.entity.address;

import com.accenture.be.entity.user.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_address")
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "public_email", length = 100, nullable = false)
    private String publicEmail;

    @Column(name = "phone_number", length = 30, nullable = false)
    private String phoneNumber;

    @Column(name = "street", length = 100, nullable = false)
    private String street;

    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @Column(name = "country", length = 50, nullable = false)
    private String country;

    @OneToOne(mappedBy = "address")
    private User user;

    public Address() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id &&
                Objects.equals(user, address.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", publicEmail='" + publicEmail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", user=" + user +
                '}';
    }

}
