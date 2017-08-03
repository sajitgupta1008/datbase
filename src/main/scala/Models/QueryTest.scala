package Models

case class QueryTest(testCaseId: Int, testCaseDesc: String, precondition: String,
                     query: String, postcondition: String)