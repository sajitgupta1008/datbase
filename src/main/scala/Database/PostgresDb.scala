package Database


class PostgresDb extends Database {
  override val url = "jdbc:postgresql://localhost:5432/postgres"
  override val driver = "org.postgresql.Driver"
  override val username = "postgres"
  override val password = "postgres"
}