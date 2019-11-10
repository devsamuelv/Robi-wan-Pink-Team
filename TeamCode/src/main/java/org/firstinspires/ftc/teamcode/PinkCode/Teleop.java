package org.firstinspires.ftc.teamcode.PinkCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.vuforia.ModelTargetResult;

import org.apache.http.*;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamServer;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamClient.*;

import java.io.PipedOutputStream;

import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

@TeleOp(group = "Teleop", name = "Teleop")
public class Teleop extends OpMode {

    private double MotorPower = 80;
    private int PowerLevel = 5;

    @Override
    public void init() {
        StartHardware(hardwareMap);
    }

    @Override
    public void loop() {
//        LeftBack.setPower(gamepad1.left_stick_y);
//        LeftFront.setPower(gamepad1.left_stick_y);
//
//        RightFront.setPower(gamepad1.right_stick_y);
//        RightBack.setPower(gamepad1.right_stick_y);

        if (gamepad1.right_bumper = true) {
            if (PowerLevel == 1) {
                PowerLevel ++;
                gamepad1.right_bumper = false;
            }
            if (PowerLevel == 2) {
                PowerLevel ++;
                gamepad1.right_bumper = false;
            }
            if (PowerLevel == 3) {
                PowerLevel ++;
                gamepad1.right_bumper = false;
            }
            if (PowerLevel == 4) {
                PowerLevel ++;
                gamepad1.right_bumper = false;
            }
            if (PowerLevel == 5) {
                PowerLevel ++;
                gamepad1.right_bumper = false;
            }
        }

        if (PowerLevel == 1) {
            MotorPower = 20;
        } else if (PowerLevel == 2) {
            MotorPower = 40;
        } else if (PowerLevel == 3) {
            MotorPower = 60;
        } else if (PowerLevel == 4) {
            MotorPower = 80;
        } else if (PowerLevel == 5) {
            MotorPower = 100;
        } else {
            telemetry.addData("Power Level Error:" , "Invalid Power Level Value");
            telemetry.update();
        }

        if (gamepad1.right_stick_y == 1 && gamepad1.left_stick_y == 1) {
            RightBack.setPower(MotorPower);
            RightFront.setPower(MotorPower);
            LeftBack.setPower(MotorPower);
            LeftFront.setPower(MotorPower);
        } else {
            telemetry.addData("Motor Error:", "Motor Value Not Identified 1&1");
            telemetry.update();
        }

        if (gamepad1.right_stick_y == -1 && gamepad1.left_stick_y == -1) {
            LeftFront.setPower(-MotorPower);
            LeftBack.setPower(-MotorPower);
            RightBack.setPower(-MotorPower);
            RightFront.setPower(-MotorPower);
        } else {
            telemetry.addData("Motor Error:", "Motor Value Not Identified -1&-1");
            telemetry.update();
        }

        if (gamepad1.right_stick_y == -1 && gamepad1.left_stick_y == 1) {
            RightFront.setPower(-MotorPower);
            RightBack.setPower(-MotorPower);
            LeftBack.setPower(MotorPower);
            LeftFront.setPower(MotorPower);
        } else {
            telemetry.addData("Motor Error:", "Motor Value Not Identified -1&1");
            telemetry.update();
        }

        if (gamepad1.left_stick_y == -1 && gamepad1.right_stick_y == 1) {
            LeftBack.setPower(-MotorPower);
            LeftFront.setPower(-MotorPower);
            RightFront.setPower(MotorPower);
            RightBack.setPower(MotorPower);
        } else {
            telemetry.addData("Motor Error:", "Motor Value Not Identified L-1 & 1");
            telemetry.update();
        }
    }
}
