package controllers

import play.api.mvc.AsyncResult
import play.api.mvc.Result
import play.api.test.Helpers

object Extract {
  def from(r: Result): (Int, Map[String, String], String) = {
    val newRes = r match {
      case AsyncResult(promise) => Helpers.await(promise)
      case _ => r
    }
    (Helpers.status(newRes), Helpers.headers(newRes), Helpers.contentAsString(newRes))
  }
}
