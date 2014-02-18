package uk.ac.ebi.intact.jami.synchronizer;

import org.apache.commons.collections.map.IdentityMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.clone.InteractionCloner;
import uk.ac.ebi.intact.jami.merger.IntactInteractionEvidenceMergerEnrichOnly;
import uk.ac.ebi.intact.jami.model.extension.*;
import uk.ac.ebi.intact.jami.synchronizer.impl.*;
import uk.ac.ebi.intact.jami.utils.IntactUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Default synchronizer for interaction evidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/01/14</pre>
 */

public class IntactInteractionEvidenceSynchronizer extends AbstractIntactDbSynchronizer<InteractionEvidence, IntactInteractionEvidence> {

    private Map<InteractionEvidence, IntactInteractionEvidence> persistedObjects;

    private IntactDbSynchronizer<Annotation, InteractionAnnotation> annotationSynchronizer;
    private IntactDbSynchronizer<Xref, InteractionXref> xrefSynchronizer;
    private IntactDbSynchronizer<CvTerm, IntactCvTerm> interactionTypeSynchronizer;
    private IntactDbSynchronizer<Checksum, InteractionChecksum> checksumSynchronizer;
    private IntactDbSynchronizer<Entity, AbstractIntactEntity> participantEvidenceSynchronizer;
    private IntactDbSynchronizer<Parameter, InteractionEvidenceParameter> parameterSynchronizer;
    private IntactDbSynchronizer<Confidence, InteractionEvidenceConfidence> confidenceSynchronizer;
    private IntactDbSynchronizer<VariableParameterValueSet, IntactVariableParameterValueSet> variableValueSetSynchronizer;

    private static final Log log = LogFactory.getLog(IntactInteractionEvidenceSynchronizer.class);

    public IntactInteractionEvidenceSynchronizer(EntityManager entityManager){
        super(entityManager, IntactInteractionEvidence.class);
        // to keep track of persisted cvs
        this.persistedObjects = new IdentityMap();
    }

    public IntactInteractionEvidence find(InteractionEvidence interaction) throws FinderException {
        if (this.persistedObjects.containsKey(interaction)){
            return this.persistedObjects.get(interaction);
        }
        else{
            return null;
        }
    }

    public IntactInteractionEvidence persist(IntactInteractionEvidence object) throws FinderException, PersisterException, SynchronizerException {
        // only persist if not already done
        if (!this.persistedObjects.containsKey(object)){
            return this.persistedObjects.get(object);
        }

        IntactInteractionEvidence persisted = super.persist(object);
        this.persistedObjects.put(object, persisted);

        return persisted;
    }

    @Override
    public IntactInteractionEvidence synchronize(InteractionEvidence object, boolean persist) throws FinderException, PersisterException, SynchronizerException {
        // only synchronize if not already done
        if (!this.persistedObjects.containsKey(object)){
            return this.persistedObjects.get(object);
        }

        IntactInteractionEvidence persisted = super.synchronize(object, persist);
        this.persistedObjects.put(object, persisted);

        return persisted;
    }

    public void synchronizeProperties(IntactInteractionEvidence intactInteraction) throws FinderException, PersisterException, SynchronizerException {
        // then check shortlabel/synchronize
        prepareAndSynchronizeShortLabel(intactInteraction);
        // then check interaction detection method
        prepareInteractionType(intactInteraction);
        // then check participant identification method
        prepareConfidences(intactInteraction);
        // then check organism
        prepareParameters(intactInteraction);
        // then check annotations
        prepareAnnotations(intactInteraction);
        // then check xrefs
        prepareXrefs(intactInteraction);
        // then check interactions
        prepareParticipants(intactInteraction);
        // then check variable parameters
        prepareVariableParametersValues(intactInteraction);
    }

    public void clearCache() {
        this.persistedObjects.clear();
        getChecksumSynchronizer().clearCache();
        getXrefSynchronizer().clearCache();
        getAnnotationSynchronizer().clearCache();
        getParameterSynchronizer().clearCache();
        getConfidenceSynchronizer().clearCache();
        getInteractionTypeSynchronizer().clearCache();
        getParticipantEvidenceSynchronizer().clearCache();
        getVariableValueSetSynchronizer().clearCache();
    }

    public IntactDbSynchronizer<Annotation, InteractionAnnotation> getAnnotationSynchronizer() {
        if (this.annotationSynchronizer == null){
            this.annotationSynchronizer = new IntactAnnotationSynchronizer<InteractionAnnotation>(getEntityManager(), InteractionAnnotation.class);
        }
        return annotationSynchronizer;
    }

    public void setAnnotationSynchronizer(IntactDbSynchronizer<Annotation, InteractionAnnotation> annotationSynchronizer) {
        this.annotationSynchronizer = annotationSynchronizer;
    }

    public IntactDbSynchronizer<Xref, InteractionXref> getXrefSynchronizer() {
        if (this.xrefSynchronizer == null){
            this.xrefSynchronizer = new IntactXrefSynchronizer<InteractionXref>(getEntityManager(), InteractionXref.class);
        }
        return xrefSynchronizer;
    }

    public void setXrefSynchronizer(IntactDbSynchronizer<Xref, InteractionXref> xrefSynchronizer) {
        this.xrefSynchronizer = xrefSynchronizer;
    }

    public IntactDbSynchronizer<CvTerm, IntactCvTerm> getInteractionTypeSynchronizer() {
        if (this.interactionTypeSynchronizer == null){
            this.interactionTypeSynchronizer = new IntactCvTermSynchronizer(getEntityManager(), IntactUtils.INTERACTION_TYPE_OBJCLASS);
        }
        return interactionTypeSynchronizer;
    }

    public void setInteractionTypeSynchronizer(IntactDbSynchronizer<CvTerm, IntactCvTerm> interactionTypeSynchronizer) {
        this.interactionTypeSynchronizer = interactionTypeSynchronizer;
    }

    public IntactDbSynchronizer<Checksum, InteractionChecksum> getChecksumSynchronizer() {
        if (this.checksumSynchronizer == null){
            this.checksumSynchronizer = new IntactChecksumSynchronizer<InteractionChecksum>(getEntityManager(), InteractionChecksum.class);
        }
        return checksumSynchronizer;
    }

    public void setChecksumSynchronizer(IntactDbSynchronizer<Checksum, InteractionChecksum> checksumSynchronizer) {
        this.checksumSynchronizer = checksumSynchronizer;
    }

    public IntactDbSynchronizer<Entity, AbstractIntactEntity> getParticipantEvidenceSynchronizer() {
        if (this.participantEvidenceSynchronizer == null){
            this.participantEvidenceSynchronizer = new IntActEntitySynchronizer(getEntityManager());
        }
        return participantEvidenceSynchronizer;
    }

    public void setParticipantEvidenceSynchronizer(IntactDbSynchronizer<Entity, AbstractIntactEntity> participantEvidenceSynchronizer) {
        this.participantEvidenceSynchronizer = participantEvidenceSynchronizer;
    }

    public IntactDbSynchronizer<Parameter, InteractionEvidenceParameter> getParameterSynchronizer() {
        if (this.parameterSynchronizer == null){
            this.parameterSynchronizer = new IntactParameterSynchronizer<Parameter, InteractionEvidenceParameter>(getEntityManager(), InteractionEvidenceParameter.class);
        }
        return parameterSynchronizer;
    }

    public void setParameterSynchronizer(IntactDbSynchronizer<Parameter, InteractionEvidenceParameter> parameterSynchronizer) {
        this.parameterSynchronizer = parameterSynchronizer;
    }

    public IntactDbSynchronizer<Confidence, InteractionEvidenceConfidence> getConfidenceSynchronizer() {
        if (this.confidenceSynchronizer == null){
            this.confidenceSynchronizer = new IntactConfidenceSynchronizer<Confidence, InteractionEvidenceConfidence>(getEntityManager(), InteractionEvidenceConfidence.class);
        }
        return confidenceSynchronizer;
    }

    public void setConfidenceSynchronizer(IntactDbSynchronizer<Confidence, InteractionEvidenceConfidence> confidenceSynchronizer) {
        this.confidenceSynchronizer = confidenceSynchronizer;
    }

    public IntactDbSynchronizer<VariableParameterValueSet, IntactVariableParameterValueSet> getVariableValueSetSynchronizer() {
        if (this.variableValueSetSynchronizer == null){
            this.variableValueSetSynchronizer = new IntactVariableParameterValueSetSynchronizer(getEntityManager());
        }
        return variableValueSetSynchronizer;
    }

    public void setVariableValueSetSynchronizer(IntactDbSynchronizer<VariableParameterValueSet, IntactVariableParameterValueSet> variableValueSetSynchronizer) {
        this.variableValueSetSynchronizer = variableValueSetSynchronizer;
    }

    @Override
    protected Object extractIdentifier(IntactInteractionEvidence object) {
        return object.getAc();
    }

    @Override
    protected IntactInteractionEvidence instantiateNewPersistentInstance(InteractionEvidence object, Class<? extends IntactInteractionEvidence> intactClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        IntactInteractionEvidence inter = new IntactInteractionEvidence();
        InteractionCloner.copyAndOverrideInteractionEvidenceProperties(object, inter, false, false);
        return inter;
    }

    protected void prepareVariableParametersValues(IntactInteractionEvidence intactInteraction) throws PersisterException, FinderException, SynchronizerException {

        if (intactInteraction.areVariableParameterValuesInitialized()){
            Collection<VariableParameterValueSet> parametersToPersist = new ArrayList<VariableParameterValueSet>(intactInteraction.getVariableParameterValues());
            for (VariableParameterValueSet param : parametersToPersist){
                // do not persist or merge parameters because of cascades
                VariableParameterValueSet expParam = getVariableValueSetSynchronizer().synchronize(param, false);
                // we have a different instance because needed to be synchronized
                if (expParam != param){
                    intactInteraction.getVariableParameterValues().remove(param);
                    intactInteraction.getVariableParameterValues().add(expParam);
                }
            }
        }
    }

    protected void prepareParticipants(IntactInteractionEvidence intactInteraction) throws PersisterException, FinderException, SynchronizerException {
        if (intactInteraction.areParticipantsInitialized()){
            Collection<ParticipantEvidence> participantsToPersist = new ArrayList<ParticipantEvidence>(intactInteraction.getParticipants());
            for (ParticipantEvidence participant : participantsToPersist){
                // reinit parent
                participant.setInteraction(intactInteraction);
                // do not persist or merge participants because of cascades
                ParticipantEvidence expPart = (ParticipantEvidence)getParticipantEvidenceSynchronizer().synchronize(participant, false);
                // we have a different instance because needed to be synchronized
                if (expPart != participant){
                    intactInteraction.getParticipants().remove(participant);
                    intactInteraction.addParticipant(expPart);
                }
            }
        }
    }

    protected void prepareParameters(IntactInteractionEvidence intactInteraction) throws PersisterException, FinderException, SynchronizerException {
        if (intactInteraction.areParametersInitialized()){
            Collection<Parameter> parametersToPersist = new ArrayList<Parameter>(intactInteraction.getParameters());
            for (Parameter param : parametersToPersist){
                // do not persist or merge parameters because of cascades
                Parameter expPar = getParameterSynchronizer().synchronize(param, false);
                // we have a different instance because needed to be synchronized
                if (expPar != param){
                    intactInteraction.getParameters().remove(param);
                    intactInteraction.getParameters().add(expPar);
                }
            }
        }
    }

    protected void prepareInteractionType(IntactInteractionEvidence intactInteraction) throws PersisterException, FinderException, SynchronizerException {
        CvTerm type = intactInteraction.getInteractionType();
        if (type != null){
            intactInteraction.setInteractionType(getInteractionTypeSynchronizer().synchronize(type, true));
        }
    }

    protected void prepareXrefs(IntactInteractionEvidence intactInteraction) throws FinderException, PersisterException, SynchronizerException {
        if (intactInteraction.areXrefsInitialized()){
            List<Xref> xrefsToPersist = new ArrayList<Xref>(intactInteraction.getXrefs());
            for (Xref xref : xrefsToPersist){
                // do not persist or merge xrefs because of cascades
                Xref expRef = getXrefSynchronizer().synchronize(xref, false);
                // we have a different instance because needed to be synchronized
                if (expRef != xref){
                    intactInteraction.getXrefs().remove(xref);
                    intactInteraction.getXrefs().add(expRef);
                }
            }
        }
    }

    protected void prepareAnnotations(IntactInteractionEvidence intactInteraction) throws FinderException, PersisterException, SynchronizerException {
        if (intactInteraction.areAnnotationsInitialized()){
            List<Annotation> annotationsToPersist = new ArrayList<Annotation>(intactInteraction.getAnnotations());
            for (Annotation annotation : annotationsToPersist){
                // do not persist or merge annotations because of cascades
                Annotation expAnnotation = getAnnotationSynchronizer().synchronize(annotation, false);
                // we have a different instance because needed to be synchronized
                if (expAnnotation != annotation){
                    intactInteraction.getAnnotations().remove(annotation);
                    intactInteraction.getAnnotations().add(expAnnotation);
                }
            }
        }
    }

    protected void prepareConfidences(IntactInteractionEvidence intactInteraction) throws FinderException, PersisterException, SynchronizerException {
        if (intactInteraction.areConfidencesInitialized()){
            List<Confidence> confsToPersist = new ArrayList<Confidence>(intactInteraction.getConfidences());
            for (Confidence confidence : confsToPersist){
                // do not persist or merge confidences because of cascades
                Confidence expConf = getConfidenceSynchronizer().synchronize(confidence, false);
                // we have a different instance because needed to be synchronized
                if (expConf != confidence){
                    intactInteraction.getConfidences().remove(confidence);
                    intactInteraction.getConfidences().add(expConf);
                }
            }
        }
    }

    protected void prepareAndSynchronizeShortLabel(IntactInteractionEvidence intactInteraction) throws SynchronizerException {
        // first initialise shortlabel if not done
        if (intactInteraction.getShortName() == null){
            intactInteraction.setShortName(IntactUtils.generateAutomaticInteractionEvidenceShortlabelFor(intactInteraction, IntactUtils.MAX_SHORT_LABEL_LEN));
        }

        // then synchronize with database
        boolean first = true;
        String name;
        List<String> existingInteractions;
        do{
            name = intactInteraction.getShortName().trim().toLowerCase();
            existingInteractions = Collections.EMPTY_LIST;
            String originalName = first ? name : IntactUtils.excludeLastNumberInShortLabel(name);

            if (first){
                first = false;
            }
            // don't truncate all bait-prey label, keeps at least one character for bait, one for - and one for prey
            else if (originalName.contains("-") && originalName.length() > 3){
                int lastIndex = originalName.lastIndexOf("-");
                String label1 = originalName.substring(0, lastIndex);
                String label2 = originalName.substring(lastIndex+1);

                if (label1.length() >= label2.length() && label1.length() > 1){
                    label1 = label1.substring(0, label1.length() - 1);
                }
                else if (label2.length() > label1.length() && label2.length() > 1){
                    label2 = label2.substring(0, label2.length() - 1);
                }
                name = label1 + "-" + label2;
            }
            else if (originalName.length() > 1){
                name = originalName.substring(0, originalName.length() - 1);
            }
            else {
                break;
            }

            // check if short name already exist, if yes, synchronize with existing label
            Query query = getEntityManager().createQuery("select i.shortName from IntactInteractionEvidence i " +
                    "where i.shortName = :name or i.shortName like :nameWithSuffix"
                    + (intactInteraction.getAc() != null ? "and i.ac <> :interAc" : ""));
            query.setParameter("name", name);
            query.setParameter("nameWithSuffix", name+"-%");
            if (intactInteraction.getAc() != null){
                query.setParameter("interAc", intactInteraction.getAc());
            }
            existingInteractions = query.getResultList();
            String nameInSync = IntactUtils.synchronizeShortlabel(name, existingInteractions, IntactUtils.MAX_SHORT_LABEL_LEN, true);
            intactInteraction.setShortName(nameInSync);
        }
        while(name.length() > 6 && !existingInteractions.isEmpty());
    }

    @Override
    protected void initialiseDefaultMerger() {
        super.setIntactMerger(new IntactInteractionEvidenceMergerEnrichOnly());
    }
}
