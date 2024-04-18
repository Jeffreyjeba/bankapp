package lock;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

public class LockSupplier {

	private static Map<Long ,Userlock> lockCache =Collections.synchronizedMap(new WeakHashMap<Long, Userlock>());
	
	public static Userlock getLock(long accountNumber) {
		Userlock lock=lockCache.get(accountNumber);
		if(lock== null) {
			Userlock newLock=createNewLock();
			lockCache.put(accountNumber, newLock);
			return newLock;
		}
		else {
			return lock;
		}
	}
	
	private static Userlock createNewLock() {
		return new Userlock();
	}	
}
