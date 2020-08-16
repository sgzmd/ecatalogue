package com.sigizmund.fb2meta;

import org.apache.log4j.BasicConfigurator
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test

class Fb2MetaReaderTest {
    init {
        BasicConfigurator.configure()
    }

    @Test
    fun testGetAuthor() {
        val stream = javaClass.getResourceAsStream("fb2.xml")
        val meta = Fb2MetaReader.getMeta(stream)
        assertEquals("John Doe", meta.authors[0])
    }
}