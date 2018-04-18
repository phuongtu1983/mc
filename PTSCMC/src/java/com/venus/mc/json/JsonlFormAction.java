/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.json;

import com.venus.core.sax.DOMDocument;
import com.venus.core.util.GenericValidator;
import com.venus.mc.util.Constants;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jdom.Element;

/**
 *
 * @author phuongtu
 */
public class JsonlFormAction extends org.apache.struts.action.Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JsonFormBean formBean = (JsonFormBean) form;
        String mapMessage = request.getParameter("mapMessage");
        String params = request.getParameter("params");
        String xmlFile = request.getSession().getServletContext().getRealPath("/functionMap.xml");
        DOMDocument xmlProperties = new DOMDocument(xmlFile);
        Element root = xmlProperties.getRoot();
        Element el = this.getElement(root, mapMessage);
        if (el != null) {
            formBean.setMessage(this.parseToJson(el, params));
        }
        request.getSession().setAttribute(Constants.JSON_FORM, formBean);
        return mapping.findForward(Constants.FORWARD_ACT_SUCCESS);
    }

    private Element getElement(Element parentElement, String message) {
        Element el = null;
        List childParent = parentElement.getChildren("message");
        for (int i = 0; i < childParent.size(); i++) {
            el = (Element) childParent.get(i);
            if (message.equals(el.getAttributeValue("content"))) {
                return el;
            }
        }
        return null;
    }

    private String parseToJson(Element element, String params) {
        String json = "{\"message\":\"" + element.getAttributeValue("content") + "\"";
        Element parentPage = element.getChild("parentPage");
        if (parentPage != null) {
            json += ",\"parentPage\":\"" + parentPage.getText() + "\"";
        }
        Element func = element.getChild("func");
        if (func != null) {
            json += ",\"func\":\"" + func.getText() + "\"";
        }
        if (!GenericValidator.isBlankOrNull(params)) {
            json += ",\"params\":\"" + params + "\"";
        }
        json += "}";
        return json;
    }
}
