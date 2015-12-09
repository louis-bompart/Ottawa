package org.Ottawa.elasticsearch.controller;

import org.Ottawa.elasticsearch.controller.DataImporter;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by louis on 09/12/2015.
 */
public class dataImporterTest {

    @Test
    public void testDoImport() throws Exception {
        DataImporter dataImporter = new DataImporter();
        dataImporter.doImport();
    }
}