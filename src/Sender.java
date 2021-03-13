import java.nio.charset.StandardCharsets;
import java.security.*;

public class Sender implements DigitalSignature{
    private PublicKey pubKey;
    private PrivateKey privateKey;
    private Signature signature;

    public PublicKey getPubKey(){
        return pubKey;
    }

    public void setPubKey(PublicKey pubKey){
        this.pubKey = pubKey;
    }

    public Sender() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
            SecureRandom secRan = new SecureRandom();
            kpg.initialize(512, secRan);
            KeyPair keyPair = kpg.generateKeyPair();
            this.pubKey = keyPair.getPublic();
            this.privateKey = keyPair.getPrivate();
            this.signature = Signature.getInstance("DSA");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] generateSignature(String message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        this.signature.initSign(this.privateKey);
        this.signature.update(message.getBytes(StandardCharsets.UTF_8));
        byte[] newSignature = this.signature.sign();
        return newSignature;
    }

    @Override
    public boolean isSignatureValid(PublicKey pubKey, String message, byte[] signed) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
       throw new UnsupportedOperationException();
    }
}
