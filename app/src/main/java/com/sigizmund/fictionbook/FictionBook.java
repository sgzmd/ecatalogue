/*
 * File: FictionBook.java
 * Package: com.sigizmund.fictionbook
 * Project:
 * Generated on: Mon Aug 17 13:02:37 BST 2020
 * From schema(s): /mnt/c/Users/sigiz/Downloads/FictionBook.xsd
 * By: Xsd2SaxParser tool (Eric Blanchard)
 */

package com.sigizmund.fictionbook;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * Implements a SAX parser
 */
public class FictionBook extends DefaultHandler {
    public static class StopParsingException extends SAXException {}

    private static final Logger LOGGER = LoggerFactory.getLogger(FictionBook.class);
    private static boolean configured = false;
    private static SAXParserFactory factory = null;
    private SAXParser saxParser = null;
    private String elementToSkip = null;
    private int skipedElementDepth = 0;
    private CharArrayWriter text = null;
    private int currentDepth = 0;

    // Elements names
    private static final String SUBTITLE_TAG = "subtitle";
    private static final String YEAR_TAG = "year";
    private static final String VERSION_TAG = "version";
    private static final String PUBLISH_INFO_TAG = "publish-info";
    private static final String STYLE_TAG = "style";
    private static final String SRC_OCR_TAG = "src-ocr";
    private static final String TRANSLATOR_TAG = "translator";
    private static final String MIDDLE_NAME_TAG = "middle-name";
    private static final String BOOK_TITLE_TAG = "book-title";
    private static final String P_TAG = "p";
    private static final String KEYWORDS_TAG = "keywords";
    private static final String IMAGE_TAG = "image";
    private static final String NICKNAME_TAG = "nickname";
    private static final String PART_TAG = "part";
    private static final String SUB_TAG = "sub";
    private static final String GENRE_TAG = "genre";
    private static final String EPIGRAPH_TAG = "epigraph";
    private static final String SECTION_TAG = "section";
    private static final String ID_TAG = "id";
    private static final String SEQUENCE_TAG = "sequence";
    private static final String OUTPUT_TAG = "output";
    private static final String TITLE_TAG = "title";
    private static final String SUP_TAG = "sup";
    private static final String HOME_PAGE_TAG = "home-page";
    private static final String POEM_TAG = "poem";
    private static final String SRC_LANG_TAG = "src-lang";
    private static final String EMAIL_TAG = "email";
    private static final String OUTPUT_DOCUMENT_CLASS_TAG = "output-document-class";
    private static final String FICTION_BOOK_TAG = "FictionBook";
    private static final String A_TAG = "a";
    private static final String EMPTY_LINE_TAG = "empty-line";
    private static final String TR_TAG = "tr";
    private static final String BINARY_TAG = "binary";
    private static final String ISBN_TAG = "isbn";
    private static final String AUTHOR_TAG = "author";
    private static final String TITLE_INFO_TAG = "title-info";
    private static final String EMPHASIS_TAG = "emphasis";
    private static final String TABLE_TAG = "table";
    private static final String SRC_TITLE_INFO_TAG = "src-title-info";
    private static final String HISTORY_TAG = "history";
    private static final String CUSTOM_INFO_TAG = "custom-info";
    private static final String STRONG_TAG = "strong";
    private static final String SRC_URL_TAG = "src-url";
    private static final String ANNOTATION_TAG = "annotation";
    private static final String LANG_TAG = "lang";
    private static final String CODE_TAG = "code";
    private static final String TH_TAG = "th";
    private static final String BOOK_NAME_TAG = "book-name";
    private static final String CITY_TAG = "city";
    private static final String STANZA_TAG = "stanza";
    private static final String LAST_NAME_TAG = "last-name";
    private static final String PROGRAM_USED_TAG = "program-used";
    private static final String TEXT_AUTHOR_TAG = "text-author";
    private static final String TD_TAG = "td";
    private static final String BODY_TAG = "body";
    private static final String FIRST_NAME_TAG = "first-name";
    private static final String DOCUMENT_INFO_TAG = "document-info";
    private static final String PUBLISHER_TAG = "publisher";
    private static final String CITE_TAG = "cite";
    private static final String DESCRIPTION_TAG = "description";
    private static final String V_TAG = "v";
    private static final String COVERPAGE_TAG = "coverpage";
    private static final String STRIKETHROUGH_TAG = "strikethrough";
    private static final String DATE_TAG = "date";
    private static final String STYLESHEET_TAG = "stylesheet";

    // Attributes names
    private static final String STYLE_ATTR = "style";
    private static final String INCLUDE_ALL_ATTR = "include-all";
    private static final String TITLE_ATTR = "title";
    private static final String PRICE_ATTR = "price";
    private static final String CURRENCY_ATTR = "currency";
    private static final String ROWSPAN_ATTR = "rowspan";
    private static final String INCLUDE_ATTR = "include";
    private static final String CONTENT_TYPE_ATTR = "content-type";
    private static final String NUMBER_ATTR = "number";
    private static final String VALIGN_ATTR = "valign";
    private static final String VALUE_ATTR = "value";
    private static final String TYPE_ATTR = "type";
    private static final String ALT_ATTR = "alt";
    private static final String ALIGN_ATTR = "align";
    private static final String COLSPAN_ATTR = "colspan";
    private static final String CREATE_ATTR = "create";
    private static final String NAME_ATTR = "name";
    private static final String MATCH_ATTR = "match";
    private static final String INFO_TYPE_ATTR = "info-type";
    private static final String ID_ATTR = "id";
    private static final String MODE_ATTR = "mode";

    private String firstName = null;
    private String lastName = null;
    private String middleName = null;
    private List<FbAuthor> authors = null;
    private String title = null;
    private String sequenceName = null;
    private int seqNumber = -1;

    public List<FbAuthor> getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public int getSeqNumber() {
        return seqNumber;
    }

    /**
     * Private constructor
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    private FictionBook() throws ParserConfigurationException, SAXException {
        text = new CharArrayWriter ();
        saxParser = factory.newSAXParser();
    }

    /**
     * Gets a new instance of FictionBook.
     * Ensuring that the SAX parser factory has been configured only once.
     * @return a <b>FictionBook</b> instance.
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static FictionBook getInstance() throws ParserConfigurationException, SAXException {
        if (! configured) {
            configure();
        }
        return new FictionBook();
    }

    /**
     * Configures the SAX parser factory.
     * The default JRE provided SAX parser is used (controlled by
     * <code>"javax.xml.parsers.SAXParserFactory"</code> system property.
     */
    public static void configure() {
        LOGGER.debug("configure:");

        // Use the configured SAXParserFactory
        factory = SAXParserFactory.newInstance();
        /* Customize SAX parser */
        factory.setNamespaceAware(true);
        factory.setValidating(false);
        configured = true;
    }

    public void parseFromString(final String str) throws SAXException, IOException {
        LOGGER.debug("parseFrontString: str={}", str);
        if (str == null || str.length() == 0) {
            LOGGER.warn("parseFrontString: null string");
            return;
        }
        InputSource src = new InputSource(new StringReader(str));
        parse(src);
    }

    public void parse(InputStream is) throws SAXException, IOException {
        LOGGER.debug("parse: InputStream={}", is);
        parse(new InputSource(is));
    }

    public void parse(InputSource is) throws SAXException, IOException {
        LOGGER.debug("parse: InputSource={}", is);
        saxParser.parse(is, this);
    }

    /* (non-Javadoc)
     * Resolves relative path URI entities to current directory
     */
    public InputSource resolveEntity(String publicId, String systemId)
            throws SAXException {
        LOGGER.debug("resolveEntity: systemId={}", systemId);
        LOGGER.debug("resolveEntity: ByPass the DTD by providing an empty input stream");
        /* Provide an empty entity resolver */
        return new InputSource(new StringReader(""));
    }

    /* (non-Javadoc)
     */
    public void processingInstruction(String target, String data) throws SAXException {
        LOGGER.debug("processingInstruction: target={}, data={}", target, data);
    }

    /* (non-Javadoc)
     */
    public void skippedEntity(String name) throws SAXException {
        LOGGER.debug("skippedEntity: name={}", name);
    }

    /* (non-Javadoc)
     */
    public void startDocument() throws SAXException {
        LOGGER.debug("startDocument:");
    }


    /* (non-Javadoc)
     */
    public void endDocument() throws SAXException {
        LOGGER.debug("endDocument: ");
    }

    /* (non-Javadoc)
     */
    public void startElement (String namespaceURI, String sName, String qName,
                              Attributes attrs) throws SAXException {
        String eName = qName; /* element name (assuming namespaceAware) */

        currentDepth++;
        LOGGER.debug("startElement: {}, currentDepth={}", eName, currentDepth);

        if (elementToSkip != null) {
            LOGGER.debug("startElement: skipping element={}", eName);
            return;
        }

        text.reset();

        if (SUBTITLE_TAG.equals(eName)) {
            //TODO Handle subtitle start element
        } else if (YEAR_TAG.equals(eName)) {
            //TODO Handle year start element
        } else if (VERSION_TAG.equals(eName)) {
            //TODO Handle version start element
        } else if (PUBLISH_INFO_TAG.equals(eName)) {
            //TODO Handle publish-info start element
        } else if (STYLE_TAG.equals(eName)) {
            //TODO Handle style start element
        } else if (SRC_OCR_TAG.equals(eName)) {
            //TODO Handle src-ocr start element
        } else if (TRANSLATOR_TAG.equals(eName)) {
            //TODO Handle translator start element
        } else if (MIDDLE_NAME_TAG.equals(eName)) {
            //TODO Handle middle-name start element
        } else if (BOOK_TITLE_TAG.equals(eName)) {
            //TODO Handle book-title start element
        } else if (P_TAG.equals(eName)) {
            //TODO Handle p start element
        } else if (KEYWORDS_TAG.equals(eName)) {
            //TODO Handle keywords start element
        } else if (IMAGE_TAG.equals(eName)) {
            //TODO Handle image start element
        } else if (NICKNAME_TAG.equals(eName)) {
            //TODO Handle nickname start element
        } else if (PART_TAG.equals(eName)) {
            //TODO Handle part start element
        } else if (SUB_TAG.equals(eName)) {
            //TODO Handle sub start element
        } else if (GENRE_TAG.equals(eName)) {
            //TODO Handle genre start element
        } else if (EPIGRAPH_TAG.equals(eName)) {
            //TODO Handle epigraph start element
        } else if (SECTION_TAG.equals(eName)) {
            //TODO Handle section start element
        } else if (ID_TAG.equals(eName)) {
            //TODO Handle id start element
        } else if (SEQUENCE_TAG.equals(eName)) {
            int nameIdx = -1;
            if ((nameIdx = attrs.getIndex("name")) >= 0) {
                this.sequenceName = attrs.getValue(nameIdx);
                int seqNumIdx = -1;
                if ((seqNumIdx = attrs.getIndex("number")) >= 0) {
                    this.seqNumber = Integer.parseInt(attrs.getValue(seqNumIdx));
                }
            }
        } else if (OUTPUT_TAG.equals(eName)) {
            //TODO Handle output start element
        } else if (TITLE_TAG.equals(eName)) {
            //TODO Handle title start element
        } else if (SUP_TAG.equals(eName)) {
            //TODO Handle sup start element
        } else if (HOME_PAGE_TAG.equals(eName)) {
            //TODO Handle home-page start element
        } else if (POEM_TAG.equals(eName)) {
            //TODO Handle poem start element
        } else if (SRC_LANG_TAG.equals(eName)) {
            //TODO Handle src-lang start element
        } else if (EMAIL_TAG.equals(eName)) {
            //TODO Handle email start element
        } else if (OUTPUT_DOCUMENT_CLASS_TAG.equals(eName)) {
            //TODO Handle output-document-class start element
        } else if (FICTION_BOOK_TAG.equals(eName)) {
            //TODO Handle FictionBook start element
        } else if (A_TAG.equals(eName)) {
            //TODO Handle a start element
        } else if (EMPTY_LINE_TAG.equals(eName)) {
            //TODO Handle empty-line start element
        } else if (TR_TAG.equals(eName)) {
            //TODO Handle tr start element
        } else if (BINARY_TAG.equals(eName)) {
            //TODO Handle binary start element
        } else if (ISBN_TAG.equals(eName)) {
            //TODO Handle isbn start element
        } else if (AUTHOR_TAG.equals(eName)) {
            //TODO Handle author start element
        } else if (TITLE_INFO_TAG.equals(eName)) {
            //TODO Handle title-info start element
        } else if (EMPHASIS_TAG.equals(eName)) {
            //TODO Handle emphasis start element
        } else if (TABLE_TAG.equals(eName)) {
            //TODO Handle table start element
        } else if (SRC_TITLE_INFO_TAG.equals(eName)) {
            //TODO Handle src-title-info start element
        } else if (HISTORY_TAG.equals(eName)) {
            //TODO Handle history start element
        } else if (CUSTOM_INFO_TAG.equals(eName)) {
            //TODO Handle custom-info start element
        } else if (STRONG_TAG.equals(eName)) {
            //TODO Handle strong start element
        } else if (SRC_URL_TAG.equals(eName)) {
            //TODO Handle src-url start element
        } else if (ANNOTATION_TAG.equals(eName)) {
            //TODO Handle annotation start element
        } else if (LANG_TAG.equals(eName)) {
            //TODO Handle lang start element
        } else if (CODE_TAG.equals(eName)) {
            //TODO Handle code start element
        } else if (TH_TAG.equals(eName)) {
            //TODO Handle th start element
        } else if (BOOK_NAME_TAG.equals(eName)) {
            //TODO Handle book-name start element
        } else if (CITY_TAG.equals(eName)) {
            //TODO Handle city start element
        } else if (STANZA_TAG.equals(eName)) {
            //TODO Handle stanza start element
        } else if (LAST_NAME_TAG.equals(eName)) {
            //TODO Handle last-name start element
        } else if (PROGRAM_USED_TAG.equals(eName)) {
            //TODO Handle program-used start element
        } else if (TEXT_AUTHOR_TAG.equals(eName)) {
            //TODO Handle text-author start element
        } else if (TD_TAG.equals(eName)) {
            //TODO Handle td start element
        } else if (BODY_TAG.equals(eName)) {
            //TODO Handle body start element
        } else if (FIRST_NAME_TAG.equals(eName)) {
            //TODO Handle first-name start element
        } else if (DOCUMENT_INFO_TAG.equals(eName)) {
            //TODO Handle document-info start element
        } else if (PUBLISHER_TAG.equals(eName)) {
            //TODO Handle publisher start element
        } else if (CITE_TAG.equals(eName)) {
            //TODO Handle cite start element
        } else if (DESCRIPTION_TAG.equals(eName)) {
            //TODO Handle description start element
        } else if (V_TAG.equals(eName)) {
            //TODO Handle v start element
        } else if (COVERPAGE_TAG.equals(eName)) {
            //TODO Handle coverpage start element
        } else if (STRIKETHROUGH_TAG.equals(eName)) {
            //TODO Handle strikethrough start element
        } else if (DATE_TAG.equals(eName)) {
            //TODO Handle date start element
        } else if (STYLESHEET_TAG.equals(eName)) {
            //TODO Handle stylesheet start element
        }  else {
            LOGGER.debug("startElement: Unknown element={}", eName);
            /* Unknown elements fall here, so just ask to skip this unknown
             * element (and all nested elements) */
            elementToSkip = eName;
            skipedElementDepth = currentDepth;
        }
    }

    /* (non-Javadoc)
     */
    public void endElement(String namespaceURI, String sName, String qName)
            throws SAXException {
        String eName = qName; /* element name (assuming namespaceAware) */

        LOGGER.debug("endElement: {}", eName);
        if (elementToSkip != null) {
            if (elementToSkip.equals(eName) && currentDepth == skipedElementDepth) {
                elementToSkip = null;
                skipedElementDepth = -1;
            }
            currentDepth--;
            return;
        }

        if (SUBTITLE_TAG.equals(eName)) {
            //TODO Handle subtitle end element
        } else if (YEAR_TAG.equals(eName)) {
            //TODO Handle year end element
        } else if (VERSION_TAG.equals(eName)) {
            //TODO Handle version end element
        } else if (PUBLISH_INFO_TAG.equals(eName)) {
            //TODO Handle publish-info end element
        } else if (STYLE_TAG.equals(eName)) {
            //TODO Handle style end element
        } else if (SRC_OCR_TAG.equals(eName)) {
            //TODO Handle src-ocr end element
        } else if (TRANSLATOR_TAG.equals(eName)) {
            //TODO Handle translator end element
        } else if (MIDDLE_NAME_TAG.equals(eName)) {
            this.middleName = text.toString();
        } else if (BOOK_TITLE_TAG.equals(eName)) {
            this.title = text.toString();
        } else if (P_TAG.equals(eName)) {
            //TODO Handle p end element
        } else if (KEYWORDS_TAG.equals(eName)) {
            //TODO Handle keywords end element
        } else if (IMAGE_TAG.equals(eName)) {
            //TODO Handle image end element
        } else if (NICKNAME_TAG.equals(eName)) {
            //TODO Handle nickname end element
        } else if (PART_TAG.equals(eName)) {
            //TODO Handle part end element
        } else if (SUB_TAG.equals(eName)) {
            //TODO Handle sub end element
        } else if (GENRE_TAG.equals(eName)) {
            //TODO Handle genre end element
        } else if (EPIGRAPH_TAG.equals(eName)) {
            //TODO Handle epigraph end element
        } else if (SECTION_TAG.equals(eName)) {
            //TODO Handle section end element
        } else if (ID_TAG.equals(eName)) {
            //TODO Handle id end element
        } else if (SEQUENCE_TAG.equals(eName)) {
            //TODO Handle sequence end element
        } else if (OUTPUT_TAG.equals(eName)) {
            //TODO Handle output end element
        } else if (TITLE_TAG.equals(eName)) {
            //TODO Handle title end element
        } else if (SUP_TAG.equals(eName)) {
            //TODO Handle sup end element
        } else if (HOME_PAGE_TAG.equals(eName)) {
            //TODO Handle home-page end element
        } else if (POEM_TAG.equals(eName)) {
            //TODO Handle poem end element
        } else if (SRC_LANG_TAG.equals(eName)) {
            //TODO Handle src-lang end element
        } else if (EMAIL_TAG.equals(eName)) {
            //TODO Handle email end element
        } else if (OUTPUT_DOCUMENT_CLASS_TAG.equals(eName)) {
            //TODO Handle output-document-class end element
        } else if (FICTION_BOOK_TAG.equals(eName)) {
            //TODO Handle FictionBook end element
        } else if (A_TAG.equals(eName)) {
            //TODO Handle a end element
        } else if (EMPTY_LINE_TAG.equals(eName)) {
            //TODO Handle empty-line end element
        } else if (TR_TAG.equals(eName)) {
            //TODO Handle tr end element
        } else if (BINARY_TAG.equals(eName)) {
            //TODO Handle binary end element
        } else if (ISBN_TAG.equals(eName)) {
            //TODO Handle isbn end element
        } else if (AUTHOR_TAG.equals(eName)) {
            if (this.authors == null) {
                this.authors = new ArrayList<FbAuthor>();
            }

            FbAuthor.Builder authorBuilder = FbAuthor.builder();
            if (firstName != null) {
                authorBuilder.setFirstName(firstName);
            }
            if (middleName != null) {
                authorBuilder.setMiddleName(middleName);
            }
            if (lastName != null) {
                authorBuilder.setLastName(lastName);
            }

            firstName = lastName = middleName = null;
            authors.add(authorBuilder.build());
        } else if (TITLE_INFO_TAG.equals(eName)) {
            throw new StopParsingException();
        } else if (EMPHASIS_TAG.equals(eName)) {
            //TODO Handle emphasis end element
        } else if (TABLE_TAG.equals(eName)) {
            //TODO Handle table end element
        } else if (SRC_TITLE_INFO_TAG.equals(eName)) {
            //TODO Handle src-title-info end element
        } else if (HISTORY_TAG.equals(eName)) {
            //TODO Handle history end element
        } else if (CUSTOM_INFO_TAG.equals(eName)) {
            //TODO Handle custom-info end element
        } else if (STRONG_TAG.equals(eName)) {
            //TODO Handle strong end element
        } else if (SRC_URL_TAG.equals(eName)) {
            //TODO Handle src-url end element
        } else if (ANNOTATION_TAG.equals(eName)) {
            //TODO Handle annotation end element
        } else if (LANG_TAG.equals(eName)) {
            //TODO Handle lang end element
        } else if (CODE_TAG.equals(eName)) {
            //TODO Handle code end element
        } else if (TH_TAG.equals(eName)) {
            //TODO Handle th end element
        } else if (BOOK_NAME_TAG.equals(eName)) {
            //TODO Handle book-name end element
        } else if (CITY_TAG.equals(eName)) {
            //TODO Handle city end element
        } else if (STANZA_TAG.equals(eName)) {
            //TODO Handle stanza end element
        } else if (LAST_NAME_TAG.equals(eName)) {
            this.lastName = text.toString();
        } else if (PROGRAM_USED_TAG.equals(eName)) {
            //TODO Handle program-used end element
        } else if (TEXT_AUTHOR_TAG.equals(eName)) {
            //TODO Handle text-author end element
        } else if (TD_TAG.equals(eName)) {
            //TODO Handle td end element
        } else if (BODY_TAG.equals(eName)) {
            //TODO Handle body end element
        } else if (FIRST_NAME_TAG.equals(eName)) {
            this.firstName = text.toString();
        } else if (DOCUMENT_INFO_TAG.equals(eName)) {
            //TODO Handle document-info end element
        } else if (PUBLISHER_TAG.equals(eName)) {
            //TODO Handle publisher end element
        } else if (CITE_TAG.equals(eName)) {
            //TODO Handle cite end element
        } else if (DESCRIPTION_TAG.equals(eName)) {
            //TODO Handle description end element
        } else if (V_TAG.equals(eName)) {
            //TODO Handle v end element
        } else if (COVERPAGE_TAG.equals(eName)) {
            //TODO Handle coverpage end element
        } else if (STRIKETHROUGH_TAG.equals(eName)) {
            //TODO Handle strikethrough end element
        } else if (DATE_TAG.equals(eName)) {
            //TODO Handle date end element
        } else if (STYLESHEET_TAG.equals(eName)) {
            //TODO Handle stylesheet end element
        }  else {
            LOGGER.debug("endElement: Unknown ending tag=" + eName);
        }
        currentDepth--;
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#characters(char[], int, int)
     */
    public void characters(char buf[], int offset, int len)
            throws SAXException {
        if (elementToSkip != null) {
            return;
        }
        if (len > 0) {
            text.write (buf, offset, len);
        }
    }

    /* (non-Javadoc)
     */
    public void warning(SAXParseException e) throws SAXException {
        LOGGER.warn("warning: XML parsing warning: " + e.toString(), e);
    }

    /* (non-Javadoc)
     */
    public void error(SAXParseException e) throws SAXException {
        LOGGER.warn("error: XML parsing error: " + e.toString(), e);
    }

    /* (non-Javadoc)
     */
    public void fatalError(SAXParseException e) throws SAXException {
        LOGGER.error("fatalError: parsing fatal error: " + e.toString(), e);
        throw e;
    }

    public String getText()
    {
        return text.toString().trim();
    }
}
