package api

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

//set JAVA_OPTS=-Dconfig.file=test/resources/local/application.conf

class SampleSimulation extends Simulation {
  private val conf = ConfigFactory.load()

  val httpProtocol = http
    .baseUrl(conf.getString("gatling.baseUrl.api")) // Here is the root for all relative URLs
    .acceptHeader(
      "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
    ) // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader(
      "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0"
    )

  val scn =
    scenario("Scenario Name") // A scenario is a chain of requests and pauses
      .exec(
        http("request_1")
          .get("/")
      )

  //  setUp(scn.inject(atOnceUsers(100)).protocols(httpProtocol))

  val waveInjections = Seq(
    nothingFor(2.0 seconds),
    constantUsersPerSec(10) during (2.0 seconds),
    constantUsersPerSec(20) during (2.0 seconds),
    constantUsersPerSec(30) during (2.0 seconds),
    constantUsersPerSec(40) during (2.0 seconds),
    constantUsersPerSec(50) during (10 seconds),
    constantUsersPerSec(40) during (2.0 seconds),
    constantUsersPerSec(30) during (2.0 seconds),
    constantUsersPerSec(20) during (2.0 seconds),
    constantUsersPerSec(10) during (2.0 seconds),
    nothingFor(2.0 seconds),
  )

  setUp(
    scn
      .inject(
//        rampUsersPerSec(1) to conf.getInt("gatling.users") during (30 seconds),
//        constantUsersPerSec(conf.getInt("gatling.users")) during (1 minute)
        waveInjections
      )
      .protocols(httpProtocol)
  )

}
