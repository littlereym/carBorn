package com.example.demo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.model.BackstageAccount;



@Mapper
public interface BackstageAccountMapper {

	@Select("<script>" 
			+ "SELECT "
    		+ "id as id ,"
			+ "account_name as accountName,"
//    		+ "account_password as accountPassword,"
			+ "account_permission_level as accountPermissionLevel ," 
			+ "account_status as accountStatus ,"
			+ "account_last_login_time as accountLastLoginTime  " 
			+ "FROM backstage_account " 
			+ "ORDER BY account_last_login_time  DESC; "
			+ "</script>")
	List<BackstageAccount> selectAll();

	@Select("<script>" 
			+ "SELECT " 
			+ "id as id ," 
			+ "account_name as accountName,"
			+ "account_password as accountPassword," 
			+ "account_permission_level as accountPermissionLevel ,"
			+ "account_status as accountStatus ," 
			+ "account_last_login_time as accountLastLoginTime  "
			+ "FROM backstage_account " 
			+ "WHERE account_name = #{userName}; " 
			+ "</script>")
	BackstageAccount findByaccountName(String userName);

	@Select("<script>" 
			+ "SELECT " 
			+ "count(*)" 
			+ "FROM backstage_account " 
			+ "WHERE account_name = #{userName}; "
			+ "</script>")
	Integer determineName(String userName);

	int deleteByPrimaryKey(Integer id);

	@Insert("<script>" 
			+ "INSERT INTO " 
			+ "backstage_account(" 
			+ "backstage_account.account_name,"
			+ "backstage_account.account_password," 
			+ "backstage_account.account_permission_level,"
			+ "backstage_account.account_status," 
			+ "backstage_account.account_last_login_time)" 
			+ "VALUES" 
			+ " ("
			+ "#{accountName}, " 
			+ "#{accountPassword}, " 
			+ "#{accountPermissionLevel}, " 
			+ "#{accountStatus}, "
			+ "#{accountLastLoginTime} " 
			+ ")" 
			+ "</script>")
	int insert(BackstageAccount record);

	int insertSelective(BackstageAccount record);

	BackstageAccount selectByPrimaryKey(Integer id);

	@Update("<script>" 
	+ "UPDATE backstage_account " 
	+ "SET backstage_account.account_last_login_time = #{date}"
	+ "WHERE backstage_account.account_name= #{userName}  "
	+ "</script>"
	)
	int updateByLoginTime(String userName, Date date);

	
	@Update("<script>" 
			+ "UPDATE backstage_account " 
			+ "SET backstage_account.account_status = #{status}"
			+ "WHERE backstage_account.id= #{id}  "
			+ "</script>"
			)
			int stope(String id, String status);
	
	@Update("<script>" 
			+ "DELETE FROM backstage_account " 
			
			+ "WHERE backstage_account.id= #{id}  "
			+ "</script>"
			)
	int delete(String id);

	
	@Update("<script>" 
			+ "UPDATE backstage_account " 
			+ "SET backstage_account.account_password = #{password}"
			+ "WHERE backstage_account.account_name= #{userName}  "
			+ "</script>"
			)
	int updateByPassWord(String userName, String password);

	
	int updateByPrimaryKey(BackstageAccount record);
}