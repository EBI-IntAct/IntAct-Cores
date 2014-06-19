package uk.ac.ebi.intact.jami.context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.ac.ebi.intact.jami.dao.IntactDao;
import uk.ac.ebi.intact.jami.lifecycle.LifeCycleManager;

/**
 * The {@code IntactContext} class is the general context for the IntAct Core API.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 */
@Component(value = "intactJamiContext")
public class IntactContext {

    private static final Log log = LogFactory.getLog(IntactContext.class);

    @Autowired
    private IntactConfiguration jamiIntactConfiguration;

    @Autowired
    private UserContext jamiUserContext;

    @Autowired
    private IntactDao intactDao;

    @Autowired
    private LifeCycleManager jamiLifeCycleManager;

    public IntactContext() {
    }

    public IntactConfiguration getIntactConfiguration() {
        return jamiIntactConfiguration;
    }

    public void setIntactConfiguration(IntactConfiguration jamiIntactConfiguration) {
        this.jamiIntactConfiguration = jamiIntactConfiguration;
    }

    public UserContext getUserContext() {
        return jamiUserContext;
    }

    public void setUserContext(UserContext userContext) {
        this.jamiUserContext = userContext;
    }

    public IntactDao getIntactDao() {
        return intactDao;
    }

    public void setIntactDao(IntactDao intactDao) {
        this.intactDao = intactDao;
    }

    public LifeCycleManager getJamiLifeCycleManager() {
        return jamiLifeCycleManager;
    }

    public void setJamiLifeCycleManager(LifeCycleManager jamiLifeCycleManager) {
        this.jamiLifeCycleManager = jamiLifeCycleManager;
    }
}
