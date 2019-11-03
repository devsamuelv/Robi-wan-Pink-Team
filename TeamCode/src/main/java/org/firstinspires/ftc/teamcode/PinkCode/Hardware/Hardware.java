package org.firstinspires.ftc.teamcode.PinkCode.Hardware;

import com.qualcomm.hardware.microsoft.MicrosoftGamepadXbox360;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Hardware {
    public static DcMotor LeftBack;
    public static DcMotor RightBack;
    public static DcMotor RightFront;
    public static DcMotor LeftFront;

    private static Servo ServoAngle1; // placeHolder
    private static Servo servo2; // placeHolder
    private static Servo servo3; // placeHolder

    public static Telemetry telemetry;
    private static HardwareMap hardwareMap;

    public static void StartHardware() throws NullPointerException{

        RightBack = hardwareMap.dcMotor.get("MotorRightBack");
        RightFront = hardwareMap.dcMotor.get("MotorRightFront");
        LeftBack = hardwareMap.dcMotor.get("MotorLeftBack");
        LeftFront = hardwareMap.dcMotor.get("MotorLeftFront");

        RightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        RightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
