package com.baofeng.dt.util

import com.typesafe.config.{Config, ConfigFactory}

/**
  * 读取自定义配置文件，不包含hive、hadoop、spark、其他.xml等配置文件
  *
  * @author sunliangliang 2019/4/26 https://github.com/sunliangliang9201/user_profile
  * @version 1.0
  */
object ConfigUtil {

  val conf = ConfigFactory.load("application.properties")

  /**
    * 读取默认的配置文件并返回读取key的对象
    * @return 返回Config对象
    */
  def getConf: Option[Config] = {
    Some(conf)
  }
}
