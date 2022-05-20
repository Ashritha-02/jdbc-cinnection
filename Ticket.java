package railways;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
public class Ticket {
   

    static int availableLowerBerths = 4;
    static int availableMiddleBerths = 4;
    static int availableUpperBerths = 4;
    static int availableRacTickets = 4;
    static int availableWaitingList = 2;

    static Queue<Integer> waitingList = new LinkedList<>();
    static Queue<Integer> racList =  new LinkedList<>();
    static List<Integer> bookedTicketList =  new ArrayList<>();
    static List<Integer> lowerBerthsPositions = new ArrayList<>(Arrays.asList(4));
    static List<Integer> middleBerthsPositions = new ArrayList<>(Arrays.asList(4));
    static List<Integer> upperBerthsPositions = new ArrayList<>(Arrays.asList(4));
    static List<Integer> racPositions = new ArrayList<>(Arrays.asList(4));
    static List<Integer> waitingListPositions = new ArrayList<>(Arrays.asList(2));

    static Map<Integer, Passenger> passengers = new HashMap<>();
	

   
    public void bookTicket(Passenger p,int id) {
    	Dbconnection objdbconnnection=new Dbconnection();
    	Connection connection =objdbconnnection.get_connection();
    	
    	PreparedStatement ps=null;
    	try {
    		String sql = "insert into passenger"
    				+ " values (?, ?, ?, ?)";
    		ps=connection.prepareStatement(sql);
    		ps.setInt(1, id);
    		ps.setString(2, p.name);
    		ps.setInt(3, p.age);
    		ps.setString(4, p.berthPreference);
    		//System.out.println(ps);
    		ps.executeUpdate();
    	}catch(Exception e) {
    		System.out.println(e);
    	}
    }

    
    public void addToRAC(Passenger p,int racInfo,String allotedRAC)
    {
       
        p.number = racInfo;
        p.alloted = allotedRAC;
       
        passengers.put(p.passengerId,p);
       
        racList.add(p.passengerId);
        
        availableRacTickets--;
        
        racPositions.remove(0);

        System.out.println("Added to RAC Successfully");
    }

    
    public void addToWaitingList(Passenger p,int waitingListInfo,String allotedWL)
    {
        
        p.number = waitingListInfo; 
        p.alloted = allotedWL;
       
        passengers.put(p.passengerId,p);
        
        waitingList.add(p.passengerId);
        
        availableWaitingList--;
        
        waitingListPositions.remove(0);

        System.out.println(" Added to Waiting List Successfully");
    }

    
    public void cancelTicket(int id)
    {
    	Dbconnection objdbconnnection=new Dbconnection();
    	Connection connection =objdbconnnection.get_connection();
    	
    	PreparedStatement ps=null;
    	try {
    		String sql = "delete from passenger where id=?";
    		
    		ps=connection.prepareStatement(sql);
    		ps.setInt(1, id);
    		
    		System.out.println(ps);
    		ps.executeUpdate();
    	}catch(Exception e) {
    		System.out.println(e);
    	}
        
        Passenger p = passengers.get(id);
        passengers.remove(Integer.valueOf(id));
        
        bookedTicketList.remove(Integer.valueOf(id));

        
        int positionBooked = p.number;

        System.out.println("cancelled Successfully");

        
       if(p.alloted.equals("L")) 
        { 
          availableLowerBerths++;
          lowerBerthsPositions.add(positionBooked);
        }
        else if(p.alloted.equals("M"))
        { 
          availableMiddleBerths++;
          middleBerthsPositions.add(positionBooked);
        }
        else if(p.alloted.equals("U"))
        { 
          availableUpperBerths++;
          upperBerthsPositions.add(positionBooked);
        }

        
        if(racList.size() > 0)
        {
            
            Passenger passengerFromRAC = passengers.get(racList.poll());
            int positionRac = passengerFromRAC.number;
            racPositions.add(positionRac);
            racList.remove(Integer.valueOf(passengerFromRAC.passengerId));
            availableRacTickets++;

           
            if(waitingList.size()>0)
            {
                Passenger passengerFromWaitingList = passengers.get(waitingList.poll());
                int positionWL = passengerFromWaitingList.number;
                waitingListPositions.add(positionWL);
                waitingList.remove(Integer.valueOf(passengerFromWaitingList.passengerId));

                passengerFromWaitingList.number = racPositions.get(0);
                passengerFromWaitingList.alloted = "RAC";
                racPositions.remove(0);
                racList.add(passengerFromWaitingList.passengerId);
                
                availableWaitingList++;
                availableRacTickets--;
            }
            
            Main.bookTicket(passengerFromRAC, id);
        }
    
    }

    
    public void printAvailable()
    {
    
    	
        System.out.println("Available Lower Berths "  + availableLowerBerths);
        System.out.println("Available Middle Berths "  + availableMiddleBerths);
        System.out.println("Available Upper Berths "  + availableUpperBerths);
        System.out.println("Available RACs " + availableRacTickets);
        System.out.println("Available Waiting List " + availableWaitingList);
        
    }

    
    public void printPassengers(int id)
    {
       /* if(passengers.size() == 0)
 {
           System.out.println("No details of passengers");
            return;
        }*/
    	Dbconnection objdbconnnection=new Dbconnection();
    	Connection connection =objdbconnnection.get_connection();
    	
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	try {
    		String sql = "select * from passenger where id= ?" ;
    		ps=connection.prepareStatement(sql);
    		ps.setInt(1, id);
    		
    		//System.out.println(ps);
    		rs=ps.executeQuery();
    		
    		while(rs.next()) {
    			System.out.println("id- "+rs.getInt("id"));
    			System.out.println("name- "+rs.getString("name"));
    			System.out.println("age- "+rs.getInt("age"));
    			System.out.println("birthpreference- "+rs.getString("birthpreference"));
    		}
    	}catch(Exception e) {
    		System.out.println(e);
        /*for(Passenger p : passengers.values())
        {
            System.out.println("PASSENGER ID " + p.passengerId );
            System.out.println(" Name " + p.name );
            System.out.println(" Age " + p.age );
            System.out.println(" Status " + p.number + p.alloted);
            
        }*/
    }


	
}}
