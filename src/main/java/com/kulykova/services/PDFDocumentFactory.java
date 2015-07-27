package com.kulykova.services;

import com.kulykova.model.FormModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PDFDocumentFactory {
  private PDDocument document;
  private PDPage page;

  public ByteArrayOutputStream createPDFDocument(FormModel formModel) {
    document = new PDDocument();
    page = new PDPage();
    document.addPage(page);

    PDFont font = PDType1Font.HELVETICA_BOLD;
    ByteArrayOutputStream baos;

    try {
      PDPageContentStream stream = getPdPageContentStream(formModel, font);
      stream.close();

      baos = new ByteArrayOutputStream();
      document.save(baos);
      document.close();
    } catch (Exception e) {
      throw new RuntimeException("Form wasn't filled properly");
    }

    return baos;
  }

  protected PDPageContentStream getPdPageContentStream(FormModel formModel, PDFont font) throws IOException {
    PDPageContentStream stream = new PDPageContentStream(document, page);
    stream.beginText();
    stream.setFont(font, 14);
    stream.moveTextPositionByAmount(100, 700);
    stream.drawString(formModel.getFirstName() + " " + formModel.getLastName());
    stream.endText();

    stream.beginText();
    stream.setFont(font, 14);
    stream.moveTextPositionByAmount(100, 650);
    stream.drawString(formModel.getPassport());
    stream.endText();
    return stream;
  }
}
