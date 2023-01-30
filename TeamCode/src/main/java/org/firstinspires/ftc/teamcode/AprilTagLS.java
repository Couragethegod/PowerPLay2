/*
 * Copyright (c) 2021 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Autonomous
public class AprilTagLS extends LinearOpMode
{
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    // Tag ID 1,2,3 from the 36h11 family
    int LEFT = 1;
    int MIDDLE = 2;
    int RIGHT = 3;

    AprilTagDetection tagOfInterest = null;
    DcMotor Lwheels;
    DcMotor Rwheels;
    DcMotor Barm;
    DcMotor Uarm;
    DcMotor Claw;
    CRServo Rclaw;
    CRServo Lclaw;

    @Override
    public void runOpMode()
    {

        Uarm = hardwareMap.get(DcMotor.class, "Uarm");
        Barm = hardwareMap.get(DcMotor.class, "Barm");
        Lwheels = hardwareMap.get(DcMotor.class, "Lwheels");
        Rwheels = hardwareMap.get(DcMotor.class, "Rwheels");
        Claw = hardwareMap.get(DcMotor.class, "Claw");
        Lclaw = hardwareMap.get(CRServo.class, "Lclaw");
        Rclaw = hardwareMap.get(CRServo.class, "Rclaw");

        Lwheels.setDirection(DcMotorSimple.Direction.REVERSE);
        Rwheels.setDirection(DcMotorSimple.Direction.FORWARD);
        Barm.setDirection(DcMotorSimple.Direction.REVERSE);
        Uarm.setDirection(DcMotorSimple.Direction.REVERSE);

        Uarm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Barm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lwheels.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Rwheels.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        telemetry.setMsTransmissionInterval(50);

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        Uarm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Barm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lwheels.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Rwheels.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        while (!isStarted() && !isStopRequested())
        {
           /* Uarm.setTargetPosition(40);
            Barm.setTargetPosition(40);
            Uarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Barm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Uarm.setPower(0.5);
            Barm.setPower(0.5);
            while (Barm.isBusy() && Uarm.isBusy()){
                sleep(15);
            }
            Claw.setPower(-.5);*/


            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0)
            {
                boolean tagFound = false;

                for(AprilTagDetection tag : currentDetections)
                {
                    if(tag.id == LEFT || tag.id == MIDDLE || tag.id == RIGHT)
                    {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if(tagFound)
                {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterest);
                }
                else
                {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterest == null)
                    {
                        telemetry.addLine("(The tag has never been seen)");
                    }
                    else
                    {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(tagOfInterest);
                    }
                }

            }
            else
            {
                telemetry.addLine("Don't see tag of interest :(");

                if(tagOfInterest == null)
                {
                    telemetry.addLine("(The tag has never been seen)");
                }
                else
                {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }

            }

            telemetry.update();
            sleep(20);
        }

        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */

        /* Update the telemetry */
        if(tagOfInterest != null)
        {
            telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            telemetry.update();
        }
        else
        {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }

        /* Actually do something useful */
        if( tagOfInterest == null){
            //default trajectory here if preferred
        }else if(tagOfInterest.id == LEFT){
            Uarm.setTargetPosition(40);
            Barm.setTargetPosition(40);
            Uarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Barm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Uarm.setPower(0.5);
            Barm.setPower(0.5);
            while (Barm.isBusy() && Uarm.isBusy()){
                sleep(15);
            }
            Claw.setPower(-.5);
            sleep(500);
            Lwheels.setTargetPosition(Lwheels.getCurrentPosition() - 1200);
            Rwheels.setTargetPosition(Rwheels.getCurrentPosition() - 1200);
            Rwheels.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Lwheels.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Lwheels.setPower(1);
            Rwheels.setPower(1);

            while (Lwheels.isBusy() && Rwheels.isBusy()){
                sleep(15);
            }
            Lwheels.setTargetPosition(Lwheels.getTargetPosition() - 695);
            Rwheels.setTargetPosition(Rwheels.getTargetPosition() + 695);

            while (Lwheels.isBusy() && Rwheels.isBusy()){
                sleep(15);
            }
            Rwheels.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            Lwheels.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            Lwheels.setPower(-0.4);
            Rwheels.setPower(-0.4);
            sleep(1000);
            Lwheels.setPower(0);
            Rwheels.setPower(0);
            Rwheels.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Lwheels.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            sleep(20);
            Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 1700);
            Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 1700);
            Lwheels.setPower(1);
            Rwheels.setPower(1);
            Rwheels.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Lwheels.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (Rwheels.isBusy() && Lwheels.isBusy()){
                sleep(15);
            }
            telemetry.addLine("Left");
            //left trajectory
        }else if(tagOfInterest.id == MIDDLE){
            //middle trajectory
            Uarm.setTargetPosition(40);
            Barm.setTargetPosition(40);
            Uarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Barm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Uarm.setPower(0.5);
            Barm.setPower(0.5);
            while (Barm.isBusy() && Uarm.isBusy()){
                sleep(15);
            }
            Claw.setPower(-.5);
            sleep(500);
            Lwheels.setPower(1);
            Rwheels.setPower(1);
            Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 300);
            Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 300);
            Lwheels.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Rwheels.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (Rwheels.isBusy() && Lwheels.isBusy()){
                sleep(15);
            }
            sleep(20);
            Lwheels.setTargetPosition(Lwheels.getTargetPosition() - 525);
            Rwheels.setTargetPosition(Rwheels.getTargetPosition() + 525);
            while (Lwheels.isBusy() && Rwheels.isBusy()){
                sleep(15);
            }
            sleep(20);
            Lwheels.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            Rwheels.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            Lwheels.setPower(-.5);
            Rwheels.setPower(-.5);
            sleep(1000);
            Rwheels.setPower(0);
            Lwheels.setPower(0);
            sleep(500);
            Lwheels.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Rwheels.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Lwheels.setPower(1);
            Rwheels.setPower(1);
            Lwheels.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Rwheels.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 2500);
            Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 2500);
            while (Rwheels.isBusy() && Lwheels.isBusy()){
                sleep(15);
            }
            telemetry.addLine("Mid");
        }else{
            //right trajectory
            Uarm.setTargetPosition(40);
            Barm.setTargetPosition(40);
            Uarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Barm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Uarm.setPower(0.5);
            Barm.setPower(0.5);
            while (Barm.isBusy() && Uarm.isBusy()){
                sleep(15);
            }
            Claw.setPower(-.5);
            sleep(500);
            Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 1400);
            Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 1400);
            Lwheels.setPower(1);
            Rwheels.setPower(1);
            Rwheels.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Lwheels.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (Lwheels.isBusy() && Rwheels.isBusy()){
                sleep(15);
            }
            sleep(500);
            Lwheels.setTargetPosition(Lwheels.getTargetPosition() - 530);
            Rwheels.setTargetPosition(Rwheels.getTargetPosition() + 510);
            while (Lwheels.isBusy() && Rwheels.isBusy()){
                sleep(15);
            }
            sleep(500);
            Lwheels.setTargetPosition(Lwheels.getCurrentPosition() + 2000);
            Rwheels.setTargetPosition(Rwheels.getCurrentPosition() + 2000);
            while (Lwheels.isBusy() && Rwheels.isBusy()){
                sleep(15);
            }
            telemetry.addLine("Right");
        }
        telemetry.addData("Uarm", Uarm.getCurrentPosition());
        telemetry.addData("Barm", Barm.getCurrentPosition());
        telemetry.addData("Claw", Claw.getCurrentPosition());
        telemetry.addData("left", Lwheels.getCurrentPosition());
        telemetry.update();

        /* You wouldn't have this in your autonomous, this is just to prevent the sample from ending */
        //while (opModeIsActive()) {
        //    sleep(20);
        //    telemetry.update();}
    }

    void tagToTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
    }
}