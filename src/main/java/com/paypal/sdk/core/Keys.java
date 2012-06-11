package com.paypal.sdk.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.KeyManager;

public class Keys {
	private static final Map KEYMANAGERS = Collections.synchronizedMap(new HashMap());

	/**
	 * Registers new key managers with the given identifier. If the key managers with
	 * the given ID already exists it will be overridden.
	 * 
	 * @param id the identifier for this protocol
	 * @param keymanagers the key managers to register
	 */
	public static void registerKeys(String id, KeyManager[] keymanagers) {
		if (id == null) 
		{
			throw new IllegalArgumentException("ID is null");
		}
		if (keymanagers == null) 
		{
			throw new IllegalArgumentException("Key managers is null");
		}
		KEYMANAGERS.put(id, keymanagers);
	}

	/**
	 * Unregisters the key managers with the given ID.
	 * 
	 * @param id the ID of the key managers to remove
	 */
	public static void unregisterKeys(String id) {
		if (id == null) 
		{
			throw new IllegalArgumentException("ID is null");
		}
		KEYMANAGERS.remove(id);
	}
	
	/**
	 * Returns true if the key managers map one or more keys to the specified value.
	 * 
	 * @param id the ID of the key managers to remove
	 */
	public static boolean containsKey(String id) {
		if (id == null) 
		{
			throw new IllegalArgumentException("ID is null");
		}
		return KEYMANAGERS.containsKey(id);
	}

	/**
	 * Gets the SSL Context with the given ID.
	 * 
	 * @param id the SSL context ID 
	 * @return KeyManager[] the key managers
	 * @throws IllegalStateException if a protocol with the ID cannot be found
	 */
	public static KeyManager[] getKeyManagers(String id) throws IllegalStateException {
		if (id == null) 
		{
			throw new IllegalArgumentException("ID is null");
		}
		return (KeyManager[]) KEYMANAGERS.get(id);
	} 
}