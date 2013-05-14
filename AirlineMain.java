/*  
 * CS1020 (AY2012/3 Sem2)  
 * Lab #2 Ex3  
 * Author    : Ipsita Mohapatra
 * Matric no.: A0101286N
 * Lab Group : 1
 * Description of program: To take in information about all the airlines with the from city, to city, departure time, arrival day, arrival time and cost. 
 * Take in a number of queries and process them and return the relevant information.
 */ 

import java.util.*;

class SGTime {
	public int hour;
	public int minute;
	private int totalMin;

	public SGTime() {}

	public SGTime(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
		this.totalMin = (hour*60) + minute;
	}

	public int getTime() {return totalMin;}
}

class Airline {
	private String from_city;
	private String to_city;
	private SGTime departure_time;
	private int arrival_day;
	private SGTime arrival_time;
	private int cost;

	public Airline() {}

	public Airline(String from_city, String to_city, SGTime departure_time, 
			int arrival_day, SGTime arrival_time, int cost) {
		this.from_city = from_city;
		this.to_city = to_city;
		this.departure_time = departure_time;
		this.arrival_day = arrival_day;
		this.arrival_time = arrival_time;
		this.cost = cost;
	}

	public String getFromCity () {return from_city;}

	public String getToCity () {return to_city;}

	public int getArrDay() {return arrival_day;}

	public int getCost() {return cost;}

	public int getDepartTime() {return departure_time.getTime();}

	public int getArrTime() {return arrival_time.getTime();}

	public int getDepartHr() {return departure_time.hour;}

	public int getDepartMin() {return departure_time.minute;}

	public int getArrHr() {return arrival_time.hour;}

	public int getArrMin() {return arrival_time.minute;}

}

class ProcessQueries {
	private ArrayList<Airline> airlines = new ArrayList<Airline>();

	ProcessQueries() {}

	public void addAirline(Airline al){
		airlines.add(al);
	}

	//Type1 Query
	public Airline getEarliestDeparture(String fromCity, String toCity, SGTime current_time){
		int earliestIndex=0, shortestTime=0, duration;
		boolean flag = true;

		for (int i=0;  i<airlines.size(); i++) {
			if ((fromCity.equals((airlines.get(i)).getFromCity())) 
					&& (toCity.equals((airlines.get(i)).getToCity())) ) {
				if (airlines.get(i).getDepartTime() > current_time.getTime()+1) { 
					duration = (airlines.get(i).getDepartTime() - current_time.getTime());
					if (flag) {
						shortestTime = duration;
						earliestIndex = i;
						flag = false;
					}
					if (duration < shortestTime) {
						shortestTime = duration;
						earliestIndex = i;
					}
				}
				else {
					duration = (airlines.get(i).getDepartTime() - current_time.getTime()) + (24*60);
					if (flag) {
						shortestTime = duration;
						earliestIndex = i;
						flag = false;
					}	
					if (duration < shortestTime) {
						shortestTime = duration;
						earliestIndex = i;
					}
				}
			}
		}
		return airlines.get(earliestIndex);
	}

	//Type2 Query
	public Airline getEarliestArrival(String fromCity, String toCity, SGTime current_time){
		int duration=0, shortestTime = 0, earliestIndex = 0;
		boolean flag = true;

		for(int i=0; i<airlines.size(); i++) {
			if ((fromCity.equals((airlines.get(i)).getFromCity())) 
					&& (toCity.equals((airlines.get(i)).getToCity())) ) {
				if (airlines.get(i).getDepartTime() > current_time.getTime()+1) { 
					duration = (((airlines.get(i).getArrDay()*24*60) - current_time.getTime()) + (airlines.get(i).getArrTime()));
					if (flag) {
						shortestTime = duration;
						earliestIndex = i;
						flag = false;
					}
					if (duration < shortestTime) {
						shortestTime = duration;
						earliestIndex = i;
					}
				}
				else {
					if (flag) {
						shortestTime = duration;
						earliestIndex = i;
						flag = false;
					}	
					if (duration < shortestTime) {
						shortestTime = duration;
						earliestIndex = i;
					}
				}
			}
		}
		return airlines.get(earliestIndex);
	}

	//Type3 Query 
	public Airline getShortestFlight(String fromCity, String toCity, SGTime current_time){
		int duration, minTime = 0, minIndex = 0;
		boolean flag = true;

		for(int i=0; i<airlines.size(); i++) {
			if ((fromCity.equals((airlines.get(i)).getFromCity())) 
					&& (toCity.equals((airlines.get(i)).getToCity()))) {
				duration = (((airlines.get(i).getArrDay()*24*60) - airlines.get(i).getDepartTime()) + (airlines.get(i).getArrTime()));
				if (flag) {
					minTime = duration;
					minIndex = i;
					flag = false;
				}
				if (duration < minTime) {
					minTime = duration;
					minIndex = i;
				}
			}
		}
		return airlines.get(minIndex);
	}

	//Type4 Query 
	public Airline getLowestCost(String fromCity, String toCity, SGTime current_time){
		int minCost = 0, index = 0;
		boolean flag = true;

		for (int i=0; i<airlines.size(); i++) {
			if ((fromCity.equals((airlines.get(i)).getFromCity())) 
					&& (toCity.equals((airlines.get(i)).getToCity()))) {
				if (flag) {
					minCost = airlines.get(i).getCost();
					index = i;
					flag = false;	
				}
				if (airlines.get(i).getCost() < minCost) {
					minCost = airlines.get(i).getCost();
					index = i;
				}
			}
		}
		return airlines.get(index);
	}
}

class AirlineMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numAirlines, numQueries, queryType;
		String fromCity, toCity, time; 
		String[] newTime;
		SGTime departTime, arrTime, currTime;
		int arrDay, cost, hour, min;
		Airline AL;
		ProcessQueries processQueries = new ProcessQueries();
		Airline outputAirline = new Airline();

		numAirlines = sc.nextInt();
		for (int i=0; i<numAirlines; i++) {
			fromCity = sc.next();
			toCity = sc.next();
			time = sc.next();
			newTime = time.split(":");
			hour = Integer.valueOf(newTime[0]);
			min = Integer.valueOf(newTime[1]);
			departTime = new SGTime(hour, min);

			arrDay = sc.nextInt();
			time = sc.next();
			newTime = time.split(":");
			hour = Integer.valueOf(newTime[0]);
			min = Integer.valueOf(newTime[1]);
			arrTime = new SGTime(hour, min);
			cost = sc.nextInt();
			AL = new Airline(fromCity, toCity, departTime, arrDay, arrTime, cost);
			processQueries.addAirline(AL);
		}

		// Read in Queries and process them and output results accordingly.
		numQueries = sc.nextInt();
		for (int i=0; i<numQueries; i++) {
			queryType = sc.nextInt();
			fromCity = sc.next();
			toCity = sc.next();

			time = sc.next();
			newTime = time.split(":");
			hour = Integer.valueOf(newTime[0]);
			min = Integer.valueOf(newTime[1]);
			currTime = new SGTime(hour, min);

			switch (queryType) {
				case 1:
					outputAirline = processQueries.getEarliestDeparture(fromCity, toCity, currTime);
					break;

				case 2:
					outputAirline = processQueries.getEarliestArrival(fromCity, toCity, currTime);
					break;

				case 3:
					outputAirline = processQueries.getShortestFlight(fromCity, toCity, currTime);
					break;

				case 4:
					outputAirline = processQueries.getLowestCost(fromCity, toCity, currTime);
					break;
			}

			System.out.print(outputAirline.getFromCity() + " " + outputAirline.getToCity() + " " + outputAirline.getDepartHr() + ":");
			if (outputAirline.getDepartMin() < 10) {
				System.out.print("0" + outputAirline.getDepartMin());	
			}
			else {
				System.out.print(outputAirline.getDepartMin());
			}
			System.out.print(" " + outputAirline.getArrDay() + " " + outputAirline.getArrHr() + ":");
			if (outputAirline.getArrMin() < 10) {
				System.out.print("0" + outputAirline.getArrMin());	
			}
			else {
				System.out.print(outputAirline.getArrMin());
			}
			System.out.println(" " + outputAirline.getCost());
		}
	}
}

