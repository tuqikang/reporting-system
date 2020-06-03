package cn.tqktqk.work.problemreportingsystem.utils;

import cn.tqktqk.work.problemreportingsystem.exceptions.ServerException;
import cn.tqktqk.work.problemreportingsystem.model.enums.ResponseEnum;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * █████▒█      ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 *
 * @Classname PasswordUtils
 * @Description TODO
 * @Date 2020/6/3 3:25 下午
 * @Author 麦麦麦阁
 */
public class PasswordUtils {

    /**
     * 要求key至少长度为8个字符
     */
    private static final String KEY = "problems";

    private static final SecureRandom RANDOM = new SecureRandom();

    private static DESKeySpec keySpec = null;

    private static SecretKeyFactory keyFactory = null;
    private static SecretKey secretKey = null;
    private static final String CIPHER_KEY = "des";

    static {
        try {
            keySpec = new DESKeySpec(KEY.getBytes());
            keyFactory = SecretKeyFactory.getInstance(CIPHER_KEY);
            secretKey = keyFactory.generateSecret(keySpec);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public static final String encode(String plainText) {
        String result;
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_KEY);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, RANDOM);
            byte[] cipherData = cipher.doFinal(plainText.getBytes());
            result = Base64.getUrlEncoder().encodeToString(cipherData);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    public static final String decode(String text) {
        byte[] cipherData = Base64.getUrlDecoder().decode(text);
        Cipher cipher = null;
        String result;
        try {
            cipher = Cipher.getInstance(CIPHER_KEY);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, RANDOM);
            byte[] plainData = cipher.doFinal(cipherData);
            result = new String(plainData);
        } catch (Exception e) {
            result = "";
        }
        return result;
    }

    public static final boolean match(String content,String password){
        if (StringUtils.isEmpty(content)) {
            throw new ServerException(ResponseEnum.CUSTOM,"输入值不能为空");
        }
        return encode(content).equals(password);
    }
}
