package com.lewap02.learning.model.dao;

import com.lewap02.learning.util.annotations.Getter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empid", insertable = true, updatable = false, nullable = false)
    private Integer empId;

    @Column(name = "empname", insertable = true, updatable = true)
    private String empName;

    public Employee () {

    }

    public Employee (String empName) {
        this.empName = empName;
    }

    @Getter(fieldName = "empId")
    public Integer getEmpId() {
        return this.empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    @Getter(fieldName = "empName")
    public String getEmpName() {
        return this.empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

}
