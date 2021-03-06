#!/bin/bash

echo ""
echo "Applying migration DoYouKnowCountryOfNationality"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /doYouKnowCountryOfNationality                        controllers.willtrust.DoYouKnowCountryOfNationalityController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /doYouKnowCountryOfNationality                        controllers.willtrust.DoYouKnowCountryOfNationalityController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeDoYouKnowCountryOfNationality                  controllers.willtrust.DoYouKnowCountryOfNationalityController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeDoYouKnowCountryOfNationality                  controllers.willtrust.DoYouKnowCountryOfNationalityController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "doYouKnowCountryOfNationality.title = doYouKnowCountryOfNationality" >> ../conf/messages.en
echo "doYouKnowCountryOfNationality.heading = doYouKnowCountryOfNationality" >> ../conf/messages.en
echo "doYouKnowCountryOfNationality.checkYourAnswersLabel = doYouKnowCountryOfNationality" >> ../conf/messages.en
echo "doYouKnowCountryOfNationality.error.required = Select yes if doYouKnowCountryOfNationality" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryDoYouKnowCountryOfNationalityUserAnswersEntry: Arbitrary[(DoYouKnowCountryOfNationalityPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[DoYouKnowCountryOfNationalityPage.type]";\
    print "        value <- arbitrary[Boolean].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryDoYouKnowCountryOfNationalityPage: Arbitrary[DoYouKnowCountryOfNationalityPage.type] =";\
    print "    Arbitrary(DoYouKnowCountryOfNationalityPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(DoYouKnowCountryOfNationalityPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def doYouKnowCountryOfNationality: Option[Row] = userAnswers.get(DoYouKnowCountryOfNationalityPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"doYouKnowCountryOfNationality.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(yesOrNo(answer)),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.DoYouKnowCountryOfNationalityController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"doYouKnowCountryOfNationality.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration DoYouKnowCountryOfNationality completed"
