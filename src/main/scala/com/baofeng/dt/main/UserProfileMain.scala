package com.baofeng.dt.main

import com.baofeng.dt.util.ConfigUtil
import org.apache.spark.sql.SparkSession

/**
  * 用户画像主程序入口，数据来源主要以wirless、pc、tv埋点数据以及线上用户信息数据库，以及第三方用户信息
  * 主要流程为：数据收集、处理 —— 模型构建（基于规则 + 机器学习） —— 打用户标签 —— 以规定格式存储
  * 具体细节待调研完毕后整理到README.md
  *
  * @author sunliangliang 2019/4/26 https://github.com/sunliangliang9201/user_profile
  * @version 1.0
  */
object UserProfileMain {

  def main(args: Array[String]): Unit = {
    val appConf = ConfigUtil.getConf.get
    val spark = SparkSession.builder().appName("UserProfile").master("local[3]").getOrCreate()
    val hiveDF = spark.read.format("jdbc")
      .option("url", appConf.getString("hive_url"))
      .option("dbtable", appConf.getString("hive_dbtable"))
      .option("user", appConf.getString("hive_user"))
      .option("password", appConf.getString("hive_passwd"))
      .load()
    hiveDF.select("country").show(5)

  }
}
