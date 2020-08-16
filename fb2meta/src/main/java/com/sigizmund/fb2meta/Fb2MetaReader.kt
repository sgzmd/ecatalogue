package com.sigizmund.fb2meta

import mu.KotlinLogging
import org.apache.commons.io.input.BOMInputStream
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler
import java.io.InputStream
import javax.xml.parsers.SAXParserFactory

data class Fb2Meta(val title: String, val authors: List<String>, val series: String)

internal class StopParsingException : SAXException()

internal class Fb2DocHandler : DefaultHandler() {
    private val logger = KotlinLogging.logger {}

    val authors = mutableListOf<String>()

    var firstName: String? = null
    var lastName: String? = null
    var callCharacterProcessor = false
    var characterProcessor: (String) -> Unit = fun(ch: String) {}

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        callCharacterProcessor = false

        when (qName) {
            "author" -> {
                finishAuthorProcessing()
            }
            "title-info" -> {
                throw StopParsingException()
            }
        }
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        if (callCharacterProcessor) {
            val s = String(ch!!)
            val ss = s.substring(start, start + length)
            logger.debug { "characters($ss)" }
            characterProcessor(ss)
        }
    }

    override fun startElement(
        uri: String?,
        localName: String?,
        qName: String?,
        attributes: Attributes?
    ) {
        logger.debug { "uri=$uri, localName=$localName, qName=$qName" }
        callCharacterProcessor = false
        when (qName) {
            "author" -> {
                finishAuthorProcessing()
            }
            "first-name" -> {
                characterProcessor = { ch ->
                    firstName = ch
                }
                callCharacterProcessor = true
            }
            "last-name" -> {
                characterProcessor = { ch ->
                    lastName = ch
                }
                callCharacterProcessor = true
            }
        }
    }

    private fun finishAuthorProcessing() {
        if (firstName != null || lastName != null) {
            authors.add("$firstName $lastName".trim())
            firstName = null
            lastName = null
        }
    }
}

class Fb2MetaReader {
    companion object {
        fun getMeta(inputStream: InputStream): Fb2Meta {
            val bomIs = BOMInputStream(inputStream)
            val factory = SAXParserFactory.newInstance()
            val parser = factory.newSAXParser()
            val handler = Fb2DocHandler()
            try {
                parser.parse(bomIs, handler)
            } catch (e: StopParsingException) {
            }

            return Fb2Meta("", handler.authors, "")
        }
    }
}