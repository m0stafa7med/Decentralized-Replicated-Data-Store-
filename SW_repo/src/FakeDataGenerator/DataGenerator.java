package src.FakeDataGenerator;

import java.util.Random;
//import src.Communication.SendThread;
import src.DataStore.DataStore;
import java.util.Queue;
import java.io.IOException;
import java.util.LinkedList;

public class DataGenerator implements Runnable{
	private Queue<Integer> Q=new LinkedList<>();
	private static DataStore dataStore = DataStore.creatInstance();
	private Random rnd = new Random();
	//private SendThread sendThread =new SendThread();//will be solved soon.


	private int generateData()
	{
		return rnd.nextInt(1000);
	}
	
	public void run()
	{
		int data; 
		while(true)
		{
			data = this.generateData();
			System.out.println(data);
			Q.add(data);
			//try {
			//	sendThread.sendMesg(data);        //send for all
			//} catch (IOException e) {
			//	e.printStackTrace();
			//}
			
			if(Q.size()>=500)
				dataStore.saveAll(Q);
		
			
			
		}
			
		
	}


}
