package uk.ac.ebi.intact.jami.utils.comparator;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.utils.comparator.CollectionComparator;
import psidev.psi.mi.jami.utils.comparator.xref.UnambiguousExternalIdentifierComparator;
import uk.ac.ebi.intact.jami.ApplicationContextProvider;
import uk.ac.ebi.intact.jami.context.IntactContext;
import uk.ac.ebi.intact.jami.model.extension.IntactCvTerm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/**
 * Comparator for IntAct cv terms that take into account objClass and always look at the shortlabel first
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>24/01/14</pre>
 */

public class IntactCvTermComparator implements Comparator<CvTerm> {

    private static IntactCvTermComparator intactCvTermComparator;
    protected UnambiguousExternalIdentifierComparator identifierComparator;
    protected CollectionComparator<Xref> identifierCollectionComparator;

    private Source institution;
    private String cvPrefix;

    /**
     * Creates a new CvTermComparator with UnambiguousExternalIdentifierComparator
     *
     */
    public IntactCvTermComparator() {
        this.identifierComparator = new UnambiguousExternalIdentifierComparator();
        this.identifierCollectionComparator = new CollectionComparator<Xref>(this.identifierComparator);

        final IntactContext context = ApplicationContextProvider.getBean("intactContext");
        this.cvPrefix = "IA:";
        if (context != null){
            String prefix = context.getConfig().getLocalCvPrefix();
            this.cvPrefix = prefix != null ? prefix+":" : "IA:";
            this.institution = context.getConfig().getDefaultInstitution();
        }
    }

    public UnambiguousExternalIdentifierComparator getIdentifierComparator() {
        return identifierComparator;
    }

    public int compare(CvTerm cvTerm1, CvTerm cvTerm2) {

        int EQUAL = 0;
        int BEFORE = -1;
        int AFTER = 1;

        if (cvTerm1 == cvTerm2){
            return 0;
        }
        else if (cvTerm1 == null){
            return AFTER;
        }
        else if (cvTerm2 == null){
            return BEFORE;
        }
        else {
            // first compares mi identifiers.
            String mi1 = cvTerm1.getMIIdentifier();
            String mi2 = cvTerm2.getMIIdentifier();
            String mod1 = cvTerm1.getMODIdentifier();
            String mod2 = cvTerm2.getMODIdentifier();
            String par1 = cvTerm1.getPARIdentifier();
            String par2 = cvTerm2.getPARIdentifier();

            // get objclass first
            String objClass1 = cvTerm1 instanceof IntactCvTerm ? ((IntactCvTerm) cvTerm1).getObjClass() : null;
            String objClass2 = cvTerm2 instanceof IntactCvTerm ? ((IntactCvTerm) cvTerm2).getObjClass() : null;

            if (objClass1 != null && objClass2 != null){
                int comp = objClass1.compareTo(objClass2);
                if (comp != 0){
                    return comp;
                }
            }
            else if (objClass1 != null){
                 return BEFORE;
            }
            else if (objClass2 != null){
                return AFTER;
            }

            if (mi1 != null && mi2 != null){
                return mi1.compareTo(mi2);
            }
            else if (mi1 != null){
                return BEFORE;
            }
            else if (mi2 != null){
                return AFTER;
            }
            else if (mod1 != null && mod2 != null){
                return mod1.compareTo(mod2);
            }
            else if (mod1 != null){
                return BEFORE;
            }
            else if (mod2 != null){
                return AFTER;
            }
            else if (par1 != null && par2 != null){
                return par1.compareTo(par2);
            }
            else if (par1 != null){
                return BEFORE;
            }
            else if (par2 != null){
                return AFTER;
            }
            // exclude automatically generated identifier from the list of identifiers
            else if (this.institution != null){
                Collection<Xref> identifiers1 = new ArrayList<Xref>(cvTerm1.getIdentifiers().size());
                filterAutomaticallyGeneratedIdentifiers(identifiers1, cvTerm1.getIdentifiers());
                Collection<Xref> identifiers2 = new ArrayList<Xref>(cvTerm2.getIdentifiers().size());
                filterAutomaticallyGeneratedIdentifiers(identifiers2, cvTerm2.getIdentifiers());

                if (!identifiers1.isEmpty() && !identifiers2.isEmpty()){
                    // the identifiers must be the same
                    return this.identifierCollectionComparator.compare(identifiers1, identifiers2);
                }
                else if (!identifiers1.isEmpty()){
                    return BEFORE;
                }
                else if (!identifiers2.isEmpty()){
                    return AFTER;
                }
                else {
                    // check names which cannot be null because we could not compare the identifiers
                    String label1 = cvTerm1.getShortName();
                    String label2 = cvTerm2.getShortName();

                    return label1.toLowerCase().trim().compareTo(label2.toLowerCase().trim());
                }
            }
            else if (!cvTerm1.getIdentifiers().isEmpty() && !cvTerm2.getIdentifiers().isEmpty()){
                // the identifiers must be the same
                return this.identifierCollectionComparator.compare(cvTerm1.getIdentifiers(), cvTerm2.getIdentifiers());
            }
            else if (!cvTerm1.getIdentifiers().isEmpty()){
                return BEFORE;
            }
            else if (!cvTerm2.getIdentifiers().isEmpty()){
                return AFTER;
            }
            else {
                // check names which cannot be null because we could not compare the identifiers
                String label1 = cvTerm1.getShortName();
                String label2 = cvTerm2.getShortName();

                return label1.toLowerCase().trim().compareTo(label2.toLowerCase().trim());
            }
        }
    }

    private void filterAutomaticallyGeneratedIdentifiers(Collection<Xref> identifiers, Collection<Xref> originalXrefs) {
         for (Xref ref : originalXrefs){
             if (XrefUtils.isXrefFromDatabase(ref, this.institution.getMIIdentifier(), this.institution.getShortName())
                     && XrefUtils.doesXrefHaveQualifier(ref, Xref.IDENTITY_MI, Xref.IDENTITY) &&
                     ref.getId().startsWith(cvPrefix)){
                  // ignore this identifier
             }
             else{
                 identifiers.add(ref);
             }
         }
    }

    /**
     * Use UnambiguousCvTermComparator to know if two CvTerms are equals.
     * @param cv1
     * @param cv2
     * @return true if the two CvTerms are equal
     */
    public static boolean areEquals(CvTerm cv1, CvTerm cv2){
        if (intactCvTermComparator == null){
            intactCvTermComparator = new IntactCvTermComparator();
        }

        return intactCvTermComparator.compare(cv1, cv2) == 0;
    }
}