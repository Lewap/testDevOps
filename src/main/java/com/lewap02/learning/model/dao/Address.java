package com.lewap02.learning.model.dao;

import com.lewap02.learning.util.annotations.Getter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adrId", insertable = true, updatable = false, nullable = false)
    private Integer adrId;

    @Column(name = "street", insertable = true, updatable = true)
    private String street;

    @Column(name = "houseNumber", insertable = true, updatable = true)
    private String houseNumber;

    @Column(name = "city", insertable = true, updatable = true)
    private String city;

    public Address () {

    }

    public Address (String street, String houseNumber, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;

    }

    @Getter(fieldName = "adrId")
    public Integer getAdrId() {
        return this.adrId;
    }

    @Getter(fieldName = "street")
    public String getStreet() {
        return this.street;
    }

    @Getter(fieldName = "houseNumber")
    public String getHouseNumber() {
        return this.houseNumber;
    }

    @Getter(fieldName = "city")
    public String getCity() {
        return this.city;
    }

    public void setAdrId(Integer adrId) {
        this.adrId = adrId;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
    public void setCity(String city) {
        this.city = city;
    }

}
