package info.mukel.telegrambot4s.examples

import java.net.URLEncoder
import info.mukel.telegrambot4s._, api._, methods._, models._, Implicits._

/**
  * Let me Google that for you!
  */
class LmgtfyBot(token: String) extends TestBot(token) with Polling with Commands {
  def lmgtfyUrl(query: Seq[String]) =
    "http://lmgtfy.com/?q=" + URLEncoder.encode(query.mkString(" "), "UTF-8")

  on("/lmgtfy", "Generates Google search links for posting/trolling in forums and communities.") { implicit msg => args =>
    reply(lmgtfyUrl(args), disableWebPagePreview = true)
  }

  on("/lmgtfy2") { implicit msg => args =>
    val singleButtonMarkup = InlineKeyboardMarkup(Seq(Seq(
      InlineKeyboardButton("Google it now!", url = lmgtfyUrl(args))
    )))

    reply("Let me Google that for you!",
      replyMarkup = singleButtonMarkup)
  }
}
