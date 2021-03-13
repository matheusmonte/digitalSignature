import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;

public interface DigitalSignature {
    public byte[] generateSignature(String message)
            throws NoSuchAlgorithmException,
            InvalidKeyException,
            SignatureException;

    public boolean isSignatureValid(PublicKey pubKey, String message, byte[] signed)
            throws NoSuchAlgorithmException,
            InvalidKeyException,
            SignatureException;

}
