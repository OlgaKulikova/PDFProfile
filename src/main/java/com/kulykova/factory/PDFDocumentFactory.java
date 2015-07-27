package com.kulykova.factory;

import com.kulykova.model.FormModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Component
public class PDFDocumentFactory {
  public static ByteArrayOutputStream createPDFDocument(FormModel formModel) {
    PDDocument document = new PDDocument();
    PDPage page = new PDPage();
    document.addPage(page);

    PDFont font = PDType1Font.HELVETICA_BOLD;
    ByteArrayOutputStream baos;

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
      baos = new ByteArrayOutputStream();
      document.save(baos);
      document.close();
    } catch (Exception e) {
      throw new RuntimeException("Form wasn't filled properly");
    }

    return baos;
  }
}
