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

package models.autocomplete

import com.google.inject.ImplementedBy
import javax.inject.{Inject, Singleton}
import play.api.Environment
import play.api.libs.json.{JsValue, Json}

import scala.io.Source

@ImplementedBy(classOf[GovUkCountryDataProvider])
trait CountryDataProvider {
  def fetch: Option[Seq[NameValuePair]]
}

@Singleton
class GovUkCountryDataProvider @Inject()(env: Environment) extends CountryDataProvider {

  // TODO: use this to filter countries that aren't included in the schema
  lazy val countryCodes: Set[String] = Set()

  private val resourcePath = "public/location-autocomplete-canonical-list.json"

  private def stripCode(value: String): String = value.substring(value.indexOf(":") + 1)

  private def isEncoded(value: String): Boolean = value.indexOf(":") > -1

  private def getJson: Option[JsValue] = env.resourceAsStream(resourcePath) map { stream =>
    Json.parse(Source.fromInputStream(stream).mkString)
  }

  override def fetch: Option[Seq[NameValuePair]] = getJson map { j =>
    j.as[Seq[NameValuePair]] map {
      case n@NameValuePair(_, value) if isEncoded(value) => n.copy(value = stripCode(value))
      case n => n
    }
    // TODO: Uncomment this to filter by country codes
    //    collect {
    //    //      case n if countryCodes.contains(n.value.toUpperCase()) => n
    //    //    }
  }
}
