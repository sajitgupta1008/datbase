package Database


class MySqlDb extends Database {

  override val url = "jdbc:mysql://localhost:3306/sj?allowMultiQueries=true"
  override val driver = "com.mysql.jdbc.Driver"
  override val username = "root"
  override val password = "hellomoto"

}