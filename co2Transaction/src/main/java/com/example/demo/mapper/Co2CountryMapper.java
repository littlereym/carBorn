package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.model.Co2Country;

@Mapper
public interface Co2CountryMapper {
    
	
	int deleteByPrimaryKey(String countryWalletAddress);
	
	@Insert("<script>" +
	    		"INSERT INTO co2_country\r\n" + 
	    		"(\r\n" + 
	    		"country_name,\r\n" + 
	    		"country_forest_area,\r\n" + 
	    		"country_credits,\r\n" + 
	    		"country_coints\r\n" + 
	    		")VALUES(\r\n" + 
	    		"#{countryName},\r\n" + 
	    		"#{countryForestArea},\r\n" + 
	    		"#{countryCredits},\r\n" +
	    		"#{countryCoints})"
	    		+ "</script>")
    int insert(Co2Country record);

	
	@Update(
    		"UPDATE co2_country\r\n" + 
    		"SET "
    		+ " co2_country.country_Longitude=#{country_Longitude},\r\n"  
    		+ " co2_country.country_Latitude=#{country_Latitude}\r\n" + 
    		
    		"WHERE country_number=#{country_number}"
    	
    		)
int insertCountryNumber(String country_Longitude,String country_Latitude,String country_number);
	
	@Update(
			"UPDATE co2_country\r\n" + 
					"SET "
					+"country_coints=#{coint}"+
    		"WHERE country_number=#{country_number}"
    		
			)
	int updateCoints(float coint,String country_number);
	
	@Select("SELECT  "
			+ "SUM(co2_country.country_credits)  "
			
			+"FROM co2_country   "		
			)
	long allCountryCO2Ccredits();
	
	
	@Select("SELECT  "

		+ "SUM(co2_country.country_coints)\r\n" + 
			"FROM co2_country"		
			)
	long allCountryCO2Coints();
   
	
	
	@Select("SELECT  "
			+ "country_name as countryName ,  "  
			+ "country_forest_area as countryForestArea ,  "  
		
			+ "country_credits as countryCredits ,  "  
			+ "country_coints as countryCoints   "  
			+ "FROM co2_country "		
			+ "WHERE country_name = #{countryName}"
			+ ""	
			)
	Co2Country selectByName(String countryName);
	
	@Select("SELECT  "
			+ "country_name as countryName ,  "  
			+ "country_forest_area as countryForestArea ,  "  		
			+ "country_credits as countryCredits ,  "  
			+ "country_coints as countryCoints ,  "  
			+ "country_wallet_address as countryWalletAddress ,  "  		
			+ "country_Longitude as countryLongitude ,  "  
			+ "country_Latitude as countryLatitude   "  
			+ "FROM co2_country "		
			+ "WHERE country_number = #{code}"	
			)
	Co2Country selectByPrimaryKey(String code);

	
	//改成依照國家名稱排序
	@Select("SELECT  "

			+ "country_name as countryName ,  "  
			+ "country_forest_area as countryForestArea ,  "  		
			+ "country_number as countryNumber ,  "  		
			  		
			+ "country_credits as countryCredits ,  "  
			+ "country_coints as countryCoints   "  
			+ "FROM co2_country "		
//			+ "ORDER BY  country_credits DESC   "
			+ "ORDER BY  country_name    "

			)
    List<Co2Country> selectCountryCo2ByPage();

	@Select("SELECT  "
			+ "country_name as countryName ,  "  
			+ "id "  
			
			+ "FROM co2_country "		
			+ "ORDER BY  country_credits DESC   "

			
			)
    List<Co2Country> selectAllCountryName();
	
	@Select("SELECT  "
			+ "country_name as countryName ,  "  
			+ "country_forest_area as countryForestArea ,  "  		
			+ "country_credits as countryCredits ,  "  
			+ "country_coints as countryCoints ,  "  
			+ "country_number as countryNumber ,  "  
			+ "country_Longitude as countryLongitude ,  "  
			+ "country_Latitude as countryLatitude   "   	
			+ "FROM co2_country "		
			+ "ORDER BY  country_credits DESC   "

			
			)
    List<Co2Country> selectAllCountryCode();
	
	@Select("SELECT  "
			+ "country_name as countryName ,  "  
			+ "country_forest_area as countryForestArea ,  "  
			+ "country_credits as countryCredits ,  "  
			+ "country_coints as countryCoints ,  "  
			+ "country_wallet_address as countryWalletAddress ,  "  
			+ "country_number as countryNumber ,  "  
			+ "country_Longitude as countryLongitude ,  "  
			+ "country_Latitude as countryLatitude ,  "  
			+ "id "  
			+ "FROM co2_country "		
			+ "ORDER BY  country_credits DESC  "
			+ " LIMIT 20  "		
			)
    List<Co2Country> select20CountryInf();
	
	@Select("SELECT  "
			+ "country_name as countryName ,  "  
			+ "country_forest_area as countryForestArea ,  "  
			+ "country_credits as countryCredits ,  "  
			+ "country_coints as countryCoints ,  "  
			+ "country_wallet_address as countryWalletAddress ,  "  
			+ "country_number as countryNumber ,  "  
			+ "country_Longitude as countryLongitude ,  "  
			+ "country_Latitude as countryLatitude ,  "  
			+ "id "  
			+ "FROM co2_country "		
			+ "ORDER BY  country_credits DESC   "		
			)
    List<Co2Country> selectAllCountryInf();
	
    int updateByPrimaryKeySelective(Co2Country record);

    int updateByPrimaryKey(Co2Country record);
}