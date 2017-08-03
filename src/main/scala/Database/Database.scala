package Database

import java.sql.{Connection, DriverManager, SQLException, Statement}

import org.apache.log4j.Logger

trait Database {

  private val logger = Logger.getLogger(this.getClass)
  val url: String
  val driver: String
  val username: String = ""
  val password: String = ""

  def connectToDatabase(): Connection = {
    try {
      Class.forName(driver)
      DriverManager.getConnection(url, username, password)
    }
    catch {
      case  e:SQLException => throw e
    }

  }

  def runQuery(query: String, connection: Connection): Long = {


    try {
      val statement  = connection.createStatement()
      logger.info(s"connection established, executing QUERY : $query")
      val startTime = System.nanoTime()
      statement.execute(query)
      System.nanoTime() - startTime
    }
    catch {
      case e:Exception => throw e
    }

  }

}
