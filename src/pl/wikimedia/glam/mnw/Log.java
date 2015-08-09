/*
 * The MIT License
 *
 * Copyright 2015 Paweł Marynowski.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package pl.wikimedia.glam.mnw;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Log {
    private JTextPane loger;
    
    public Log(JTextPane loger) {
        this.loger = loger;
    }
    
    public void log(String s) {
        try {
            Document doc = loger.getDocument();
            doc.insertString(doc.getLength(), s, null);
        } catch(BadLocationException exc) {
        }
    }
    
    public void bold(String s) {
        try {
            SimpleAttributeSet attributes = new SimpleAttributeSet();
            StyleConstants.setBold(attributes, true);
            Document doc = loger.getDocument();
            doc.insertString(doc.getLength(), s, attributes);
        } catch(BadLocationException exc) {
        }
    }
    
    public void red(String s) {
        try {
            SimpleAttributeSet attributes = new SimpleAttributeSet();
            StyleConstants.setForeground(attributes, Color.red);
            Document doc = loger.getDocument();
            doc.insertString(doc.getLength(), s, attributes);
        } catch(BadLocationException exc) {
        }
    }
    
    public void green(String s) {
        try {
            SimpleAttributeSet attributes = new SimpleAttributeSet();
            StyleConstants.setForeground(attributes, new Color(0, 102, 0));
            Document doc = loger.getDocument();
            doc.insertString(doc.getLength(), s, attributes);
        } catch(BadLocationException exc) {
        }
    }
}
