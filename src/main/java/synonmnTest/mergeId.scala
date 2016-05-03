import scala.io.Source
import scala.util.parsing.json.JSON;

object destCity {

  def main(args: Array[String]) {

    val url = "http://sinai.sankuai.com/sinai/poi/query/detail/"
    val line = "41363477"
    //for (line <- Source.fromFile(args(0)).getLines()) {
      val tmpUrl = url + line
      println(tmpUrl)
      try {
        val result = JSON.parseFull(Source.fromURL(tmpUrl).getLines().mkString)
        result match {
          case Some(e: List[Map[String, Double]]@unchecked) => {

            val mergeId  = e(0)("mergedId")
            println(line + "\t" + mergeId)
          }
          case _ => println("failed:" + result)
        }

      } catch {
        case ex: Exception => {
          println(line + "\t" + ex)
        }
      }

   // }
  }
}
