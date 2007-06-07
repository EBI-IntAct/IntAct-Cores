/**
 * Generated by Agitar build: Agitator Version 1.0.4.000276 (Build date: Mar 27, 2007) [1.0.4.000276]
 * JDK Version: 1.5.0_09
 *
 * Generated on 04-Apr-2007 08:28:05
 * Time to generate: 00:15.981 seconds
 *
 */

package agitar.uk.ac.ebi.intact.modelt; import uk.ac.ebi.intact.model.*;

import com.agitar.lib.junit.AgitarTestCase;


public class JournalRefAgitarTest extends AgitarTestCase {

    static Class TARGET_CLASS = JournalRef.class;

    public void testConstructor() throws Throwable {
        Institution owner = new Institution( "testJournalRefShortLabel" );
        CvJournal cvJournal = new CvJournal( new Institution( "testJournalRefShortLabel1" ), "testJournalRefShortLabel" );
        JournalRef journalRef = new JournalRef( owner, "testJournalRefTitle", "testJournalRefAuthors", new Integer( 0 ), "testJournalRefFirstpage", cvJournal );
        assertEquals( "journalRef.getPubmidId()", 0, journalRef.getPubmidId().intValue() );
        assertEquals( "journalRef.getEvidences().size()", 0, journalRef.getEvidences().size() );
        assertEquals( "journalRef.getFirstpage()", "testJournalRefFirstpage", journalRef.getFirstpage() );
        assertSame( "journalRef.getCvJournal()", cvJournal, journalRef.getCvJournal() );
        assertEquals( "journalRef.getAuthors()", "testJournalRefAuthors", journalRef.getAuthors() );
        assertEquals( "journalRef.getTitle()", "testJournalRefTitle", journalRef.getTitle() );
        assertSame( "journalRef.getOwner()", owner, journalRef.getOwner() );
        assertEquals( "journalRef.getAnnotatedObjects().size()", 0, journalRef.getAnnotatedObjects().size() );
    }

    public void testSetCvJournal() throws Throwable {
        Institution owner = new Institution( "testJournalRefShortLabel" );
        CvJournal cvJournal = new CvJournal( owner, "testJournalRefShortLabel1" );
        JournalRef journalRef = new JournalRef( owner, "testJournalRefTitle", "testJournalRefAuthors", new Integer( 0 ), "testJournalRefFirstpage", new CvJournal( new Institution( "testJournalRefShortLabel1" ), "testJournalRefShortLabel" ) );
        journalRef.setCvJournal( cvJournal );
        assertSame( "journalRef.getCvJournal()", cvJournal, journalRef.getCvJournal() );
    }

    public void testSetCvJournalAc() throws Throwable {
        JournalRef journalRef = new JournalRef( new Institution( "testJournalRefShortLabel" ), "testJournalRefTitle", "testJournalRefAuthors", new Integer( 0 ), "testJournalRefFirstpage", new CvJournal( new Institution( "testJournalRefShortLabel1" ), "testJournalRefShortLabel" ) );
        journalRef.setCvJournalAc( "testJournalRefAc" );
        assertEquals( "journalRef.getCvJournalAc()", "testJournalRefAc", journalRef.getCvJournalAc() );
    }

    public void testSetFirstpage() throws Throwable {
        JournalRef journalRef = new JournalRef( new Institution( "testJournalRefShortLabel1" ), "testJournalRefTitle", "testJournalRefAuthors", new Integer( 0 ), "testJournalRefFirstpage", new CvJournal( new Institution( "testJournalRefShortLabel" ), "testJournalRefShortLabel" ) );
        journalRef.setFirstpage( "testJournalRefFirstpage1" );
        assertEquals( "journalRef.getFirstpage()", "testJournalRefFirstpage1", journalRef.getFirstpage() );
    }

    public void testSetPubmidId() throws Throwable {
        JournalRef journalRef = new JournalRef( new Institution( "testJournalRefShortLabel1" ), "testJournalRefTitle", "testJournalRefAuthors", new Integer( 0 ), "testJournalRefFirstpage", new CvJournal( new Institution( "testJournalRefShortLabel" ), "testJournalRefShortLabel" ) );
        journalRef.setPubmidId( new Integer( 0 ) );
        assertEquals( "journalRef.getPubmidId()", 0, journalRef.getPubmidId().intValue() );
    }

    public void testConstructorThrowsNullPointerException() throws Throwable {
        try {
            new JournalRef( new Institution( "testJournalRefShortLabel1" ), null, "testJournalRefAuthors", new Integer( 0 ), "testJournalRefFirstpage", new CvJournal( new Institution( "testJournalRefShortLabel" ), "testJournalRefShortLabel" ) );
            fail( "Expected NullPointerException to be thrown" );
        } catch ( NullPointerException ex ) {
            assertEquals( "ex.getMessage()", "valid Reference must have a non-null title!", ex.getMessage() );
            assertThrownBy( Reference.class, ex );
        }
    }
}

