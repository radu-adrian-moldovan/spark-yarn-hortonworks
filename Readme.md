Sample Spark 1.4.1 (Hortonworks)
================================

This maven project uses Hortonwork's and  Apache Spark that can be run against a Hadoop Cluster managed through Yarn.


**Prerequisites:**
HDP 2.3.2 with Hortonworks Sandbox
Source: http://hortonworks.com/hdp/downloads/#hdf


**Build, deploy, summit job**

Running the **'deploy.sh'** will produce an output and "Submitted application is given"

15/11/12 14:42:57 INFO SecurityManager: Changing modify acls to: hdfs

15/11/12 14:42:57 INFO SecurityManager: SecurityManager: authentication disabled; ui acls disabled; users with view permissions: Set(hdfs); users with modify permissions: Set(hdfs)

15/11/12 14:42:57 INFO Client: Submitting application 25 to ResourceManager

15/11/12 14:42:57 INFO YarnClientImpl: Submitted application application_1447159372353_0025

15/11/12 14:42:58 INFO Client: Application report for **application_1447159372353_0025** (state: ACCEPTED)



**Fetch logs, filer job/user outputs**

Running the **'logs.sh application_id'** (e.g. [user_demo]$ logs application_1447159372353_0025) will produce:

- an input in logs directory (RAM_application_1447159372353_0025.log (<- a filtered job/user outputs ) and application_1447159372353_0025.log)

- an output (see below)



 LogType:stdout
 Log Upload Time:Thu Nov 12 14:43:50 +0000 2015
 LogLength:1195
 Log Contents:
 
 #RAM# : ******************************* v 1.0 ***************************************
 
 #RAM# : 1. Loading file
 
 #RAM# : 2. Split each row, and cache it!
 
 #RAM# : 3. Start building the dataframe
 
 #RAM# : 4. Printing dataframe schema
 
 root
 
  |-- id: string (nullable = true)
 
  |-- cod: string (nullable = true)
 
  |-- date: string (nullable = true)
 
  |-- type1: string (nullable = true)
 
  |-- type2: string (nullable = true)
 
  |-- internalId: string (nullable = true)
 
  |-- category: string (nullable = true)


 #RAM# : 5.0 Remove existing parquet file

 #RAM# : 5.1 Write DF in parquet

 #RAM# : 6. Read DF in parquet

 #RAM# : 7. Create Table <dataParquetTable>

 #RAM# : 8. Filter DF in parquet

 #RAM# : 9. Final count: 436

 #RAM# : ******************************* done! ***************************************


enjoy!