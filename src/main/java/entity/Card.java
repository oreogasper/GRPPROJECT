package entity;

import java.awt.Image;

public class Card implements AbstractCard{
    private int rank;
    private String suit;
    private String name;
    private Image img;

    public Card(int rank, String suit, String name, Image img) {
        this.rank = rank;
        this.suit = suit;
        this.name = name;
        this.img = img;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSuit() {
        return suit;
    }

    @Override
    public Image getImage() {
        return img;
    }
}
