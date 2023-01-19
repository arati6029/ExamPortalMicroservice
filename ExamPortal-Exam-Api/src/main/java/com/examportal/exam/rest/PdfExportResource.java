package com.examportal.exam.rest;



//import com.examportal.exam.dao.StudentRepository;
//import com.examportal.exam.model.Student;
//import com.examportal.exam.service.PDFGeneratorService;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
@RestController
@RequestMapping()
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")

public class PdfExportResource {
	@Autowired
	private StudentRepository studentRepo;
	
    private final PDFGeneratorService pdfGeneratorService;

    public PDFExportController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }
   
    @GetMapping("/pdf/generate/{id}")
    public void generatePDF(HttpServletResponse response, @PathVariable long id) throws IOException {
       //System.out.println("inside generate pdf "+name+" "+rollNo);
    	Student student= studentRepo.findById(id).orElseThrow();
    	String name= student.getName();
    	int rollNo=student.getRollNo();
    	response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=student_resultpdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(response,id,name,rollNo);
    }
}
