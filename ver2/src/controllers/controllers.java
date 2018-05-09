package controllers;

//======================================================================
//======================================================================
interface BaseController 
{
	void run();

	void finish();
}


//======================================================================
//======================================================================
public class controllers 
{
	  
	   public BaseController getShape(String shapeType)
	   {
	      if(shapeType == null)
	      {
	         return null;
	      }		
	      if(shapeType.equalsIgnoreCase("CIRCLE"))
	      {
	         return new Circle();
	         
	      } else if(shapeType.equalsIgnoreCase("RECTANGLE"))
	      {
	         return new Rectangle();
	         
	      } else if(shapeType.equalsIgnoreCase("SQUARE"))
	      {
	         return new Square();
	      }
	      
	      return null;
	   }
}







//class workgood
//{ 
//	private Motors motors = new Motors();
//	private Sensors sensor = new Sensors();
//	int light;
//	int array[] = {32, 37, 39, 42, 44, 46, 48};
//	
//	public void run() 
//	{
//		light = sensor.getLightSensorVal();
//		
//		if( light <= array[0])
//		{
//			motors.setPower(50, 10);
//		}
//		
//		else if( light > array[0]  &&  light <  array[1])
//		{
//			motors.setPower(80, 40);
//		}
//		else if(light >= array[1] && light < array[2])
//		{
//			motors.setPower(80, 60);
//		}	
//		
//		else if(light >= array[2] && light <= array[3])
//		{
//			motors.setPower(80, 75);
//		}			
//		else if( light > array[3] && light < array[4])
//		{
//			motors.setPower(75, 80);
//		}
//		
//		else if( light >= array[4] && light <= array[5])
//		{
//			motors.setPower(60, 80);
//		}
//		else if( light > array[5] && light < array[6])
//		{
//			motors.setPower(40, 80);
//		}
//		
//		else if( light >=  array[6])
//		{
//			motors.setPower(10, 50);
//		}
//			
//	}
//}




	

