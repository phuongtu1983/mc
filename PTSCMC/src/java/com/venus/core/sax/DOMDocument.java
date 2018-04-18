/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.core.sax;

import org.jdom.Element;

/**
 *
 * @author phuongtu
 */
public class DOMDocument extends XmlProperties {

    public DOMDocument(String fileName) throws XmlPropertiesException {
        super(fileName);
    }
    
    public Element getRoot(){
        return root;
    }
}
