package com.sigizmund.ecatalogue.formats.fb2

import com.sigizmund.fictionbook.FbAuthor
import com.sigizmund.fictionbook.FictionBook
import mu.KotlinLogging
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler
import java.io.InputStream
import java.util.*
import javax.xml.parsers.SAXParserFactory

data class Fb2Meta(
    val title: String,
    val authors: List<FbAuthor>,
    val series: String?,
    val numInSequence: Int?
)

class Fb2MetaReader {
    companion object {
        fun getMeta(inputStream: InputStream): Fb2Meta {
            val factory = SAXParserFactory.newInstance()
            val parser = factory.newSAXParser()
            val handler = FictionBook.getInstance()
            try {
                parser.parse(inputStream, handler)
            } catch (e: FictionBook.StopParsingException) {
            }

            return Fb2Meta(
                handler.title!!,
                handler.authors,
                handler.sequenceName,
                handler.seqNumber
            )
        }
    }
}