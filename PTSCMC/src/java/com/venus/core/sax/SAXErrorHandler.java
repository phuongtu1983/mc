/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.core.sax;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXParseException;

/**
 *
 * @author phuongtu
 */
public class SAXErrorHandler extends DefaultHandler {

    private String errorMessage;

    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public void error(SAXParseException e) {
        this.errorMessage = e.getMessage();
    }

    @Override
    public void warning(SAXParseException e) {
        this.errorMessage = e.getMessage();
    }

    @Override
    public void fatalError(SAXParseException e) {
        this.errorMessage = e.getMessage();
    }
}
