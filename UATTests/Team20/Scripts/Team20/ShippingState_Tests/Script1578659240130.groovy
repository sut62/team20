import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import static org.junit.Assert.*

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:8080/#/login')

WebUI.setText(findTestObject('Object Repository/Page_Parcel Delivery System/input__form-control'), '1')

WebUI.setText(findTestObject('Object Repository/Page_Parcel Delivery System/input__form-control_1'), 'chatdanai374@gmail.com')

WebUI.click(findTestObject('Object Repository/Page_Parcel Delivery System/input__btn btn-primary btn-block btn-lg'))

WebUI.click(findTestObject('Object Repository/Page_Parcel Delivery System/a_'))

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Parcel Delivery System/select_Chatdanai PhakaketPattarasit Lomthai_9273ba'), 
    '1', true)

WebUI.setText(findTestObject('Object Repository/Page_Parcel Delivery System/input_ Package ID_input-with-list'), '3')

WebUI.click(findTestObject('Object Repository/Page_Parcel Delivery System/button_'))

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Parcel Delivery System/select_'), '2', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Parcel Delivery System/select__1'), '1', true)

WebUI.click(findTestObject('Object Repository/Page_Parcel Delivery System/button__1'))

assertEquals('ทำการบันทึกสถานะพัสดุสำเร็จ', WebUI.getAlertText())

WebUI.closeBrowser()


