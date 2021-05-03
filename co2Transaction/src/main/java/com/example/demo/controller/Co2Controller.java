package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.exceptions.MessageDecodingException;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;
import org.web3j.protocol.core.methods.request.Transaction;

import com.example.demo.mapper.Co2TransactionRecordsMapper;
import com.example.demo.mapper.Co2CountryMapper;
import com.example.demo.mapper.Co2UserMapper;
import com.example.demo.model.Co2Country;
import com.example.demo.model.Co2TransactionRecords;
import com.example.demo.model.Co2User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;



@Api(tags = "碳權API")
@Controller
public class Co2Controller {
	
	
	
	
	
	private static Web3j web3j = Web3j.build(new HttpService("https://mainnet-rpc.thundercore.com"));
	
	@Autowired
	Co2CountryMapper co2CountryMapper;

	@Autowired
	Co2UserMapper co2UserMapper;
	@Autowired
	Co2TransactionRecordsMapper co2TransactionRecordsMapper;
	public BigInteger getAddressNonce(String address) throws Exception {
		// 從乙太鏈上取得 address 的 nonce
		EthGetTransactionCount ethGetTransactionCount = web3j
				.ethGetTransactionCount(address, DefaultBlockParameterName.PENDING).sendAsync().get();
		return ethGetTransactionCount.getTransactionCount();

	}
// 

	/**
	 * 
	 * @param address
	 * @return
	 * @throws IOException
	 */
	@ApiOperation(value = "顯示使用者交易紀錄", notes = "")
	@ResponseBody
	@PostMapping("/showUserTransaction")
	public List<Co2TransactionRecords> showUserTransaction(String address) throws IOException {

		return co2TransactionRecordsMapper.selectUserTransaction(address);

	}

	@ApiOperation(value = "顯示全球國家名稱", notes = "")
	@ResponseBody
	@PostMapping("/showGlobalCountry")
	public long showGlobalCountry() throws IOException {

		List<Co2Country> list = co2CountryMapper.selectAllCountryName();

		for (int i = 0; i < list.size(); i++) {

			System.out.println(list.get(i).getCountryName());

		}

		return co2CountryMapper.allCountryCO2Ccredits();

	}

	@ApiOperation(value = "顯示全球碳信用額度", notes = "")
	@ResponseBody
	@PostMapping("/showGlobalCo2Credits")
	public long showGlobalCo2Credits() throws IOException {

		return co2CountryMapper.allCountryCO2Ccredits();

	}

	@ApiOperation(value = "顯示全球碳幣額度使用者排行", notes = "")
	@ResponseBody
	@PostMapping("/selectAllRankByUserName")
	public List<Co2TransactionRecords> selectAllRankByUserName() throws IOException {

		return co2TransactionRecordsMapper.selectAllRankByUserName();

	}

	@ApiOperation(value = "顯示各國資料", notes = "")
	@ResponseBody
	@PostMapping("/showCountry")
	public List<Co2Country> showCountry() throws IOException {

		List<Co2Country> list = co2CountryMapper.selectCountryCo2ByPage();

		return list;
	}

	@ApiOperation(value = "顯示所有國家名稱資料對照表", notes = "")
	@ResponseBody
	@PostMapping("/selectAllCountryName")
	public List<Co2Country> selectAllCountryName() throws IOException {

		List<Co2Country> list = co2CountryMapper.selectAllCountryName();

		return list;

	}

	@ApiOperation(value = "使用TXHASH顯示證書資料", notes = "" + "姓名" + "購買總量" + "TXhash" + "購買日期")

	@ResponseBody
	@PostMapping("/selectCertificateByTxhash")
	public  Co2TransactionRecords selectCertificateByTxhash(String txash) throws IOException {

//		Map<String, Co2TransactionRecords> map = new HashMap<String, Co2TransactionRecords>();
		
		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		String dateString = sdf.format(date);

		Co2TransactionRecords list = co2TransactionRecordsMapper.selectCertificateByTxhash(txash);

		Co2User co2User=co2UserMapper.selectByPrimaryKey(list.getTokenBuyAddress());
			
		String country = list.getTokenCountry();

		String bookNumber = dateString.substring(2) + country +list.getTokenAmout() +txash.substring(txash.length() - 5);

		list.setBookNumber(bookNumber);

		list.setUserName(co2User.getUserName());	
		
		return list;

	}

	
	/*
	 * 先檢查餘額是否充足
	 * 
	 * */
	@ApiOperation(value = "購買國家幣", notes = "從使用者帳戶匯出")
	@ResponseBody
	@PostMapping("/buyCountryCoin")
	public String buyCountryCoin(
			@RequestParam(value = "code") String code,
			@RequestParam(value = "fromAddress") String fromAddress,		
			@RequestParam(value = "fromPrivateKey") String fromPrivateKey,
			@RequestParam(value = "quantity") Float quantity) throws IOException, InterruptedException, ExecutionException {

		String transactionHash = "";
		
		String toAddress = "0xA7da08F27165CF25436A290f8377d6Ff670Ced16";
		Function function = new Function("balanceOf", Arrays.asList(new Address(toAddress)), 
				// Solidity Types in
				// smart contract
				// functions
				
Arrays.asList(new TypeReference<Uint256>()
{
}));
		String encodedFunction = FunctionEncoder.encode(function);
		EthCall response = web3j.ethCall(
				Transaction.createEthCallTransaction(toAddress, "0x4f3C8E20942461e2c3Bdd8311AC57B0c222f2b82", encodedFunction),
				DefaultBlockParameterName.PENDING).sendAsync().get();
		
		List<Type> someTypes = FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
 
		
		BigDecimal tmpBigDecimal = new BigDecimal(someTypes.get(0).getValue().toString())
				.divide(new BigDecimal(Math.pow(10, 6)));
		BigInteger TradeAmt=BigInteger.valueOf(quantity.longValue());
		BigInteger value = TradeAmt;
		// 傳送交易
		value = web3j.ethGasPrice().send().getGasPrice();

		// 將乙太幣轉成最小單位
		value = Convert.toWei(String.valueOf(TradeAmt), Convert.Unit.GWEI).toBigInteger();

		try {

			// 加载转账所需的凭证，用私钥
			Credentials credentials = Credentials.create(fromPrivateKey);
		
			// 获取nonce，交易笔数
//			BigInteger nonce = getAddressNonce(fromAddress);
			// get gasPrice
			BigInteger gasprice = web3j.ethGasPrice().send().getGasPrice();
//			BigInteger gaslimit = BigInteger.valueOf(48000000);

			// 取得 fromAddress 的 nonce
			BigInteger nonce = getAddressNonce(fromAddress);
			// 設定可接受的最大 gas price
			BigInteger gaslimit = Contract.GAS_LIMIT;

			// 將 value 轉成乙太鏈上的單位： wei
			BigDecimal val = new BigDecimal(String.valueOf(TradeAmt)).multiply(new BigDecimal(Math.pow(10, 6)));
			System.out.println("X=====" + val.toString());
		
			// 创建RawTransaction交易对象
			
			RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasprice, gaslimit,
					"0x4f3C8E20942461e2c3Bdd8311AC57B0c222f2b82", encodedFunction);
			// 用對方地址
//			RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, gasprice, gaslimit, toAddress,
//					value);
			byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
			String hexValue = Numeric.toHexString(signedMessage);
			EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
			transactionHash = ethSendTransaction.getTransactionHash();
			System.out.println("transactionHash===" + transactionHash);
			if (transactionHash == null) {
				return "帳號未綁定或是發送者不擁有該物品";
			}

			while (true) {
				EthGetTransactionReceipt ethReceipt = web3j.ethGetTransactionReceipt(transactionHash).send();
				if (ethReceipt.getResult() != null) {
					break;
				}
				TimeUnit.SECONDS.sleep((long) 10);
			}
		
		
			//國家扣除點數
			
			Co2Country co2Country =co2CountryMapper.selectByPrimaryKey(code);
			
			co2CountryMapper.updateCoints(co2Country.getCountryCoints()-quantity, code);
			
			//user增加點數
			
			Co2User co2User=co2UserMapper.selectByPrimaryKey(fromPrivateKey);
			
			co2UserMapper.updateCo2PointsByWallet(co2User.getUserCo2Points(), fromPrivateKey);
			
			String blockchainTransactionMessage = "<a href='https://scan.thundercore.com/transactions/" + transactionHash + "'>交易紀錄</a>";
	
		
		} catch (MessageDecodingException e) {
			return "輸入格式錯誤";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "s";

	}
	
	@ApiOperation(value = "顯示所有國家名稱代碼對照表", notes = "")
	@ResponseBody
	@PostMapping("/selectAllCountryCode")
	public Map<String, Co2Country> selectAllCountryCode() throws IOException {

		List<Co2Country> list = co2CountryMapper.selectAllCountryCode();

		Map<String, Co2Country> map = new HashMap<String, Co2Country>();

		for (int i = 0; i < list.size(); i++) {

			map.put(list.get(i).getCountryNumber(), list.get(i));

		}

		return map;

	}

	@ApiOperation(value = "紀錄使用者分享次數", notes = "")
	@ResponseBody
	@PostMapping("/countSharingTimes")
	public boolean countSharingTimes(String user) throws IOException {

//		0x2E3a5E4f6b901c90C9C43Ee426e2FeCB4B3f0D67
		int count = co2UserMapper.selectUserShareLinkById(user);

		count = count + 1;

		int update = co2UserMapper.updateUserShareById(user);

		if (update > 0) {

			return true;
		} else {

			return false;
		}

	}

	@ApiOperation(value = "用代碼找出國家碳額度", notes = "")
	@ResponseBody
	@PostMapping("/selectCountryInf")
	public Co2Country selectCountryInf(String code) throws IOException {

		Co2Country co2Country = co2CountryMapper.selectByPrimaryKey(code);

		//PHL
		System.out.println("CountryCredits==="+co2Country.getCountryCredits());
//		System.out.println("CountryCredits==="+parseNumber(",###,###.00", co2Country.getCountryCredits().));
		
		return co2Country;
	}

	@ApiOperation(value = "隨機產生碳權", notes = "主要邏輯為；選出碳權排名前二十的國家去隨機")
	@ResponseBody
	@PostMapping("/selectRandon")
	public Co2Country selectRandon() throws IOException {

		List<Co2Country> co2Country = co2CountryMapper.select20CountryInf();

		int i = (int) (Math.random() * 20);

//		System.out.println("i===="+i);

		return co2Country.get(i);
	}

	/*
	 * 外部註冊三步驟
	 * 
	 * 一、檢查帳號使否存在 1.來源(FB、IG) 
	 * 2.帳號 二、回傳PK 1.如果存在select PK 2.如果不存在存入PK
	 * 
	 */
	/*
	 * 註冊第一步
	 */
	@ApiOperation(value = "外部用戶註冊第一步\r\n" + "輸入社群種類以及帳號或ID回傳該帳號的PK", notes = "檢查帳號是否存在\r\n" + "需要欄位\r\n"
			+ "社群來源FB或其他社群；community\r\n" + "社群帳號或ID:account")
	@ResponseBody
	@PostMapping("/selectPrivateKey")
	public String selectPrivateKey(String account, String community) throws Exception {
		System.out.println("account==" + account);
		System.out.println("community" + community);

		try {
			String s = co2UserMapper.selectPrivateKey(account, community);
			System.out.println("s====" + s);
			if (s != null) {

				return s;
			}
			return "unsuccessful";
		} catch (NullPointerException e) {
			// TODO: handle exception

			return e.toString();
		}

	}

	@ApiOperation(value = "外部用戶註冊第二步\r\n" + "存入私鑰" + "", notes = "帳號沒有PK時呼叫")
	@ResponseBody
	@PostMapping("/insertForOut")
	public String insertForOut(String account, String community, String user_address, String user_pkey ,String userName

	) throws Exception {

		int s = co2UserMapper.selectCount(user_address);

		if (s > 0) {

			System.out.println("S>0");

			return "user_address duplication";
		} else {

			System.out.println("S!>0");

			int i = co2UserMapper.insertForOut(account, community, user_address, user_pkey);
			
			System.out.println("i==" + i);

			if (userName==""||userName==null) {
				userName="Username";
			}
			co2UserMapper.updateNameByWallet(user_address,userName);
			if (i > 0) {
				return "success";
			} else {
				return "false";
			}

		}

	}

	@ApiOperation(value = "顯示使用者碳權交易總額名次", notes = "排行 名稱 總額")
	@ResponseBody
	@PostMapping("/showUserCo2Rank")
	public JSONObject showUserCo2Rank(
			String userAddress) throws Exception {

		JSONObject js = new JSONObject();

		Co2User co2User = co2UserMapper.selectByPrimaryKey(userAddress);

		js.put("rank", co2UserMapper.selectUserRank(userAddress) + 1);
		js.put("co2User", co2User);

		return js;

	}

	@ApiOperation(value = "修改用戶名稱", notes = "")
	@ResponseBody
	@PostMapping("/updateNameByWallet")
	public Boolean updateNameByWallet(String address,

			String name) throws Exception {

		if (isETHValidAddress(address) && co2UserMapper.selectByPrimaryKey(address) == null) {

			// 多一欄
			co2UserMapper.insert(name, address);
			return true;

		}

		if (co2UserMapper.updateNameByWallet(address, name) > 0) {

			return true;
		}

		return false;
	}

	@ApiOperation(value = "取出使用者帳戶首頁數據", notes = "")
	@ResponseBody
	@PostMapping("/selectCo2UserByUserAddressForFirstPage")
	public JSONObject selectCo2UserByUserAddressForFirstPage(String userAddress) throws IOException {

		Co2User co2User = co2UserMapper.selectByPrimaryKey(userAddress);
		JSONObject obj = new JSONObject();
		if (co2User != null) {

			float f = co2User.getUserCo2Points();

			System.out.println("getUserCo2Points===" + f);

			Map<String, Float> map = new HashMap<String, Float>();

			List<Co2TransactionRecords> list = co2TransactionRecordsMapper.selectByPrimaryKey(userAddress);

			for (int i = 0; i < list.size(); i++) {

				map.put(list.get(i).getTokenCountry(), list.get(i).getTokenAmout());

			}

			obj.put("Co2", map);
			obj.put("id", co2User.getId());
			obj.put("totalCo2", controlNumber(f));
			obj.put("gasoline", controlNumber(f * 113));
			obj.put("Driving", controlNumber(f * 2481));
			obj.put("coal", controlNumber(f * 1102));
			obj.put("smartphone", controlNumber(f * 127532));
			obj.put("beef", controlNumber(f * 9.7));
			obj.put("BBQ", controlNumber(f * 40.9));
			obj.put("userAddress", userAddress);

			return obj;
			
		} else {

			co2UserMapper.insert("Username", userAddress);

			obj.put("Co2", "");
			obj.put("id", co2UserMapper.selectByPrimaryKey(userAddress).getId());
			obj.put("totalCo2", 0);
			obj.put("gasoline", 0);
			obj.put("Driving", 0);
			obj.put("coal", 0);

			obj.put("smartphone", 0);
			obj.put("beef", 0);
			obj.put("BBQ", 0);
			obj.put("userAddress", userAddress);

			return obj;
		}
		
	}

	// 控制輸出數字的位數
	public String controlNumber(double d) {
//		(a+"").length()
		if (d < 10000) {

			d = ((int) (d * 100)) / 100;

			return String.valueOf(d);

		} else if (d < 100000 && d >= 10000) {

			d = ((int) (d * 10)) / 10;

			return String.valueOf(d);

		} else if (d < 1000000 && d >= 100000) {
			d = (int) d / 1;
			return String.valueOf(d);
		} else if (d < 10000000 && d >= 1000000) {
			d = (int) d / 1000;
			return String.valueOf(d) + "k";
		} else {

			d = (int) d / 1000000;

			return String.valueOf(d) + "M";
		}

	}

	public static boolean isETHValidAddress(String input) {
		if (input.isEmpty() || !input.startsWith("0x"))
			return false;
		return isValidAddress(input);
	}

	public static boolean isValidAddress(String input) {
		String cleanInput = Numeric.cleanHexPrefix(input);

		try {
			Numeric.toBigIntNoPrefix(cleanInput);
		} catch (NumberFormatException e) {
			return false;
		}

		return cleanInput.length() == 40;
	}

	@Value("${config.uploadFiles.path}")
	private String file_path;
	
	 @Value("${filePath}")
	    private String filePath;
	
//	 @GetMapping("/upload")
//	    public String uploading() {
//	        //跳轉到 templates 目錄下的 uploading.html
//	        return "uploading";
//	    }
	 
	/**
	 * 頭像圖片上傳
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploading", method = RequestMethod.POST)
	public void saveHeaderPic(@RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		
		System.out.println("saveHeaderPic");
		
		if (!file.isEmpty()) {

			  try {
		            uploadFile(file.getBytes(), filePath, file.getOriginalFilename());
		        } catch (Exception e) {
		            e.printStackTrace();
		            System.out.println("檔案上傳失敗!");
		       
		        }
		        System.out.println("檔案上傳成功!");
		}
//
//		
//		String resMsg = "";
//		try {
//
//			long startTime = System.currentTimeMillis();
//
//			System.out.println("fileName：" + file.getOriginalFilename());
//			String path = "/Users/loukai/easylife/files/" + new Date().getTime() + file.getOriginalFilename();
//			System.out.println("path:" + path);
//
//			File newFile = new File(path);
//			// 通過CommonsMultipartFile的方法直接寫檔案
//			file.transferTo(newFile);
//			long endTime = System.currentTimeMillis();
//			System.out.println("執行時間：" + String.valueOf(endTime - startTime) + "ms");
//			resMsg = "1";
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			resMsg = "0";
//		}
//		response.getWriter().write(resMsg);

	}

	 public void  uploadFile(byte[] file, String filePath, String fileName) throws Exception { 
	        File targetFile = new File(filePath);  
	        if(!targetFile.exists()){    
	            targetFile.mkdirs();    
	        }       
	        FileOutputStream out = new FileOutputStream(filePath+fileName);
	        out.write(file);
	        out.flush();
	        out.close();
	    }
	// save file
	private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue; // next pls
			}
//			String fileDirPath = new String("src/main/resources" + UPLOADED_FOLDER);

			byte[] bytes = file.getBytes();

			Path path = Paths.get(file_path + file.getOriginalFilename());

			Files.write(path, bytes);

		}

	}
	
}
