package hans.startup.petfinderbackend.utils;

import io.jsonwebtoken.*;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

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

    public static PublicKey getPublicKey() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(new File("key.properties")));
            String publicKey = prop.getProperty("public");
            PemReader reader = new PemReader(new FileReader(publicKey));
            PemObject object = reader.readPemObject();
            byte[] content = object.getContent();

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(content);
            return KeyFactory.getInstance("RSA").generatePublic(keySpec);
        } catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String tokenGenerator(String fisrtname, String lastname, String email) {
        try {
            return Jwts.builder()
                    .claim("firstname", fisrtname)
                    .claim("lastname", lastname)
                    .claim("email", email)
                    .setId(UUID.randomUUID().toString())
                    .setIssuedAt(Date.from(Instant.now()))
                    .setExpiration(Date.from(Instant.now().plus(3, ChronoUnit.HOURS)))
                    .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
                    .compact();
        } catch (Exception e) {
            System.err.println("Token generation failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static Jws<Claims> verifyToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(getPublicKey())
                    .build()
                    .parseClaimsJws(token);
        } catch (Exception e) {
            System.err.println("Token verification failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
