package com.example.rds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.AmazonRDSClientBuilder;
import com.amazonaws.services.rds.model.DBInstance;
import com.amazonaws.services.rds.model.DescribeDBInstancesResult;

public class ExampleOracle {

	public static void main(String[] args) {
		
		//Authenticate RDS Client
		AmazonRDS awsRDS = AmazonRDSClientBuilder.standard().withRegion(Regions.AP_SOUTHEAST_2).build();
		
        //Let's fetch all the databases created in AWS Console
		DescribeDBInstancesResult dbInstResult = awsRDS.describeDBInstances();
		
		List<DBInstance> dbInstances= dbInstResult.getDBInstances();
		
		for(DBInstance dbInst:dbInstances)
		{
			System.out.println("DB Instance:: "+ dbInst.getDBName());
		}
		
		//Let's Connect to our database
		String RDS_INSTANCE_HOSTNAME = "Enter Your Endpoint";
		String RDS_INSTANCE_PORT = "1521";
		
		String JDBC_URL = "jdbc:oracle:thin:@" + RDS_INSTANCE_HOSTNAME + ":" + RDS_INSTANCE_PORT + ":ORCL";
		
		try {

			Connection connection=DriverManager.getConnection(  
					JDBC_URL,"admin","password");
			
	        //verify the connection is successful
	        Statement stmt= connection.createStatement();
	        ResultSet rs=stmt.executeQuery("SELECT * FROM DUAL");
	        while (rs.next()) {
	        	    String id = rs.getString(1);
	            System.out.println(id); //Should print "X"
	        }

	        //close the connection
	        stmt.close();
	        connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
