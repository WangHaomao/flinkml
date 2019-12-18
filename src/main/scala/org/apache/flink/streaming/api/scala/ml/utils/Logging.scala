package org.apache.flink.streaming.api.scala.ml.utils

import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

trait Logging {
  val log = Logger(LoggerFactory.getLogger(this.getClass))

  def logInfo(info: String): Unit ={
    log.info(info)
  }

  def logWarning(info: String): Unit ={
    log.warn(info)
  }


}
