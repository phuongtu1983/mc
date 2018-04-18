/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.mrir.decorator;

import com.venus.mc.bean.MrirBean;
import com.venus.mc.process.store.mrir.MrirFormBean;
import com.venus.mc.util.MCUtil;
import org.displaytag.decorator.TotalTableDecorator;

/**
 *
 * @author thcao
 */
public class MrirDecorator extends TotalTableDecorator {

    public MrirDecorator() {
        super();
    }

    public String getMrirNumber() {
        MrirBean bean = (MrirBean) getCurrentRowObject();
        StringBuffer str = new StringBuffer();
        str.append("<a href=\"#\" onclick=\"mrirForm(").append(bean.getMrirId()).append(")\">");
        str.append(bean.getMrirNumber()).append("</a>");
        return str.toString();
    }

    public String getMrirKind() {
        MrirBean bean = (MrirBean) getCurrentRowObject();
        StringBuffer str = new StringBuffer();        
        if (bean.getKind() == MrirFormBean.KIND_MSV_PROJECT) {
            str.append(bean.getProName());
        } else if ( bean.getKind() == MrirFormBean.KIND_MSV_COMPANY ||
                bean.getKind() == MrirFormBean.KIND_MRV) {
            str.append(MCUtil.getBundleString("message.mrir.kind" + bean.getKind()));
        } 
        return str.toString();
    }
    
     public String getProName() {
        MrirBean bean = (MrirBean) getCurrentRowObject();
        StringBuffer str = new StringBuffer();        
        str.append(bean.getProName());
        return str.toString();
    }
}
