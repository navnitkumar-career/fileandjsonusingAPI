package com.api.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfService {

	private Logger logger = LoggerFactory.getLogger(PdfService.class);

	public ByteArrayInputStream createPdf() {
		logger.info("create PDF");
		String title = "Welcome to WEB TRACKING";
		String content = "we provide website";
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Document document = new Document();
		PdfWriter.getInstance(document, out);
		HeaderFooter footer = new HeaderFooter(true, new Phrase(" page"));
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setBorderWidthBottom(0);
		document.setFooter(footer);

		document.open();
		Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
		Paragraph titleparagraph = new Paragraph(title, titleFont);
		titleparagraph.setAlignment(Element.ALIGN_CENTER);
		document.add(titleparagraph);

		Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 18);
		Paragraph paragraph = new Paragraph(content);
		paragraph.add(new Chunk("hello how are you"));
		document.add(paragraph);

		document.close();

		return new ByteArrayInputStream(out.toByteArray());
	}
}
