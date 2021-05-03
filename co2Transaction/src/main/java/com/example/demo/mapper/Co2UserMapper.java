package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.model.Co2User;
@Mapper
public interface Co2UserMapper {
    int deleteByPrimaryKey(String userAddress);

    
    @Insert("INSERT INTO co2_user("
    		+ "co2_user.user_name,"
  
    		+ "co2_user.user_address)\r\n" + 
    		"VALUES ( #{name}, #{address})")
    int insert(String name,String address);
   
    @Select("SELECT  "
    		
    		+ "count(*)  "  
			+ "FROM co2_user "		
			+ "WHERE user_address = #{user_address}"	
    		)
    int selectCount(String user_address);

    @Select("SELECT co2_user.user_address_private_key\r\n" + 
    		"FROM co2_user\r\n" + 
    		"WHERE co2_user.user_account=#{account}\r\n" 
    	
    		)
    String selectPrivateKey(String account,String community);
    
     
    @Insert("INSERT INTO co2_user("
    		+ "co2_user.user_account,"
    		+ "co2_user.user_community_kind,"	
    	
    		+ "co2_user.user_address, "
    		+ "co2_user.user_address_private_key) " + 
    		"VALUES ( #{account}, #{community}, #{user_address}, #{user_pkey})")
    int insertForOut(String account,String community,String user_address, String user_pkey);
    
 
    
    @Update("UPDATE co2_user\r\n" + 
    		"SET"
    		+ " user_name= #{name}\r\n" + 
    		 ", user_account= #{account}\r\n" + 
    		
    		"WHERE user_address=#{wallet}")
    int updateForOut(String wallet,String account,String name);

    
    @Update("UPDATE co2_user\r\n" + 
    		"SET user_name= #{name}\r\n" + 
    		
    		"WHERE user_address=#{wallet}")
    int updateNameByWallet(String wallet,String name);

    @Update("UPDATE co2_user\r\n" + 
    		"SET user_co2_points= #{point}\r\n" + 
    		
    		"WHERE user_address_private_key=#{wallet}")
    int updateCo2PointsByWallet(Float point,String wallet);

    
    @Update("UPDATE co2_user\r\n" + 
    		"SET user_share_link= #{name}\r\n" + 
    		
    		"WHERE id=#{id}")
    int updateUserShareById(String user);
    
    int insertSelective(Co2User record);

    
    @Select("SELECT  "
			+ "user_name as userName ,  "  
			+ "user_address as userAddress ,  "  
			+ "user_co2_points as userCo2Points ,  "  
			+ "user_tree_points as userTreePoints ,  "  
			+ "user_share_link as userShareLink ,  "  
			+ "user_account as userAccount   "  
						
			+ "FROM co2_user "		
			
			)
    List<Co2User> selectAll();
   
    
    @Select("SELECT  "
    		+ "id ,  "  
			+ "user_name as userName ,  "  
			+ "user_address as userAddress ,  "  
			+ "user_co2_points as userCo2Points ,  "  
			+ "user_tree_points as userTreePoints ,  "  
			+ "user_address_Platform_key as userAddressPlatformKey ,  "  
			+ "user_address_private_key as userAddressPrivateKey   "  			
			+ "FROM co2_user "		
			+ "WHERE user_address = #{userAddress}"	
			)
    Co2User selectByPrimaryKey(String userAddress);
   
    @Select("SELECT  "
    		
    		+ "user_share_link  "  
    	
			
			+ "FROM co2_user "		
			+ "WHERE id = #{id}"	
    		)
    int selectUserShareLinkById(String userAddress);
    @Select("SELECT COUNT(*)\r\n" + 
    		"FROM co2_user\r\n" + 
    		"WHERE co2_user.user_co2_points>\r\n" + 
    		"(\r\n" + 
    		"SELECT co2_user.user_co2_points\r\n" + 
    		"FROM co2_user\r\n" + 
    		"WHERE co2_user.user_address=#{userAddress}\r\n" + 
    		") "	
    		)
    int selectUserRank(String userAddress);

    
    
    int updateByPrimaryKeySelective(Co2User record);

    int updateByPrimaryKey(Co2User record);
}