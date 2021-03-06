/*
 * Copyright (C) 2005  Alexis Miara (alexis.miara@licef.ca)
 *
 * This file is part of LomPad.
 *
 * LomPad is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * LomPad is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LomPad; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package ca.licef.lompad;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import java.awt.*;

class OrCompositeComponent extends FormComponent {
    JPanel jPanelWrapperGauche;
    JPanel jPanelWrapperDroite;
    JPanel jPanelOrCompGauche;
    JPanel jPanelOrCompDroite;

    LocalizeJLabel jLabelType;
    LocalizeJLabel jLabelName;
    LocalizeJLabel jLabelMinVer;
    LocalizeJLabel jLabelMaxVer;
    JComboBox jComboBoxType;
    JComboBox jComboBoxName;
    JTextFieldPopup jTextFieldMinVer;
    JTextFieldPopup jTextFieldMaxVer;

    String[] typeValues = {"4.4.1.1-1", "4.4.1.1-2"};
    String[] OSValues = new String[]{"pc-dos", "ms-windows", "macos", "unix", "multi-os", "none"};
    String[] BrowserValues = new String[]{"any", "netscape communicator", "ms-internet explorer", "opera", "amaya"};

    public OrCompositeComponent() {
        super(null);

        jPanelWrapperGauche = new JPanel();
        jPanelWrapperGauche.setOpaque(false);
        jPanelWrapperDroite = new JPanel();
        jPanelWrapperDroite.setOpaque(false);
        jPanelOrCompGauche = new JPanel();
        jPanelOrCompGauche.setOpaque(false);
        jPanelOrCompDroite = new JPanel();
        jPanelOrCompDroite.setOpaque(false);

        jLabelType = new LocalizeJLabel("4.4.1.1");
        jLabelType.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
        jLabelName = new LocalizeJLabel("4.4.1.2");
        jLabelName.setFont(jLabelType.getFont());
        jLabelMinVer = new LocalizeJLabel("4.4.1.3");
        jLabelMinVer.setFont(jLabelType.getFont());
        jLabelMaxVer = new LocalizeJLabel("4.4.1.4");
        jLabelMaxVer.setFont(jLabelType.getFont());

        jComboBoxName = new JComboBox();
        jComboBoxName.setFont(new Font("Dialog", Font.PLAIN, 11));

        jComboBoxType = new JComboBox();
        jComboBoxType.setFont(jComboBoxName.getFont());
        jComboBoxType.addActionListener(new SymAction());
        jComboBoxType.addItem(null);
        jComboBoxType.addItem(new OrderedValue(typeValues[0], 1, true));
        jComboBoxType.addItem(new OrderedValue(typeValues[1], 2, true));
        jTextFieldMinVer = new JTextFieldPopup();
        jTextFieldMaxVer = new JTextFieldPopup();

        jPanelWrapperGauche.setLayout(new CardLayout(5, 0));
        jPanelWrapperDroite.setLayout(new CardLayout(5, 0));
        jPanelOrCompGauche.setLayout(new GridLayout(4, 1, 0, 2));
        jPanelOrCompDroite.setLayout(new GridLayout(4, 1, 0, 2));

        jPanelOrCompGauche.add(jLabelType);
        jPanelOrCompDroite.add(jComboBoxType);

        jPanelOrCompGauche.add(jLabelName);
        jPanelOrCompDroite.add(jComboBoxName);

        jPanelOrCompGauche.add(jLabelMinVer);
        jPanelOrCompDroite.add(jTextFieldMinVer);

        jPanelOrCompGauche.add(jLabelMaxVer);
        jPanelOrCompDroite.add(jTextFieldMaxVer);

        jPanelWrapperGauche.add("g", jPanelOrCompGauche);
        jPanelWrapperDroite.add("d", jPanelOrCompDroite);
        jPanelGauche.add(BorderLayout.WEST, jPanelWrapperGauche);
        jPanelGauche.add(BorderLayout.CENTER, jPanelWrapperDroite);
    }

    boolean isFilled() {
        return !(jComboBoxName.getSelectedItem() == null &&
                jTextFieldMinVer.getText().trim().equals("") &&
                jTextFieldMaxVer.getText().trim().equals(""));
    }

    public void setEnabled(boolean b) {
        jComboBoxType.setEnabled(b);
        jComboBoxName.setEnabled(b);
        jTextFieldMinVer.setEditable(b);
        jTextFieldMinVer.setBackground(Color.white);
        jTextFieldMaxVer.setEditable(b);
        jTextFieldMaxVer.setBackground(Color.white);
    }

    public void setSelectedValue(JComboBox jComboBox, String s) {
        for (int i = 0; i < jComboBox.getItemCount(); i++) {
            Object v = jComboBox.getItemAt(i);
            if (s.equals(v)) {
                jComboBox.setSelectedItem(v);
                return;
            }
        }
    }

    class SymAction implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if (object == jComboBoxType) {
                jComboBoxName.removeAllItems();
                int index = jComboBoxType.getSelectedIndex();

                String[] values = null;
                if (index == 1) values = OSValues;
                if (index == 2) values = BrowserValues;

                if (values != null)
                    for (int i = 0; i < values.length; i++)
                        jComboBoxName.addItem(values[i]);
            }
        }
    }
    
    String toEvaluateFrame(String key) {
        String xml = "";

        if (jComboBoxName.getSelectedItem() != null)            
            xml += Util.getXMLVocabulary(((OrderedValue) jComboBoxType.getSelectedItem()).value.toString()) + 
                   ": " + jComboBoxName.getSelectedItem();                           
        
        if (!jTextFieldMinVer.getText().trim().equals(""))
            xml += ", " + Util.convertSpecialCharactersForXML(jTextFieldMinVer.getText().trim());
        
        if (!jTextFieldMaxVer.getText().trim().equals(""))
            xml += " - " + Util.convertSpecialCharactersForXML(jTextFieldMaxVer.getText().trim()) + "\n";

        //if (xml.equals("")) xml = null;
        return xml;
    }
    

    //XML
    String toXML(String key) {
        String xml = "";

        if (jComboBoxName.getSelectedItem() != null) {
            
            
            xml += "<" + Util.getTag(key + ".1") + ">" +
                    "<source>LOMv1.0</source>\n" +
                    "<value>" +
                    Util.getXMLVocabulary(((OrderedValue) jComboBoxType.getSelectedItem()).value.toString()) +
                    "</value>\n" +
                    "</" + Util.getTag(key + ".1") + ">\n";
            xml += "<" + Util.getTag(key + ".2") + ">" +
                    "<source>LOMv1.0</source>\n" +
                    "<value>" + jComboBoxName.getSelectedItem() + "</value>\n" +
                    "</" + Util.getTag(key + ".2") + ">\n";
        }
        
        
        if (!jTextFieldMinVer.getText().trim().equals(""))
            xml += "<" + Util.getTag(key + ".3") + ">" +
                    Util.convertSpecialCharactersForXML(jTextFieldMinVer.getText().trim()) +
                    "</" + Util.getTag(key + ".3") + ">\n";
        if (!jTextFieldMaxVer.getText().trim().equals(""))
            xml += "<" + Util.getTag(key + ".4") + ">" +
                    Util.convertSpecialCharactersForXML(jTextFieldMaxVer.getText().trim()) +
                    "</" + Util.getTag(key + ".4") + ">\n";

        if (xml.equals("")) xml = null;
        return xml;
    }

    void fromXML(String path, Element e) {
        NodeList list = e.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element child = (Element) node;
                try {
                    int pos = Util.getPosTag(path + "/" + child.getTagName().toLowerCase());
                    if (pos == 1) {
                        NodeList nl = child.getElementsByTagNameNS(Util.lomNSURI,"value");
                        if (nl.getLength() != 0) {
                            Element val = (Element) nl.item(0);
                            if (val.getFirstChild() != null) {
                                String key = val.getFirstChild().getNodeValue().trim().replace(' ', '_');
                                jComboBoxType.setSelectedIndex(Util.getPosVocabulary(key, false));
                            }
                        }
                    }
                    if (pos == 2) {
                        NodeList nl = child.getElementsByTagNameNS(Util.lomNSURI,"value");
                        if (nl.getLength() != 0) {
                            Element val = (Element) nl.item(0);
                            if (val.getFirstChild() != null)
                                setSelectedValue(jComboBoxName, val.getFirstChild().getNodeValue());
                        }
                    }
                    if (pos == 3) {
                        if (child.getFirstChild() != null)
                            jTextFieldMinVer.setText(child.getFirstChild().getNodeValue());
                    }
                    if (pos == 4) {
                        if (child.getFirstChild() != null)
                            jTextFieldMaxVer.setText(child.getFirstChild().getNodeValue());
                    }
                } catch (IllegalTagException ite) {
                }
            }
        }
    }

    //HTML
    String toHTML(String key) {
        String html = "";

        if (jComboBoxName.getSelectedItem() != null) {
            html += Util.getVocabulary(((OrderedValue) jComboBoxType.getSelectedItem()).value.toString()) + " : " +
                        jComboBoxName.getSelectedItem() + "<br>";
        }
        if (!jTextFieldMinVer.getText().trim().equals(""))
            html += Util.getLabel(key + ".1.3") + " : " + jTextFieldMinVer.getText().trim() + "<br>";

        if (!jTextFieldMaxVer.getText().trim().equals(""))
            html += Util.getLabel(key + ".1.4") + " : " + jTextFieldMaxVer.getText().trim() + "<br>";

        if (html.equals("")) html = null;
        return html;
    }
}
