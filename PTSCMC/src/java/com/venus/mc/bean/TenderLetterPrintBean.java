/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author mai vinh loc
 */
public class TenderLetterPrintBean {

    private String name;
    private String nameUpcase;
    private String address;
    private String phone;
    private String fax;
    private String email;
    private String bidDeadlineTime;
    private String bidDeadline;
    private String packName;
    private String packNameUpcase;
    private String placeDelivery;
    private String number;
    private String date;
    private String day;
    private String month;
    private String year;
    private String abbreviate;
    private String currency;
    private String deliveryTime;
    public TenderLetterPrintBean() {
    }

    public TenderLetterPrintBean(String name, String nameUpcase, String address, String phone, String fax, String email, String bidDeadlineTime, String bidDeadline, String packName, String packNameUpcase, String placeDelivery, String number, String date, String day, String month, String year, String abbreviate, String currency,  String deliveryTime) {
        this.name = name;
        this.nameUpcase = nameUpcase;
        this.address = address;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.bidDeadlineTime = bidDeadlineTime;
        this.bidDeadline = bidDeadline;
        this.packName = packName;
        this.packNameUpcase = packNameUpcase;
        this.placeDelivery = placeDelivery;
        this.number = number;
        this.date = date;
        this.day = day;
        this.month = month;
        this.year = year;
        this.abbreviate = abbreviate;
        this.currency = currency;
        this.deliveryTime= deliveryTime;
    }

   

    public void setAbbreviate(String abbreviate) {
        this.abbreviate = abbreviate;
    }

    public String getAbbreviate() {
        return abbreviate;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setPlaceDelivery(String placeDelivery) {
        this.placeDelivery = placeDelivery;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setNameUpcase(String nameUpcase) {
        this.nameUpcase = nameUpcase;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setBidDeadlineTime(String bidDeadlineTime) {
        this.bidDeadlineTime = bidDeadlineTime;
    }

    public void setBidDeadline(String bidDeadline) {
        this.bidDeadline = bidDeadline;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getYear() {
        return year;
    }

    public String getPlaceDelivery() {
        return placeDelivery;
    }

    public String getPhone() {
        return phone;
    }

    public String getPackName() {
        return packName;
    }

    public String getNumber() {
        return number;
    }

    public String getNameUpcase() {
        return nameUpcase;
    }

    public String getName() {
        return name;
    }

    public String getMonth() {
        return month;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public String getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }

    public String getBidDeadlineTime() {
        return bidDeadlineTime;
    }

    public String getBidDeadline() {
        return bidDeadline;
    }

    public String getAddress() {
        return address;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPackNameUpcase() {
        return packNameUpcase;
    }

    public void setPackNameUpcase(String packNameUpcase) {
        this.packNameUpcase = packNameUpcase;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    
}
