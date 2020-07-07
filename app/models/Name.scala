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

package models

import play.api.libs.json.{JsObject, OWrites, Reads, __}

final case class Name(firstName: String,
                      middleName: Option[String],
                      lastName: String)

object Name {

  implicit lazy val reads: Reads[Name] = {
    import play.api.libs.functional.syntax._
    (
        (__ \ "firstName").read[String] and
        (__ \ "middleName").readNullable[String] and
        (__ \ "lastName").read[String]
      ) (Name.apply _)
  }

  implicit lazy val writes: OWrites[Name] = {
    import play.api.libs.functional.syntax._
    (
        (__ \ "firstName").write[String] and
        (__ \ "middleName").writeNullable[String] and
        (__ \ "lastName").write[String]
      ) (unlift(Name.unapply))
  }
}
