#!/bin/bash

echo ""
echo "Applying migration IsCountryOfNationalitySameAsCountryOfResidency"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /isCountryOfNationalitySameAsCountryOfResidency                        controllers.IsCountryOfNationalitySameAsCountryOfResidencyController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /isCountryOfNationalitySameAsCountryOfResidency                        controllers.IsCountryOfNationalitySameAsCountryOfResidencyController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeIsCountryOfNationalitySameAsCountryOfResidency                  controllers.IsCountryOfNationalitySameAsCountryOfResidencyController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeIsCountryOfNationalitySameAsCountryOfResidency                  controllers.IsCountryOfNationalitySameAsCountryOfResidencyController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "isCountryOfNationalitySameAsCountryOfResidency.title = isCountryOfNationalitySameAsCountryOfResidency" >> ../conf/messages.en
echo "isCountryOfNationalitySameAsCountryOfResidency.heading = isCountryOfNationalitySameAsCountryOfResidency" >> ../conf/messages.en
echo "isCountryOfNationalitySameAsCountryOfResidency.checkYourAnswersLabel = isCountryOfNationalitySameAsCountryOfResidency" >> ../conf/messages.en
echo "isCountryOfNationalitySameAsCountryOfResidency.error.required = Select yes if isCountryOfNationalitySameAsCountryOfResidency" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryIsCountryOfNationalitySameAsCountryOfResidencyUserAnswersEntry: Arbitrary[(IsCountryOfNationalitySameAsCountryOfResidencyPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[IsCountryOfNationalitySameAsCountryOfResidencyPage.type]";\
    print "        value <- arbitrary[Boolean].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryIsCountryOfNationalitySameAsCountryOfResidencyPage: Arbitrary[IsCountryOfNationalitySameAsCountryOfResidencyPage.type] =";\
    print "    Arbitrary(IsCountryOfNationalitySameAsCountryOfResidencyPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(IsCountryOfNationalitySameAsCountryOfResidencyPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def isCountryOfNationalitySameAsCountryOfResidency: Option[Row] = userAnswers.get(IsCountryOfNationalitySameAsCountryOfResidencyPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"isCountryOfNationalitySameAsCountryOfResidency.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(yesOrNo(answer)),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.IsCountryOfNationalitySameAsCountryOfResidencyController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"isCountryOfNationalitySameAsCountryOfResidency.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration IsCountryOfNationalitySameAsCountryOfResidency completed"
