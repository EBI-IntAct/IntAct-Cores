/**
 * Generated by Agitar build: Agitator Version 1.0.4.000276 (Build date: Mar 27, 2007) [1.0.4.000276]
 * JDK Version: 1.5.0_09
 *
 * Generated on 04-Apr-2007 08:26:37
 * Time to generate: 00:07.573 seconds
 *
 */

package agitar.uk.ac.ebi.intact.modelt;

import uk.ac.ebi.intact.model.*;

import com.agitar.lib.junit.AgitarTestCase;
import uk.ac.ebi.intact.model.util.AnnotatedObjectUtils;

public class CvDevelopmentalStageAgitarTest extends AgitarTestCase {

    static Class TARGET_CLASS = CvDevelopmentalStage.class;

    public void testConstructor() throws Throwable {
        Institution owner = new Institution( "testCvDevelopmentalStageShortLabel" );
        CvDevelopmentalStage cvDevelopmentalStage = new CvDevelopmentalStage( owner, "testCvDevelopmentalStageShortLabel" );
        assertEquals( "cvDevelopmentalStage.xrefs.size()", 0, cvDevelopmentalStage.xrefs.size() );
        assertEquals( "cvDevelopmentalStage.getAliases().size()", 0, cvDevelopmentalStage.getAliases().size() );
        assertEquals( "cvDevelopmentalStage.getEvidences().size()", 0, cvDevelopmentalStage.getEvidences().size() );
        assertEquals( "cvDevelopmentalStage.shortLabel", "testCvDevelopmentalS", cvDevelopmentalStage.getShortLabel() );
        assertEquals( "cvDevelopmentalStage.annotations.size()", 0, cvDevelopmentalStage.annotations.size() );
        assertSame( "cvDevelopmentalStage.getOwner()", owner, cvDevelopmentalStage.getOwner() );
        assertEquals( "cvDevelopmentalStage.references.size()", 0, cvDevelopmentalStage.references.size() );
    }

    public void testConstructorThrowsIllegalArgumentException() throws Throwable {
        try {
            new CvDevelopmentalStage( new Institution( "testCvDevelopmentalStageShortLabel" ), "" );
            fail( "Expected IllegalArgumentException to be thrown" );
        } catch ( IllegalArgumentException ex ) {
            assertEquals( "ex.getMessage()", "Must define a non empty short label", ex.getMessage() );
            assertThrownBy( AnnotatedObjectUtils.class, ex );
        }
    }

    public void testConstructorThrowsNullPointerException() throws Throwable {
        try {
            new CvDevelopmentalStage( new Institution( "testCvDevelopmentalStageShortLabel" ), null );
            fail( "Expected NullPointerException to be thrown" );
        } catch ( NullPointerException ex ) {
            assertEquals( "ex.getMessage()", "Must define a non null short label", ex.getMessage() );
            assertThrownBy( AnnotatedObjectUtils.class, ex );
        }
    }
}

