package com.estatementsgenerate.pdffile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.estatementsgenerate.model.Customer;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import lombok.RequiredArgsConstructor;







@Component
@RequiredArgsConstructor
public class SendPdf {

	private final SpringTemplateEngine templateEngine;

	/**
	 * Generate pdf.
	 *
	 * @param student the student
	 * @return the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public File generatePdf(Customer customer) throws IOException {
		
		System.out.println("===================student"+customer);
	
//		Resource resource = new ClassPathResource("/BicReminderCL01.html");
//		File htmlFile = resource.getFile();
		File pdfFile = new File("target/" + customer.getNoAkaun() + "_estatement.pdf");
		
		System.out.println("rtyuiop["+pdfFile);
//		if (!htmlFile.exists()) {
//			System.out.println("========= HTML file not found ===========");
//			throw new RuntimeException(new FileNotFoundException("HTML file not found: " + htmlFile.getAbsolutePath()));
//		}
//		String htmlContent = new String(
//				Files.readAllBytes(Paths.get("src/main/resources/templates/BicReminderCL01.html")));
		try (OutputStream outputStream = new FileOutputStream(pdfFile)) {
			PdfRendererBuilder builder = new PdfRendererBuilder();
			Context context = new Context();
		
//			System.out.println(customer.getSetakat());
//			System.out.println(customer.getNoAkaun());
//			System.out.println(customer.getAlamatPenjamin1());
//
//			System.out.println(customer.getJumlahBaki());
//
//			System.out.println(customer.getJenisPembiayaan());


			context.setVariable("tarikh",customer.getSetakat());
			context.setVariable("noAkaun",customer.getNoAkaun());
			context.setVariable("alamatPenjamin",customer.getAlamatPenjamin1());
			context.setVariable("jenisPembiayaan",customer.getJenisPembiayaan());
			context.setVariable("jumlahBaki",customer.getJumlahBaki());
			context.setVariable("namaAgensi",customer.getNamaPegawaiBank1());
			context.setVariable("alamat",customer.getAlamat());
			context.setVariable("noTelefon",customer.getNoTelAgensi());
			context.setVariable("noFas",customer.getNoFaksAgensi());
			context.setVariable("namaPegawaiBank2",customer.getNamaPegawaiBank2());


			String htmlContent1 = templateEngine.process("demo", context);
			builder.withHtmlContent(htmlContent1,"classpath:/static/");
			
			builder.toStream(outputStream);
			builder.run();

		} catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}
		return pdfFile; 

	}

}
