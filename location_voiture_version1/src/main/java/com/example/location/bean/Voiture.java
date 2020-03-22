package com.example.location.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javafx.scene.control.Button;




/**
 *
 * @author Abida Hassan
 */
@Entity
public class Voiture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30)
    private String libelle;
    private String image;
    @Column(length = 20)
    private String matricule;
    @Column(length = 4)
    private int quatite;
    @Column(length = 15)
    private String color;
    @Column(length = 4)
    private double coutParJour;
    @Column(columnDefinition = "TEXT")
    private String discreption;
    @ManyToOne
    private Agence agence;
    @ManyToOne
    private Marque marque;
    @ManyToOne
    private Categorie categorie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getQuatite() {
        return quatite;
    }

    public void setQuatite(int quatite) {
        this.quatite = quatite;
    }

    public double getCoutParJour() {
        return coutParJour;
    }

    public void setCoutParJour(double coutParJour) {
        this.coutParJour = coutParJour;
    }

    public String getDiscreption() {
        return discreption;
    }

    public void setDiscreption(String discreption) {
        this.discreption = discreption;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

  

    public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
	
    public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	public String getMarqueBrand() {
		return this.marque.getBrand();
	}
	public String getCategorieName() {
		return this.categorie.getName();
	}
	public Button getButtonDelete() {
		 Button deleteV = new Button("delete");
         deleteV.setStyle("-fx-background-color: transparent;-fx-text-fill:red");
         return deleteV;
	}
	public Button getButtonEdit() {
		 Button editV = new Button("edit");
        editV.setStyle("-fx-background-color: transparent;-fx-text-fill:blue");
        return editV;
	}
	
	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voiture)) {
            return false;
        }
        Voiture other = (Voiture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


	



	

	@Override
	public String toString() {
		return "Voiture [id=" + id + ", libelle=" + libelle + ", image=" + image + ", matricule=" + matricule
				+ ", quatite=" + quatite + ", color=" + color + ", coutParJour=" + coutParJour + ", discreption="
				+ discreption + ", agence=" + agence + ", marque=" + marque + ", categorie=" + categorie + "]";
	}

	public Voiture(Long id, String libelle, String image, String matricule, int quatite, String color,
			double coutParJour, String discreption, Agence agence, Marque marque, Categorie categorie) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.image = image;
		this.matricule = matricule;
		this.quatite = quatite;
		this.color = color;
		this.coutParJour = coutParJour;
		this.discreption = discreption;
		this.agence = agence;
		this.marque = marque;
		this.categorie = categorie;
	}

	public Voiture() {
    }

}
