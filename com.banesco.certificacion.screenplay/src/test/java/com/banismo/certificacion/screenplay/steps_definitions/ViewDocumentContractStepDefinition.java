package com.banismo.certificacion.screenplay.steps_definitions;

import com.banismo.certificacion.screenplay.exeptions.GeneralException;
import com.banismo.certificacion.screenplay.interactions.SwitchWindow;
import com.banismo.certificacion.screenplay.questions.UrlDocument;
import com.banismo.certificacion.screenplay.tasks.GoToSavingAccount;
import com.banismo.certificacion.screenplay.user_interfaces.HomeCompanyPage;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

import static com.banismo.certificacion.screenplay.util.ExceptionsConstant.URL_DOES_NOT_MATCH;
import static com.banismo.certificacion.screenplay.util.GeneralConstant.URL_DOCUMENT_PDF;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class ViewDocumentContractStepDefinition {

    @Managed(driver = "chrome")
    private WebDriver herBrowser;
    private final Actor actor = Actor.named("General");
    private final HomeCompanyPage homePage = new HomeCompanyPage();


    @Before
    public void setUp(){
        actor.can(BrowseTheWeb.with(herBrowser));
    }


    @Given("^I go to the companies home page$")
    public void iGoToTheCompaniesHomePage() {
        actor.wasAbleTo(Open.browserOn(homePage));
    }


    @When("^I enter savings accounts through the deposit menu and select document$")
    public void iEnterSavingsAccountsThroughTheDepositMenuAndSelectDocument() {
            actor.attemptsTo(
                    GoToSavingAccount.toSeeDocument(),
                    SwitchWindow.andClose(herBrowser)
            );
    }

    @Then("^I should see that the document is correct$")
    public void iShouldSeeThatTheDocumentIsCorrect() {
        actor.should(seeThat(UrlDocument.isCorrect(herBrowser,URL_DOCUMENT_PDF))
                        .orComplainWith(GeneralException.class,URL_DOES_NOT_MATCH)
        );
    }
}
