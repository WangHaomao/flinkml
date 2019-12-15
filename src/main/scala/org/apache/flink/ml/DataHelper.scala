package org.apache.flink.ml
import org.apache.flink.ml.common.LabeledVector
import org.apache.flink.ml.math.DenseVector
import org.apache.flink.ml.math.Vector

import scala.collection.mutable.ArrayBuffer


object DataHelper {
  def loadCsvData(filePath:String, labelInx:Int): Seq[LabeledVector] = {
    val dataM = localFileReader(filePath)
    val attrsLen = dataM(0).length
    var attrsCnt = 0
    for (row <- dataM) yield {
      var lb = 0.0
      val attrs = Array.fill(attrsLen)(0.0)
      for(idx <- row.indices){
        if(idx == labelInx) lb = row(idx)
        else {
          attrs(attrsCnt) =  row(idx)
          attrsCnt += 1
        }
      }
      LabeledVector(lb, DenseVector(attrs))
    }
  }
  def loadCsvData(filePath:String): Seq[Vector] = {
    ???
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
