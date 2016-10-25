// this is my first autonomous program so don't anything fancy

// fyi, i like making SMALL and weird comments, so just ignore them
/**          _ _  _ _ _        _ _ _          _ _ _      _ _      __
             \\ /    /       / _ _  \        |  _ \    |  |     |  |
              \/    /       / /    \ \       | |  \ \  |  |     |  |
              /    /       / /_ _ _ \ \      | |   \ \ |  |     |  |
             /    /\      / /_ _ _ _ \ \     | |    \ \|  |     |__|
            /    / \\    / /          \ \    | |     \    |      __
           /_ _ /   \\  /_/            \_\   |_|      \_ _|     |__|

           If you are wondering about why I made this, i was stumped



           start with moving the robot.(near the bottom) uncomment the color sensor



*/

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;


/**
 * Created by lulyork on 9/27/2016.
 */
@Autonomous(name = "Concept: AtonomousHolonomicDrive", group = "Concept")
public class AtonomousHolonomicDrive extends LinearOpMode {


    //declaring motors
    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
    DcMotor ballShooter;
    //servos
    Servo colorSensorServo;
    Servo ballChuteServo;
    //sensors
    ColorSensor colorSensor;


    static final double    MOTOR_SPEED = 0.5;
    static final double CHUTE_LOWERED = 0.5;
    static final double RELOAD_SPEED = 0.05;
 //   public AutonomousHolonomicDrive(){
//}
    //this would be more fun if i knew what was happening







    @Override
    public void runOpMode () throws InterruptedException {

        float hsvValues[] = {0F,0F,0F};


       // values is a reference to the hsvValues array.
        final float values[] = hsvValues;




        colorSensor.enableLed(false);


        // convert the RGB values to HSV values.
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8 , hsvValues);

        /*telemetry.addData("Clear", colorSensor.alpha());
        telemetry.addData("Red  ", colorSensor.red());
        telemetry.addData("Green", colorSensor.green());
        telemetry.addData("Blue ", colorSensor.blue());
        telemetry.addData("Hue", hsvValues[0]);*/
        // Hm... sandwiches

        //motors
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        ballShooter = hardwareMap.dcMotor.get("ballShooter");
        //servos
        colorSensorServo = hardwareMap.servo.get("colorSensorServo");
        ballChuteServo = hardwareMap.servo.get("ballChuteServo");

        //color sensor
        colorSensor = hardwareMap.colorSensor.get("color sensor");


        //These work without reversing (Tetrix motors).
        //AndyMark motors may be opposite, in which case uncomment these lines:
        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
        //motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        //motorBackRight.setDirection(DcMotor.Direction.REVERSE);



        // I have no idea what i'm doing

        /* for copying
          motorFrontRight.setPower(MOTOR_SPEED);
          motorFrontLeft.setPower(MOTOR_SPEED);
          motorBackRight.setPower(MOTOR_SPEED);
          motorBackLeft.setPower(MOTOR_SPEED);
          */

        waitForStart();
        //lower the ball chute
        ballChuteServo.setPosition(CHUTE_LOWERED);
        sleep(500);
        //launching balls
        //ball 1
        ballShooter.setPower(MOTOR_SPEED);
        sleep(100);
        //reloading
        ballShooter.setPower(RELOAD_SPEED);
        sleep(250);
        // ball 2
        ballShooter.setPower(MOTOR_SPEED);
        sleep(1000);
        //heading towards the beacon...
        motorFrontLeft.setPower(MOTOR_SPEED);
        motorBackRight.setPower(MOTOR_SPEED);
        sleep(1250);

        //pressing the beacon buttons...
        colorSensor.enableLed(false);
        if(colorSensor.blue() > colorSensor.red() && colorSensor.blue() > colorSensor.green()){
        colorSensorServo.setPosition(0.7);
        }
        else if(colorSensor.red() > colorSensor.blue() && colorSensor.red() > colorSensor.green()){
            colorSensorServo.setPosition(0.3);
        }

        sleep(100);
        colorSensorServo.setPosition(0.5);
        //moving on to the next beacon...
        motorFrontRight.setPower(MOTOR_SPEED);
        motorFrontLeft.setPower(MOTOR_SPEED);
        motorBackRight.setPower(MOTOR_SPEED);
        motorBackLeft.setPower(MOTOR_SPEED);
        sleep(250);
        //pressing the beacon buttons...again....
        if(colorSensor.blue() > colorSensor.red() && colorSensor.blue() > colorSensor.green()){
            colorSensorServo.setPosition(0.7);
        }
        else if(colorSensor.red() > colorSensor.blue() && colorSensor.red() > colorSensor.green()){
            colorSensorServo.setPosition(0.3);
        }
        sleep(100);
        colorSensorServo.setPosition(0.5);
        //knocking off the cap ball
        motorFrontRight.setPower(MOTOR_SPEED);
        motorBackLeft.setPower(MOTOR_SPEED);
        sleep(1000);
        idle();
        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackRight.setPower(0);
        motorBackLeft.setPower(0);
    }

}

