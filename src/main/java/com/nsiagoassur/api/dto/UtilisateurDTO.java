package com.nsiagoassur.api.dto;

public class UtilisateurDTO {
    private Integer id;
    private String login;
    private String nom;
    private String prenoms;
    private String libelle;
    
    // d'autres champs si nécessaire, mais pas le mot de passe

    // Constructeur
    public UtilisateurDTO(Integer id, String login, String nom, String prenoms,String libelle) {
        this.id = id;
        this.setLogin(login);
        this.setNom(nom);
        this.setPrenoms(prenoms);
        this.setLibelle(libelle);
       
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }
    

    public void setId(Integer id) {
        this.id = id;
    }

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenoms() {
		return prenoms;
	}

	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


 
}
