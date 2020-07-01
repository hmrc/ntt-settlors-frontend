package pages

import pages.behaviours.PageBehaviours


class WhatIsCountryOfHeadOfficePageSpec extends PageBehaviours {

  "WhatIsCountryOfHeadOfficePage" - {

    beRetrievable[String](WhatIsCountryOfHeadOfficePage)

    beSettable[String](WhatIsCountryOfHeadOfficePage)

    beRemovable[String](WhatIsCountryOfHeadOfficePage)
  }
}
