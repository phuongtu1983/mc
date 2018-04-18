package com.venus.mc.bean;

/**
 * @author phuongtu
 */
public class SystemConfigBean {

    private int id;
    private int type;
    private String value;

    public SystemConfigBean() {
    }

    public SystemConfigBean(int id, int type, String value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public static int DNTO = 1;
    public static int DNCC = 2;
    public static int INVOICETO = 3;
    public static int INVOICECC = 4;
    public static int MRIRTO = 5;
    public static int MRIRCC = 6;
    public static int MSVTO = 7;
    public static int MSVCC = 8;
    public static int REPAIRTO = 9;
    public static int REPAIRCC = 10;
    public static int AUDITTO = 11;
    public static int AUDITCC = 12;
    public static int MATINTO = 13;
    public static int MATINCC = 14;
    public static int MATOUTTO = 15;
    public static int MATOUTCC = 16;
}
