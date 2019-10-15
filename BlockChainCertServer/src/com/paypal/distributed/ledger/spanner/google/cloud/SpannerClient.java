package com.paypal.distributed.ledger.spanner.google.cloud;

import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.DatabaseId;
import com.google.cloud.spanner.ResultSet;
import com.google.cloud.spanner.Spanner;
import com.google.cloud.spanner.SpannerOptions;
import com.google.cloud.spanner.Statement;

/**
 * A quick start code for Cloud Spanner. It demonstrates how to setup the Cloud Spanner client and
 * execute a simple query using it against an existing database.
 */
public class SpannerClient {
  public static void main(String args[]) throws Exception {

    // Instantiates a client
    SpannerOptions options = SpannerOptions.newBuilder().setProjectId("datacrunk").build();
    
    Spanner spanner = options.getService();


    // Name of your instance & database.
    String instanceId = "blockchainidentity";
    String databaseId = "mobileidentity";	
    try {
      // Creates a database client
      DatabaseClient dbClient = spanner.getDatabaseClient(DatabaseId.of(
    		  "datacrunk", instanceId, databaseId));
      // Queries the database
      ResultSet resultSet = dbClient.singleUse().executeQuery(Statement.of("SELECT 1"));

      System.out.println("\n\nResults:");
      // Prints the results
      while (resultSet.next()) {
        System.out.printf("%d\n\n", resultSet.getLong(0));
      }
    } finally {
      // Closes the client which will free up the resources used
      spanner.close();
    }
  }
}