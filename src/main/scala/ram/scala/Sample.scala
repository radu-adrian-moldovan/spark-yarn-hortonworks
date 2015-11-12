package ram.scala

import org.apache.spark.{SparkConf, SparkContext}

case class RamModel(id:String, cod: String, date:String, type1:String, type2:String, internalId:String, category: String)

trait RAMLog {
  private val SIGNATURE = "#RAM# "

  def ramLog(msg: String) {
    println(SIGNATURE + ": " + msg)
  }
}

object Sample extends RAMLog{

  val PARQUET_DIR  = "/demo/data/Customer/dataParquet"
  val INPUT_DATA = "/demo/data/Customer/acct.txt"
  val INPUT_DATA_SNAPSHOT =  List (
    "5252102770|6276|20130313|31|103|355474044073896|R06",
    "5232100880|6389|20120408|21|468|359226043102589|R06")

  val hadoopConf = new org.apache.hadoop.conf.Configuration()
  val hdfs = org.apache.hadoop.fs.FileSystem.get(new
      java.net.URI("hdfs://sandbox.hortonworks.com:8020"), hadoopConf)

  def main (args: Array[String]) {

    val sparkConf = new SparkConf().setAppName("RAM-Scala")
    val sc = new SparkContext(sparkConf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._

    ramLog("******************************* v 1.0 ***************************************")
    ramLog("1. Loading file")
    var file  = sc.textFile(INPUT_DATA)

    ramLog("2. Split each row, and cache it!")
    var orginalData = file.map(_.split('|'))
    orginalData.cache

    ramLog("3. Start building the dataframe")
    val dataDF   = orginalData.map(item=>RamModel( item(0), item(1), item(2), item(3), item(4), item(5), item(6))).toDF()

    ramLog("4. Printing dataframe schema")
    dataDF.printSchema

    ramLog("5.0 Remove existing parquet file")


    try {
      hdfs.delete(new org.apache.hadoop.fs.Path(PARQUET_DIR), true)
    } catch
      {
        case error : Throwable => {

          ramLog("!!!!!!!! Error RAM demo")
          error.printStackTrace()


        }
      }

    ramLog("5.1 Write DF in parquet")
    dataDF.write.parquet(PARQUET_DIR)

    ramLog("6. Read DF in parquet")
    val dataParquet = sqlContext.read.parquet(PARQUET_DIR)


    ramLog("7. Create Table <dataParquetTable>")
    dataParquet.registerTempTable("dataParquetTable")

    ramLog("8. Filter DF in parquet")
    val dataFiltered = sqlContext.sql("select category from dataParquetTable where category = 'R02'")

    ramLog("9. Final count: " + dataFiltered.count)
    dataFiltered.count

    ramLog ("******************************* done! ***************************************")

  }

}
