import Database.{ MySqlDb, PostgresDb, SqliteDb}
import service.{CsvHandler, PerformanceTest}

object testApp extends App {

  val MYSQLFILE = "src/test/resources/MySQLDDL.csv"
  val POSTGRESFILE = "src/test/resources/PostgreSQLDDL.csv"
  val SQLITEFILE = "src/test/resources/SQLiteDDL.csv"


  val testMysql = new PerformanceTest(new MySqlDb)
  val testPostgres = new PerformanceTest(new PostgresDb)
  val testSqlite = new PerformanceTest(new SqliteDb)

  val mysqlOutput = testMysql.testCsvFile(MYSQLFILE)
  val postgresOutput = testPostgres.testCsvFile(POSTGRESFILE)
  val sqliteOutput = testSqlite.testCsvFile(SQLITEFILE)

  val combinedOutput = testSqlite.generateOutputFiles(MYSQLFILE, mysqlOutput, POSTGRESFILE, postgresOutput, SQLITEFILE, sqliteOutput)

  new CsvHandler().writeCsv("src/test/output/final.csv", combinedOutput)

}
