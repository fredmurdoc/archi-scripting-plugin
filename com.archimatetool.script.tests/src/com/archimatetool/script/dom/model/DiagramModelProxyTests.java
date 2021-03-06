/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.script.dom.model;

import junit.framework.JUnit4TestAdapter;


/**
 * DiagramModelProxy Tests
 * 
 * @author Phillip Beauvoir
 */
public abstract class DiagramModelProxyTests extends EObjectProxyTests {
    
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(DiagramModelProxyTests.class);
    }
    
}