/**
 * Copyright 2008 The European Bioinformatics Institute, and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.ebi.intact.core.persister;

import uk.ac.ebi.intact.core.unit.IntactBasicTestCase;
import org.junit.Test;
import org.junit.Assert;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.ArrayList;

/**
 * TODO comment that class header
 *
 * @author Prem Anand (prem@ebi.ac.uk)
 * @version $Id$
 * @since 2.0.1-SNAPSHOT
 */
public class DefaultEntityStateCopierTest extends IntactBasicTestCase {

    @Test
    public void copyCollection() throws Exception {
        Collection<Integer> source = new ArrayList<Integer>();
        Collection<Integer> target = new ArrayList<Integer>();

        source.add( 1 );
        source.add( 2 );
        source.add( 3 );

        target.add( 3 );

        DefaultEntityStateCopier desc = new DefaultEntityStateCopier();

        desc.copyCollection( source,target );

        Assert.assertEquals(3, target.size());
        Assert.assertTrue( target.contains( 1 ));
        Assert.assertTrue( target.contains( 2 ));
        Assert.assertTrue( target.contains( 3 ));

        Assert.assertTrue( CollectionUtils.isEqualCollection(source, target));
    }

    @Test
    public void copyCollection_2() throws Exception {
        Collection<Integer> source = new ArrayList<Integer>();
        Collection<Integer> target = new ArrayList<Integer>();

        source.add( 1 );

        target.add( 1 );
        target.add( 2 );
        target.add( 3 );

        DefaultEntityStateCopier desc = new DefaultEntityStateCopier();

        desc.copyCollection( source,target );

        Assert.assertEquals(1, target.size());
        Assert.assertTrue( target.contains( 1 ));

        Assert.assertTrue( CollectionUtils.isEqualCollection(source, target));
    }

    @Test
    public void copyCollection_3() throws Exception {
        Collection<Integer> source = new ArrayList<Integer>();
        Collection<Integer> target = new ArrayList<Integer>();

        source.add( 1 );
        source.add( 2 );
        source.add( 3 );

        target.add( 1 );
        target.add( 2 );
        target.add( 3 );

        DefaultEntityStateCopier desc = new DefaultEntityStateCopier();

        desc.copyCollection( source,target );

        Assert.assertEquals(3, target.size());
        Assert.assertTrue( target.contains( 1 ));
        Assert.assertTrue( target.contains( 2 ));
        Assert.assertTrue( target.contains( 3 ));

        Assert.assertTrue( CollectionUtils.isEqualCollection(source, target));
    }
}
