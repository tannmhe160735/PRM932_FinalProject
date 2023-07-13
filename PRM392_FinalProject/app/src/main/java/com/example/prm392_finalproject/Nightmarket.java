package com.example.prm392_finalproject;

import java.util.Date;

public class Nightmarket {
    private int Useid;
    private int gun1;
    private int gun2;
    private int gun3;
    private int gun4;
    private int gun5;
    private int gun6;
    private Date Date_start;
    private Date Date_end;
    private int Discount1;
    private int Discount2;
    private int Discount3;
    private int Discount4;
    private int Discount5;
    private int Discount6;
    private boolean IsOpen1;
    private boolean IsOpen2;
    private boolean IsOpen3;
    private boolean IsOpen4;
    private boolean IsOpen5;
    private boolean IsOpen6;

    public Nightmarket() {
    }

    public Nightmarket(int useid, int gun1, int gun2, int gun3, int gun4, int gun5, int gun6, Date date_start, Date date_end, int discount1, int discount2, int discount3, int discount4, int discount5, int discount6, boolean isOpen1, boolean isOpen2, boolean isOpen3, boolean isOpen4, boolean isOpen5, boolean isOpen6) {
        Useid = useid;
        this.gun1 = gun1;
        this.gun2 = gun2;
        this.gun3 = gun3;
        this.gun4 = gun4;
        this.gun5 = gun5;
        this.gun6 = gun6;
        Date_start = date_start;
        Date_end = date_end;
        Discount1 = discount1;
        Discount2 = discount2;
        Discount3 = discount3;
        Discount4 = discount4;
        Discount5 = discount5;
        Discount6 = discount6;
        IsOpen1 = isOpen1;
        IsOpen2 = isOpen2;
        IsOpen3 = isOpen3;
        IsOpen4 = isOpen4;
        IsOpen5 = isOpen5;
        IsOpen6 = isOpen6;
    }

    public Nightmarket(int useid, int gun1, int gun2, int gun3, int gun4, int gun5, int gun6, Date date_start, Date date_end, int discount1, int discount2, int discount3, int discount4, int discount5, int discount6) {
        Useid = useid;
        this.gun1 = gun1;
        this.gun2 = gun2;
        this.gun3 = gun3;
        this.gun4 = gun4;
        this.gun5 = gun5;
        this.gun6 = gun6;
        Date_start = date_start;
        Date_end = date_end;
        Discount1 = discount1;
        Discount2 = discount2;
        Discount3 = discount3;
        Discount4 = discount4;
        Discount5 = discount5;
        Discount6 = discount6;
    }

    public int getUseid() {
        return Useid;
    }

    public void setUseid(int useid) {
        Useid = useid;
    }

    public int getGun1() {
        return gun1;
    }

    public void setGun1(int gun1) {
        this.gun1 = gun1;
    }

    public int getGun2() {
        return gun2;
    }

    public void setGun2(int gun2) {
        this.gun2 = gun2;
    }

    public int getGun3() {
        return gun3;
    }

    public void setGun3(int gun3) {
        this.gun3 = gun3;
    }

    public int getGun4() {
        return gun4;
    }

    public void setGun4(int gun4) {
        this.gun4 = gun4;
    }

    public int getGun5() {
        return gun5;
    }

    public void setGun5(int gun5) {
        this.gun5 = gun5;
    }

    public int getGun6() {
        return gun6;
    }

    public void setGun6(int gun6) {
        this.gun6 = gun6;
    }

    public Date getDate_start() {
        return Date_start;
    }

    public void setDate_start(Date date_start) {
        Date_start = date_start;
    }

    public Date getDate_end() {
        return Date_end;
    }

    public void setDate_end(Date date_end) {
        Date_end = date_end;
    }

    public int getDiscount1() {
        return Discount1;
    }

    public void setDiscount1(int discount1) {
        Discount1 = discount1;
    }

    public int getDiscount2() {
        return Discount2;
    }

    public void setDiscount2(int discount2) {
        Discount2 = discount2;
    }

    public int getDiscount3() {
        return Discount3;
    }

    public void setDiscount3(int discount3) {
        Discount3 = discount3;
    }

    public int getDiscount4() {
        return Discount4;
    }

    public void setDiscount4(int discount4) {
        Discount4 = discount4;
    }

    public int getDiscount5() {
        return Discount5;
    }

    public void setDiscount5(int discount5) {
        Discount5 = discount5;
    }

    public int getDiscount6() {
        return Discount6;
    }

    public void setDiscount6(int discount6) {
        Discount6 = discount6;
    }

    public boolean isOpen1() {
        return IsOpen1;
    }

    public void setOpen1(boolean open1) {
        IsOpen1 = open1;
    }

    public boolean isOpen2() {
        return IsOpen2;
    }

    public void setOpen2(boolean open2) {
        IsOpen2 = open2;
    }

    public boolean isOpen3() {
        return IsOpen3;
    }

    public void setOpen3(boolean open3) {
        IsOpen3 = open3;
    }

    public boolean isOpen4() {
        return IsOpen4;
    }

    public void setOpen4(boolean open4) {
        IsOpen4 = open4;
    }

    public boolean isOpen5() {
        return IsOpen5;
    }

    public void setOpen5(boolean open5) {
        IsOpen5 = open5;
    }

    public boolean isOpen6() {
        return IsOpen6;
    }

    public void setOpen6(boolean open6) {
        IsOpen6 = open6;
    }
}
