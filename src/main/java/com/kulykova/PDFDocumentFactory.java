package com.kulykova;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PDFDocumentFactory {
  public static PDDocument createPDFDocument(FormModel formModel) {
    PDDocument document = new PDDocument();
    PDPage page = new PDPage();
    document.addPage(page);

    PDFont font = PDType1Font.HELVETICA_BOLD;

    try(PDPageContentStream stream = new PDPageContentStream(document, page)){
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
      stream.close();
      document.save("D:\\Profile.pdf");
      document.close();
    } catch (COSVisitorException|IOException e) {
      e.printStackTrace();
    }

    return document;
  }
}
