import Database.{ MySqlDb, PostgresDb, SqliteDb}
import service.{CsvHandler, PerformanceTest}

object testApp extends App {

  val MYSQLFILE = "/home/knoldus/CSV/MySQLDDL.csv"
  val POSTGRESFILE = "/home/knoldus/CSV/PostgreSQLDDL.csv"
  val SQLITEFILE = "/home/knoldus/CSV/SQLiteDDL.csv"


  val testMysql = new PerformanceTest(new MySqlDb)
  val testPostgres = new PerformanceTest(new PostgresDb)
  val testSqlite = new PerformanceTest(new SqliteDb)

  val mysqlOutput = testMysql.testCsvFile(MYSQLFILE)
  val postgresOutput = testPostgres.testCsvFile(POSTGRESFILE)
  val sqliteOutput = testSqlite.testCsvFile(SQLITEFILE)

  val combinedOutput = testSqlite.generateOutputFiles(MYSQLFILE, mysqlOutput, POSTGRESFILE, postgresOutput, SQLITEFILE, sqliteOutput)

  new CsvHandler().writeCsv("/home/knoldus/out/final.csv", combinedOutput)

}
