package org.cwowhappy.study.flink.sample01

import org.apache.flink.streaming.api.scala._

object SimpleSample {
    def main(args: Array[String]): Unit = {
        val environment = StreamExecutionEnvironment.getExecutionEnvironment
        val filename = "/data/cwowhappy/dataset/flink-test/simple-sample-01.txt"
        val dataStream: DataStream[(String, Int)] = environment.readTextFile(filename).map((_, 1))
        dataStream.keyBy(_._1).countWindow(5, 3).reduce((item1, item2) => (item1._1, item1._2 + item2._2)).print()

        environment.execute("simple-sample")
    }

}
