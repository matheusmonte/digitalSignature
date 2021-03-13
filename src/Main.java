import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
	    String message = "Test Message";
        String notValidMessage = "This message is different from original";

        Receiver receiver = new Receiver();
	    Sender originalSender = new Sender();
	    Sender notMessageSender = new Sender();

	    byte[] signature = originalSender.generateSignature(message);
	    byte[] wrongSignature = notMessageSender.generateSignature(message);
        PublicKey originalSenderPublicKey = originalSender.getPubKey();
        PublicKey notMessageOriginalSenderPublicKey = notMessageSender.getPubKey();

        //Valid communication , no atack
        System.out.println("Original Public key, Message and Signature. is valid:" + receiver.isSignatureValid(
                originalSenderPublicKey,
                message,
                signature));

        //Public key atack
        System.out.println("Original: Message and Signature - Changed: Public key. is valid:" +
                receiver.isSignatureValid(
                    notMessageOriginalSenderPublicKey,
                    message,
                    signature
                )
        );

        //Public key and Message atack
        System.out.println("Original: Signature - Changed: Public key, Message. is valid:" + receiver.isSignatureValid(
                notMessageOriginalSenderPublicKey,
                notValidMessage,
                signature));

        //Signature atack
        System.out.println("Original: Message - Changed: Public key, Signature. is valid:" + receiver.isSignatureValid(
                originalSenderPublicKey,
                message,
                wrongSignature));

        //Content atack
        System.out.println("Original: Public Key and Signature - Changed: Message, is valid:" +
                receiver.isSignatureValid(
                    originalSenderPublicKey,
                    notValidMessage,
                    signature
                )
        );
    }
}
