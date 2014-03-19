package uk.ac.ebi.intact.jami.synchronizer.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import psidev.psi.mi.jami.model.*;
import uk.ac.ebi.intact.jami.context.SynchronizerContext;
import uk.ac.ebi.intact.jami.merger.IntactDbMergerIgnoringPersistentObject;
import uk.ac.ebi.intact.jami.model.extension.*;
import uk.ac.ebi.intact.jami.synchronizer.AbstractIntactDbSynchronizer;
import uk.ac.ebi.intact.jami.synchronizer.FinderException;
import uk.ac.ebi.intact.jami.synchronizer.PersisterException;
import uk.ac.ebi.intact.jami.synchronizer.SynchronizerException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Default synchronizer/finder for Ranges
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>27/01/14</pre>
 */

public class RangeSynchronizerTemplate<I extends AbstractIntactRange> extends AbstractIntactDbSynchronizer<Range, I> {
    private static final Log log = LogFactory.getLog(RangeSynchronizerTemplate.class);

    public RangeSynchronizerTemplate(SynchronizerContext context, Class<? extends I> intactClass){
        super(context, intactClass);
    }

    public I find(Range object) throws FinderException {
        return null;
    }

    public void synchronizeProperties(I object) throws FinderException, PersisterException, SynchronizerException {
        // prepare start position
        IntactPosition start;
        if (!(object.getStart() instanceof IntactPosition)){
            start = new IntactPosition(object.getStart().getStatus(), object.getStart().getStart(), object.getStart().getEnd());
        }
        else{
            start = (IntactPosition)object.getStart();
        }
        // prepare start status
        CvTerm startStatus = getContext().getRangeStatusSynchronizer().synchronize(start.getStatus(), true);
        start.setStatus(startStatus);

        // prepare end position
        IntactPosition end;
        if (!(object.getEnd() instanceof IntactPosition)){
            end = new IntactPosition(object.getEnd().getStatus(), object.getEnd().getStart(), object.getEnd().getEnd());
        }
        else{
            end = (IntactPosition)object.getEnd();
        }
        // prepare end status
        CvTerm endStatus = getContext().getRangeStatusSynchronizer().synchronize(end.getStatus(), true);
        end.setStatus(endStatus);
        // reset positions
        object.setPositions(start, end);

        // prepare ResultingSequence
        boolean experimental = getIntactClass().equals(ExperimentalRange.class);
        if (object.getResultingSequence() != null
                && (experimental && !(object.getResultingSequence() instanceof ExperimentalResultingSequence))){
            ResultingSequence reSeq = object.getResultingSequence();
            object.setResultingSequence(new ExperimentalResultingSequence(reSeq.getOriginalSequence(), reSeq.getNewSequence()));
            object.getResultingSequence().getXrefs().addAll(reSeq.getXrefs());

            // prepare xrefs of resulting sequence
            prepareXrefs((ExperimentalResultingSequence)object.getResultingSequence(), experimental);

        }
        else if (object.getResultingSequence() != null
                && (!experimental && !(object.getResultingSequence() instanceof ModelledResultingSequence))){
            ResultingSequence reSeq = object.getResultingSequence();
            object.setResultingSequence(new ModelledResultingSequence(reSeq.getOriginalSequence(), reSeq.getNewSequence()));
            object.getResultingSequence().getXrefs().addAll(reSeq.getXrefs());

            // prepare xrefs of resulting sequence
            prepareXrefs((ExperimentalResultingSequence)object.getResultingSequence(), experimental);
        }

        // synchronize participant
        if (object.getParticipant() != null){
            Participant synchronizedParticipant = getContext().getParticipantSynchronizer().synchronize(object.getParticipant(), false);
            object.setParticipant(synchronizedParticipant);
        }
    }

    public void clearCache() {
        // nothing to do
    }

    @Override
    protected Object extractIdentifier(I object) {
        return object.getAc();
    }

    @Override
    protected I instantiateNewPersistentInstance(Range object, Class<? extends I> intactClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return intactClass.getConstructor(Position.class, Position.class, Boolean.class, ResultingSequence.class).newInstance(object.getStart(), object.getEnd(), object.isLink(), object.getResultingSequence());
    }

    @Override
    protected void storeInCache(Range originalObject, I persistentObject, I existingInstance) {
        // nothing to do
    }

    @Override
    protected I fetchObjectFromCache(Range object) {
        return null;
    }

    @Override
    protected boolean isObjectStoredInCache(Range object) {
        return false;
    }

    protected void prepareXrefs(ExperimentalResultingSequence intactObj, boolean experimental) throws FinderException, PersisterException, SynchronizerException {
        if (intactObj.areXrefsInitialized()){
            List<Xref> xrefsToPersist = new ArrayList<Xref>(intactObj.getXrefs());
            for (Xref xref : xrefsToPersist){
                // do not persist or merge xrefs because of cascades
                Xref objRef = experimental ? getContext().getExperimentalResultingSequenceXrefSynchronizer().synchronize(xref, false) : getContext().getModelledResultingSequenceXrefSynchronizer().synchronize(xref, false);
                // we have a different instance because needed to be synchronized
                if (objRef != xref){
                    intactObj.getXrefs().remove(xref);
                    intactObj.getXrefs().add(objRef);
                }
            }
        }
    }

    @Override
    protected void initialiseDefaultMerger() {
        super.setIntactMerger(new IntactDbMergerIgnoringPersistentObject<Range, I>(this));
    }
}