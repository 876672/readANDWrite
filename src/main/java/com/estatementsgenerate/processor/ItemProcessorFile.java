package com.estatementsgenerate.processor;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.estatementsgenerate.model.Customer;
import com.estatementsgenerate.pdffile.SendPdf;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ItemProcessorFile implements ItemProcessor<Customer, Customer> {
	
    
    @Autowired
    private SendPdf sendPdf;


	public Customer process(Customer item) throws Exception {
		  File pdfFile = sendPdf.generatePdf(item);
	        
          System.out.println("---------pdfFile--------" +pdfFile);
          if (!pdfFile.exists()) {
            throw new MessagingException("Attachment file not found: ");
        }

        FileSystemResource attachment = new FileSystemResource(pdfFile);
        try (PDDocument doc = PDDocument.load(pdfFile)) {
            AccessPermission accessPermission = new AccessPermission();
            accessPermission.setCanExtractContent(true);
            accessPermission.setCanModify(true);
            accessPermission.setCanModifyAnnotations(true);
            accessPermission.setCanPrint(true);
            accessPermission.setCanPrintDegraded(true);
            accessPermission.setCanAssembleDocument(true);
            accessPermission.setCanExtractForAccessibility(true);
            accessPermission.setCanFillInForm(true);

            String password = item.getNamaPelanggan().substring(0, 2)
                    + item.getNoAkaun().substring(0, 6);            
                        StandardProtectionPolicy spp = new StandardProtectionPolicy(password, password, accessPermission);
            spp.setEncryptionKeyLength(128);
            spp.setPermissions(accessPermission);

            doc.protect(spp);
           
            
            File encryptedFile = new File("target/estatement.pdf");
            
            doc.save(pdfFile);
            attachment = new FileSystemResource(encryptedFile);

        }  catch (IOException e) {
            throw new MessagingException("Error processing PDF file: " + e.getMessage(), e);
        
        }
		return item;
       
    }
}
        
//	@Autowired
//	EmailServiceImpl emailService;
//
//
//	/**
//	 * Process.
//	 *
//	 * @param student the student
//	 * @return the student
//	 * @throws Exception the exception
//	 */
//	@Override
//	public Customer process(Customer customer) throws Exception {
//		try {
//			if (checkValidEmail(customer.getEmailPelanggan())) {
//				emailService.sendMimeMessage(customer, "Bank Statement", pathToAttachment);
//				log.debug("processor: {}", customer);
//
//			} else {
//				log.info("Invalid email: {}", customer.getEmailPelanggan());
//				System.out.println("student---------------" + customer);
//
//			}
//
//		} catch (Exception exception) {
//			log.info("error: {}", exception.getMessage());
//		}
//		return customer;
//	}
//
//	/** The path to attachment. */
//	String pathToAttachment = "target/estatement.pdf";
//
//	// ----------------------------------------------------------------------------------------------------
//
//	/**
//	 * Check valid email.
//	 *
//	 * @param email the email
//	 * @return true, if successful
//	 */
//	private boolean checkValidEmail(String email) {
//
//		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
//				+ "A-Z]{2,7}$";
//
//		Pattern pat = Pattern.compile(emailRegex);
//		if (email == null)
//			return false;
//		return pat.matcher(email).matches();
//	}
//
////	public List<Student> getInvalidEmailStudents() {
////		System.out.println("invalidEmailStudents-------" + invalidEmailStudents);
////		return invalidEmailStudents;
////	}





//}
