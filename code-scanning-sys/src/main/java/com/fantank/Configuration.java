package com.fantank;

public interface Configuration {
    int[] VALIDSTATE = {1, 2, 3};

    String requestUrl = "http://localhost:8080/scanning/code";

    String pagePath = "src/main/resources/target.html";

    String[] audioPath = {"src/main/resources/Audios/qrcodeScanSuccess.mp3","src/main/resources/Audios/qrcodeScanInvalid.mp3","src/main/resources/Audios/qrcodeScanError.mp3","src/main/resources/Audios/notAllowed.mp3"};
}
