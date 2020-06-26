#!/bin/bash

echo ""
echo "Applying migration WhatIsCountryOfResidency"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whatIsCountryOfResidency                        controllers.WhatIsCountryOfResidencyController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whatIsCountryOfResidency                        controllers.WhatIsCountryOfResidencyController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeWhatIsCountryOfResidency                  controllers.WhatIsCountryOfResidencyController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeWhatIsCountryOfResidency                  controllers.WhatIsCountryOfResidencyController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whatIsCountryOfResidency.title = whatIsCountryOfResidency" >> ../conf/messages.en
echo "whatIsCountryOfResidency.heading = whatIsCountryOfResidency" >> ../conf/messages.en
echo "whatIsCountryOfResidency.checkYourAnswersLabel = whatIsCountryOfResidency" >> ../conf/messages.en
echo "whatIsCountryOfResidency.error.required = Enter whatIsCountryOfResidency" >> ../conf/messages.en
echo "whatIsCountryOfResidency.error.length = WhatIsCountryOfResidency must be 100 characters or less" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsCountryOfResidencyUserAnswersEntry: Arbitrary[(WhatIsCountryOfResidencyPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[WhatIsCountryOfResidencyPage.type]";\
    print "        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsCountryOfResidencyPage: Arbitrary[WhatIsCountryOfResidencyPage.type] =";\
    print "    Arbitrary(WhatIsCountryOfResidencyPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(WhatIsCountryOfResidencyPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def whatIsCountryOfResidency: Option[Row] = userAnswers.get(WhatIsCountryOfResidencyPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"whatIsCountryOfResidency.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(lit\"$answer\"),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.WhatIsCountryOfResidencyController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"whatIsCountryOfResidency.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration WhatIsCountryOfResidency completed"
