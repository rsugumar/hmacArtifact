/**
 * Created by 1583009 on 4/6/2018.
 */
public class MainTest {
    public static void main(String[] args) throws Exception {
        String keyFilePath = "C:\\Users\\1583009\\Documents\\source_codes\\HMAC\\keyFile.txt";
        HmacSha256Signature hmacObj = new HmacSha256Signature(keyFilePath);
        String data = "data"; // TODO: Txn Id + TimeStamp + AppName
        //In the “Authorization” field in HTTP Request Header with the following format
        //Authorization=Bearer <Hash Value>

        String encodedStr = hmacObj.encode(data);
        System.out.format("Encoded to: %s", encodedStr);
        assert encodedStr.equals("5031fe3d989c6d1537a013fa6e739da23463fdaec3b70137d828e36ace221bd0");
    }
}
