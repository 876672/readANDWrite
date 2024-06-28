package com.estatementsgenerate.repositoies;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estatementsgenerate.model.TransactionRecord;


public interface TxnRecordRepository extends JpaRepository<TransactionRecord, String> {

	

}
