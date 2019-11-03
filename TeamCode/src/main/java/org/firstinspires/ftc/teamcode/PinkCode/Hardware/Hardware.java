package org.firstinspires.ftc.teamcode.PinkCode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Hardware {
    public static Servo LeftFrontServo;
    public static DcMotor LeftBack;
    public static Servo RightFrontServo;
    public static DcMotor RightBack;

    private static Servo ServoAngle1; // placeHolder
    private static Servo servo2; // placeHolder
    private static Servo servo3; // placeHolder

    public static Telemetry telemetry;
    private static HardwareMap hardwareMap;

    public static void StartHardware() throws NullPointerException{
        RightBack = hardwareMap.dcMotor.get("motor1");
//        RightFrontServo = hardwareMap.servo.get("frontRightDriveServo");
//        LeftFrontServo = hardwareMap.servo.get("frontLeftDriveServo");
//      RightBack = hardwareMap.dcMotor.get("backMotorRight");
//      LeftBack = hardwareMap.dcMotor.get("backMotorLeft");

        ServoAngle1 = hardwareMap.servo.get("servo1"); // placeHolder
        servo2 = hardwareMap.servo.get("servo2"); // placeHolder
        servo3 = hardwareMap.servo.get("servo3"); // placeHolder

        ServoAngle1.setPosition(0);
        servo2.setPosition(0);
        servo3.setPosition(0);

        RightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        RightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
