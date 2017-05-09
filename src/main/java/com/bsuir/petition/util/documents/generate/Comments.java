package com.bsuir.petition.util.documents.generate;

import com.bsuir.petition.bean.entity.Comment;
import com.bsuir.petition.util.documents.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.supercsv.exception.SuperCsvException;
import org.supercsv.io.ICsvBeanWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Comments extends Document {

    private List<Comment> comments;

    @Override
    public void setObjectList(Object objectList) {
        this.comments = (List<Comment>) objectList;
    }

    @Override
    public void buildPdf(com.itextpdf.text.Document doc) throws DocumentException {
        doc.add(new Paragraph("Comments statistics"));
        doc.add(new Paragraph("Total comments: " + comments.size()));
        PdfPTable table = new PdfPTable(2);
        table.addCell(new PdfPCell(new Paragraph("Comment text")));
        table.addCell(new PdfPCell(new Paragraph("User nickname")));
        for (Comment comment : comments) {
            table.addCell(new PdfPCell(new Paragraph(comment.getText())));
            table.addCell(new PdfPCell(new Paragraph(comment.getUser().getNick())));
        }
        doc.add(table);
    }

    @Override
    public void buildXls() {

    }

    @Override
    public void buildCsv(ICsvBeanWriter writer) throws IOException {
        String[] header = {"text","userName"};
        writer.writeHeader(header);
        for (Comment comment : comments) {
            try {
                writer.write(new CommentBean(comment.getText(), comment.getUser().getNick()), header);
            } catch (SuperCsvException exception) {
                System.out.println(exception.toString());
            }
        }
    }

    public class CommentBean {
        private String text;
        private String userName;

        public CommentBean(String text, String userName) {
            this.text = text;
            this.userName = userName;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
