package org.firstinspires.ftc.teamcode.PinkCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.teamcode.PinkCode.Hardware.Hardware.*;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.PinkCode.Systems.Controls;

import java.util.ArrayList;
import java.util.List;

@Autonomous(group = "Auto", name = "Auto")
public class AutoVuforia extends Controls {
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = VuforiaLocalizer.CameraDirection.BACK;
    private static final boolean PHONE_IS_PORTRAIT = false;

    private static final String VUFORIA_KEY =
            "AU5HdoL/////AAABmdflEYY1uEgKvLLnXhuUKQEiOh/Swf8w1NP3fjwJ0L5KhNZjEBmtqvcb1vRriuL7dxpTimmKsrPxVN0GSemDm1z0zZHiuEDJjN6is0gE5cC8eCf5/w4A9J9xygAQMiK4UOje3lWQjKpyMbqNeKgy1I6PZqyXBae1+6/gecIRmHuDjcqGFcEnRKmf8e6iPrFIdaC53DkmQUxJWRalVEqWsdmwmLm69AsaoG+aL7D0xkupVo7U23C2fdDkl66qsFO7v7jf0ONGEdmNjg1TTEKQmrip86/iMst+I7mdLA/pYsY00EjAjgPJ8YdXEqR5pKR2CK4DNmVU+c2A7T+w+KhGwxJ8us9j9FpYTd1yC0wRQD0R";

    private static final float mmPerInch = 25.4f;
    private static final float mmTargetHeight = (6) * mmPerInch;

    // Constant for Stone Target
    private static final float stoneZ = 2.00f * mmPerInch;

    // Constants for the center support targets
    private static final float bridgeZ = 6.42f * mmPerInch;
    private static final float bridgeY = 23 * mmPerInch;
    private static final float bridgeX = 5.18f * mmPerInch;
    private static final float bridgeRotY = 59;
    private static final float bridgeRotZ = 180;

    // Constants for perimeter targets
    private static final float halfField = 72 * mmPerInch;
    private static final float quadField = 36 * mmPerInch;

    // Class Members
    private OpenGLMatrix lastLocation = null;
    private VuforiaLocalizer vuforia = null;
    private boolean targetVisible = false;
    private float phoneXRotate = 0;
    private float phoneYRotate = 0;
    private float phoneZRotate = 0;

    public enum Functions {
        stage1,
        stage2,
        park
    }

    int cameraMonitorViewId;
    private VuforiaLocalizer.Parameters parameters;
    private VuforiaTrackable stoneTarget;
    private VuforiaTrackable blueRearBridge;
    private VuforiaTrackable redRearBridge;
    private VuforiaTrackable redFrontBridge;
    private VuforiaTrackable blueFrontBridge;

    private VuforiaTrackables targetsSkyStone;
    private List<VuforiaTrackable> Trackables;

    private Functions functions;

    @Override
    public void init() {
        StartHardware(hardwareMap);
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CAMERA_CHOICE;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
        telemetry.addData("Vuforia", vuforia);
        telemetry.update();

        targetsSkyStone = this.vuforia.loadTrackablesFromAsset("Skystone");

        stoneTarget = targetsSkyStone.get(0);
        stoneTarget.setName("Stone Target");
        blueRearBridge = targetsSkyStone.get(1);
        blueRearBridge.setName("Blue Rear Bridge");
        redRearBridge = targetsSkyStone.get(2);
        redRearBridge.setName("Red Rear Bridge");
        redFrontBridge = targetsSkyStone.get(3);
        redFrontBridge.setName("Red Front Bridge");
        blueFrontBridge = targetsSkyStone.get(4);
        blueFrontBridge.setName("Blue Front Bridge");

        // For convenience, gather together all the trackable objects in one easily-iterable collection */
        Trackables = new ArrayList<VuforiaTrackable>();

        Trackables.add(stoneTarget);

        // Set the position of the Stone Target.  Since it's not fixed in position, assume it's at the field origin.
        // Rotated it to to face forward, and raised it to sit on the ground correctly.
        // This can be used for generic target-centric approach algorithms
        stoneTarget.setLocation(OpenGLMatrix
                .translation(0, 0, stoneZ)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));

        //Set the position of the bridge support targets with relation to origin (center of field)
        blueFrontBridge.setLocation(OpenGLMatrix
                .translation(-bridgeX, bridgeY, bridgeZ)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, bridgeRotY, bridgeRotZ)));

        blueRearBridge.setLocation(OpenGLMatrix
                .translation(-bridgeX, bridgeY, bridgeZ)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, -bridgeRotY, bridgeRotZ)));

        redFrontBridge.setLocation(OpenGLMatrix
                .translation(-bridgeX, -bridgeY, bridgeZ)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, -bridgeRotY, 0)));

        redRearBridge.setLocation(OpenGLMatrix
                .translation(bridgeX, -bridgeY, bridgeZ)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, bridgeRotY, 0)));
    }

    @Override
    public void loop() {
        // get the location of the Red Bridge.
        OpenGLMatrix locationVectorRed = stoneTarget.getLocation();

        if (CAMERA_CHOICE == BACK) {
            phoneYRotate = -90;
        } else {
            phoneYRotate = 90;
        }

        // Rotate the phone vertical about the X axis if it's in portrait mode
        if (PHONE_IS_PORTRAIT) {
            phoneXRotate = 90;
        }

        switch (functions) {
            case stage1:
                if (getPosition().get(0) != locationVectorRed.getTranslation().get(0)) {
                    drive(true, false, 1.0, 1.0);
                    telemetry.addData("Red Bridge Location: ", locationVectorRed.toString());
                    telemetry.addData("Current Bot Location: ", getPosition().get(0));
                    telemetry.update();
                } else if (getPosition().get(0) == locationVectorRed.getTranslation().get(0)) {
                    functions = Functions.stage2;
                }
                break;

            case stage2:
                if (50 != locationVectorRed.getTranslation().get(2)) {
                    drive(false, false, 1.0, 1.0);
                    telemetry.addData("Red Bridge Location: ", locationVectorRed.toString());
                    telemetry.addData("Current Bot Location: ", getPosition().get(1));
                    telemetry.update();
                } else if (getPosition().get(2) == locationVectorRed.getTranslation().get(2)) {
                    functions = Functions.park;
                }
                break;

            case park:
                for (int i = 0; i != 1000; i++) {
                    drive(false, false, 1.0, 1.0);
                    telemetry.addData("Time: ", i);
                    telemetry.update();
                }
                break;
        }

        // Next, translate the camera lens to where it is on the robot.
        // In this example, it is centered (left to right), but forward of the middle of the robot, and above ground level.
        final float CAMERA_FORWARD_DISPLACEMENT = 4.0f * mmPerInch;   // eg: Camera is 4 Inches in front of robot center
        final float CAMERA_VERTICAL_DISPLACEMENT = 8.0f * mmPerInch;   // eg: Camera is 8 Inches above ground
        final float CAMERA_LEFT_DISPLACEMENT = 0;    // eg: Camera is ON the robot's center line

        OpenGLMatrix robotFromCamera = OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES, phoneYRotate, phoneZRotate, phoneXRotate));

        /**  Let all the trackable listeners know where the phone is.  */
        for (VuforiaTrackable trackable : Trackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(robotFromCamera, parameters.cameraDirection);
        }

        targetsSkyStone.activate();

        // check all the trackable targets to see which one (if any) is visible.
        targetVisible = false;
        for (VuforiaTrackable trackable : Trackables) {
            if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                telemetry.addData("Visible Target", trackable.getName());
                targetVisible = true;

                // getUpdatedRobotLocation() will return null if no new information is available since
                // the last time that call was made, or if the trackable is not currently visible.
                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) trackable.getListener()).getUpdatedRobotLocation();
                if (robotLocationTransform != null) {
                    lastLocation = robotLocationTransform;
                }
                break;
            }
        }

        // Provide feedback as to where the robot is located (if we know).
        if (targetVisible) {
            // if robot position is not equal to image location move to image
            if (getPosition().get(0) != locationVectorRed.getTranslation().get(0)) {
                functions = Functions.stage1;
            } else if (getPosition().get(0) == locationVectorRed.getTranslation().get(0)) {
                telemetry.addData("Position Data", "At Position X");
                telemetry.update();
            }
        } else {
            telemetry.addData("Visible Target", "none");
        }
        telemetry.update();
    }
//
//    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = VuforiaLocalizer.CameraDirection.BACK;
//    private static final boolean PHONE_IS_PORTRAIT = false;
//
//    private static final String VUFORIA_KEY =
//            "AU5HdoL/////AAABmdflEYY1uEgKvLLnXhuUKQEiOh/Swf8w1NP3fjwJ0L5KhNZjEBmtqvcb1vRriuL7dxpTimmKsrPxVN0GSemDm1z0zZHiuEDJjN6is0gE5cC8eCf5/w4A9J9xygAQMiK4UOje3lWQjKpyMbqNeKgy1I6PZqyXBae1+6/gecIRmHuDjcqGFcEnRKmf8e6iPrFIdaC53DkmQUxJWRalVEqWsdmwmLm69AsaoG+aL7D0xkupVo7U23C2fdDkl66qsFO7v7jf0ONGEdmNjg1TTEKQmrip86/iMst+I7mdLA/pYsY00EjAjgPJ8YdXEqR5pKR2CK4DNmVU+c2A7T+w+KhGwxJ8us9j9FpYTd1yC0wRQD0R";
//
//    private static final float mmPerInch = 25.4f;
//    private static final float mmTargetHeight = (6) * mmPerInch;
//
//    // Constant for Stone Target
//    private static final float stoneZ = 2.00f * mmPerInch;
//
//    // Constants for the center support targets
//    private static final float bridgeZ = 6.42f * mmPerInch;
//    private static final float bridgeY = 23 * mmPerInch;
//    private static final float bridgeX = 5.18f * mmPerInch;
//    private static final float bridgeRotY = 59;
//    private static final float bridgeRotZ = 180;
//
//    // Constants for perimeter targets
//    private static final float halfField = 72 * mmPerInch;
//    private static final float quadField = 36 * mmPerInch;
//
//    // Class Members
//    private OpenGLMatrix lastLocation = null;
//    private VuforiaLocalizer vuforia = null;
//    private boolean targetVisible = false;
//    private float phoneXRotate = 0;
//    private float phoneYRotate = 0;
//    private float phoneZRotate = 0;
//
//    public enum Functions {
//        stage1,
//        stage2,
//        park
//    }
//
//    private Functions functions;
//
//    @Override
//    public void runOpMode() {
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
//
//        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
//
//        parameters.vuforiaLicenseKey = VUFORIA_KEY;
//        parameters.cameraDirection = CAMERA_CHOICE;
//
//        //  Instantiate the Vuforia engine
//        vuforia = ClassFactory.getInstance().createVuforia(parameters);
//        telemetry.addData("Vuforia", vuforia);
//        telemetry.update();
//
//        // Load the data sets for the trackable objects. These particular data
//        // sets are stored in the 'assets' part of our application.
//        VuforiaTrackables targetsSkyStone = this.vuforia.loadTrackablesFromAsset("Skystone");
//
//        VuforiaTrackable stoneTarget = targetsSkyStone.get(0);
//        stoneTarget.setName("Stone Target");
//        VuforiaTrackable blueRearBridge = targetsSkyStone.get(1);
//        blueRearBridge.setName("Blue Rear Bridge");
//        VuforiaTrackable redRearBridge = targetsSkyStone.get(2);
//        redRearBridge.setName("Red Rear Bridge");
//        VuforiaTrackable redFrontBridge = targetsSkyStone.get(3);
//        redFrontBridge.setName("Red Front Bridge");
//        VuforiaTrackable blueFrontBridge = targetsSkyStone.get(4);
//        blueFrontBridge.setName("Blue Front Bridge");
//
//        // For convenience, gather together all the trackable objects in one easily-iterable collection */
//        List<VuforiaTrackable> Trackables = new ArrayList<VuforiaTrackable>();
//
//        Trackables.add(stoneTarget);
//
//        // get the location of the Red Bridge.
//        OpenGLMatrix locationVectorRed = stoneTarget.getLocation();
//
//        // Set the position of the Stone Target.  Since it's not fixed in position, assume it's at the field origin.
//        // Rotated it to to face forward, and raised it to sit on the ground correctly.
//        // This can be used for generic target-centric approach algorithms
//        stoneTarget.setLocation(OpenGLMatrix
//                .translation(0, 0, stoneZ)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));
//
//        //Set the position of the bridge support targets with relation to origin (center of field)
//        blueFrontBridge.setLocation(OpenGLMatrix
//                .translation(-bridgeX, bridgeY, bridgeZ)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, bridgeRotY, bridgeRotZ)));
//
//        blueRearBridge.setLocation(OpenGLMatrix
//                .translation(-bridgeX, bridgeY, bridgeZ)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, -bridgeRotY, bridgeRotZ)));
//
//        redFrontBridge.setLocation(OpenGLMatrix
//                .translation(-bridgeX, -bridgeY, bridgeZ)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, -bridgeRotY, 0)));
//
//        redRearBridge.setLocation(OpenGLMatrix
//                .translation(bridgeX, -bridgeY, bridgeZ)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, bridgeRotY, 0)));
//
////        //Set the position of the perimeter targets with relation to origin (center of field)
////        red1.setLocation(OpenGLMatrix
////                .translation(quadField, -halfField, mmTargetHeight)
////                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 180)));
////
////        red2.setLocation(OpenGLMatrix
////                .translation(-quadField, -halfField, mmTargetHeight)
////                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 180)));
////
////        front1.setLocation(OpenGLMatrix
////                .translation(-halfField, -quadField, mmTargetHeight)
////                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0 , 90)));
////
////        front2.setLocation(OpenGLMatrix
////                .translation(-halfField, quadField, mmTargetHeight)
////                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 90)));
////
////        blue1.setLocation(OpenGLMatrix
////                .translation(-quadField, halfField, mmTargetHeight)
////                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 0)));
////
////        blue2.setLocation(OpenGLMatrix
////                .translation(quadField, halfField, mmTargetHeight)
////                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 0)));
////
////        rear1.setLocation(OpenGLMatrix
////                .translation(halfField, quadField, mmTargetHeight)
////                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0 , -90)));
////
////        rear2.setLocation(OpenGLMatrix
////                .translation(halfField, -quadField, mmTargetHeight)
////                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));
//
//        if (CAMERA_CHOICE == BACK) {
//            phoneYRotate = -90;
//        } else {
//            phoneYRotate = 90;
//        }
//
//        // Rotate the phone vertical about the X axis if it's in portrait mode
//        if (PHONE_IS_PORTRAIT) {
//            phoneXRotate = 90;
//        }
//
//        switch (functions) {
//            case stage1:
//                if (getPosition().get(0) != locationVectorRed.getTranslation().get(0)) {
//                     drive(true, false, 1.0, 1.0);
//                    telemetry.addData("Red Bridge Location: ", locationVectorRed.toString());
//                    telemetry.addData("Current Bot Location: ", getPosition().get(0));
//                    telemetry.update();
//                } else if (getPosition().get(0) == locationVectorRed.getTranslation().get(0)) {
//                    functions = Functions.stage2;
//                }
//                break;
//
//            case stage2:
//                if (50 != locationVectorRed.getTranslation().get(2)) {
//                     drive(false, false, 1.0, 1.0);
//                    telemetry.addData("Red Bridge Location: ", locationVectorRed.toString());
//                    telemetry.addData("Current Bot Location: ", getPosition().get(1));
//                    telemetry.update();
//                } else if (getPosition().get(2) == locationVectorRed.getTranslation().get(2)) {
//                    functions = Functions.park;
//                }
//                break;
//
//            case park:
//                for (int i = 0; i != 1000; i++) {
//                     drive(false, false, 1.0, 1.0);
//                    telemetry.addData("Time: ", i);
//                    telemetry.update();
//                }
//                break;
//        }
//
//        // Next, translate the camera lens to where it is on the robot.
//        // In this example, it is centered (left to right), but forward of the middle of the robot, and above ground level.
//        final float CAMERA_FORWARD_DISPLACEMENT = 4.0f * mmPerInch;   // eg: Camera is 4 Inches in front of robot center
//        final float CAMERA_VERTICAL_DISPLACEMENT = 8.0f * mmPerInch;   // eg: Camera is 8 Inches above ground
//        final float CAMERA_LEFT_DISPLACEMENT = 0;    // eg: Camera is ON the robot's center line
//
//        OpenGLMatrix robotFromCamera = OpenGLMatrix
//                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES, phoneYRotate, phoneZRotate, phoneXRotate));
//
//        /**  Let all the trackable listeners know where the phone is.  */
//        for (VuforiaTrackable trackable : Trackables) {
//            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(robotFromCamera, parameters.cameraDirection);
//        }
//
//        targetsSkyStone.activate();
//
//        // check all the trackable targets to see which one (if any) is visible.
//        targetVisible = false;
//        for (VuforiaTrackable trackable : Trackables) {
//            if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
//                telemetry.addData("Visible Target", trackable.getName());
//                targetVisible = true;
//
//                // getUpdatedRobotLocation() will return null if no new information is available since
//                // the last time that call was made, or if the trackable is not currently visible.
//                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) trackable.getListener()).getUpdatedRobotLocation();
//                if (robotLocationTransform != null) {
//                    lastLocation = robotLocationTransform;
//                }
//                break;
//            }
//        }
//
//        // Provide feedback as to where the robot is located (if we know).
//        if (targetVisible) {
//            // if robot position is not equal to image location move to image
//            if (getPosition().get(0) != locationVectorRed.getTranslation().get(0)) {
//                functions = Functions.stage1;
//            } else if (getPosition().get(0) == locationVectorRed.getTranslation().get(0)) {
//                telemetry.addData("Position Data", "At Position X");
//                telemetry.update();
//            }
//        } else {
//            telemetry.addData("Visible Target", "none");
//        }
//        telemetry.update();
//
//        targetsSkyStone.deactivate();
//    }// loop

    //
//    private VectorF getPosition() {
//        return lastLocation.getTranslation();
//    }
//
//    private Orientation getRotation() {
//        return Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
//    }
    private VectorF getPosition() {
        return lastLocation.getTranslation();
    }
}
