package com.example.location.bean;



import java.io.Serializable;
import java.util.Date;
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
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateLoc;
    @Temporal(TemporalType.DATE)
    private Date dateRetour;
    @Temporal(TemporalType.DATE)
    private Date dateRetourEffec;
    @ManyToOne
    private Reservation reservation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateLoc() {
        return dateLoc;
    }

    public void setDateLoc(Date dateLoc) {
        this.dateLoc = dateLoc;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Date getDateRetourEffec() {
        return dateRetourEffec;
    }

    public void setDateRetourEffec(Date dateRetourEffec) {
        this.dateRetourEffec = dateRetourEffec;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Location() {
    }

    public Location(Long id, Date dateLoc, Date dateRetour, Date dateRetourEffec, Reservation reservation) {
        this.id = id;
        this.dateLoc = dateLoc;
        this.dateRetour = dateRetour;
        this.dateRetourEffec = dateRetourEffec;
        this.reservation = reservation;
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
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Location{" + "id=" + id + ", dateLoc=" + dateLoc + ", dateRetour=" + dateRetour + ", dateRetourEffec=" + dateRetourEffec + ", reservation=" + reservation + '}';
    }

   

}
