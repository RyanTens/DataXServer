package org.tianlangstudio.data.hamal.server.http

import akka.actor.ActorSystem
import org.tianlangstudio.data.hamal.core.handler.LocalServerHandler
import org.tianlangstuido.data.hamal.core.HamalConf

/**
  *
  * Created by zhuhq on 17-4-13.
  */
object HttpServerApp extends App{
  implicit val system = ActorSystem("datax-http-server")
  val concurrence = if(args.length > 1) {
    args(0).toInt
  } else  {
    3
  }
  //使用LocalServerHandler多线程方式执行任务
  val httpServer = new HttpServer(new LocalServerHandler(concurrence), new HamalConf())
  sys.addShutdownHook {
    httpServer.stop()
  }

  while(true){
    Thread.sleep(10000)
  }
}