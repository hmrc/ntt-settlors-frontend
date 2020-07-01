/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers

import play.api.i18n.Messages
import play.api.libs.json.{JsBoolean, JsObject, JsValue, Json}
import services.CountryService

trait CountryLookup {

  val countryService: CountryService
  private val selectedField:(String, JsValue) = ("selected", JsBoolean(true))

  private def getMsg(messages: Messages, defaultMessage:Option[String]):String =
    defaultMessage.map(msg => messages(msg)).getOrElse("")

  def countries(messages: Messages, selected: Option[String] = None, defaultMessage:Option[String] = None): Seq[JsObject] =
    Seq(Json.obj("value" -> "", "text" -> getMsg(messages, defaultMessage))) ++
    countryService.getCountries.getOrElse(Seq.empty).map(nv => {
      val json = Json.obj(("value", nv.value), ("text", nv.name))
      selected.map(s => if(nv.value == s) json + selectedField else json).getOrElse(json)
    })
}
