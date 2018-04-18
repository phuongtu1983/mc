package com.venus.mc.process.store.asset;

import com.venus.mc.core.SpineAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Mai Vinh Loc
 */
public class AssetPanelAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "assetPanel";
    }
}
