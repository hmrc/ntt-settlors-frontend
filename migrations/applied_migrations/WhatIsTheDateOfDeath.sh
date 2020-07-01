#!/bin/bash

echo ""
echo "Applying migration WhatIsTheDateOfDeath"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whatIsTheDateOfDeath                  controllers.willtrust.WhatIsTheDateOfDeathController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whatIsTheDateOfDeath                  controllers.willtrust.WhatIsTheDateOfDeathController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeWhatIsTheDateOfDeath                        controllers.willtrust.WhatIsTheDateOfDeathController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeWhatIsTheDateOfDeath                        controllers.willtrust.WhatIsTheDateOfDeathController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whatIsTheDateOfDeath.title = WhatIsTheDateOfDeath" >> ../conf/messages.en
echo "whatIsTheDateOfDeath.heading = WhatIsTheDateOfDeath" >> ../conf/messages.en
echo "whatIsTheDateOfDeath.hint = For example, 12 11 2007" >> ../conf/messages.en
echo "whatIsTheDateOfDeath.checkYourAnswersLabel = WhatIsTheDateOfDeath" >> ../conf/messages.en
echo "whatIsTheDateOfDeath.error.required.all = Enter the whatIsTheDateOfDeath" >> ../conf/messages.en
echo "whatIsTheDateOfDeath.error.required.two = The whatIsTheDateOfDeath" must include {0} and {1} >> ../conf/messages.en
echo "whatIsTheDateOfDeath.error.required = The whatIsTheDateOfDeath must include {0}" >> ../conf/messages.en
echo "whatIsTheDateOfDeath.error.invalid = Enter a real WhatIsTheDateOfDeath" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheDateOfDeathUserAnswersEntry: Arbitrary[(WhatIsTheDateOfDeathPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[WhatIsTheDateOfDeathPage.type]";\
    print "        value <- arbitrary[Int].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheDateOfDeathPage: Arbitrary[WhatIsTheDateOfDeathPage.type] =";\
    print "    Arbitrary(WhatIsTheDateOfDeathPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(WhatIsTheDateOfDeathPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def whatIsTheDateOfDeath: Option[Row] = userAnswers.get(WhatIsTheDateOfDeathPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"whatIsTheDateOfDeath.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(Literal(answer.format(dateFormatter))),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.WhatIsTheDateOfDeathController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"whatIsTheDateOfDeath.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration WhatIsTheDateOfDeath completed"
