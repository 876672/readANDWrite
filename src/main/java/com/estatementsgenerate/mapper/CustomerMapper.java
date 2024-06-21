package com.estatementsgenerate.mapper;


import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;
import org.springframework.stereotype.Component;

import com.estatementsgenerate.model.Customer;


@Component
public class CustomerMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(RowSet rs) throws Exception {
        Customer customer = new Customer();
       Object[] row = rs.getCurrentRow();
          
     
       customer.setNoAkaun((String) row[0]);
       customer.setNamaPelanggan((String) row[1]);
       customer.setAlamat1((String) row[2]);
       customer.setAlamat2((String) row[3]);
       customer.setAlamat3((String) row[4]);
       customer.setAlamat4((String) row[5]);
       customer.setAlamat5((String) row[6]);
       customer.setAlamat6((String) row[7]);
       customer.setJenisPembiayaan((String) row[8]);
       customer.setJumlahBaki((String) row[9]);
       customer.setSetakat((String) row[10]);
       customer.setNamaPenjamin1((String) row[11]);
       customer.setAlamatPenjamin1((String) row[12]);
       customer.setAlamatPenjamin11((String) row[13]);
       customer.setAlamatPenjamin12((String) row[14]);
       customer.setAlamatPenjamin13((String) row[15]);
       customer.setAlamatPenjamin14((String) row[16]);
       customer.setAlamatPenjamin15((String) row[17]);
       customer.setNamaPenjamin2((String) row[18]);
       customer.setAlamatPenjamin2((String) row[19]);
       customer.setAlamatPenjamin21((String) row[10]);
       customer.setAlamatPenjamin22((String) row[22]);
       customer.setAlamatPenjamin23((String) row[22]);
       customer.setAlamatPenjamin24((String) row[23]);
       customer.setAlamatPenjamin25((String) row[24]);
       customer.setNamaPenjamin3((String) row[25]);
       customer.setAlamatPenjamin3((String) row[26]);
       customer.setAlamatPenjamin31((String) row[27]);
       customer.setAlamatPenjamin32((String) row[28]);
       customer.setAlamatPenjamin33((String) row[29]);
       customer.setAlamatPenjamin34((String) row[30]);
       customer.setAlamatPenjamin35((String) row[31]);
       customer.setDcaName((String) row[32]);
       customer.setAlamat((String) row[33]);
       customer.setNoTelAgensi((String) row[34]);
       customer.setNoFaksAgensi((String) row[35]);
       customer.setNamaPegawaiBank1((String) row[36]);
       customer.setNoTelPegawaiBank1((String) row[37]);
       customer.setNamaPegawaiBank2((String) row[38]);
       customer.setNoTelPegawaiBank2((String) row[39]);
       customer.setNamaPegawaiBank3((String) row[40]);
       customer.setNoTelPegawaiBank3((String) row[41]);
       customer.setNamaPegawaiBank4((String) row[42]);
       customer.setNoTelPegawaiBank4((String) row[43]);
       customer.setNamaPelanggan((String) row[44]);
       
       customer.setNoKadPengenalanPelanggan((String) row[45]);
       customer.setEmailPelanggan((String) row[46]);
       customer.setNamaPenjamin1((String) row[47]);
       customer.setNoKadPengenalanPenjamin1((String) row[48]);
       customer.setEmailPenjamin1((String) row[49]);
       customer.setNamaPenjamin2((String) row[50]);
       customer.setNoKadPengenalanPenjamin2((String) row[51]);
       customer.setEmailPenjamin2((String) row[52]);
       customer.setNamaPenjamin3((String) row[53]);
       customer.setNoKadPengenalanPenjamin3((String) row[54]);
       customer.setEmailPenjamin3((String) row[55]);

       
       
       return customer;

        
    }
}