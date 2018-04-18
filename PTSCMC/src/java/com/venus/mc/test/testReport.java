/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.test;

import com.venus.mc.core.HierSpineReportParser;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author PhuongTu
 */
public class testReport extends HierSpineReportParser {

    private String table1 = "table1";
    private String tableRow1 = "tableRow1";
    private String tableRow2 = "tableRow2";
    private testReportBean bean;

    public testReport(String xmlTemplate, String wordTemplate, String resultFileName) {
        super(xmlTemplate, wordTemplate, resultFileName);
    }

    @Override
    protected void parse(Object object) throws Exception {
        
        setText("mcrp_nmed", "bien nho ten em goi ve");
        
        bean = new testReportBean(this);
        try {
            initBean();
        } catch (Exception ex) {
        }
        Hashtable map = new Hashtable();
        ArrayList arrTable = new ArrayList();
        arrTable.add(bean);
        map.put(table1, arrTable);
        this.setArrTable(map);

    }

    @Override
    public String getTabText(String rowName, int rowId, String tab) {
        if (rowName.equals(tableRow1)) {
            if (bean.getBeanLevel1() != null) {
                if (bean.getBeanLevel1() != null) {
                    ArrayList arr = bean.getBeanLevel1();
                    testReportBeanLevel1 level1 = null;
                    int amount = 0;
                    for (int i = 0; i < arr.size(); i++) {
                        amount += 1;
                        level1 = (testReportBeanLevel1) arr.get(i);
                        if (rowId == amount) {
                            break;
                        }
                        if (level1 != null && level1.beanLevel2 != null) {
                            amount += level1.beanLevel2.size();
                        }
                    }
                    if (level1 != null) {
                        if (tab.equals("mcrp_n1")) {
                            return level1.getN1();
                        } else if (tab.equals("mcrp_vendor")) {
                            return level1.getVendor();
                        } else if (tab.equals("mcrp_deliveryDate")) {
                            return level1.getVendor();
                        } else if (tab.equals("mcrp_certificate")) {
                            return level1.getVendor();
                        }
                    }
                }
            }
        } else if (rowName.equals(tableRow2)) {
            if (bean.getBeanLevel1() != null) {
                ArrayList arr = bean.getBeanLevel1();
                testReportBeanLevel1 level1 = null;
                int amount = 0;
                testReportBeanLevel2 level2 = null;
                for (int i = 0; i < arr.size(); i++) {
                    amount += 1;
                    level1 = (testReportBeanLevel1) arr.get(i);
                    if (level1.getBeanLevel2() != null) {
                        if (rowId > amount && rowId <= amount + level1.getBeanLevel2().size()) {
                            level2 = (testReportBeanLevel2) level1.getBeanLevel2().get(i);
                            break;
                        }
                        amount += level1.beanLevel2.size();
                    }
                }
                if (level2 != null) {
                    if (tab.equals("mcrp_n2")) {
                        return level2.getN2();
                    } else if (tab.equals("mcrp_material")) {
                        return level2.getMaterial();
                    }
                }
            }
        }
        return "";
    }

    @Override
    public int getNumberOfRowToDuplicate(int id, String name) {
        int result = 0;
        if (name.equals(tableRow1)) {
            if (bean.getBeanLevel1() != null) {
                result = bean.getBeanLevel1().size();
            }
        } else if (name.equals(tableRow2)) {
            ArrayList arr = bean.getBeanLevel1();
            testReportBeanLevel1 level1 = null;
            int amount = 0;
            for (int i = 0; i < arr.size(); i++) {
                amount += 1;
                level1 = (testReportBeanLevel1) arr.get(i);
                if (id == amount + 1) {
                    if (level1.getBeanLevel2() != null) {
                        result = level1.getBeanLevel2().size();
                        break;
                    }
                }
                if (level1.getBeanLevel2() != null) {
                    amount += level1.beanLevel2.size();
                }
            }
        }
        return result;
    }

    private void initBean() {
        for (int i = 0; i < 1; i++) {
            testReportBeanLevel1 level1 = new testReportBeanLevel1();
            bean.getBeanLevel1().add(level1);
            level1.setN1("4." + i);
            level1.setVendor("Cong ty " + i);
            level1.setDeliveryDate("thoi gian giao hang " + i);
            level1.setCertificate("chung chi kem theo " + i);
            level1.setN3(1 + "");
            level1.setAmount3("amout0." + i);
            level1.setN4(2 + "");
            level1.setAmount4("amout1." + i);
            level1.setN5(3 + "");
            level1.setAmount5("amout2." + i);
            for (int j = 0; j < i + 1; j++) {
                testReportBeanLevel2 level2 = new testReportBeanLevel2();
                level1.getBeanLevel2().add(level2);
                level2.setAmount(j);
                level2.setMaterial("mat" + i + "." + j);
                level2.setN2(j + "");
                level2.setPrice(j);
                level2.setQuantity(j);
                level2.setUnit("unit" + j);

            }
        }
    }
}
