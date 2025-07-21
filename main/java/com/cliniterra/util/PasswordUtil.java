package com.cliniterra.util;
/**
 * @author Seer Pant
 */
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets; 
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

//imports for converting apsswords to AES encrpytion key
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;
import java.util.logging.Level;

//imports for encryption
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.util.Base64;


public class PasswordUtil{
    /*Defining the encryption algortihm to be used*/ 
    private static final String encryption_algo = "AES/GCM/NoPadding";
    
    /*Defining the key size*/
    private static final int tag_length = 128;
    
    /*setting initialization vector size */
    private static final int initVec_length = 12;

    /*Setting size of salt value to be added */
    private static final int salt_length = 16;

    /*Setting character encoding to UTF-8 for string to byte conversion */
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    /*method for creating random array of bytes for initialization vecor or salt */
    public static byte[] getRandomNonce(int numBytes){
        byte[] nonce = new byte[numBytes];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }

    //method to create a random AES key
    public static SecretKey getEncKey(int keysize) throws NoSuchAlgorithmException {
        //creating AES key generator
        KeyGenerator aesGen = KeyGenerator.getInstance("AES");
        //setting up AES key
        aesGen.init(keysize, SecureRandom.getInstanceStrong());
        //generating and returning AES key
        return aesGen.generateKey();
    }


    //Converting password to random AES encryption key
    public static SecretKey getEncKeyFromPassword(char[] password, byte[] salt){
        try{
            //generating secret keys from passwords
            SecretKeyFactory factoryKey = SecretKeyFactory.getInstance("PBKDF2WtihHmacSHA256");
            
            KeySpec spec = new PBEKeySpec(password,salt,65536,256);
            
            //formatting key for AES compatibility
            SecretKey secret = new SecretKeySpec(factoryKey.generateSecret(spec).getEncoded(),"AES");
            return secret;
            
        }catch (NoSuchAlgorithmException ex){
            //code to print stack trace error and log to a log file
            Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE,null,ex);
        }catch(InvalidKeySpecException ex){
            Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;

    }

    //ecrypting password using AES encryption with GCM mode
    public static String encrypt(String username, String password){
            try{
                //getting salt of 16 bytes
                byte[] salt = getRandomNonce(salt_length);

                //generating 12 bytes long Initalization vector 
                byte[] iv = getRandomNonce(initVec_length);
                
                //generating unique key for the user based on their username
                SecretKey getEncKeyFromPassword = getEncKeyFromPassword(username.toCharArray(), salt);
                
                //new cipher object for encryption
                Cipher cipher = Cipher.getInstance(encryption_algo);
                
                cipher.init(Cipher.ENCRYPT_MODE, getEncKeyFromPassword, new GCMParameterSpec(tag_length, iv));
                
                //encrypting password and converting to bytes
                byte[] cipherText = cipher.doFinal(password.getBytes());

                // combining salt,iv and ecrypted password into a byte array
                byte[] cipherWithSaltandIv = ByteBuffer.allocate(iv.length + salt.length + cipherText.length).put(iv).put(salt).put(cipherText).array();
            
                //encoding to base64c
                return Base64.getEncoder().encodeToString(cipherWithSaltandIv);
            }catch(Exception ex){
                return null;
            }
    }


            //decryption method
            public static String decrpytion(String encPassword, String username){
            try{
                byte[] decode = Base64.getDecoder().decode(encPassword.getBytes(UTF_8));

                //wrapping into ByteBuffer
                ByteBuffer bb = ByteBuffer.wrap(decode);

                byte[] iv = new byte[initVec_length];
                bb.get(iv); 

                byte[] salt = new byte[salt_length];
                bb.get(salt);

                byte[] cipherText = new byte[bb.remaining()];
                bb.get(cipherText);

                //creating AES key from extracted salt
                SecretKey aesKeyFromPassword = PasswordUtil.getEncKeyFromPassword(username.toCharArray(), salt);
                
                //creating a cipher instance
                Cipher cipher = Cipher.getInstance(encryption_algo);
                //initializing cipher in decrypt mode with authentication 
                cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(tag_length, iv));

                //decrypting password
                byte[] passwordPlainText = cipher.doFinal(cipherText);

                return new String(passwordPlainText, UTF_8);

            }catch(Exception ex){
                return null;
            }
    }
}



    

 