package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.Co2TransactionRecords;


@Mapper
public interface Co2TransactionRecordsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Co2TransactionRecords record);

    int insertSelective(Co2TransactionRecords record);

	
	
    @Select("SELECT co2_transaction_records.token_name AS tokenName ,\r\n" + 
    		"		co2_transaction_records.token_type AS tokenType ,\r\n" + 
    		"		token_country AS tokenCountry ,\r\n" + 
    		"		co2_user.user_name AS tokenBuyAddress ,\r\n" + 
    		"		co2_transaction_records.token_sale_address AS tokenSaleAddress,\r\n" + 
    		"		co2_transaction_records.token_rate AS tokenRate ,\r\n" + 
    		"		co2_transaction_records.token_trading_time AS tokenTradingTime,\r\n" + 
    		"		co2_transaction_records.token_certificate AS tokenCertificate ,\r\n" + 
    		"		co2_transaction_records.token_amout AS tokenAmout,\r\n" + 
    		"		co2_transaction_records.token_txHash AS tokenTxhash\r\n" + 
    		"		FROM co2_transaction_records "
    		+ "LEFT JOIN co2_user  "
    		+ "ON co2_user.user_address=co2_transaction_records.token_buy_address"
    		+ "")
    List<Co2TransactionRecords> selectAll();
	
	@Select("SELECT 	co2_user.user_name AS tokenBuyAddress ,\r\n" + 
	
	"		SUM(token_amout) AS tokenAmout\r\n" + 
			
	"		FROM co2_transaction_records "
	+ "LEFT JOIN co2_user  "
	+ "ON co2_user.user_address=co2_transaction_records.token_buy_address  "
	+ "GROUP BY co2_user.user_name "
	+ "Limit 10"
	)
    List<Co2TransactionRecords> selectAllRankByUserName();

    @Select("SELECT token_country AS tokenCountry,SUM(token_amout) AS tokenAmout\r\n" + 
    		"FROM co2_transaction_records\r\n" + 
    		"WHERE token_buy_address=#{userAddress}\r\n" + 
    		"GROUP BY token_country;")
    List <Co2TransactionRecords >selectByPrimaryKey(String userAddress);

    @Select("SELECT co2_transaction_records.token_name AS tokenName ,\r\n" + 
    		"		co2_transaction_records.token_type AS tokenType ,\r\n" + 
    		"		token_country AS tokenCountry ,\r\n" + 
    		"		token_buy_address AS tokenBuyAddress ,\r\n" + 
    		"		co2_transaction_records.token_sale_address AS tokenSaleAddress,\r\n" + 
    		"		co2_transaction_records.token_rate AS tokenRate ,\r\n" + 
    		"		co2_transaction_records.token_trading_time AS tokenTradingTime,\r\n" + 
    		"		co2_transaction_records.token_certificate AS tokenCertificate ,\r\n" + 
    		"		co2_transaction_records.token_amout AS tokenAmout,\r\n" + 
    		"		co2_transaction_records.token_quantity AS tokenQuantity,\r\n" + 
    		"		co2_transaction_records.token_txHash AS tokenTxhash\r\n" + 

    		"		FROM co2_transaction_records "+ 
    		"       WHERE co2_transaction_records.token_buy_address=#{userAddress}")
    List<Co2TransactionRecords> selectUserTransaction(String address);
    
    @Select("SELECT co2_transaction_records.token_name AS tokenName ,\r\n" + 
    		"		co2_transaction_records.token_type AS tokenType ,\r\n" + 
    		"		token_country AS tokenCountry ,\r\n" + 
    		"		token_buy_address AS tokenBuyAddress ,\r\n" + 
    		"		co2_transaction_records.token_sale_address AS tokenSaleAddress,\r\n" + 
    		"		co2_transaction_records.token_rate AS tokenRate ,\r\n" + 
    		"		co2_transaction_records.token_trading_time AS tokenTradingTime,\r\n" + 
    		"		co2_transaction_records.token_certificate AS tokenCertificate ,\r\n" + 
    		"		co2_transaction_records.token_amout AS tokenAmout,\r\n" + 
    		"		co2_transaction_records.token_txHash AS tokenTxhash\r\n" + 
    		"		FROM co2_transaction_records "+ 
    		"       WHERE co2_transaction_records.token_txHash=#{userAddress}")
    Co2TransactionRecords selectCertificateByTxhash(String txash);

    
    int updateByPrimaryKeySelective(Co2TransactionRecords record);

    int updateByPrimaryKey(Co2TransactionRecords record);

       

	@Select("<script>SET @rank:=0;\r\n" + 
	"SELECT ts.*\r\n" + 
	"FROM (\r\n" + 
	"SELECT ta.*,(@rank:=@rank+1) AS id\r\n" + 
	"FROM\r\n" + 
	"(\r\n" + 
	"SELECT \r\n" + 
	"	cu.user_name AS tokenSaleAddress\r\n" + 
	"	, SUM(token_amout) AS tokenAmout\r\n" + 
	"	,token_buy_address AS tokenBuyAddress\r\n" + 
	"FROM co2_transaction_records ctr, co2_user cu\r\n" + 
	"WHERE cu.user_address = ctr.token_buy_address\r\n" + 
	"GROUP BY cu.user_name\r\n" + 
	"ORDER BY tokenAmout DESC\r\n" + 
	" 		) AS ta \r\n" + 
	"		) AS ts\r\n" + 
	"WHERE tokenBuyAddress=#{userAddress}</script>")
	Co2TransactionRecords selectCoinRankByUserAddress(String userAddress);
	
	@Select({"<script>", "SET @rank:=0;"
			, "SELECT ta.*,(@rank:=@rank+1) AS 'rank'\r\n" + 
			"FROM\r\n" + 
			"(\r\n" + 
			"SELECT \r\n" + 
			"	cu.user_name AS tokenSaleAddress\r\n" + 
			"	, SUM(token_amout) AS tokenAmout\r\n" + 
			"	,token_buy_address AS tokenBuyAddress\r\n" + 
			"FROM co2_transaction_records ctr, co2_user cu\r\n" + 
			"WHERE cu.user_address = ctr.token_buy_address\r\n" + 
			"GROUP BY cu.user_name\r\n" + 
			"ORDER BY tokenAmout DESC\r\n" + 
			" 		) AS ta \r\n" + 
			"","</script>"})
	List<Co2TransactionRecords> selectCoinRankListByUserAddress();

}
