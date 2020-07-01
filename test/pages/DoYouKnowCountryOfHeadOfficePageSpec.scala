package pages

import pages.behaviours.PageBehaviours

class DoYouKnowCountryOfHeadOfficePageSpec extends PageBehaviours {

  "DoYouKnowCountryOfHeadOfficePage" - {

    beRetrievable[Boolean](DoYouKnowCountryOfHeadOfficePage)

    beSettable[Boolean](DoYouKnowCountryOfHeadOfficePage)

    beRemovable[Boolean](DoYouKnowCountryOfHeadOfficePage)
  }
}
