package uk.ac.ebi.intact.jami.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import psidev.psi.mi.jami.model.Source;
import uk.ac.ebi.intact.jami.dao.IntactDao;
import uk.ac.ebi.intact.jami.synchronizer.FinderException;
import uk.ac.ebi.intact.jami.synchronizer.PersisterException;
import uk.ac.ebi.intact.jami.synchronizer.SynchronizerException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Source service
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/02/14</pre>
 */
@Service
@Lazy
public class SourceService implements IntactService<Source>{

    @Autowired
    private IntactDao intactDAO;
    private IntactQuery intactQuery;

    @Transactional(propagation = Propagation.REQUIRED)
    public long countAll() {
        if (this.intactQuery != null){
            return this.intactDAO.getSourceDao().countByQuery(this.intactQuery.getCountQuery(), this.intactQuery.getQueryParameters());
        }
        return this.intactDAO.getSourceDao().countAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterator<Source> iterateAll() {
        return new IntactQueryResultIterator<Source>(this);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Source> fetchIntactObjects(int first, int max) {
        if (this.intactQuery != null){
            return new ArrayList<Source>(this.intactDAO.getSourceDao().getByQuery(intactQuery.getQuery(), intactQuery.getQueryParameters(), first, max));
        }
        return new ArrayList<Source>(this.intactDAO.getSourceDao().getAll("ac", first, max));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(Source object) throws PersisterException, FinderException, SynchronizerException {
        // we can synchronize the complex with the database now
        intactDAO.getSynchronizerContext().getSourceSynchronizer().synchronize(object, true);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(Collection<? extends Source> objects) throws SynchronizerException, PersisterException, FinderException {
        for (Source source : objects){
            saveOrUpdate(source);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Source object) throws PersisterException, FinderException, SynchronizerException {

        this.intactDAO.getSynchronizerContext().getSourceSynchronizer().delete(object);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Collection<? extends Source> objects) throws SynchronizerException, PersisterException, FinderException {
        for (Source source : objects){
            delete(source);
        }
    }

    public IntactQuery getIntactQuery() {
        return intactQuery;
    }

    public void setIntactQuery(IntactQuery intactQuery) {
        this.intactQuery = intactQuery;
    }
}
