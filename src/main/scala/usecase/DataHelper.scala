package usecase

import org.apache.flink.ml.common.LabeledVector
import org.apache.flink.ml.math.{DenseVector, Vector}


object DataHelper {
  def loadTrainData(): Seq[LabeledVector] = {
    val trainPath = "data/adult_train.csv"
    val trainData = loadCsvData(trainPath,labelInx = 0)
    trainData
  }
  def loadValidData(): Seq[LabeledVector] = {
    val testPath = "data/adult_test.csv"
    val testData = loadCsvData(testPath,labelInx = 0)
    testData
  }


  def loadCsvData(filePath:String, labelInx:Int): Seq[LabeledVector] = {
    val dataM = localFileReader(filePath)
    val attrsLen = dataM(0).length
    var attrsCnt = 0
    for (row <- dataM) yield {
      var lb = 0.0
      val attrs = Array.fill(attrsLen - 1)(0.0)
      for(idx <- row.indices){
        if(idx == labelInx) lb = row(idx)
        else {
          attrs(attrsCnt) = row(idx)
          attrsCnt += 1
        }
      }
      attrsCnt = 0
      if(lb.toInt != 0 && lb.toInt != 1){
        println("label error")
      }
      println(attrs.mkString(","))
      LabeledVector(lb.toInt, DenseVector(attrs))
    }
  }
  def loadCsvData(filePath:String): Seq[Vector] = {
    val dataM = localFileReader(filePath)
    for (row <- dataM) yield {
      DenseVector(row.toArray)
    }
  }
  /**
   * Csv data without head columns
   * */
  def localFileReader(filePath:String):Array[scala.Vector[Double]] = {
    val bufferedSource = io.Source.fromFile(filePath)
    val dataArray:Array[scala.Vector[Double]] = (
      for (line <- bufferedSource.getLines) yield {
      val rows: scala.Vector[Double] = line.split(",").map(e =>{
        val ele = e.trim
        if(ele == "") Double.NaN
        else ele.toDouble
      }).toVector
      rows
    }).toArray
    bufferedSource.close
    dataArray
  }
}
