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
package uk.ac.ebi.intact.persistence.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import uk.ac.ebi.intact.context.IntactSession;
import uk.ac.ebi.intact.model.meta.ImexImport;
import uk.ac.ebi.intact.model.meta.ImexImportStatus;
import uk.ac.ebi.intact.persistence.dao.ImexImportDao;

import java.util.List;

/**
 * TODO comment this
 *
 * @author Bruno Aranda (baranda@ebi.ac.uk)
 * @version $Id$
 */
public class ImexImportDaoImpl extends HibernateBaseDaoImpl<ImexImport> implements ImexImportDao<ImexImport> {

    public ImexImportDaoImpl(Session session, IntactSession intactSession) {
        super(ImexImport.class, session, intactSession);
    }

    public List<ImexImport> getFailed() {
        Query query = getSession().createQuery("select imex from uk.ac.ebi.intact.model.meta.ImexImport imex where imex.status = :status");
        query.setParameter("status", ImexImportStatus.ERROR);

        return query.list();
    }

    public ImexImport getByPmid(String pmid) {
        return (ImexImport) getSession().createCriteria(getEntityClass())
                .add(Restrictions.eq("pmid", pmid)).uniqueResult();
    }

    public List<String> getAllOkPmids() {
        Query query = getSession().createQuery("select imex.pmid from uk.ac.ebi.intact.model.meta.ImexImport imex where imex.status = :status");
        query.setParameter("status", ImexImportStatus.OK);

        return query.list();
    }

    public List<String> getAllPmids() {
        return getSession().createQuery("select pmid from uk.ac.ebi.intact.model.meta.ImexImport").list();
    }
}