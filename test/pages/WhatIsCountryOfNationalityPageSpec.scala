package pages

import pages.behaviours.PageBehaviours


class WhatIsCountryOfNationalityPageSpec extends PageBehaviours {

  "WhatIsCountryOfNationalityPage" - {

    beRetrievable[String](WhatIsCountryOfNationalityPage)

    beSettable[String](WhatIsCountryOfNationalityPage)

    beRemovable[String](WhatIsCountryOfNationalityPage)
  }
}
