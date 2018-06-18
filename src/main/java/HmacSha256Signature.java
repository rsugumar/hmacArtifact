import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;

/**
 * Created by 1583009 on 4/6/2018.
 */
public final class HmacSha256Signature {
    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
    private final byte[] mKey;

    public HmacSha256Signature(String keyFilePath) throws IOException {
        mKey = readKeyFromFile(keyFilePath);
    }

    public String encode(String data)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        return getHMAC_SHA256String(data, mKey);
    }

    private String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();

        for (byte b : bytes) {
            formatter.format("%02x", b);
        }

        return formatter.toString();
    }

    private String getHMAC_SHA256String(String data, byte[] key)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA256_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
        mac.init(signingKey);
        return toHexString(mac.doFinal(data.getBytes()));
    }

    private byte[] readKeyFromFile(String fileName)
            throws IOException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        StringBuilder keyString = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null)
            keyString.append(line);
        return keyString.toString().getBytes();
    }
}
