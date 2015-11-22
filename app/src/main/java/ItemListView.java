package com.fabricesalmon.adet;

public class ItemListView {
    private int color;
    private String pseudo;

    public String getPseudo() {
        return pseudo;
    }
    public int getColor() {
        return color;
    }

    public ItemListView(int color, String pseudo) {
        this.color = color;
        this.pseudo = pseudo;
    }
}
