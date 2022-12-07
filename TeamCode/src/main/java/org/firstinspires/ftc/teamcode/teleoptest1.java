package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class teleoptest1 extends LinearOpMode {
    private DcMotor arm1;
    private DcMotor arm2;
    private Servo torqueL;
    private Servo torqueR;
    private DcMotor backL;
    private DcMotor frontL;
    private DcMotor backR;
    private DcMotor frontR;
    private CRServo speedL;
    private CRServo speedR;
    int press = 0;

    @Override
    public void runOpMode() {

        arm1 = hardwareMap.get(DcMotor.class, "arm1");
        arm2 = hardwareMap.get(DcMotor.class, "arm2");
        torqueL = hardwareMap.get(Servo.class, "L torque");
        torqueR = hardwareMap.get(Servo.class, "R torque");
        backL = hardwareMap.get(DcMotor.class, "backL");
        frontL = hardwareMap.get(DcMotor.class, "frontL");
        backR = hardwareMap.get(DcMotor.class, "backR");
        frontR = hardwareMap.get(DcMotor.class, "frontR");
        speedL = hardwareMap.get(CRServo.class, "L speed");
        speedR = hardwareMap.get(CRServo.class, "R speed");

        arm1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        torqueR.scaleRange(0.2, 0.8);
        torqueL.scaleRange(0.2, 0.8);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        arm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            move();
            arm();
            claw();
        }
    }
    public void move(){
        float drive = gamepad1.left_stick_y;
        float turn = gamepad1.left_stick_x;

        backL.setPower(((drive - turn) / 4) * 3);
        frontL.setPower(((drive - turn) / 4) * 3);
        backR.setPower((-(drive + turn) / 4) * 3);
        frontR.setPower((-(drive + turn) / 4) * 3);
    }
    public void arm(){
        if (gamepad1.y) {
            // high
            arm1.setTargetPosition(-1405);
            arm2.setTargetPosition(-1405);
            arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm1.setPower(1);
            arm2.setPower(1);
        } else if (gamepad1.b) {
            // medium
            arm1.setTargetPosition(-1000);
            arm2.setTargetPosition(-1000);
            arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm1.setPower(1);
            arm2.setPower(1);
        } else if (gamepad1.a) {
            // ground
            arm1.setTargetPosition(-40);
            arm2.setTargetPosition(-40);
            arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm1.setPower(0.5);
            arm2.setPower(0.5);
        } else if (gamepad1.x) {
            // low
            arm1.setTargetPosition(-625);
            arm2.setTargetPosition(-625);
            arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm1.setPower(1);
            arm2.setPower(1);
        }
    }
    public void claw(){
        if (gamepad2.left_bumper) {
            // closed
            torqueL.setPosition(0);
            torqueR.setPosition(0.05);
            sleep(500);
            speedL.setPower(0);
            speedR.setPower(0);
            press = 0;
        } else if (gamepad2.right_bumper) {
            // open
            torqueL.setPosition(0.05);
            torqueR.setPosition(0);
            press = 1;
        } if (press == 1 || gamepad1.right_bumper){
            speedL.setPower(-1);
            speedR.setPower(1);
        }
        if (gamepad2.dpad_down) {
            // if at junction higher than ground
            arm1.setTargetPosition(arm1.getCurrentPosition() + 40);
            arm2.setTargetPosition(arm2.getCurrentPosition() + 40);
            arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm1.setPower(0.5);
            arm2.setPower(0.5);
        } else if (gamepad2.dpad_up){
            arm1.setTargetPosition(arm1.getCurrentPosition() - 40);
            arm2.setTargetPosition(arm2.getCurrentPosition() - 40);
            arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm1.setPower(0.5);
            arm2.setPower(0.5);
        } else if (gamepad2.dpad_down && arm2.getCurrentPosition() > -50 && arm1.getCurrentPosition() > -50) {
            // if at ground height
        }
    }
}