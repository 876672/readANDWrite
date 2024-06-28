package com.estatementsgenerate.processor;

import org.springframework.batch.item.ItemProcessor;

import com.estatementsgenerate.model.TransactionRecord;

public class FileItemProcessor implements ItemProcessor<TransactionRecord, TransactionRecord> {

	@Override
	public TransactionRecord process(TransactionRecord item) throws Exception {

		statementObject(item);

		return null;
	}

	public void statementObject(TransactionRecord item) {
 }

}
