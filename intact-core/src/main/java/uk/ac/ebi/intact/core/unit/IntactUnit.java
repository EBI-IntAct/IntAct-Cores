/*
 * Copyright 2001-2007 The European Bioinformatics Institute.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.ebi.intact.core.unit;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import uk.ac.ebi.intact.business.IntactException;
import uk.ac.ebi.intact.business.IntactTransactionException;
import uk.ac.ebi.intact.commons.util.TestDataset;
import uk.ac.ebi.intact.config.impl.AbstractHibernateDataConfig;
import uk.ac.ebi.intact.context.DataContext;
import uk.ac.ebi.intact.context.IntactConfigurator;
import uk.ac.ebi.intact.context.IntactContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Utilities to deal with the database, useful for testing
 *
 * @author Bruno Aranda (baranda@ebi.ac.uk)
 * @version $Id$
 */
public class IntactUnit {

    private DefaultDataTypeFactory dataTypeFactory;

    public IntactUnit(){
        dataTypeFactory = new HsqldbDataTypeFactory();
    }

    public IntactUnit(DefaultDataTypeFactory dataTypeFactory){
        this.dataTypeFactory = dataTypeFactory;
    }

    /**
     * Import a testDataset into the database
     * @param testDataset the dataset to use
     */
    public void importTestDataset(TestDataset testDataset)  {
        if (testDataset == null) {
            throw new NullPointerException("testDataset");
        }

        try {
            DatabaseOperation.INSERT.execute(getDatabaseConnection(), convertTestDatasetToDbUnit(testDataset));
        } catch (Exception e) {
            throw new IntactException("Exception importing dataset: "+testDataset.getId(), e);
        }
    }

    private IDataSet convertTestDatasetToDbUnit(TestDataset testDataset) throws DataSetException, IOException {
        return new FlatXmlDataSet(testDataset.getDbUnitDataset());
    }

    /**
     * Drops and creates the schema, initializing intact. Beware that it commits transactions
     */
    public void resetSchema() throws IntactTransactionException {
        DataContext dataContext = IntactContext.getCurrentInstance().getDataContext();
        dataContext.beginTransaction();

        dropSchema();

        dataContext.commitTransaction();
        dataContext.beginTransaction();

        createSchema();

        dataContext.commitTransaction();
        dataContext.beginTransaction();

        IntactConfigurator.initializeDatabase(IntactContext.getCurrentInstance());

        dataContext.commitTransaction();
        dataContext.beginTransaction();

    }

    /**
     * Creates a schema 
     */
    public void createSchema() {
        SchemaExport se = new SchemaExport(getConfiguration());
        se.create(false, true);
    }

    /**
     * Drops the current schema, emptying the database
     */
    public void dropSchema() {
        SchemaExport se = new SchemaExport(getConfiguration());
        se.drop(false, true);
    }

    private Configuration getConfiguration() {
        AbstractHibernateDataConfig dataConfig = (AbstractHibernateDataConfig) IntactContext.getCurrentInstance().getConfig().getDefaultDataConfig();
        return dataConfig.getConfiguration();
    }

    public IDataSet createDbUnitDataset() throws SQLException {
        return createDbUnitDataset("IA_%");
    }

    public IDataSet createDbUnitDataset(String tablePrefix) throws SQLException {
        Connection con = IntactContext.getCurrentInstance().getDataContext().getDaoFactory().connection();

        ResultSet tables = con.getMetaData().getTables(null, null, tablePrefix, new String[]{"TABLE"});
        QueryDataSet allTablesDataSet = new QueryDataSet(getDatabaseConnection());
        while (tables.next())
        {
            String tableName = tables.getString(3);
            allTablesDataSet.addTable(tableName);
        }

        return allTablesDataSet;
    }

    public IDatabaseConnection getDatabaseConnection() {
        IntactContext context = IntactContext.getCurrentInstance();

        Connection con = context.getDataContext().getDaoFactory().connection();
        IDatabaseConnection connection = new DatabaseConnection(con);

        DatabaseConfig config = connection.getConfig();
        config.setProperty( DatabaseConfig.PROPERTY_DATATYPE_FACTORY, dataTypeFactory );

        return connection;
    }

    public void exportDbUnitDataSetToFile(IDataSet dataset, File file) throws IOException, DataSetException {
        FlatXmlDataSet.write( dataset, new FileOutputStream(file));
    }
}