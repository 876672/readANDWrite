package com.estatementsgenerate.jobConfig;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estatementsgenerate.model.Customer;
import com.estatementsgenerate.pdffile.ExcelGenerator;



@Component
public class WriterExcel  implements ItemWriter<Customer>{
  

	@Autowired
	ExcelGenerator excelGenerator; 
	
	/**
	 * Write.
	 *
	 * @param chunk the chunk
	 * @throws Exception the exception
	 */
	@Override
	public void write(Chunk<? extends Customer> chunk) throws Exception {
		
		excelGenerator.generateExcel(chunk);
		  
	}
		
}
		
	
	

