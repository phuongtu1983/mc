/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.test;

import java.util.ArrayList;

/**
 *
 * @author PhuongTu
 */
public class testReportBeanLevel1 {

    public String n1;
    public String vendor;
    public String n3;
    public String amount3;
    public String n4;
    public String amount4;
    public String n5;
    public String amount5;
    public String deliveryDate;
    public String certificate;
    public ArrayList beanLevel2;

    public String getAmount3() {
        return amount3;
    }

    public void setAmount3(String amount3) {
        this.amount3 = amount3;
    }

    public String getAmount4() {
        return amount4;
    }

    public void setAmount4(String amount4) {
        this.amount4 = amount4;
    }

    public String getAmount5() {
        return amount5;
    }

    public void setAmount5(String amount5) {
        this.amount5 = amount5;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public ArrayList getBeanLevel2() {
        if (beanLevel2 == null) {
            beanLevel2 = new ArrayList();
        }
        return beanLevel2;
    }

    public void setBeanLevel2(ArrayList beanLevel2) {
        this.beanLevel2 = beanLevel2;
    }

    public String getN1() {
        return n1;
    }

    public void setN1(String n1) {
        this.n1 = n1;
    }

    public String getN3() {
        return n3;
    }

    public void setN3(String n3) {
        this.n3 = n3;
    }

    public String getN4() {
        return n4;
    }

    public void setN4(String n4) {
        this.n4 = n4;
    }

    public String getN5() {
        return n5;
    }

    public void setN5(String n5) {
        this.n5 = n5;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}
