package forms

import javax.inject.Inject

import forms.mappings.Mappings
import play.api.data.Form

class WhatIsCountryOfNationalityFormProvider @Inject() extends Mappings {

  def apply(): Form[String] =
    Form(
      "value" -> text("whatIsCountryOfNationality.error.required")
        .verifying(maxLength(100, "whatIsCountryOfNationality.error.length"))
    )
}
