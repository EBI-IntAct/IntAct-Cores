/*
 * Copyright 2001-2007 The European Bioinformatics Institute.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.ebi.intact.model.visitor.impl;

import uk.ac.ebi.intact.model.*;
import uk.ac.ebi.intact.model.visitor.BaseIntactVisitor;

import java.io.PrintStream;

/**
 * @author Bruno Aranda (baranda@ebi.ac.uk)
 * @version $Id$
 */
public class PrintVisitor extends BaseIntactVisitor {

    private static final String INDENTATION = "   ";

    private PrintStream ps;

    public PrintVisitor() {
        ps = System.out;
    }

    public PrintVisitor(PrintStream ps) {
        this.ps = ps;
    }

    @Override
    public void visitAnnotatedObject(AnnotatedObject annotatedObject) {
        ps.println(indentation()+annotatedObject.getClass().getSimpleName()+": "+annotatedObject.getShortLabel());
    }

    @Override
    public void visitXref(Xref xref) {
        ps.println(indentation()+"Xref: "+xref.getPrimaryId()+" (db: "+xref.getCvDatabase().getShortLabel()+", qual: "+xref.getCvXrefQualifier().getShortLabel()+")");
    }

    protected String indentation() {
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<getHierarchyLevel(); i++) {
            sb.append(INDENTATION);
        }

        return sb.toString();
    }
}