package hw1;
/**
 * Camera battery simulation
 * @author Neha
 *
 */

public class CameraBattery {
	/**
	 * The formulas that guide the rate of charging and discharging are as follows
	 */
	
	public static final int NUM_CHARGER_SETTINGS = 4;
	public static final double CHARGE_RATE = 2.0; 
	public static final double DEFAULT_CAMERA_POWER_CONSUMPTION = 1.0;
	
	
	private double PowerConsumption; 
	private double BatteryCapacity; 
	private int chargerSetting;
	private double batteryCharge;
	private double TotalCameraCharge;
	private double totalDrain;
	private double cameraBatteryconnected;
	private double ExternalBattery;
	private double cameraDrain;
	private double IncreaseInCharge;
	
	
	
	/**
	 * This is one public constructor.
	 */
	
	public CameraBattery(double batteryStartingCharge, double batteryCapacity) {
		BatteryCapacity = batteryCapacity;
		batteryCharge = Math.min(batteryStartingCharge, batteryCapacity);
		PowerConsumption = DEFAULT_CAMERA_POWER_CONSUMPTION;
		chargerSetting = 0;
		cameraBatteryconnected = 0;
		totalDrain = 0;
		ExternalBattery = 0;
		
	}
	
	
	/**
	 * Indicates the user has pressed the setting button one time on the external charger.
	 */
	
	
	public void buttonPress() {
		chargerSetting = (chargerSetting + 1) % NUM_CHARGER_SETTINGS;
		
	}
	
	
	/**
	 * The method returns the actual amount the battery has been charged. 
	 * @param minutes
	 * @return TotalCameraCharge
	 */
	
	
	public double cameraCharge(double minutes){ 
		double charge = minutes * CHARGE_RATE*cameraBatteryconnected;
		double originalcharge = TotalCameraCharge;
		TotalCameraCharge = Math.min(TotalCameraCharge + charge,BatteryCapacity);
		batteryCharge+= TotalCameraCharge - originalcharge;
		return TotalCameraCharge - originalcharge;
		

	}
	
	
	/**
	 * The method returns the actual amount drain from the battery. 
	 * @param minutes
	 * @return cameraDrain
	 */
	
	
	public double drain(double minutes) {
		cameraDrain = Math.min(batteryCharge,PowerConsumption * minutes * cameraBatteryconnected);
		TotalCameraCharge = TotalCameraCharge - cameraDrain;
		batteryCharge = batteryCharge - cameraDrain;
		totalDrain = totalDrain + cameraDrain;
		return cameraDrain;
	}
	
	
	/**
	 * The method returns the actual amount the battery has been charged.   
	 * @param minutes
	 * @return
	 */
		
	 
	 public double externalCharge(double minutes) {
		 IncreaseInCharge = Math.min( BatteryCapacity - batteryCharge, minutes * chargerSetting * ExternalBattery * CHARGE_RATE);
		 batteryCharge += IncreaseInCharge;
		 return IncreaseInCharge;
		 
	 }
	 
	 
	 /**
	  * Get the current charge of the camera's battery. 
	  * @return TotalCameraCharge
	  */
	 
	 public double getCameraCharge() {
		 return TotalCameraCharge;
	 }
	 
	 
	 /**
	  * Reset the battery monitoring system by setting the total battery drain count back to 0. 
	  */
	 
	 public void resetBatteryMonitor() {
		 totalDrain = 0; 	 
		 
		 
	 }
	 
	 
	 /**
	  * Get the battery's capacity. 
	  * @return BatteryCapacity
	  */
	 
	 public double getBatteryCapacity() {
		return BatteryCapacity;
		 
	 }
	 
	 
	 /**
	  * Get the power consumption of the camera.
	  * @return PowerConsumption
	  */
	 
	 public double getCameraPowerConsumption() {
		return PowerConsumption; 
		 
	 }
	 
	 
	 /**
	  * Get the external charger setting.
	  * @return chargerSetting
	  */
	 
	 public int getChargerSetting() {
		return chargerSetting;
		 
	 }
	 
	 
	 /**
	  * Get the total amount of power drained from the battery since the last time the battery monitor was started or reset. 
	  * @return totalDrain
	  */
	 
	 public double getTotalDrain() {
		return totalDrain;
	 }
	 
	 
	 /**
	  * Move the battery to the external charger. Updates any variables as needed to represent the move. 
	  */
	 
	 
	 public void moveBatteryExternal() {
		 ExternalBattery = 1;
		 cameraBatteryconnected = 0;
		 TotalCameraCharge = 0;
		 
	 }
	 
	 
	 /**
	  * Move the battery to the camera. Updates any variables as needed to represent the move. 	
	  */
	 
	 
	 public void moveBatteryCamera() {
		 ExternalBattery = 0;
		 cameraBatteryconnected = 1;
		 TotalCameraCharge = batteryCharge;
		 
		 
	 }
	 
	 
	 /**
	  * Remove the battery from either the camera or external charger. Updates any variables as needed to represent the removal. 
	  */
	 
	 public void removeBattery() {
		 cameraBatteryconnected = 0;
		 ExternalBattery = 0;
		 TotalCameraCharge = 0;
		 
		 
	 }
	 
	 
	 /**
	  * Get the battery's current charge. 
	  * @return batteryCharge
	  */
	 
	 
	 public double getBatteryCharge() {
		 return batteryCharge ;
		 
	 }
	 
	 
	 /**
	  * Set the power consumption of the camera. 
	  * @param 
	  */
	 
	 
	public void setCameraPowerConsumption(double cameraPowerConsumption) {
		this.PowerConsumption = cameraPowerConsumption;
	} 
}