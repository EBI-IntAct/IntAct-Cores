/**
 * Generated by Agitar build: Agitator Version 1.0.4.000276 (Build date: Mar 27, 2007) [1.0.4.000276]
 * JDK Version: 1.5.0_09
 *
 * Generated on 04-Apr-2007 08:27:58
 * Time to generate: 00:53.743 seconds
 *
 */

package agitar.uk.ac.ebi.intact.modelt;

import com.agitar.lib.junit.AgitarTestCase;
import uk.ac.ebi.intact.model.*;
import uk.ac.ebi.intact.model.util.CvObjectUtils;

import java.util.HashSet;

public class AliasAgitarTest extends AgitarTestCase {

    static Class TARGET_CLASS = Alias.class;

    public void testClone() throws Throwable {
        Alias experimentAlias = new ExperimentAlias( new Institution( "testAliasShortLabel2" ), new Experiment( new Institution( "testAliasShortLabel1" ), "testAliasShortLabel", null ), new CvAliasType( new Institution( "testAliasShortLabel" ), "testAliasShortLabel" ), "testAliasName" );
        ExperimentAlias result = ( ExperimentAlias ) experimentAlias.clone();
        assertNull( "result.getParent()", result.getParent() );
        assertNull( "(ExperimentAlias) experimentAlias.getParent()", experimentAlias.getParent() );
        assertNull( "(ExperimentAlias) experimentAlias.parentAc", getPrivateField( experimentAlias, "parentAc" ) );
    }

    public void testEquals() throws Throwable {
        CvAliasType cvAliasType = new CvAliasType( new Institution( "testAliasShortLabel" ), "testAliasShortLabel" );
        CvObjectAlias o = new CvObjectAlias( null, new CvInteraction( null, "testAliasShortLabel" ), cvAliasType, "testAliasName" );
        Alias publicationAlias = new PublicationAlias( new Institution( "testAliasShortLabel2" ), new Publication( new Institution( "testAliasShortLabel1" ), "218" ), cvAliasType, "testAliasName" );
        publicationAlias.setCvAliasType( null );
        boolean result = publicationAlias.equals( o );
        assertFalse( "result", result );
    }

//    public void testEquals1() throws Throwable {
//        Institution anOwner = (Institution) Mockingbird.getProxyObject(Institution.class);
//        Feature feature = (Feature) Mockingbird.getProxyObject(Feature.class);
//        CvAliasType cvAliasType = (CvAliasType) Mockingbird.getProxyObject(CvAliasType.class);
//        Mockingbird.enterRecordingMode();
//        Mockingbird.setReturnValue(feature.getAc(), "testString");
//        Mockingbird.enterTestMode();
//        Alias featureAlias = new FeatureAlias(anOwner, feature, cvAliasType, "testAliasName");
//        Mockingbird.enterTestMode();
//        boolean result = featureAlias.equals("");
//        assertFalse("result", result);
//        assertInvoked(feature, "getAc");
//    }

    public void testEquals2() throws Throwable {
        Institution owner = new Institution( "testAliasShortLabel" );
        Alias bioSourceAlias = new BioSourceAlias( owner, new BioSource( owner, "testAliasShortLabel1", "49514" ), new CvAliasType( new Institution( "testAliasShortLabel2" ), "testAliasShortLabel" ), "testAliasName" );
        bioSourceAlias.setCvAliasType( null );
        Alias o = new BioSourceAlias( new Institution( "testAlias\nShortLabel" ), new BioSource( new Institution( "testAliasShortLabel1" ), "testAliasShortLabel", "77296" ), new CvAliasType( new Institution( "testAlias\rShortLabel" ), "testAliasShortLabel1" ), "testAliasName1" );
        o.setCvAliasType( null );
        boolean result = bioSourceAlias.equals( o );
        assertFalse( "result", result );
    }

    public void testEquals3() throws Throwable {
        CvAliasType cvAliasType = new CvAliasType( null, "testString" );
        CvAliasType cvAliasType2 = new CvAliasType( null, "testString" );
        Alias interactorAlias = new InteractorAlias( new Institution( "testAliasShortLabel1" ), new Complex(), cvAliasType, "testString" );
        boolean result = interactorAlias.equals( new PublicationAlias( new Institution( "testAliasShortLabel2" ), new Publication( new Institution( "testAliasShortLabel" ), "218" ), cvAliasType2, "testString" ) );
        assertTrue( "result", result );
        assertSame( "(InteractorAlias) interactorAlias.getCvAliasType()", cvAliasType, interactorAlias.getCvAliasType() );
    }

    public void testEquals4() throws Throwable {
        CvAliasType cvAliasType = new CvAliasType( new Institution( "testAliasShortLabel1" ), "testAliasShortLabel1" );
        Alias cvObjectAlias = new CvObjectAlias( new Institution( "testAlias\nShortLabel" ), new CvInteraction( new Institution( "testAlias\tShortLabel" ), "testAliasShortLabel" ), cvAliasType, "testAliasName" );
        boolean result = cvObjectAlias.equals( new PublicationAlias( new Institution( "testAlias\rShortLabel" ), new Publication( new Institution( "testAliasShortLabel" ), "218" ), new CvAliasType( new Institution( "testAliasShortLabel2" ), "testAliasShortLabel" ), "testAliasName" ) );
        assertFalse( "result", result );
        assertSame( "(CvObjectAlias) cvObjectAlias.getCvAliasType()", cvAliasType, cvObjectAlias.getCvAliasType() );
    }

//    public void testEquals5() throws Throwable {
//        Publication publication = new Publication( null, "218" );
//        CvAliasType cvAliasType = new CvAliasType( null, "testString" );
//        Alias componentAlias = new ComponentAlias( new Institution( "testAliasShortLabel1" ), new Component( new Institution( "testAlias\rShortLabel" ), new InteractionImpl( new HashSet(), new CvInteractionType( new Institution( "testAliasShortLabel" ), "testAliasShortLabel" ), "testAliasShortLabel", null ), new Complex(), new CvExperimentalRole( new Institution( "testAliasShortLabel2" ), "testAliasShortLabel" ) ), null, "testAliasName" );
//        componentAlias.setCvAliasType( cvAliasType );
//        boolean result = componentAlias.equals( new PublicationAlias( new Institution( "testAlias\nShortLabel" ), publication, new CvAliasType( new Institution( "testAlias\tShortLabel" ), "testString" ), "testAliasName1" ) );
//        assertFalse( "result", result );
//        assertSame( "(ComponentAlias) componentAlias.getCvAliasType()", cvAliasType, componentAlias.getCvAliasType() );
//    }

    public void testEquals6() throws Throwable {
        Alias o = new InteractorAlias( new Institution( "testAliasShortLabel1" ), new Complex(), new CvAliasType( new Institution( "testAliasShortLabel" ), "testAliasShortLabel" ), "testAliasName" );
        boolean result = o.equals( o );
        assertTrue( "result", result );
    }

//    public void testHashCode() throws Throwable {
//        Institution anOwner = (Institution) Mockingbird.getProxyObject(Institution.class);
//        Feature feature = (Feature) Mockingbird.getProxyObject(Feature.class);
//        CvAliasType cvAliasType = (CvAliasType) Mockingbird.getProxyObject(CvAliasType.class);
//        Mockingbird.enterRecordingMode();
//        Mockingbird.setReturnValue(feature.getAc(), "testString");
//        Mockingbird.enterTestMode();
//        Alias featureAlias = new FeatureAlias(anOwner, feature, cvAliasType, "testAliasName");
//        featureAlias.setCvAliasType(null);
//        featureAlias.setName(null);
//        Mockingbird.enterTestMode();
//        int result = featureAlias.hashCode();
//        assertEquals("result", 29, result);
//        assertNull("(FeatureAlias) featureAlias.getCvAliasType()", featureAlias.getCvAliasType());
//        assertNull("(FeatureAlias) featureAlias.getName()", featureAlias.getName());
//        assertInvoked(feature, "getAc");
//    }

    public void testHashCode1() throws Throwable {
        CvAliasType cvAliasType = new CvAliasType( new Institution( "testAliasShortLabel2" ), "testAliasShortLabel" );
        Alias bioSourceAlias = new BioSourceAlias( new Institution( "testAliasShortLabel1" ), new BioSource( new Institution( "testAliasShortLabel" ), "testAliasShortLabel", "77296" ), cvAliasType, "testAliasName" );
        int result = bioSourceAlias.hashCode();
        assertEquals( "result", -1105068509, result );
        assertSame( "(BioSourceAlias) bioSourceAlias.getCvAliasType()", cvAliasType, bioSourceAlias.getCvAliasType() );
        assertEquals( "(BioSourceAlias) bioSourceAlias.getName()", "testAliasName", bioSourceAlias.getName() );
    }

    public void testHashCode2() throws Throwable {
        Alias interactorAlias = new InteractorAlias( new Institution( "testAliasShortLabel" ), new Complex(), null, "testAliasName" );
        int result = interactorAlias.hashCode();
        assertEquals( "result", -2025472046, result );
        assertNull( "(InteractorAlias) interactorAlias.getCvAliasType()", interactorAlias.getCvAliasType() );
        assertEquals( "(InteractorAlias) interactorAlias.getName()", "testAliasName", interactorAlias.getName() );
    }

//    public void testSetCvAliasType() throws Throwable {
//        Interactor interactor = new SmallMoleculeImpl( "testAliasShortLabel", null, null );
//        Institution owner = new Institution( "testAliasShortLabel" );
//        CvAliasType cvAliasType = new CvAliasType( owner, "testAliasShortLabel" );
//        Alias componentAlias = new ComponentAlias( owner, new Component( new Institution( "testAliasShortLabel2" ), new InteractionImpl( new ArrayList( 100 ), ( CvInteractionType ) null, ( CvInteractorType ) null, "testAliasShortLabel", new Institution( "testAlias\rShortLabel" ) ), interactor, new CvExperimentalRole( new Institution( "testAliasShortLabel1" ), "testAliasShortLabel" ) ), cvAliasType, "testAliasName" );
//        componentAlias.setCvAliasType( cvAliasType );
//        assertSame( "(ComponentAlias) componentAlias.getCvAliasType()", cvAliasType, componentAlias.getCvAliasType() );
//    }

    public void testSetName() throws Throwable {
        Alias cvObjectAlias = new CvObjectAlias( new Institution( "testAliasShortLabel1" ), new CvFuzzyType( new Institution( "testAliasShortLabel" ), "testAliasShortLabel" ), new CvAliasType( new Institution( "testAliasShortLabel2" ), "testAliasShortLabel" ), "testAliasName" );
        cvObjectAlias.setName( "XXXXXXXXXXXXXXXXXXXXXXX\nXXXXXXX\r \r" );
        assertEquals( "(CvObjectAlias) cvObjectAlias.getName()", "XXXXXXXXXXXXXXXXXXXXXXX\nXXXXXX", cvObjectAlias.getName() );
    }

    public void testSetName1() throws Throwable {
        Alias publicationAlias = new PublicationAlias( new Institution( "testAliasShortLabel" ), new Publication( new Institution( "testAliasShortLabel2" ), "218" ), new CvAliasType( new Institution( "testAliasShortLabel1" ), "testAliasShortLabel" ), "testAliasName" );
        publicationAlias.setName( " \r\nXX   XXXXXXXXXXX  XX XX  X XX " );
        assertEquals( "(PublicationAlias) publicationAlias.getName()", "XX   XXXXXXXXXXX  XX XX  X XX", publicationAlias.getName() );
    }

    public void testSetName2() throws Throwable {
        Institution owner = new Institution( "testAliasShortLabel1" );
        Alias interactorAlias = new InteractorAlias( owner, new InteractionImpl( new HashSet(), new CvInteractionType( new Institution( "testAliasShortLabel" ), "testAliasShortLabel" ), "testAliasShortLabel", null ), new CvAliasType( owner, "testAliasShortLabel" ), "testAliasName" );
        interactorAlias.setName( "30CharactersXXXXXXXXXXXXXXXXXX" );
        assertEquals( "(InteractorAlias) interactorAlias.getName()", "30CharactersXXXXXXXXXXXXXXXXXX", interactorAlias.getName() );
    }

    public void testSetName3() throws Throwable {
        Alias interactorAlias = new InteractorAlias( new Institution( "testAliasShortLabel2" ), new NucleicAcidImpl( new Institution( "testAliasShortLabel1" ), new BioSource( new Institution( "testAlias\rShortLabel" ), "testAliasShortLabel", "77296" ), "testAliasShortLabel", new CvInteractorType( new Institution( "testAliasShortLabel" ), "testAliasShortLabel" ) ), new CvAliasType( new Institution( "testAlias\nShortLabel" ), "testAliasShortLabel" ), "testAliasName" );
        interactorAlias.setName( null );
        assertNull( "(InteractorAlias) interactorAlias.getName()", interactorAlias.getName() );
    }

    public void testSetParent() throws Throwable {
        Alias bioSourceAlias = new BioSourceAlias( new Institution( "testAliasShortLabel2" ), new BioSource( new Institution( "testAliasShortLabel" ), "testAliasShortLabel", "77296" ), new CvAliasType( new Institution( "testAliasShortLabel1" ), "testAliasShortLabel" ), "testAliasName" );
        AnnotatedObject parent = new CvExperimentalRole( new Institution( "testAlias\rShortLabel" ), "testAliasShortLabel" );
        bioSourceAlias.setParent( parent );
        assertSame( "(BioSourceAlias) bioSourceAlias.getParent()", parent, bioSourceAlias.getParent() );
    }

    public void testSetParentAc() throws Throwable {
        Institution owner = new Institution( "testAliasShortLabel" );
        CvAliasType cvAliasType = new CvAliasType( owner, "testAliasShortLabel" );
        Alias bioSourceAlias = new BioSourceAlias( new Institution( "testAliasShortLabel1" ), new BioSource( owner, "testAliasShortLabel", "77296" ), cvAliasType, "testAliasName" );
        bioSourceAlias.setParentAc( "testAliasParentAc" );
        assertEquals( "(BioSourceAlias) bioSourceAlias.parentAc", "testAliasParentAc", getPrivateField( bioSourceAlias, "parentAc" ) );
    }

//    public void testToString() throws Throwable {
//        Institution anOwner = (Institution) Mockingbird.getProxyObject(Institution.class);
//        Publication publication = (Publication) Mockingbird.getProxyObject(Publication.class);
//        CvAliasType cvAliasType = (CvAliasType) Mockingbird.getProxyObject(CvAliasType.class);
//        Mockingbird.enterRecordingMode();
//        Mockingbird.setReturnValue(publication.getAc(), "");
//        Mockingbird.enterTestMode();
//        Alias publicationAlias = new PublicationAlias(anOwner, publication, cvAliasType, "testAliasName");
//        CvAliasType cvAliasType2 = (CvAliasType) Mockingbird.getProxyObject(CvAliasType.class);
//        publicationAlias.setCvAliasType(cvAliasType2);
//        Mockingbird.enterRecordingMode();
//        Mockingbird.setReturnValue(cvAliasType2.getShortLabel(), "19CharactersXXXXXXX");
//        Mockingbird.enterTestMode();
//        String result = publicationAlias.toString();
//        assertEquals("result", "Alias[name: testAliasName, type: 19CharactersXXXXXXX]", result);
//        assertInvoked(cvAliasType2, "getShortLabel");
//        assertInvoked(publication, "getAc");
//    }

    public void testToString1() throws Throwable {
        Institution owner = new Institution( "testAliasShortLabel" );
        Alias interactorAlias = new InteractorAlias( owner, new ProteinImpl( owner, null, "testAliasShortLabel", null ), new CvAliasType( new Institution( "testAliasShortLabel1" ), "testAliasShortLabel" ), "testAliasName" );
        interactorAlias.setCvAliasType( null );
        String result = interactorAlias.toString();
        assertEquals( "result", "Alias[name: testAliasName, type: ]", result );
    }

    public void testHashCodeThrowsNullPointerException() throws Throwable {
        Alias bioSourceAlias = new BioSourceAlias( new Institution( "testAliasShortLabel2" ), new BioSource( new Institution( "testAliasShortLabel" ), "testAliasShortLabel", "77296" ), new CvAliasType( new Institution( "testAliasShortLabel1" ), "testAliasShortLabel" ), "testAliasName" );
        CvAliasType cvAliasType = new CvAliasType( new Institution( "testAlias\rShortLabel" ), "testAliasShortLabel1" );
        cvAliasType.addXref( new CvObjectXref( null, null, "testAliasAPrimaryId", "testAliasASecondaryId", "testAliasADatabaseRelease", new CvXrefQualifier( null, "testAliasShortLabel" ) ) );
        bioSourceAlias.setCvAliasType( cvAliasType );
        try {
            bioSourceAlias.hashCode();
            fail( "Expected NullPointerException to be thrown" );
        } catch ( NullPointerException ex ) {
            assertEquals( "ex.getMessage()", "cvObject should not be null", ex.getMessage() );
            assertThrownBy( CvObjectUtils.class, ex );
            assertEquals( "(BioSourceAlias) bioSourceAlias.getName()", "testAliasName", bioSourceAlias.getName() );
            assertSame( "(BioSourceAlias) bioSourceAlias.getCvAliasType()", cvAliasType, bioSourceAlias.getCvAliasType() );
        }
    }
}

