package uk.ac.ebi.intact.jami.merger;

import psidev.psi.mi.jami.enricher.EntityPoolEnricher;
import psidev.psi.mi.jami.enricher.ParticipantEnricher;
import psidev.psi.mi.jami.enricher.impl.FullEntityPoolUpdater;
import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.ModelledEntityPool;
import psidev.psi.mi.jami.model.ModelledFeature;
import uk.ac.ebi.intact.jami.model.extension.IntactModelledEntityPool;

import java.util.Comparator;

/**
 * Modelled entity pool merger based on the jami entity enricher.
 * It will only add missing info, it does not override anything
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/01/14</pre>
 */

public class IntactModelledEntityPoolMergerOverride
        extends IntactModelledParticipantMergerOverride<ModelledEntityPool, IntactModelledEntityPool> implements EntityPoolEnricher<ModelledEntityPool, ModelledFeature>{

    public IntactModelledEntityPoolMergerOverride() {
        super(IntactModelledEntityPool.class, new FullEntityPoolUpdater<ModelledEntityPool, ModelledFeature>());
    }

    public IntactModelledEntityPoolMergerOverride(EntityPoolEnricher<ModelledEntityPool, ModelledFeature> basicEnricher) {
        super(IntactModelledEntityPool.class, basicEnricher);
    }

    @Override
    protected EntityPoolEnricher<ModelledEntityPool, ModelledFeature> getBasicEnricher() {
        return (EntityPoolEnricher<ModelledEntityPool, ModelledFeature>)super.getBasicEnricher();
    }

    public void setParticipantEnricher(ParticipantEnricher interactorEnricher) {
         getBasicEnricher().setParticipantEnricher(interactorEnricher);
    }

    public ParticipantEnricher getParticipantEnricher() {
        return getBasicEnricher().getParticipantEnricher();
    }

    public void setParticipantComparator(Comparator<Entity> interactorComparator) {
        getBasicEnricher().setParticipantComparator(interactorComparator);
    }

    public Comparator<Entity> getParticipantComparator() {
        return getBasicEnricher().getParticipantComparator();
    }
}
