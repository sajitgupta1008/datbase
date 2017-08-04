package service

import java.io.{BufferedWriter, FileNotFoundException, FileReader, FileWriter}

import Models.QueryTest
import com.opencsv.{CSVReader, CSVWriter}

import scala.collection.JavaConversions._

class CsvHandler {

  private val zero = 0
  private val one = 1
  private val two = 2
  private val three = 3
  private val four = 4
  private val OUTPUT_DIR = "src/test/output/"

  def readCsv(filePath: String): List[QueryTest] = {

    try {
      val fileReader = new FileReader(filePath)
      try {
        val csvReader = new CSVReader(fileReader, ',', '\"')
        val queryList = for (row <- csvReader.readAll) yield {

          QueryTest(row(zero).toInt, row(one), row(two).trim, row(three).trim, row(four).trim)
        }

        queryList.toList

      }
      finally {
        fileReader.close()
      }
    }
    catch {
      case ex: FileNotFoundException => throw ex
    }

  }

  def writeCsv(filePath: String, content: List[Array[String]]): Unit = {

    lazy val writer = new BufferedWriter(new FileWriter(OUTPUT_DIR + filePath.substring(filePath.lastIndexOf("/"))))
    try {
      val csvWriter = new CSVWriter(writer)
      csvWriter.writeAll(content)
      writer.close()
    }
    catch {
      case ex: Exception => throw ex
    }
  }

}


