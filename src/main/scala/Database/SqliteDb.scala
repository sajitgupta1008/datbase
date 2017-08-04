package Database

import java.sql.{Connection, DriverManager}

class  SqliteDb extends Database {

  override val url = "jdbc:sqlite:/home/knoldus/sj.db"
  override val driver = "org.sqlite.JDBC"

  override def connectToDatabase(): Connection = {
    Class.forName(driver)
    DriverManager.getConnection(url)

  }



}