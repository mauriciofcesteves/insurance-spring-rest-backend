package com.study.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "name")
    private String name;

    @Column(name = "securecompany")
    private String securecompany;

    @Column(name = "licenseplate")
    private String licenseplate;

    @Column(name = "initial_term")
    private LocalDate initialTerm;

    @Column(name = "final_term")
    private LocalDate finalTerm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public Customer cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Customer cnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public Customer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecurecompany() {
        return securecompany;
    }

    public Customer securecompany(String securecompany) {
        this.securecompany = securecompany;
        return this;
    }

    public void setSecurecompany(String securecompany) {
        this.securecompany = securecompany;
    }

    public String getLicenseplate() {
        return licenseplate;
    }

    public Customer licenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
        return this;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public LocalDate getInitialTerm() {
        return initialTerm;
    }

    public Customer initialTerm(LocalDate initialTerm) {
        this.initialTerm = initialTerm;
        return this;
    }

    public void setInitialTerm(LocalDate initialTerm) {
        this.initialTerm = initialTerm;
    }

    public LocalDate getFinalTerm() {
        return finalTerm;
    }

    public Customer finalTerm(LocalDate finalTerm) {
        this.finalTerm = finalTerm;
        return this;
    }

    public void setFinalTerm(LocalDate finalTerm) {
        this.finalTerm = finalTerm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        if(customer.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + id +
            ", cpf='" + cpf + "'" +
            ", cnpj='" + cnpj + "'" +
            ", name='" + name + "'" +
            ", securecompany='" + securecompany + "'" +
            ", licenseplate='" + licenseplate + "'" +
            ", initialTerm='" + initialTerm + "'" +
            ", finalTerm='" + finalTerm + "'" +
            '}';
    }

}