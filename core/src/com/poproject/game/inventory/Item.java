package com.poproject.game.inventory;

public enum Item {
    W_MACE009("W_Mace009"),
    W_MACE010("W_Mace010"),
    W_MACE011("W_Mace011"),
    W_MACE012("W_Mace012"),
    W_MACE013("W_Mace013"),
    W_MACE014("W_Mace014"),
    W_SPEAR001("W_Spear001"),
    W_SPEAR002("W_Spear002"),
    W_SPEAR003("W_Spear003"),
    W_SPEAR004("W_Spear004"),
    W_SPEAR005("W_Spear005"),
    W_SPEAR006("W_Spear006"),
    W_SPEAR007("W_Spear007"),
    W_SPEAR008("W_Spear008"),
    W_SPEAR009("W_Spear009"),
    W_SPEAR010("W_Spear010"),
    W_SPEAR011("W_Spear011"),
    W_SPEAR012("W_Spear012"),
    W_SPEAR013("W_Spear013"),
    W_SPEAR014("W_Spear014"),
    W_SWORD001("W_Sword001"),
    W_SWORD002("W_Sword002"),
    W_SWORD003("W_Sword003"),
    W_SWORD004("W_Sword004"),
    W_SWORD005("W_Sword005"),
    W_SWORD006("W_Sword006"),
    W_SWORD007("W_Sword007"),
    W_SWORD008("W_Sword008"),
    W_SWORD009("W_Sword009"),
    W_SWORD010("W_Sword010"),
    W_SWORD011("W_Sword011"),
    W_SWORD012("W_Sword012"),
    W_SWORD013("W_Sword013"),
    W_SWORD014("W_Sword014"),
    W_SWORD015("W_Sword015");


    private Item(String textureRegion) {
        this.textureRegion = textureRegion;
    }

    private String textureRegion;

    public String getTextureRegion() {
        return textureRegion;
    }
}
