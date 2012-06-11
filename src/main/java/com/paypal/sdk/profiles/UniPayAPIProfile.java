package com.paypal.sdk.profiles;

import java.io.Serializable;
import java.text.MessageFormat;

import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.sdk.exceptions.WarningException;
import com.paypal.sdk.util.MessageResources;

/**
 * API Profile with UniPay.
 * 
 * @author PayPal DTS
 */
public class UniPayAPIProfile extends BaseAPIProfile implements Serializable {
	/**
	 * The email for UniPay auth model
	 */
	private transient String emailSubject = "";

	/*
	 * Return the signature for UniPay authentication
	 * 
	 * @return the emailSubject
	 */
	public String getFirstPartyEmail() {
		return this.emailSubject;
	}

	/**
	 * Set the Subjectemail for UniPay authentication
	 * 
	 * @param Subjectemail
	 *            the Subjectemail
	 */
	public void setFirstPartyEmail(String signature) {
		this.emailSubject = signature;
	}

	/**
	 * Return the signature for Three Token authentication
	 * 
	 * @return the signature
	 */
	public String getSignature() {
		return null;
	}

	/**
	 * Set the signature for Three Token authentication
	 * 
	 * @param signature
	 *            the signature
	 */
	public void setSignature(String signature) throws PayPalException {
		throw new WarningException(MessageFormat.format(MessageResources
				.getMessage("INVALID_PROFILE_OPERATION"), new Object[] {
				"signature", this.getClass().getName() }));
	}

	/**
	 * Return the certificate filename used by this profile to authenticate
	 * itself to PayPal
	 * 
	 * @return the certificate filename used by this profile to authenticate
	 *         itself to PayPal
	 */
	public String getCertificateFile() {
		return null;
	}

	/**
	 * Set the certificate filename used by this profile to authenticate itself
	 * to PayPal
	 * 
	 * @param certificateFile
	 *            the certificate filename used by this profile to authenticate
	 *            itself to PayPal
	 */
	public void setCertificateFile(String certificateFile)
			throws PayPalException {
		throw new WarningException(MessageFormat.format(MessageResources
				.getMessage("INVALID_PROFILE_OPERATION"), new Object[] {
				"certificateFile", this.getClass().getName() }));
	}

	/**
	 * Return the private key password
	 * 
	 * @return the private key password
	 */
	public String getPrivateKeyPassword() {
		return null;
	}

	/**
	 * Set the private key password
	 * 
	 * @param privateKeyPassword
	 *            the private key password
	 */
	public void setPrivateKeyPassword(String privateKeyPassword)
			throws PayPalException {
		throw new WarningException(MessageFormat.format(MessageResources
				.getMessage("INVALID_PROFILE_OPERATION"), new Object[] {
				"privateKeyPassword", this.getClass().getName() }));
	}

	public String getOauth_Signature() {
		return null;
	}

	public void setOauth_Signature(String oauth_Signature)
			throws PayPalException {
		throw new WarningException(MessageFormat.format(MessageResources
				.getMessage("INVALID_PROFILE_OPERATION"), new Object[] {
				"OauthSignature", this.getClass().getName() }));
	}

	public String getOauth_Token() {
		return null;
	}

	public void setOauth_Token(String oauth_Token) throws PayPalException {
		throw new WarningException(MessageFormat.format(MessageResources
				.getMessage("INVALID_PROFILE_OPERATION"), new Object[] {
				"OauthToken", this.getClass().getName() }));
	}

	public String getOauth_Timestamp() {
		return null;
	}

	public void setOauth_Timestamp(String oauth_Timestamp)
			throws PayPalException {
		throw new WarningException(MessageFormat.format(MessageResources
				.getMessage("INVALID_PROFILE_OPERATION"), new Object[] {
				"OauthTimestamp", this.getClass().getName() }));
	}
} // UniPayAPIProfile
