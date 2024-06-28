//package com.estatementsgenerate.jobConfig;
//
//import org.springframework.batch.item.Chunk;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.stereotype.Component;
//
//import com.estatementsgenerate.model.TransactionRecord;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import lombok.Data;
//
//@Component
//@Data
//public class Writer1 implements ItemWriter<TransactionRecord>{
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//	@Override
//	public void write(Chunk<? extends TransactionRecord> item) throws Exception {
//	 String json = objectMapper.writeValueAsString(item);
//     System.out.println("Converted to JSON: " + json);  
//	
//	}
//	
//	
//
//}
