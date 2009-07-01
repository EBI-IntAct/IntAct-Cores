package uk.ac.ebi.intact.model.util;

import org.junit.*;
import static org.junit.Assert.*;
import uk.ac.ebi.intact.config.impl.SmallCvPrimer;
import uk.ac.ebi.intact.context.DataContext;
import uk.ac.ebi.intact.context.IntactContext;
import uk.ac.ebi.intact.core.persister.PersisterHelper;
import uk.ac.ebi.intact.core.unit.IntactBasicTestCase;
import uk.ac.ebi.intact.core.unit.IntactMockBuilder;
import uk.ac.ebi.intact.model.*;

import java.util.ArrayList;
import java.util.Collection;

public class CvObjectUtilsTest extends IntactBasicTestCase {

    @After
    public void end() throws Exception {
        // override default behaviour of closing IntactContext after each test
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        final DataContext dataContext = IntactContext.getCurrentInstance().getDataContext();
        SmallCvPrimer primer = new SmallCvPrimer(dataContext.getDaoFactory());

        dataContext.beginTransaction();
        primer.createCVs();
        dataContext.commitTransaction();

        // add some nucleic acid CVs
        CvDatabase uniprot = new IntactMockBuilder().createCvObject(CvDatabase.class, CvDatabase.UNIPROT_MI_REF, CvDatabase.UNIPROT);
        CvExperimentalRole expRoleUnsp = new IntactMockBuilder().createCvObject(CvExperimentalRole.class, CvExperimentalRole.UNSPECIFIED_PSI_REF, CvExperimentalRole.UNSPECIFIED);
        CvExperimentalRole expRoleBait = new IntactMockBuilder().createCvObject(CvExperimentalRole.class, CvExperimentalRole.BAIT_PSI_REF, CvExperimentalRole.BAIT);
        CvBiologicalRole bioRoleEnzyme = new IntactMockBuilder().createCvObject(CvBiologicalRole.class, CvBiologicalRole.ENZYME_PSI_REF, CvBiologicalRole.ENZYME);
        CvBiologicalRole bioRoleUnsp = new IntactMockBuilder().createCvObject(CvBiologicalRole.class, CvBiologicalRole.UNSPECIFIED_PSI_REF, CvBiologicalRole.UNSPECIFIED);

        PersisterHelper.saveOrUpdate(uniprot, expRoleUnsp, expRoleBait, bioRoleEnzyme, bioRoleUnsp);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        IntactContext.closeCurrentInstance();
    }

    @Test
    public void isNucleicAcidType() throws Exception {

        IntactMockBuilder mockBuilder = new IntactMockBuilder();

        //       f    g
        //      / \
        //     d   e
        //    / \/  \
        //   a   b   c

        final CvInteractorType a = mockBuilder.createCvObject( CvInteractorType.class, "MI:0001", "a" );
        final CvInteractorType b = mockBuilder.createCvObject( CvInteractorType.class, "MI:0002", "b" );
        final CvInteractorType c = mockBuilder.createCvObject( CvInteractorType.class, "MI:0003", "c" );
        final CvInteractorType d = mockBuilder.createCvObject( CvInteractorType.class, "MI:0004", "d" );
        final CvInteractorType e = mockBuilder.createCvObject( CvInteractorType.class, "MI:0005", "e" );
        final CvInteractorType f = mockBuilder.createCvObject( CvInteractorType.class, CvInteractorType.NUCLEIC_ACID_MI_REF, "f" );
        final CvInteractorType g = mockBuilder.createCvObject( CvInteractorType.class, "XX:1234", "g" );

        a.addParent( d );
        b.addParent( d );
        b.addParent( e );
        c.addParent( e );

        d.addParent( f );
        e.addParent( f );

        Assert.assertTrue( CvObjectUtils.isNucleicAcidType( a ) );
        Assert.assertTrue( CvObjectUtils.isNucleicAcidType( b ) );
        Assert.assertTrue( CvObjectUtils.isNucleicAcidType( c ) );
        Assert.assertTrue( CvObjectUtils.isNucleicAcidType( d ) );
        Assert.assertTrue( CvObjectUtils.isNucleicAcidType( e ) );
        Assert.assertTrue( CvObjectUtils.isNucleicAcidType( f ) );
        Assert.assertFalse( CvObjectUtils.isNucleicAcidType( g ) );

        Assert.assertFalse( CvObjectUtils.isProteinType( a ) );
        Assert.assertFalse( CvObjectUtils.isProteinType( b ) );
        Assert.assertFalse( CvObjectUtils.isProteinType( c ) );
        Assert.assertFalse( CvObjectUtils.isProteinType( d ) );
        Assert.assertFalse( CvObjectUtils.isProteinType( e ) );
        Assert.assertFalse( CvObjectUtils.isProteinType( f ) );
        Assert.assertFalse( CvObjectUtils.isProteinType( g ) );
    }

    @Test
    public void isChildOfType() throws Exception {

        IntactMockBuilder mockBuilder = new IntactMockBuilder();

        //       f    g
        //      / \
        //     d   e
        //    / \/  \
        //   a   b   c

        final CvInteractorType a = mockBuilder.createCvObject( CvInteractorType.class, "MI:0001", "a" );
        final CvInteractorType b = mockBuilder.createCvObject( CvInteractorType.class, "MI:0002", "b" );
        final CvInteractorType c = mockBuilder.createCvObject( CvInteractorType.class, "MI:0003", "c" );
        final CvInteractorType d = mockBuilder.createCvObject( CvInteractorType.class, "MI:0004", "d" );
        final CvInteractorType e = mockBuilder.createCvObject( CvInteractorType.class, "MI:0005", "e" );
        final CvInteractorType f = mockBuilder.createCvObject( CvInteractorType.class, "MI:0006", "f" );
        final CvInteractorType g = mockBuilder.createCvObject( CvInteractorType.class, "XX:1234", "g" );

        a.addParent( d );
        b.addParent( d );
        b.addParent( e );
        c.addParent( e );

        d.addParent( f );
        e.addParent( f );

        Assert.assertTrue( CvObjectUtils.isChildOfType( a, "MI:0006", true ) );
        Assert.assertFalse( CvObjectUtils.isChildOfType( a, "MI:0006", false ) );

        Assert.assertFalse( CvObjectUtils.isChildOfType( f, "MI:0001", true ) );
        Assert.assertFalse( CvObjectUtils.isChildOfType( f, "MI:0001", false ) );

        Assert.assertFalse( CvObjectUtils.isChildOfType( a, "MI:0003", true ) );
        Assert.assertFalse( CvObjectUtils.isChildOfType( a, "MI:0003", false ) );
        Assert.assertFalse( CvObjectUtils.isChildOfType( c, "MI:0001", true ) );
        Assert.assertFalse( CvObjectUtils.isChildOfType( c, "MI:0001", false ) );
    }

    @Test
    public void getChildrenMIs() throws Exception {

        IntactMockBuilder mockBuilder = new IntactMockBuilder();

        //       f    g
        //      / \
        //     d   e
        //    / \/  \
        //   a   b   c

        final CvInteractorType a = mockBuilder.createCvObject( CvInteractorType.class, "MI:0001", "a" );
        final CvInteractorType b = mockBuilder.createCvObject( CvInteractorType.class, "MI:0002", "b" );
        final CvInteractorType c = mockBuilder.createCvObject( CvInteractorType.class, "MI:0003", "c" );
        final CvInteractorType d = mockBuilder.createCvObject( CvInteractorType.class, "MI:0004", "d" );
        final CvInteractorType e = mockBuilder.createCvObject( CvInteractorType.class, "MI:0005", "e" );
        final CvInteractorType f = mockBuilder.createCvObject( CvInteractorType.class, "MI:0006", "f" );
        final CvInteractorType g = mockBuilder.createCvObject( CvInteractorType.class, "XX:1234", "g" );

        a.addParent( d );
        b.addParent( d );
        b.addParent( e );
        c.addParent( e );

        d.addParent( f );
        e.addParent( f );

        Assert.assertEquals( 1, CvObjectUtils.getChildrenMIs( a ).size() );
        Assert.assertEquals( 3, CvObjectUtils.getChildrenMIs( d ).size() );
        Assert.assertEquals( 6, CvObjectUtils.getChildrenMIs( f ).size() ); // b is here only once
        Assert.assertEquals( 1, CvObjectUtils.getChildrenMIs( g ).size() );
    }

    @Test
    public void getChildrenMIs_collection() throws Exception {

        IntactMockBuilder mockBuilder = new IntactMockBuilder();

        //       f    g
        //      / \
        //     d   e
        //    / \/  \
        //   a   b   c

        final CvInteractorType a = mockBuilder.createCvObject( CvInteractorType.class, "MI:0001", "a" );
        final CvInteractorType b = mockBuilder.createCvObject( CvInteractorType.class, "MI:0002", "b" );
        final CvInteractorType c = mockBuilder.createCvObject( CvInteractorType.class, "MI:0003", "c" );
        final CvInteractorType d = mockBuilder.createCvObject( CvInteractorType.class, "MI:0004", "d" );
        final CvInteractorType e = mockBuilder.createCvObject( CvInteractorType.class, "MI:0005", "e" );
        final CvInteractorType f = mockBuilder.createCvObject( CvInteractorType.class, "MI:0006", "f" );
        final CvInteractorType g = mockBuilder.createCvObject( CvInteractorType.class, "XX:1234", "g" );

        a.addParent( d );
        b.addParent( d );
        b.addParent( e );
        c.addParent( e );

        d.addParent( f );
        e.addParent( f );

        Collection<String> collected = new ArrayList<String>( );
        CvObjectUtils.getChildrenMIs( a, collected );
        Assert.assertEquals( 1, collected.size() );

        collected.clear();
        CvObjectUtils.getChildrenMIs( d, collected );
        Assert.assertEquals( 3, collected.size() );

        collected.clear();
        CvObjectUtils.getChildrenMIs( f, collected );
        Assert.assertEquals( 7, collected.size() ); // b is here twice as we use a List

        collected.clear();
        CvObjectUtils.getChildrenMIs( g, collected );
        Assert.assertEquals( 1, collected.size() );
    }

    @Test
    public void createCvObject() throws Exception {
        CvObject cv = getMockBuilder().createCvObject(CvDatabase.class, CvDatabase.PSI_MI_MI_REF, CvDatabase.PSI_MI);
        Assert.assertNotNull(cv.getIdentifier());
        Assert.assertEquals(1, cv.getXrefs().size());
    }

    @Test
    public void getPsiMiXref() throws Exception {
        CvObject cv = getMockBuilder().createCvObject(CvDatabase.class, CvDatabase.UNIPROT_MI_REF, CvDatabase.UNIPROT);
        Assert.assertEquals(CvDatabase.UNIPROT_MI_REF, CvObjectUtils.getPsiMiIdentityXref(cv).getPrimaryId());
    }

    @Test
    public void testGetPsiMiIdentityXref() throws Exception {
        assertFalse( 0 == getDaoFactory().getCvObjectDao().countAll() );

        CvDatabase uniprotKb = getIntactContext().getCvContext().getByMiRef( CvDatabase.class, CvDatabase.UNIPROT_MI_REF );

        CvObjectXref cvObjectXref = CvObjectUtils.getPsiMiIdentityXref( uniprotKb );

        assertNotNull( "The xref retuned should not be null", cvObjectXref );
        assertEquals( CvDatabase.UNIPROT_MI_REF, cvObjectXref.getPrimaryId() );
    }

    @Test
    public void testGetPsiMiIdentityXref_psiMiRef() throws Exception {
        assertFalse( 0 == getDaoFactory().getCvObjectDao().countAll() );

        CvXrefQualifier identityQual = getIntactContext().getCvContext().getByMiRef( CvXrefQualifier.class, CvXrefQualifier.IDENTITY_MI_REF );

        CvObjectXref cvObjectXref = CvObjectUtils.getPsiMiIdentityXref( identityQual );

        assertNotNull( "The xref retuned should not be null", cvObjectXref );
        assertEquals( CvXrefQualifier.IDENTITY_MI_REF, cvObjectXref.getPrimaryId() );

        CvXrefQualifier identityOfIdentityQual = cvObjectXref.getCvXrefQualifier();
        assertNotNull( identityOfIdentityQual );

        CvObjectXref cvIdentityOfIdentityXref = CvObjectUtils.getPsiMiIdentityXref( identityOfIdentityQual );
        assertNotNull( cvIdentityOfIdentityXref );
        assertEquals( CvXrefQualifier.IDENTITY_MI_REF, cvIdentityOfIdentityXref.getPrimaryId() );

    }

    @Test
    public void createRoleInfo_relevant() throws Exception {

        String labelUnspecified = CvExperimentalRole.UNSPECIFIED;
        String miUnspecified = CvExperimentalRole.UNSPECIFIED_PSI_REF;
        String labelExpDefined = CvExperimentalRole.BAIT;
        String miExpDefined = CvExperimentalRole.BAIT_PSI_REF;
        String labelBioDefined = CvBiologicalRole.ENZYME;
        String miBioDefined = CvBiologicalRole.ENZYME_PSI_REF;

        CvExperimentalRole expUnspecified = getIntactContext().getCvContext().getByMiRef( CvExperimentalRole.class, miUnspecified );
        CvExperimentalRole expDefined = getIntactContext().getCvContext().getByMiRef( CvExperimentalRole.class, miExpDefined );
        CvBiologicalRole bioUnspecified = getIntactContext().getCvContext().getByMiRef( CvBiologicalRole.class, miUnspecified );
        CvBiologicalRole bioDefined = getIntactContext().getCvContext().getByMiRef( CvBiologicalRole.class, miBioDefined );

        assertNotNull( expUnspecified );
        assertNotNull( expDefined );
        assertNotNull( bioDefined );
        assertNotNull( expDefined );

        // case 1: exp unspecified - bio neutral
        RoleInfo roleInfo1 = CvObjectUtils.createRoleInfo( expUnspecified, bioUnspecified );
        assertEquals( labelUnspecified, roleInfo1.getRelevantName() );
        assertEquals( miUnspecified, roleInfo1.getRelevantMi() );

        // case 2: exp unspecified - bio defined
        RoleInfo roleInfo2 = CvObjectUtils.createRoleInfo( expUnspecified, bioDefined );
        assertEquals( labelBioDefined, roleInfo2.getRelevantName() );
        assertEquals( miBioDefined, roleInfo2.getRelevantMi() );

        // case 3: exp defined - bio neutral
        RoleInfo roleInfo3 = CvObjectUtils.createRoleInfo( expDefined, bioUnspecified );
        assertEquals( labelExpDefined, roleInfo3.getRelevantName() );
        assertEquals( miExpDefined, roleInfo3.getRelevantMi() );

        // case 4: exp defined - bio defined
        String separator = RoleInfo.RELEVANT_SEPARATOR;
        RoleInfo roleInfo4 = CvObjectUtils.createRoleInfo( expDefined, bioDefined );
        assertEquals( labelExpDefined + separator + labelBioDefined, roleInfo4.getRelevantName() );
        assertEquals( miExpDefined + separator + miBioDefined, roleInfo4.getRelevantMi() );
    }
}
