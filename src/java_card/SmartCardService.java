/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_card;

import java.io.ByteArrayOutputStream;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author anymore1405
 */
public class SmartCardService {

    public static final byte[] AID_APPLET = {(byte) 0x11, (byte) 0x22, (byte) 0x33, (byte) 0x44, (byte) 0x55, (byte) 0x00};
    public static final byte[] exponent = {0x01, 0x00, 0x01};
    private final static byte CLA = (byte) 0xA0;

    private static final byte INIT_CARD = (byte) 0x00;
    private static final byte CLEAR_CARD = (byte) 0x01;
    private static final byte CHECK_PIN = (byte) 0x02;
    private static final byte UNLOCK_CARD = (byte) 0x03;
    private static final byte LOCK_CARD = (byte) 0x04;

    private static final byte GET_INFO = (byte) 0x05;

    private static final byte CHANGE_PIN = (byte) 0x06;

    private static final byte CHANGE_IMAGE = (byte) 0x07;
    private static final byte GET_IMAGE = (byte) 0x08;
    private static final byte UPDATE_INFO = (byte) 0x09;
    private static final byte UPDATE_PIN = (byte) 0x10;
    private static final byte VERIFY = (byte) 0x11;
    private static final byte GET_ID = (byte) 0x12;

    public static final byte NULL_STATUS = 0x20; // space
    public static final byte STATUS_FALSE = 0;
    public static final byte STATUS_TRUE = 1;
    public static final byte STATUS_LOCKED = 2;
    public static final byte STATUS_SPLIT_VALUE = 0x40; //@
    public static final byte STATUS_NEED_CHANGE_PIN = 0x21; //!
    public static final byte STATUS_CARD_NOT_INIT = 0x3F; //?

    public Card card;
    private TerminalFactory terminalFactory;
    private CardChannel cardChannel;
    private CardTerminal cardTerminal;
    private List<CardTerminal> cardTerminals;
    private ResponseAPDU responseAPDU;
    private PublicKey rsaPublicKey;
    private final Signature signature = Signature.getInstance("SHA1withRSA");

    private byte status = NULL_STATUS;

    public boolean isConnected;
    public int id = 0;

    private final Cipher cipher = Cipher.getInstance("RSA");

    ;

    public SmartCardService() throws NoSuchPaddingException, NoSuchAlgorithmException {

    }

    private void setStatus(byte status) {
        this.status = status;
    }

    public byte getStatus() {
        return status;
    }

    private byte[] sendToCard(CommandAPDU commandAPDU) {
        try {
            status = NULL_STATUS;
            responseAPDU = cardChannel.transmit(commandAPDU);

            String check = Integer.toHexString(responseAPDU.getSW());
            if (check.equals("6400")) {
                System.out.println("Error: " + Arrays.toString(responseAPDU.getData()));
            }
            if (responseAPDU.getData().length == 1) {
                setStatus(responseAPDU.getData()[0]);
            }
            return responseAPDU.getData();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new byte[]{};
    }

    public boolean connectCard() throws CardException {
        try {
            terminalFactory = TerminalFactory.getDefault();
            cardTerminals = terminalFactory.terminals().list();

            card = cardTerminals.get(0).connect("*");
            cardChannel = card.getBasicChannel();

            if (cardChannel == null) {
                return false;
            }

            responseAPDU = cardChannel.transmit(new CommandAPDU(0x00, (byte) 0xA4, 0x04, 0x00, AID_APPLET));

            String check = Integer.toHexString(responseAPDU.getSW());
            if (check.equals("9000")) {
                isConnected = true;
                return true;
            } else if (check.equals("6400")) {
                // message card invalid
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {

        }

        return false;
    }

    public void setRsaPublicKey(PublicKey rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }

    public void setPublicKey(byte[] publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        RSAPublicKeySpec spec = new RSAPublicKeySpec(new BigInteger(1, publicKey), new BigInteger(1, exponent));
        KeyFactory factory = KeyFactory.getInstance("RSA");
        rsaPublicKey = factory.generatePublic(spec);
    }

    public byte[] encrypt(byte[] data) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
        return cipher.doFinal(data);
    }

    public byte[] decrypt(byte[] data) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);
        return cipher.doFinal(data);
    }

    public boolean disconnectCard() {
        try {
            card.disconnect(false);
            isConnected = false;
            return true;
        } catch (Exception e) {
            System.out.println("java_card.SmartCardService.disconnectCard()");
        }
        return false;
    }

    public java_card.Card getInfoCard() throws CardException {
        return new java_card.Card(sendToCard(new CommandAPDU(CLA, GET_INFO, 0x00, 0x00)));
    }

    public byte[] initCard(java_card.Card card, String pin) throws CardException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] pub = sendToCard(new CommandAPDU(CLA, INIT_CARD, 0x00, 0x00, (pin + "@" + card.toString()).getBytes(StandardCharsets.UTF_8)));
        setPublicKey(pub);
        return pub;
    }
    public boolean login(String pin) {
        byte[] data = sendToCard(new CommandAPDU(CLA, CHECK_PIN, 0x00, 0x00, (pin.getBytes(StandardCharsets.UTF_8))));
        if (data.length > 0) {
            setStatus(data[0]);
            return true;
        }
        return false;
    }
    public boolean verify() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, CardException, SignatureException {
        String stringCheck = UUID.randomUUID().toString();
        System.out.println("verify: "+ stringCheck);
        byte[] data = sendToCard(new CommandAPDU(CLA, VERIFY, 0x00, 0x00, (stringCheck.getBytes(StandardCharsets.UTF_8))));
        if (data.length > 1) {
            setStatus(data[0]);
            signature.initVerify(rsaPublicKey);
            signature.update(stringCheck.getBytes(StandardCharsets.UTF_8));
            return signature.verify(data, 1, 128);
        }
        if(data.length == 0){
            setStatus(STATUS_FALSE);
        } else {
            setStatus(data[0]);
        }
        return false;
    }

    public void getInfo() {
        byte[] data = sendToCard(new CommandAPDU(CLA, GET_INFO, 0x00, 0x00));
        System.out.println("res: " + new String(data));
    }

    public void updateInfo(java_card.Card card) {
        sendToCard(new CommandAPDU(CLA, UPDATE_INFO, 0x00, 0x00, card.toStringWithoutId().getBytes(StandardCharsets.UTF_8)));
    }

    public void updatePin(String newPin) {
        sendToCard(new CommandAPDU(CLA, UPDATE_PIN, 0x00, 0x00, newPin.getBytes(StandardCharsets.UTF_8)));
    }

    public void unlockCard() {
        sendToCard(new CommandAPDU(CLA, UNLOCK_CARD, 0x00, 0x00));
    }

    public void lockCard() {
        sendToCard(new CommandAPDU(CLA, LOCK_CARD, 0x00, 0x00));
    }

    public byte[] getImage() {
        byte[] data = new byte[0];
        byte[] _temp = sendToCard(new CommandAPDU(CLA, GET_IMAGE, 0x00, 0x00));

        while (_temp.length > 0) {

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                outputStream.write(data);
                outputStream.write(_temp);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            data = outputStream.toByteArray();
            _temp = sendToCard(new CommandAPDU(CLA, GET_IMAGE, 0x00, 0x00));
        }
        return data;
    }

    public void changeImage(byte[] image) {
        int length = image.length;
        int pointer = 0;
        boolean first = true;
        while (pointer < length) {
            int size = length - pointer > 128 ? 128 : length - pointer;
            byte[] buf = new byte[size];
            System.arraycopy(image, pointer, buf, 0, size);
            // p2 = 0 -> gui lan dau, p2 = 1 -> gui cac lan sau
            sendToCard(new CommandAPDU(CLA, CHANGE_IMAGE, 0x00, first ? 0x00 : 0x01, buf));
            first = false;
            pointer += size;
        }
    }

    public void getId(){
        byte[] data = sendToCard(new CommandAPDU(CLA, GET_ID, 0x00, 0x00));
        if(data.length == 0){
            setStatus(STATUS_CARD_NOT_INIT);
            id = 0;
        }
        else {
            setStatus(STATUS_TRUE);
            id = Integer.parseInt(new String(data, StandardCharsets.UTF_8));
        }
    }

    public void clearCard(){
        sendToCard(new CommandAPDU(CLA, CLEAR_CARD, 0x00, 0x00));
    }
}
