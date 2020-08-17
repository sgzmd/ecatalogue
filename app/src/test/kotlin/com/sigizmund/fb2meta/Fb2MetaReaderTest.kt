package com.sigizmund.fb2meta;

import com.sigizmund.ecatalogue.formats.fb2.Fb2MetaReader
import com.sigizmund.fictionbook.FbAuthor
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
        val author = FbAuthor.builder().setFirstName("John").setLastName("Doe").build()

        assertEquals(author, meta.authors[0])
        assertEquals("Fiction Book", meta.title)
    }
}