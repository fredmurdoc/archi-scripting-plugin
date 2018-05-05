/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.script.dom.model;

import com.archimatetool.editor.model.DiagramModelUtils;
import com.archimatetool.model.IArchimateConcept;
import com.archimatetool.model.IArchimateRelationship;
import com.archimatetool.model.IDiagramModel;
import com.archimatetool.model.IDiagramModelArchimateComponent;
import com.archimatetool.model.IFolder;

/**
 * Archimate Concept wrapper proxy
 * 
 * @author Phillip Beauvoir
 */
public abstract class ArchimateConceptProxy extends EObjectProxy {
    
    public ArchimateConceptProxy() {
    }
    
    public ArchimateConceptProxy(IArchimateConcept concept) {
        super(concept);
    }
    
    @Override
    protected IArchimateConcept getEObject() {
        return (IArchimateConcept)super.getEObject();
    }

    public void delete() {
        checkModelAccess();
        
        if(getSourceRelationships().isEmpty() && getTargetRelationships().isEmpty() && getDiagramComponentInstances().isEmpty()) {
            ((IFolder)getEObject().eContainer()).getElements().remove(getEObject());
        }
        else {
            throw new ArchiScriptException(Messages.ArchimateConceptProxy_0 + " " + this); //$NON-NLS-1$
        }
    }
    
    public ArchimateRelationshipProxyCollection getSourceRelationships() {
        ArchimateRelationshipProxyCollection list = new ArchimateRelationshipProxyCollection();
        for(IArchimateRelationship r : getEObject().getSourceRelationships()) {
            list.add(new ArchimateRelationshipProxy(r));
        }
        return list;
    }
    
    public ArchimateRelationshipProxyCollection getTargetRelationships() {
        ArchimateRelationshipProxyCollection list = new ArchimateRelationshipProxyCollection();
        for(IArchimateRelationship r : getEObject().getTargetRelationships()) {
            list.add(new ArchimateRelationshipProxy(r));
        }
        return list;
    }
    
    public DiagramModelArchimateComponentProxyCollection getDiagramComponentInstances() {
        DiagramModelArchimateComponentProxyCollection list = new DiagramModelArchimateComponentProxyCollection();
        
        for(IDiagramModel dm : getEObject().getArchimateModel().getDiagramModels()) {
            for(IDiagramModelArchimateComponent dmc : DiagramModelUtils.findDiagramModelComponentsForArchimateConcept(dm, getEObject())) {
                list.add((DiagramModelArchimateComponentProxy)EObjectProxy.get(dmc));
            }
        }
        
        return list;
    }
    
    @Override
    public Object attr(String attribute) {
        switch(attribute) {
            case "sourceRelationships": //$NON-NLS-1$
                return getSourceRelationships();
            case "targetRelationships": //$NON-NLS-1$
                return getTargetRelationships();
            case "diagramComponentInstances": //$NON-NLS-1$
                return getDiagramComponentInstances();
        }
        
        return super.attr(attribute);
    }

}