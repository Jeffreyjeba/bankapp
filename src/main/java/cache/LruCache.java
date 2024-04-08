package cache;

import java.util.LinkedHashMap;
import java.util.Map;
import database.CustomerService;
import utility.BankException;

import org.json.JSONArray;


public class LruCache<K,V> extends LinkedHashMap<K, V> {
	
	
	private static final long serialVersionUID = 1L;
	private int size;
	
	private  LruCache( int size) {
		super(size,0.75f,true);
		this.size=size;
	}
	
	
	@Override
	public boolean removeEldestEntry(Map.Entry<K, V> old) {
		return size()>size;
	}

	public static <K,V> LruCache<K, V> getLru(int size) {
		return new LruCache<K, V>(size);
	}
	
	private static final LruCache<String,JSONArray>  lruObj= new LruCache<String,JSONArray>(10);
	
	CustomerService csObj=CustomerService.getCustomerService();
	
	
	public JSONArray getAccounts(long id) throws BankException {
		String idString=Long.toString(id);
		if(checkPresence(idString)) {
			return  lruObj.get(idString);
		}
		else {
			JSONArray jArray= csObj.getAccounts(id);
			lruObj.put(idString, jArray);
			return jArray;
		}
	}
	
	private boolean checkPresence(String name) {
		return lruObj.containsKey(name);
	}
	
	
	
	
}
