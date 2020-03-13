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

    public static Servo ground_servo;
    public static Servo grabber;

    public static Telemetry telemetry;
    private static HardwareMap hwmap = null;

    public static void StartHardware(HardwareMap hardwareMap){
        hwmap = hardwareMap;

        RightFront = hwmap.get(DcMotor.class, "rf");
        RightBack = hwmap.get(DcMotor.class, "rb");
        LeftFront = hwmap.get(DcMotor.class, "lf");
        LeftBack = hwmap.get(DcMotor.class, "lb");

        RightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        RightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        RightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        RightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        LeftFront.setDirection(DcMotorSimple.Direction.FORWARD);

        RightBack.setPower(0);
        LeftBack.setPower(0);
        RightFront.setPower(0);
        LeftFront.setPower(0);

        ground_servo = hwmap.get(Servo.class, "sr1");
        grabber = hwmap.get(Servo.class, "sr2");

        ground_servo.setPosition(Servo.MAX_POSITION);
        grabber.setPosition(0.5);

        grabber.setDirection(Servo.Direction.FORWARD);
    }
}
