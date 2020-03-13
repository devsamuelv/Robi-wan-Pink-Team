package org.firstinspires.ftc.teamcode.PinkCode;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

public interface VuforiaSettings {
    VuforiaLocalizer.CameraDirection CAMERA_DIRECTION = VuforiaLocalizer.CameraDirection.FRONT;
    VuforiaLocalizer.Parameters parms = null;
    boolean PHONE_IS_PORTRAIT = false;
    String Vuforia_key = "AU5HdoL/////AAABmdflEYY1uEgKvLLnXhuUKQEiOh/Swf8w1NP3fjwJ0L5KhNZjEBmtqvcb1vRriuL7dxpTimmKsrPxVN0GSemDm1z0zZHiuEDJjN6is0gE5cC8eCf5/w4A9J9xygAQMiK4UOje3lWQjKpyMbqNeKgy1I6PZqyXBae1+6/gecIRmHuDjcqGFcEnRKmf8e6iPrFIdaC53DkmQUxJWRalVEqWsdmwmLm69AsaoG+aL7D0xkupVo7U23C2fdDkl66qsFO7v7jf0ONGEdmNjg1TTEKQmrip86/iMst+I7mdLA/pYsY00EjAjgPJ8YdXEqR5pKR2CK4DNmVU+c2A7T+w+KhGwxJ8us9j9FpYTd1yC0wRQD0R";
    float mmPerInch = 25.4f;
    float mmTargetHeight = (6) * mmPerInch;

    // height for stone
    float stoneZ = 2.00f * mmPerInch;

    float bridgeZ = 6.42f * mmPerInch;
    float bridgeX = 5.18f * mmPerInch;
    float bridgeY = 23 * mmPerInch;
    float bridgeRotY = 59;
    float bridgeRotZ = 180;

    float halfField = 72 * mmPerInch;
    float quadField = 36 * mmPerInch;
}
