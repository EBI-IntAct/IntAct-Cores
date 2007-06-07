/**
 * Generated by Agitar build: Agitator Version 1.0.4.000276 (Build date: Mar 27, 2007) [1.0.4.000276]
 * JDK Version: 1.5.0_09
 *
 * Generated on 04-Apr-2007 08:30:47
 * Time to generate: 02:36.046 seconds
 *
 */

package agitar.uk.ac.ebi.intact.modelt; import com.agitar.lib.junit.AgitarTestCase;
import com.agitar.lib.mockingbird.Mockingbird;
import uk.ac.ebi.intact.model.*;
import uk.ac.ebi.intact.model.util.AnnotatedObjectUtils;

import java.util.ArrayList;
import java.util.Collection;

public class FeatureAgitarTest extends AgitarTestCase {

    static Class TARGET_CLASS = Feature.class;

//    public void testConstructor() throws Throwable {
//        Interaction interaction = new InteractionImpl( new ArrayList(), null, "testFeatureShortLabel", new Institution( "testFeatureShortLabel" ) );
//        Institution owner = new Institution( "testFeatureShortLabel1" );
//        Component component = new Component( owner, interaction, new ProteinImpl( null, null, "testFeatureShortLabel", new CvInteractorType( null, "testFeatureShortLabel" ) ), new CvExperimentalRole( new Institution( "testFeatureShortLabel2" ), "testFeatureShortLabel" ) );
//        Institution owner2 = new Institution( "testFeature\rShortLabel" );
//        CvFeatureType type = new CvFeatureType( owner, "testFeatureShortLabel" );
//        Feature feature = new Feature( owner2, "testFeatureShortLabel", component, type );
//        assertEquals( "feature.getRanges().size()", 0, feature.getRanges().size() );
//        assertEquals( "feature.xrefs.size()", 0, feature.xrefs.size() );
//        assertEquals( "feature.getAliases().size()", 0, feature.getAliases().size() );
//        assertEquals( "feature.getEvidences().size()", 0, feature.getEvidences().size() );
//        assertEquals( "feature.shortLabel", "testFeatureShortLabe", feature.getShortLabel() );
//        assertSame( "feature.getComponent()", component, feature.getComponent() );
//        assertEquals( "feature.annotations.size()", 0, feature.annotations.size() );
//        assertSame( "feature.getOwner()", owner2, feature.getOwner() );
//        assertSame( "feature.getCvFeatureType()", type, feature.getCvFeatureType() );
//        assertEquals( "feature.references.size()", 0, feature.references.size() );
//    }
//
//    public void testAddRange() throws Throwable {
//        Feature feature = new Feature( null, "testFeatureShortLabel", new Component( new Institution( "testFeatureShortLabel" ), "testFeatureShortLabel", new InteractionImpl( new ArrayList( 100 ), new ArrayList( 1000 ), new CvInteractionType( new Institution( "testFeatureShortLabel" ), "testFeatureShortLabel" ), "testFeatureShortLabel", new Institution( "testFeature\rShortLabel" ) ), new SmallMoleculeImpl( "testFeatureShortLabel", new Institution( "testFeature\nShortLabel" ), new CvInteractorType( new Institution( "testFeatureShortLabel1" ), "testFeatureShortLabel" ) ), new CvExperimentalRole( new Institution( "testFeature\tShortLabel" ), "testFeatureShortLabel" ) ), new CvFeatureType( new Institution( "testFeatureShortLabel2" ), "testFeatureShortLabel" ) );
//        Range range = new Range( new Institution( "testFeatureShortLabel1" ), -1, 100, "testFeatureSeq" );
//        feature.addRange( range );
//        assertEquals( "feature.getRanges().size()", 1, feature.getRanges().size() );
//        assertSame( "feature.getRanges().get(0)", range, ( ( List ) feature.getRanges() ).get( 0 ) );
//    }
//
//    public void testAddRange1() throws Throwable {
//        Feature feature = new Feature( null, "testFeatureShortLabel", new Component( new Institution( "testFeatureShortLabel" ), "testFeatureShortLabel", new InteractionImpl( new ArrayList( 100 ), new ArrayList( 1000 ), new CvInteractionType( new Institution( "testFeatureShortLabel" ), "testFeatureShortLabel" ), "testFeatureShortLabel", new Institution( "testFeature\rShortLabel" ) ), new SmallMoleculeImpl( "testFeatureShortLabel", new Institution( "testFeature\nShortLabel" ), new CvInteractorType( new Institution( "testFeatureShortLabel1" ), "testFeatureShortLabel" ) ), new CvExperimentalRole( new Institution( "testFeature\tShortLabel" ), "testFeatureShortLabel" ) ), new CvFeatureType( new Institution( "testFeatureShortLabel2" ), "testFeatureShortLabel" ) );
//        Range range = new Range( new Institution( "testFeatureShortLabel1" ), -1, 100, "testFeatureSeq" );
//        feature.addRange( range );
//        feature.addRange( range );
//        assertEquals( "feature.getRanges().size()", 1, feature.getRanges().size() );
//    }
//
//    public void testClone() throws Throwable {
//        Component component = new Component( new Institution( "testFeatureShortLabel" ), "testFeatureShortLabel", new InteractionImpl( new ArrayList( 100 ), new ArrayList( 1000 ), new CvInteractionType( new Institution( "testFeatureShortLabel" ), "testFeatureShortLabel" ), "testFeatureShortLabel", new Institution( "testFeature\rShortLabel" ) ), new SmallMoleculeImpl( "testFeatureShortLabel", new Institution( "testFeature\nShortLabel" ), new CvInteractorType( new Institution( "testFeatureShortLabel1" ), "testFeatureShortLabel" ) ), new CvExperimentalRole( new Institution( "testFeature\tShortLabel" ), "testFeatureShortLabel" ) );
//        Feature feature = new Feature( null, "testFeatureShortLabel", component, new CvFeatureType( new Institution( "testFeatureShortLabel2" ), "testFeatureShortLabel" ) );
//        feature.addRange( new Range( new Institution( "testFeatureShortLabel1" ), -1, 100, "testFeatureSeq" ) );
//        Feature result = ( Feature ) feature.clone();
//        assertEquals( "result.getAliases().size()", 0, result.getAliases().size() );
//        assertEquals( "feature.getRanges().size()", 1, feature.getRanges().size() );
//        assertEquals( "feature.shortLabel", "testFeatureShortLabe", feature.getShortLabel() );
//        assertNull( "feature.getBoundDomain()", feature.getBoundDomain() );
//        assertSame( "feature.getComponent()", component, feature.getComponent() );
//        assertEquals( "feature.annotations.size()", 0, feature.annotations.size() );
//    }

//    public void testEquals() throws Throwable {
//        Interaction interaction = new InteractionImpl( new ArrayList(), null, "testFeatureShortLabel", new Institution( "testFeatureShortLabel" ) );
//        boolean result = new Feature( new Institution( "testFeature\rShortLabel" ), "testFeatureShortLabel", new Component( new Institution( "testFeatureShortLabel1" ), interaction, new ProteinImpl( null, null, "testFeatureShortLabel", new CvInteractorType( null, "testFeatureShortLabel" ) ), new CvExperimentalRole( new Institution( "testFeatureShortLabel2" ), "testFeatureShortLabel" ) ), new CvFeatureType( new Institution( "testFeature\nShortLabel" ), "testFeatureShortLabel" ) ).equals( null );
//        assertFalse( "result", result );
//    }

    public void testEquals1() throws Throwable {
        Feature o = new Feature( ( Institution ) Mockingbird.getProxyObject( Institution.class ), "testFeatureShortLabel", ( Component ) Mockingbird.getProxyObject( Component.class ), ( CvFeatureType ) Mockingbird.getProxyObject( CvFeatureType.class ) );
        Mockingbird.enterTestMode();
        boolean result = o.equals( o );
        assertTrue( "result", result );
    }

    public void testGetAliases() throws Throwable {
        Feature feature = new Feature( ( Institution ) Mockingbird.getProxyObject( Institution.class ), "testFeatureShortLabel", ( Component ) Mockingbird.getProxyObject( Component.class ), ( CvFeatureType ) Mockingbird.getProxyObject( CvFeatureType.class ) );
        Mockingbird.enterTestMode();
        ArrayList result = ( ArrayList ) feature.getAliases();
        assertEquals( "result.size()", 0, result.size() );
    }

    public void testGetAnnotations() throws Throwable {
        Feature feature = new Feature( ( Institution ) Mockingbird.getProxyObject( Institution.class ), "testFeatureShortLabel", ( Component ) Mockingbird.getProxyObject( Component.class ), ( CvFeatureType ) Mockingbird.getProxyObject( CvFeatureType.class ) );
        Mockingbird.enterTestMode();
        ArrayList result = ( ArrayList ) feature.getAnnotations();
        assertEquals( "result.size()", 0, result.size() );
    }

//    public void testGetXrefs() throws Throwable {
//        Interaction interaction = new InteractionImpl( new ArrayList(), null, "testFeatureShortLabel", new Institution( "testFeatureShortLabel" ) );
//        ArrayList result = ( ArrayList ) new Feature( new Institution( "testFeature\rShortLabel" ), "testFeatureShortLabel", new Component( new Institution( "testFeatureShortLabel1" ), interaction, new ProteinImpl( null, null, "testFeatureShortLabel", new CvInteractorType( null, "testFeatureShortLabel" ) ), new CvExperimentalRole( new Institution( "testFeatureShortLabel2" ), "testFeatureShortLabel" ) ), new CvFeatureType( new Institution( "testFeature\nShortLabel" ), "testFeatureShortLabel" ) ).getXrefs();
//        assertEquals( "result.size()", 0, result.size() );
//    }

    public void testRemoveRange() throws Throwable {
        Feature feature = new Feature( ( Institution ) Mockingbird.getProxyObject( Institution.class ), "testFeatureShortLabel", ( Component ) Mockingbird.getProxyObject( Component.class ), ( CvFeatureType ) Mockingbird.getProxyObject( CvFeatureType.class ) );
        Range range = ( Range ) Mockingbird.getProxyObject( Range.class );
        Mockingbird.enterTestMode();
        feature.removeRange( range );
        assertEquals( "feature.getRanges().size()", 0, feature.getRanges().size() );
    }

//    public void testSetBoundDomain() throws Throwable {
//        Interaction interaction = new InteractionImpl( new ArrayList(), null, "testFeatureShortLabel", new Institution( "testFeatureShortLabel1" ) );
//        Component component = new Component( new Institution( "testFeatureShortLabel2" ), interaction, new ProteinImpl( null, null, "testFeatureShortLabel", new CvInteractorType( null, "testFeatureShortLabel" ) ), new CvExperimentalRole( new Institution( "testFeature\rShortLabel" ), "testFeatureShortLabel" ) );
//        CvFeatureType type = new CvFeatureType( new Institution( "testFeature\nShortLabel" ), "testFeatureShortLabel" );
//        Feature feature = new Feature( new Institution( "testFeatureShortLabel" ), "testFeatureShortLabel", component, type );
//        Feature feature2 = new Feature( null, "testFeatureShortLabel1", component, type );
//        feature.setBoundDomain( feature2 );
//        assertSame( "feature.getBoundDomain()", feature2, feature.getBoundDomain() );
//    }

    public void testSetComponent() throws Throwable {
        Feature feature = new Feature( null, "testFeatureShortLabel", ( Component ) Mockingbird.getProxyObject( Component.class ), ( CvFeatureType ) Mockingbird.getProxyObject( CvFeatureType.class ) );
        Component component = ( Component ) Mockingbird.getProxyObject( Component.class );
        Mockingbird.enterTestMode();
        feature.setComponent( component );
        assertSame( "feature.getComponent()", component, feature.getComponent() );
    }

//    public void testSetComponentForClone() throws Throwable {
//        CvInteractorType type = new CvInteractorType( null, "testFeatureShortLabel" );
//        Object[] objects = new Object[3];
//        Collection experiments = Arrays.asList( objects );
//        Interaction interaction = new InteractionImpl( experiments, ( CvInteractionType ) null, ( CvInteractorType ) null, "testFeatureShortLabel", ( Institution ) null );
//        Institution owner = new Institution( "testFeature\nShortLabel" );
//        Interactor interactor = new NucleicAcidImpl( owner, new BioSource( null, "testFeatureShortLabel", "11609" ), "testFeatureShortLabel", type );
//        Feature feature = new Feature( null, "testFeatureShortLabel", new Component( new Institution( "testFeatureShortLabel" ), "testFeatureShortLabel", new InteractionImpl( new ArrayList( 100 ), new ArrayList( 1000 ), new CvInteractionType( new Institution( "testFeatureShortLabel" ), "testFeatureShortLabel" ), "testFeatureShortLabel", new Institution( "testFeature\rShortLabel" ) ), new SmallMoleculeImpl( "testFeatureShortLabel", owner, new CvInteractorType( new Institution( "testFeatureShortLabel1" ), "testFeatureShortLabel1" ) ), new CvExperimentalRole( new Institution( "testFeature\tShortLabel" ), "testFeatureShortLabel" ) ), new CvFeatureType( new Institution( "testFeatureShortLabel2" ), "testFeatureShortLabel" ) );
//        Component component = new Component( new Institution( "testFeatureShortLabel1" ), interaction, interactor, new CvExperimentalRole( null, "testFeatureShortLabel1" ) );
//        super.callPrivateMethod("uk.ac.ebi.intact.model.Feature", "setComponentForClone", new Class[]{Component.class}, feature, new Object[]{component} );
////        feature.setComponentForClone( component );
//        assertSame( "feature.getComponent().getInteractor()", interactor, feature.getComponent().getInteractor() );
//        assertSame( "feature.getComponent().getInteraction().getExperiments()", experiments, ( ( InteractionImpl ) feature.getComponent().getInteraction() ).getExperiments() );
//        assertNull( "feature.getComponent().getInteraction().getCvInteractionType()", ( ( InteractionImpl ) feature.getComponent().getInteraction() ).getCvInteractionType() );
//        assertSame( "feature.getComponent()", component, feature.getComponent() );
//    }

    public void testSetRanges() throws Throwable {
        Feature feature = new Feature( ( Institution ) Mockingbird.getProxyObject( Institution.class ), "testFeatureShortLabel", ( Component ) Mockingbird.getProxyObject( Component.class ), ( CvFeatureType ) Mockingbird.getProxyObject( CvFeatureType.class ) );
        Collection ranges = ( Collection ) Mockingbird.getProxyObject( Collection.class );
        Mockingbird.enterTestMode();
        feature.setRanges( ranges );
        assertSame( "feature.getRanges()", ranges, feature.getRanges() );
    }

    public void testConstructorThrowsIllegalArgumentException() throws Throwable {
        Institution owner = ( Institution ) Mockingbird.getProxyObject( Institution.class );
        Component component = ( Component ) Mockingbird.getProxyObject( Component.class );
        CvFeatureType type = ( CvFeatureType ) Mockingbird.getProxyObject( CvFeatureType.class );
        Mockingbird.enterTestMode();
        try {
            new Feature( owner, "", component, type );
            fail( "Expected IllegalArgumentException to be thrown" );
        } catch ( IllegalArgumentException ex ) {
            assertEquals( "ex.getMessage()", "Must define a non empty short label", ex.getMessage() );
            assertThrownBy( AnnotatedObjectUtils.class, ex );
        }
    }

    public void testConstructorThrowsNullPointerException() throws Throwable {
        try {
            new Feature( new Institution( "testFeatureShortLabel1" ), "testFeatureShortLabel", null, new CvFeatureType( new Institution( "testFeatureShortLabel" ), "testFeatureShortLabel" ) );
            fail( "Expected NullPointerException to be thrown" );
        } catch ( NullPointerException ex ) {
            assertEquals( "ex.getMessage()", "Cannot create Feature without a Component!", ex.getMessage() );
            assertThrownBy( Feature.class, ex );
        }
    }

    public void testConstructorThrowsNullPointerException1() throws Throwable {
        Institution owner = ( Institution ) Mockingbird.getProxyObject( Institution.class );
        Component component = ( Component ) Mockingbird.getProxyObject( Component.class );
        Mockingbird.enterTestMode();
        try {
            new Feature( owner, "testFeatureShortLabel", component, null );
            fail( "Expected NullPointerException to be thrown" );
        } catch ( NullPointerException ex ) {
            assertEquals( "ex.getMessage()", "Must have a CvFeatureType to create a Feature!", ex.getMessage() );
            assertThrownBy( Feature.class, ex );
        }
    }

    public void testAddRangeThrowsNullPointerException() throws Throwable {
        Feature feature = new Feature( ( Institution ) Mockingbird.getProxyObject( Institution.class ), "testFeatureShortLabel", ( Component ) Mockingbird.getProxyObject( Component.class ), ( CvFeatureType ) Mockingbird.getProxyObject( CvFeatureType.class ) );
        feature.setRanges( null );
        Range range = ( Range ) Mockingbird.getProxyObject( Range.class );
        Mockingbird.enterTestMode();
        try {
            feature.addRange( range );
            fail( "Expected NullPointerException to be thrown" );
        } catch ( NullPointerException ex ) {
            assertNull( "ex.getMessage()", ex.getMessage() );
            assertThrownBy( Feature.class, ex );
            assertNull( "feature.getRanges()", feature.getRanges() );
        }
    }

    public void testRemoveRangeThrowsNullPointerException() throws Throwable {
        Feature feature = new Feature( ( Institution ) Mockingbird.getProxyObject( Institution.class ), "testFeatureShortLabel", ( Component ) Mockingbird.getProxyObject( Component.class ), ( CvFeatureType ) Mockingbird.getProxyObject( CvFeatureType.class ) );
        feature.setRanges( null );
        Range range = ( Range ) Mockingbird.getProxyObject( Range.class );
        Mockingbird.enterTestMode();
        try {
            feature.removeRange( range );
            fail( "Expected NullPointerException to be thrown" );
        } catch ( NullPointerException ex ) {
            assertNull( "ex.getMessage()", ex.getMessage() );
            assertThrownBy( Feature.class, ex );
            assertNull( "feature.getRanges()", feature.getRanges() );
        }
    }
}

