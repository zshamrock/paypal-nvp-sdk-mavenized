package com.paypal.sdk.profiles;

import java.io.Serializable;
import java.text.MessageFormat;

import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.sdk.exceptions.WarningException;
import com.paypal.sdk.util.MessageResources;

public class PermissionAPIProfile extends BaseAPIProfile implements
		Serializable {

	/**
	 * The signature for Three Token authentication
	 */
	private String oauth_Signature = "";
	private String oauth_Token = "";
	private String oauth_Timestamp = "";
	private String certificateFile = "";
	private String privateKeyPassword = "";
	

	public String getCertificateFile() {
		return certificateFile;
	}

	public void setCertificateFile(String certificateFile) {
		this.certificateFile = certificateFile;
	}

	public String getPrivateKeyPassword() {
		return privateKeyPassword;
	}

	public void setPrivateKeyPassword(String privateKeyPassword) {
		this.privateKeyPassword = privateKeyPassword;
	}

	public String getOauth_Signature() {
		return oauth_Signature;
	}

	public void setOauth_Signature(String oauth_Signature) {
		this.oauth_Signature = oauth_Signature;
	}

	public String getOauth_Token() {
		return oauth_Token;
	}

	public void setOauth_Token(String oauth_Token) {
		this.oauth_Token = oauth_Token;
	}

	public String getOauth_Timestamp() {
		return oauth_Timestamp;
	}

	public void setOauth_Timestamp(String oauth_Timestamp) {
		this.oauth_Timestamp = oauth_Timestamp;
	}

	public String getFirstPartyEmail() {
		return null;
	}


	public void setFirstPartyEmail(String str) throws PayPalException {
		throw new WarningException(MessageFormat.format(MessageResources
				.getMessage("INVALID_PROFILE_OPERATION"), new Object[] {
				"signature", this.getClass().getName() }));
	}


	public void setSignature(String signature) throws PayPalException {
		throw new WarningException(MessageFormat.format(MessageResources
				.getMessage("INVALID_PROFILE_OPERATION"), new Object[] {
				"signature", this.getClass().getName() }));
	}
	public String getSignature()
    {
        return null;
    }
}
