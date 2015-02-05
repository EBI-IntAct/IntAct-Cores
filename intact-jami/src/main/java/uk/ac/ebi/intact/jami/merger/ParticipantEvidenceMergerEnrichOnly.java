package uk.ac.ebi.intact.jami.merger;

import psidev.psi.mi.jami.enricher.OrganismEnricher;
import psidev.psi.mi.jami.enricher.ParticipantEvidenceEnricher;
import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import uk.ac.ebi.intact.jami.model.extension.IntactParticipantEvidence;

/**
 * participant evidence merger based on the jami entity enricher.
 * It will only add missing info, it does not override anything
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/01/14</pre>
 */

public class ParticipantEvidenceMergerEnrichOnly<E extends ParticipantEvidence, I extends IntactParticipantEvidence>
        extends ParticipantMergerEnrichOnly<E, I, FeatureEvidence> implements ParticipantEvidenceEnricher<E> {

    public ParticipantEvidenceMergerEnrichOnly() {
        super((Class<I>)IntactParticipantEvidence.class);
    }

    public ParticipantEvidenceMergerEnrichOnly(ParticipantEvidenceEnricher<E> basicEnricher) {
        super((Class<I>)IntactParticipantEvidence.class, basicEnricher);
    }

    public ParticipantEvidenceMergerEnrichOnly(Class<I> intactClass) {
        super(intactClass);
    }

    public ParticipantEvidenceMergerEnrichOnly(Class<I> intactClass, ParticipantEvidenceEnricher<E> basicEnricher) {
        super(intactClass, basicEnricher);
    }

    public OrganismEnricher getOrganismEnricher() {
        return null;
    }

    @Override
    public void setOrganismEnricher(OrganismEnricher enricher) {

    }

    @Override
    protected void mergeOtherProperties(I obj1, I obj2) {
        super.mergeOtherProperties(obj1, obj2);

        // reset parent
        obj2.setInteraction(obj1.getInteraction());
    }
}
