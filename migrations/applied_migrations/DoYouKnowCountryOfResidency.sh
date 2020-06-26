#!/bin/bash

echo ""
echo "Applying migration DoYouKnowCountryOfResidency"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /doYouKnowCountryOfResidency                        controllers.DoYouKnowCountryOfResidencyController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /doYouKnowCountryOfResidency                        controllers.DoYouKnowCountryOfResidencyController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeDoYouKnowCountryOfResidency                  controllers.DoYouKnowCountryOfResidencyController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeDoYouKnowCountryOfResidency                  controllers.DoYouKnowCountryOfResidencyController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "doYouKnowCountryOfResidency.title = doYouKnowCountryOfResidency" >> ../conf/messages.en
echo "doYouKnowCountryOfResidency.heading = doYouKnowCountryOfResidency" >> ../conf/messages.en
echo "doYouKnowCountryOfResidency.checkYourAnswersLabel = doYouKnowCountryOfResidency" >> ../conf/messages.en
echo "doYouKnowCountryOfResidency.error.required = Select yes if doYouKnowCountryOfResidency" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryDoYouKnowCountryOfResidencyUserAnswersEntry: Arbitrary[(DoYouKnowCountryOfResidencyPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[DoYouKnowCountryOfResidencyPage.type]";\
    print "        value <- arbitrary[Boolean].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryDoYouKnowCountryOfResidencyPage: Arbitrary[DoYouKnowCountryOfResidencyPage.type] =";\
    print "    Arbitrary(DoYouKnowCountryOfResidencyPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(DoYouKnowCountryOfResidencyPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def doYouKnowCountryOfResidency: Option[Row] = userAnswers.get(DoYouKnowCountryOfResidencyPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"doYouKnowCountryOfResidency.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(yesOrNo(answer)),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.DoYouKnowCountryOfResidencyController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"doYouKnowCountryOfResidency.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration DoYouKnowCountryOfResidency completed"
