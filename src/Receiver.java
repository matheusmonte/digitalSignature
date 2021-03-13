import java.nio.charset.StandardCharsets;
import java.security.*;

public class Receiver implements DigitalSignature{
    @Override
    public byte[] generateSignature(String message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSignatureValid(PublicKey pubKey, String message, byte[] signed) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signatureClient = Signature.getInstance("DSA");
        signatureClient.initVerify(pubKey);
        signatureClient.update(message.getBytes(StandardCharsets.UTF_8));
        return signatureClient.verify(signed);
    }
}
