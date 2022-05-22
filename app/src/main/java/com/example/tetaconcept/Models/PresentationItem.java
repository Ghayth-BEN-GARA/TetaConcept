package com.example.tetaconcept.Models;

public class PresentationItem {
    private int image;
    private String titre,description;

    public PresentationItem(int image, String titre, String description) {
        this.image = image;
        this.titre = titre;
        this.description = description;
    }

    public PresentationItem() {
        super();
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
