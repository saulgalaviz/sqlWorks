import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.text.ParseException;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class JDBC_Example
{
	static Scanner kb = new Scanner(System.in);
	//---------------------------------------------------------------------------------------------------------------
	public static void main(String[] args) throws ParseException 
	{
		Connection conn;
		Statement stmt;
		
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost; databaseName=DESKTOP-FBDT0VD\\SOUTSQL; integratedSecurity=true");
			stmt = conn.createStatement();
		
			while(true)
			{
				System.out.print("Command: ");
				
				String input = kb.nextLine();
				
				switch(input.trim())
				{
					case "ITD":
						insertTripData(stmt);
						break;
					case "AD":
						addDriver(stmt);
						break;
					case "AB":
						addBus(stmt);
						break;
					case "ATO":
						addTripOffering(stmt);
						break;
					case "DB":
						deleteBus(stmt);
						break;
					case "DTO":
						deleteTripOffering(stmt);
						break;
					case "CB":
						changeBus(stmt);
						break;
					case "CD":
						changeDriver(stmt);
						break;
					case "DS":
						displayschedule(stmt);
						break;
					case "DTS":
						displayTripStops(stmt);
						break;
					case "DW":
						displayWeekly(stmt);
						break;
					case "EX":
						System.exit(0);
						break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//---------------------------------------------------------------------------------------------------------------
	public static void insertTripData(Statement stmt) throws SQLException
	{
		System.out.print("Trip Number-");
		String tripNo = kb.nextLine().trim();
		System.out.print("Date-");
		String date = kb.nextLine().trim();
		System.out.print("Start Time-");
		String startTime = kb.nextLine().trim();
		System.out.print("Stop Number-");
		String stop = kb.nextLine().trim();
		System.out.print("Arrival Time-");
		String arrivalTime = kb.nextLine().trim();
		System.out.print("Starting Time-");
		String actualStart = kb.nextLine().trim();
		System.out.print("Arriving Time-");
		String actualArrival = kb.nextLine().trim();
		System.out.print("Time to be in by-");
		String passIn = kb.nextLine().trim();
		System.out.print("Time to be out by-");
		String passOut = kb.nextLine().trim();
			
		try
		{
			stmt.execute("INSERT INTO ActualTripStopInfo VALUES ('" + tripNo + "', '" + date + "', '" + startTime 
					+ "', '" + stop + "', '" + arrivalTime + "', '" + actualStart + "', '" + actualArrival 
					+ "', '" + passIn + "', '" + passOut + "')");
		}catch(SQLServerException e){
		}
		System.out.println("Recorded!");
	}
	//---------------------------------------------------------------------------------------------------------------
	public static void addDriver(Statement stmt) throws SQLException
	{
		System.out.print("Driver Name-");
		String driver = kb.nextLine().trim();
		System.out.print("Phone NO-");
		String phone = kb.nextLine().trim();
		
		try
		{
			stmt.execute("INSERT INTO Driver VALUES ('" + driver + "', '" + phone + "')");
			
			System.out.println("Updated!");
		
		}catch (SQLServerException e){
		}
	}
	//---------------------------------------------------------------------------------------------------------------
	public static void addBus(Statement stmt) throws SQLException
	{
		System.out.print("Bus NO-");
		String bus = kb.nextLine().trim();
		System.out.print("Model-");
		String model = kb.nextLine().trim();
		System.out.print("Year-");
		String year = kb.nextLine().trim();
			
		try
		{
			stmt.execute("INSERT INTO Bus VALUES ('" + bus + "', '" + model + "', '" + year + "')");
				
			System.out.println("Updated!");
			
		}catch (SQLServerException e){
		}
	}	
	//---------------------------------------------------------------------------------------------------------------
	public static void addTripOffering(Statement stmt) throws SQLException
	{
		System.out.print("Trip NO-");
		String tripNo = kb.nextLine().trim();
		System.out.print("Date-");
		String date = kb.nextLine().trim();
		System.out.print("Start Time-");
		String startTime = kb.nextLine().trim();
		System.out.print("Arrival Time-");
		String arrivalTime = kb.nextLine().trim();
		System.out.print("Driver-");
		String driver = kb.nextLine().trim();
		System.out.print("Bus NO-");
		String bus = kb.nextLine().trim();
			
		try{
			
			stmt.execute("INSERT INTO TripOffering VALUES ('" + tripNo + "', '" + date + "', '" + startTime 
					+ "', '" + arrivalTime + "', '" + driver + "', '" + bus + "')");
				
		}catch (SQLServerException e){
		}
	}	
	//---------------------------------------------------------------------------------------------------------------
	public static void deleteBus(Statement stmt) throws SQLException
	{
		System.out.print("Bus No-");
		String bus = kb.nextLine().trim();
		
		try
		{
			if(stmt.executeUpdate("DELETE Bus WHERE BusID = '" + bus + "'") == 0)
				System.out.println("No Bus ID = " + bus);
			
			else
				System.out.println("Deleted!");
		
		}catch(SQLServerException e){
			System.out.println("No Bus ID = " + bus);
		}
	}	
	//---------------------------------------------------------------------------------------------------------------
	public static void deleteTripOffering(Statement stmt) throws SQLException
	{
		System.out.print("Trip NO-");
		String tripNo = kb.nextLine().trim();
		System.out.print("Date-");
		String date = kb.nextLine().trim();
		System.out.print("Start Time-");
		String startTime = kb.nextLine().trim();
		
		try
		{
			if(stmt.executeUpdate(tripNo + date + startTime) == 0)
				System.out.println("No such time offered");
			
			else
				System.out.println("Deleted");
			
		}catch (SQLServerException e){
		}
	}
	//---------------------------------------------------------------------------------------------------------------
	public static void changeDriver(Statement stmt) throws SQLException
	{
		System.out.print("Driver Name-");
		String driver = kb.nextLine().trim();
		System.out.print("Trip NO-");
		String tripNo = kb.nextLine().trim();
		System.out.print("Date-");
		String date = kb.nextLine().trim();
		System.out.print("Start Time-");
		String startTime = kb.nextLine().trim();
		
		try{
		
			if(stmt.executeUpdate(driver + tripNo + date + startTime) == 0)
				System.out.println("No such trip offering");
			
			else
				System.out.println("Updated!");
			
		}catch (SQLServerException e){
		}
	}
	//---------------------------------------------------------------------------------------------------------------
	public static void changeBus(Statement stmt) throws SQLException
	{
		System.out.print("Bus NO-");
		String bus = kb.nextLine().trim();
		System.out.print("Trip NO-");
		String tripNo = kb.nextLine().trim();
		System.out.print("Date-");
		String date = kb.nextLine().trim();
		System.out.print("Start Time-");
		String startTime = kb.nextLine().trim();
		
		try{
		
			if(stmt.executeUpdate(bus + tripNo + date + startTime + "'") == 0)
				System.out.println("No Such Offering");
			
			else
				System.out.println("Updated!");
			
		}catch (SQLServerException e){
		}
	}
	//---------------------------------------------------------------------------------------------------------------
	public static void displayWeekly(Statement stmt) throws ParseException, SQLException
	{
		System.out.print("Driver Name-");
		String driver = kb.nextLine().trim();
		System.out.print("Date-");
		String dateStr = kb.nextLine().trim();
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
		Calendar date = new GregorianCalendar();
		date.setTime(df.parse(dateStr));
		
		for(int count = 0; count < 7; count++)
		{
			dateStr = df.format(date.getTime());
			
			try
			{
				ResultSet rs = stmt.executeQuery("SELECT TripNumber, Date, scheduledStartTime, scheduledArrivalTime, BusID " +
				"FROM TripOffering " + "WHERE DriverName LIKE '" + driver + "' " + "AND Date = '" + dateStr + "' " +
				"Order By scheduledStartTime ");
				
				ResultSetMetaData rsmd = rs.getMetaData();
				
				int colCount = rsmd.getColumnCount();
				
				if(count == 0)
				{
					for(int count2 = 0; count2 < colCount; count2++)
					{
						if(count2 == 1 || count2 == 3)
							System.out.print(rsmd.getColumnName(count2));
						
						else
							System.out.print(rsmd.getColumnName(count2));
					}
				}
				while(rs.next()){
				
					for(int count2 = 0; count2 <= colCount; count2++)
						System.out.print(rs.getString(count2));
				}
				rs.close();
			
		}catch(SQLServerException e){
		}
	
		date.add(Calendar.DATE, 1);
		}
	}	
	//---------------------------------------------------------------------------------------------------------------
	public static void displayTripStops(Statement stmt) throws SQLException
	{
		System.out.print("Trip NO-");
		
		String tripNo = kb.nextLine().trim();
		
		try{
		
			ResultSet rs = stmt.executeQuery("SELECT * " +"FROM TripStopInfo " +
			"WHERE TripNumber = '" + tripNo + "' " + "Order By SequenceNumber ");
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int colCount = rsmd.getColumnCount();
			
			for(int count = 0; count < colCount; count++)
				System.out.print(rsmd.getColumnName(count) + "\t");
			
			while(rs.next())
			{
				for(int i = 1; i <= colCount; i++)
					System.out.print(rs.getString(i) + "\t\t");
			}
			
			rs.close();
			
		}catch (SQLServerException e){
		}
	}
	//---------------------------------------------------------------------------------------------------------------
	public static void displayschedule(Statement stmt) throws SQLException
	{
		System.out.print("Start Location-");
		String startLoc = kb.nextLine().trim();
		System.out.print("Ending Destination-");
		String destLoc = kb.nextLine().trim();
		System.out.print("Date-");
		String date = kb.nextLine().trim();
		
		try
		{
			ResultSet rs = stmt.executeQuery("SELECT T0.scheduledStartTime, T0.scheduledArrivalTime, T0.DriverName, T0.BusID " +
			"FROM TripOffering T0, Trip T1 WHERE T1.StartLocationName LIKE '" + startLoc + "' AND " +
			"T1.DestinationName LIKE '" + destLoc + "' AND T0.Date = '" + date + "' AND " +
			"T1.TripNumber = T0.TripNumber Order by scheduledStartTime ");
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int colCount = rsmd.getColumnCount();
			
			for(int count = 0; count < colCount; count++)
				System.out.print(rsmd.getColumnName(count) + "\t");
			
			while(rs.next())
				for(int i = 1; i <= colCount; i++)
					System.out.print(rs.getString(i) + "\t\t");
			
		}catch (SQLServerException e){
		}
	}	
	//---------------------------------------------------------------------------------------------------------------
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
