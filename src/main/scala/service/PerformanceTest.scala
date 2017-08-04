package service

import Database.Database
import Models.QueryTest
import org.apache.log4j.Logger

class PerformanceTest(database: Database) {

  private val logger = Logger.getLogger(this.getClass)
  private val csvHandler: CsvHandler = new CsvHandler

  def testCsvFile(filePath: String): List[Array[String]] = {

    val lines: List[QueryTest] = csvHandler.readCsv(filePath)
    val dbConnection = database.connectToDatabase()

    val output: List[Array[String]] = lines.map {

      case QueryTest(testId, testDesc, precondition, query, postcondition) =>

        database.runQuery(precondition, dbConnection)

        val executionTime = database.runQuery(query, dbConnection)

        database.runQuery(postcondition, dbConnection)

        Array(testId.toString, testDesc, query, executionTime.toString)
    }

    dbConnection.close()
    logger.info("connection closed")
    output

  }


  def generateOutputFiles(mysqlFile: String, mysqlContent: List[Array[String]], postgresFile: String,
                          postgresContent: List[Array[String]], sqliteFile: String, sqliteContent: List[Array[String]]): List[Array[String]] = {

    csvHandler.writeCsv(mysqlFile, mysqlContent)
    csvHandler.writeCsv(postgresFile, postgresContent)
    csvHandler.writeCsv(sqliteFile, sqliteContent)

    val combinedOutput: List[Array[String]] = mysqlContent.map {
      case Array(testId, testDesc, query, executionTime) =>
        val postgresTime = postgresContent.filter(arr => arr(0) == testId).map(arr => arr(3))
        val sqliteTime = sqliteContent.filter(arr => arr(0) == testId).map(arr => arr(3))

        Array(testId, testDesc, executionTime, postgresTime.head, sqliteTime.head)
    }

    combinedOutput

  }

}

