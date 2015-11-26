package com.fabricesalmon.adet;

public class ListViewItem {
    private int mi_Color;
    private String ms_Pseudo;

    public String getPseudo() {
        return ms_Pseudo;
    }

    public int getColor() {
        return mi_Color;
    }

    public ListViewItem(int li_Color, String ls_Pseudo) {
        this.mi_Color = li_Color;
        this.ms_Pseudo = ls_Pseudo;
    }
}
