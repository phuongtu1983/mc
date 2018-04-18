// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name: XmlProperties.java

package com.venus.core.sax;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public abstract class XmlProperties {
    
    protected Document xmlProperties;
    
    protected Element root;
    
    public XmlProperties(String fileName) throws XmlPropertiesException {
        //InputStream in = null;
        BufferedReader in = null;        
        try {                        
            in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"));            
            SAXBuilder parser = new SAXBuilder(false);
            parser.setValidation(false);
            parser.setEntityResolver(new EntityResolver() {
                public InputSource resolveEntity(String publicId, String systemId) throws SAXException,IOException {
                    return new InputSource(new ByteArrayInputStream("<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
                }
            });
            xmlProperties = parser.build(in);
            root = xmlProperties.getRootElement();
        } catch (JDOMException je) {
            throw new XmlPropertiesException(
                "Could not read xml properties file: " + fileName, je);
        } catch (IOException ioe) {
            throw new XmlPropertiesException(
                "Could not read xml properties file: " + fileName, ioe);
        } catch (Exception ex) {
            
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ioexception) {
                    // Ignore
                }
            }
        }
    }
    
}