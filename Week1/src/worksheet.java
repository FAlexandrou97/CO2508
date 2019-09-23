interface cellphoneInterface {
    void makeCalls(int phoneNumber);

    void connect2Wifi(String ssid, String password);

    void selfDestruct(String finalWords);
}

class cellphone implements cellphoneInterface {

    int batteryLife = 36000;
    int gData = 5;
    boolean smartphone = true;

    public void makeCalls(int phoneNumber) {
        System.out.println("Test");
    }

    public void connect2Wifi(String ssid, String password) {
        if (ssid.equals("uclan_Wifi") && password.equals("somePass")) {
            System.out.println("Connected Successfully!");
        } else {
            System.out.println("NOPE!");
        }
    }

    public void selfDestruct(String finalWords) {
        System.out.println("Test");
    }

}


class Main {

    public static void main(String[] args) {
        cellphone onePlus6T = new cellphone();
        onePlus6T.connect2Wifi("uclan_Wifi", "somePass");
    }
}