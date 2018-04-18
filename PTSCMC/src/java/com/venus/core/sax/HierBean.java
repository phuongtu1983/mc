/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.core.sax;

/**
 *
 * @author PhuongTu
 */
public class HierBean {

    protected StringBuffer content;

    public StringBuffer getContent() {
        return content;
    }

    public void setContent(StringBuffer content) {
        if (this.content == null) {
            this.content = content;
        } else {
            this.content.append(content);
        }
    }

    public StringBuffer convertToString() {
        return content;
    }

    protected String normalize(String s) {
        StringBuffer str = new StringBuffer();

        int len = (s != null) ? s.length() : 0;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '<': {
                    str.append("&lt;");
                    break;
                }
                case '>': {
                    str.append("&gt;");
                    break;
                }
                case '&': {
                    str.append("&amp;");
                    break;
                }
                case '"': {
                    str.append("&quot;");
                    break;
                }
                case '\'': {
                    str.append("&apos;");
                    break;
                }
                default: {
                    str.append(ch);
                }
            }
        }

        return str.toString();
    }
}
