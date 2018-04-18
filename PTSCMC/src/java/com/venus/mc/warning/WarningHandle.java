/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.warning;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.MailUtil;
import com.venus.mc.MCConfig;
import com.venus.mc.bean.DnBean;
import com.venus.mc.bean.DnWarningBean;
import com.venus.mc.bean.RepairPlanWarningBean;
import com.venus.mc.bean.SystemConfigBean;
import com.venus.mc.contract.InvoiceContractFormBean;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.EmcDAO;
import com.venus.mc.dao.McoDAO;
import com.venus.mc.dao.RepairPlanDAO;
import com.venus.mc.dao.SystemConfigDAO;
import com.venus.mc.dao.VerifiedPlanDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;

/**
 *
 * @author phuongtu
 */
public class WarningHandle {

    public static final String EMAIL_DEFAULT = MCConfig.getSenderMail();

    public void WarningMaterialDelivery(int param) {
        try {
            DnDAO dnDAO = new DnDAO();
            ArrayList arrDetail = dnDAO.getDnDetailsForWarning(DnBean.KIND1, param);
            if (arrDetail.size() > 0) {
                String strSub = MCUtil.getBundleString("param.warning.label") + " : " + MCUtil.getBundleString("param.warning.label.deliveryNotice") + " - " + DateUtil.today("dd/MM/yyyy");
                String content = WarningContentHandle.getDnsDetailForWarning(strSub, arrDetail);
                String mailTo = "";
                String mailCC = "";
                try {
                    SystemConfigDAO configDAO = new SystemConfigDAO();
                    SystemConfigBean bean = null;
                    bean = configDAO.getConfig(SystemConfigBean.DNTO);
                    if (bean != null) {
                        mailTo = bean.getValue();
                    }
                    bean = configDAO.getConfig(SystemConfigBean.DNCC);
                    if (bean != null) {
                        mailTo = bean.getValue();
                    }
                    if (GenericValidator.isBlankOrNull(mailTo)) {
                        mailTo = EMAIL_DEFAULT;
                    }
                    if (GenericValidator.isBlankOrNull(mailCC)) {
                        mailCC = EMAIL_DEFAULT;
                    }
                } catch (Exception ex) {
                }
                MailUtil.sendMailAuth(MCConfig.getSenderMail(),
                        mailTo,
                        mailCC,
                        strSub,
                        content,
                        null,
                        null,
                        MCConfig.getMailServer(),
                        MCConfig.getAuthUserName(),
                        MCConfig.getAuthPassword());
                String ids = "0";
                DnWarningBean bean = null;
                for (int i = 0; i < arrDetail.size(); i++) {
                    bean = (DnWarningBean) arrDetail.get(i);
                    ids += "," + bean.getDnwId();
                }
                if (!ids.equals("0")) {
                    dnDAO.updateDnsWarnedStatus(ids);
                }
            }
        } catch (Exception ex) {
        }
    }

    public void WarningContractInvoicePaid(int param) {
        try {
            ContractDAO contractDAO = new ContractDAO();
            ArrayList arrDetail = contractDAO.getContractInvoicesForWarning(param);
            if (arrDetail.size() > 0) {
                String strSub = MCUtil.getBundleString("param.warning.label") + " : " + MCUtil.getBundleString("param.warning.label.invoice") + " - " + DateUtil.today("dd/MM/yyyy");
                String content = WarningContentHandle.getInvoicesForWarning(strSub, arrDetail);
                String mailTo = "";
                String mailCC = "";
                try {
                    SystemConfigDAO configDAO = new SystemConfigDAO();
                    SystemConfigBean bean = null;
                    bean = configDAO.getConfig(SystemConfigBean.INVOICETO);
                    if (bean != null) {
                        mailTo = bean.getValue();
                    }
                    bean = configDAO.getConfig(SystemConfigBean.INVOICECC);
                    if (bean != null) {
                        mailTo = bean.getValue();
                    }
                    if (GenericValidator.isBlankOrNull(mailTo)) {
                        mailTo = EMAIL_DEFAULT;
                    }
                    if (GenericValidator.isBlankOrNull(mailCC)) {
                        mailCC = EMAIL_DEFAULT;
                    }
                } catch (Exception ex) {
                }
                MailUtil.sendMailAuth(MCConfig.getSenderMail(),
                        mailTo,
                        mailCC,
                        strSub,
                        content,
                        null,
                        null,
                        MCConfig.getMailServer(),
                        MCConfig.getAuthUserName(),
                        MCConfig.getAuthPassword());
                String ids = "0";
                InvoiceContractFormBean bean = null;
                for (int i = 0; i < arrDetail.size(); i++) {
                    bean = (InvoiceContractFormBean) arrDetail.get(i);
                    ids += "," + bean.getIcId();
                }
                if (!ids.equals("0")) {
                    contractDAO.updateInvoiceWarnedStatus(ids);
                }
            }
        } catch (Exception ex) {
        }
    }

    public void WarningMrir(int param) {
        try {
            DnDAO dnDAO = new DnDAO();
            ArrayList arrDetail = dnDAO.getMrirForWarning(param);
            if (arrDetail.size() > 0) {
                String strSub = MCUtil.getBundleString("param.warning.label") + " : " + MCUtil.getBundleString("param.warning.label.mrir") + " - " + DateUtil.today("dd/MM/yyyy");
                String content = WarningContentHandle.getMrirForWarning(strSub, arrDetail);
                String mailTo = "";
                String mailCC = "";
                try {
                    SystemConfigDAO configDAO = new SystemConfigDAO();
                    SystemConfigBean bean = null;
                    bean = configDAO.getConfig(SystemConfigBean.MRIRTO);
                    if (bean != null) {
                        mailTo = bean.getValue();
                    }
                    bean = configDAO.getConfig(SystemConfigBean.MRIRCC);
                    if (bean != null) {
                        mailTo = bean.getValue();
                    }
                    if (GenericValidator.isBlankOrNull(mailTo)) {
                        mailTo = EMAIL_DEFAULT;
                    }
                    if (GenericValidator.isBlankOrNull(mailCC)) {
                        mailCC = EMAIL_DEFAULT;
                    }
                } catch (Exception ex) {
                }
                MailUtil.sendMailAuth(MCConfig.getSenderMail(),
                        mailTo,
                        mailCC,
                        strSub,
                        content,
                        null,
                        null,
                        MCConfig.getMailServer(),
                        MCConfig.getAuthUserName(),
                        MCConfig.getAuthPassword());
                String ids = "0";
                InvoiceContractFormBean bean = null;
                for (int i = 0; i < arrDetail.size(); i++) {
                    bean = (InvoiceContractFormBean) arrDetail.get(i);
                    ids += "," + bean.getIcId();
                }
                if (!ids.equals("0")) {
                    //dnDAO.updateMrirWarnedStatus(ids);
                }
            }
        } catch (Exception ex) {
        }
    }

    public void WarningMsv(int param) {
        try {
            DnDAO dnDAO = new DnDAO();
            ArrayList arrDetail = dnDAO.getMsvForWarning(param);
            if (arrDetail.size() > 0) {
                String strSub = MCUtil.getBundleString("param.warning.label") + " : " + MCUtil.getBundleString("param.warning.label.msv") + " - " + DateUtil.today("dd/MM/yyyy");
                String content = WarningContentHandle.getMsvForWarning(strSub, arrDetail);
                String mailTo = "";
                String mailCC = "";
                try {
                    SystemConfigDAO configDAO = new SystemConfigDAO();
                    SystemConfigBean bean = null;
                    bean = configDAO.getConfig(SystemConfigBean.MSVTO);
                    if (bean != null) {
                        mailTo = bean.getValue();
                    }
                    bean = configDAO.getConfig(SystemConfigBean.MSVCC);
                    if (bean != null) {
                        mailTo = bean.getValue();
                    }
                    if (GenericValidator.isBlankOrNull(mailTo)) {
                        mailTo = EMAIL_DEFAULT;
                    }
                    if (GenericValidator.isBlankOrNull(mailCC)) {
                        mailCC = EMAIL_DEFAULT;
                    }
                } catch (Exception ex) {
                }
                MailUtil.sendMailAuth(MCConfig.getSenderMail(),
                        mailTo,
                        mailCC,
                        strSub,
                        content,
                        null,
                        null,
                        MCConfig.getMailServer(),
                        MCConfig.getAuthUserName(),
                        MCConfig.getAuthPassword());
                String ids = "0";
                InvoiceContractFormBean bean = null;
                for (int i = 0; i < arrDetail.size(); i++) {
                    bean = (InvoiceContractFormBean) arrDetail.get(i);
                    ids += "," + bean.getIcId();
                }
                if (!ids.equals("0")) {
                    //dnDAO.updateMsvWarnedStatus(ids);
                }
            }
        } catch (Exception ex) {
        }
    }

    public void WarningRepair(int param) {
        try {
            RepairPlanDAO rpDAO = new RepairPlanDAO();
            ArrayList arrDetail = rpDAO.getRepairPlanForWarning(param);
            if (arrDetail.size() > 0) {
                String strSub = MCUtil.getBundleString("param.warning.label") + " : " + MCUtil.getBundleString("param.warning.label.repair") + " - " + DateUtil.today("dd/MM/yyyy");
                String content = WarningContentHandle.getRepairPlanForWarning(strSub, arrDetail);
                String mailTo = "";
                String mailCC = "";
                try {
                    SystemConfigDAO configDAO = new SystemConfigDAO();
                    SystemConfigBean bean = null;
                    bean = configDAO.getConfig(SystemConfigBean.REPAIRTO);
                    if (bean != null) {
                        mailTo = bean.getValue();
                    }
                    bean = configDAO.getConfig(SystemConfigBean.REPAIRCC);
                    if (bean != null) {
                        mailTo = bean.getValue();
                    }
                    if (GenericValidator.isBlankOrNull(mailTo)) {
                        mailTo = EMAIL_DEFAULT;
                    }
                    if (GenericValidator.isBlankOrNull(mailCC)) {
                        mailCC = EMAIL_DEFAULT;
                    }
                } catch (Exception ex) {
                }
                MailUtil.sendMailAuth(MCConfig.getSenderMail(),
                        mailTo,
                        mailCC,
                        strSub,
                        content,
                        null,
                        null,
                        MCConfig.getMailServer(),
                        MCConfig.getAuthUserName(),
                        MCConfig.getAuthPassword());
                String ids = "0";
                RepairPlanWarningBean bean = null;
                for (int i = 0; i < arrDetail.size(); i++) {
                    bean = (RepairPlanWarningBean) arrDetail.get(i);
                    ids += "," + bean.getRpId();
                }
                if (!ids.equals("0")) {
                    rpDAO.updateRepairPlanWarnedStatus(ids);
                }
            }
        } catch (Exception ex) {
        }
    }

    public void WarningAudit(int param) {
        try {
            VerifiedPlanDAO rpDAO = new VerifiedPlanDAO();
            ArrayList arrDetail = rpDAO.getVerifiedPlanForWarning(param);
            if (arrDetail.size() > 0) {
                String strSub = MCUtil.getBundleString("param.warning.label") + " : " + MCUtil.getBundleString("param.warning.label.audit") + " - " + DateUtil.today("dd/MM/yyyy");
                String content = WarningContentHandle.getRepairPlanForWarning(strSub, arrDetail);
                String mailTo = "";
                String mailCC = "";
                try {
                    SystemConfigDAO configDAO = new SystemConfigDAO();
                    SystemConfigBean bean = null;
                    bean = configDAO.getConfig(SystemConfigBean.AUDITTO);
                    if (bean != null) {
                        mailTo = bean.getValue();
                    }
                    bean = configDAO.getConfig(SystemConfigBean.AUDITCC);
                    if (bean != null) {
                        mailTo = bean.getValue();
                    }
                    if (GenericValidator.isBlankOrNull(mailTo)) {
                        mailTo = EMAIL_DEFAULT;
                    }
                    if (GenericValidator.isBlankOrNull(mailCC)) {
                        mailCC = EMAIL_DEFAULT;
                    }
                } catch (Exception ex) {
                }
                MailUtil.sendMailAuth(MCConfig.getSenderMail(),
                        mailTo,
                        mailCC,
                        strSub,
                        content,
                        null,
                        null,
                        MCConfig.getMailServer(),
                        MCConfig.getAuthUserName(),
                        MCConfig.getAuthPassword());
                String ids = "0";
                RepairPlanWarningBean bean = null;
                for (int i = 0; i < arrDetail.size(); i++) {
                    bean = (RepairPlanWarningBean) arrDetail.get(i);
                    ids += "," + bean.getRpId();
                }
                if (!ids.equals("0")) {
                    rpDAO.updateVerifiedPlanWarnedStatus(ids);
                }
            }
        } catch (Exception ex) {
        }
    }

    public void WarningMatIn(int param) {
        try {
            McoDAO mcoDAO = new McoDAO();
            ArrayList arrDetail = mcoDAO.getMaterialCarryOutForWarning(param);
            if (arrDetail.size() > 0) {
                String strSub = MCUtil.getBundleString("param.warning.label") + " : " + MCUtil.getBundleString("param.warning.label.matIn") + " - " + DateUtil.today("dd/MM/yyyy");
                String content = WarningContentHandle.getMcoForWarning(strSub, arrDetail);
                String mailTo = "";
                String mailCC = "";
                try {
                    SystemConfigDAO configDAO = new SystemConfigDAO();
                    SystemConfigBean bean = null;
                    bean = configDAO.getConfig(SystemConfigBean.MATINTO);
                    if (bean != null) {
                        mailTo = bean.getValue();
                    }
                    bean = configDAO.getConfig(SystemConfigBean.MATINCC);
                    if (bean != null) {
                        mailTo = bean.getValue();
                    }
                    if (GenericValidator.isBlankOrNull(mailTo)) {
                        mailTo = EMAIL_DEFAULT;
                    }
                    if (GenericValidator.isBlankOrNull(mailCC)) {
                        mailCC = EMAIL_DEFAULT;
                    }
                } catch (Exception ex) {
                }
                MailUtil.sendMailAuth(MCConfig.getSenderMail(),
                        mailTo,
                        mailCC,
                        strSub,
                        content,
                        null,
                        null,
                        MCConfig.getMailServer(),
                        MCConfig.getAuthUserName(),
                        MCConfig.getAuthPassword());
                String ids = "0";
                InvoiceContractFormBean bean = null;
                for (int i = 0; i < arrDetail.size(); i++) {
                    bean = (InvoiceContractFormBean) arrDetail.get(i);
                    ids += "," + bean.getIcId();
                }
                if (!ids.equals("0")) {
                    mcoDAO.updateMcoWarnedStatus(ids);
                }
            }
        } catch (Exception ex) {
        }
    }

    public void WarningMatOut(int param) {
        try {
            EmcDAO mcDAO = new EmcDAO();
            ArrayList arrDetail = mcDAO.getMaterialCarryOnForWarning(param);
            if (arrDetail.size() > 0) {
                String strSub = MCUtil.getBundleString("param.warning.label") + " : " + MCUtil.getBundleString("param.warning.label.matOut") + " - " + DateUtil.today("dd/MM/yyyy");
                String content = WarningContentHandle.getMcForWarning(strSub, arrDetail);
                String mailTo = "";
                String mailCC = "";
                try {
                    SystemConfigDAO configDAO = new SystemConfigDAO();
                    SystemConfigBean bean = null;
                    bean = configDAO.getConfig(SystemConfigBean.MATOUTTO);
                    if (bean != null) {
                        mailTo = bean.getValue();
                    }
                    bean = configDAO.getConfig(SystemConfigBean.MATOUTCC);
                    if (bean != null) {
                        mailTo = bean.getValue();
                    }
                    if (GenericValidator.isBlankOrNull(mailTo)) {
                        mailTo = EMAIL_DEFAULT;
                    }
                    if (GenericValidator.isBlankOrNull(mailCC)) {
                        mailCC = EMAIL_DEFAULT;
                    }
                } catch (Exception ex) {
                }
                MailUtil.sendMailAuth(MCConfig.getSenderMail(),
                        mailTo,
                        mailCC,
                        strSub,
                        content,
                        null,
                        null,
                        MCConfig.getMailServer(),
                        MCConfig.getAuthUserName(),
                        MCConfig.getAuthPassword());
                String ids = "0";
                InvoiceContractFormBean bean = null;
                for (int i = 0; i < arrDetail.size(); i++) {
                    bean = (InvoiceContractFormBean) arrDetail.get(i);
                    ids += "," + bean.getIcId();
                }
                if (!ids.equals("0")) {
                    mcDAO.updateMcWarnedStatus(ids);
                }
            }
        } catch (Exception ex) {
        }
    }
}
