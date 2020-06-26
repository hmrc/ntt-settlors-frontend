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

package forms

import javax.inject.Inject
import forms.mappings.Mappings
import models.Name
import play.api.data.Form
import play.api.data.Forms._

class WhatIsSettlorsNameFormProvider @Inject() extends Mappings {

  def apply(): Form[Name] =
    Form(
      mapping(
        "firstName" -> text("whatIsSettlorsName.firstName.error.required")
          .verifying(maxLength(100, "whatIsSettlorsName.firstName.error.length")),
        "middleName" -> optionalText().verifying(optMaxLength(100, "whatIsSettlorsName.middleName.error.length")),
        "lastName" -> text("whatIsSettlorsName.lastName.error.required").verifying(maxLength(100, "whatIsSettlorsName.lastName.error.length"))
      )(Name.apply)(Name.unapply)
    )
}
