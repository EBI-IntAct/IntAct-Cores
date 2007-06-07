/**
 * Generated by Agitar build: Agitator Version 1.0.4.000276 (Build date: Mar 27, 2007) [1.0.4.000276]
 * JDK Version: 1.5.0_09
 *
 * Generated on 04-Apr-2007 08:22:52
 * Time to generate: 00:07.620 seconds
 *
 */

package agitar.uk.ac.ebi.intact.modelt; import uk.ac.ebi.intact.model.*;

import com.agitar.lib.junit.AgitarTestCase;
import uk.ac.ebi.intact.model.util.AnnotatedObjectUtils;

public class CvProductRoleAgitarTest extends AgitarTestCase {

    static Class TARGET_CLASS = CvProductRole.class;

    public void testConstructor() throws Throwable {
        Institution owner = new Institution( "testCvProductRoleShortLabel" );
        CvProductRole cvProductRole = new CvProductRole( owner, "testCvProductRoleShortLabel" );
        assertEquals( "cvProductRole.xrefs.size()", 0, cvProductRole.xrefs.size() );
        assertEquals( "cvProductRole.getAliases().size()", 0, cvProductRole.getAliases().size() );
        assertEquals( "cvProductRole.getEvidences().size()", 0, cvProductRole.getEvidences().size() );
        assertEquals( "cvProductRole.shortLabel", "testCvProductRoleSho", cvProductRole.getShortLabel() );
        assertEquals( "cvProductRole.annotations.size()", 0, cvProductRole.annotations.size() );
        assertSame( "cvProductRole.getOwner()", owner, cvProductRole.getOwner() );
        assertEquals( "cvProductRole.references.size()", 0, cvProductRole.references.size() );
    }

    public void testConstructorThrowsIllegalArgumentException() throws Throwable {
        try {
            new CvProductRole( new Institution( "testCvProductRoleShortLabel" ), "" );
            fail( "Expected IllegalArgumentException to be thrown" );
        } catch ( IllegalArgumentException ex ) {
            assertEquals( "ex.getMessage()", "Must define a non empty short label", ex.getMessage() );
            assertThrownBy( AnnotatedObjectUtils.class, ex );
        }
    }

    public void testConstructorThrowsNullPointerException() throws Throwable {
        try {
            new CvProductRole( new Institution( "testCvProductRoleShortLabel" ), null );
            fail( "Expected NullPointerException to be thrown" );
        } catch ( NullPointerException ex ) {
            assertEquals( "ex.getMessage()", "Must define a non null short label", ex.getMessage() );
            assertThrownBy( AnnotatedObjectUtils.class, ex );
        }
    }
}

