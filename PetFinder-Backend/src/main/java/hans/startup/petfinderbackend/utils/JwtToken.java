package hans.startup.petfinderbackend.utils;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Properties;

public class JwtToken {

    public static PrivateKey getPrivateKey() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(new File("key.properties")));
            String privateKey = prop.getProperty("private");
            PemReader reader = new PemReader(new FileReader(privateKey));
            PemObject object = reader.readPemObject();
            byte[] content = object.getContent();

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(content);
            PrivateKey pk = KeyFactory.getInstance("RSA").generatePrivate(keySpec);
            return pk;
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
