package com.example.location.bean;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Abida Hassan
 */
@Entity
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30)
    private String reference;
	private double totale;
        @ManyToOne
	private User client;
        @ManyToOne
	private Reservation reservation;
        @Temporal(TemporalType.TIMESTAMP)
	private Date facDate;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Date getFacDate() {
        return facDate;
    }

    public void setFacDate(Date facDate) {
        this.facDate = facDate;
    }

    public Facture(Long id, String reference, double totale, User client, Reservation reservation, Date facDate) {
        this.id = id;
        this.reference = reference;
        this.totale = totale;
        this.client = client;
        this.reservation = reservation;
        this.facDate = facDate;
    }

    public Facture() {
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facture)) {
            return false;
        }
        Facture other = (Facture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Facture{" + "id=" + id + ", reference=" + reference + ", totale=" + totale + ", client=" + client + ", reservation=" + reservation + ", facDate=" + facDate + '}';
    }

   
    
}
