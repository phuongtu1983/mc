/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.workReport;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmrirBean;
import com.venus.mc.bean.EosDBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.EmrirDAO;
import com.venus.mc.util.MCUtil;

/**
 *
 * @author kngo
 */
public class EosDReport extends SpineReportParser {

    public EosDReport(String xmlTemplate, String wordTemplate, String resultFileName) {
        super(xmlTemplate, wordTemplate, resultFileName);
    }

    @Override
    protected void parse(Object object) throws Exception {
        Integer eosdIdObject = (Integer) object;
        int eosdId = eosdIdObject.intValue();
        EmrirDAO emrirDAO = new EmrirDAO();
        EosDBean bean = null;
        try {
            bean = emrirDAO.getEosD(eosdId);
        } catch (Exception ex) {
            LogUtil.error(EosDReport.class.getName() + ": " + ex.getMessage());
        }
        if (bean == null) {
            return;
        }
        EmrirBean mrirBean = null;
        try {
            mrirBean = emrirDAO.getEmrir(bean.getEmrirId());
        } catch (Exception ex) {
            LogUtil.error(EosDReport.class.getName() + ": " + ex.getMessage());
        }
        if (mrirBean != null) {
            setText("mcrp_proName", mrirBean.getProName());
            setText("mcrp_emrirNumber", mrirBean.getEmrirNumber());


        } else {
            setText("mcrp_proName", "");
            setText("mcrp_emrirNumber", "");
        }
        setText("mcrp_actionBy", bean.getActionByName());
        setText("mcrp_createdEmpName", bean.getCreatedEmpName());
        setText("mcrp_eosdNumber", bean.getEosdNumber());
        setText("mcrp_createdDate1", bean.getCreatedDate());
        setText("mcrp_createdDate2", bean.getCreatedDate());
        setText("mcrp_description", bean.getDescription());
        setText("mcrp_correctAct", bean.getCorrectAct());
        setText("mcrp_dueDate", bean.getDueDate());
        setText("mcrp_closedDate", bean.getClosedDate());
        setText("mcrp_note", bean.getNote());
        if (bean.getClosed() == 0) {
            setText("mcrp_closed", MCUtil.getBundleString("message.osd.closed1"));
        } else if (bean.getClosed() == 1) {
            setText("mcrp_closed", MCUtil.getBundleString("message.osd.closed2"));
        }
        String[] sNonCon = bean.getNonConform().split(",");
        for (int i = 0; i < sNonCon.length; i++) {
            int idx = NumberUtil.parseInt(sNonCon[i], 0);
            switch (idx) {
                case 1:
                    setCheck("checkbox1", true);
                    break;
                case 2:
                    setCheck("checkbox2", true);
                    break;
                case 4:
                    setCheck("checkbox3", true);
                    break;
                case 8:
                    setCheck("checkbox4", true);
                    break;
                default:
                    break;
                }
        }
    }

    @Override
    public String getTabText(String rowId, int i, String tab) {
        return "";
    }
}
