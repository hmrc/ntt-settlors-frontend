package forms

import forms.behaviours.StringFieldBehaviours
import play.api.data.FormError

class WhatIsCountryOfNationalityFormProviderSpec extends StringFieldBehaviours {

  val requiredKey = "whatIsCountryOfNationality.error.required"
  val lengthKey = "whatIsCountryOfNationality.error.length"
  val maxLength = 100

  val form = new WhatIsCountryOfNationalityFormProvider()()

  ".value" - {

    val fieldName = "value"

    behave like fieldThatBindsValidData(
      form,
      fieldName,
      stringsWithMaxLength(maxLength)
    )

    behave like fieldWithMaxLength(
      form,
      fieldName,
      maxLength = maxLength,
      lengthError = FormError(fieldName, lengthKey, Seq(maxLength))
    )

    behave like mandatoryField(
      form,
      fieldName,
      requiredError = FormError(fieldName, requiredKey)
    )
  }
}
