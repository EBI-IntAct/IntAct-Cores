package uk.ac.ebi.intact.jami.synchronizer.impl;

import psidev.psi.mi.jami.model.VariableParameterValue;
import psidev.psi.mi.jami.model.VariableParameterValueSet;
import uk.ac.ebi.intact.jami.context.SynchronizerContext;
import uk.ac.ebi.intact.jami.merger.IntactDbMergerIgnoringPersistentObject;
import uk.ac.ebi.intact.jami.model.extension.IntactVariableParameterValueSet;
import uk.ac.ebi.intact.jami.synchronizer.AbstractIntactDbSynchronizer;
import uk.ac.ebi.intact.jami.synchronizer.FinderException;
import uk.ac.ebi.intact.jami.synchronizer.PersisterException;
import uk.ac.ebi.intact.jami.synchronizer.SynchronizerException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Synchronizer for variable parameter value set
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>31/01/14</pre>
 */

public class VariableParameterValueSetSynchronizer extends AbstractIntactDbSynchronizer<VariableParameterValueSet, IntactVariableParameterValueSet> {

    public VariableParameterValueSetSynchronizer(SynchronizerContext context) {
        super(context, IntactVariableParameterValueSet.class);
    }

    @Override
    protected Object extractIdentifier(IntactVariableParameterValueSet object) {
        return object.getId();
    }

    @Override
    protected IntactVariableParameterValueSet instantiateNewPersistentInstance(VariableParameterValueSet object, Class<? extends IntactVariableParameterValueSet> intactClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return new IntactVariableParameterValueSet(object);
    }

    public IntactVariableParameterValueSet find(VariableParameterValueSet object) throws FinderException {
        return null;
    }

    public void synchronizeProperties(IntactVariableParameterValueSet object) throws FinderException, PersisterException, SynchronizerException {
        if (object.areVariableParameterValuesInitialized()){
            List<VariableParameterValue> valuesToPersist = new ArrayList<VariableParameterValue>(object);
            for (VariableParameterValue value : valuesToPersist){
                VariableParameterValue valueCheck = getContext().getVariableParameterValueSynchronizer().synchronize(value, false);
                // we have a different instance because needed to be synchronized
                if (valueCheck != value){
                    object.remove(value);
                    object.add(valueCheck);
                }
            }
        }
    }

    public void clearCache() {
        // nothing to do
    }

    @Override
    protected void initialiseDefaultMerger() {
        super.setIntactMerger(new IntactDbMergerIgnoringPersistentObject<VariableParameterValueSet, IntactVariableParameterValueSet>(this));
    }
}