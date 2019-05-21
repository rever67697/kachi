package com.team.util;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.lang3.StringUtils;

public class AESmixRSAUtil {

	private final static String EMPTY_RESULT = "";

	private final static String AES_ALGORITHM = "AES";

	private final static String TEST_KEY_NAME = "_MOCK_TEST_KEY_";

	/**
	 * 测试环境密钥：	ceoYuXDvYI1CjGe6ciLemw==
	 * sit环境密钥：	boaHUNSrlz6VBU4/VFwabA==
	 * 正式环境密钥：PeRDDudk9VF94RNgD8Mq5w==
	 * http://www.gzhealth.gov.cn/rhin_portal/site/register?openId=2088102119561183
	 * &idNum=+oMA5oaqYlfnGUvmU0V6VzFUqWgBIewBFBTjEAo4alU=
	 * &telNumber=18UjLlvZl2zEECtLdNWsAA==
	 * &isPersonTrue=true&sign=sVB86l75x3xVoKm7Iq9z4+UpBvPazm8yaAGxtnkx0naRSLkqZl4aK/hh7qW8ypWeCqf7+pb3ZCMch2a4zsvGkWIhTiFNLZbOw6Dg8SFZ06qYDXiCpm1PJSgGn6u8gXFl7PkxrAwsMY+qh7WQ9nBTre+0NtT84RE//a4GUGXb2jvBczJez4WyV74x2XX84r9qHSOuFdA4C6gaTRZUPqCnbpiSq34gt0oF52eySicq4f/5sQXbYctiUo+vCUlI/uR8dfFvJorUJVtiwwmvQFnB4eDrq4hvb5DRYD62b6zLvv39Rc2iQfOaKE4x9LWzFUdW15+qB4ReGyj6mck9oHulSg==
	 */
	private final static String AES_KEY = "PeRDDudk9VF94RNgD8Mq5w==";

	private final static String RSA_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA11ynQ0uul7URxYFB4IkCUVP3F0Xi/xeUtzFXgoeb3G6s7dKMM+z9ppzJZ/ljUaMiyxhaX5TBE078KToN04I0dBUJh7JUNIvKrkWP4Vibrsgby8Iy7f8SSFEBtmLmxz7zZIAzndPqHS0MAoCeGPmUgM1F6LfyUdnpCoGaMyiESdFn59PngSwC4eJXQqNnxUF5RCth0PrEfN67llgYM2ki4jhCSL0EmQnJMwzWWZSCuy8jVt22xgt/q8A4MJ4tKbaT4kWgLAorPW809iLwO1lRf9SLHHpXD0iRmpfslKg2UTxknjxayRg35egBj79oiVIwdH6ab+XAXBWb5uzWnswnNwIDAQAB";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String str = "440104195909256418";
			System.out.println("加密前=="+str);
			String encrypt = AESmixRSAUtil.encrypt(str);
			System.out.println("加密后=="+encrypt);
			System.out.println("解密后=="+AESmixRSAUtil.decrypt(encrypt));


			//http://www.gzhealth.gov.cn/rhin_portal/wx/jkda/healthEvents.html?CODE=undefined&OPEN_ID=oPtA3uKL6StuTyCK07XDrkUc6I04&CHANNEL=WAP&cardNumber=3WlztA30Vx4y3jkNt/YYVSbBH3kLEad8lJXOH6wapNo=&cardType=01&rnd=041926825027732606

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * 使用公钥进行验签的方法
	 *
	 * @param text
	 *            原始数据数据
	 * @param signedText
	 *            签名过的数据
	 * @param publicKeyData
	 *            公钥数据
	 * @param algorithm
	 *            签名算法,目前KMI支持NONEwithRSA, MD2withRSA, MD5withRSA, SHA1withRSA,
	 *            SHA256withRSA, SHA384withRSA, SHA512withRSA , SHA1withDSA
	 * @return 如果验签成功,返回true,验签失败,返回false
	 * @throws GeneralSecurityException
	 */
	public static boolean verify(final byte[] text, final byte[] signedText,
								 final byte[] publicKeyData, final String algorithm)
			throws GeneralSecurityException {
		final PublicKey publicKey = getPublicKey(publicKeyData, algorithm);
		final Signature signatureChecker = Signature.getInstance(algorithm);
		signatureChecker.initVerify(publicKey);
		signatureChecker.update(text);
		return signatureChecker.verify(signedText);
	}

	/**
	 * 得到公钥
	 *
	 * @param keyData
	 *            密钥数据
	 * @throws GeneralSecurityException
	 */
	private static PublicKey getPublicKey(final byte[] keyData,
										  final String algorithm) throws GeneralSecurityException {
		final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyData);
		final KeyFactory keyFactory = KeyFactory.getInstance(StringUtils
				.substringAfter(algorithm, "with"));
		return keyFactory.generatePublic(keySpec);
	}

	/**
	 * 加密敏感信息(无防篡改功能)
	 *
	 * @param keyName
	 *            密钥别名
	 * @param data
	 *            明文敏感数据(原始string)
	 * @return Base64编码后的密文数据
	 */
	public static String encrypt(String keyName, String data) {
		if (StringUtils.isBlank(keyName) || StringUtils.isBlank(data)) {
			System.out.println("入参不能为空: keyName=" + keyName + ", data=" + data);
			return EMPTY_RESULT;
		}
		return enOrDecrypt(keyName, Base64.encode(data.getBytes()),
				Cipher.ENCRYPT_MODE);
	}

	public static String encrypt(String data) {
		return encrypt(TEST_KEY_NAME, data);
	}

	/**
	 * 解密敏感信息(无防篡改功能)
	 *
	 * @param keyName
	 *            密钥别名
	 *            密文数据(base64编码的string)
	 * @return 明文数据(原始string, 非base64编码)
	 */
	public static String decrypt(String keyName, String data) {
		// 入参校验
		if (StringUtils.isBlank(keyName) || StringUtils.isBlank(data)) {
			System.out.println("入参不能为空: keyName=" + keyName + ", data=" + data);
			return EMPTY_RESULT;
		}

		String originalBase64Data = enOrDecrypt(keyName, data,
				Cipher.DECRYPT_MODE);
		String result = null;
		try {
			result = new String(Base64.decode(originalBase64Data));
		} catch (Exception e) {
			System.out.println("Base64解码失败，originalBase64Data＝"
					+ originalBase64Data + e.getMessage());
		}
		return result;
	}

	public static String decrypt(String data) {
		return decrypt(TEST_KEY_NAME,data);
	}

	/**
	 * 加/解密
	 *
	 * @param keyName
	 *            密钥别名
	 * @param data
	 *            明文/密文卡号(base64编码的string)
	 * @param mode
	 *            加解密标识：加密——Cipher.ENCRYPT_MODE；解密——Cipher.DECRYPT_MODE
	 * @return 加解密的结果
	 */
	@SuppressWarnings("static-access")
	private static String enOrDecrypt(String keyName, String data, int mode) {
		try {
			// 1. 获取密钥
			String key = "";
			if (StringUtils.equals(TEST_KEY_NAME, keyName)) {
				key = AES_KEY;
			} else {
				key = AES_KEY;
			}
			// 2. 加解密结果进行Base64编码后返回
			byte[] resultBytes = symmtricCrypto(Base64.decode(data),Base64.decode(key), AES_ALGORITHM, mode);
			return Base64.encode(resultBytes);
		} catch (Exception e) {
			System.out.println("未知异常, [ keyName=" + keyName + ", data=" + data
					+ ", mode=" + mode + "]");
			return EMPTY_RESULT;
		}
	}

	public static byte[] symmtricCrypto(byte[] text, byte[] keyData,
										String algorithm, int mode) throws GeneralSecurityException {
		String fullAlg = algorithm + "/CBC/PKCS5Padding";
		byte[] iv = initIv(fullAlg);
		return doCrypto(text, keyData, iv, fullAlg, "CBC", "PKCS5Padding", mode);
	}

	private static byte[] initIv(String fullAlg)
			throws GeneralSecurityException {
		Cipher cipher = Cipher.getInstance(fullAlg);
		int blockSize = cipher.getBlockSize();
		byte[] iv = new byte[blockSize];
		for (int i = 0; i < blockSize; ++i) {
			iv[i] = 0;
		}
		return iv;
	}

	/**
	 * 实现加解密的方法
	 *
	 * @param text
	 *            待加/解密的数据
	 * @param keyData
	 *            密钥数据
	 * @param iv
	 *            初始向量
	 * @param fullAlg
	 *            对称加密算法全名。eg.DESede/CBC/PKCS5Padding
	 * @param padding
	 *            填充模式,目前KMI接受的参数有PKCS5Padding和NoPadding.
	 * @param mode
	 *            加解密标识：加密——Cipher.ENCRYPT_MODE；解密——Cipher.DECRYPT_MODE。
	 * @return 密文(加密)/明文（解密）。
	 * @throws GeneralSecurityException
	 *             当用户输入KMI不接受的参数时,会抛出异常 当密钥数据的长度不符合算法要求时,会抛出异常
	 *             在NoPadding填充模式下,当待加密的数据不是相应的算法的块大小的整数倍时,会抛出异常
	 */
	public static byte[] doCrypto(byte[] text, byte[] keyData, byte[] iv,
								  String fullAlg, String workingMode, String padding, int mode)
			throws GeneralSecurityException {
		if (!StringUtils.equals(workingMode, "CBC")
				&& !StringUtils.equals(workingMode, "ECB")) {
			throw new GeneralSecurityException("错误的工作模式,目前KMI只支持CBC和ECB两种工作模式");
		}

		if (!StringUtils.equals(padding, "PKCS5Padding")
				&& !StringUtils.equals(padding, "NoPadding")) {
			throw new GeneralSecurityException(
					"错误的填充模式,目前KMI只支持PKCS5Padding和NoPadding两种工作模式");
		}

		if (mode != Cipher.ENCRYPT_MODE && mode != Cipher.DECRYPT_MODE) {
			throw new GeneralSecurityException(
					"错误的加解密标识,目前KMI只支持Cipher.ENCRYPT_MODE和Cipher.DECRYPT_MODE");
		}

		Cipher cipher = getCipher(keyData, iv, fullAlg, workingMode, mode);
		return cipher.doFinal(text);
	}

	/**
	 * 根据参数初始化cipher的方法
	 *
	 * @param keyData
	 *            密钥数据
	 * @param fullAlg
	 *            用来初始化Cipher对象的算法全称(已经加上工作模式和填充模式的)
	 * @param workingMode
	 *            工作模式,目前KMI接受的参数有CBC和ECB.
	 *            填充模式,目前KMI接受的参数有PKCS5Padding和NoPadding.
	 * @param mode
	 *            加解密标识：加密——Cipher.ENCRYPT_MODE；解密——Cipher.DECRYPT_MODE。
	 * @return cipher
	 * @throws GeneralSecurityException
	 */
	private static Cipher getCipher(byte[] keyData, byte[] iv, String fullAlg,
									String workingMode, int mode) throws GeneralSecurityException {
		Cipher cipher = Cipher.getInstance(fullAlg);
		SecretKey secretKey = new SecretKeySpec(keyData, StringUtils
				.substringBefore(fullAlg, "/"));

		if (StringUtils.equals(workingMode, "CBC")) {
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			cipher.init(mode, secretKey, ivSpec);
		} else {
			cipher.init(mode, secretKey);
		}
		return cipher;
	}

}
