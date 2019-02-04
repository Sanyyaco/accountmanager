package com.restapp.accountmanager;

import com.restapp.accountmanager.model.Account;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;


public class AccountmanagerApplicationTests {

	public static final String REST_SERVICE_URI = "http://localhost:8080/accounts";

	/* GET */
	@SuppressWarnings("unchecked")
	private static void listAllAccounts(){
		System.out.println("Testing listAllUsers API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> accountsMap = restTemplate.getForObject(REST_SERVICE_URI, List.class);

		if(accountsMap!=null){
			for(LinkedHashMap<String, Object> map : accountsMap){
				System.out.println("Account : id="+map.get("id")+", Value="+map.get("value"));
			}
		}else{
			System.out.println("No account exist----------");
		}
	}

	/* POST */
	private static void createAccount() {
		System.out.println("Testing create User API----------");
		RestTemplate restTemplate = new RestTemplate();
		Account account = new Account(0,1000000000);
	}

	/* GET */
	private static void getAccount(int accountId){
		System.out.println("Testing getAccount API----------");
		RestTemplate restTemplate = new RestTemplate();
		Account account = restTemplate.getForObject(REST_SERVICE_URI+"/" + accountId, Account.class);
		System.out.println(account.toString());
	}

	/* PUT */
	private static void updateAccount(int accountId, int value) {
		System.out.println("Testing update Account API----------");
		RestTemplate restTemplate = new RestTemplate();
		Account account  = new Account(accountId,value);
		restTemplate.put(REST_SERVICE_URI+"/update/"+account.getId(), account);
		System.out.println(account.toString());
	}

	/* DELETE */
	private static void deleteAccount(int accountId) {
		System.out.println("Testing delete Account API----------");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI+"/delete/" + accountId);
	}

	/* DELETE ALL */
	private static void deleteAllAccounts() {
		System.out.println("Testing all delete Accounts API----------");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI+"/deleteAll/");
	}

	public static void main(String[] args) {
		listAllAccounts();
		createAccount();
		getAccount(1);
		updateAccount(1,20000);
		deleteAccount(3);
		deleteAllAccounts();
	}

}

